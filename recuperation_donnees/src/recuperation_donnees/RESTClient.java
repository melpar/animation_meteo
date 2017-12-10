package recuperation_donnees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

class RESTClient {
    private final String endpoint;

    RESTClient(String endpoint) {
        if(endpoint == null)System.out.println("Endpoint can not be null or empty");

        // sanitize
        int ptr = endpoint.length() - 1;
        while(endpoint.charAt(ptr) == '/') ptr --;

        if(ptr + 1 != endpoint.length()) this.endpoint = endpoint.substring(ptr + 1);
        else this.endpoint = endpoint;
    }

    String getDataAsString(Map<String, String> args) throws IOException {
        String requestUrl = constructRequestUrl(args);
        String response = requestForData(requestUrl);

        if(response == null) return "";
        else return response;
    }

    private String requestForData(String requestUrl) throws IOException {

        URL url = new URL(requestUrl);
        

        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.setRequestProperty("Authorization", "Token KXeusWlDWAcJSVThzFtvlkfJLAKntlcD");
        httpConnection.setRequestMethod("GET");
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));

        String line = null;
        StringBuilder response = new StringBuilder();

        while((line = reader.readLine()) != null){
            response.append(line);
        }

        reader.close();

        return response.toString();
    }

    private String constructRequestUrl(Map<String, String> args){
        StringBuilder queryParams = new StringBuilder();
        queryParams.append(endpoint);
        queryParams.append("?");

        Iterator<String> keyIter = args.keySet().iterator();
        int index = 0;

        final int LENGTH_EXCLUDING_LAST_ARG = args.keySet().size() - 1;

        // construct string from all the arguments except the last one
        while(keyIter.hasNext() && index < LENGTH_EXCLUDING_LAST_ARG){
            String key = keyIter.next();
            queryParams.append(key).append("=").append(args.get(key));
            queryParams.append("&");

            index ++;
        }

        // construct string using the last element
        if(keyIter.hasNext() && index < args.keySet().size()){
            String key = keyIter.next();
            queryParams.append(key).append("=").append(args.get(key));

            index ++;
        }

        return queryParams.toString();
    }


}
