/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.enterprise.wechat;

import com.damon.enterprise.wechat.client.WeChatClient;
import com.damon.enterprise.wechat.properties.WeChatProperties;
import com.damon.enterprise.wechat.serializer.WeChatLocalDateDeserializer;
import com.damon.enterprise.wechat.serializer.WeChatLocalDateSerializer;
import com.damon.enterprise.wechat.serializer.WeChatLocalDateTimeDeserializer;
import com.damon.enterprise.wechat.serializer.WeChatLocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.damon.enterprise.wechat.exception.WeChatConfigException;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-11 16:09
 */
@Configuration
@ComponentScan("com.damon.enterprise.wechat.service")
@EnableConfigurationProperties(WeChatProperties.class)
public class EnterpriseWeChatConfiguration {

    private WeChatProperties weChatProperties;

    public EnterpriseWeChatConfiguration(WeChatProperties weChatProperties) {
        this.weChatProperties = weChatProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public WeChatClient weChatClient() {
        if (StringUtils.isEmpty(weChatProperties.getUrl())) {
            throw new WeChatConfigException("url", "企业微信调用地址未配置");
        }

        Retryer retryer = Retryer.NEVER_RETRY;

        if (Boolean.TRUE.equals(weChatProperties.getRetry())) {
            retryer = new Retryer.Default(weChatProperties.getPeriod(), weChatProperties.getMaxPeriod(),
                weChatProperties.getMaxAttempts());
        }

        return Feign.builder().logger(new Slf4jLogger()).logLevel(feign.Logger.Level.FULL).encoder(jacksonEncoder())
            .decoder(jacksonDecoder())
            .options(new Request.Options(weChatProperties.getConnectTimeOut(), weChatProperties.getReadTimeout()))
            .retryer(retryer).target(WeChatClient.class, weChatProperties.getUrl());
    }


    private JacksonEncoder jacksonEncoder() {
        SimpleModule encoderModule = new SimpleModule();
        encoderModule.addSerializer(LocalDate.class, new WeChatLocalDateSerializer());
        encoderModule.addSerializer(LocalDateTime.class, new WeChatLocalDateTimeSerializer());

        ObjectMapper objectMapper = (new ObjectMapper()).setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(SerializationFeature.INDENT_OUTPUT, true).registerModules(encoderModule);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
        return new JacksonEncoder(objectMapper);
    }

    private JacksonDecoder jacksonDecoder() {
        SimpleModule decoderModule = new SimpleModule();

        decoderModule.addDeserializer(LocalDate.class, new WeChatLocalDateDeserializer());
        decoderModule.addDeserializer(LocalDateTime.class, new WeChatLocalDateTimeDeserializer());
        ObjectMapper decoderObjectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModules(decoderModule);
        return new JacksonDecoder(decoderObjectMapper);
    }

}
