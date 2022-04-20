package org.openapi4j.operation.validator.util;


import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class ContentTypeTest {

  @Test
  public void shouldParseContentTypeParameters() {
    // given
    String contentType = "application/json;charset=UTF-8; profile=https://example.com/my-resource+v1;some_attribute=\"quoted-string-value\"";

    // when
    Map<String, String> parameters = ContentType.getParameters(contentType);

    // then
    Assert.assertEquals(parameters.size(), 3);
    Assert.assertEquals(parameters.get("charset"), "utf-8");
    Assert.assertEquals(parameters.get("profile"), "https://example.com/my-resource+v1");
    Assert.assertEquals(parameters.get("some_attribute"), "\"quoted-string-value\"");
  }

  @Test
  public void shouldNotParseEmptyParameters() {
    // given
    String contentType = "application/json; param= ;param2=; =some-value-but-no-attribute";

    // when
    Map<String, String> parameters = ContentType.getParameters(contentType);

    // then
    Assert.assertTrue(parameters.isEmpty());
  }

  @Test
  public void shouldNotParseInvalidCharset() {
    // given
    String contentType = "application/json; charset=INVALID-CHARSET; another-attribute=some-value";

    // when
    Map<String, String> parameters = ContentType.getParameters(contentType);

    // then
    Assert.assertEquals(parameters.size(), 1);
    Assert.assertEquals(parameters.get("another-attribute"), "some-value");
  }

}
