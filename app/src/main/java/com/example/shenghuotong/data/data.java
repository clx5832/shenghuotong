package com.example.shenghuotong.data;

import androidx.annotation.NonNull;

public class data {
    private String name;
    private String possword;

    public data(String name, String password) {
        this.name=name;
        this.possword=password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPossword() {
        return possword;
    }

    public void setPossword(String possword) {
        this.possword = possword;
    }

    @NonNull
    @Override
    public String toString() {
        return "info{" +
                "name='" + name + '\'' +
                ", password='" + possword + '\'' +
                '}';
    }
}
