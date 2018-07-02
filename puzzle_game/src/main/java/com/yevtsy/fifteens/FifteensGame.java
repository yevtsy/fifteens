package com.yevtsy.fifteens;

import com.yevtsy.fifteens.board.Board;
import com.yevtsy.fifteens.game.FifteensPuzzleGame;
import com.yevtsy.fifteens.game.PuzzleGame;
import com.yevtsy.fifteens.model.Move;
import javaslang.control.Try;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class FifteensGame {

    public static void main(String[] args) throws IOException {
        System.out.println(readme());

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintStream out = new PrintStream(System.out, true)) {

            out.println("Enter the side size of board: ");
            int side = Integer.parseInt(br.readLine());

            out.println("Enter the number of shuffles: ");
            int shuffles = Integer.parseInt(br.readLine());

            PuzzleGame game = new FifteensPuzzleGame();
            Board board = game.initialize(side, shuffles);

            out.println("Initial board:");
            out.println(board);

            while (!board.isTerminated()) {
                out.println("Enter the next move: ");
                final Move nextMove = Move.valueOf(br.readLine()); // TODO : add validation here for correctness

                final Board current = board;
                board = Try.of(() -> game.move(current, nextMove))
                        .onFailure(e -> out.println(nextMove + " is not correct movement for this board. Please, try again"))
                        .getOrElse(current);

                out.println("Current state:");
                out.println(board);
            }

            out.println("Congratulations! You solved the puzzle");
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
                "shuffle     ->    number of shuffles\n" +
                '\n' +
                "Available movements : left, right, up, down\n" +
                '\n' +
                "Example of the initial state of the field\n" +
                "-----------------------------\n" +
                "1\t0\t2\t3\n" +
                "5\t6\t7\t4\n" +
                "9\t10\t11\t8\n" +
                "13\t14\t15\t12\n";
    }
}
