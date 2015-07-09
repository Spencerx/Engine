/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package eu.netide.core.shimconnectivity;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    private ServiceRegistration<?> _shimManagerServiceRegistration;
    private ShimManager _shimManager;

    public void start(BundleContext context) {
        System.out.println("Core Shim Management starting...");
        _shimManager = new ShimManager();
        _shimManager.SetConnector(new ZeroMQBasedShimConnector(_shimManager));
        _shimManager.GetConnector().Open(5555);

        //_shimManagerServiceRegistration = context.registerService(IShimManager.class, _shimManager, null);
        //((IShimManager) context.getService(context.getServiceReference(IShimManager.class))).GetConnector().SendMessage("Test from shimconnectivity");

        System.out.println("Core Shim Management started!");
    }

    public void stop(BundleContext context) {
        _shimManagerServiceRegistration.unregister();
        _shimManager.GetConnector().Close();
        _shimManager = null;
        System.out.println("Core Shim Management stopped!");
    }

}