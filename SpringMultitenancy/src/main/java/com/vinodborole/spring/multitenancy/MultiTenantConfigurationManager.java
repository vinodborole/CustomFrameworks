/**
 * 
 */
package com.vinodborole.spring.multitenancy;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.vinodborole.spring.dao.master.IOrganizationDao;
import com.vinodborole.spring.models.master.MailInfo;
import com.vinodborole.spring.models.master.Organizations;
import com.vinodborole.spring.models.master.TenantInfo;

/**
 * @author VINOD
 *
 */
public class MultiTenantConfigurationManager {

    public static final int MASTER_DB_ID = 0;

    private static final Logger logger = Logger.getLogger(MultiTenantConfigurationManager.class);
    private final Map<Object, Object> targetDataSources = new ConcurrentHashMap<Object, Object>();
    private final Map<Object, Object> tenantMailProperties = new ConcurrentHashMap<Object, Object>();

    public MultiTenantConfigurationManager(final IOrganizationDao orgDao) {
        for (final Organizations org : orgDao.getAllOrganizations()) {
            addTenantInformation(org.getTenantInfo());
            addMailProperties(org);
        }
        logger.info("Tenant information size : " + targetDataSources.size());
        logger.info("Mail properties size : " + tenantMailProperties.size());
    }

    private void addTenantInformation(final TenantInfo tenantInfo) {
        // TODO: Reading the Property file should ideally be done at the app
        // context xml, and the
        // properties can be wired using SpEL. This will eliminate multiple
        // reads
        final Resource resource = new ClassPathResource("database.properties");
        try {
            final Properties props = PropertiesLoaderUtils.loadProperties(resource);
            final String url = "jdbc:" + tenantInfo.getType() + "://" + tenantInfo.getHost() + ":" + tenantInfo.getPort() + "/" + tenantInfo.getName();
            final String username = tenantInfo.getUsername();
            final String password = tenantInfo.getPassword();
            final DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(props.getProperty("database.driverClassName"));
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            targetDataSources.put(tenantInfo.getTenantid(), dataSource);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private void addMailProperties(final Organizations org) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        Properties javaMailProperties = new Properties();
        MailInfo mailInfo = org.getMailInfo();
        javaMailProperties.put("mail.smtp.host", mailInfo.getHost());
        javaMailProperties.put("mail.smtp.auth", mailInfo.isAuth());
        javaMailProperties.put("mail.smtp.port", String.valueOf(mailInfo.getPort()));
        if (mailInfo.isSsl()) {
            javaMailProperties.put("mail.smtp.starttls.enable", mailInfo.isSsl());
            javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        sender.setJavaMailProperties(javaMailProperties);
        sender.setUsername(mailInfo.getUsername());
        sender.setPassword(mailInfo.getPassword());
        tenantMailProperties.put(org.getOrganizationid(), sender);
    }

    public Map<Object, Object> getTargetDataSources() {
        return Collections.unmodifiableMap(targetDataSources);
    }

    public Map<Object, Object> getTenantMailProperties() {
        return Collections.unmodifiableMap(tenantMailProperties);
    }

}
