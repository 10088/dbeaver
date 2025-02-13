/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2023 DBeaver Corp and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jkiss.wmi.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Qualified object
 */
public abstract class WMIQualifiedObject {

    private volatile List<WMIQualifier> qualifiers;

    public Collection<WMIQualifier> getQualifiers()
        throws WMIException
    {
        if (qualifiers == null) {
            synchronized (this) {
                if (qualifiers == null) {
                    qualifiers = new ArrayList<>();
                    readObjectQualifiers(qualifiers);
                }
            }
        }
        return qualifiers;
    }

    public Object getQualifier(String name)
        throws WMIException
    {
        for (WMIQualifier q : getQualifiers()) {
            if (q.getName().equalsIgnoreCase(name)) {
                return q.getValue();
            }
        }
        return null;
    }

    protected abstract void readObjectQualifiers(List<WMIQualifier> qualifiers) throws WMIException;

}
