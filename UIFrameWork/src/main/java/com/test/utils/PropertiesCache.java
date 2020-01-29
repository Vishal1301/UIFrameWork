package com.test.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * @author Vishal Limbani
 *
 */
public class PropertiesCache {

	private static final Logger log = Logger.getLogger(PropertiesCache.class);
	private final Properties configProp = new Properties();

// Reading configuration file
	private PropertiesCache() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
		log.info(in);
		log.info("Read all the configurations from  file");
		try {
			configProp.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Instantiating PropertiesCache
	private static class LazyHolder {
		private static final PropertiesCache INSTANCE = new PropertiesCache();
	}
// Method tp return instance of PropertiesCache
	public static PropertiesCache getInstance() {
		return LazyHolder.INSTANCE;
	}

	//Method to get property for key
	public String getProperty(String key) {
		return configProp.getProperty(key);
	}
// Method to get all property name
	public Set<String> getAllPropertyNames() {
		return configProp.stringPropertyNames();
	}
//Method to check if any key is present in the configuration file.
	public boolean containsKey(String key) {
		return configProp.containsKey(key);
	}

}
