package by.kovalevskii.framework.util;

import java.util.Random;

public class StringUtil {
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrtsuvwxyz";
    private static final Random random = new Random();

    public static String randomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(LETTERS.charAt(random.nextInt(LETTERS.length())));
        }
        return sb.toString();
    }

    public static String randomEmail() {
        StringBuilder sb = new StringBuilder();
        sb.append(randomString(8))
                .append('@')
                .append(randomString(4))
                .append('.')
                .append(randomString(2));
        return sb.toString();
    }
}
