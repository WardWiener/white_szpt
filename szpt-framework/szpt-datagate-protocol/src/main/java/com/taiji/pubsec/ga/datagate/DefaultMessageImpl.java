/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年6月27日 上午11:14:15
 */
package com.taiji.pubsec.ga.datagate;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author yucy
 *
 */
public class DefaultMessageImpl implements Message {

	private static final long serialVersionUID = -6764603938345670657L;
	
	private String id;
	private MessageType type;
	private Serializable[] body;
	private Boolean request;
	
	public DefaultMessageImpl(String id, MessageType inout, Serializable[] body, Boolean request) {
		super();
		this.id = id;
		this.type = inout;
		this.body = body;
		this.request = request;
	}
	
	@Override
	public String getId() {
		return id;
	}
	@Override
	public MessageType getType() {
		return type;
	}
	@Override
	public Serializable[] getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "DefaultMessageImpl [id=" + id + ", type=" + type + ", body="
				+ Arrays.toString(body) + "]";
	}

	@Override
	public Boolean isRequest() {
		return request;
	}
	
}
