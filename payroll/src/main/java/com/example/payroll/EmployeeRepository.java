package com.example.payroll;

import org.springframework.data.jpa.repository.JpaRepository;

// jpaRepository를 상속받고 엔티티,pk를 추가해주면 알아서 jpa가 쿼리문을 생성해준다
interface  EmployeeRepository extends JpaRepository<Employee, Long> {

}
