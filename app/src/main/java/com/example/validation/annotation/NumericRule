package com.example.validation.annotation;

import com.example.validation.validator.ValidationRule;
import org.apache.commons.lang3.StringUtils;

public class NumericRule implements ValidationRule<Numeric> {

  @Override
  public boolean isValid(Numeric annotation, Object object) {
    if (object != null && object instanceof String) {
      String target = (String) object;
      if (!StringUtils.isNumeric(target)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String getMessage(Numeric annotation) {
    return annotation.message();
  }
}
