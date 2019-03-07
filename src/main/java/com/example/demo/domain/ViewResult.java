package com.example.demo.domain;


public class ViewResult {
  public static int ERROR_CODE = 0;
  public static int RIGHT_CODE = 1;

  private int code;
  private String msg;
  private Object result;

  public ViewResult() {
  }

  public ViewResult(int code, Object result) {
    this.code = code;
    this.result = result;
  }

  public ViewResult(int code) {
    this.code = code;
  }

  public ViewResult(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public ViewResult(int code, String msg, Object result) {
    this.code = code;
    this.msg = msg;
    this.result = result;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Object getResult() {
    return result;
  }

  public void setResult(Object result) {
    this.result = result;
  }
}
