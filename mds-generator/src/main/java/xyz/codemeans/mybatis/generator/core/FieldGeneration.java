package xyz.codemeans.mybatis.generator.core;

import java.lang.reflect.Field;
import java.sql.JDBCType;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yuanwq
 */
@Data
@Accessors(chain = true)
public class FieldGeneration {

  private final Field field;
  private Class<?> sqlColumnType;
  private String sqlSupportFieldName;
  private String sqlTableFieldName;
  private String columnName;
  private JDBCType jdbcType;
}
