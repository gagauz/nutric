package com.xl0e.nutric.dao;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.xl0e.nutric.model.Product;

@Service
public class ProductDao extends AbstractDao<Integer, Product> {

    public int removeById(Collection<Integer> ids) {
        return createQuery("delete from Product p where p.id in :ids").setParameterList("ids", ids).executeUpdate();
    }

}
