package com.yevtsy.fifteens.board;

import com.yevtsy.fifteens.exception.IllegalMoveException;
import com.yevtsy.fifteens.model.Move;
import com.yevtsy.fifteens.utils.PuzzleUtils;

import java.util.Arrays;

public class FifteensBoard implements Board {
    private byte[] currentField;
    private byte[] initialField;
    private int sideSize;
    private boolean isSolvable;

    public FifteensBoard(int sideSize, byte[] currentField) {
        this.sideSize = sideSize;
        this.currentField = currentField;
        this.initialField = PuzzleUtils.init(sideSize * sideSize);
        this.isSolvable = isValidState();
    }

    @Override
    public Board move(Move move) throws IllegalMoveException {
        int blank = 0;
        for (; blank < currentField.length; ++blank) {
            if (currentField[blank] == 0) {
                break;
            }
        }

        final int step = step(move);
        int next = blank + step;
        if (!canMove(step, blank, next)) {
            throw new IllegalMoveException();
        }

        byte[] neighborField = Arrays.copyOf(currentField, currentField.length);
        byte temp = neighborField[blank];
        neighborField[blank] = neighborField[next];
        neighborField[next] = temp;

        return new FifteensBoard(sideSize, neighborField);
    }

    @Override
    public boolean isSolvable() {
        return isSolvable;
    }

    @Override
    public boolean isTerminated() {
        return Arrays.equals(currentField, initialField);
    }

    @Override
    public String toString() {
        StringBuilder sbf = new StringBuilder(currentField.length);
        for (int i = 0; i < sideSize; i++) {
            for (int j = 0; j < sideSize; j++) {
                sbf.append(currentField[j + i * sideSize]);
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
        if (next < 0 || next >= currentField.length) {
            return false;
        }

        if ((step == 1) && ((blank + 1) % sideSize == 0)) {
            return false;
        }

        return (step != -1) || ((blank + 1) % sideSize != 1);
    }

    /**
     * https://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
     */
    private boolean isValidState() {
        int parity = 0;
        int row = 0;
        int blankRow = 0;

        for (int i = 0; i < currentField.length; i++) {
            if (i % sideSize == 0) {
                row++;
            }

            if (currentField[i] == 0) {
                blankRow = row;
                continue;
            }

            for (int j = i + 1; j < currentField.length; j++) {
                if (currentField[i] > currentField[j] && currentField[j] != 0) {
                    parity++;
                }
            }
        }

        return sideSize % 2 == 0
                ? blankRow % 2 == 0 ? parity % 2 == 0 : parity % 2 != 0
                : parity % 2 == 0;
    }
}
