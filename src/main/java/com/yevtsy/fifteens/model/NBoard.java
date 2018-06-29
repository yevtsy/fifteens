package com.yevtsy.fifteens.model;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;

public class NBoard implements Board {

    private Board parent;
    private byte[] initialField;
    private byte[] currentField;
    private int sideSize;
    private int g;
    private int h;

    public NBoard(int sideSize) {
        this.sideSize = sideSize;
        this.initialField = init();
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
    public void updatePassedCost(int cost) {
        this.g = cost;
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
        return null;
    }

    @Override
    public boolean isValid() {
        return false;
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

    private byte[] init() { // TODO : rethink this code
        int size = sideSize * sideSize;
        byte[] original = new byte[size];
        byte k = 0;

        for (int i = 0; i < sideSize; i++) {
            for (int j = 0; j < sideSize; j++) {
                original[j + i * sideSize] = ++k;
            }
        }

        original[size - 1] = 0;

        return original;
    }
}
