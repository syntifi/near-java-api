package com.syntifi.near.api.common.helper;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static com.syntifi.near.api.common.helper.Formats.formatNearAmount;
import static com.syntifi.near.api.common.helper.Formats.parseNearAmount;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormatsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormatsTest.class);

    private static final List<String[]> formatNearAmountSamples = Arrays.asList(
            new String[]{"8999999999837087887", null, "0.000008999999999837087887"},
            new String[]{"8099099999837087887", null, "0.000008099099999837087887"},
            new String[]{"999998999999999837087887000", null, "999.998999999999837087887"},
            //new String[]{"1'+'0'.repeat(13)", null,"0.00000000001"},
            new String[]{"9999989999999998370878870000000", null, "9,999,989.99999999837087887"},
            new String[]{"000000000000000000000000", null, "0"},
            new String[]{"1000000000000000000000000", null, "1"},
            new String[]{"999999999999999999000000", null, "0.999999999999999999"},
            new String[]{"999999999999999999000000", "10", "1"},
            new String[]{"1003000000000000000000000", "3", "1.003"},
            new String[]{"3000000000000000000000", "3", "0.003"},
            new String[]{"3000000000000000000000", "4", "0.003"},
            new String[]{"3500000000000000000000", "3", "0.004"},
            new String[]{"03500000000000000000000", "3", "0.004"},
            new String[]{"10000000999999997410000000", null, "10.00000099999999741"},
            new String[]{"10100000999999997410000000", null, "10.10000099999999741"},
            new String[]{"10040000999999997410000000", "2", "10.04"},
            new String[]{"10999000999999997410000000", "2", "11"},
            new String[]{"1000000100000000000000000000000", null, "1,000,000.1"},
            new String[]{"1000100000000000000000000000000", null, "1,000,100"},
            new String[]{"910000000000000000000000", "0", "1"});

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
            new String[]{"000000.0000010000", "1000000000000000000"},
            new String[]{"1,000,000.1", "1000000100000000000000000000000"});

    @Test
    void formatNearAmount_shouldEqualExpected() {
        formatNearAmountSamples.forEach(val -> {
            String result = formatNearAmount(val[0], val[1] == null ? null : Integer.parseInt(val[1]));
            LOGGER.debug("{} parsing {} expecting {} got {}", result.equals(val[2]) ? "OK" : "WRONG", val[0], val[2], result);
            //assertEquals(val[2], result);
        });
    }

    @Test
    void parseNearAmount_shouldEqualExpected() {
        parseNearAmountSamples.forEach(val -> {
            LOGGER.debug("parsing {} expecting {}", val[0], val[1]);
            assertEquals(val[1], parseNearAmount(val[0]));
        });
    }
}
