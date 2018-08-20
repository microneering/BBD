/*
 * BBDSPBroker.java
 * 
 * Created on May 21, 2007, 9:30:23 PM
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package javax.persistence.dataModel;

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
 * bbdjpaadmin/BBDEntityFieldBroker.java
 */
import bbd.BBDAbstractFactory;
import bbd.BBDBeanArrayList;
import bbd.IBBDAPIPrincipal;
import bbd.IBBDBeanAPI;
import bbd.IBBDBeanBroker;
import bbd.IBBDBeanConnection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Broker to control model for entity fields
 * @param B Entity Field ben
 * @param L List of Entity Field Beans
 * @author james gamber
 */
public class BBDEntityFieldBroker<B extends EntityFieldBean, L extends BBDBeanArrayList<B>>
        implements IBBDBeanBroker<B, L> {

    static final Logger log = Logger.getLogger(BBDEntityFieldBroker.class.getName());
    /**
     * Keys used to find the Java Class Persist SQL
     */
    private final IBBDBeanAPI SELECT = BBDAbstractFactory.makeBBDBeanAPI("persist", "getEntityFields", EntityFieldBean.class);
    private final IBBDBeanAPI SELECTALL = BBDAbstractFactory.makeBBDBeanAPI("persist", "getFields", EntityFieldBean.class);
    private final IBBDBeanAPI UPDATE = BBDAbstractFactory.makeBBDBeanAPI("persist", "updateEntityField", EntityFieldBean.class);
    protected IBBDAPIPrincipal userAccess = BBDAbstractFactory.makeBBDPrincipal("?", "?");

    public BBDEntityFieldBroker() {
    }

    @Override
    public L select(final B row) {

        if (row == null) {
            return selectAll();
        }

        SELECT.setBbdPrincipal(userAccess);

        @SuppressWarnings( "unchecked" )
        final IBBDBeanConnection<B, L> myConnection = BBDAbstractFactory.makeBBDBeanConnection();
        @SuppressWarnings( "unchecked" )
        L list = (L) new BBDBeanArrayList<B>();
        try {
            list = myConnection.executeQuery(SELECT, row.getBbdjpaobjectId());
        } catch (final SQLException e) {
            log.severe(e.toString());
        }

        return list;
    }

    public L selectAll() {

        SELECTALL.setBbdPrincipal(userAccess);

        final IBBDBeanConnection<B, L> myConnection = BBDAbstractFactory.makeBBDBeanConnection();
        L list = (L) new BBDBeanArrayList<B>();
        try {
            list = (L) myConnection.executeQuery(SELECTALL);
        } catch (final SQLException e) {
            log.severe(e.toString());
        }

        return list;
    }

    @Override
    public int update(final B row) {

        return 0;
    }

    @Override
    public int update(final L list) {

        int updated = 0;

        for (B r : list) {
            int count = update(r);
            if (count == 0) {
            //count = insert(r);
            }
            updated += count;
        }

        return updated;
    }

    @Override
    public void setPrincipal(final String name, final String password) {

        userAccess = BBDAbstractFactory.makeBBDPrincipal(name, password);
    }

    @Override
    public int insert(B row) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int insert(L list) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(B row) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(L list) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Integer> insertGetGeneratedKeys( B row ) throws SQLException {
        throw new UnsupportedOperationException( "Not supported yet." );
    }

    @Override
    public List<Integer> insertGetGeneratedKeys( L list ) throws SQLException {
        throw new UnsupportedOperationException( "Not supported yet." );
    }

    @Override
    public void setPrincipal( IBBDAPIPrincipal principal ) {
        throw new UnsupportedOperationException( "Not supported yet." );
    }
}
