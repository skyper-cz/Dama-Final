package com.company;

import javax.swing.*;

import java.awt.*;

import static com.company.Main.buttony;
import static com.company.Main.plocha;
import static java.util.Objects.isNull;

public class ImagePlacer {
    static public void VlozObrazky() {
        for (int x = 0; x < plocha.length; x++) {
            for (int y = 0; y < plocha[x].length; y++) {
                if (!plocha[x][y].isBlack) {
                    buttony[x][y].setIcon(new ImageIcon("resources/white_piece.png"));
                    
                } else if (isNull(plocha[x][y].piece)) {
                    if (plocha[x][y].isSelected) {
                        buttony[x][y].setIcon(new ImageIcon("resources/cyan.png"));
                        
                    }
                    else {
                        buttony[x][y].setIcon(new ImageIcon("resources/Black_piece.png"));
                    }
                } else if (plocha[x][y].piece.isBlack) {
                    if (plocha[x][y].piece.isDama) {
                        if (plocha[x][y].isSelected) {
                            buttony[x][y].setIcon(new ImageIcon("resources/Black_queen_cyan.png"));
                        }
                        else {
                            buttony[x][y].setIcon(new ImageIcon("resources/black_piece.png"));
                            buttony[x][y].setIcon(new ImageIcon("resources/Black_queen.png"));
                        }
                    } else {
                        if (plocha[x][y].isSelected) {
                            buttony[x][y].setIcon(new ImageIcon("resources/Black_cyan.png"));
                        }
                        else {
                            buttony[x][y].setIcon(new ImageIcon("resources/black_piece.png"));
                            buttony[x][y].setIcon(new ImageIcon("resources/Black.png"));
                        }
                    }
                }
                else {
                    if (plocha[x][y].piece.isDama) {
                        if (plocha[x][y].isSelected) {
                            buttony[x][y].setIcon(new ImageIcon("resources/White_queen_cyan.png"));
                        }
                        else {
                            buttony[x][y].setIcon(new ImageIcon("resources/black_piece.png"));
                            buttony[x][y].setIcon(new ImageIcon("resources/White_queen.png"));
                        }
                    }
                    else {
                        if (plocha[x][y].isSelected) {
                            buttony[x][y].setIcon(new ImageIcon("resources/White_cyan.png"));

                            

                        }
                        else {
                            buttony[x][y].setIcon(new ImageIcon("resources/black_piece.png"));
                            buttony[x][y].setIcon(new ImageIcon("resources/White.png"));
                        }
                    }
                }
            }
        }
    }
}
