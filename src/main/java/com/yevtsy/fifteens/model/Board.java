package com.yevtsy.fifteens.model;

import java.util.Collection;

public interface Board {

    Board parent();

    void parent(Board parent);

    Iterable<Board> neighbors();

    int passedCost();

    int heuristic();

    boolean isValid();

    boolean isTerminated();

    Collection<Board> moves();
}
