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
        Board terminatedThreeSide = new NBoard(3, init(3));
        Board terminatedFourSide = new NBoard(4, init(4));
        Board terminatedFiveSide = new NBoard(5, init(5));


        return new Object[][]{
                {"correct/puzzle01.txt", terminatedThreeSide},
                {"correct/puzzle02.txt", terminatedThreeSide},
                {"correct/puzzle34.txt", terminatedThreeSide},
                {"correct/puzzle35.txt", terminatedThreeSide},
                {"correct/puzzle36.txt", terminatedThreeSide},

                {"correct/puzzle03.txt", terminatedFourSide},
                {"correct/puzzle42.txt", terminatedFourSide},
                {"correct/puzzle43.txt", terminatedFourSide},
                {"correct/puzzle44.txt", terminatedFourSide},
                {"correct/puzzle45.txt", terminatedFourSide},
                {"correct/puzzle46.txt", terminatedFourSide},
                {"correct/puzzle47.txt", terminatedFourSide},
                {"correct/puzzle48.txt", terminatedFourSide},
                {"correct/puzzle49.txt", terminatedFourSide},
                {"unresolvable/puzzle05.txt", terminatedFourSide},
                {"unresolvable/puzzle06.txt", terminatedFourSide},

                {"correct/puzzle51.txt", terminatedFiveSide},
        };
    }

    @Test(dataProvider = "correctBoards")
    public void unresolvableBoardTest(String path, Board terminated) throws IOException {
        final Tuple2<Byte, byte[]> board = parseBoard(path);
        List<Board> transformations = new ArrayList<>(search.search(new NBoard(board._1, board._2)));
        Assert.assertEquals(transformations.get(transformations.size() - 1), terminated);
    }
}
