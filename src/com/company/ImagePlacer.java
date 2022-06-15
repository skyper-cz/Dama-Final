package com.company;

import javax.swing.*;

import static com.company.Main.buttony;
import static com.company.Main.plocha;
import static java.util.Objects.isNull;

public class ImagePlacer {
    static public void VlozObrazky() {
        for (int x = 0; x < plocha.length; x++) {
            for (int y = 0; y < plocha[x].length; y++) {
                if (!plocha[x][y].isBlack) {
                    buttony[x][y].setIcon(new ImageIcon("resources/checkers/white.png"));
                } else if (isNull(plocha[x][y].piece)) {
                    if (plocha[x][y].isSelected) {
                        buttony[x][y].setIcon(new ImageIcon("resources/checkers/blank highlighted.png"));
                    } else {
                        buttony[x][y].setIcon(new ImageIcon("resources/checkers/blank.png"));
                    }
                } else if (plocha[x][y].piece.isBlack) {
                    if (plocha[x][y].piece.isDama) {
                        if (plocha[x][y].isSelected) {
                            buttony[x][y].setIcon(new ImageIcon("resources/checkers/black double checker highlighted.png"));
                            //System.out.print("BDH ");
                        } else {
                            buttony[x][y].setIcon(new ImageIcon("resources/checkers/black double checker.png"));
                            //System.out.print("BDX ");
                        }
                    } else {
                        if (plocha[x][y].isSelected) {
                            buttony[x][y].setIcon(new ImageIcon("resources/checkers/black checker highlighted.png"));
                            //System.out.print("BCH ");
                        } else {
                            buttony[x][y].setIcon(new ImageIcon("resources/checkers/black checker.png"));
                            //System.out.print("BCX ");
                        }
                    }
                } else {
                    if (plocha[x][y].piece.isDama) {
                        if (plocha[x][y].isSelected) {
                            buttony[x][y].setIcon(new ImageIcon("resources/checkers/white double checker highlighted.png"));
                            //System.out.print("WDH ");
                        } else {
                            buttony[x][y].setIcon(new ImageIcon("resources/checkers/white double checker.png"));
                            //System.out.print("WDX ");
                        }
                    } else {
                        if (plocha[x][y].isSelected) {
                            buttony[x][y].setIcon(new ImageIcon("resources/checkers/white checker highlighted.png"));
                            //System.out.print("WCH ");
                        } else {
                            buttony[x][y].setIcon(new ImageIcon("resources/checkers/white checker.png"));
                            //System.out.print("WCX ");
                        }
                    }
                }
            }
            //System.out.println();
        }
        System.out.println("Done!");



    }
}
