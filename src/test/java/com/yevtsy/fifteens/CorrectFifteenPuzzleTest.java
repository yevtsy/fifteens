package com.yevtsy.fifteens;

import com.yevtsy.fifteens.model.Board;
import com.yevtsy.fifteens.model.NBoard;
import com.yevtsy.fifteens.search.AStarEngine;
import com.yevtsy.fifteens.search.SearchEngine;
import javaslang.Tuple2;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CorrectFifteenPuzzleTest extends FifteensPuzzleTest {

    private SearchEngine search = new AStarEngine();

    @DataProvider(name = "correctBoards")
    Object[][] unresolvableBoards() {
        Board terminatedThreeSide = new NBoard(3, initializeField(3));
        Board terminatedFourSide = new NBoard(4, initializeField(4));
        Board terminatedFiveSide = new NBoard(5, initializeField(5));


        return new Object[][]{
                {"boards/valid/puzzle01.txt", terminatedThreeSide},
                {"boards/valid/puzzle02.txt", terminatedThreeSide},
                {"boards/valid/puzzle34.txt", terminatedThreeSide},
                {"boards/valid/puzzle35.txt", terminatedThreeSide},
                {"boards/valid/puzzle36.txt", terminatedThreeSide},

                {"boards/valid/puzzle03.txt", terminatedFourSide},
                {"boards/valid/puzzle42.txt", terminatedFourSide},
                {"boards/valid/puzzle43.txt", terminatedFourSide},
                {"boards/valid/puzzle44.txt", terminatedFourSide},
                {"boards/valid/puzzle45.txt", terminatedFourSide},
//                {"boards/valid/puzzle46.txt", terminatedFourSide},
                {"boards/valid/puzzle47.txt", terminatedFourSide},
//                {"boards/valid/puzzle48.txt", terminatedFourSide},
                {"boards/valid/puzzle49.txt", terminatedFourSide},
//                {"boards/valid/puzzle05.txt", terminatedFourSide},
                {"boards/valid/puzzle06.txt", terminatedFourSide},

                {"boards/valid/puzzle51.txt", terminatedFiveSide},
        };
    }

    @Test(dataProvider = "correctBoards")
    public void unresolvableBoardTest(String path, Board terminated) throws IOException {
        final Tuple2<Byte, byte[]> board = parseBoard(path);
        List<Board> transformations = new ArrayList<>(search.search(new NBoard(board._1, board._2)));
        Assert.assertEquals(transformations.get(transformations.size() - 1), terminated);
    }
}
