package com.yevtsy.fifteens.model;

public class NBoard implements Board {

    private NBoard parent;
    private int g;
    private int h;

    @Override
    public Board parent() {
        return parent;
    }

    @Override
    public Iterable<Board> neighbors() {
        return null;
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
    public boolean isValid() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }
}
