/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javax.persistence;

import bbd.BBDAbstractFactory;
import bbd.BBDBeanArrayList;
import bbd.IBBDAPIPrincipal;
import bbd.IBBDBeanAPI;
import bbd.IBBDBeanBroker;
import bbd.IBBDBeanConnection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.dataModel.EntityBean;

/**
 *
 * @author James Gamber
 */
public class BBDJPAEntityBroker  <B extends EntityBean, L extends BBDBeanArrayList<B>> 
           implements IBBDBeanBroker<B,L> {

	static final Logger log = Logger.getLogger(BBDJPAEntityBroker.class.getName());

	/**
	 * Keys used to find the API SQL
	 */
	/**
	 * Keys used to find the Java Class Persist SQL
	 */
	private final IBBDBeanAPI SELECT = BBDAbstractFactory.makeBBDBeanAPI("persist", "getEntities", EntityBean.class);
	private final IBBDBeanAPI UPDATE = BBDAbstractFactory.makeBBDBeanAPI("persist", "updateJCs", EntityBean.class);
	
	protected IBBDAPIPrincipal userAccess = BBDAbstractFactory.makeBBDPrincipal("?", "?");

    @Override
	public L select(final B row) {
            
            if (row == null) {
                return selectAll();
            }

		SELECT.setBbdPrincipal(userAccess);

		final IBBDBeanConnection<B, L> myConnection = BBDAbstractFactory.makeBBDBeanConnection();
		L list = (L) new BBDBeanArrayList<B>();
		try {
			list = (L) myConnection.executeQuery(SELECT);
		} catch (final SQLException e) {
			log.severe(e.toString());
		}

		return list;
	}

	public L selectAll() {

		SELECT.setBbdPrincipal(userAccess);

		final IBBDBeanConnection<B, L> myConnection = BBDAbstractFactory.makeBBDBeanConnection();
		L list = (L) new BBDBeanArrayList<B>();
		try {
			list = (L) myConnection.executeQuery(SELECT);
		} catch (final SQLException e) {
			log.severe(e.toString());
		}

		return list;
	}



    @Override
    public void setPrincipal(String name, String password) {
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
    public int update(B row) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(L list) {
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
