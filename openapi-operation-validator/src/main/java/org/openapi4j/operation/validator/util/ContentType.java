package org.openapi4j.operation.validator.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ContentType {
  // Those following reg expressions must capture only the content type value
  // WITHOUT any additional properties (;charset=... obviously)
  private static final Pattern JSON_PATTERN = Pattern.compile("(?:application|text)/(?:.+\\+)?json");
  private static final Pattern XML_PATTERN = Pattern.compile("(?:application|text)/(?:.+\\+)?xml");
  private static final Pattern CHARSET_PATTERN = Pattern.compile("(?:charset=)(.*)");
  private static final Pattern PARAMETERS_PATTERN = Pattern.compile("; ?([^;\\s]+)=([^;\\s]+)");
  private static final String CHARSET_PARAMETER = "charset";

  private ContentType() {
  }

  /**
   * @param contentType The given content type to check.
   * @return {@code true} if content type is json (i.e pseudo (application|text)/(json|*+json)).
   * {@code false} otherwise.
   */
  public static boolean isJson(final String contentType) {
    if (contentType == null) return false;
    return JSON_PATTERN.matcher(contentType.toLowerCase()).matches();
  }

  /**
   * @param contentType The given content type to check.
   * @return {@code true} if content type is form URL encoded (i.e application/x-www-form-urlencoded).
   * {@code false} otherwise.
   */
  public static boolean isFormUrlEncoded(final String contentType) {
    if (contentType == null) return false;
    return contentType.toLowerCase().startsWith("application/x-www-form-urlencoded");
  }

  /**
   * @param contentType The given content type to check.
   * @return {@code true} if content type is form data (i.e multipart/form-data).
   * {@code false} otherwise.
   */
  public static boolean isMultipartFormData(final String contentType) {
    if (contentType == null) return false;

    return
      contentType.toLowerCase().startsWith("multipart/form-data") ||
        contentType.toLowerCase().startsWith("multipart/mixed");
  }

  /**
   * @param contentType The given content type to check.
   * @return {@code true} if content type is xml (i.e pseudo (application|text)/(xml|*+xml)).
   * {@code false} otherwise.
   */
  public static boolean isXml(final String contentType) {
    if (contentType == null) return false;
    return XML_PATTERN.matcher(contentType.toLowerCase()).matches();
  }

  /**
   * @param contentType The given content type to check.
   * @return The defined charset for the current content type.
   * 'utf-8' if none defined.
   */
  public static String getCharSet(String contentType) {
    if (contentType == null) return StandardCharsets.UTF_8.name();

    Matcher matcher = CHARSET_PATTERN.matcher(contentType);
    if (matcher.find()) {
      String charset = matcher.group(1).trim();
      if (Charset.isSupported(charset)) {
        return charset;
      }
    }

    return StandardCharsets.UTF_8.name();
  }

  /**
   * @param contentType The given content type to check.
   * @return The defined charset for the current content type.
   * 'utf-8' if none defined.
   */
  public static String getCharSetOrNull(String contentType) {
    if (contentType == null) return null;

    Matcher matcher = CHARSET_PATTERN.matcher(contentType);
    if (matcher.find()) {
      String charset = matcher.group(1).trim();
      if (Charset.isSupported(charset)) {
        return charset;
      }
    }

    return null;
  }

  /**
   *
   * @param contentType The given content type to check.
   * @return All parameters of the given content type, including its charset if it is supported.
   */
  public static Map<String, String> getParameters(String contentType) {
    Map<String, String> parameters = new HashMap<>();

    Matcher m = PARAMETERS_PATTERN.matcher(contentType.toLowerCase());
    while (m.find()) {
      if (m.groupCount() == 2) {
        String attribute = m.group(1);
        String value = m.group(2);

        if (!CHARSET_PARAMETER.equals(attribute) || Charset.isSupported(value)) {
          parameters.put(attribute, value);
        }
      }
    }

    return Collections.unmodifiableMap(parameters);
  }

  public static String getTypeOnly(String contentType) {
    if (contentType == null) return null;
    final int endIndex = contentType.indexOf(';');
    if (endIndex == -1) {
      return contentType;
    } else {
      return contentType.substring(0, endIndex).toLowerCase();
    }
  }
}
