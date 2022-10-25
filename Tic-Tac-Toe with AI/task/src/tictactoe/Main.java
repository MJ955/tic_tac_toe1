package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        //Create and print initial table
        System.out.println("Enter the cells:");
        Scanner scanner = new Scanner(System.in);
        String cells = scanner.nextLine();
        String[][] tableState = new String[3][3];

        for (int i = 0; i < tableState.length; i++) {
            for (int j = 0; j < tableState[i].length; j++) {
                String symbol = String.valueOf(cells.charAt(i * tableState.length + j));
                tableState[i][j] = symbol;
            }    
        }

       // for (int i = 0; i < tableState.length; i++) {

        //    System.out.println(Arrays.toString(tableState[i]));
       // }
        printTable(tableState);

        //Enter coordinates
        System.out.println("Enter the coordinates:");
        int cellX = 0;
        int cellY = 0;
        boolean condshn = true;
        do {
            int[] crds = readInt();
            cellX = crds[0];
            cellY = crds[1];

            if (cellX < 1 || cellX > 3 || cellY < 1 || cellY > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                System.out.println("Enter the coordinates:");
                condshn = true;
            } else if (tableState[cellX - 1][cellY - 1].equals("X") || tableState[cellX - 1][cellY - 1].equals("O")) {
                System.out.println("This cell is occupied! Choose another one!");
                System.out.println("Enter the coordinates:");
                condshn = true;
            }
            else {

                condshn = false;
            }
        }
        while (condshn);

        //Check X & O and print the new table
        int countX = 0;
        int countO = 0;
        for (int i = 0; i < tableState.length; i++) {
            for (int j = 0; j < tableState[i].length; j++)
                if (tableState[i][j].equals("X")) {
                    countX++;
                } else if (tableState[i][j].equals("O")) {
                    countO++;
                }
        }
        if (countO > countX || countO == countX) {
            tableState[cellX - 1][cellY - 1] = "X";
        } else {
            tableState[cellX -1][cellY -1] = "O";
        }

        //for (int i = 0; i < tableState.length; i++) {
        //    System.out.println(Arrays.toString(tableState[i]));
       // }
        printTable(tableState);

        //Output the result of the game
        gameResult(tableState);
    }
    // Functions
    static int[] readInt() {
        Scanner scanner = new Scanner(System.in);
        int cellX = 0, cellY = 0;
        int[] cellCrds = {0, 0};
        String[] temp = scanner.nextLine().split(" ");
        try {
            cellX = Integer.parseInt(temp[0]);
            cellY = Integer.parseInt(temp[1]);
            cellCrds[0] = cellX;
            cellCrds[1] = cellY;
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
        } return cellCrds;

//        while (!scanner.hasNextInt()) {
//            System.out.println("You should enter numbers!");
//            System.out.println("Enter the coordinates:");
//            scanner.next();
//        }
//        return scanner.nextInt();

    }

    static boolean gameNotFinished(String[][] table) {
        int cntr = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j].equals("X")) {
                    cntr++;
                }
                if (table[i][j].equals("O")) {
                    cntr++;
                }
            }
        }
        return !xWins(table) && !oWins(table) && !draw(table) && cntr!= 9;

    }
    static boolean draw (String[][] table) {
        int cntrX = 0;
        int cntrO = 0;
        int cntrXO = 0;
        for (int i = 0; i < table.length; i++) {
            for (int a = 0; a < table[i].length; a++) {
                if (table[i][a].equals("X")) {
                    cntrX++;
                } else if (table[i][a].equals("O")) {
                    cntrO++;
                }
            }
            if (cntrX + cntrO == 3) {
                cntrX = 0;
                cntrO = 0;
                cntrXO++;
            } else {break;}

        } return cntrXO == 3;
    }
    static boolean across (String[][] table) {
        int count = 0;
        boolean xAcross = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j].equals("X")) {
                    count++;
                }
            }
            if (count == 3) {
                xAcross = false;
                break;
            } else {count = 0;}

        }return xAcross;
    }

    static boolean down(String[][] table) {
        boolean isDown = true;
        int j = 0;
        int count1 = 0;
        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < 3; i++) {

                if (table[i][j].equals("X")) {
                    count1++;

                }
            }
            if (count1 == 3) {
                isDown = false;
                break;
            } else {
                count1 = 0;
                j++;
            }
        } return isDown;
    }

    static boolean diagLr(String[][] table){
        int countD = 0;
        int b = 0;
        boolean lr = true;

        for (int i = 0; i < table.length; i++) {
            if (table[i][b].equals("X"))
                countD++;
            b++;
        }
        if (countD == 3) {
            lr = false;

        } return lr;
    }

    static boolean diagRl(String[][] table) {
        int countDD = 0;
        int c = table.length-1;
        boolean rl = true;
        for (int i = 0; i < table.length; i++) {
            if (table[i][c].equals("X")) {
                countDD++;
                c--;
            }
        }
        if (countDD == 3) {
            rl = false;

        } return rl;
    }

    static boolean xWins (String[][] table) {
        return (!across(table) || !down(table) || !diagLr(table) || !diagRl(table));
    }

// МЕТОДЫ ДЛЯ О:

    static boolean acrossO (String[][] table) {
        int count = 0;
        boolean oAcross = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j].equals("O")) {
                    count++;
                }
            }
            if (count == 3) {
                oAcross = false;
                break;
            } else {count = 0;}

        }return oAcross;
    }

    static boolean downO(String[][] table) {
        boolean isDownO = true;
        int j = 0;
        int count1 = 0;
        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < 3; i++) {

                if (table[i][j].equals("O")) {
                    count1++;

                }
            }
            if (count1 == 3) {
                isDownO = false;
                break;
            } else {
                count1 = 0;
                j++;
            }
        } return isDownO;
    }

    static boolean diagLrO(String[][] table){
        int countD = 0;
        int b = 0;
        boolean lrO = true;

        for (int i = 0; i < table.length; i++) {
            if (table[i][b].equals("O"))
                countD++;
            b++;
        }
        if (countD == 3) {
            lrO = false;

        } return lrO;
    }

    static boolean diagRlO(String[][] table) {
        int countDD = 0;
        int c = table.length-1;
        boolean rlO = true;
        for (int i = 0; i < table.length; i++) {
            if (table[i][c].equals("O")) {
                countDD++;
                c--;
            }
        }
        if (countDD == 3) {
            rlO = false;

        } return rlO;
    }

    static boolean oWins (String[][] table) {
        return (!acrossO(table) || !downO(table) || !diagLrO(table) || !diagRlO(table));
    }

//РЕЗУЛЬТАТ ИГРЫ:

    static void gameResult (String[][] table) {
        if (gameNotFinished(table)) {
            System.out.println("Game not finished");
        }
        if (draw(table)) {
            System.out.println("Draw");
        }
        if (xWins(table)) {
            System.out.println("X wins");
        }
        if (oWins(table)) {
            System.out.println("O wins");
        }
    }
    static void printTable(String[][] table) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {

                if (table[i][j].equals("_")) {
                    table[i][j] = " ";
                }
                System.out.print(table[i][j] + " ");


            }
            System.out.println("|");
        }
        System.out.println("---------");

    }


}
