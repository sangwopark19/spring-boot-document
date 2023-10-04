package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue filtering() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        //JacksonValue에 엔티티 담기
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        // 필터 방식 생성
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
        // JacksonValue에 넣을 필더로 빈과 필터를 추가
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        // JacksonValue에 필터 넣기
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList() {
        List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value3", "value4", "value5"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        Set<String> filter = new HashSet<>();
        filter.add("field2");
        filter.add("field3");
        // 메서드로 따로 뺌
        FilterProvider filters = filterProvider(filter);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    private FilterProvider filterProvider(Set<String> filters) {
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept(filters);
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", simpleBeanPropertyFilter);
        return filterProvider;
    }

}
