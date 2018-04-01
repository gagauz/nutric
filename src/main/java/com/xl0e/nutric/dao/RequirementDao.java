package com.xl0e.nutric.dao;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.xl0e.nutric.model.Requirement;

@Service
public class RequirementDao extends AbstractDao<Integer, Requirement> {

    public int removeById(Collection<Integer> ids) {
        return createQuery("delete from Requirement p where p.id in :ids").setParameterList("ids", ids).executeUpdate();
    }

}
