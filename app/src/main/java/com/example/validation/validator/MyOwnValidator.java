package com.example.validation.validator;

import com.example.validation.annotation.NotEmpty;
import com.example.validation.annotation.NotEmptyRule;
import com.example.validation.annotation.Numeric;
import com.example.validation.annotation.NumericRule;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyOwnValidator {
  private final Map<Class<? extends Annotation>, ValidationRule> RULE_MAP = registerRuleMap();

  public List<ValidationResult> validate(Object object) {
    Class<?> cls = object.getClass();
    Field[] fields = cls.getDeclaredFields();
    List<ValidationResult> resultList = new ArrayList<>();
    for (Field field : fields) {
      field.setAccessible(true);
      Annotation[] annotations = field.getAnnotations();
      if (annotations.length == 0) {
        continue;
      }

      Object fieldValue = getValue(field, object);
      for (Annotation annotation : annotations) {
        ValidationResult result = doCheck(annotation, field.getName(), fieldValue);
        if (result.hasError()) {
          resultList.add(result);
        }
      }
    }
    return resultList;
  }

  private ValidationResult doCheck(Annotation annotation, String fieldName, Object fieldValue) {
    if (RULE_MAP.containsKey(annotation.annotationType())) {
      ValidationRule rule = RULE_MAP.get(annotation.annotationType());
      boolean isValid = rule.isValid(annotation, fieldValue);
      if (!isValid) {
        String errorMsgKey = rule.getMessage(annotation);
        return new ValidationResult(fieldName, errorMsgKey);
      }
    }
    return ValidationResult.noError();
  }

  private Object getValue(Field field, Object object) {
    try {
      return field.get(object);
    } catch (IllegalAccessException e) {
      throw new IllegalStateException(e);
    }
  }

  private Map<Class<? extends Annotation>, ValidationRule> registerRuleMap() {
    Map<Class<? extends Annotation>, ValidationRule> map = new HashMap<>();
    map.put(NotEmpty.class, new NotEmptyRule());
    map.put(Numeric.class, new NumericRule());
    return map;
  }
}
