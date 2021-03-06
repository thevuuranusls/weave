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
package com.continuuity.weave.internal;

import com.continuuity.weave.api.RunId;
import com.continuuity.weave.api.WeaveContext;
import com.continuuity.weave.api.WeaveRunnableSpecification;
import com.continuuity.weave.common.Cancellable;
import com.continuuity.weave.discovery.Discoverable;
import com.continuuity.weave.discovery.DiscoveryService;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 *
 */
public final class BasicWeaveContext implements WeaveContext {

  private final RunId runId;
  private final RunId appRunId;
  private final InetAddress host;
  private final String[] args;
  private final String[] appArgs;
  private final WeaveRunnableSpecification spec;
  private final int instanceId;
  private final DiscoveryService discoveryService;
  private volatile int instanceCount;

  public BasicWeaveContext(RunId runId, RunId appRunId, InetAddress host, String[] args, String[] appArgs,
                           WeaveRunnableSpecification spec, int instanceId, DiscoveryService discoveryService,
                           int instanceCount) {
    this.runId = runId;
    this.appRunId = appRunId;
    this.host = host;
    this.args = args;
    this.appArgs = appArgs;
    this.spec = spec;
    this.instanceId = instanceId;
    this.discoveryService = discoveryService;
    this.instanceCount = instanceCount;
  }

  @Override
  public RunId getRunId() {
    return runId;
  }

  @Override
  public RunId getApplicationRunId() {
    return appRunId;
  }

  @Override
  public int getInstanceCount() {
    return instanceCount;
  }

  public void setInstanceCount(int count) {
    this.instanceCount = count;
  }

  @Override
  public InetAddress getHost() {
    return host;
  }

  @Override
  public String[] getArguments() {
    return args;
  }

  @Override
  public String[] getApplicationArguments() {
    return appArgs;
  }

  @Override
  public WeaveRunnableSpecification getSpecification() {
    return spec;
  }

  @Override
  public int getInstanceId() {
    return instanceId;
  }

  @Override
  public Cancellable announce(final String serviceName, final int port) {
    return discoveryService.register(new Discoverable() {
      @Override
      public String getName() {
        return serviceName;
      }

      @Override
      public InetSocketAddress getSocketAddress() {
        return new InetSocketAddress(getHost(), port);
      }
    });
  }
}
