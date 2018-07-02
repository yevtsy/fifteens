package com.yevtsy.fifteens.board;

import com.yevtsy.fifteens.exception.IllegalMoveException;
import com.yevtsy.fifteens.model.Move;

public class FifteensBoard implements Board {

    @Override
    public Board move(Move move) throws IllegalMoveException {
        return null;
    }

    @Override
    public boolean isSolvable() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }
}
