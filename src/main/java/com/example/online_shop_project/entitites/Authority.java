package com.example.online_shop_project.entitites;

import javax.persistence.*;
//Must have for authorization this implementation:
@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorityId;

    @Column(length = 50)
    private String username;

    @Column(length = 20)
    private String authority;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Authority() {
    }

    public Authority(String username, String userAuthority, User user) {
        this.username = username;
        this.authority = userAuthority;
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserAuthority() {
        return authority;
    }

    public void setUserAuthority(String userAuthority) {
        this.authority = userAuthority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
