package com.xl0e.nutric.dao;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.xl0e.nutric.model.MenuGroup;

@Service
public class MenuGroupDao extends AbstractDao<Integer, MenuGroup> {

    public int deleteById(Integer id) {
        return createQuery("delete from MenuGroup p where p.id in :ids").setParameterList("ids", Arrays.asList(id)).executeUpdate();
    }

}
