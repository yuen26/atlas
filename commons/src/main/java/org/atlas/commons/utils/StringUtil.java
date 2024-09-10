package org.atlas.commons.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {

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

    public static String shuffle(String source) {
        List<Character> characters = new ArrayList<>();
        for (char character : source.toCharArray()) {
            characters.add(character);
        }
        Collections.shuffle(characters);

        StringBuilder shuffledString = new StringBuilder();
        for (char character : characters) {
            shuffledString.append(character);
        }
        return shuffledString.toString();
    }
}
