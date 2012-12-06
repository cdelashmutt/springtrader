/*
 * Copyright 2002-2012 the original author or authors.
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
package org.springframework.nanotrader.data.domain.autonomics;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO: Describe MapBasedAutonomicContext
 *
 * @author <a href="mailto:cdelashmutt@vmware.com">cdelashmutt</a>
 * @version 1.0
 */
public class MapBasedAutonomicContext implements AutonomicContext {
	
	private Map<String,Object> source = new HashMap<String,Object>(0);
	
	@SuppressWarnings("unchecked")
	public MapBasedAutonomicContext(Map<String,? extends Object> source)
	{
		this.source = (Map<String,Object>)source;
	}

	@Override
	public Object get(String key) {
		return source.get(key);
	}

	@Override
	public void put(String key, Object value) {
		source.put(key, value);
	}
}
