package com.yevtsy.fifteens.board;

import com.yevtsy.fifteens.PuzzleTest;
import com.yevtsy.fifteens.exception.IllegalMoveException;
import com.yevtsy.fifteens.model.Move;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static java.util.Arrays.asList;

public class FifteensBoardTest extends PuzzleTest {

    @Test
    public void testCorrectInternalState() {
        final FifteensBoard board = new FifteensBoard(fifteensTerminatedState);
        Assert.assertEquals(board.state(), fifteensTerminatedState);
    }

    @DataProvider(name = "invalidMovements")
    Object[][] invalidMovements() {
        return new Object[][]{
                {asList(Move.RIGHT)},
                {asList(Move.DOWN)},
                {asList(Move.LEFT, Move.LEFT, Move.LEFT, Move.LEFT)},
                {asList(Move.UP, Move.UP, Move.UP, Move.UP)}
        };
    }

    @DataProvider(name = "movements")
    Object[][] movements() {
        return new Object[][]{
                {asList(Move.LEFT, Move.UP, Move.LEFT)},
                {asList(Move.UP, Move.LEFT, Move.DOWN)},
                {asList(Move.LEFT, Move.LEFT, Move.UP, Move.RIGHT)},
                {asList(Move.UP, Move.UP, Move.LEFT, Move.DOWN)}
        };
    }

    @Test(dataProvider = "invalidMovements", expectedExceptions = IllegalMoveException.class)
    public void testInvalidMovements(List<Move> movements) {
        Board board = new FifteensBoard(fifteensTerminatedState);
        for (Move move : movements) {
            board = board.move(move);
        }
    }

    @Test(dataProvider = "movements")
    public void testValidMovements(List<Move> movements) {
        Board board = new FifteensBoard(fifteensTerminatedState);
        Board tmp = board;
        for (Move move : movements) {
            tmp = tmp.move(move);
        }

        Assert.assertNotEquals(tmp, board);
    }
}
