package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


// 클래스 수준에서 설정하는 정적 필터링
// 보통은 클래스 수준보단 특정 필드에 @JsonIgnore 를 추가하는게 낫다
//@JsonIgnoreProperties({"field2", "field1"})
@JsonFilter("SomeBeanFilter")
public class SomeBean {
    private String field1;
    // 정적 필터링 설정어노테이션 비밀번호같이 보여주기 싫은 정보에 넣어준다
    // 보통은 이렇게 필드수준에서 설정하는걸 권장한다.
//    @JsonIgnore
    private String field2;
    private String field3;

    public SomeBean(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    @Override
    public String toString() {
        return "SomeBean{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                '}';
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }
}
