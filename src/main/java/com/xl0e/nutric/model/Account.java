package com.xl0e.nutric.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.xl0e.hibernate.model.Model;

@Entity
@Table
public class Account extends Model {
    private String   usernameHash;
    private String   passwordHash;
    private HashType hashType;

    public String getUsernameHash() {
        return usernameHash;
    }

    public void setUsernameHash(String usernameHash) {
        this.usernameHash = usernameHash;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Enumerated(EnumType.STRING)
    public HashType getHashType() {
        return hashType;
    }

    public void setHashType(HashType hashType) {
        this.hashType = hashType;
    }
}
