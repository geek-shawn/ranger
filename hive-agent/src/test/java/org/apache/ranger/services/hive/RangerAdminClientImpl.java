/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ranger.services.hive;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.ranger.admin.client.RangerAdminClient;
import org.apache.ranger.plugin.model.RangerRole;
import org.apache.ranger.plugin.util.GrantRevokeRequest;
import org.apache.ranger.plugin.util.GrantRevokeRoleRequest;
import org.apache.ranger.plugin.util.RangerRoles;
import org.apache.ranger.plugin.util.RangerUserStore;
import org.apache.ranger.plugin.util.ServicePolicies;
import org.apache.ranger.plugin.util.ServiceTags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A test implementation of the RangerAdminClient interface that just reads policies in from a file and returns them
 */
public class RangerAdminClientImpl implements RangerAdminClient {
    private static final Logger LOG = LoggerFactory.getLogger(RangerAdminClientImpl.class);
    private final static String cacheFilename = "hive-policies.json";
    private final static String tagFilename = "hive-policies-tag.json";
    private Gson gson;

    public void init(String serviceName, String appId, String configPropertyPrefix) {
        Gson gson = null;
        try {
            gson = new GsonBuilder().setDateFormat("yyyyMMdd-HH:mm:ss.SSS-Z").setPrettyPrinting().create();
        } catch(Throwable excp) {
            LOG.error("RangerAdminClientImpl: failed to create GsonBuilder object", excp);
        }
        this.gson = gson;
    }

    @Override
    public void init(String serviceName, String appId, String configPropertyPrefix, Configuration config) {

    }

    public ServicePolicies getServicePoliciesIfUpdated(long lastKnownVersion, long lastActivationTimeInMillis) throws Exception {

        String basedir = System.getProperty("basedir");
        if (basedir == null) {
            basedir = new File(".").getCanonicalPath();
        }

        java.nio.file.Path cachePath = FileSystems.getDefault().getPath(basedir, "/src/test/resources/" + cacheFilename);
        byte[] cacheBytes = Files.readAllBytes(cachePath);

        return gson.fromJson(new String(cacheBytes), ServicePolicies.class);
    }

    @Override
    public RangerRoles getRolesIfUpdated(long lastKnownRoleVersion, long lastActivationTimeInMills) throws Exception {
        return null;
    }

    @Override
    public RangerRole createRole(RangerRole request) throws Exception {
        return null;
    }

    @Override
    public void dropRole(String execUser, String roleName) throws Exception {

    }

    @Override
    public List<String> getAllRoles(String execUser) throws Exception {
        return null;
    }

    @Override
    public List<String> getUserRoles(String execUser) throws Exception {
        return null;
    }

    @Override
    public RangerRole getRole(String execUser, String roleName) throws Exception {
        return null;
    }

    @Override
    public void grantRole(GrantRevokeRoleRequest request) throws Exception {

    }

    @Override
    public void revokeRole(GrantRevokeRoleRequest request) throws Exception {

    }

    public void grantAccess(GrantRevokeRequest request) throws Exception {

    }

    public void revokeAccess(GrantRevokeRequest request) throws Exception {

    }

    public ServiceTags getServiceTagsIfUpdated(long lastKnownVersion, long lastActivationTimeInMillis) throws Exception {
        String basedir = System.getProperty("basedir");
        if (basedir == null) {
            basedir = new File(".").getCanonicalPath();
        }

        java.nio.file.Path cachePath = FileSystems.getDefault().getPath(basedir, "/src/test/resources/" + tagFilename);
        byte[] cacheBytes = Files.readAllBytes(cachePath);

        return gson.fromJson(new String(cacheBytes), ServiceTags.class);
    }

    public List<String> getTagTypes(String tagTypePattern) throws Exception {
        return null;
    }

    @Override
    public RangerUserStore getUserStoreIfUpdated(long lastKnownUserStoreVersion, long lastActivationTimeInMillis) throws Exception {
        return null;
    }


}