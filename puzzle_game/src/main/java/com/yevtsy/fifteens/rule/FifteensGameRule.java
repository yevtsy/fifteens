package com.yevtsy.fifteens.rule;

import com.yevtsy.fifteens.board.Board;

import java.util.Arrays;

public class FifteensGameRule implements GameRule {

    @Override
    public boolean isValidState(byte[] state) {
        final int sideSize = (int) Math.sqrt(state.length);

        int parity = 0;
        int row = 0;
        int blankRow = 0;

        for (int i = 0; i < state.length; i++) {
            if (i % sideSize == 0) {
                row++;
            }

            if (state[i] == 0) {
                blankRow = row;
                continue;
            }

            for (int j = i + 1; j < state.length; j++) {
                if (state[i] > state[j] && state[j] != 0) {
                    parity++;
                }
            }
        }

        return sideSize % 2 == 0
                ? blankRow % 2 == 0 ? parity % 2 == 0 : parity % 2 != 0
                : parity % 2 == 0;
    }

    @Override
    public boolean isGameOver(Board actual, Board expected) {
        return Arrays.equals(actual.state(), expected.state());
    }

    @Override
    public byte[] terminatedState(int size) {
        byte[] original = new byte[size];

        for (int i = 0; i < size; ++i) {
            original[i] = (byte) (i + 1);
        }

        original[size - 1] = 0;
        return original;
    }
}
