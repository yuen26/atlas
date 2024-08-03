package org.atlas.shared.util.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MaskUtil {

    public static String mask(String str, int firstChars, char maskChar) {
        if (str == null) {
            return null;
        }

        int strLength = str.length();
        if (firstChars <= 0) {
            char[] maskedArray = new char[strLength];
            Arrays.fill(maskedArray, maskChar);
            return new String(maskedArray);
        }

        if (strLength <= firstChars) {
            return str;
        }

        return str.substring(0, firstChars) + String.valueOf(maskChar).repeat(strLength - firstChars);
    }
}
