package model;

import java.io.Serializable;

public class Roles implements Serializable
{
    private long id;

    private String roleCode;

    private String roleName;

    public Roles(long id, String roleCode, String roleName)
    {
        this.id = id;
        this.roleCode = roleCode;
        this.roleName = roleName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
