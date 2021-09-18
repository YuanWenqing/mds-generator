package xyz.codemeans.mybatis.generator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.JDBCType;

/**
 * specify name, jdbcType of column if needed. Or, name will be generated automatically and jdbcType
 * will be derived by the type of annotated field.
 *
 * @author: yuanwq
 * @date: 2021/9/17
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MdsColumn {

  /**
   * specify column name.
   */
  String name() default "";

  JDBCType jdbcType();
}
