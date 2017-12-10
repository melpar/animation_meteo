package recuperation_donnees;

import java.net.MalformedURLException;
import java.util.Map;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class NOAASOAPService extends NOAAService {

    private String serviceEndpoint = "http://graphical.weather.gov/xml/SOAP_server/ndfdXMLserver.php";

    
    protected String getData(String methodName, Map<String, String> params) throws NOAACommunicationException {
        SOAPClient client = new SOAPClient(serviceEndpoint);
        try {
            SOAPMessage message = client.getDataAsSOAPMessage(methodName, params);
            return NOAASOAPUtil.getSOAPMessageContentAsString(message);
        } catch (MalformedURLException e) {
            throw new NOAACommunicationException("Invalid protocol for endpoint: "+serviceEndpoint, e);
        } catch (SOAPException e) {
            throw new NOAACommunicationException("Unable to make SOAP request to "+serviceEndpoint+
                    ". Embedded exception has more details.", e);
        }
    }

    public String getServiceEndpoint() {
        return serviceEndpoint;
    }

    public void setServiceEndpoint(String serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }
    
    public String getData(NOAAMethod method) throws NOAACommunicationException {
        // validate method
        method.assertMethodValidity();

        // make a call and get xml content
        String textResponse = getData(method.getMethodName(), method.getArguments());

        if(textResponse != null) return textResponse;
        else return "";
    }
}