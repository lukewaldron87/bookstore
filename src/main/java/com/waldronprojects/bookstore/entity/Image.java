package com.waldronprojects.bookstore.entity;
import javax.persistence.*;

@Entity
@Table(name="image")
public class Image {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /*@Column(name = "product_id")
    private long productId;*/

    @Column(name = "file_url")
    private String fileUrl;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    public Image () {
    }

    public Image (long id, String fileUrl) {
        this.id = id;
        this.fileUrl = fileUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Image {")
                .append(" id='").append(id).append("'")
                .append(", fileUrl='").append(fileUrl).append("'")
                .append("}");
        return stringBuilder.toString();
    }
}
