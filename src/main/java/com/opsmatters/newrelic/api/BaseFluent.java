/*
 * Copyright 2018 Gerald Curley
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.opsmatters.newrelic.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.logging.Logger;
import javax.ws.rs.core.GenericType;
import com.opsmatters.newrelic.api.model.alerts.AlertIncident;
import com.opsmatters.newrelic.api.model.alerts.AlertViolation;
import com.opsmatters.newrelic.api.model.alerts.AlertEvent;
import com.opsmatters.newrelic.api.model.policies.AlertPolicy;
import com.opsmatters.newrelic.api.model.policies.AlertPolicyChannel;
import com.opsmatters.newrelic.api.model.channels.AlertChannel;
import com.opsmatters.newrelic.api.model.conditions.AlertCondition;
import com.opsmatters.newrelic.api.model.conditions.NrqlAlertCondition;
import com.opsmatters.newrelic.api.model.conditions.ExternalServiceAlertCondition;
import com.opsmatters.newrelic.api.model.conditions.PluginsAlertCondition;
import com.opsmatters.newrelic.api.model.conditions.SyntheticsAlertCondition;
import com.opsmatters.newrelic.api.model.conditions.InfraAlertCondition;
import com.opsmatters.newrelic.api.model.entities.Application;
import com.opsmatters.newrelic.api.model.entities.ApplicationHost;
import com.opsmatters.newrelic.api.model.entities.ApplicationInstance;
import com.opsmatters.newrelic.api.model.entities.BrowserApplication;
import com.opsmatters.newrelic.api.model.entities.MobileApplication;
import com.opsmatters.newrelic.api.model.entities.KeyTransaction;
import com.opsmatters.newrelic.api.model.entities.Plugin;
import com.opsmatters.newrelic.api.model.entities.PluginComponent;
import com.opsmatters.newrelic.api.model.entities.Server;
import com.opsmatters.newrelic.api.model.entities.Metric;
import com.opsmatters.newrelic.api.model.entities.MetricData;
import com.opsmatters.newrelic.api.model.deployments.Deployment;
import com.opsmatters.newrelic.api.model.labels.Label;
import com.opsmatters.newrelic.api.model.users.User;
import com.opsmatters.newrelic.api.model.products.UsageData;

/**
 * Provides the types and HTTP operations to be used with the API calls.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class BaseFluent
{
    private static final Logger logger = Logger.getLogger(BaseFluent.class.getName());

    protected static final GenericType<AlertPolicy> ALERT_POLICY = new GenericType<AlertPolicy>(){};
    protected static final GenericType<Collection<AlertPolicy>> ALERT_POLICIES = new GenericType<Collection<AlertPolicy>>(){};

    protected static final GenericType<AlertChannel> ALERT_CHANNEL = new GenericType<AlertChannel>(){};
    protected static final GenericType<Collection<AlertChannel>> ALERT_CHANNELS = new GenericType<Collection<AlertChannel>>(){};

    protected static final GenericType<AlertPolicyChannel> ALERT_POLICY_CHANNEL = new GenericType<AlertPolicyChannel>(){};

    protected static final GenericType<AlertCondition> ALERT_CONDITION = new GenericType<AlertCondition>(){};
    protected static final GenericType<Collection<AlertCondition>> ALERT_CONDITIONS = new GenericType<Collection<AlertCondition>>(){};

    protected static final GenericType<NrqlAlertCondition> NRQL_ALERT_CONDITION = new GenericType<NrqlAlertCondition>(){};
    protected static final GenericType<Collection<NrqlAlertCondition>> NRQL_ALERT_CONDITIONS = new GenericType<Collection<NrqlAlertCondition>>(){};

    protected static final GenericType<ExternalServiceAlertCondition> EXTERNAL_SERVICE_ALERT_CONDITION = new GenericType<ExternalServiceAlertCondition>(){};
    protected static final GenericType<Collection<ExternalServiceAlertCondition>> EXTERNAL_SERVICE_ALERT_CONDITIONS = new GenericType<Collection<ExternalServiceAlertCondition>>(){};

    protected static final GenericType<PluginsAlertCondition> PLUGINS_ALERT_CONDITION = new GenericType<PluginsAlertCondition>(){};
    protected static final GenericType<Collection<PluginsAlertCondition>> PLUGINS_ALERT_CONDITIONS = new GenericType<Collection<PluginsAlertCondition>>(){};

    protected static final GenericType<SyntheticsAlertCondition> SYNTHETICS_ALERT_CONDITION = new GenericType<SyntheticsAlertCondition>(){};
    protected static final GenericType<Collection<SyntheticsAlertCondition>> SYNTHETICS_ALERT_CONDITIONS = new GenericType<Collection<SyntheticsAlertCondition>>(){};

    protected static final GenericType<InfraAlertCondition> INFRA_ALERT_CONDITION = new GenericType<InfraAlertCondition>(){};
    protected static final GenericType<Collection<InfraAlertCondition>> INFRA_ALERT_CONDITIONS = new GenericType<Collection<InfraAlertCondition>>(){};

    protected static final GenericType<AlertIncident> ALERT_INCIDENT = new GenericType<AlertIncident>(){};
    protected static final GenericType<Collection<AlertIncident>> ALERT_INCIDENTS = new GenericType<Collection<AlertIncident>>(){};

    protected static final GenericType<AlertViolation> ALERT_VIOLATION = new GenericType<AlertViolation>(){};
    protected static final GenericType<Collection<AlertViolation>> ALERT_VIOLATIONS = new GenericType<Collection<AlertViolation>>(){};

    protected static final GenericType<AlertEvent> ALERT_EVENT = new GenericType<AlertEvent>(){};
    protected static final GenericType<Collection<AlertEvent>> ALERT_EVENTS = new GenericType<Collection<AlertEvent>>(){};

    protected static final GenericType<Application> APPLICATION = new GenericType<Application>(){};
    protected static final GenericType<Collection<Application>> APPLICATIONS = new GenericType<Collection<Application>>(){};

    protected static final GenericType<ApplicationHost> APPLICATION_HOST = new GenericType<ApplicationHost>(){};
    protected static final GenericType<Collection<ApplicationHost>> APPLICATION_HOSTS = new GenericType<Collection<ApplicationHost>>(){};

    protected static final GenericType<ApplicationInstance> APPLICATION_INSTANCE = new GenericType<ApplicationInstance>(){};
    protected static final GenericType<Collection<ApplicationInstance>> APPLICATION_INSTANCES = new GenericType<Collection<ApplicationInstance>>(){};

    protected static final GenericType<BrowserApplication> BROWSER_APPLICATION = new GenericType<BrowserApplication>(){};
    protected static final GenericType<Collection<BrowserApplication>> BROWSER_APPLICATIONS = new GenericType<Collection<BrowserApplication>>(){};

    protected static final GenericType<MobileApplication> MOBILE_APPLICATION = new GenericType<MobileApplication>(){};
    protected static final GenericType<Collection<MobileApplication>> MOBILE_APPLICATIONS = new GenericType<Collection<MobileApplication>>(){};

    protected static final GenericType<KeyTransaction> KEY_TRANSACTION = new GenericType<KeyTransaction>(){};
    protected static final GenericType<Collection<KeyTransaction>> KEY_TRANSACTIONS = new GenericType<Collection<KeyTransaction>>(){};

    protected static final GenericType<Plugin> PLUGIN = new GenericType<Plugin>(){};
    protected static final GenericType<Collection<Plugin>> PLUGINS = new GenericType<Collection<Plugin>>(){};

    protected static final GenericType<PluginComponent> PLUGIN_COMPONENT = new GenericType<PluginComponent>(){};
    protected static final GenericType<Collection<PluginComponent>> PLUGIN_COMPONENTS = new GenericType<Collection<PluginComponent>>(){};

    protected static final GenericType<Server> SERVER = new GenericType<Server>(){};
    protected static final GenericType<Collection<Server>> SERVERS = new GenericType<Collection<Server>>(){};

    protected static final GenericType<Collection<Metric>> METRICS = new GenericType<Collection<Metric>>(){};
    protected static final GenericType<MetricData> METRIC_DATA = new GenericType<MetricData>(){};

    protected static final GenericType<Deployment> DEPLOYMENT = new GenericType<Deployment>(){};
    protected static final GenericType<Collection<Deployment>> DEPLOYMENTS = new GenericType<Collection<Deployment>>(){};

    protected static final GenericType<Label> LABEL = new GenericType<Label>(){};
    protected static final GenericType<Collection<Label>> LABELS = new GenericType<Collection<Label>>(){};

    protected static final GenericType<User> USER = new GenericType<User>(){};
    protected static final GenericType<Collection<User>> USERS = new GenericType<Collection<User>>(){};

    protected static final GenericType<UsageData> USAGE_DATA = new GenericType<UsageData>(){};

    protected HttpContext HTTP;
    private NewRelicApiService apiService;

    /**
     * Constructor that takes a http context and API service.
     * @param httpContext The set of HTTP operations
     * @param apiService The set of API operations
     */
    public BaseFluent(HttpContext httpContext, NewRelicApiService apiService)
    {    
        this.HTTP = httpContext;
        this.apiService = apiService;
    }
    
    /**
     * Returns the API service.
     * @return The API service
     */
    public NewRelicApiService getService()
    {    
        return this.apiService;
    }

    /**
     * Encode "/" to its URL encoded representation "%2F".
     * @param str The input string
     * @return The encoded String
     */
    public static String encode(String str)
    {
        String encodedValue = str;

        try
        {
            encodedValue = URLEncoder.encode(str, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            logger.severe("Failed to encode value: "+str);
        }

        return encodedValue;
    }
}
