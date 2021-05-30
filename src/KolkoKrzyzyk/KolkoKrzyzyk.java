package KolkoKrzyzyk;

import java.util.Locale;
import java.util.Scanner;

class KolkoKrzyzyk {
    static char oponent = 'x', player = 'o';

    static Boolean isMovesLeft(char[][] board) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == '_') return true;
        return false;
    }

    public static int evaluate(char[][] b) {
        int whoWins;
        if (b[0][1] == b[0][2] && (b[0][1] == b[1][0] || b[0][1] == b[2][0])) {
            whoWins = b[0][1];
            if (whoWins == oponent) return +10;
            else if (whoWins == player) return -10;
        } else if (b[0][0] == b[0][2] && (b[0][0] == b[1][1] || b[0][0] == b[2][1])) {
            whoWins = b[0][0];
            if (whoWins == oponent) return +10;
            else if (whoWins == player) return -10;
        } else if (b[0][0] == b[0][1] && (b[0][0] == b[1][2] || b[0][0] == b[2][2])) {
            whoWins = b[0][0];
            if (whoWins == oponent) return +10;
            else if (whoWins == player) return -10;
        } else if (b[1][2] == b[1][1] && (b[1][2] == b[2][0] || b[1][2] == b[0][0])) {
            whoWins = b[1][2];
            if (whoWins == oponent) return +10;
            else if (whoWins == player) return -10;
        } else if (b[1][0] == b[1][2] && (b[1][0] == b[2][1] || b[1][0] == b[0][1])) {
            whoWins = b[1][0];
            if (whoWins == oponent) return +10;
            else if (whoWins == player) return -10;
        } else if (b[1][0] == b[1][1] && (b[1][0] == b[0][2] || b[1][0] == b[2][2])) {
            whoWins = b[1][0];
            if (whoWins == oponent) return +10;
            else if (whoWins == player) return -10;
        } else if (b[2][1] == b[2][2] && (b[2][1] == b[1][0] || b[2][1] == b[0][0])) {
            whoWins = b[2][1];
            if (whoWins == oponent) return +10;
            else if (whoWins == player) return -10;
        } else if (b[2][0] == b[2][2] && (b[2][0] == b[1][1] || b[2][0] == b[0][1])) {
            whoWins = b[2][0];
            if (whoWins == oponent) return +10;
            else if (whoWins == player) return -10;
        } else if (b[2][0] == b[2][1] && (b[2][0] == b[1][2] || b[2][0] == b[0][2])) {
            whoWins = b[2][0];
            if (whoWins == oponent) return +10;
            else if (whoWins == player) return -10;
        } else if (b[0][0] == b[1][0] && (b[0][0] == b[2][1] || b[0][0] == b[2][2])) {
            whoWins = b[0][0];
            if (whoWins == oponent) return +10;
            else if (whoWins == player) return -10;
        } else if (b[0][0] == b[2][0] && (b[0][0] == b[1][1] || b[0][0] == b[1][2])) {
            whoWins = b[0][0];
            if (whoWins == oponent) return +10;
            else if (whoWins == player) return -10;
        } else if (b[1][0] == b[2][0] && (b[1][0] == b[0][1] || b[1][0] == b[0][2])) {
            whoWins = b[1][0];
            if (whoWins == oponent) return +10;
            else if (whoWins == player) return -10;
        } else if (b[0][1] == b[1][1] && (b[0][1] == b[2][0] || b[0][1] == b[2][2])) {
            whoWins = b[0][1];
            if (whoWins == oponent) return +10;
            else if (whoWins == player) return -10;
        } else if (b[0][1] == b[2][1] && (b[0][1] == b[1][0] || b[0][1] == b[1][2])) {
            whoWins = b[0][1];
            if (whoWins == oponent) return +10;
            else if (whoWins == player) return -10;
        } else if (b[1][1] == b[2][1] && (b[1][1] == b[0][0] || b[1][1] == b[0][2])) {
            whoWins = b[1][1];
            if (whoWins == oponent) return +10;
            else if (whoWins == player) return -10;
        } else if (b[0][2] == b[1][2] && (b[0][2] == b[2][1] || b[0][2] == b[2][0])) {
            whoWins = b[0][2];
            if (whoWins == oponent) return +10;
            else if (whoWins == player) return -10;
        } else if (b[0][2] == b[2][2] && (b[0][2] == b[1][1] || b[0][2] == b[1][0])) {
            whoWins = b[0][2];
            if (whoWins == oponent) return +10;
            else if (whoWins == player) return -10;
        } else if (b[1][2] == b[2][2] && (b[1][2] == b[0][1] || b[1][2] == b[0][0])) {
            whoWins = b[1][2];
            if (whoWins == oponent) return +10;
            else if (whoWins == player) return -10;
        }
        return 0;
    }

    static int minimax(char[][] board, int depth, Boolean isMax) {
        int score = evaluate(board);
        if (score == 10) return score - depth;
        if (score == -10) return score + depth;
        if (!isMovesLeft(board)) return 0;
        int best;
        if (isMax) {
            best = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '_') {
                        board[i][j] = oponent;
                        best = Math.max(best, minimax(board, depth + 1, false));
                        board[i][j] = '_';
                    }
                }
            }
        } else {
            best = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '_') {
                        board[i][j] = player;
                        best = Math.min(best, minimax(board, depth + 1, true));
                        board[i][j] = '_';
                    }
                }
            }
        }
        return best;
    }

    static Move findBestMove(char[][] board) {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '_') {
                    board[i][j] = oponent;
                    int moveVal = minimax(board, 0, false);
                    board[i][j] = '_';
                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    public static void main(String[] args) {
        char[][] board = {{'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}};

        for (int i = 0; i < 3; i++) {
            Move bestMove = findBestMove(board);
            board[bestMove.row][bestMove.col] = oponent;
            draw(board);
            boolean isPlayerInserted=playerInsert(board);
            while (!isPlayerInserted){
                isPlayerInserted=playerInsert(board);
            }
        }
        draw(board);
        pcMoving(board);
        draw(board);
        playerMoving(board);
        draw(board);
        if (checkWinner(board) == -1) System.out.println("REMIS");
        else if (checkWinner(board) == 2) System.out.println("WYGRAŁEŚ! :DDD");
        else if (checkWinner(board) == 1) System.out.println("PRZEGRAŁEŚ :((");
    }

    public static void draw(char[][] b) {
        char[] letters = {'A', 'B', 'C'};
        System.out.println("     1      2      3 ");
        System.out.println("   |-----|------|------|");
        for (int i = 0; i < 3; i++) {
            System.out.print(letters[i] + "  | ");
            for (int j = 0; j < 3; j++) {
                System.out.print(" ");
                if (b[i][j] == '_') System.out.print("   ");
                else if (b[i][j] == 'x') System.out.print("X  ");
                else if (b[i][j] == 'o') System.out.print("O  ");
                System.out.print("|  ");
            }
            System.out.println();
            System.out.print("   |-----|------|------|");
            System.out.println();
        }
    }

    public static boolean playerInsert(char[][] b) {
        System.out.print("Wpisz miejsce: ");
        int[] wsp = decoder();
        if (b[wsp[0]][wsp[1]] == '_') {
            b[wsp[0]][wsp[1]] = player;
            return true;
        } else {
            System.out.println("To miejsce jest zajęte!");
            return false;
        }
    }

    public static int[] decoder() {
        Scanner sc = new Scanner(System.in);
        String input = sc.next().toLowerCase(Locale.ROOT);
        while (!(input.equals("a1") || input.equals("a2") || input.equals("a3") || input.equals("b1") || input.equals("b2") || input.equals("b3") || input.equals("c1") || input.equals("c2") || input.equals("c3"))){
            System.out.println("Nie ma takiego miejsca na planszy. Przykładowe miejsce: b2");
            input = sc.next().toLowerCase(Locale.ROOT);
        }
        char[] letters = {'a', 'b', 'c'};
        int[] wsp = new int[2];
        for (int i = 0; i < 3; i++) {
            if (letters[i] == input.charAt(0)) {
                wsp[0] = i;
            }
        }
        wsp[1] = Character.getNumericValue(input.charAt(1)) - 1;
        return wsp;
    }

    public static void pcMoving(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 'x') {
                    for (int k = 0; k < board.length; k++) {
                        for (int l = 0; l < board.length; l++) {
                            if (board[k][l] == '_' && (j == l || i == k)) {
                                board[k][l] = 'x';
                                board[i][j] = '_';
                                if (checkWinner(board) == 1) {
                                    return;
                                } else {
                                    board[k][l] = '_';
                                    board[i][j] = 'x';
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 'x') {
                    for (int k = 0; k < board.length; k++) {
                        for (int l = 0; l < board.length; l++) {
                            if (board[k][l] == '_' && (j == l || i == k)) {
                                board[k][l] = 'o';
                                board[i][j] = '_';
                                if (checkWinner(board) == 2) {
                                    board[k][l] = 'x';
                                    return;
                                } else {
                                    board[k][l] = '_';
                                    board[i][j] = 'x';
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static int checkWinner(char[][] b) {
        boolean isPlayerWin = false;
        boolean isComputerWin = false;
        for (int row = 0; row < 3; row++) {
            if (b[row][0] == b[row][1] &&
                    b[row][1] == b[row][2]) {
                if (b[row][0] == oponent) {
                    isComputerWin = true;
                } else if (b[row][0] == player)
                    isPlayerWin = true;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (b[0][col] == b[1][col] &&
                    b[1][col] == b[2][col]) {
                if (b[0][col] == oponent) {
                    isComputerWin = true;
                } else if (b[0][col] == player) {
                    isPlayerWin = true;
                }
            }
        }

        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
            if (b[0][0] == oponent) {
                isComputerWin = true;
            } else if (b[0][0] == player) {
                isPlayerWin = true;
            }
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
            if (b[0][2] == oponent) {
                isComputerWin = true;
            } else if (b[0][2] == player) {
                isPlayerWin = true;
            }
        }
        if (isComputerWin && isPlayerWin) return -1;
        if (isPlayerWin) return 2;
        if (isComputerWin) return 1;
         else return -1;
    }

    public static void playerMoving(char[][] board) {
        System.out.println("Wpisz współrzędne znaku który chcesz przesunąć: ");
        int[] from = decoder();
        if (board[from[0]][from[1]] == player) {
            System.out.print("Dokąd?: ");
            int[] where = decoder();
            if (board[where[0]][where[1]] == '_' && (from[0] == where[0] || from[1] == where[1])) {
                board[from[0]][from[1]] = '_';
                board[where[0]][where[1]] = player;
                return;
            } else
                System.out.println("Nie możesz przesunąć znaku w to miejsce! Przesunąć znak można tylko poziomo lub pionowo, a docelowe miejsce musi być puste.");
            playerMoving(board);
        } else System.out.println("Ten znak nie należy do ciebie lub został błędnie wpisany!: ");
        playerMoving(board);
    }

    static class Move {
        int row, col;
    }
}
