/*
 * BBDJPAProperties.java
 *
 * Created on January 20, 2007, 6:24 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 * 
 * Copyright @2007 microneering, Inc, All Rights Reserved Worldwide.
 *
 */

package javax.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * 
 * @author james gamber
 */
@SuppressWarnings("serial")
final class BBDJPAProperties extends java.util.Properties {

	private static final Logger log = Logger.getLogger(BBDJPAProperties.class.getName());

	static final String bbdJPPADB = "BBDJPADB";

	static final String TableColumnTemplate = "TableColumnTemplate";
	
	static final String bbdJPAUser = "BBDJPAUser";
	
	static final String bbdJPAPW = "BBDJPAPW";
	
	static private final String bbdJPAPropFileName = "BBDJPA.properties";
	
	static private final BBDJPAProperties pp = new BBDJPAProperties();

	/** Creates a new instance of Properties */
	private BBDJPAProperties() {
		super();

		final InputStream is = BBDJPAProperties.class
				.getResourceAsStream(bbdJPAPropFileName);
		if (is == null)
		{
			log.severe("File not found "+bbdJPAPropFileName);
			return;
		}
		try {
			load(is);
			is.close();
		} catch (final IOException ex) {
			log.severe(ex.toString());
		}
	}

	@Override
	public String getProperty(final String key) {
		final String s = super.getProperty(key);
		if (s == null) {
			log.severe("Persistence properties file is missing a value for the key "
					+ key);
		} 
		return s;
	}

	static public String get(final String key) {
		return pp.getProperty(key);
	}

}
