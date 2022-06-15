package com.company;

public class Piece {
    final boolean isBlack;
    boolean isDama;

    public Piece(boolean isBlack) {
        this.isBlack = isBlack;
        this.isDama = false;
    }

    public Piece(boolean isBlack, boolean isDama) {
        this.isBlack = isBlack;
        this.isDama = isDama;
    }

    public void promote(){
        this.isDama = true;
    }
}
