package org.atlas.shared.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {

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
