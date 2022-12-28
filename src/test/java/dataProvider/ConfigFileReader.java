package dataProvider;

import enums.E_Environment;

/**
* The ConfigFileReader read all specific data from properties file which use for other class. 

* @author  Joe Phan
* @since   2022 Oct 19th
*/

public class ConfigFileReader {
	PropertyReader apiUrl;
	PropertyReader testData;
	public ConfigFileReader(){
		apiUrl =  new PropertyReader("src/test/java/properties/url.properties");
		testData=  new PropertyReader("src/test/java/properties/data.properties");
	}
	
	public String getApplicationUrl(E_Environment My_eURL) {
		String url;
		switch (My_eURL) {
		case DEV:
			url = apiUrl.readProperty("dev.environment.url");
			break;
		case TEST:
			url = apiUrl.readProperty("test.environment.url");
			break;
		case STAGE:
			url = apiUrl.readProperty("stg.environment.url");
			break;
		case PRODUCTION:
			url = apiUrl.readProperty("production.environment.url");
			break;
		default:
			url = apiUrl.readProperty("stg.environment.url");
			break;
		}
		if(url != null) 
			return url;
		else throw new RuntimeException("url not specified in the properties file.");
	}
	public E_Environment getEnvironment() {
		String environment =  testData.readProperty("environment");
		if(environment != null) 
			return  E_Environment.valueOf(environment);
		else 
			throw new RuntimeException("E_URL_Environment not specified in the properties file.");		
	}
}
