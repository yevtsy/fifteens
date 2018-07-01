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
        final int threeSide = 3;
        final byte[] fieldThree = initializeField(threeSide);
        Board terminatedThreeSide = new NBoard(threeSide, fieldThree);

        final int fourSide = 4;
        final byte[] fieldFour = initializeField(fourSide);
        Board terminatedFourSide = new NBoard(fourSide, fieldFour);

        return new Object[][] {
                {shuffleBoard(fieldThree, threeSide, 3), terminatedThreeSide},
                {shuffleBoard(fieldThree, threeSide, 5), terminatedThreeSide},
                {shuffleBoard(fieldThree, threeSide, 10), terminatedThreeSide},
                {shuffleBoard(fieldThree, threeSide, 20), terminatedThreeSide},
                {shuffleBoard(fieldThree, threeSide, 30), terminatedThreeSide},
                {shuffleBoard(fieldThree, threeSide, 40), terminatedThreeSide},
                {shuffleBoard(fieldThree, threeSide, 50), terminatedThreeSide},
                {shuffleBoard(fieldThree, threeSide, 60), terminatedThreeSide},

                {shuffleBoard(fieldFour, fourSide, 3), terminatedFourSide},
                {shuffleBoard(fieldFour, fourSide, 5), terminatedFourSide},
                {shuffleBoard(fieldFour, fourSide, 10), terminatedFourSide},
                {shuffleBoard(fieldFour, fourSide, 20), terminatedFourSide},
                {shuffleBoard(fieldFour, fourSide, 30), terminatedFourSide},
                {shuffleBoard(fieldFour, fourSide, 40), terminatedFourSide},
                {shuffleBoard(fieldFour, fourSide, 50), terminatedFourSide},
                {shuffleBoard(fieldFour, fourSide, 60), terminatedFourSide}
        };
    }

    @Test(dataProvider = "boards")
    public void shuffledBoardTest(Board current, Board terminated) {
        List<Board> transformations = new ArrayList<>(engine.search(current));
        Assert.assertEquals(transformations.get(transformations.size() - 1), terminated);
    }
}
