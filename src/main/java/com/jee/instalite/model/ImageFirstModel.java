/*
package com.jee.instalite.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name ="image")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name ="name")
    private String name ;

    @Column(name ="type")
    private String type ;
    @Column(nullable = false)
    private Long userId;

    @Lob
    @Column(name = "image",length = 1000)
    private byte[] imageData;


    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

*/
/*    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return name;
    }

    public void setFileName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }*//*

}
*/
