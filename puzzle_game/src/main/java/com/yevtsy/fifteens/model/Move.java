package com.yevtsy.fifteens.model;

public enum Move {

    LEFT("left"),
    RIGHT("right"),
    UP("up"),
    DOWN("down");

    private String move;

    Move(String move) {
        this.move = move;
    }

    @Override
    public String toString() {
        return move;
    }

    public static Move parse(String move) {
        for (Move m : Move.values()) {
            if (m.toString().equalsIgnoreCase(move)) {
                return m;
            }
        }
        return null;
    }
}
