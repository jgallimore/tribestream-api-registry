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
package org.tomitribe.tribestream.registryng.resources;

import org.tomitribe.tribestream.registryng.domain.ApplicationDetail;
import org.tomitribe.tribestream.registryng.domain.EndpointDetail;
import org.tomitribe.tribestream.registryng.repository.Repository;
import org.tomitribe.tribestream.registryng.service.PathTransformUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * basically same as {@see com.tomitribe.tribestream.registry.resource.RegistryResource} but
 * supporting aliasing (human readable) queries.
 */
@Path("alias/registry")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class AliasRegistryResource extends RegistryResourceBase {

    @Inject
    private Repository repository;

    @GET
    @Path("application/{deployable}")
    public ApplicationDetail applicationDetail(@PathParam("deployable")
                                               final String deployable,

                                               @QueryParam("lang")
                                               @DefaultValue("en")
                                               final String lang,

                                               @Context final UriInfo uriInfo, @Context final HttpHeaders headers) {
        return applicationDetail(repository.findByApplicationId(deployable), lang, uriInfo, headers);
    }

    @GET
    @Path("endpoint/{deployable}/{httpMethod}/{path:.*}")
    public EndpointDetail endpointDetail(@PathParam("deployable")
                                         final String deployable,

                                         @PathParam("httpMethod")
                                         final String httpMethod,

                                         @PathParam("path")
                                         final String path,

                                         @QueryParam("lang")
                                         @DefaultValue("en")
                                         final String lang,

                                         @Context final UriInfo uriInfo, @Context final HttpHeaders headers) {
        return endpointDetail(
                repository.findEndpoint(deployable, httpMethod, PathTransformUtil.colonToBraces(path)),
                lang, uriInfo, headers);
    }
/*
    @GET
    @Path("see/{deployable}/{title}")
    @Description(value = "Returns the link for further information (@SeeAlso) for the specified application and link.", lang = "en")
    public SeeDetail seeDetail(@PathParam("deployable")
                               @Description(value = "Application alias", lang = "en")
                               @Required
                               final String deployable,

                               @PathParam("title")
                               @Description(value = "See title", lang = "en")
                               @Required
                               final String title,

                               @QueryParam("format")
                               @Description(value = "Expected format. Specifying 'ignore' will leave the content unformatted", lang = "en")
                               @DefaultValue("html")
                               final String format) {
        final DeployableInfo deployableFound = alias.findDeployable(deployable);
        return seeDetail(deployableFound, deployableFound.getId(), alias.findSee(deployable, title), format);
    }
    */
}