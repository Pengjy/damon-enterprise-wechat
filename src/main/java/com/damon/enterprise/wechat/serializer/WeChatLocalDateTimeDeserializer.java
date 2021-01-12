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
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-11 16:54
 */
public class WeChatLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

	@Override
	public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException {
	    String localDateTimeStr = p.readValueAs(String.class);
	    if (localDateTimeStr == null || localDateTimeStr.isEmpty()) {
            return null;
        }
	    LocalDateTime ldt = null;
	    try {
	        ldt = LocalDateTime.parse(localDateTimeStr, WeChatLocalDateTimeSerializer.FORMATTER_1);
        } catch (DateTimeParseException e1) {
            try {
                ldt = LocalDateTime.parse(localDateTimeStr, WeChatLocalDateTimeSerializer.FORMATTER_2);
            } catch (DateTimeParseException e2) {
                try {
                    ldt = LocalDateTime.parse(localDateTimeStr, WeChatLocalDateTimeSerializer.FORMATTER_3);
                } catch (DateTimeParseException e3) {
                    try {
                        ldt = LocalDateTime.parse(localDateTimeStr, WeChatLocalDateTimeSerializer.FORMATTER_4);
                    } catch (DateTimeParseException e4) {
                        throw new DateTimeParseException("cannot parse " + localDateTimeStr, localDateTimeStr, 0);
                    }
                }
            }
        }
		return ldt;
	}

}
