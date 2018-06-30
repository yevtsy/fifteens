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

    @Test
    public void test2() throws IOException {
        final Tuple2<Byte, byte[]> board = parseBoard("puzzle02.txt");
        SearchEngine search = new AStarEngine();
        final Collection<Board> moves = search.search(new NBoard(board._1, board._2));
        moves.forEach(System.out::println);
    }

    @Test
    public void test3() throws IOException {
        final Tuple2<Byte, byte[]> board = parseBoard("puzzle03.txt");
        SearchEngine search = new AStarEngine();
        final Collection<Board> moves = search.search(new NBoard(board._1, board._2));
        moves.forEach(System.out::println);
    }

    @Test
    public void test4() throws IOException {
        final Tuple2<Byte, byte[]> board = parseBoard("puzzle04.txt");
        SearchEngine search = new AStarEngine();
        final Collection<Board> moves = search.search(new NBoard(board._1, board._2));
        moves.forEach(System.out::println);
    }

    @Test
    public void test5() throws IOException {
        final Tuple2<Byte, byte[]> board = parseBoard("puzzle05.txt");
        SearchEngine search = new AStarEngine();
        final Collection<Board> moves = search.search(new NBoard(board._1, board._2));
        moves.forEach(System.out::println);
    }

    @Test
    public void test6() throws IOException {
        final Tuple2<Byte, byte[]> board = parseBoard("puzzle06.txt");
        SearchEngine search = new AStarEngine();
        final Collection<Board> moves = search.search(new NBoard(board._1, board._2));
        moves.forEach(System.out::println);
    }
}
