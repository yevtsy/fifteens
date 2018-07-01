package com.yevtsy.fifteens;

import com.yevtsy.fifteens.model.NBoard;
import com.yevtsy.fifteens.search.AStarEngine;
import com.yevtsy.fifteens.search.SearchEngine;
import javaslang.Tuple2;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class UnsolvableFifteenthPuzzleTest extends FifteensPuzzleTest {

    private SearchEngine search = new AStarEngine();

    @DataProvider(name = "unresolvableBoards")
    Object[][] unresolvableBoards() {
        return new Object[][]{
                {"unresolvable/puzzle01.txt"},
                {"unresolvable/puzzle02.txt"},
                {"unresolvable/puzzle03.txt"},
                {"unresolvable/puzzle04.txt"}
        };
    }

    @Test(dataProvider = "unresolvableBoards", expectedExceptions = IllegalArgumentException.class)
    public void unresolvableBoardTest(String path) throws IOException {
        final Tuple2<Byte, byte[]> board = parseBoard(path);
        search.search(new NBoard(board._1, board._2));
    }
}
