package com.xl0e.nutric.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.tapestry5.security.api.AccessAttributes;
import org.apache.tapestry5.security.api.User;
import org.apache.tapestry5.web.services.security.SecuredAccessAttributes;

import com.xl0e.hibernate.model.Model;
import com.xl0e.util.CryptoUtils;

@Entity
@Table
public class Account extends Model implements User {
    private static final AccessAttributes USER = new SecuredAccessAttributes(new String[] { "user" });
    private String usernameHash;
    private String passwordHash;
    private HashType hashType;
    private List<MenuSet> menuSets;

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

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, orphanRemoval = true)
    public List<MenuSet> getMenuSets() {
        return menuSets;
    }

    public void setMenuSets(List<MenuSet> menuSets) {
        this.menuSets = menuSets;
    }

    @Transient
    public void setUsername(String string) {
        setUsernameHash(CryptoUtils.createSHA512String(string));
    }

    @Transient
    public void setPassword(String string) {
        setPasswordHash(CryptoUtils.createSHA512String(string));
    }

    @Transient
    @Override
    public AccessAttributes getAccessAttributes() {
        return USER;
    }
}
