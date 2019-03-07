package com.example.demo.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class JacksonConfiguration {

  @Bean
  public ObjectMapper jacksonObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();

    SimpleModule simpleModule = new SimpleModule();
    simpleModule.addSerializer(new JsonSerializer<Date>() {

      @Override
      public Class<Date> handledType() {
        return Date.class;
      }

      @Override
      public void serialize(Date value, JsonGenerator gen,
                            SerializerProvider serializers) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        gen.writeString(sdf.format(value));
      }

    });

    objectMapper.registerModule(simpleModule);
    return objectMapper;
  }

}
