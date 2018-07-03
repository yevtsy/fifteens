package com.yevtsy.fifteens.board;

import com.yevtsy.fifteens.exception.IllegalMoveException;
import com.yevtsy.fifteens.model.Move;

import java.util.Arrays;

public class FifteensBoard implements Board {
    private byte[] state;
    private int sideSize;

    public FifteensBoard(byte[] state) {
        this.state = state;
        this.sideSize = (int) Math.sqrt(state.length);
    }

    @Override
    public byte[] state() {
        return Arrays.copyOf(state, state.length);
    }

    @Override
    public Board move(Move move) throws IllegalMoveException {
        int blank = 0;
        for (; blank < state.length; ++blank) {
            if (state[blank] == 0) {
                break;
            }
        }

        final int step = step(move);
        int next = blank + step;
        if (!canMove(step, blank, next)) {
            throw new IllegalMoveException();
        }

        byte[] neighborState = Arrays.copyOf(state, state.length);
        byte temp = neighborState[blank];
        neighborState[blank] = neighborState[next];
        neighborState[next] = temp;

        return new FifteensBoard(neighborState);
    }

    @Override
    public String toString() {
        StringBuilder sbf = new StringBuilder(state.length);
        for (int i = 0; i < sideSize; i++) {
            for (int j = 0; j < sideSize; j++) {
                sbf.append(state[j + i * sideSize]);
                sbf.append('\t');
            }
            sbf.append('\n');
        }
        return sbf.toString();
    }

    private int step(Move move) {
        switch (move) {
            case UP:
                return -sideSize;
            case DOWN:
                return sideSize;
            case LEFT:
                return -1;
            case RIGHT:
                return 1;
            default:
                throw new IllegalMoveException();
        }
    }

    private boolean canMove(int step, int blank, int next) {
        if (next < 0 || next >= state.length) {
            return false;
        }

        if ((step == 1) && ((blank + 1) % sideSize == 0)) {
            return false;
        }

        return (step != -1) || ((blank + 1) % sideSize != 1);
    }
}
