package com.taska.pm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskStatus {

    PENDING(EnumValues.PENDING_VALUE),
    IN_WORK(EnumValues.IN_WORK_VALUE),
    POSTPONED(EnumValues.POSTPONED_VALUE),
    REWORK(EnumValues.REWORK_VALUE),
    DONE(EnumValues.DONE_VALUE),
    TESTING(EnumValues.TESTING_VALUE),
    CHECK(EnumValues.CHECK_VALUE),
    CHECKED(EnumValues.CHECKED_VALUE);

    private final String displayValue;
}
