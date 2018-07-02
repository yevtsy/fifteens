package com.yevtsy.fifteens.game;

import com.yevtsy.fifteens.board.Board;
import com.yevtsy.fifteens.exception.IllegalMoveException;
import com.yevtsy.fifteens.model.Move;

public class FifteensPuzzleGame implements PuzzleGame {

    @Override
    public Board initialize(int size, int shuffle) {
        return null;
    }

    @Override
    public Board move(Board board, Move move) throws IllegalMoveException {
        return null;
    }

    @Override
    public boolean isGameOver(Board board) {
        return false;
    }
}
