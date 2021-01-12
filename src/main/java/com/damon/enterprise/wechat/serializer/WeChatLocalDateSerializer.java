/*
 * Project: phc-health-data-resource-dal
 * 
 * File Created at 2019年11月7日
 * 
 * Copyright 2012 Greenline.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Greenline Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Greenline.com.
 */
package com.damon.enterprise.wechat.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-11 16:54
 */
public class WeChatLocalDateSerializer extends JsonSerializer<LocalDate> {

	static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	@Override
	public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
	    String localDateStr = null;
	    if (value != null) {
			localDateStr = FORMATTER.format(value);
		}
		gen.writeString(localDateStr);
	}
	
}
