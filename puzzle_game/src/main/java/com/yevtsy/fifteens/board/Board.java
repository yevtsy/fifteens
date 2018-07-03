package com.yevtsy.fifteens.board;

import com.yevtsy.fifteens.exception.IllegalMoveException;
import com.yevtsy.fifteens.model.Move;

public interface Board {

    /**
     * Return internal state of this board
     */
    byte[] state();

    /**
     * Apply movement and return new board
     *
     * @return board after moving
     * @throws IllegalMoveException if move is not valid
     */
    Board move(Move move) throws IllegalMoveException;
}
