package com.gmail.kolesnyk.zakhar.user;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The {@code User} JPA entity that mapped on table "users"
 *
 * @author Kolesnyk Zakhar
 * @see com.gmail.kolesnyk.zakhar.user.UserDao
 * @see com.gmail.kolesnyk.zakhar.BaseDao
 * @see com.gmail.kolesnyk.zakhar.AbstractDao
 * @since JDK1.8
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "phone")
    private String phone;

    public User() {
    }

    public User(String userName, String phone) {
        this.userName = userName;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
