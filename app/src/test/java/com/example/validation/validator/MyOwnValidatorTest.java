package com.example.validation.validator;

import com.example.validation.example.ExampleForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("自作バリデータークラスのテスト")
class MyOwnValidatorTest {

  @Nested
  class バリデーションルールで検知された数だけバリデーション結果を返す {

    @Test
    void バリデーションルールで検知された数が3の場合はバリデーション結果の数は3になる() throws Exception {
      MyOwnValidator validator = new MyOwnValidator();
      ExampleForm form = new ExampleForm("", "");

      List<ValidationResult> results = validator.validate(form);

      assertEquals(3, results.size());
    }

    @Test
    void バリデーションルールで何も検知されなかった場合はバリデーション結果の数は0になる() throws Exception {
      MyOwnValidator validator = new MyOwnValidator();
      ExampleForm form = new ExampleForm("太郎", "18");

      List<ValidationResult> results = validator.validate(form);

      assertEquals(0, results.size());
    }
  }

  @Nested
  class サンプル的にいくつかのバリデーションを試す {

    @Test
    void 空文字をバリデーションできる() throws Exception {
      MyOwnValidator validator = new MyOwnValidator();
      ExampleForm form = new ExampleForm("", "18");

      List<ValidationResult> results = validator.validate(form);
      ValidationResult result = results.get(0);

      assertEquals("name", result.getFieldName());
      assertEquals("空はだめです", result.getErrorMessage());
    }

    @Test
    void 数字入力をバリデーションできる() throws Exception {
      MyOwnValidator validator = new MyOwnValidator();
      ExampleForm form = new ExampleForm("太郎", "十八");

      List<ValidationResult> results = validator.validate(form);
      ValidationResult result = results.get(0);

      assertEquals("age", result.getFieldName());
      assertEquals("数字以外を入力しないでください", result.getErrorMessage());
    }
  }
}
