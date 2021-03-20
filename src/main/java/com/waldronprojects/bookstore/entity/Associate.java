package com.waldronprojects.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;

/**
 * A person related to a product
 * eg Oscar Wild is an associate to the book The Picture of Dorian Gray
 */
@Entity
@Table(name = "associate")
public class Associate {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "bio")
    private String bio;

    @ManyToMany
    @JoinTable(name = "associate_associate_types",
            joinColumns = @JoinColumn(name = "associate_id"),
            inverseJoinColumns = @JoinColumn(name = "associate_type_id"))
    private Collection<AssociateType> associateTypeCollection;


    public Associate () {
    }


    public Associate (long id, String name, String bio,
                      Collection<AssociateType> associateTypeCollection) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.associateTypeCollection = associateTypeCollection;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String description) {
        this.bio = description;
    }

    public Collection<AssociateType> getAssociateTypeCollection() {
        return associateTypeCollection;
    }

    public void setAssociateTypeCollection(Collection<AssociateType> associateTypeCollection) {
        this.associateTypeCollection = associateTypeCollection;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Associate {")
                .append(" id='").append(id).append("'")
                .append(", name='").append(name).append("'")
                .append(", bio='").append(bio).append("'")
                .append(", associateTypeCollection='")
                    .append(associateTypeCollection.toString()).append("'")
                .append("}");
        return stringBuilder.toString();
    }
}
