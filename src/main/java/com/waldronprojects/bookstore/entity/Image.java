package com.waldronprojects.bookstore.entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Image {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "fileUrl")
    private String fileUrl;

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
