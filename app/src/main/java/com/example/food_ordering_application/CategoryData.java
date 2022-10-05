package com.example.food_ordering_application;

public class CategoryData
{
    private int categoryImageId;
    private String categoryName;

    public CategoryData(int categoryImageId, String categoryName)
    {
        this.categoryImageId = categoryImageId;
        this.categoryName = categoryName;
    }

    public int getCategoryImageId()
    {
        return categoryImageId;
    }

    public void setCategoryImageId(int categoryImageId) {
        this.categoryImageId = categoryImageId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
