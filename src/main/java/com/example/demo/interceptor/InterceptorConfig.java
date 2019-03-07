package com.example.demo.interceptor;


import com.example.demo.annotation.MyAnnotation;
import com.example.demo.domain.ThreadLocalUtil;
import com.example.demo.domain.User;
import com.example.demo.util.UserTokenUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new MyAnnotationInterceptor()).addPathPatterns("/**");
  }

  class MyAnnotationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

      //业务系统可以通过HTTP头中读取token
      String token = request.getHeader("token");

      // getUserInfo by token
      User user = UserTokenUtil.getUserByToken(token);
      // 将user信息放进线程上下文
      ThreadLocalUtil.setUser(user);

      if (handler instanceof HandlerMethod) {
        MyAnnotation methodAnnotation = ((HandlerMethod) handler).getMethod().getAnnotation(MyAnnotation.class);
        MyAnnotation classAnnotation = ((HandlerMethod) handler).getMethod()
            .getDeclaringClass().getAnnotation(MyAnnotation.class);
        if (methodAnnotation != null || classAnnotation != null) {
          // TODO your service
          System.out.println("get method with MyAnnotation");
        }
      }
      return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
      // 请求结束清除线程上下文
      ThreadLocalUtil.remove();
    }
  }
}
