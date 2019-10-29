package com.song.util.annotation;

import com.song.util.enums.BooleanType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Song on 2019/07/26.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ImportField {
    int DEFAULT_COLUMN = 0;
    String DATE_PATTERN_1 = "yyyyMMdd";
    String DATE_PATTERN_2 = "yyyy-MM-dd";

    int column() default DEFAULT_COLUMN;

    String datePattern() default "";

    BooleanType booleanType() default BooleanType.true_false;

}
