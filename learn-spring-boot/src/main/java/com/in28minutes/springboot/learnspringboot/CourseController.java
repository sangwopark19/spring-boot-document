package com.in28minutes.springboot.learnspringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {

    @GetMapping("/courses")
    public List<Course> retrieveAllCourses() {
        return Arrays.asList(
                new Course(1, "sdfjalskdjfkl", "in28minutes"),
                new Course(1, "Learn DevOps", "in28minutes"),
                new Course(1, "Learn DevOps", "in28minutes"),
                new Course(1, "hihi", "in28minutes"),
                new Course(1, "hihi", "in28minutes")

        );
    }

}
