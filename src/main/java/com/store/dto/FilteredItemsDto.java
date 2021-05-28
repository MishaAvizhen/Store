package com.store.dto;

public class FilteredItemsDto {
    private String description;
    private String tags;
    private String title;

    public FilteredItemsDto(String description, String tags, String title) {
        this.description = description;
        this.tags = tags;
        this.title = title;
    }

    public FilteredItemsDto() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
