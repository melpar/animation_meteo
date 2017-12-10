package recuperation_donnees;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

class SOAPClient {
    private final String endpoint;

    SOAPClient(String endpoint) {
        this.endpoint = endpoint;
    }

    SOAPMessage getDataAsSOAPMessage(String methodName, Map<String, String> args) throws MalformedURLException, SOAPException {
        // construct message
        SOAPMessage requestMessage = buildMessage(methodName, args);

        // connect to server and retrieve information
        SOAPMessage responseMessage = requestForData(requestMessage);

        return responseMessage;
    }

    private SOAPMessage requestForData(SOAPMessage message) throws SOAPException, MalformedURLException {
        SOAPConnectionFactory connectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection connection = connectionFactory.createConnection();

        try{
            return connection.call(message, new URL(endpoint));
        }finally {
            connection.close();
        }
    }

    private SOAPMessage buildMessage(String methodName, Map<String, String> args) throws SOAPException {
        // construct a message
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage message = messageFactory.createMessage();

        // getData message envelope and body
        SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
        SOAPBody body = envelope.getBody();

        // create body element
        Name bodyName = envelope.createName(methodName, "m", endpoint);
        SOAPBodyElement bodyElement = body.addBodyElement(bodyName);

        // add parameters as children of body element
        for(String param : args.keySet()){
            Name name = envelope.createName(param);
            bodyElement.addChildElement(name).addTextNode(args.get(param));
        }

        message.saveChanges();

        return message;
    }

    String getEndpoint() {
        return endpoint;
    }
}
