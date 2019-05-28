package com.mds;

public class CurrentUser {
    private String acc_type;
    private String username;
    private Integer group;
    private String cnp;
    private String cnpPass;

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getCnpPass() {
        return cnpPass;
    }

    public void setCnpPass(String cnpPass) {
        this.cnpPass = cnpPass;
    }

    public CurrentUser(String acc_type, String username) {
        this.acc_type = acc_type;
        this.username = username;
    }

    public String getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(String acc_type) {
        this.acc_type = acc_type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
