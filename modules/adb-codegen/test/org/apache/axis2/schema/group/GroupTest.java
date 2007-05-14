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
package org.apache.axis2.schema.group;

import junit.framework.TestCase;
import group.test.axis2.apache.org.*;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.util.StAXUtils;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;


public class GroupTest extends TestCase {

    public void testSequenceGroupElement(){

        TestSequenceGroupElement testGroupSequenceElement = new TestSequenceGroupElement();
        testGroupSequenceElement.setParam1("param1");
        TestSequenceGroup testSequenceGroup = new TestSequenceGroup();
        testSequenceGroup.setSequenceParam1("sequenceParam1");
        testSequenceGroup.setSequenceParam2("sequenceParam2");
        testGroupSequenceElement.setTestSequenceGroup(testSequenceGroup);

        OMElement omElement =
                testGroupSequenceElement.getOMElement(TestSequenceGroupElement.MY_QNAME, OMAbstractFactory.getOMFactory());
        try {
            String omElementString = omElement.toStringWithConsume();
            System.out.println("OM String ==> " + omElementString);
            XMLStreamReader xmlReader =
                    StAXUtils.createXMLStreamReader(new ByteArrayInputStream(omElementString.getBytes()));
            TestSequenceGroupElement result = TestSequenceGroupElement.Factory.parse(xmlReader);
            assertEquals(result.getParam1(),"param1");
            assertEquals(result.getTestSequenceGroup().getSequenceParam1(),"sequenceParam1");
            assertEquals(result.getTestSequenceGroup().getSequenceParam2(),"sequenceParam2");
        } catch (XMLStreamException e) {
            fail();
        } catch (Exception e) {
            fail();
        }
    }

    public void testNestedSequenceGroupElement(){

        TestSequenceNestedGroupElement testSequenceNestedGroupElement = new TestSequenceNestedGroupElement();
        testSequenceNestedGroupElement.setParam1("param1");

        TestSequenceNestedGroup testSequenceNestedGroup = new TestSequenceNestedGroup();
        testSequenceNestedGroup.setNestedSequenceParam1("nestedSequenceParam1");

        TestSequenceGroup testSequenceGroup = new TestSequenceGroup();
        testSequenceGroup.setSequenceParam1("sequenceParam1");
        testSequenceGroup.setSequenceParam2("sequenceParam2");

        testSequenceNestedGroup.setTestSequenceGroup(testSequenceGroup);

        testSequenceNestedGroupElement.setTestSequenceNestedGroup(testSequenceNestedGroup);

        OMElement omElement =
                testSequenceNestedGroupElement.getOMElement(TestSequenceNestedGroupElement.MY_QNAME, OMAbstractFactory.getOMFactory());
        try {
            String omElementString = omElement.toStringWithConsume();
            System.out.println("OM String ==> " + omElementString);
            XMLStreamReader xmlReader =
                    StAXUtils.createXMLStreamReader(new ByteArrayInputStream(omElementString.getBytes()));
            TestSequenceNestedGroupElement result = TestSequenceNestedGroupElement.Factory.parse(xmlReader);
            assertEquals(result.getParam1(),"param1");
            assertEquals(result.getTestSequenceNestedGroup().getNestedSequenceParam1(),"nestedSequenceParam1");
            assertEquals(result.getTestSequenceNestedGroup().getTestSequenceGroup().getSequenceParam1(),"sequenceParam1");
            assertEquals(result.getTestSequenceNestedGroup().getTestSequenceGroup().getSequenceParam2(),"sequenceParam2");
        } catch (XMLStreamException e) {
            fail();
        } catch (Exception e) {
            fail();
        }
    }

    public void testChoiceGroupElement(){

        TestChoiceGroupElement testGroupChoiceElement = new TestChoiceGroupElement();
        testGroupChoiceElement.setParam1("param1");
        TestChoiceGroup testChoiceGroup = new TestChoiceGroup();
        testChoiceGroup.setChoiceParam1("choiceParam1");
        testGroupChoiceElement.setTestChoiceGroup(testChoiceGroup);

        OMElement omElement =
                testGroupChoiceElement.getOMElement(TestChoiceGroupElement.MY_QNAME, OMAbstractFactory.getOMFactory());
        try {
            String omElementString = omElement.toStringWithConsume();
            System.out.println("OM String ==> " + omElementString);
            XMLStreamReader xmlReader =
                    StAXUtils.createXMLStreamReader(new ByteArrayInputStream(omElementString.getBytes()));
            TestChoiceGroupElement result = TestChoiceGroupElement.Factory.parse(xmlReader);
            assertEquals(result.getTestChoiceGroup().getChoiceParam1(),"choiceParam1");
        } catch (XMLStreamException e) {
            fail();
        } catch (Exception e) {
            fail();
        }
    }

    public void testNestedChoiceGroupElement(){

        TestChoiceNestedGroupElement testChoiceNestedGroupElement = new TestChoiceNestedGroupElement();
        testChoiceNestedGroupElement.setParam1("param1");

        TestChoiceNestedGroup testChoiceNestedGroup = new TestChoiceNestedGroup();
        testChoiceNestedGroup.setNestedChoiceParam1("nestedChoiceParam1");

        TestChoiceGroup testChoiceGroup = new TestChoiceGroup();
        testChoiceGroup.setChoiceParam1("choiceParam1");

        testChoiceNestedGroup.setTestChoiceGroup(testChoiceGroup);

        testChoiceNestedGroupElement.setTestChoiceNestedGroup(testChoiceNestedGroup);

        OMElement omElement =
                testChoiceNestedGroupElement.getOMElement(testChoiceNestedGroupElement.MY_QNAME, OMAbstractFactory.getOMFactory());
        try {
            String omElementString = omElement.toStringWithConsume();
            System.out.println("OM String ==> " + omElementString);
            XMLStreamReader xmlReader =
                    StAXUtils.createXMLStreamReader(new ByteArrayInputStream(omElementString.getBytes()));
            TestChoiceNestedGroupElement result = TestChoiceNestedGroupElement.Factory.parse(xmlReader);
            assertEquals(result.getTestChoiceNestedGroup().getTestChoiceGroup().getChoiceParam1(),"choiceParam1");
        } catch (XMLStreamException e) {
            fail();
        } catch (Exception e) {
            fail();
        }
    }

}
