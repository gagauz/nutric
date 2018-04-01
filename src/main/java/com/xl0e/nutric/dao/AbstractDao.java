package com.xl0e.nutric.dao;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.xl0e.hibernate.dao.AbstractHibernateDao;
import com.xl0e.hibernate.model.IModel;
import com.xl0e.util.CryptoUtils;

public class AbstractDao<Id extends Serializable, E extends IModel<Id>> extends AbstractHibernateDao<Id, E> {

    private static Logger LOG = LoggerFactory.getLogger(AbstractDao.class);

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.disable(MapperFeature.USE_GETTERS_AS_SETTERS);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public AbstractDao() {
        idResolverMap.put(Integer.class,
                string -> Optional.ofNullable(string)
                        .map(s -> Integer.parseInt(CryptoUtils.decryptAES(s)))
                        .orElse(null));
    }

    @Override
    public String idToString(Id id) {
        if (id instanceof Integer) {
            return CryptoUtils.encryptAES(id.toString());
        }
        return super.idToString(id);
    }

    public E copy(E original) {
        try {
            return mapper.readValue(mapper.writeValueAsBytes(original), getEntityClass());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
