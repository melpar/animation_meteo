package recuperation_donnees;

import java.util.Map;

public abstract class NOAAService {

    /**
     * Using the information with in the NOAAMethod,
     * this class will make a call to the appropriate NOAA
     * service and return a String representation of the data.
     *
     * @param method
     * @return String representation of the data provided by NOAA for the specified request.
     * @exception NOAACommunicationException is thrown when there was problem
     *              while establishing a connection with NOAA's services
     */
    public String getData(NOAAMethod method) throws NOAACommunicationException {
        // validate method
        method.assertMethodValidity();

        // make a call and get xml content
        String textResponse = getData(method.getMethodName(), method.getArguments());

        if(textResponse != null) return textResponse;
        else return "";
    }

    /**
     * Retrieves data from the appropriate NOAA service as represented by the concrete class.
     *
     * @param methodName
     * @param params
     * @return
     */
    protected abstract String getData(String methodName, Map<String, String> params) throws NOAACommunicationException;
}
