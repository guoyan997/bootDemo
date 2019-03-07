package com.example.demo.util;


import com.example.demo.domain.User;

public class UserTokenUtil {

  //TODO implement your own service
  public static User getUserByToken(String token) {
    return new User(1000L, "张三", 10);
  }
}
