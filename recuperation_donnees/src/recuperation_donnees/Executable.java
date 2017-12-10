package recuperation_donnees;

public interface Executable {

    /**
     * This method encapsulates logic related to executing a NOAA request
     * and returning data from the service.
     *
     * @return
     */
    public String executeAndReturnData() throws NOAACommunicationException;
}