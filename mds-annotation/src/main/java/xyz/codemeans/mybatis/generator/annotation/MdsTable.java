package xyz.codemeans.mybatis.generator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Exclusively config of generation for type. If some setting is unset, config will fallback into
 * global config.
 *
 * @author yuanwq
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MdsTable {

  /**
   * name of generated SqlSupport type
   */
  String sqlSupportType() default "";

  /**
   * name of generated static SqlTable instance in SqlSupport type
   */
  String sqlTableInstance() default "";

  /**
   * name of generated SqlTable subclass
   */
  String sqlTableType() default "";

  /**
   * name of generated Fields class
   */
  String fieldsType() default "";

  /**
   * table name
   */
  String table() default "";

  /**
   * schema name: prepended to table name if present
   */
  String schema() default "";

  /**
   * catalog name: prepended to table name if present
   */
  String catalog() default "";
}
