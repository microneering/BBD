/**
 * 
 */
package myApp.myDataModel;
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
 * BBDDemoApplication/myApp.myDataModel/HelloBeanBroker.java
 */
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import bbd.BBDAbstractFactory;
import bbd.BBDBeanArrayList;
import bbd.IBBDAPIPrincipal;
import bbd.IBBDBeanAPI;
import bbd.IBBDBeanConnection;

/**
 * @param B extend HelloBean
 * @author james gamber
 * 
 */
public class HelloBeanBroker<B extends HelloBean> {

	static final Logger log = Logger.getLogger(HelloBeanBroker.class.getName());

	/**
	 * Keys used to find the API SQL
	 */
	private final IBBDBeanAPI selectHello = BBDAbstractFactory.makeBBDBeanAPI("test", "HelloWorld",HelloBean.class);
	private final IBBDBeanAPI selectHello2 = BBDAbstractFactory.makeBBDBeanAPI("test", "HelloWorld2",HelloBean.class);
	private final IBBDBeanAPI selectHello3 = BBDAbstractFactory.makeBBDBeanAPI("test",
			"HelloDataModelBroker.selectHello3","select 'Hello pass through sql!'",HelloBean.class);

	/**
	 * Create a list of HelloBeans.
	 * 
	 * @return Empty list of type ArrayList<B> .
	 */
	public List<B> select() {

		return new ArrayList<B>();

	}

	/**
	 * Ask the database for information using an BBD API that has been
	 * deprecated.
	 * 
	 * @return Array list of type BBDArrayList<B> that contains info returned
	 *         by database.
	 */
	public List<B> select1() {

			final IBBDAPIPrincipal userAccess = BBDAbstractFactory.makeBBDPrincipal("Duke", "Java");
			selectHello.setBbdPrincipal(userAccess);
	
		final IBBDBeanConnection<B, BBDBeanArrayList<B>> myConnection = BBDAbstractFactory.makeBBDBeanConnection();
		List<B> bbdBeanList = new BBDBeanArrayList<B>();

		try {

			bbdBeanList = myConnection.executeQuery(selectHello);

		} catch (final SQLException e) {
			log.severe(e.toString());
		}

		return bbdBeanList;
	}

	/**
	 * Ask the database for information by passing input parameter to database.
	 * 
	 * @return Array list of type BBDArrayList<B> that contains info returned
	 *         by database.
	 */
	public List<B> select2() {

		final IBBDAPIPrincipal userAccess = BBDAbstractFactory.makeBBDPrincipal("Duke", "Java");
		selectHello2.setBbdPrincipal(userAccess);

		final IBBDBeanConnection<B, BBDBeanArrayList<B>> myConnection = BBDAbstractFactory.makeBBDBeanConnection();
		List<B> bbdBeanList = new BBDBeanArrayList<B>();

		try {

			bbdBeanList = myConnection.executeQuery(selectHello2, "Duke");

		} catch (final SQLException e) {
			log.severe(e.toString());
		} 

		return bbdBeanList;
	}

	/**
	 * Ask the database for information using dynamic SQL and bypassing the BBD
	 * API.
	 * 
	 * @return Array list of type BBDBeanList<B> that contains info returned by
	 *         database.
	 */
	public List<B> select3() {

		final IBBDAPIPrincipal userAccess = BBDAbstractFactory.makeBBDPrincipal("Duke", "Java");
		selectHello3.setBbdPrincipal(userAccess);

		final IBBDBeanConnection<B, BBDBeanArrayList<B>> myConnection = BBDAbstractFactory.makeBBDBeanConnection();
		List<B> bbdBeanList = new BBDBeanArrayList<B>();

		try {

			bbdBeanList = myConnection.executeQuery(selectHello3);

		} catch (final SQLException e) {
			log.severe(e.toString());
		}

		return bbdBeanList;
	}
	
	public static void main(final String[] args)
	{
		final HelloBeanBroker<HelloBean> hbb = new HelloBeanBroker<HelloBean>();
		List<HelloBean> list = hbb.select1();
		log.info(list.get(0).getHello());
		
		list = hbb.select2();
		log.info(list.get(0).getHello());

		
		list = hbb.select3();
		log.info(list.get(0).getHello());

		System.exit(0);       
	}

}
