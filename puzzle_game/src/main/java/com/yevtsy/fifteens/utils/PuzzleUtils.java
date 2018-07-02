package com.yevtsy.fifteens.utils;

public class PuzzleUtils {

    /**
     * Return the initial(terminated) representation
     * of puzzle board
     *
     * @param size - the size of puzzle board
     * @return initial state of puzzle board
     */
    public static byte[] init(int size) {
        byte[] original = new byte[size];

        for (int i = 0; i < size; ++i) {
            original[i] = (byte) (i + 1);
        }

        original[size - 1] = 0;
        return original;
    }
}
