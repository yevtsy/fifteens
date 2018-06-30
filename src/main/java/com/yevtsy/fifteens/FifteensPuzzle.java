package com.yevtsy.fifteens;

import com.yevtsy.fifteens.model.Board;
import com.yevtsy.fifteens.model.NBoard;
import com.yevtsy.fifteens.search.AStarEngine;
import com.yevtsy.fifteens.search.SearchEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Stream;

public class FifteensPuzzle {

    public static void main(String[] args) throws IOException {
        StringBuilder contentBuilder = new StringBuilder("\n");
        try (Stream<String> stream = Files.lines(Paths.get(FifteensPuzzle.class.getClassLoader()
                .getResource("readme").getFile()), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        System.out.println(contentBuilder);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintStream out = new PrintStream(System.out, true)) {
            out.println("Enter the side size of board: ");
            int side = Integer.parseInt(br.readLine());
            out.println("Enter the fields: ");
            String fields = br.readLine();

            final String[] values = fields.split(" ");
            final int total = side * side;
            if (total != values.length) {
                throw new IllegalArgumentException("Input board is not valid");
            }

            byte[] field = new byte[total];
            for (int i = 0; i < values.length; ++i) {
                field[i] = Byte.parseByte(values[i]);
            }

            SearchEngine engine = new AStarEngine();
            final Collection<Board> transformations = engine.search(new NBoard(side, field));
            transformations.forEach(System.out::println);
        }
    }
}
