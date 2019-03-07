package com.example.demo.domain;

public class ThreadLocalUtil {
  private static ThreadLocal<User> threadLocal = new ThreadLocal();

  public static User getUser() {
    return threadLocal.get();
  }

  public static void setUser(User user) {
    threadLocal.set(user);
  }

  public static void remove() {
    threadLocal.remove();
  }
}
