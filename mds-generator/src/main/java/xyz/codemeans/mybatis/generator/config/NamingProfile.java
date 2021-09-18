package xyz.codemeans.mybatis.generator.config;

import com.google.common.base.CaseFormat;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yuanwq
 */
@Data
@Accessors(chain = true)
public class NamingProfile {

  /**
   * name format to convert from; if unset, no conversion
   */
  private CaseFormat fromFormat;
  /**
   * name format to convert to; if unset, no conversion
   */
  private CaseFormat toFormat;
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
