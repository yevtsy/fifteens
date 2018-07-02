package com.yevtsy.fifteens.game;

import com.yevtsy.fifteens.board.Board;
import com.yevtsy.fifteens.exception.IllegalMoveException;
import com.yevtsy.fifteens.model.Move;

public interface PuzzleGame {

    Board initialize(int size, int shuffle);

    Board move(Board board, Move move) throws IllegalMoveException;

    boolean isGameOver(Board board);
}
