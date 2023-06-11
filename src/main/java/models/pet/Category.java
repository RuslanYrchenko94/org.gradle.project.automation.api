package models.pet;

public class Category{


    private String categoryName;
    private int categoryId;

    public String getCategoryName() {
        return categoryName;
    }

    public Category setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public Category setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        return this;
    }



    @Override
    public String toString(){
        return
                "{\"id\":"+categoryId+",\"name\":\""+categoryName+"\"}";
    }
}