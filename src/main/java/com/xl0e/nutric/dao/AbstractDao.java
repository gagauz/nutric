package com.xl0e.nutric.dao;

import java.io.Serializable;

import com.xl0e.hibernate.model.IModel;

public class AbstractDao<Id extends Serializable, E extends IModel<Id>> extends com.xl0e.hibernate.dao.AbstractDao<Id, E> {

}
