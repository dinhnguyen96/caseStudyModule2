package model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable
{
    private Long id;

    private String userCode;

    private String username;

    private String password;

    private List<Roles> rolesList;

    public User(Long id, String userCode,String username, String password, List<Roles> rolesList)
    {
        this.id = id;
        this.userCode = userCode;
        this.username = username;
        this.password = password;
        this.rolesList = rolesList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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

    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }
}
