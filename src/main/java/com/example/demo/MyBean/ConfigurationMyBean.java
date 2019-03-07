package com.example.demo.MyBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfigurationMyBean {

  @Bean
  public MyBean getMyBean() {
    return new MyBean("myBean", "test");
  }

}

class MyBean {
  private String name;
  private String method;

  public MyBean(String name, String method) {
    this.name = name;
    this.method = method;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }
}