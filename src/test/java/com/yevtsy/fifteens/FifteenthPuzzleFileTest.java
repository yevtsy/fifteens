package com.yevtsy.fifteens;

import com.yevtsy.fifteens.model.Board;
import com.yevtsy.fifteens.model.NBoard;
import com.yevtsy.fifteens.search.AStarEngine;
import com.yevtsy.fifteens.search.SearchEngine;
import javaslang.Tuple2;
import org.junit.Test;

import java.io.IOException;
import java.util.Collection;

public class FifteenthPuzzleFileTest extends FifteensPuzzleTest {

    @Test
    public void test1() throws IOException {
        final Tuple2<Byte, byte[]> board = parseBoard("puzzle01.txt");
        SearchEngine search = new AStarEngine();
        final Collection<Board> moves = search.search(new NBoard(board._1, board._2));
        moves.forEach(System.out::println);
    }
}
