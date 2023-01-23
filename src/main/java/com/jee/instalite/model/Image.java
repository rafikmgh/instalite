package com.jee.instalite.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "image")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    private String name ;
    private String type ;
    private String filePath ;

    @Column(nullable = true)
    private String description ;

    @Column(nullable = true)
    private Long userId;


    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;


}
