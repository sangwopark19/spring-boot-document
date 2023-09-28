package com.example.payroll;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//이 어노테이션은 클래스 내부의 메서드들이 반환된 데이터가 템플릿을 렌더링 하는 대신
// 응답 본문에 직접 기록된다는 것을 나타낸다.
@RestController
public class EmployeeController {
    // EmployeeRepository 는 생성자에 의해 컨트롤러에 주입된다.
    private final EmployeeRepository repository;
    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/employees")
    List<Employee> all() {
        return repository.findAll();
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id) {
        //EmployeeNotFoundException 은 직원을 조회했지만 찾을 수 없는 경우를 나타내는 데 사용되는 예외이다.
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        // 요청으로 새로운 직원 객체와 pk를 받는다.
        // 이후 pk로 직원검색을 한 후 값이 있으면 새로운 직원객체로 세이브를해준다
        // 없으면 새로운 직원객체에 가져온 Pk 를 넣고 세이브를한다.
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }


    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
