package com.yevtsy.fifteens;

import javaslang.Tuple;
import javaslang.Tuple2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;

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

    protected byte[] shuffle(int sideSize, int shuffleCount) {
        int moves = shuffleCount;
        byte[] startState = init(sideSize);

        int[] neighbors = {-sideSize, sideSize, -1, 1};
        Random r = new Random();
        while (moves > 0) {
            int j = r.nextInt(neighbors.length);
            byte[] state = moveNext(startState, neighbors[j], sideSize);
            if (state != null) {
                startState = state;
                moves--;
            }
        }
        return startState;
    }

    private byte[] moveNext(byte[] startState, int neighborIndex, int sideSize) {
        int blank = 0;
        for (; blank < startState.length; blank++) {
            if (startState[blank] == 0) {
                break;
            }
        }

        int next = blank + neighborIndex;
        if (canMove(startState, (byte) neighborIndex, blank, next, sideSize)) {
            byte[] neighborField = Arrays.copyOf(startState, startState.length);
            byte temp = neighborField[blank];
            neighborField[blank] = neighborField[next];
            neighborField[next] = temp;

            return neighborField;
        }

        return null;
    }

    private boolean canMove(byte[] startState, byte neighborIndex, int blank, int next, int sideSize) {
        if (next < 0 || next >= startState.length) {
            return false;
        }
        // TODO : check WTF below
        if ((neighborIndex == 1) && ((blank + 1) % sideSize == 0)) {
            return false;
        }

        return (neighborIndex != -1) || ((blank + 1) % sideSize != 1);
    }

    protected byte[] init(int sideSize) {
        int size = sideSize * sideSize;
        byte[] original = new byte[size];

        for (int i = 0; i < size; ++i) {
            original[i] = (byte) (i + 1);
        }

        original[size - 1] = 0;
        return original;
    }
}
