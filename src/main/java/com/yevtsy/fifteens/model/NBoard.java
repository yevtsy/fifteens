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

    private byte[] neighborIndexes;
    private Board parent;
    @EqualsAndHashCode.Include
    private byte[] currentField;
    private byte[] initialField;
    private int sideSize;

    // TODO : rethink if these fields are algorithm-specific
    private int g;
    private int h;

    public NBoard(int sideSize, byte[] field) {
        this.sideSize = sideSize;
        this.neighborIndexes = new byte[]{(byte) -sideSize, (byte) sideSize, -1, 1};
        this.currentField = field;
        this.initialField = init();
        this.g = 0;
        this.h = calculateHeuristic();
    }

    private NBoard(int sideSize, byte[] field, int passedCost) {
        this(sideSize, field);
        this.g = passedCost;
    }

    @Override
    public Board parent() {
        return parent;
    }

    @Override
    public int passedCost() {
        return g;
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
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        for (byte neighborIndex : neighborIndexes) {

            int blank = 0;
            for (; blank < currentField.length; blank++) {
                if (currentField[blank] == 0) {
                    break;
                }
            }

            int next = blank + neighborIndex;

            // TODO : validations should be separate AND remove continue statements
            if (next < 0 || next >= currentField.length) {
                continue;
            }
            // TODO : check WTF below
            if ((neighborIndex == 1) && ((blank + 1) % sideSize == 0)) {
                continue;
            }
            if ((neighborIndex == -1) && ((blank + 1) % sideSize == 1)) {
                continue;
            }

            byte[] neighborField = Arrays.copyOf(currentField, currentField.length);
            byte temp = neighborField[blank];
            neighborField[blank] = neighborField[next];
            neighborField[next] = temp;

            neighbors.add(new NBoard(sideSize, neighborField, g + 1));
        }
        return neighbors;
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
    public String toString() {
        StringBuilder sbf = new StringBuilder(currentField.length);
        for (int i = 0; i < sideSize; i++) {
            for (int j = 0; j < sideSize; j++) {
                sbf.append(currentField[j + i * sideSize]);
                sbf.append("\t");
            }
            sbf.append("\n");
        }
        return sbf.toString();
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
        int h = 0;
        for (int i = 0; i < sideSize * sideSize; i++) {
            if (currentField[i] != initialField[i]) {
                h++;
            }
        }

        return h;
    }

    private boolean isSolvable() {
        int invCount = 0;
        int blankPos = 0;

        for (int i = 0; i < currentField.length; i++) {
            if (currentField[i] == 0) {
                blankPos = i / sideSize + 1;
            }

            if (i == 0)
                continue;

            for (int j = i + 1; j < currentField.length; j++) {
                if (currentField[j] < currentField[i]) {
                    invCount++;
                }
            }
        }

        invCount = invCount + blankPos;
        return (invCount & 1) == 0;
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
