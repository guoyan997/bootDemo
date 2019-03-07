package com.example.demo.resource;


import com.example.demo.annotation.MyAnnotation;
import com.example.demo.domain.ThreadLocalUtil;
import com.example.demo.domain.User;
import com.example.demo.exception.MyException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/InterfacePlatform/Client")
@MyAnnotation
public class MyResource {

  @PostMapping("/hello")
  public String sayHello(@RequestBody User user) {
    User userLocal = ThreadLocalUtil.getUser();
    return userLocal.getName() + " say Hello to " + user.getName();
  }

  @CrossOrigin
  @RequestMapping("/str")
  public String getStr(HttpServletRequest request, HttpServletResponse response) {
    Cookie[] cookies = request.getCookies();
    response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
    response.setHeader("Access-Control-Allow-Credentials", "true");
    System.out.print(cookies);
    return "Hello World";
  }

  @GetMapping("/user")
  public String getUser(@RequestParam("name") String name, @RequestParam("age") String age) {

    return "name"+ name + "age" + age;
  }
  @PostMapping("/user1")
  public String getUser1(@RequestParam("name") String name, @RequestParam("age") String age) {
    return "name"+ name + "age" + age;
  }
  @PostMapping("/user2")
  public String getUser2(@RequestParam("arr") String[] arr) {
    return "arr"+ arr;
  }

  @GetMapping("/ex")
  public void throwEx() throws MyException {
    throw new MyException("发生异常了");
  }

}
