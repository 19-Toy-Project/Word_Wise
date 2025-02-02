package com.wordwise.common.utils;

import com.wordwise.common.enums.WordType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * JPA에서 해당 컨버터를 자동으로 적용하도록 지정.
 * 엔티티에서 @Convert를 따로 지정하지 않아도, WordType 타입을 발견하면 자동 적용됨.
 * autoApply = false로 설정하면, 개별 필드에 @Convert(WordTypeConverter.class)를 명시해야 적용됨.
 */
@Converter(autoApply = true)
public class WordTypeConverter implements AttributeConverter<WordType, Integer> {

    // Enum을 DB 숫자 값으로 변환
    @Override
    public Integer convertToDatabaseColumn(WordType attribute) {
        if (attribute == null) {
            return null;
        }
        return WordType.getCode();
    }

    // 숫자를 Enum 값으로 변환
    @Override
    public WordType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return WordType.fromCode(dbData);
    }



}
