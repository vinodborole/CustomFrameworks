/**
 * 
 */
package com.vinodborole.spring.models.master;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vinodborole.spring.models.AbstractEntity;

/**
 * @author vinod
 * 
 */

@Entity
@Table(name = "productrole")
public class ProductRole extends AbstractEntity {

    @Id
    @GeneratedValue
    private int productroleid;
    private String name;

    public int getProductroleid() {
        return productroleid;
    }

    public void setProductroleid(int productroleid) {
        this.productroleid = productroleid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
