package com.yevtsy.fifteens.search;

import com.yevtsy.fifteens.model.Board;

import java.util.Collection;

public interface SearchEngine {
    Collection<Board> search(Board board);
}
