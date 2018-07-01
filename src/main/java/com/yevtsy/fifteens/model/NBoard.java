package com.yevtsy.fifteens.model;

import lombok.EqualsAndHashCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NBoard implements Board {

    private byte[] steps;
    private Board parent;
    @EqualsAndHashCode.Include
    private byte[] currentField;
    private byte[] initialField;
    private int sideSize;
    private int h;
    private boolean isSolvable;

    public NBoard(int sideSize, byte[] field) {
        this.sideSize = sideSize;
        this.steps = new byte[]{(byte) -sideSize, (byte) sideSize, -1, 1};
        this.currentField = field;
        this.initialField = init();
        this.h = calculateHeuristic();
        this.isSolvable = isFilledCorrectly() && isSolvable();
    }

    @Override
    public Board parent() {
        return parent;
    }

    @Override
    public int heuristic() {
        return h;
    }

    @Override
    public void parent(Board parent) {
        this.parent = parent;
    }

    @Override
    public boolean isTerminated() {
        return Arrays.equals(currentField, initialField);
    }

    @Override
    public boolean isValid() {
        return isSolvable;
    }

    @Override
    public Collection<Board> moves() {
        Deque<Board> path = new ArrayDeque<>();
        Board current = this;
        while (current != null) {
            path.push(current);
            current = current.parent();
        }

        return path;
    }

    @Override
    public Collection<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        for (byte step : steps) {
            int blank = 0;
            for (; blank < currentField.length; ++blank) {
                if (currentField[blank] == 0) {
                    break;
                }
            }

            int next = blank + step;
            if (canMove(step, blank, next)) {
                byte[] neighborField = Arrays.copyOf(currentField, currentField.length);
                byte temp = neighborField[blank];
                neighborField[blank] = neighborField[next];
                neighborField[next] = temp;

                neighbors.add(new NBoard(sideSize, neighborField));
            }
        }
        return neighbors;
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

    private boolean canMove(byte step, int blank, int next) {
        if (next < 0 || next >= currentField.length) {
            return false;
        }

        if ((step == 1) && ((blank + 1) % sideSize == 0)) {
            return false;
        }

        return (step != -1) || ((blank + 1) % sideSize != 1);
    }

    private byte[] init() {
        int size = sideSize * sideSize;
        byte[] original = new byte[size];

        for (int i = 0; i < size; ++i) {
            original[i] = (byte) (i + 1);
        }

        original[size - 1] = 0;
        return original;
    }

    private int calculateHeuristic() {
        int heuristic = 0;
        for (int i = 0; i < sideSize; i++)
            for (int j = 0; j < sideSize; j++) {
                if (currentField[i * sideSize + j] == 0)
                    continue;
                int x = (currentField[i * sideSize + j] - 1) / sideSize;
                int y = (currentField[i * sideSize + j] - 1) % sideSize;
                heuristic += (Math.abs(x - i) + Math.abs(y - j));
            }

        return heuristic;
    }

    private boolean isSolvable() {
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

    private boolean isFilledCorrectly() {
        int size = sideSize * sideSize;
        byte[] counts = new byte[size];

        for (byte value : currentField) {
            if (value < 0 || value > size - 1 || counts[value] > 1) {
                return false;
            }
            counts[value] += 1;
        }

        return true;
    }
}
