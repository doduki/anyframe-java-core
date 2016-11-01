/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.anyframe.xp.query;

import java.io.PrintWriter;
import java.util.Map;

import org.anyframe.query.QueryServiceException;

import com.tobesoft.xplatform.data.DataSet;
import com.tobesoft.xplatform.data.DataSetList;
import com.tobesoft.xplatform.data.VariableList;

/**
 * This is the interface used for manipulating database in developing the
 * presentation layer using XPlatform which is the RIA solution of the TOBE
 * Software company.
 * <p>
 * In order to use XPQueryService the
 * anyframe.core.query.ria.impl.RiaQueryServiceImpl</code> class has to be
 * registered as Bean.
 * </p>
 * XPQueryService Configuration Example:
 * 
 * <pre>
 * &lt;bean name=&quot;riaQueryService&quot; class=&quot;anyframe.core.query.ria.impl.RiaQueryServiceImpl&quot;&gt;
 *      &lt;property name=&quot;jdbcTemplate&quot; ref=&quot;pagingNamedParamJdbcTemplate&quot;/&gt;
 *      &lt;property name=&quot;lobHandler&quot; ref=&quot;lobHandler&quot;/&gt;
 *      &lt;property name=&quot;sqlRepository&quot; ref=&quot;sqlLoader&quot;/&gt;
 * &lt;/bean&gt;
 * &lt;bean id=&quot;xpQueryService&quot; class=&quot;anyframe.core.query.ria.xp.impl.XPQueryServiceImpl&quot;&gt;
 *      &lt;property name=&quot;riaQueryService&quot; ref=&quot;riaQueryService&quot;/&gt;
 *      &lt;property name=&quot;sqlRepository&quot; ref=&quot;sqlLoader&quot;/&gt;
 * &lt;/bean&gt;
 * 
 * &lt;bean id=&quot;pagingNamedParamJdbcTemplate&quot; 
 *          class=&quot;anyframe.core.query.impl.jdbc.PagingNamedParamJdbcTemplate&quot;&gt;
 *      &lt;constructor-arg index=&quot;0&quot; ref=&quot;pagingJdbcTemplate&quot;/&gt;
 *      &lt;constructor-arg index=&quot;1&quot; ref=&quot;dataSource&quot;/&gt;
 * &lt;/bean&gt;
 * </pre>
 * 
 * <p>
 * The MiQueryService's configuration has <code>riaQueryService</code>,
 * <code>sqlRepository</code>.
 * </p>
 * 
 * @author Soyon Lim
 * @author JongHoon Kim
 */
public interface XPQueryService {
	String QUERY_INSERT = DataSet.ROW_TYPE_NAME_INSERTED;

	String QUERY_UPDATE = DataSet.ROW_TYPE_NAME_UPDATED;

	String QUERY_DELETE = DataSet.ROW_TYPE_NAME_DELETED;

	/**
	 * This is a method for querying using the VariableList
	 * 
	 * @param queryId identifier of query statement to execute
	 * @param variableList VariableList including the query conditions
	 * @return DataSet of extracted out parameters
	 * @throws Exception if there is any problem executing the query
	 */
	DataSet search(String queryId, VariableList variableList) throws Exception;

	/**
	 * This is a method for querying with paging using the VariableList
	 * 
	 * @param queryId identifier of query statement to execute
	 * @param variableList VariableList including the query conditions
	 * @param pageIndex page number which expected to be displayed (pageIndex >
	 * 0)
	 * @param pageSize page size which expected to be displayed per page
	 * (pageSize > 0)
	 * @return DataSet of extracted out parameters
	 * @throws Exception if there is any problem executing the query
	 */
	DataSet search(String queryId, VariableList variableList, int pageIndex, int pageSize) throws Exception;

	/**
	 * This is a method for querying using the DataSet
	 * 
	 * @param queryId identifier of query statement to execute
	 * @param dataSet the DataSet including the query conditions
	 * @return DataSet of extracted out parameters
	 * @throws Exception if there is any problem executing the query
	 */
	DataSet search(String queryId, DataSet dataSet) throws Exception;

	/**
	 * This is the method at querying using the VariableList. The return value
	 * is <code>void</code> because the query result is put to the screen as
	 * Stream using PrintWriter. At large scale querying, this is recommended.
	 * 
	 * @param queryId identifier of query statement to execute
	 * @param variableList the VariableList including the query conditions
	 * @param pageIndex page number which expected to be displayed (pageIndex >
	 * 0)
	 * @param pageSize page size which expected to be displayed per page
	 * (pageSize > 0)
	 * @param dataSetName the id of DataSet for saving the query result
	 * @param writer the PrintWriter object for writing the query result
	 * @throws Exception if there is any problem executing the query
	 */
	void search(String queryId, VariableList variableList, int pageIndex, int pageSize, String dataSetName,
			PrintWriter writer) throws Exception;

	/**
	 * This is a method for querying using the DataSet for paging
	 * @param queryId identifier of query statement to execute
	 * @param dataSet the DataSet including the query conditions, page
	 * index("pageIndex") and page size("pageSize").
	 * @return DataSet of extracted out parameters
	 * @throws Exception if there is any problem executing the query
	 */
	DataSet searchWithPaging(String queryId, DataSet dataSet) throws Exception;

	/**
	 * This is the method for inserting, updating and deleting the unitary data
	 * using the VariableList.
	 * 
	 * @param queryId identifier of query statement to execute
	 * @param variableList the VariableList including the query conditions
	 * @return the number of records affected
	 * @throws QueryServiceException if there is any problem executing the query
	 */
	int update(String queryId, VariableList variableList) throws QueryServiceException;

	/**
	 * This is the method for inserting, updating and deleting using
	 * VariableList and DataSet.
	 * 
	 * @param queryId identifier of query statement to execute
	 * @param variableList the VariableList including the query conditions
	 * @param queryMap the map with status of row and query identifier. For
	 * insert status, the Map's key is insert, update status is update, delete
	 * status is delete. We decide the query id executed in using the Status
	 * value because in the DataSet the value of appropriate record's Status is
	 * set.
	 * @param paramDataSet the DataSet including the query conditions
	 * @return the number of records affected
	 * @throws QueryServiceException if there is any problem executing the query
	 */
	int update(String queryId, VariableList variableList, Map queryMap, DataSet paramDataSet)
			throws QueryServiceException;

	/**
	 * This is for inserting, updating and deleting using VariableList and
	 * DataSet. The IXPActionCommand's implemented class containing the needed
	 * business logic is executed Before/after manipulating a database.
	 * 
	 * @param queryId identifier of query statement to execute
	 * @param variableList the VariableList including the query conditions
	 * @param queryMap the map with status of row and query identifier. For
	 * insert status, the Map's key is insert, update status is update, delete
	 * status is delete. We decide the query id executed in using the Status
	 * value because in the DataSet the value of appropriate record's Status is
	 * set.
	 * @param dataSet the DataSet including the query conditions
	 * @param actionCommand the XPActionCommand including the business logic
	 * before/after the insert, update, delete execution.
	 * @return the number of records affected
	 * @throws QueryServiceException if there is any problem executing the query
	 */
	int update(String queryId, VariableList variableList, Map queryMap, DataSet dataSet, XPActionCommand actionCommand)
			throws QueryServiceException;

	/**
	 * the method for inserting, updating and deleting using the DataSet to the
	 * database.
	 * 
	 * @param queryMap the map with status of row and query identifier. For
	 * insert status, the Map's key is insert, update status is update, delete
	 * status is delete. We decide the query id executed in using the Status
	 * value because in the DataSet the value of appropriate record's Status is
	 * set.
	 * @param dataSet the DataSet including the query conditions
	 * @return the number of records affected
	 * @throws QueryServiceException if there is any problem executing the query
	 */
	int update(Map queryMap, DataSet dataSet) throws QueryServiceException;

	/**
	 * This is the method for inserting, updating and deleting using the DataSet
	 * to the database. The IXPActionCommand's implemented class containing the
	 * needed business logic is executed Before/after manipulating a database.
	 * 
	 * @param queryMap the map with status of row and query identifier. For
	 * insert status, the Map's key is insert, update status is update, delete
	 * status is delete. We decide the query id executed in using the Status
	 * value because in the DataSet the value of appropriate record's Status is
	 * set.
	 * @param dataSet the DataSet including the query conditions
	 * @param actionCommand the XPActionCommand including the business logic
	 * before/after the insert, update, delete execution.
	 * @return the number of records affected
	 * @throws QueryServiceException if there is any problem executing the query
	 */
	int update(Map queryMap, DataSet dataSet, XPActionCommand actionCommand) throws QueryServiceException;

	DataSetList execute(String queryId)throws QueryServiceException;
	
	/**
	 * This is the method for executing callablestatement using the DataSet to
	 * the database.
	 * 
	 * @param queryId identifier of query statement to execute
	 * @param dataset the DataSet including the query conditions
	 * @return DataSet of extracted out parameters
	 * @throws QueryServiceException if there is any problem executing the query
	 */
	DataSetList execute(String queryId, DataSet dataset) throws QueryServiceException;

}