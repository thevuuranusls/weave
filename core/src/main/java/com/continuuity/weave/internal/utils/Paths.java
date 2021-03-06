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
package com.continuuity.weave.internal.utils;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;

import java.util.regex.Pattern;

/**
 *
 */
public final class Paths {

  private static final Pattern WHITE_SPACE_PATTERN = Pattern.compile("^\\s*(.*?)\\s*$");

  public static String getClassPath(Iterable<String> paths) {
    return Joiner.on(':').join(Iterables.transform(paths, new Function<String, String>() {
      @Override
      public String apply(String input) {
        return WHITE_SPACE_PATTERN.matcher(input).replaceAll("$1");
      }
    }));
  }

  private Paths() {
  }
}
