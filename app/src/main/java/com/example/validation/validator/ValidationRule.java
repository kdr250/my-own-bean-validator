package com.example.validation.validator;

import java.lang.annotation.Annotation;

public interface ValidationRule<T extends Annotation> {

  boolean isValid(T annotation, Object object);

  String getMessage(T annotation);
}
