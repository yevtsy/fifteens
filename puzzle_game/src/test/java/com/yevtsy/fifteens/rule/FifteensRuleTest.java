package com.yevtsy.fifteens.rule;

import com.yevtsy.fifteens.PuzzleTest;
import com.yevtsy.fifteens.board.Board;
import com.yevtsy.fifteens.board.FifteensBoard;
import com.yevtsy.fifteens.model.Move;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FifteensRuleTest extends PuzzleTest {

    private static final GameRule rule = new FifteensGameRule();

    @Test
    public void testTerminatedStateGeneration() {
        Assert.assertEquals(rule.terminatedState(16), fifteensTerminatedState);
    }

    @Test
    public void testGameOver() {
        Board actual = new FifteensBoard(fifteensTerminatedState);
        Board expected = new FifteensBoard(fifteensTerminatedState);

        Assert.assertTrue(rule.isGameOver(actual, expected));
    }

    @Test
    public void testGameIsNotOver() {
        Board actual = new FifteensBoard(fifteensTerminatedState);
        actual = actual.move(Move.LEFT);

        Board expected = new FifteensBoard(fifteensTerminatedState);

        Assert.assertFalse(rule.isGameOver(actual, expected));
    }

    @DataProvider(name = "invalidStates")
    Object[][] invalidMovements() {
        return new Object[][]{
                {new byte[]{1, 0, 2, 3}},
                {new byte[]{1, 2, 3, 4, 6, 5, 7, 8, 0}},
                {new byte[]{8, 6, 7, 2, 5, 4, 1, 3, 0}},
                {new byte[]{3, 2, 4, 8, 1, 6, 0, 12, 5, 10, 7, 11, 9, 13, 14, 15}}
        };
    }

    @DataProvider(name = "states")
    Object[][] movements() {
        return new Object[][]{
                {new byte[]{0, 1, 3, 4, 2, 5, 7, 8, 6}},
                {new byte[]{4, 1, 3, 0, 2, 6, 7, 5, 8}},
                {new byte[]{2, 0, 3, 4, 1, 10, 6, 8, 5, 9, 7, 12, 13, 14, 11, 15}},
                {new byte[]{2, 6, 7, 3, 5, 13, 4, 11, 10, 0, 1, 15, 9, 14, 12, 8}},
                {new byte[]{13, 1, 5, 4, 2, 3, 6, 8, 7, 10, 0, 9, 11, 14, 15, 12}}
        };
    }

    @Test(dataProvider = "invalidStates")
    public void testInvalidStates(byte[] state) {
        Assert.assertFalse(rule.isValidState(state));
    }

    @Test(dataProvider = "states")
    public void testValidStates(byte[] state) {
        Assert.assertTrue(rule.isValidState(state));
    }
}
