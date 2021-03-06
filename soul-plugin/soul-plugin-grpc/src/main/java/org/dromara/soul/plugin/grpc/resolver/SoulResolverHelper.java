/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dromara.soul.plugin.grpc.resolver;

import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import org.dromara.soul.plugin.grpc.loadbalance.SubChannels;

import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.Map;

/**
 * SoulResolverHelper.
 *
 * @author zhanglei
 */
public class SoulResolverHelper {

    /**
     * ConvertToEquivalentAddressGroup.
     *
     * @param instance instance
     * @return EquivalentAddressGroup
     */
    public static EquivalentAddressGroup convertToEquivalentAddressGroup(final SoulServiceInstance instance) {
        return new EquivalentAddressGroup(new InetSocketAddress(instance.getHost(), instance.getPort()), createAttributes(instance));
    }

    /**
     * CreateAttributes.
     *
     * @param instance instance
     * @return Attributes
     */
    private static Attributes createAttributes(final SoulServiceInstance instance) {
        Map<String, String> metadata = instance.getMetadata();
        if (metadata == null) {
            metadata = Collections.emptyMap();
        }
        return SubChannels.createAttributes(instance.getWeight());
    }
}
