package com.aliohyn.ustora.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class SlideItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String image;
    private String link;
    private String description;

    public SlideItem() {

    }
    public SlideItem(String name, String image, String link, String description) {
        this.name = name;
        this.image = image;
        this.link = link;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlideItem slideItem = (SlideItem) o;
        return Objects.equals(id, slideItem.id) &&
                Objects.equals(name, slideItem.name) &&
                Objects.equals(image, slideItem.image) &&
                Objects.equals(link, slideItem.link) &&
                Objects.equals(description, slideItem.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, image, link, description);
    }

    @Override
    public String toString() {
        return "SlideItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
