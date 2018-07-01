package com.yevtsy.fifteens.search;

import com.yevtsy.fifteens.model.Board;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStarEngine implements SearchEngine {

    private static final int INF = 1_000;

    @Override
    public Collection<Board> search(Board board) {
        if (!board.isValid()) {
            throw new IllegalArgumentException("Board " + board + " has invalid data or cannot be solved");
        }

        Int2IntMap gScore = new Int2IntOpenHashMap();
        gScore.put(board.hashCode(), 0);

        IntSet closed = new IntOpenHashSet();
        Queue<Board> open = new PriorityQueue<>(Comparator.comparingInt(b -> gScore.getOrDefault(b.hashCode(), INF) + b.heuristic()));
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

                int tentativeScore = gScore.getOrDefault(current.hashCode(), INF) + 1;

                if (!open.contains(neighbor)) {
                    if (tentativeScore < gScore.getOrDefault(neighbor.hashCode(), INF)) {
                        neighbor.parent(current);
                        gScore.put(neighbor.hashCode(), tentativeScore);
                    }

                    open.add(neighbor);
                }
            }
        }

        return Collections.emptyList();
    }
}
