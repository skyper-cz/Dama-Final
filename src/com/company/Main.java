package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static java.util.Objects.isNull;
import static com.company.ImagePlacer.VlozObrazky;

public class Main {
    public static JFrame frame = new JFrame("Checkers");
    public static Cell[][] plocha = new Cell[8][8];
    public static JButton[][] buttony = new JButton[8][8];
    public static boolean isSelected = false;
    public static int xSelected = -1;
    public static int ySelected = -1;
    public static JLabel blackWon = new JLabel(new ImageIcon("resources/checkers/Victory screens/black wins.png"));
    public static JLabel whiteWon = new JLabel(new ImageIcon("resources/checkers/Victory screens/white wins.png"));

    public static void main(String[] args) {
        System.out.println("Started checkers.");
        frame.setSize(642, 660);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initializeButtons();

        initializeCells();

        System.out.println("Game ready!");
        VlozObrazky();

    }

    public static void initializeButtons() {
        System.out.println("Initializing buttons...");
        int x = 0;
        int y = 0;

        for (int ix = 0; ix < buttony.length; ix++) {
            x = 1;
            for (int iy = 0; iy < buttony[ix].length; iy++) {
                buttony[ix][iy] = new JButton();
                buttony[ix][iy].setBounds(x, y, 80, 80);
                frame.add(buttony[ix][iy]);
                buttony[ix][iy].setVisible(true);
                buttony[ix][iy].addActionListener(Main::Click);
                x += 80;
            }
            System.out.println("x = " + x);
            y += 80;
        }
        System.out.println("y = " + y);

        blackWon.setBounds(23, 81, 642, 640);
        whiteWon.setBounds(23, 81, 642, 640);
        frame.add(blackWon);
        frame.add(whiteWon);
        blackWon.setVisible(false);
        whiteWon.setVisible(false);

        System.out.println("Done!");
    }

    public static void initializeCells() {
        System.out.println("Initializing cells...");
        @SuppressWarnings("UnusedAssignment") int x = 0;
        int y = 0;

        // Lord, have mercy.
        for (int ix = 0; ix < plocha.length; ix++) {
            x = 0;
            for (int iy = 0; iy < plocha[ix].length; iy++) {
                if (((x % 2) + (y % 2)) % 2 == 1) {
                    if (y < 3)
                        plocha[ix][iy] = new Cell(true, true);
                    else if (y > 4)
                        plocha[ix][iy] = new Cell(true, false);
                    else plocha[ix][iy] = new Cell(true);
                } else plocha[ix][iy] = new Cell(false);
                x++;
            }
            y++;
        }
        System.out.println("Done!");
    }

    static public void Click(ActionEvent e) {
        for (int x = 0; x < buttony.length; x++) {
            for (int y = 0; y < buttony[x].length; y++) {
                if (e.getSource() == buttony[x][y]) {
                    if (isSelected) {
                        if (!plocha[x][y].isSelected) {
                            isSelected = false;
                        } else {
                            switch (plocha[x][y].selectReason) {
                                default:
                                    isSelected = false;
                                    break;

                                case JUMP:
                                    if (x > xSelected) {
                                        if (y > ySelected) {
                                            plocha[x - 1][y - 1].piece = null;
                                        } else if (y < ySelected) {
                                            plocha[x - 1][y + 1].piece = null;
                                        }
                                    } else if (x < xSelected) {
                                        if (y > ySelected) {
                                            plocha[x + 1][y - 1].piece = null;
                                        } else if (y < ySelected) {
                                            plocha[x + 1][y + 1].piece = null;
                                        }
                                    }
                                case MOVE:
                                    plocha[x][y].piece = plocha[xSelected][ySelected].piece;
                                    plocha[xSelected][ySelected].piece = null;
                                    isSelected = false;
                                    if ((x == 0 && !plocha[x][y].piece.isBlack) || (x == 7 && plocha[x][y].piece.isBlack)) {
                                        plocha[x][y].piece.promote();
                                    }
                                    break;
                            }
                        }

                        DeselectAll();
                        VlozObrazky();
                    }
                    else {
                        if (plocha[x][y].isBlack) {
                            plocha[x][y].select(true, Cell.Reason.NONE);
                            isSelected = true;
                        }
                        if (!isNull(plocha[x][y].piece)) {
                            if (plocha[x][y].piece.isDama) {
                                for (int yPol = -1; yPol < 2; yPol = yPol + 2) {
                                    for (int xPol = -1; xPol < 2; xPol = xPol + 2) {
                                        for (int i = 1; i < 8; i++) {
                                            if ((x + i * xPol) > 7 || (x + i * xPol) < 0 || (y + i * yPol) > 7 || (y + i * yPol) < 0) {
                                                break;
                                            }
                                            else if (isNull(plocha[x + i * xPol][y + i * yPol].piece)) {
                                                plocha[x + i * xPol][y + i * yPol].select(true, Cell.Reason.MOVE);
                                            }
                                            else if (plocha[x + i * xPol][y + i * yPol].piece.isBlack == plocha[x][y].piece.isBlack) {
                                                break;
                                            } else if (plocha[x + i * xPol][y + i * yPol].piece.isBlack != plocha[x][y].piece.isBlack) {
                                                if ((x + (i + 1) * xPol) > 7 || (x + (i + 1) * xPol) < 0 || (y + (i + 1) * yPol) > 7 || (y + (i + 1) * yPol) < 0) {
                                                    break;
                                                }
                                                else if (isNull(plocha[x + (i + 1) * xPol][y + (i + 1) * yPol].piece)) {
                                                    plocha[x + (i + 1) * xPol][y + (i + 1) * yPol].select(true, Cell.Reason.JUMP);
                                                    break;
                                                }
                                                else {
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (plocha[x][y].piece.isBlack) {

                                    try {
                                        if (!isNull(plocha[x + 1][y - 1].piece)) {
                                            if (plocha[x][y].piece.isBlack != plocha[x + 1][y - 1].piece.isBlack && isNull(plocha[x + 2][y - 2].piece)) {
                                                plocha[x + 2][y - 2].select(true, Cell.Reason.JUMP);
                                            }
                                        } else plocha[x + 1][y - 1].select(true, Cell.Reason.MOVE);
                                    } catch (Exception ignored) {
                                    }

                                    try {
                                        if (!isNull(plocha[x - 1][y - 1].piece)) {
                                            if (plocha[x][y].piece.isBlack != plocha[x - 1][y - 1].piece.isBlack && isNull(plocha[x - 2][y - 2].piece)) {
                                                plocha[x - 2][y - 2].select(true, Cell.Reason.JUMP);
                                            }
                                        }
                                    } catch (Exception ignored) {
                                    }

                                    try {

                                        if (!isNull(plocha[x + 1][y + 1].piece)) {
                                            if (plocha[x][y].piece.isBlack != plocha[x + 1][y + 1].piece.isBlack && isNull(plocha[x + 2][y + 2].piece)) {
                                                plocha[x + 2][y + 2].select(true, Cell.Reason.JUMP);
                                            }
                                        } else plocha[x + 1][y + 1].select(true, Cell.Reason.MOVE);
                                    } catch (Exception ignored) {
                                    }

                                    try {
                                        if (!isNull(plocha[x - 1][y + 1].piece)) {
                                            if (plocha[x][y].piece.isBlack != plocha[x - 1][y + 1].piece.isBlack && isNull(plocha[x - 2][y + 2].piece)) {
                                                plocha[x - 2][y + 2].select(true, Cell.Reason.JUMP);
                                            }
                                        }
                                    } catch (Exception ignored) {
                                    }

                                }

                                else {
                                    try {
                                        if (!isNull(plocha[x + 1][y - 1].piece)) {
                                            if (plocha[x][y].piece.isBlack != plocha[x + 1][y - 1].piece.isBlack && isNull(plocha[x + 2][y - 2].piece)) {
                                                plocha[x + 2][y - 2].select(true, Cell.Reason.JUMP);
                                            }
                                        }
                                    } catch (Exception ignored) {
                                    }

                                    try {
                                        if (!isNull(plocha[x - 1][y - 1].piece)) {
                                            if (plocha[x][y].piece.isBlack != plocha[x - 1][y - 1].piece.isBlack && isNull(plocha[x - 2][y - 2].piece)) {
                                                plocha[x - 2][y - 2].select(true, Cell.Reason.JUMP);
                                            }
                                        } else plocha[x - 1][y - 1].select(true, Cell.Reason.MOVE);
                                    } catch (Exception ignored) {
                                    }

                                    try {
                                        if (!isNull(plocha[x + 1][y + 1].piece)) {
                                            if (plocha[x][y].piece.isBlack != plocha[x + 1][y + 1].piece.isBlack && isNull(plocha[x + 2][y + 2].piece)) {
                                                plocha[x + 2][y + 2].select(true, Cell.Reason.JUMP);
                                            }
                                        }
                                    } catch (Exception ignored) {
                                    }

                                    try {
                                        if (!isNull(plocha[x - 1][y + 1].piece)) {
                                            if (plocha[x][y].piece.isBlack != plocha[x - 1][y + 1].piece.isBlack && isNull(plocha[x - 2][y + 2].piece)) {
                                                plocha[x - 2][y + 2].select(true, Cell.Reason.JUMP);
                                            }
                                        } else plocha[x - 1][y + 1].select(true, Cell.Reason.MOVE);
                                    } catch (Exception ignored) {
                                    }


                                }
                            }
                        } else System.out.println("Clicked tile has a null piece.");
                        xSelected = x;
                        ySelected = y;
                        VlozObrazky();
                    }

                }
            }
        }
        WhoWon();
    }
    public static void DeselectAll() {
        for (Cell[] cells : plocha) {
            for (Cell cell : cells) {
                cell.isSelected = false;
            }
        }
    }

    public void select(int x, int y) {
        plocha[x][y].isSelected = true;
    }


    public void deselect(int x, int y) {
        plocha[x][y].isSelected = false;
    }

    public static void WhoWon() {
        boolean isItBlack = true;
        boolean isItWhite = true;
        for (Cell[] cells : plocha) {
            for (Cell cell : cells) {
                if (!isNull(cell.piece)) {
                    if (cell.piece.isBlack) {
                        isItWhite = false;
                    } else {
                        isItBlack = false;
                    }
                }
            }
        }
        if (isItBlack && isItWhite) {
            System.out.println("Vyhráli oba");
        }


        if (isItBlack || isItWhite) {
            System.out.println("Somebody won!");
            for (int x = 0; x < plocha.length; x++) {
                for (int y = 0; y < plocha[x].length; y++) {
                    buttony[x][y].setVisible(false);
                }
            }
            if (isItBlack) {
                blackWon.setVisible(true);
            } else {
                whiteWon.setVisible(true);
            }
        } else
            System.out.println("Nobody won!");
    }
}
