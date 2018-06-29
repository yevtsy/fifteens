package com.yevtsy.fifteens.search;

import com.yevtsy.fifteens.model.Board;

import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class AStarEngine implements SearchEngine {

    @Override
    public Collection<Board> search(Board board) {
        Set<Integer> closed = new HashSet<>();
        Queue<Board> open = new PriorityQueue<>(); // TODO : put here comparator or define it for Board object ?

        open.add(board);
        board.updatePassedCost(0);

        while (!open.isEmpty()) {
            Board current = open.poll();
            if (current.isTerminated()) {
                return current.moves();
            }

            closed.add(current.hashCode()); // TODO : imeplement hash code properly

            final Iterable<Board> neighbors = current.neighbors();
            for (Board neighbor : neighbors) {
                if (closed.contains(neighbor.hashCode())) {
                    continue;
                }

                int g = current.passedCost() + 1;
                if (!open.contains(neighbor)) {
                    open.add(neighbor);
                    neighbor.parent(current);
                    neighbor.updatePassedCost(g);
                }

                if (g < neighbor.passedCost()) {
                    neighbor.parent(current);
                    neighbor.updatePassedCost(g);
                }
            }
        }
    }
}
