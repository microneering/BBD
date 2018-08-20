/**
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
 * BBD/bbd/BBDAPIPrincipal.java
 */
import java.io.Serializable;
import java.security.Principal;

/**
 * This is the authentication token for BBD.
 *
 * This contains the database logon information. There can be any number of
 * Principal's, each one assigned to a specific database transaction. John maybe
 * permited to execute grants on a query API that limits the columns he we see
 * on a specific table, while Mary may logon and use an AIP where she is see all
 * columns on the same table, and performing updates to the table.
 *
 * Actual access control is defined by the database by placing specific types of
 * access in stored procedures, then granting execute of that stored procedures
 * to users and/or groups.
 *
 * @author james gamber
 *
 */
final class BBDAPIPrincipal implements Principal, Serializable, IBBDAPIPrincipal {

    /**
     * Database logon password. *
     */
    private final String password;
    /**
     * Database logon user name *
     */
    private final String userName;
    /**
     * When multiple transactions use the same Principal, that principal remains
     * active until all transactions are completed.
     */
    private transient int usageCounter = 1;

    /**
     * Creates a new Instance of BBDAPIPrincipal.
     *
     * @param userName Database logon user name.
     * @param password Database logon password.
     */
    public BBDAPIPrincipal( final String userName, final String password ) {
        this.userName = userName;
        this.password = password;
    }

    /* (non-Javadoc)
	 * @see java.security.Principal#getName()
     */
    @Override
    public String getName() {

        return userName;
    }

    /**
     * Get the Database logon password. Package private.
     *
     * @return Database logon password.
     */
    String getPassword() {
        return password;
    }

    /**
     * Each time the same principal is used to request a connection, the usage
     * counter is incremented.
     */
    public synchronized void incrementUsage() {
        usageCounter++;
    }

    /**
     * Each time an attempt to close a connection that has been used by this
     * principal, the usage counter is decremented.
     *
     * This creates a type of pooling, where the the connection does not really
     * close until all transactions that are using it with this principal have
     * completed.
     *
     * This is a performance enhancement that can be replaced by JDBC driver
     * placed connection pooling.
     *
     * @return Number of transactions using this Principal's connection.
     */
    public synchronized boolean decrementUsage() {

        usageCounter--;

        return usageCounter < 1;

    }

    @Override
    public boolean equals( Object princ ) {

        // return false for all the null cases
        if( princ == null ) {
            return false;
        }

        if( !( princ instanceof BBDAPIPrincipal ) ) {
            return false;
        }

        BBDAPIPrincipal principal = ( BBDAPIPrincipal ) princ;

        if( password == null || userName == null
                        || principal.getPassword() == null || principal.getName() == null ) {
            return false;
        }

        if( principal.getPassword().equals( this.password )
                        && principal.getName().equals( this.userName ) ) {
            return true;
        }

        return false;

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + ( this.password != null ? this.password.hashCode() : 0 );
        hash = 97 * hash + ( this.userName != null ? this.userName.hashCode() : 0 );
        return hash;
    }

    /**
     * Return the number of usages of this principal
     *
     */
    synchronized int getUsages() {
        return usageCounter;
    }
}
