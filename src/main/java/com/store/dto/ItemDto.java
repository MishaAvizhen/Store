package com.store.dto;

public class ItemDto {

    private String title;
    private String description;
    private String tags;

    public ItemDto(String title, String description, String tags) {
        this.title = title;
        this.description = description;
        this.tags = tags;
    }

    public ItemDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
