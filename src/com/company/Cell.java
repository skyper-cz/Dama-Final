package com.company;

import static java.util.Objects.isNull;

public class Cell {
    final boolean isBlack;
    Piece piece;
    boolean isSelected;
    Reason selectReason;

    public Cell(boolean isBlack) {
        this.isBlack = isBlack;
        this.piece = null;
        isSelected = false;
        selectReason = null;
    }

    public Cell(boolean isBlack, Piece piece) {
        this.isBlack = isBlack;
        this.piece = piece;
        isSelected = false;
        selectReason = null;

        if (!isBlack && !isNull(piece)) {
            System.out.println("Bílá buňka vytvořena");
        }
    }

    public Cell(boolean isBlack, boolean isPieceBlack) {
        this.isBlack = isBlack;
        this.piece = new Piece(isPieceBlack);
        isSelected = false;
        selectReason = null;

        if (!isBlack){
            System.out.println("Bílá buňka vytvořena");
        }
    }

    public Cell(boolean isBlack, boolean isPieceBlack, boolean isPieceQueen) {
        this.isBlack = isBlack;
        this.piece = new Piece(isPieceBlack, isPieceQueen);
        isSelected = false;
        selectReason = null;

        if (!isBlack){
            System.out.println("Bílá buňka vytvořena");
        }
    }

    public void select(){
        this.isSelected = !this.isSelected;
    }
    public void select(boolean value){
        this.isSelected = value;
    }

    public void select(boolean value, Reason r){
        this.isSelected = value;
        this.selectReason = r;
    }
    enum Reason {
        JUMP, MOVE, NONE
    }
}
