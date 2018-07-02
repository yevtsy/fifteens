package com.yevtsy.fifteens;

import com.yevtsy.fifteens.model.Board;
import com.yevtsy.fifteens.model.NBoard;
import com.yevtsy.fifteens.search.AStarEngine;
import javaslang.Tuple2;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CorrectFifteenPuzzleTest extends FifteensPuzzleTest {

    @DataProvider(name = "correctBoards")
    Object[][] unresolvableBoards() {
        Board terminatedThreeSide = new NBoard(3, initializeField(3));
        Board terminatedFourSide = new NBoard(4, initializeField(4));
        Board terminatedFiveSide = new NBoard(5, initializeField(5));


        return new Object[][]{
                {"boards/valid/puzzle01.txt", terminatedThreeSide},
                {"boards/valid/puzzle02.txt", terminatedThreeSide},
                {"boards/valid/puzzle06.txt", terminatedThreeSide},
                {"boards/valid/puzzle07.txt", terminatedThreeSide},
                {"boards/valid/puzzle08.txt", terminatedThreeSide},

                {"boards/valid/puzzle03.txt", terminatedFourSide},
                {"boards/valid/puzzle09.txt", terminatedFourSide},
                {"boards/valid/puzzle10.txt", terminatedFourSide},
                {"boards/valid/puzzle11.txt", terminatedFourSide},
                {"boards/valid/puzzle12.txt", terminatedFourSide},
                {"boards/valid/puzzle14.txt", terminatedFourSide},
                {"boards/valid/puzzle16.txt", terminatedFourSide},
                {"boards/valid/puzzle05.txt", terminatedFourSide},

                {"boards/valid/puzzle17.txt", terminatedFiveSide},
        };
    }

    @Test(dataProvider = "correctBoards")
    public void unresolvableBoardTest(String path, Board terminated) throws IOException {
        final Tuple2<Byte, byte[]> board = parseBoard(path);
        List<Board> transformations = new ArrayList<>(new AStarEngine(new NBoard(board._1, board._2)).run());
        Assert.assertEquals(transformations.get(transformations.size() - 1), terminated);
    }
}
