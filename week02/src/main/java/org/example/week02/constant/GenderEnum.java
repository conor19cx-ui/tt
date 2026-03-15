package org.example.week02.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author zx
 * @date 2026/3/13
 * @description:GenderEnum
 */
@Getter
@AllArgsConstructor
public enum GenderEnum {
    MALE("男"),
    FEMALE("女"),
    SECRET("保密");

    private final String description;

    @JsonValue
    public String getDescription() {
        return this.name();
    }

    public static GenderEnum fromDescription(String description) {
        return Arrays.stream(values())
                .filter(gender -> gender.description.equals(description))
                .findFirst()
                .orElse(null);
    }
}
