package com.example.marvelheroes.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "heroes_table")
public class Hero {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String description;

    private String image_uri;

    public Hero(String name, String description, String image_uri) {
        this.name = name;
        this.description = description;
        this.image_uri = image_uri;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }
}
