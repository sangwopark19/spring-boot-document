package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    @JsonIgnore
    // 게시물의 세부정보를 가져올때 사용자의 세부정보를 안가져오게 하려는 어노테이션
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Post() {

    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
