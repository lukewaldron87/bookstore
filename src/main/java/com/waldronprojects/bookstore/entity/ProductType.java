package com.waldronprojects.bookstore.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_type")
public class ProductType {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    public ProductType() {
    }

    public ProductType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ProductType(Long id, String name, String description) {
        this.id = id;
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ProductType{id=").append(id)
                     .append(", name='").append(name).append("'")
                     .append(", description='").append(description).append("'")
                     .append('}');
        return stringBuilder.toString();
    }
}
