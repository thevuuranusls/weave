/*
 * Copyright 2012-2013 Continuuity,Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.continuuity.weave.internal.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 *
 */
public final class StackTraceElementCodec implements JsonSerializer<StackTraceElement>,
                                                     JsonDeserializer<StackTraceElement> {

  @Override
  public StackTraceElement deserialize(JsonElement json, Type typeOfT,
                                       JsonDeserializationContext context) throws JsonParseException {
    JsonObject jsonObj = json.getAsJsonObject();
    return new StackTraceElement(jsonObj.get("className").getAsString(),
                                 jsonObj.get("method").getAsString(),
                                 jsonObj.get("file").getAsString(),
                                 jsonObj.get("line").getAsInt());
  }

  @Override
  public JsonElement serialize(StackTraceElement src, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject jsonObj = new JsonObject();
    jsonObj.addProperty("className", src.getClassName());
    jsonObj.addProperty("method", src.getMethodName());
    jsonObj.addProperty("file", src.getFileName());
    jsonObj.addProperty("line", src.getLineNumber());

    return jsonObj;
  }
}
