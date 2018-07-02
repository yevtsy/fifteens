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
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FifteensGame {

    private static final List<String> VALID_MOVES = Arrays.stream(Move.values()).map(Move::toString).collect(toList());

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

            while (!game.isGameOver(board)) {
                out.println("Enter the next move: ");
                String move = br.readLine();
                Move nextMove = Move.parse(move);
                while (nextMove == null) {
                    out.println(move + " is not a valid movement. Please, choose valid one: " + VALID_MOVES);
                    move = br.readLine();
                    nextMove = Move.parse(move);
                }

                final Board current = board;
                final Move next = nextMove;
                board = Try.of(() -> game.move(current, next))
                        .onFailure(e -> out.println(next + " is not correct movement for this board. Please, try again"))
                        .getOrElse(current);

                out.println("\nCurrent state:");
                out.println(board);
            }

            out.println("\nCongratulations! You solved the puzzle");
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
