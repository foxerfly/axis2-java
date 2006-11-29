package org.apache.axis2.description;

import java.util.Map;
import java.util.HashMap;
/*
 * Copyright 2004,2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class WSDL20DefaultValueHolder {

    private static Map defaultValuesMap = new HashMap();

    static {
        defaultValuesMap.put(WSDL2Constants.ATTR_WSOAP_VERSION, WSDL2Constants.SOAP_VERSION_1_2);
    }

    public static String getDefaultValue(String name) {
        return (String) defaultValuesMap.get(name);
    }
}
