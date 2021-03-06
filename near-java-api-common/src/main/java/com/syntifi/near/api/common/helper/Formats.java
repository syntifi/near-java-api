package com.syntifi.near.api.common.helper;

import java.math.BigInteger;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Helper methods for formatting
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public class Formats {

    /**
     * Exponent for calculating how many indivisible units are there in one NEAR. See {@link #NEAR_NOMINATION}.
     */
    public static int NEAR_NOMINATION_EXP = 24;

    /**
     * Number of indivisible units in one NEAR. Derived from {@link #NEAR_NOMINATION_EXP}.
     */
    public static BigInteger NEAR_NOMINATION = BigInteger.valueOf(10).pow(NEAR_NOMINATION_EXP);

    /**
     * Pre-calculate offsets used for rounding to different number of digits
     */
    static BigInteger[] ROUNDING_OFFSETS = new BigInteger[NEAR_NOMINATION_EXP];
    static {
        BigInteger ten = BigInteger.valueOf(10);
        for (BigInteger i = BigInteger.ZERO, offset = BigInteger.valueOf(5);
             i.compareTo(BigInteger.valueOf(NEAR_NOMINATION_EXP)) < 0;
             i = i.add(BigInteger.ONE), offset = offset.multiply(ten)) {
            ROUNDING_OFFSETS[(int) i.longValue()] = offset;
        }
    }

    /**
     * Convert account balance value from internal indivisible units to NEAR. 1 NEAR is defined by {@link #NEAR_NOMINATION}.
     * Effectively this divides given amount by {@link #NEAR_NOMINATION}.
     *
     * @param balance    decimal string representing balance in smallest non-divisible NEAR units (as specified by {@link #NEAR_NOMINATION})
     * @param fracDigits number of fractional digits to preserve in formatted string. Balance is rounded to match given number of digits.
     * @return Value in Ⓝ
     */
    public static String formatNearAmount(String balance, Integer fracDigits) {
        if (fracDigits == null) fracDigits = NEAR_NOMINATION_EXP;
        BigInteger balanceBN = new BigInteger(balance, 10);
        if (fracDigits != NEAR_NOMINATION_EXP) {
            // Adjust balance for rounding at given number of digits
            final int roundingExp = NEAR_NOMINATION_EXP - fracDigits - 1;
            if (roundingExp > 0) {
                balanceBN = balanceBN.add(ROUNDING_OFFSETS[roundingExp]);
            }
        }

        balance = balanceBN.toString();
        final String wholeStr = balance != null && balance.length() - NEAR_NOMINATION_EXP > 0 ? balance.substring(0, balance.length() - NEAR_NOMINATION_EXP) : "0";
        final String fractionStr = balance != null ? padStart(balance.substring(Math.max(balance.length() - NEAR_NOMINATION_EXP, 0)), NEAR_NOMINATION_EXP, '0').substring(0, fracDigits) : "0";

        return trimTrailingZeroes(formatWithCommas(wholeStr) + "." + fractionStr);
    }

    /**
     * Convert human-readable NEAR amount to internal indivisible units.
     * Effectively this multiplies given amount by {@link #NEAR_NOMINATION}.
     *
     * @param amount decimal string (potentially fractional) denominated in NEAR.
     * @return The parsed yoctoⓃ amount or null if no amount was passed in
     */
    public static String parseNearAmount(String amount) {
        if (amount == null) {
            return null;
        }
        amount = cleanupAmount(amount);
        final String[] split = amount.split("\\.");
        final String wholePart = split[0];
        final String fracPart = split.length > 1 ? split[1] : "";
        if (split.length > 2 || fracPart.length() > NEAR_NOMINATION_EXP) {
            throw new RuntimeException("Cannot parse '" + amount + "' as NEAR amount");
        }
        final String toTrim = wholePart + padEnd(fracPart, NEAR_NOMINATION_EXP, '0');
        return trimLeadingZeroes(toTrim);
    }

    /**
     * Pads an input string from its start with a char until it reaches a max length
     *
     * @param input     the input string
     * @param maxLength the max length to reach
     * @param fillChar  the char to fill
     * @return the padded string
     */
    public static String padStart(String input, int maxLength, char fillChar) {
        StringBuilder sb = new StringBuilder();
        sb.append(input);
        while (sb.length() < maxLength) {
            sb.insert(0, fillChar);
        }
        return sb.toString();
    }

    /**
     * Pads an input string from its end with a char until it reaches a max length
     *
     * @param input     the input string
     * @param maxLength the max length to reach
     * @param fillChar  the char to fill
     * @return the padded string
     */
    public static String padEnd(String input, int maxLength, char fillChar) {
        StringBuilder sb = new StringBuilder();
        sb.append(input);
        while (sb.length() < maxLength) {
            sb.append(fillChar);
        }
        return sb.toString();
    }

    /**
     * Removes commas from the input
     *
     * @param amount A value or amount that may contain commas
     * @return string The cleaned value
     */
    public static String cleanupAmount(String amount) {
        return amount.replace(",", "").trim();
    }

    /**
     * Removes .000… from an input
     *
     * @param value A value that may contain trailing zeroes in the decimals place
     * @return string The value without the trailing zeros
     */
    public static String trimTrailingZeroes(String value) {
        return value.replaceAll("\\.?0*$", "");
    }

    /**
     * Removes leading zeroes from an input
     *
     * @param value A value that may contain leading zeroes
     * @return string The value without the leading zeroes
     */
    public static String trimLeadingZeroes(String value) {
        value = value.replaceAll("^0+", "");
        if (Objects.equals(value, "")) {
            return "0";
        }
        return value;
    }

    /**
     * Returns a human-readable value with commas
     *
     * @param value A value that may not contain commas
     * @return string A value with commas
     */
    public static String formatWithCommas(String value) {
        final String patternString = "(-?\\d+)(\\d{3})";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(value);
        while (matcher.find()) {
            value = value.replace(matcher.group(0), matcher.group(1) + "," + matcher.group(2));
            matcher = pattern.matcher(value);
        }
        return value;
    }

}
