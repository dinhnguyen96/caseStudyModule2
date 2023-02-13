package model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable
{
    private String username;

    private String password;

    private List<Roles> rolesList;

    public User(String username, String password, List<Roles> rolesList)
    {
        this.username = username;
        this.password = password;
        this.rolesList = rolesList;
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
