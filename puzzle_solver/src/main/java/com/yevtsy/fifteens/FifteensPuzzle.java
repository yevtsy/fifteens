package com.yevtsy.fifteens;

import com.yevtsy.fifteens.model.Board;
import com.yevtsy.fifteens.model.NBoard;
import com.yevtsy.fifteens.search.AStarEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Collection;

public class FifteensPuzzle {

    public static void main(String[] args) throws IOException {
        System.out.println(readme());

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

            final Collection<Board> transformations = new AStarEngine(new NBoard(side, field)).run();
            transformations.forEach(System.out::println);
        }
    }

    private static String readme() {
        return '\n' +
                "Fifteens puzzle\n" +
                "-----------------------------\n" +
                '\n' +
                "Syntax\n" +
                "-----------------------------\n" +
                "side size   ->    the size of board side\n" +
                "field       ->    representation of the board. Input in one line: 1 0 2 3 5 6 7 4 9 10 11 8 13 14 15 12\n" +
                "                  with space as a delimiter\n" +
                '\n' +
                "Example of the initial state of the field\n" +
                "-----------------------------\n" +
                "1\t0\t2\t3\n" +
                "5\t6\t7\t4\n" +
                "9\t10\t11\t8\n" +
                "13\t14\t15\t12";
    }
}
