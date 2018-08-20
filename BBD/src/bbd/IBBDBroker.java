/*
 * IBBDBroker.java
 *
 * Created on January 21, 2007, 8:54 AM
 *
 * This interface simple redefines the bean broker in more
 * complex BBD objects, IBBDRow and BBDRowArrayList.
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
 * BBD/bbd/IBBDBroker.java
 */
/**
 * Brokers provide methods for basic Create, Update, Delete (CRUD) functions.
 * 
 * Extended classes operate on groups of stored procedures, such as: testInsert
 * testUpdate testDelete
 * 
 * These stored procedures may be operating on one table or various tables
 * internal to the stored procedure. But the broker and the stored procedrues
 * represent a logical grouping in the applications business data model.
 * 
 * @param <R> 
 * @param <L>
 * @author james gamber
 */
public interface IBBDBroker<R extends IBBDRow<IBBDField>, 
                L extends BBDRowArrayList<R>> extends IBBDBeanBroker<R,L> {}
