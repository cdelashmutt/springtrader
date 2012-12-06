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
package org.springframework.nanotrader.data.service;

import org.springframework.nanotrader.data.domain.autonomics.Autonomic;
import org.springframework.nanotrader.data.domain.autonomics.AutonomicContext;
import org.springframework.nanotrader.data.domain.autonomics.Response;
import org.springframework.nanotrader.data.domain.autonomics.Sensor;

/**
 * Simplistic, sequential autonomics implementation
 *
 * @author <a href="mailto:cdelashmutt@vmware.com">cdelashmutt</a>
 * @version 1.0
 */
public class DefaultAutonomicsServiceImpl implements AutonomicsService {

	@Override
	public void evaluate(Autonomic autonomic, AutonomicContext autonomicContext) {
		boolean shouldFire = true;
		for(Sensor sensor : autonomic.getSensors()) {
			//If this sensor evaluates to false, then shouldFire becomes false, and we can stop evaluating sensors
			if( !( shouldFire &= sensor.shouldFire(autonomicContext) ) )
				break;
		}
		
		if(shouldFire)
		{
			for(Response response : autonomic.getResponses()) {
				response.perform(autonomicContext);
			}
		}
	}

}
