package com.yevtsy.fifteens;

import com.yevtsy.fifteens.model.NBoard;
import com.yevtsy.fifteens.search.AStarEngine;
import javaslang.Tuple2;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class UnsolvableFifteenthPuzzleTest extends FifteensPuzzleTest {

    @DataProvider(name = "unresolvableBoards")
    Object[][] unresolvableBoards() {
        return new Object[][]{
                {"boards/invalid/puzzle01.txt"},
                {"boards/invalid/puzzle02.txt"},
                {"boards/invalid/puzzle03.txt"},
                {"boards/invalid/puzzle04.txt"}
        };
    }

    @Test(dataProvider = "unresolvableBoards", expectedExceptions = IllegalArgumentException.class)
    public void unresolvableBoardTest(String path) throws IOException {
        final Tuple2<Byte, byte[]> board = parseBoard(path);
        new AStarEngine(new NBoard(board._1, board._2)).run();
    }
}
