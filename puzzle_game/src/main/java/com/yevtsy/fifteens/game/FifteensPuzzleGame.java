package com.yevtsy.fifteens.game;

import com.yevtsy.fifteens.board.Board;
import com.yevtsy.fifteens.board.FifteensBoard;
import com.yevtsy.fifteens.exception.IllegalMoveException;
import com.yevtsy.fifteens.model.Move;
import com.yevtsy.fifteens.utils.PuzzleUtils;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

public class FifteensPuzzleGame implements PuzzleGame {
    private static final Random random = new SecureRandom();

    @Override
    public Board initialize(int sizeSide, int shuffle) {
        byte[] initial = PuzzleUtils.init(sizeSide * sizeSide);

        Board board;
        do {
            byte[] shuffled = shuffleBoard(initial, shuffle);
            board = new FifteensBoard(sizeSide, shuffled);
        } while (!board.isSolvable());

        return board;
    }

    @Override
    public Board move(Board board, Move move) throws IllegalMoveException {
        return board.move(move);
    }

    @Override
    public boolean isGameOver(Board board) {
        return board.isTerminated();
    }

    private byte[] shuffleBoard(byte[] initial, int shuffle) {
        byte[] shuffled = Arrays.copyOf(initial, initial.length);
        final int len = initial.length - 1;

        while (shuffle > 0) {
            int r = random.nextInt(len);
            byte tmp = shuffled[r];
            shuffled[r] = shuffled[len];
            shuffled[len] = tmp;

            shuffle--;
        }

        return shuffled;
    }
}
