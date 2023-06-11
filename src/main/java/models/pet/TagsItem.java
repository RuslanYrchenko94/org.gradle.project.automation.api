package models.pet;

public class TagsItem{

    private int tagId;
    private String tagName;
    public String getTagName() {
        return tagName;
    }

    public TagsItem setTagName(String tagName) {
        this.tagName = tagName;
        return this;
    }

    public int getTagId() {
        return tagId;
    }

    public TagsItem setTagId(int tagId) {
        this.tagId = tagId;
        return this;
    }

    @Override
    public String toString(){
        return
        "{\"id\":"+ tagId +",\"name\":\""+ tagName +"\"}";
    }
}
