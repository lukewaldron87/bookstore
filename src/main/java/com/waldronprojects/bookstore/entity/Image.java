package com.waldronprojects.bookstore.entity;
import javax.persistence.*;

@Entity
@Table(name="image")
public class Image {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "productId")
    private long productId;

    @Column(name = "fileUrl")
    private String fileUrl;

    public Image () {
    }

    public Image (long id, long productId, String fileUrl) {
        this.id = id;
        this.productId = productId;
        this.fileUrl = fileUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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
                .append(", productId='").append(productId).append("'")
                .append(", fileUrl='").append(fileUrl).append("'")
                .append("}");
        return stringBuilder.toString();
    }
}
