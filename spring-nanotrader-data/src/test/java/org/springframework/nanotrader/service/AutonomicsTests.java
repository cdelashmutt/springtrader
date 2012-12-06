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
package org.springframework.nanotrader.service;

import static org.springframework.nanotrader.util.TestUtils.entry;
import static org.springframework.nanotrader.util.TestUtils.map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.nanotrader.data.domain.autonomics.Autonomic;
import org.springframework.nanotrader.data.domain.autonomics.AutonomicContext;
import org.springframework.nanotrader.data.domain.autonomics.MapBasedAutonomicContext;
import org.springframework.nanotrader.data.domain.autonomics.Response;
import org.springframework.nanotrader.data.domain.autonomics.Sensor;
import org.springframework.nanotrader.data.service.AutonomicsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests the autonomics functionality in SpringTrader
 * 
 * @author cdelashmutt
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class AutonomicsTests {

	@Autowired
	private AutonomicsService autonomicsService;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSimpleAutonomic() {
		
		Autonomic autonomic = new Autonomic();
		
		autonomic.addSensor(
			new Sensor() {
				public boolean shouldFire(AutonomicContext context) {
					return true;
				}
			}
		);
		
		autonomic.addResponse(
			new Response() {
				public void perform(AutonomicContext context) {
					context.put("result", "foo");
				}
			}
		);
		
		AutonomicContext context = new MapBasedAutonomicContext(
			map(
					entry("test", 2)
				)
			);

		autonomicsService.evaluate(autonomic, context);
		
		assertThat("Existing context should remain", (Integer)context.get("test"), equalTo(2));
		assertThat("Positive sensor should effect a response", (String)context.get("result"), equalTo("foo"));
	}

}