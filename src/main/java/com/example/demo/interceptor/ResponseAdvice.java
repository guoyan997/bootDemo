package com.example.demo.interceptor;

import com.example.demo.domain.ViewResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
  @Override
  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                ServerHttpRequest request, ServerHttpResponse response) {
    response.getHeaders().set("Content-Type", "application/json");
    // String 类型的需要特殊处理一下
    if (body instanceof String) {
      ObjectMapper mapper = new ObjectMapper();
      String json = null;
      try {
        json = mapper.writeValueAsString(new ViewResult(ViewResult.RIGHT_CODE, body));
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
      return json;
    } else if (body instanceof ViewResult) {
      return body;
    } else {
      return new ViewResult(ViewResult.RIGHT_CODE, body);
    }
  }
}
