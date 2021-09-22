package xyz.codemeans.mybatis.generator.config;

import java.sql.JDBCType;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;

/**
 * @author yuanwq
 */
@Data
public class TypeConfig {

  private JDBCType enumJdbcType = JDBCType.VARCHAR;
  private Map<String, JDBCType> type2jdbc = new LinkedHashMap<>();

  public TypeConfig() {
    // Reference: https://www.cis.upenn.edu/~bcpierce/courses/629/jdkdocs/guide/jdbc/getstart/mapping.doc.html
    // primitive
    this
        .putType2Jdbc(boolean.class, JDBCType.BOOLEAN)
        .putType2Jdbc(Boolean.class, JDBCType.BOOLEAN)
        .putType2Jdbc(char.class, JDBCType.CHAR)
        .putType2Jdbc(Character.class, JDBCType.CHAR)
        .putType2Jdbc(byte.class, JDBCType.TINYINT)
        .putType2Jdbc(Byte.class, JDBCType.TINYINT)
        .putType2Jdbc(short.class, JDBCType.SMALLINT)
        .putType2Jdbc(Short.class, JDBCType.SMALLINT)
        .putType2Jdbc(int.class, JDBCType.INTEGER)
        .putType2Jdbc(Integer.class, JDBCType.INTEGER)
        .putType2Jdbc(long.class, JDBCType.BIGINT)
        .putType2Jdbc(Long.class, JDBCType.BIGINT)
        .putType2Jdbc(float.class, JDBCType.REAL)
        .putType2Jdbc(Float.class, JDBCType.REAL)
        .putType2Jdbc(double.class, JDBCType.DOUBLE)
        .putType2Jdbc(Double.class, JDBCType.DOUBLE)
    ;
    // date & time
    this
        .putType2Jdbc(Date.class, JDBCType.DATE)
        .putType2Jdbc(java.sql.Date.class, JDBCType.DATE)
        .putType2Jdbc(Time.class, JDBCType.TIME)
        .putType2Jdbc(Timestamp.class, JDBCType.TIMESTAMP)
        .putType2Jdbc(LocalDate.class, JDBCType.DATE)
        .putType2Jdbc(LocalDateTime.class, JDBCType.TIMESTAMP)
        .putType2Jdbc("org.joda.time.DateTime", JDBCType.TIMESTAMP_WITH_TIMEZONE)
        .putType2Jdbc("org.joda.time.LocalDate", JDBCType.DATE)
        .putType2Jdbc("org.joda.time.LocalDateTime", JDBCType.TIMESTAMP)
        .putType2Jdbc("org.joda.time.LocalTime", JDBCType.TIMESTAMP)
    ;
  }

  private static String typeKey(Class<?> type) {
    if (type.isArray()) {
      return typeKey(type.getComponentType().getName()) + "-ARRAY";
    }
    return typeKey(type.getName());
  }

  private static String typeKey(String qualifiedType) {
    return qualifiedType.replace("[.$]", "-");
  }

  public TypeConfig putType2Jdbc(Class<?> type, JDBCType jdbcType) {
    type2jdbc.put(typeKey(type), jdbcType);
    return this;
  }

  public TypeConfig putType2Jdbc(String qualifiedType, JDBCType jdbcType) {
    type2jdbc.put(typeKey(qualifiedType), jdbcType);
    return this;
  }

  public JDBCType getJDBCType(Class<?> type) {
    return type2jdbc.get(typeKey(type));
  }

}
