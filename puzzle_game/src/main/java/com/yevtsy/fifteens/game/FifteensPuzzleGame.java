package com.yevtsy.fifteens.game;

import com.yevtsy.fifteens.board.Board;
import com.yevtsy.fifteens.board.FifteensBoard;
import com.yevtsy.fifteens.exception.IllegalMoveException;
import com.yevtsy.fifteens.model.Move;
import com.yevtsy.fifteens.rule.GameRule;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

public class FifteensPuzzleGame implements PuzzleGame {

    private static final Random random = new SecureRandom();
    private GameRule rules;
    private Board terminated;

    public FifteensPuzzleGame(GameRule rules, int size) {
        this.rules = rules;
        this.terminated = new FifteensBoard(rules.terminatedState(size));
    }

    @Override
    public Board shuffle(int shuffle) {
        byte[] initial = terminated.state();

        byte[] shuffled;
        do {
            shuffled = shuffleBoard(initial, shuffle);
        } while (!rules.isValidState(shuffled));

        return new FifteensBoard(shuffled);
    }

    @Override
    public Board move(Board board, Move move) throws IllegalMoveException {
        return board.move(move);
    }

    @Override
    public boolean isGameOver(Board board) {
        return rules.isGameOver(board, terminated);
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
