package com.yevtsy.fifteens.game;

import com.yevtsy.fifteens.PuzzleTest;
import com.yevtsy.fifteens.board.Board;
import com.yevtsy.fifteens.board.FifteensBoard;
import com.yevtsy.fifteens.model.Move;
import com.yevtsy.fifteens.rule.FifteensGameRule;
import com.yevtsy.fifteens.rule.GameRule;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FifteensGameTest extends PuzzleTest {

    private static final GameRule rule = new FifteensGameRule();

    @Test
    public void testShuffledBoardIsCorrect() {
        Board terminated = new FifteensBoard(fifteensTerminatedState);
        PuzzleGame game = new FifteensPuzzleGame(rule, 4);
        final Board shuffled = game.shuffle(5);

        Assert.assertNotEquals(shuffled, terminated);
        Assert.assertTrue(rule.isValidState(shuffled.state()));
    }

    @Test
    public void testMovement() {
        Board terminated = new FifteensBoard(fifteensTerminatedState);
        PuzzleGame game = new FifteensPuzzleGame(rule, 4);

        Assert.assertNotEquals(game.move(terminated, Move.LEFT), terminated);
    }

    @Test
    public void testGameOver() {
        Board terminated = new FifteensBoard(fifteensTerminatedState);
        PuzzleGame game = new FifteensPuzzleGame(rule, 4);
        Board moved = game.move(terminated, Move.LEFT);
        moved = moved.move(Move.RIGHT);

        Assert.assertTrue(rule.isGameOver(moved, terminated));
    }

    @Test
    public void testGameIsNotOver() {
        Board terminated = new FifteensBoard(fifteensTerminatedState);
        PuzzleGame game = new FifteensPuzzleGame(rule, 4);
        Board moved = game.move(terminated, Move.LEFT);

        Assert.assertFalse(rule.isGameOver(moved, terminated));
    }
}
