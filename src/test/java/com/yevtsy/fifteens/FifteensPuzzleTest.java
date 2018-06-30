package com.yevtsy.fifteens;

import javaslang.Tuple;
import javaslang.Tuple2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FifteensPuzzleTest {

    protected Tuple2<Byte, byte[]> parseBoard(String path) throws IOException {
        String file = getClass().getClassLoader().getResource(path).getFile();
        String content = new String(Files.readAllBytes(Paths.get(file)));
        final String[] numbers = content.split(" ");

        Byte size = Byte.parseByte(numbers[0]);
        byte[] field = new byte[numbers.length - 1];

        for (int i = 0; i < numbers.length - 1; ++i) {
            field[i] = Byte.parseByte(numbers[i + 1]);
        }

        return Tuple.of(size, field);
    }
}
