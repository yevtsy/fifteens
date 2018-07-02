package com.yevtsy.fifteens.board;

import com.yevtsy.fifteens.exception.IllegalMoveException;
import com.yevtsy.fifteens.model.Move;

public interface Board {

    Board move(Move move) throws IllegalMoveException;

    boolean isSolvable();

    boolean isTerminated();
}
