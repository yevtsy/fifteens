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
    @EqualsAndHashCode.Include
    private int h;

    public NBoard(int sideSize, byte[] field) {
        this.sideSize = sideSize;
        this.steps = new byte[]{(byte) -sideSize, (byte) sideSize, -1, 1};
        this.currentField = field;
        this.initialField = init();
        this.h = calculateHeuristic();
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
        return isFilledCorrectly() && isSolvable();
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
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        for (byte step : steps) {
            int blank = 0;
            for (; blank < currentField.length; blank++) {
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

    private boolean canMove(byte neighborIndex, int blank, int next) {
        if (next < 0 || next >= currentField.length) {
            return false;
        }
        // TODO : check WTF below
        if ((neighborIndex == 1) && ((blank + 1) % sideSize == 0)) {
            return false;
        }

        return (neighborIndex != -1) || ((blank + 1) % sideSize != 1);
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

//    private boolean isSolvable() {
//        int invCount = 0;
//        int blankPos = 0;
//
//        for (int i = 0; i < currentField.length; i++) {
//            if (currentField[i] == 0) {
//                blankPos = i / sideSize + 1;
//            }
//
//            if (i == 0)
//                continue;
//
//            for (int j = i + 1; j < currentField.length; j++) {
//                if (currentField[j] < currentField[i]) {
//                    invCount++;
//                }
//            }
//        }
//
//        invCount = invCount + blankPos;
//        return (invCount & 1) == 0;
//    }

    private boolean isSolvable() {
        int parity = 0;
        int gridWidth = (int) Math.sqrt(currentField.length);
        int row = 0; // the current row we are on
        int blankRow = 0; // the row with the blank tile

        for (int i = 0; i < currentField.length; i++) {
            if (i % gridWidth == 0) { // advance to next row
                row++;
            }
            if (currentField[i] == 0) { // the blank tile
                blankRow = row; // save the row on which encountered
                continue;
            }
            for (int j = i + 1; j < currentField.length; j++) {
                if (currentField[i] > currentField[j] && currentField[j] != 0) {
                    parity++;
                }
            }
        }

        if (gridWidth % 2 == 0) { // even grid
            if (blankRow % 2 == 0) { // blank on odd row; counting from bottom
                return parity % 2 == 0;
            } else { // blank on even row; counting from bottom
                return parity % 2 != 0;
            }
        } else { // odd grid
            return parity % 2 == 0;
        }
    }

    private boolean isFilledCorrectly() {
        int size = sideSize * sideSize;
        byte[] counts = new byte[size];
        for (byte value : currentField) {
            if (value < 0 || value > size - 1) {
                return false;
            }
            counts[value] += 1;
        }

        for (byte count : counts) {
            if (count != 1) {
                return false;
            }
        }

        return true;
    }
}
