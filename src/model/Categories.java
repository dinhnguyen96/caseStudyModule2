package model;

import java.io.Serializable;

public class Categories implements Serializable
{
    private Long id;

    private String categoriesCode;

    private String categoriesName;


    public Categories(Long id, String categoriesCode, String categoriesName)
    {
        this.id = id;
        this.categoriesCode = categoriesCode;
        this.categoriesName = categoriesName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoriesCode() {
        return categoriesCode;
    }

    public void setCategoriesCode(String categoriesCode) {
        this.categoriesCode = categoriesCode;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }
}
