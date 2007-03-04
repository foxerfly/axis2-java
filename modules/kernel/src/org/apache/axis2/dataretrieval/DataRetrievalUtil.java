/*
 * Copyright 2007 The Apache Software Foundation.
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

package org.apache.axis2.dataretrieval;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axiom.om.util.StAXUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

public class DataRetrievalUtil {
    private static final Log log = LogFactory.getLog(DataRetrievalUtil.class);

    private static DataRetrievalUtil instance = null;

    public static DataRetrievalUtil getInstance() {
        if (instance == null) {
            instance = new DataRetrievalUtil();
        }
        return instance;
    }

    /**
     * Loading xml file content and convert to OMElement.
     *
     * @param file - file path relative to the Service Repository
     * @return OMElement format of the xml file content
     * @throws DataRetrievalException
     */

    public OMElement buildOM(ClassLoader classLoader, String file)
            throws DataRetrievalException {
        OMElement element = null;
        InputStream servicexmlStream = null;
        try {
            servicexmlStream = getInputStream(classLoader, file);

            element = convertToOMElement(servicexmlStream);
        } catch (XMLStreamException e) {
            throw new DataRetrievalException(e);
        }

        return element;
    }

    /**
     * Convert servicexmlStream to OMElement
     *
     * @param servicexmlStream InputStream contain xml content
     * @return OMElement format of the xml content
     * @throws XMLStreamException
     */

    public static OMElement convertToOMElement(InputStream servicexmlStream)
            throws XMLStreamException {
        OMElement element = null;

        XMLStreamReader xmlReader = StAXUtils
                .createXMLStreamReader(servicexmlStream);
        OMFactory fac = OMAbstractFactory.getOMFactory();
        StAXOMBuilder staxOMBuilder = new StAXOMBuilder(fac, xmlReader);
        element = staxOMBuilder.getDocumentElement();
        element.build();
        return element;
    }

    private static InputStream getInputStream(ClassLoader classLoader,
                                              String file) throws XMLStreamException {

        InputStream servicexmlStream = classLoader.getResourceAsStream(file);

        if (servicexmlStream == null) {
            String message = "File does not exist in the Service Repository! File="
                    + file;
            if (log.isDebugEnabled()) {
                log.debug(message);
            }
            throw new XMLStreamException(message);
        }
        return servicexmlStream;

    }

}