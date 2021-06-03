package com.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.store.constants.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @JsonIgnore
    @OneToMany(mappedBy = "orderedByUser", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<UserOrder> userOrderList = new ArrayList<>();


    public User() {
    }

    public User(String username, String password, String email, Role role, List<UserOrder> userOrderList) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.userOrderList = userOrderList;
    }

    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<UserOrder> getUserOrderList() {
        return userOrderList;
    }

    public void setUserOrderList(List<UserOrder> userOrderList) {
        this.userOrderList = userOrderList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
