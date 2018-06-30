package com.yevtsy.fifteens.comparators;

import com.yevtsy.fifteens.model.Board;

import java.util.Comparator;

public class AStarBoardComparator implements Comparator<Board> {
    @Override
    public int compare(Board b1, Board b2) {
        if (b1.passedCost() + b1.heuristic() < b2.passedCost() + b2.heuristic()) {
            return -1;
        } else if (b1.passedCost() + b1.heuristic() > b2.passedCost() + b2.heuristic()) {
            return 1;
        }

        return 0;
    }
}
