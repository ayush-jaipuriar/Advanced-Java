package com.drarc.advancedjava;

import java.io.IOException;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Properties properties = new Properties();
		String propertiesConfigPath = "/config.properties";
		try {
			properties.load(App.class.getResourceAsStream(propertiesConfigPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name = properties.getProperty("name");
		System.out.println("Name : " + name);
	}
}
