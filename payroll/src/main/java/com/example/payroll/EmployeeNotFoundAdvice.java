package com.example.payroll;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class EmployeeNotFoundAdvice {

    //ResponseBody 어노테이션은 이 advice 가 응답 본문에 직접 렌더링된다는 신호이다.
    //exceptionHandler 는 EmployeeNotFoundException 이 던져진 경우에만 응답하도록 advice 를  구성한다.
    // ResponseStatus 는 HttpStatus.NOT_FOUND, 즉 HTTP 404를 발행하라고 말한다.
    // Advice 본문이 콘텐츠를 생성한다. 이 경우 예외 메세지를 제공한다.
    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(EmployeeNotFoundException ex) {
        return ex.getMessage();
    }
}
