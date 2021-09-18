package xyz.codemeans.mybatis.generator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.JDBCType;

/**
 * Exclusively config of generation for field. If some setting is unset, config will fallback into
 * global config.
 *
 * @author: yuanwq
 * @date: 2021/9/17
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MdsColumn {

  /**
   * field name in SqlSupport type
   */
  String sqlSupportField() default "";

  /**
   * component type of SqlColumn in SqlSupport/SqlTable type
   */
  Class<?> sqlColumnType() default Class.class;

  /**
   * field name in SqlTable subclass
   */
  String sqlTableField() default "";

  /**
   * column name.
   */
  String column() default "";

  /**
   * {@link JDBCType}
   */
  JDBCType jdbcType();

}
