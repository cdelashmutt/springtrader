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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * A set of conditions to evaluate against a security to automated actions
 * 
 * @author cdelashmutt
 */
//@Entity
//@Table(name = "screener")
public class Autonomic implements Serializable {

	private static final long serialVersionUID = 6495530239269408161L;

	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "screenerid")
    private Integer screenerid;

	@Version
    @Column(name = "version")
    private int version = 0;
	
	@OneToMany(mappedBy = "screener_screenerId")
	private List<Sensor> sensors = new ArrayList<Sensor>(0);

	@OneToMany(mappedBy = "screener_screenerId")
	private List<Response> responses = new ArrayList<Response>(0);
	
	@Column(name="name", length=30)
	private String name;
	
	@Column(name="description", length=250)
	private String description;

	public void addSensor(Sensor sensor) {
		sensors.add(sensor);
	}

	public void addResponse(Response response) {
		responses.add(response);
	}
	
	public List<Sensor> getSensors() {
		return sensors;
	}
	
	public List<Response> getResponses() {
		return responses;
	}
}
