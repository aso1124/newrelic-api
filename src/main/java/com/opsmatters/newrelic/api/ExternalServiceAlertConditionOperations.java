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

import java.util.Collection;
import java.util.Map;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import com.opsmatters.newrelic.api.model.condition.ExternalServiceAlertCondition;

/**
 * The set of operations used for external service alert conditions.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ExternalServiceAlertConditionOperations extends BaseFluent
{
    /**
     * Constructor that takes a http context and API service.
     * @param httpContext The set of HTTP operations
     * @param apiService The set of API operations
     */
    public ExternalServiceAlertConditionOperations(HttpContext httpContext, NewRelicApiService apiService)
    {
        super(httpContext, apiService);
    }

    /**
     * Returns the set of alert conditions for the given policy id.
     * @param policyId The id of the alert policy to return the conditions for
     * @return The set of alert conditions
     */
    public Collection<ExternalServiceAlertCondition> list(long policyId)
    {
        Map<String,Object> queryParams = Maps.newHashMap();
        queryParams.put("policy_id", new Long(policyId));
        return HTTP.GET("/alerts_external_service_conditions.json", null, queryParams, EXTERNAL_SERVICE_ALERT_CONDITIONS).get();
    }

    /**
     * Returns the external service alert condition with the given id.
     * <P>
     * This is needed because the API does not contain an operation to get a condition using the id directly.
     * @param policyId The id of the policy the condition belongs to
     * @param id The id of the external service alert condition to return
     * @return The alert condition
     */
    public Optional<ExternalServiceAlertCondition> get(long policyId, long id)
    {
        Optional<ExternalServiceAlertCondition> ret = Optional.absent();
        Collection<ExternalServiceAlertCondition> conditions = list(policyId);
        for(ExternalServiceAlertCondition condition : conditions)
        {
            if(condition.getId() == id)
                ret = Optional.of(condition);
        }
        return ret;
    }
   
    /**
     * Creates the given external service alert condition.
     * @param policyId The id of the policy to add the alert condition to
     * @param condition The alert condition to create
     * @return The alert condition that was created
     */
    public Optional<ExternalServiceAlertCondition> create(long policyId, ExternalServiceAlertCondition condition)
    {
        return HTTP.POST(String.format("/alerts_external_service_conditions/policies/%d.json", policyId), condition, EXTERNAL_SERVICE_ALERT_CONDITION);
    }

    /**
     * Updates the given external service alert condition.
     * @param condition The alert condition to update
     * @return The alert condition that was updated
     */
    public Optional<ExternalServiceAlertCondition> update(ExternalServiceAlertCondition condition)
    {
        return HTTP.PUT(String.format("/alerts_external_service_conditions/%d.json", condition.getId()), condition, EXTERNAL_SERVICE_ALERT_CONDITION);
    }

    /**
     * Deletes the external service alert condition with the given id.
     * @param id The id of the alert condition to delete
     * @return This object
     */
    public ExternalServiceAlertConditionOperations delete(long id)
    {
        HTTP.DELETE(String.format("/alerts_external_service_conditions/%d.json", id));       
        return this;
    }
}