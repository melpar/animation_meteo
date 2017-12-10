package recuperation_donnees;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public abstract class NOAARESTMethod extends NOAAMethod {

    private NOAARESTService service = new NOAARESTService();

    @Override
    public void assertMethodValidity() {
        Iterator<String> keyIter = arguments.keySet().iterator();
        while(keyIter.hasNext()){
            String key = keyIter.next();

            // check values of map are not empty or null
            String value = arguments.get(key);
            if(key == null || key.isEmpty())System.out.println("Invalid method argument " + key +". Value can not be empty or null");
        }
    }

    @Override
    public void addArgument(String name, String value) {
        // check preconditions
        // name and value is not null or empty
        if(name == null)System.out.println("Invalid argument name. Name can not be empty or null");
        if(name == null || name.isEmpty())System.out.println("Invalid argument " + name + ". Value can not be empty or null");

        // if all okay, insert to map
        arguments.put(name, value);
    }

    @Override
    protected Map<String, String> getArgumentsTemplate() {
        return new TreeMap<String, String>();
    }

    @Override
    public String executeAndReturnData() throws NOAACommunicationException {
        return service.getData(this);
    }

    /**
     * This NOAA method has a default REST service assigned to it, this may be overridden via this setter method.
     * @param service
     */
    public void setService(NOAARESTService service) {
        this.service = service;
    }

    /**
     * Returns the service to which this method will make a request to.
     *
     * @return
     */
    public NOAARESTService getService() {
        return service;
    }
}
