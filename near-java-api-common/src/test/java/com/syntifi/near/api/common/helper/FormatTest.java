package com.syntifi.near.api.common.helper;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static com.syntifi.near.api.common.helper.Format.parseNearAmount;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormatTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormatTest.class);

    private static final List<String[]> parseNearAmountSamples = Arrays.asList(
            new String[]{null, null},
            new String[]{"5.3", "5300000000000000000000000"},
            new String[]{"5", "5000000000000000000000000"},
            new String[]{"1", "1000000000000000000000000"},
            new String[]{"10", "10000000000000000000000000"},
            new String[]{"0.000008999999999837087887", "8999999999837087887"},
            new String[]{"0.000008099099999837087887", "8099099999837087887"},
            new String[]{"999.998999999999837087887000", "999998999999999837087887000"},
            new String[]{"0.000000000000001", "1000000000"},
            new String[]{"0", "0"},
            new String[]{"0.000", "0"},
            new String[]{"0.000001", "1000000000000000000"},
            new String[]{".000001", "1000000000000000000"},
            new String[]{"000000.000001", "1000000000000000000"},
            new String[]{"1,000,000.1", "1000000100000000000000000000000"});

    @Test
    void parseNearAmount_shouldEqualExpected() {
        for (String[] val : parseNearAmountSamples) {
            LOGGER.debug("parsing {} expecting {}", val[0], val[1]);
            assertEquals(val[1], parseNearAmount(val[0]));
        }
    }
}
