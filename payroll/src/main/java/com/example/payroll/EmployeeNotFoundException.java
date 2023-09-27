package com.example.payroll;

 class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("직원을 찾을 수 없습니다 " + id);
    }
}
