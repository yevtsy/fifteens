package com.yevtsy.fifteens.search;

import com.yevtsy.fifteens.comparators.AStarBoardComparator;
import com.yevtsy.fifteens.model.Board;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class AStarEngine implements SearchEngine {

    @Override
    public Collection<Board> search(Board board) {
        if (!board.isValid()) {
            // TODO : throw an exception if board is not valid
        }

        Set<Integer> closed = new HashSet<>();
        Queue<Board> open = new PriorityQueue<>(new AStarBoardComparator());

        open.add(board);

        while (!open.isEmpty()) {
            Board current = open.poll();
            if (current.isTerminated()) {
                return current.moves();
            }

            closed.add(current.hashCode());

            final Iterable<Board> neighbors = current.neighbors();
            for (Board neighbor : neighbors) {
                if (closed.contains(neighbor.hashCode())) {
                    continue;
                }

//                int g = current.passedCost() + 1;
                if (!open.contains(neighbor)) {
                    open.add(neighbor);
                    neighbor.parent(current);
                }
//                else if (g < neighbor.passedCost()) {
//                    neighbor.parent(current);
//                }
            }
        }

        return Collections.emptyList();
    }
}
