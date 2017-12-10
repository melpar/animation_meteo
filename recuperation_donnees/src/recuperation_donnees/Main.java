package recuperation_donnees;

public class Main {
	public static void main(String[]args)  {
		// Construct a generic REST method
		NOAAMethod method = new GenericRESTMethod();

		// Add the REST query string arguments
		method.addArgument("lat", "47.6201");
		method.addArgument("lon", "-122.141");
		method.addArgument("product", "time-series");
		method.addArgument("begin", "2017-09-19T10:00");
		method.addArgument("end", "2017-09-20T10:00");
		method.addArgument("Unit", "e");
		//method.addArgument("maxt", "maxt");
		
		method.addArgument("token", "KXeusWlDWAcJSVThzFtvlkfJLAKntlcD");

		// Construct the invoker
		NOAAWeather weather = new NOAAWeather();

		// Request for data :)
		String data;
		try {
			data = weather.query(method);
			System.out.println(data);
		} catch (NOAACommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
