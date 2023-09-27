package com.example.payroll;

// 이 익셉션이 던져지면 spring mvc 구성의 추가정보가 http 404를 렌더링 하는데 사용된다.
 class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("직원을 찾을 수 없습니다 " + id);
    }
}
