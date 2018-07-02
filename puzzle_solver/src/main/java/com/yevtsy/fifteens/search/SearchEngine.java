package com.yevtsy.fifteens.search;

import com.yevtsy.fifteens.model.Board;

import java.util.Collection;

public interface SearchEngine {

    /**
     * Start an execution to solve the task
     */
    Collection<Board> run();

    /**
     * Return admissible heuristic of this board.
     * For example, Hamming or Manhattan distances
     */
    int heuristic(Board board);
}
