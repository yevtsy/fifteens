package com.yevtsy.fifteens;

import com.yevtsy.fifteens.model.Board;
import com.yevtsy.fifteens.model.NBoard;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import javaslang.Tuple;
import javaslang.Tuple2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public abstract class FifteensPuzzleTest {

    protected Tuple2<Byte, byte[]> parseBoard(String path) throws IOException {
        String file = getClass().getClassLoader().getResource(path).getFile();
        return parseBoard(Paths.get(file));
    }

    protected Tuple2<Byte, byte[]> parseBoard(Path path) throws IOException {
        String content = new String(Files.readAllBytes(path));
        final String[] numbers = content.split(" ");

        Byte size = Byte.parseByte(numbers[0]);
        byte[] field = new byte[numbers.length - 1];

        for (int i = 0; i < numbers.length - 1; ++i) {
            field[i] = Byte.parseByte(numbers[i + 1]);
        }

        return Tuple.of(size, field);
    }

    protected Board shuffleBoard(byte[] field, int sideSize, int count) {
        int moves = count;
        final NBoard initialBoard = new NBoard(sideSize, field);
        Board shuffled = initialBoard;

        IntSet markedBoards = new IntOpenHashSet();
        markedBoards.add(shuffled.hashCode());

        while (moves > 0) {
            final Optional<Board> board = shuffled.neighbors().stream().filter(b -> !markedBoards.contains(b.hashCode())).findAny();
            if (!board.isPresent()) {
                return shuffled;
            }

            markedBoards.add(board.hashCode());
            shuffled = board.get();
            moves--;
        }

        return shuffled;
    }

    protected byte[] initializeField(int sideSize) {
        int size = sideSize * sideSize;
        byte[] original = new byte[size];

        for (int i = 0; i < size; ++i) {
            original[i] = (byte) (i + 1);
        }

        original[size - 1] = 0;
        return original;
    }
}
