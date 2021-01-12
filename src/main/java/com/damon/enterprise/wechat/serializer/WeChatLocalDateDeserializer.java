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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-11 16:54
 */
public class WeChatLocalDateDeserializer extends JsonDeserializer<LocalDate> {

	@Override
	public LocalDate deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException {
		String localDateStr = p.readValueAs(String.class);
		if (localDateStr == null) {
			return null;
		}
		return LocalDate.parse(localDateStr, WeChatLocalDateSerializer.FORMATTER);
	}

}
