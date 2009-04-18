package org.apache.axis2.jaxws.description;

import javax.jws.WebService;
import javax.xml.ws.WebServiceProvider;

import org.apache.axis2.jaxws.description.EndpointDescriptionJava;

import junit.framework.TestCase;

public class ServiceAnnotationTests extends TestCase {

    public void testWebServiceDefaults() {
        String className = "WebServiceDefaultTest";
        EndpointDescriptionJava testEndpointDesc = getEndpointDesc(WebServiceDefaultTest.class);
        assertNotNull(testEndpointDesc.getAnnoWebService());
        assertNull(testEndpointDesc.getAnnoWebServiceProvider());
        assertEquals(className, testEndpointDesc.getAnnoWebServiceName());
        assertEquals("", testEndpointDesc.getAnnoWebServiceEndpointInterface());
        assertEquals("http://description.jaxws.axis2.apache.org/", testEndpointDesc.getAnnoWebServiceTargetNamespace());
        assertEquals(className + "Service", testEndpointDesc.getAnnoWebServiceServiceName());
        assertEquals(className + "Port", testEndpointDesc.getAnnoWebServicePortName());
        assertEquals("", testEndpointDesc.getAnnoWebServiceWSDLLocation());
    }
    
    public void testWebServiceProviderDefaults() {
        String className = "WebServiceProviderDefaultTest";
        EndpointDescriptionJava testEndpointDesc = getEndpointDesc(WebServiceProviderDefaultTest.class);
        assertNull(testEndpointDesc.getAnnoWebService());
        assertNotNull(testEndpointDesc.getAnnoWebServiceProvider());
        // name element not allowed on WebServiceProvider
        assertEquals("", testEndpointDesc.getAnnoWebServiceName());
        // EndpointInterface element not allowed on WebServiceProvider
        assertEquals("", testEndpointDesc.getAnnoWebServiceEndpointInterface());
        assertEquals("http://description.jaxws.axis2.apache.org/", testEndpointDesc.getAnnoWebServiceTargetNamespace());
        assertEquals(className + "Service", testEndpointDesc.getAnnoWebServiceServiceName());
        assertEquals(className + "Port", testEndpointDesc.getAnnoWebServicePortName());
        assertEquals("", testEndpointDesc.getAnnoWebServiceWSDLLocation());
    }
    
    public void testWebServiceName() {
        String className = "WebServiceName"; 
        EndpointDescriptionJava testEndpointDesc = getEndpointDesc(WebServiceName.class);
        assertNotNull(testEndpointDesc.getAnnoWebService());
        assertNull(testEndpointDesc.getAnnoWebServiceProvider());
        assertEquals("WebServiceNameElement", testEndpointDesc.getAnnoWebServiceName());
        assertEquals("", testEndpointDesc.getAnnoWebServiceEndpointInterface());
        assertEquals("http://description.jaxws.axis2.apache.org/", testEndpointDesc.getAnnoWebServiceTargetNamespace());
        // Note that per JSR-181 MR Sec 4.1 pg 16, the portName uses WebService.name, but serviceName does not!
        assertEquals(className + "Service", testEndpointDesc.getAnnoWebServiceServiceName());
        assertEquals("WebServiceNameElementPort", testEndpointDesc.getAnnoWebServicePortName());
        assertEquals("", testEndpointDesc.getAnnoWebServiceWSDLLocation());
    }
    
    public void testWebServiceNameAndPort() {
        String className = "WebServiceNameAndPort"; 
        EndpointDescriptionJava testEndpointDesc = getEndpointDesc(WebServiceNameAndPort.class);
        assertNotNull(testEndpointDesc.getAnnoWebService());
        assertNull(testEndpointDesc.getAnnoWebServiceProvider());
        assertEquals("WebServiceNameAndPortElement", testEndpointDesc.getAnnoWebServiceName());
        assertEquals("", testEndpointDesc.getAnnoWebServiceEndpointInterface());
        assertEquals("http://description.jaxws.axis2.apache.org/", testEndpointDesc.getAnnoWebServiceTargetNamespace());
        // Note that per JSR-181 MR Sec 4.1 pg 16, the portName uses WebService.name, but serviceName does not!
        assertEquals(className + "Service", testEndpointDesc.getAnnoWebServiceServiceName());
        assertEquals("WebServicePortName", testEndpointDesc.getAnnoWebServicePortName());
        assertEquals("", testEndpointDesc.getAnnoWebServiceWSDLLocation());
    }
    
    public void testWebServiceAll() {
        String className = "WebServiceAll"; 
        EndpointDescriptionJava testEndpointDesc = getEndpointDesc(WebServiceAll.class);
        assertNotNull(testEndpointDesc.getAnnoWebService());
        assertNull(testEndpointDesc.getAnnoWebServiceProvider());
        assertEquals("WebServiceAllNameElement", testEndpointDesc.getAnnoWebServiceName());
        assertEquals("org.apache.axis2.jaxws.description.MyEndpointInterface", testEndpointDesc.getAnnoWebServiceEndpointInterface());
        assertEquals("http://namespace.target.jaxws.axis2.apache.org/", testEndpointDesc.getAnnoWebServiceTargetNamespace());
        assertEquals("WebServiceAllServiceElement", testEndpointDesc.getAnnoWebServiceServiceName());
        assertEquals("WebServiceAllPortElement", testEndpointDesc.getAnnoWebServicePortName());
        assertEquals("http://my.wsdl.location/foo.wsdl", testEndpointDesc.getAnnoWebServiceWSDLLocation());
    }
    
    public void testWebServiceProviderAll() {
        String className = "WebServiceProviderAll"; 
        EndpointDescriptionJava testEndpointDesc = getEndpointDesc(WebServiceProviderAll.class);
        assertNull(testEndpointDesc.getAnnoWebService());
        assertNotNull(testEndpointDesc.getAnnoWebServiceProvider());
        assertEquals("", testEndpointDesc.getAnnoWebServiceName());
        assertEquals("", testEndpointDesc.getAnnoWebServiceEndpointInterface());
        assertEquals("http://namespace.target.jaxws.axis2.apache.org/", testEndpointDesc.getAnnoWebServiceTargetNamespace());
        assertEquals("WebServiceProviderAllServiceElement", testEndpointDesc.getAnnoWebServiceServiceName());
        assertEquals("WebServiceProviderAllPortElement", testEndpointDesc.getAnnoWebServicePortName());
        assertEquals("http://my.wsdl.other.location/foo.wsdl", testEndpointDesc.getAnnoWebServiceWSDLLocation());
        
    }
    
    /*
     * Method to return the endpoint interface description for a given implementation class.
     */
    private EndpointDescriptionJava getEndpointDesc(Class implementationClass) {
        // Use the description factory directly; this will be done within the JAX-WS runtime
        ServiceDescription serviceDesc = 
            DescriptionFactory.createServiceDescriptionFromServiceImpl(implementationClass, null);
        assertNotNull(serviceDesc);
        
        EndpointDescription[] endpointDesc = serviceDesc.getEndpointDescriptions();
        assertNotNull(endpointDesc);
        assertEquals(1, endpointDesc.length);
        
        // TODO: How will the JAX-WS dispatcher get the appropriate port (i.e. endpoint)?  Currently assumes [0]
        EndpointDescription testEndpointDesc = endpointDesc[0];
        return (EndpointDescriptionJava) testEndpointDesc;
    }
}

// ===============================================
// WebService Defaults test impl
// ===============================================
@WebService()
class WebServiceDefaultTest {
    
}

@WebServiceProvider()
class WebServiceProviderDefaultTest {
    
}

// ===============================================
// WebService Name test impl
// ===============================================
// Note that name is only allowed on @WebService; not @WebServiceProvider
@WebService(name="WebServiceNameElement")
class WebServiceName {
    
}

@WebService(name="WebServiceNameAndPortElement", portName="WebServicePortName")
class WebServiceNameAndPort {
    
}

// ===============================================
// WebService All test impl
// ===============================================
@WebService(
        name="WebServiceAllNameElement", 
        endpointInterface="org.apache.axis2.jaxws.description.MyEndpointInterface",
        targetNamespace="http://namespace.target.jaxws.axis2.apache.org/",
        serviceName="WebServiceAllServiceElement",
        portName="WebServiceAllPortElement",
        wsdlLocation="http://my.wsdl.location/foo.wsdl")
class WebServiceAll {
    
}

@WebService()
interface MyEndpointInterface {
    
}

@WebServiceProvider(
        targetNamespace="http://namespace.target.jaxws.axis2.apache.org/",
        serviceName="WebServiceProviderAllServiceElement",
        portName="WebServiceProviderAllPortElement",
        wsdlLocation="http://my.wsdl.other.location/foo.wsdl")
class WebServiceProviderAll {
    
}