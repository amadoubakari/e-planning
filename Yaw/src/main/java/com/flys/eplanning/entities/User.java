package com.flys.eplanning.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * @author AMADOU BAKARI
 * @since 11/05/2018
 * @todo Elle garde le profil de l'utilisateur connecté.
 * @version 1.0.0
 * @email amadou_bakari@yahoo.fr
 * @tel (+2367) 674316936/ 690660199
 */

@DatabaseTable(tableName = "user")
public class User extends BaseEntity implements Serializable {
    @DatabaseField
    private String name;

    @DatabaseField
    private String lastName;

    @DatabaseField
    private String email;

    @DatabaseField
    private String phoneNumber;

    @DatabaseField
    private String login;

    @DatabaseField
    private String password;

    public User() {
    }

    public User(String name, String lastName, String email, String phoneNumber, String login, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (!getName().equals(user.getName())) return false;
        if (!getLastName().equals(user.getLastName())) return false;
        if (!getEmail().equals(user.getEmail())) return false;
        if (!getPhoneNumber().equals(user.getPhoneNumber())) return false;
        if (!getLogin().equals(user.getLogin())) return false;
        return getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPhoneNumber().hashCode();
        result = 31 * result + getLogin().hashCode();
        result = 31 * result + getPassword().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
