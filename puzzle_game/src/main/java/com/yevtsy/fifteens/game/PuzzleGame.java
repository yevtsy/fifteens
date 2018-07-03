package com.yevtsy.fifteens.game;

import com.yevtsy.fifteens.board.Board;
import com.yevtsy.fifteens.exception.IllegalMoveException;
import com.yevtsy.fifteens.model.Move;

public interface PuzzleGame {

    /**
     * Return shuffled initial state of puzzle
     */
    Board shuffle(int shuffle);

    /**
     * Return new board after movement
     *
     * @param board
     * @param move
     * @return new board after movement
     * @throws IllegalMoveException if move is not valid
     */
    Board move(Board board, Move move) throws IllegalMoveException;

    /**
     * Return true if game is over, false otherwise
     */
    boolean isGameOver(Board board);
}
