package org.chocodev.uploadthing.core.util;

public class HashService {
    public int stringHash(String str) {
        int h = 5381;
        int i = str.length();
        while (i != 0) {
            h = (h * 33) ^ str.charAt(--i);
        }
        return optimize(h);
    }

    public int optimize(int n) {
        return (n & 0xbfffffff) | ((n >>> 1) & 0x40000000);
    }

    public String shuffle(String str, String seed) {
        char[] chars = str.toCharArray();
        int seedNum = stringHash(seed);

        for (int i = 0; i < chars.length; i++) {
            int j = ((seedNum % (i + 1)) + i) % chars.length;
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }

        return new String(chars);
    }
}
