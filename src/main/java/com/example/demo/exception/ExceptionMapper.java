package com.example.demo.exception;

import com.example.demo.domain.ViewResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@ControllerAdvice
public class ExceptionMapper {

  @ExceptionHandler(MyException.class)
  @ResponseBody
  public ViewResult handleMyException(HttpServletRequest req,
                                      HttpServletResponse response,
                                      MyException exception) {
    System.out.println("MyException");
    return new ViewResult(ViewResult.ERROR_CODE, exception.getMessage());
  }
}
