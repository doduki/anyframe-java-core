/*
 * Copyright 2008-2011 the original author or authors.
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
package org.anyframe.plugin.jquery.category.service;

import java.util.List;

import org.anyframe.pagination.Page;
import org.anyframe.plugin.jquery.domain.JqueryCategory;

/**
 * This class is a Interface class to provide category crud functionality.
 * 
 * @author Sujeong Lee
 *
 */
public interface JqueryCategoryService {
	
	/**
	 * @param category
	 * @return
	 * @throws Exception
	 */
	String create(JqueryCategory category) throws Exception;
	
	/**
	 * @param category
	 * @return
	 * @throws Exception
	 */
	int update(JqueryCategory category) throws Exception;
	
	/**
	 * @param categoryId
	 * @return
	 * @throws Exception
	 */
	int remove(String categoryId) throws Exception;
	
	/**
	 * @param categoryId
	 * @return
	 * @throws Exception
	 */
	JqueryCategory get(String categoryId) throws Exception;
	
	/**
	 * @return
	 * @throws Exception
	 */
	List<JqueryCategory> getList() throws Exception;
	
	/**
	 * @param category
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	Page getPagingList(JqueryCategory category) throws Exception;
	
	List<String> getSearchKeyword(String keyword, String condition) throws Exception;
}