package com.aliohyn.ustora.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Product implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private double price;
    private double oldPrice;
    private String description;
    private Boolean topSellerShow;
    private Boolean newShow;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private Section section;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getTopSellerShow() {
        return topSellerShow;
    }

    public void setTopSellerShow(Boolean topSellerShow) {
        this.topSellerShow = topSellerShow;
    }

    public Boolean getNewShow() {
        return newShow;
    }

    public void setNewShow(Boolean newShow) {
        this.newShow = newShow;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                Double.compare(product.oldPrice, oldPrice) == 0 &&
                topSellerShow == product.topSellerShow &&
                newShow == product.newShow &&
                Objects.equals(id, product.id) &&
                name.equals(product.name) &&
                Objects.equals(image, product.image) &&
                Objects.equals(description, product.description) &&
                Objects.equals(brand, product.brand) &&
                Objects.equals(section, product.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, image, price, oldPrice, description, topSellerShow, newShow, brand, section);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", oldPrice=" + oldPrice +
                ", description='" + description + '\'' +
                ", topSellerShow=" + topSellerShow +
                ", newShow=" + newShow +
                ", brand=" + brand +
                ", section=" + section +
                '}';
    }

    public Product clone() throws CloneNotSupportedException {
        Product product = (Product) super.clone();
        product.setSection(this.section.clone());
        product.setBrand(this.brand.clone());
        return product;
    }
}
