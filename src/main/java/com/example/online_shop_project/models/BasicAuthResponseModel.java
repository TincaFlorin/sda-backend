package com.example.online_shop_project.models;


import java.util.List;

public class BasicAuthResponseModel {


    private List<String> authorityList;

    public BasicAuthResponseModel(List<String> authorities) {
        this.authorityList = authorities;
    }


    public List<String> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<String> authorityList) {
        this.authorityList = authorityList;
    }

}
