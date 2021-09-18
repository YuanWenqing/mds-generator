package xyz.codemeans.mybatis.generator.impl;

import com.google.common.base.Strings;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import xyz.codemeans.mybatis.generator.annotation.ExcludeGeneration;
import xyz.codemeans.mybatis.generator.config.GenerationDef;

/**
 * @author yuanwq
 */
public class TypeProcessor {

  private final NamingProcessor namingProcessor = new NamingProcessor();
  private final FieldProcessor fieldProcessor = new FieldProcessor();

  public TypeGeneration process(Class<?> type, GenerationDef def) {
    TypeGeneration generation = new TypeGeneration();
    // all names
    generation
        .setPackageName(namingProcessor.packageName(type, def))
        .setSqlSupportTypeName(
            namingProcessor.sqlSupportTypeName(type, def.getSqlSupportTypeNaming()))
        .setSqlTableTypeName(namingProcessor.sqlTableTypeName(type, def.getSqlTableTypeNaming()))
        .setSqlTableInstanceName(
            namingProcessor.sqlTableInstanceName(type, def.getSqlTableInstanceNaming()))
        .setQualifiedTableName(namingProcessor.qualifiedTableName(type, def));
    generation.setTableName(namingProcessor.tableName(generation.getQualifiedTableName()));
    // fields
    List<Field> fields = findAllFields(type);
    Collection<String> imports = findAllImports(fields);
    List<FieldGeneration> fieldGenerations = fields.stream()
        .map(field -> generateField(field, def))
        .collect(Collectors.toList());
    // content
    StringBuilder sb = new StringBuilder();
    sb.append("/* Generated by mds-generator. DO NOT EDIT! */\n\n");
    sb.append(String.format("package %s;\n\n", generation.getPackageName()));
    sb.append(imports.stream()
        .map(s -> String.format("import %s;\n", s))
        .collect(Collectors.joining()));
    sb.append("\n");
    sb.append(String.format("public final class %s {\n", generation.getSqlSupportTypeName()));
    String indent = Strings.repeat(" ", def.getIndentSize());
    sb.append(indent)
        .append(String.format("public static final %s %s = new %s();\n",
            generation.getSqlTableTypeName(), generation.getSqlTableInstanceName(),
            generation.getSqlTableTypeName()));
    for (FieldGeneration field : fieldGenerations) {
      sb.append(indent)
          .append(String.format("public static final SqlColumn<%s> %s = %s.%s;\n",
              field.getType().getSimpleName(), field.getSqlSupportFieldName(),
              generation.getSqlTableInstanceName(), field.getSqlTableFieldName()));
    }
    sb.append("\n")
        .append(indent)
        .append(String.format("public static final class %s extends SqlTable {\n",
            generation.getSqlTableTypeName()));
    for (FieldGeneration field : fieldGenerations) {
      sb.append(indent).append(indent)
          .append(String.format("public final SqlColumn<%s> %s = column(\"%s\", JDBCTType.%s);\n",
              field.getType().getSimpleName(), field.getSqlTableFieldName(),
              field.getColumnName(), field.getJdbcType().name()));
    }
    sb.append("\n")
        .append(indent).append(indent)
        .append(String.format("public %s() { super(\"%s\"); }\n",
            generation.getSqlTableTypeName(), generation.getQualifiedTableName()))
        .append(indent).append("}\n")
        .append("}");
    generation.setContent(sb.toString());
    return generation;
  }

  private FieldGeneration generateField(Field field, GenerationDef def) {
    FieldGeneration generation = new FieldGeneration(field);
    generation
        .setColumnName(fieldProcessor.column(field, def.getColumnNaming()))
        .setType(fieldProcessor.fieldType(field))
        .setSqlSupportFieldName(
            fieldProcessor.sqlSupportFieldName(field, def.getSqlSupportFieldNaming()))
        .setSqlTableFieldName(
            fieldProcessor.sqlTableFieldName(field, def.getSqlTableFieldNaming()));
    return generation;
  }

  private Collection<String> findAllImports(List<Field> fields) {
    Set<String> imports = new LinkedHashSet<>();
    imports.add("java.sql.JDBCType");
    imports.add("org.mybatis.dynamic.sql.SqlColumn");
    imports.add("org.mybatis.dynamic.sql.SqlTable");
    for (Field field : fields) {
      Class<?> type = field.getType();
      if (type.isArray()) {
        type = type.getComponentType();
      }
      if (type.getPackage().equals("java.lang")
          || type.isPrimitive()) {
        continue;
      }
      imports.add(type.getName());
    }
    return imports;
  }

  private List<Field> findAllFields(Class<?> type) {
    List<Field> fields = new ArrayList<>();
    for (Field field : type.getDeclaredFields()) {
      if (Modifier.isStatic(field.getModifiers())
          || field.getAnnotation(ExcludeGeneration.class) != null) {
        continue;
      }
      fields.add(field);
    }
    return fields;
  }

}