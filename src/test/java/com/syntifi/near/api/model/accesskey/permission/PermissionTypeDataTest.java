package com.syntifi.near.api.model.accesskey.permission;

import com.syntifi.near.api.exception.NoSuchTypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PermissionTypeDataTest {
    @Test
    void getClassByName_validName_shouldReturn_correctClass() {
        String validClassName = PermissionTypeData.FULL_ACCESS.getName();
        Class<?> expectedClass = PermissionTypeData.FULL_ACCESS.getClazz();

        assertEquals(expectedClass, PermissionTypeData.getClassByName(validClassName));
    }

    @Test
    void getClassByName_invalidName_shouldThrow_NoSuchTypeException() {
        String invalidClassName = "InvalidClassName";

        assertThrows(NoSuchTypeException.class, () -> PermissionTypeData.getClassByName(invalidClassName));
    }
}
