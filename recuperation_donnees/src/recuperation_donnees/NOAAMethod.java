package recuperation_donnees;

import java.util.Map;

public abstract class NOAAMethod implements Executable {
    protected final Map<String, String> arguments;

    public NOAAMethod() {
        arguments = getArgumentsTemplate();
    }

    /**
     * Checks whether the method instance was created
     * with the correct arguments as specified in NOAA service
     * documentation.
     *
     * @return
     */
    public abstract void assertMethodValidity();

    /**
     * Returns a String representation of the method name.
     *
     * @return
     */
    public String getMethodName(){
        return this.getClass().getSimpleName();
    }

    /**
     * Returns a collection of arguments associated to
     * this NOAA service method.
     *
     * @return
     */
    public Map<String, String> getArguments() {
        Map<String, String> clone = getArgumentsTemplate();
        clone.putAll(arguments);
        return clone;
    }

    /**
     * Adds an input argument for this method.
     *
     * @param name
     * @param value
     */
    public abstract void addArgument(String name, String value);

    /**
     * Returns a collection of arguments that are a part of this method.
     *
     * @return
     */
    protected abstract Map<String, String> getArgumentsTemplate();
}

