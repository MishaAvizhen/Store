package com.store.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "tags")
    private String tags;
    @OneToMany(mappedBy = "item", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    private Set<OrderItem> itemsList = new HashSet<>();

    public Item() {
    }

    public Item(String title, String description, String tags, Set<OrderItem> itemsList) {
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.itemsList = itemsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<OrderItem> getItemsList() {
        return itemsList;
    }

    public void setItemsList(Set<OrderItem> itemsList) {
        this.itemsList = itemsList;
    }
}

