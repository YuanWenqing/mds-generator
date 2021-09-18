package xyz.codemeans.mybatis.generator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify catalog, schema, table of the annotated type if needed. Or table name will be generated
 * automatically according to config.
 *
 * @author yuanwq
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableDef {

  /**
   * table name, like: "my_table", "my_db.my_table"
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
