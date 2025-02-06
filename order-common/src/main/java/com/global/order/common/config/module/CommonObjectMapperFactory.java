package com.global.order.common.config.module;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.YearMonthDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.YearMonthSerializer;
import com.global.order.common.code.CodeEnum;
import com.global.order.common.utils.DefaultDateTimeFormatUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonObjectMapperFactory {

    public static ObjectMapper defaultObjectMapper() {
        return new Jackson2ObjectMapperBuilder()
                .failOnUnknownProperties(false) // SpringBoot default
                .featuresToDisable(MapperFeature.DEFAULT_VIEW_INCLUSION) // SpringBoot default
                .featuresToEnable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // SpringBoot default
                .serializerByType(LocalDate.class, new LocalDateSerializer(DefaultDateTimeFormatUtils.DATE_FORMAT))
                .deserializerByType(LocalDate.class, new LocalDateDeserializer(DefaultDateTimeFormatUtils.DATE_FORMAT))
                .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DefaultDateTimeFormatUtils.DATE_TIME_FORMAT))
                .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DefaultDateTimeFormatUtils.DATE_TIME_FORMAT))
                .serializerByType(LocalTime.class, new LocalTimeSerializer(DefaultDateTimeFormatUtils.TIME_FORMAT))
                .deserializerByType(LocalTime.class, new LocalTimeDeserializer(DefaultDateTimeFormatUtils.TIME_FORMAT))
                .serializerByType(YearMonth.class, new YearMonthSerializer(DefaultDateTimeFormatUtils.YEAR_MONTH_FORMAT))
                .deserializerByType(YearMonth.class, new YearMonthDeserializer(DefaultDateTimeFormatUtils.YEAR_MONTH_FORMAT))
                .serializerByType(CodeEnum.class, new CodeEnumJsonConverter.Serializer())
                .deserializerByType(Enum.class, new CodeEnumJsonConverter.Deserializer())
                .build();
    }
}
