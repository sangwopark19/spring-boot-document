package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class User {

    private Integer id;
    // @JsonProperty를 사용하면 json 응답의 이름을 커스터마이징할 수 있다.
    // 글자는 최소 2글자 이상이어야 한다
    @JsonProperty("user_name")
    @Size(min = 2, message = "이름의 길이는 최소 2글자 이상이어야 합니다")
    private String name;
    // @Past 가 달린 변수는 항상 과거 날짜여야한다
    @JsonProperty("birth_date")
    @Past(message = "생일은 과거의 날짜여야합니다")
    private LocalDate birthDate;

    public User(Integer id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
