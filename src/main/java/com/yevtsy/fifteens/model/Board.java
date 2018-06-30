package com.yevtsy.fifteens.model;

import java.util.Collection;

public interface Board {

    Board parent();

    void parent(Board parent);

    Iterable<Board> neighbors();

    boolean isValid();

    boolean isTerminated();

    Collection<Board> moves();

    int passedCost();

    int heuristic();
}
