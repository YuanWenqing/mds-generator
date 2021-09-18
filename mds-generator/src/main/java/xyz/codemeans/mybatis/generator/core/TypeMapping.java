package xyz.codemeans.mybatis.generator.core;

import com.google.common.primitives.Primitives;
import java.lang.reflect.Field;
import java.sql.JDBCType;
import xyz.codemeans.mybatis.generator.annotation.MdsColumn;
import xyz.codemeans.mybatis.generator.config.TypeConfig;

/**
 * @author yuanwq
 */
public class TypeMapping {

  private final TypeConfig config;

  public TypeMapping(TypeConfig config) {
    this.config = config;
  }

  public Class<?> sqlColumnType(Field field) {
    MdsColumn mdsColumn = field.getAnnotation(MdsColumn.class);
    if (mdsColumn != null && !Class.class.equals(mdsColumn.sqlColumnType())) {
      return mdsColumn.sqlColumnType();
    }
    return mapToSqlColumnType(field.getType());
  }

  public Class<?> mapToSqlColumnType(Class<?> type) {
    if (type.isArray()) {
      return type;
    } else if (type.isPrimitive()) {
      return Primitives.wrap(type);
    } else {
      return type;
    }
  }

  public JDBCType jdbcType(Field field) {
    MdsColumn mdsColumn = field.getAnnotation(MdsColumn.class);
    if (mdsColumn != null) {
      return mdsColumn.jdbcType();
    }
    return mapToJdbcType(field.getType());
  }

  public JDBCType mapToJdbcType(Class<?> type) {
    JDBCType jdbcType = config.getJDBCType(type);
    if (jdbcType != null) {
      return jdbcType;
    }
    if (type.equals(byte[].class) || type.equals(Byte[].class)) {
      return JDBCType.BLOB;
    }
    if (type.isEnum() && config.getEnumJdbcType() != null) {
      return config.getEnumJdbcType();
    }
    return JDBCType.VARCHAR;
  }

}
