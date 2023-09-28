package com.example.validation.example;

import com.example.validation.annotation.NotEmpty;
import com.example.validation.annotation.Numeric;

public class ExampleForm {

  @NotEmpty(message = "空はだめです")
  private String name;

  @NotEmpty(message = "空はだめです")
  @Numeric(message = "数字以外を入力しないでください")
  private String age;

  public ExampleForm(String name, String age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public String getAge() {
    return age;
  }
}
