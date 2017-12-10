package recuperation_donnees;

public class NOAAWeather {

    /**
     * Given an executable with relevant search information, this
     * method will search the NOAA weather system and return data
     * as a String.
     *
     * @param executable A executable object which contains the request and NOAA service configuration.
     * @return String representation of NOAA's data for the specified request.
     * @exception NOAACommunicationException is thrown when there was a problem while connecting to
     *              the NOAA service.
     */
    public String query(Executable executable) throws NOAACommunicationException {
        return executable.executeAndReturnData();
    }
}
