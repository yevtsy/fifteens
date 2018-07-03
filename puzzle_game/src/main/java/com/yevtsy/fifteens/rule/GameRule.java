package com.yevtsy.fifteens.rule;

import com.yevtsy.fifteens.board.Board;

public interface GameRule {

    /**
     *
     * Check if current state is solvable
     *
     * https://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
     */
    boolean isValidState(byte[] state);

    /**
     * Return true if board is in terminated state, false otherwise
     */
    boolean isGameOver(Board actual, Board expected);

    /**
     * Return terminated state of board with size {@param size}
     */
    byte[] terminatedState(int size);
}
