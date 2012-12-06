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
package org.springframework.nanotrader.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Collection of utilities for tests.
 * 
 * @author <a href="mailto:cdelashmutt@vmware.com">cdelashmutt</a>
 * @version 1.0
 */
public class TestUtils {

	public static <K, V> Map<K, V> map(Map.Entry<K, V>... entries) {
		// Create a HashMap we shouldn't have to rehash
		HashMap<K, V> returnMap = new HashMap<K, V>(Math.round(entries.length * 0.75f) + 1);

		for (Map.Entry<K, V> entry : entries) {
			returnMap.put(entry.getKey(), entry.getValue());
		}

		return returnMap;
	}
	
	public static <K,V> MapEntry<K,V> entry(K key, V value)
	{
		return new MapEntry<K,V>(key, value);
	}

	public static class MapEntry<K, V> implements Map.Entry<K, V> {

		private K key;
		private V value;

		public MapEntry(K key, V value)
		{
			this.key = key;
			this.value = value;
		}
		
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V oldVal = this.value;
			this.value = value;
			return oldVal;
		}

	}
}
