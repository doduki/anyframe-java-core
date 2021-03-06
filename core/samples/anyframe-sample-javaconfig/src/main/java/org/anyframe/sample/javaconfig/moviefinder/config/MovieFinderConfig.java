/*
 * Copyright 2008-2012 the original author or authors.
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
package org.anyframe.sample.javaconfig.moviefinder.config;

import org.anyframe.sample.javaconfig.moviefinder.service.MovieFinder;
import org.anyframe.sample.javaconfig.moviefinder.service.impl.MovieFinderDao;
import org.anyframe.sample.javaconfig.moviefinder.service.impl.MovieFinderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

/**
 * Java based spring configuration for bean class with several options
 * 
 * @author SoYon Lim
 */
@Configuration
@Import(value = { MovieDaoConfig.class })
public class MovieFinderConfig {
	@Autowired
	private MovieFinderDao movieFinderDao;

	@Bean(initMethod = "initialize", destroyMethod = "destroy")
	@Lazy(value = false)
	@Description("Provides a MovieFinder bean")
	public MovieFinder movieFinder() {
		return new MovieFinderImpl(movieFinderDao);
	}
}
