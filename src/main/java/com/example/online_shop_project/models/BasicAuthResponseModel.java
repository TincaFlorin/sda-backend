package com.example.online_shop_project.models;


import com.example.online_shop_project.controllers.BaseController;

import java.util.List;

public class BasicAuthResponseModel extends BaseController {


    private List<String> authorityList;

    private String username;

    public BasicAuthResponseModel(List<String> authorities, String username) {
        this.authorityList = authorities;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<String> authorityList) {
        this.authorityList = authorityList;
    }

}
