/**
 * 
 */
package com.vinodborole.spring.dao.master;

import java.util.List;

import org.hibernate.criterion.Criterion;

/**
 * @author VINOD
 *
 */
public interface IMasterAbstractDao<E, I> {
    public E findById(I id);

    public void delete(E e);

    public List<E> findByCriteria(Criterion criterion);
}
