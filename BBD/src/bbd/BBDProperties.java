/*
 * BBDProperties.java
 *
 * Created on January 20, 2007, 6:24 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 *

 *
 */

package bbd;
/*
 * Copyright 2007 microneering, Inc and James Gamber
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * BBD/bbd/BBDProperties.java
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 *
 * @author james gamber
 */
@SuppressWarnings("serial")
final class BBDProperties extends java.util.Properties {
    
    private static final Logger log = Logger.getLogger(BBDProperties.class.getName());
    static private final BBDProperties bbdp = new BBDProperties();
    
    
    static final String BBDConnectionString = "BBDConnectionString";
    
    static final String UserConnectionString = "UserConnectionString";
    
    static final String DriverClass = "DriverClass";
    
    static final String GetAPI = "getAPI";
    
    static final String BBDDatabase = "BBDDatabase";
    
    static final String SQLPassThrough = "SQLPassThrough";
    
    static final String CacheDeprecatedAPI = "CacheDeprecatedAPI";
    
    static final String UseTestAPI = "UseTestAPI";
    
    static final String RefreshCachePeriod = "RefreshCachePeriod";
    
    static final String BBDUser = "BBDUser";
    
    static final String BBDPassword = "BBDPassword";
    
    static final String BBDPrincipalIsDefault = "BBDPrincipalIsDefault";
    
    static final String BBDUnitTestUser = "BBDUnitTestUser";
    
    static final String BBDUnitTestPW = "BBDUnitTestPW";
    
    static final String APIUnitTestInsert = "APIUnitTestInsert";

    static final String BBDApplicationDatabase = "BBDApplicationDatabase";
    
    /** Creates a new instance of Properties */
    private BBDProperties() {
        super();
        
        InputStream is = BBDProperties.class
                .getResourceAsStream("BBD.properties");
        
        try {
            if (is == null) {
                throw new IOException("bbd.properties file not found");
            }
            load(is);
            is.close();
        } catch (final IOException ex) {
            log.severe(ex.toString());
        } finally {
            if (is != null) {
                try { is.close();} catch (Exception ex) {}
            }
        }
    }
    
    @Override
    public String getProperty(final String key) {
        final String s = super.getProperty(key);
        if (s == null) {
            log.severe("BBD properties files is missing a value for the key "
                    + key);
        }
        return s;
    }
    
    static public String get(final String key) {
        return bbdp.getProperty(key);
    }
    
}
