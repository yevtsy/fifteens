package com.yevtsy.fifteens;

import com.yevtsy.fifteens.model.Board;
import com.yevtsy.fifteens.model.NBoard;
import com.yevtsy.fifteens.search.AStarEngine;
import com.yevtsy.fifteens.search.SearchEngine;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class FifteenPuzzleRandomShuffledTest extends FifteensPuzzleTest {

    private SearchEngine engine = new AStarEngine();

    @DataProvider(name = "boards")
    Object[][] threeSideFieldProvider() {
        final int threeSideSize = 3;
        Board terminatedThreeSide = new NBoard(threeSideSize, init(threeSideSize));

        final int fourSideSize = 4;
        Board terminatedFourSide = new NBoard(fourSideSize, init(fourSideSize));

        return new Object[][] {
                {threeSideSize, shuffle(threeSideSize, 3), terminatedThreeSide},
                {threeSideSize, shuffle(threeSideSize, 5), terminatedThreeSide},
                {threeSideSize, shuffle(threeSideSize, 10), terminatedThreeSide},
                {threeSideSize, shuffle(threeSideSize, 15), terminatedThreeSide},
                {threeSideSize, shuffle(threeSideSize, 20), terminatedThreeSide},
                {threeSideSize, shuffle(threeSideSize, 25), terminatedThreeSide},
                {threeSideSize, shuffle(threeSideSize, 30), terminatedThreeSide},

                {fourSideSize, shuffle(fourSideSize, 3), terminatedFourSide},
                {fourSideSize, shuffle(fourSideSize, 5), terminatedFourSide},
                {fourSideSize, shuffle(fourSideSize, 10), terminatedFourSide},
                {fourSideSize, shuffle(fourSideSize, 15), terminatedFourSide},
                {fourSideSize, shuffle(fourSideSize, 20), terminatedFourSide},
                {fourSideSize, shuffle(fourSideSize, 25), terminatedFourSide},
                {fourSideSize, shuffle(fourSideSize, 30), terminatedFourSide}
        };
    }

    @Test(dataProvider = "boards")
    public void shuffledBoardTest(int sideSize, byte[] field, Board terminated) {
        List<Board> transformations = new ArrayList<>(engine.search(new NBoard(sideSize, field)));
        Assert.assertEquals(transformations.get(transformations.size() - 1), terminated);
    }
}
