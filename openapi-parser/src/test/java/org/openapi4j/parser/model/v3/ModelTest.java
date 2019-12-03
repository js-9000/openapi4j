package org.openapi4j.parser.model.v3;

import org.junit.Assert;
import org.junit.Test;
import org.openapi4j.parser.OpenApi3Parser;
import org.openapi4j.parser.Checker;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.openapi4j.core.model.v3.OAI3SchemaKeywords.FORMAT_BINARY;
import static org.openapi4j.core.model.v3.OAI3SchemaKeywords.FORMAT_DOUBLE;
import static org.openapi4j.core.model.v3.OAI3SchemaKeywords.FORMAT_FLOAT;
import static org.openapi4j.core.model.v3.OAI3SchemaKeywords.FORMAT_INT32;
import static org.openapi4j.core.model.v3.OAI3SchemaKeywords.FORMAT_INT64;
import static org.openapi4j.core.model.v3.OAI3SchemaKeywords.TYPE_ARRAY;
import static org.openapi4j.core.model.v3.OAI3SchemaKeywords.TYPE_INTEGER;
import static org.openapi4j.core.model.v3.OAI3SchemaKeywords.TYPE_NUMBER;
import static org.openapi4j.core.model.v3.OAI3SchemaKeywords.TYPE_OBJECT;
import static org.openapi4j.core.model.v3.OAI3SchemaKeywords.TYPE_STRING;

public class ModelTest extends Checker {
  @Test
  public void copy() throws Exception {
    // foo api to traverse all the models getters & setters
    copyCheck("/model/v3/oai-integration/fullOfKeywords.yaml");
  }

  private void copyCheck(String name) throws Exception {
    URL specPath = getClass().getResource(name);

    OpenApi3 api = new OpenApi3Parser().parse(specPath, false);
    api.copy(api.getContext(), false);
    checkModel(specPath, api);
  }

  @Test
  public void callbackTest() {
    Callback callback = new Callback();

    mapCheck(
      "/foo",
      new Path(),
      callback::hasCallbackPath,
      callback::getCallbackPath,
      callback::setCallbackPath,
      callback::setCallbackPaths,
      callback::removeCallbackPath);
  }

  @Test
  public void componentsTest() {
    Components components = new Components();

    // Schemas
    mapCheck(
      "fooschema",
      new Schema(),
      components::hasSchema,
      components::getSchema,
      components::setSchema,
      components::setSchemas,
      components::removeSchema);

    // Responses
    mapCheck(
      "fooresponse",
      new Response(),
      components::hasResponse,
      components::getResponse,
      components::setResponse,
      components::setResponses,
      components::removeResponse);

    // Parameters
    mapCheck(
      "fooparameter",
      new Parameter(),
      components::hasParameter,
      components::getParameter,
      components::setParameter,
      components::setParameters,
      components::removeParameter);

    // Examples
    mapCheck(
      "fooexample",
      new Example(),
      components::hasExample,
      components::getExample,
      components::setExample,
      components::setExamples,
      components::removeExample);

    // Requestbodies
    mapCheck(
      "foorequestbody",
      new RequestBody(),
      components::hasRequestBody,
      components::getRequestBody,
      components::setRequestBody,
      components::setRequestBodies,
      components::removeRequestBody);

    // Header
    mapCheck(
      "fooheader",
      new Header(),
      components::hasHeader,
      components::getHeader,
      components::setHeader,
      components::setHeaders,
      components::removeHeader);

    // securitySchemes
    mapCheck(
      "foosecurityScheme",
      new SecurityScheme(),
      components::hasSecurityScheme,
      components::getSecurityScheme,
      components::setSecurityScheme,
      components::setSecuritySchemes,
      components::removeSecurityScheme);

    // links
    mapCheck(
      "foolink",
      new Link(),
      components::hasLink,
      components::getLink,
      components::setLink,
      components::setLinks,
      components::removeLink);

    // callbacks
    mapCheck(
      "foocallback",
      new Callback(),
      components::hasCallback,
      components::getCallback,
      components::setCallback,
      components::setCallbacks,
      components::removeCallback);
  }

  @Test
  public void encodingPropertyTest() {
    EncodingProperty obj = new EncodingProperty();

    mapCheck(
      "fooheader",
      new Header(),
      obj::hasHeader,
      obj::getHeader,
      obj::setHeader,
      obj::setHeaders,
      obj::removeHeader);
  }

  @Test
  public void headerTest() {
    Header header = new Header();

    mapCheck(
      "fooschema",
      new MediaType(),
      header::hasContentMediaType,
      header::getContentMediaType,
      header::setContentMediaType,
      header::setContentMediaTypes,
      header::removeContentMediaType);

    mapCheck(
      "fooexample",
      new Example(),
      header::hasExample,
      header::getExample,
      header::setExample,
      header::setExamples,
      header::removeExample);
  }

  @Test
  public void linkTest() {
    Link link = new Link();

    mapCheck(
      "fooschema",
      "foovalue",
      link::hasParameter,
      link::getParameter,
      link::setParameter,
      link::setParameters,
      link::removeParameter);

    mapCheck(
      "fooheader",
      new Header(),
      link::hasHeader,
      link::getHeader,
      link::setHeader,
      link::setHeaders,
      link::removeHeader);
  }

  @Test
  public void mediaType() {
    MediaType obj = new MediaType();

    mapCheck(
      "fooencoding",
      new EncodingProperty(),
      obj::hasEncoding,
      obj::getEncoding,
      obj::setEncoding,
      obj::setEncodings,
      obj::removeEncoding);

    mapCheck(
      "fooexample",
      new Example(),
      obj::hasExample,
      obj::getExample,
      obj::setExample,
      obj::setExamples,
      obj::removeExample);
  }

  @Test
  public void oAuthFlowTest() {
    OAuthFlow obj = new OAuthFlow();

    mapCheck(
      "foo",
      "fooscope",
      obj::hasScope,
      obj::getScope,
      obj::setScope,
      obj::setScopes,
      obj::removeScope);
  }

  @Test
  public void openApiTest() {
    OpenApi3 obj = new OpenApi3();

    listCheck(
      new Server(),
      obj::hasServers,
      obj::getServers,
      obj::setServers,
      obj::addServer,
      obj::insertServer,
      obj::removeServer);

    listCheck(
      new Tag(),
      obj::hasTags,
      obj::getTags,
      obj::setTags,
      obj::addTag,
      obj::insertTag,
      obj::removeTag);

    mapCheck(
      "paths",
      new Path(),
      obj::hasPath,
      obj::getPath,
      obj::setPath,
      obj::setPaths,
      obj::removePath);
  }

  @Test
  public void operationTest() {
    Operation obj = new Operation();

    listCheck(
      new Parameter(),
      obj::hasParameters,
      obj::getParameters,
      obj::setParameters,
      obj::addParameter,
      obj::insertParameter,
      obj::removeParameter);

    listCheck(
      new SecurityRequirement(),
      obj::hasSecurityRequirements,
      obj::getSecurityRequirements,
      obj::setSecurityRequirements,
      obj::addSecurityRequirement,
      obj::insertSecurityRequirement,
      obj::removeSecurityRequirement);

    listCheck(
      new Server(),
      obj::hasServers,
      obj::getServers,
      obj::setServers,
      obj::addServer,
      obj::insertServer,
      obj::removeServer);

    listCheck(
      "footag",
      obj::hasTags,
      obj::getTags,
      obj::setTags,
      obj::addTag,
      obj::insertTag,
      obj::removeTag);

    mapCheck(
      "foo",
      new Callback(),
      obj::hasCallback,
      obj::getCallback,
      obj::setCallback,
      obj::setCallbacks,
      obj::removeCallback);

    mapCheck(
      "foo",
      new Response(),
      obj::hasResponse,
      obj::getResponse,
      obj::setResponse,
      obj::setResponses,
      obj::removeResponse);
  }

  @Test
  public void pathTest() {
    Path obj = new Path();

    listCheck(
      new Parameter(),
      obj::hasParameters,
      obj::getParameters,
      obj::setParameters,
      obj::addParameter,
      obj::insertParameter,
      obj::removeParameter);

    listCheck(
      new Server(),
      obj::hasServers,
      obj::getServers,
      obj::setServers,
      obj::addServer,
      obj::insertServer,
      obj::removeServer);

    mapCheck(
      "foo",
      new Operation(),
      obj::hasOperation,
      obj::getOperation,
      obj::setOperation,
      obj::setOperations,
      obj::removeOperation);
  }

  @Test
  public void requestBodyTest() {
    RequestBody obj = new RequestBody();

    mapCheck(
      "foocontenttype",
      new MediaType(),
      obj::hasContentMediaType,
      obj::getContentMediaType,
      obj::setContentMediaType,
      obj::setContentMediaTypes,
      obj::removeContentMediaType);
  }

  @Test
  public void responseTest() {
    Response obj = new Response();

    mapCheck(
      "headers",
      new Header(),
      obj::hasHeader,
      obj::getHeader,
      obj::setHeader,
      obj::setHeaders,
      obj::removeHeader);

    mapCheck(
      "foocontenttype",
      new MediaType(),
      obj::hasContentMediaType,
      obj::getContentMediaType,
      obj::setContentMediaType,
      obj::setContentMediaTypes,
      obj::removeContentMediaType);

    mapCheck(
      "foolink",
      new Link(),
      obj::hasLink,
      obj::getLink,
      obj::setLink,
      obj::setLinks,
      obj::removeLink);
  }

  @Test
  public void serverTest() {
    Server obj = new Server();

    mapCheck(
      "foovar",
      new ServerVariable(),
      obj::hasVariable,
      obj::getVariable,
      obj::setVariable,
      obj::setVariables,
      obj::removeVariable);
  }

  @Test
  public void schemaTest() {
    Schema obj = new Schema();

    listCheck(
      "fooenum",
      obj::hasEnums,
      obj::getEnums,
      obj::setEnums,
      obj::addEnum,
      obj::insertEnum,
      obj::removeEnum);

    listCheck(
      "foorequired",
      obj::hasRequiredFields,
      obj::getRequiredFields,
      obj::setRequiredFields,
      obj::addRequiredField,
      obj::insertRequiredField,
      obj::removeRequiredField);

    listCheck(
      new Schema(),
      obj::hasAllOfSchemas,
      obj::getAllOfSchemas,
      obj::setAllOfSchemas,
      obj::addAllOfSchema,
      obj::insertAllOfSchema,
      obj::removeAllOfSchema);

    listCheck(
      new Schema(),
      obj::hasAnyOfSchemas,
      obj::getAnyOfSchemas,
      obj::setAnyOfSchemas,
      obj::addAnyOfSchema,
      obj::insertAnyOfSchema,
      obj::removeAnyOfSchema);

    listCheck(
      new Schema(),
      obj::hasOneOfSchemas,
      obj::getOneOfSchemas,
      obj::setOneOfSchemas,
      obj::addOneOfSchema,
      obj::insertOneOfSchema,
      obj::removeOneOfSchema);

    mapCheck(
      "foovar",
      new Schema(),
      obj::hasProperty,
      obj::getProperty,
      obj::setProperty,
      obj::setProperties,
      obj::removeProperty);

    assertFalse(obj.isDeprecated());
    assertFalse(obj.isNullable());
    assertFalse(obj.isExclusiveMaximum());
    assertFalse(obj.isExclusiveMinimum());
    assertFalse(obj.isUniqueItems());
    assertFalse(obj.hasAdditionalProperties());
    assertTrue(obj.isAdditionalPropertiesAllowed());
  }

  @Test
  public void schemaSupposedTypeTest() {
    Schema obj = new Schema();
    obj.setProperty("foo", new Schema());
    assertEquals(TYPE_OBJECT, obj.getSupposedType());

    obj = new Schema();
    obj.setItemsSchema(new Schema());
    assertEquals(TYPE_ARRAY, obj.getSupposedType());

    obj = new Schema();
    obj.setFormat(FORMAT_INT32);
    assertEquals(TYPE_INTEGER, obj.getSupposedType());
    obj.setFormat(FORMAT_INT64);
    assertEquals(TYPE_INTEGER, obj.getSupposedType());

    obj.setFormat(FORMAT_FLOAT);
    assertEquals(TYPE_NUMBER, obj.getSupposedType());
    obj.setFormat(FORMAT_DOUBLE);
    assertEquals(TYPE_NUMBER, obj.getSupposedType());

    obj.setFormat(FORMAT_BINARY);
    assertEquals(TYPE_STRING, obj.getSupposedType());

    obj = new Schema();
    obj.setType(TYPE_INTEGER);
    assertEquals(TYPE_INTEGER, obj.getSupposedType());
  }

  @Test
  public void securityParameterTest() {
    SecurityParameter obj = new SecurityParameter();

    listCheck(
      "fooparam",
      obj::hasParameters,
      obj::getParameters,
      obj::setParameters,
      obj::addParameter,
      obj::insertParameter,
      obj::removeParameter);
  }

  @Test
  public void serverVariableTest() {
    ServerVariable obj = new ServerVariable();

    listCheck(
      "fooenum",
      obj::hasEnums,
      obj::getEnums,
      obj::setEnums,
      obj::addEnum,
      obj::insertEnum,
      obj::removeEnum);
  }

  private <R> void listCheck(R value,
                             Supplier<Boolean> has,
                             Supplier<List<R>> get,
                             Consumer<List<R>> set,
                             Consumer<R> add,
                             BiConsumer<Integer, R> insert,
                             Consumer<R> rem) {

    Assert.assertFalse(has.get());
    Assert.assertNull(get.get());
    // add
    add.accept(value);
    Assert.assertNotNull(has.get());
    assertEquals(value, get.get().get(0));
    // insert
    insert.accept(0, value);
    assertEquals(2, get.get().size());
    // rem
    rem.accept(value);
    Assert.assertNotNull(has.get());
    assertEquals(1, get.get().size());
    // set
    set.accept(null);
    Assert.assertNull(get.get());
  }

  private <T, R> void mapCheck(T key,
                               R value,
                               Function<T, Boolean> has,
                               Function<T, R> get,
                               BiConsumer<T, R> set,
                               Consumer<Map<T, R>> setMulti,
                               Consumer<T> rem) {

    Assert.assertFalse(has.apply(key));
    Assert.assertNull(get.apply(key));
    // single add
    set.accept(key, value);
    assertTrue(has.apply(key));
    assertEquals(value, get.apply(key));
    // multi add
    Map<T, R> values = new HashMap<>();
    values.put(key, value);
    setMulti.accept(values);
    assertTrue(has.apply(key));
    assertEquals(value, get.apply(key));
    // rem
    rem.accept(key);
    Assert.assertFalse(has.apply(key));
    // single add (map already created)
    set.accept(key, value);
    assertTrue(has.apply(key));
    assertEquals(value, get.apply(key));
  }
}