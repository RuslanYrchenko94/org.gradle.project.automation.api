package models.pet;

import java.util.List;

public class PetStorePetModel {

    public String getName() {

        return name;
    }
    public PetStorePetModel setName(String name) {
        this.name = name;
        return this;
    }
    public String getPhotoUrls() {

        return photoUrls;
    }
    public PetStorePetModel setPhotoUrls(String photoUrls) {
        this.photoUrls = photoUrls;
        return this;
    }
    public Integer getId() {

        return id;
    }
    public PetStorePetModel setId(Integer id) {
        this.id = id;
        return this;
    }
    public Category getCategory() {

        return category;
    }
    public PetStorePetModel setCategory(Category category) {
        this.category = category;
        return this;
    }
    public List<TagsItem> getTags() {

        return tags;
    }
    public PetStorePetModel setTags(List<TagsItem> tags) {
        this.tags = tags;
        return this;
    }
    public String getStatus() {

        return status;
    }
    public PetStorePetModel setStatus(String status) {
        this.status = status;
        return this;
    }

    private String name;
    private String photoUrls;
    private Integer id;
    private Category category;
    private List<TagsItem> tags;
    private String status;
    @Override
    public String toString() {
        return
                "{\"id\":" + id + "," +
                        "\"category\":" + category + "," +
                        "\"name\":\"" + name + "\"," +
                        "\"photoUrls\":[\"" + photoUrls + "\"]," +
                        "\"tags\":" + tags + "," +
                        "\"status\":\"" + status + "\"}";
    }
}
