/*
 * Copyright 2006 Web Cohesion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.codehaus.enunciate.modules.rest;

import org.codehaus.enunciate.rest.annotations.VerbType;

import java.util.EnumMap;
import java.lang.reflect.Method;

/**
 * A REST resource.  Consists of a noun with its available operations.
 *
 * @author Ryan Heaton
 */
public class RESTResource implements Comparable<RESTResource> {

  private final String noun;
  private final EnumMap<VerbType, RESTOperation> operations = new EnumMap<VerbType, RESTOperation>(VerbType.class);

  /**
   * Construct a REST resource for the specified noun.
   *
   * @param noun The noun for this REST resource.
   */
  public RESTResource(String noun) {
    this.noun = noun;
  }

  /**
   * Adds an operation to this REST resource.
   *
   * @param verb The verb for the operation.
   * @param endpoint The endpoint on which to invoke the operation.
   * @param method The method to invoke on the endpoint.
   * @return Whether the operation was successfully added.  (false if the specified verb was already added).
   */
  public boolean addOperation(VerbType verb, Object endpoint, Method method) {
    return !operations.containsKey(verb) && operations.put(verb, new RESTOperation(verb, endpoint, method)) == null;
  }

  /**
   * Gets the REST operation for the specified verb.
   *
   * @param verb The verb.
   * @return The REST operation.
   */
  public RESTOperation getOperation(VerbType verb) {
    return operations.get(verb);
  }

  /**
   * Compares the two REST resources.
   *
   * @param other The resource to compare to this one.
   * @return The comparison.
   */
  public int compareTo(RESTResource other) {
    return this.noun.compareTo(other.noun);
  }

  @Override
  public String toString() {
    return noun;
  }
}