package com.krieger.pages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class fileReader {
	private Properties prop;
	private final String propFilePath = "configs//config.properties";
	public BufferedReader reader;
	
	/*..This class is used to read the values from properties file..*/
	public fileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propFilePath));
			prop = new Properties();
			prop.load(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getBrowser() {
		String filepath = prop.getProperty("Browser");
		if(filepath!=null) return filepath;
		else throw new RuntimeException("Browser is not given in config.properties file.");
	}
	
	public String getURL() {
		String filepath = prop.getProperty("URL");
		if(filepath!=null) return filepath;
		else throw new RuntimeException("URL is not given in config.properties file.");
	}

}
