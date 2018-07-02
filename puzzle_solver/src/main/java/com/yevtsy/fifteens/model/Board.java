package com.yevtsy.fifteens.model;

import java.util.Collection;

public interface Board {

    /**
     * Returns internal representation of board
     */
    byte[] state();

    /**
     * Set parent board to represent parent-child relationship
     */
    void parent(Board parent);

    /**
     * Returns boards that represents transformation path
     */
    Collection<Board> moves();

    /**
     * Return neighbor-boards
     */
    Collection<Board> neighbors();

    /**
     * Return true if board is solvable and filled correctly.
     * Otherwise return false
     */
    boolean isValid();

    /**
     * Check if current board is terminated
     */
    boolean isTerminated();
}
