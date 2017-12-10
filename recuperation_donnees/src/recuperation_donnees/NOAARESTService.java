package recuperation_donnees;

import java.io.IOException;
import java.util.Map;

public class NOAARESTService extends NOAAService {

    private String serviceEndpoint = "https://www.ncdc.noaa.gov/cdo-web/api/v2/data";

    @Override
    protected String getData(String methodName, Map<String, String> args) throws NOAACommunicationException {
        RESTClient client = new RESTClient(serviceEndpoint);
        try {
            String response = client.getDataAsString(args);
            return response;
        } catch (IOException e) {
            throw new NOAACommunicationException("Unable to communicate with NOAA REST service at "+serviceEndpoint+
                    ". Embedded exception has more details", e);
        }
    }

    public String getServiceEndpoint() {
        return serviceEndpoint;
    }

    public void setServiceEndpoint(String serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }
}
