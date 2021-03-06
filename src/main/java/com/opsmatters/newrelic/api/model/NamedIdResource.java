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

package com.opsmatters.newrelic.api.model;

/**
 * Represents the base class for all resources with a name and numeric id.
 * 
 * @author Gerald Curley (opsmatters)
 */
public abstract class NamedIdResource extends IdResource implements NamedResource
{
    private String name;
    
    /**
     * Default constructor.
     */
    public NamedIdResource()
    {
    }

    /**
     * Sets the name of the resource.
     * @param name The name of the resource
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the name of the resource.
     * @return The name of the resource
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Returns a string representation of the resource.
     */
    @Override
    public String toString()
    {
        return super.toString()
            +", name="+name;
    }
}