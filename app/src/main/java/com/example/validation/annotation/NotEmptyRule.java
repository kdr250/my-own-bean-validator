package com.example.validation.annotation;

import com.example.validation.validator.ValidationRule;
import org.apache.commons.lang3.StringUtils;

public class NotEmptyRule implements ValidationRule<NotEmpty> {

  @Override
  public boolean isValid(NotEmpty annotation, Object object) {
    if (object == null || (object instanceof String && StringUtils.isEmpty((String) object))) {
      return false;
    }
    return true;
  }

  @Override
  public String getMessage(NotEmpty annotation) {
    return annotation.message();
  }
}
