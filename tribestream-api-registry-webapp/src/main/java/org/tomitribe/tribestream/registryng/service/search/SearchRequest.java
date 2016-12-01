/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.tomitribe.tribestream.registryng.service.search;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class SearchRequest {
    private final String query;
    private final List<String> tags;
    private final List<String> categories;
    private final List<String> roles;
    private final List<String> apps;
    private final int page;
    private final int count;

    public SearchRequest(final String query, final List<String> tags, final List<String> categories, final List<String> roles, final List<String> apps,
                         final int page, final int count) {
        this.query = query;
        this.tags = tags;
        this.categories = categories;
        this.roles = roles;
        this.apps = apps;
        this.page = page;
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public int getCount() {
        return count;
    }

    public String getQuery() {
        return encode(query);
    }

    public List<String> getTags() {
        return encodeList(tags);
    }

    public List<String> getCategories() {
        return encodeList(categories);
    }

    public List<String> getRoles() {
        return encodeList(roles);
    }

    public List<String> getApps() {
        return encodeList(apps);
    }

    private static String encode(final String s) {
        try {
            return s != null ? URLEncoder.encode(s, "UTF-8") : null;
        } catch (final UnsupportedEncodingException e) {
            return s;
        }
    }

    private static List<String> encodeList(final List<String> list) {
        return list != null ? list.stream().map(SearchRequest::encode).collect(toList()) : null;
    }
}
