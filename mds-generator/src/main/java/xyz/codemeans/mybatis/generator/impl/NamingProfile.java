package xyz.codemeans.mybatis.generator.impl;

import lombok.Data;

/**
 * @author yuanwq
 */
@Data
public class NamingProfile {

  /**
   * prefix to prepend to auto-generated name
   */
  private String prefix;
  /**
   * suffix to append to auto-generated name
   */
  private String suffix;
  /**
   * regex for replace target in auto-generated name
   */
  private String replaceRegex;
  /**
   * replacement for replacing in auto-generated name
   */
  private String replacement;
}
