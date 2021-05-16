import java.util.Scanner;

public class Zadanie {

    public static int kolejka = 0;
    public static int[][] tablicaPol = new int[3][3];
    public static int rPozostalo = 6;
    public static int przesunPozostalo = 2;
    public static char[] letters = {'A', 'B', 'C'};

    public static void main(String[] args) {
        rysujPlansze();
        start();
    }

    public static void rysujPlansze() {
        System.out.println("    1   2   3");
        for (int i = 0; i < 3; i++) {
            System.out.print(letters[i] + " | ");
            for (int j = 0; j < 3; j++) {
                if (tablicaPol[i][j] == 1) {
                    System.out.print("O");
                } else if (tablicaPol[i][j] == 2) {
                    System.out.print("X");
                } else System.out.print(" ");
                System.out.print(" | ");
            }
            System.out.println();
        }

    }

    public static void start() {
        if (kolejka == 0) {
            System.out.print("Który gracz zaczyna? Wpisz 'x' lub 'o': ");
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            if (input.charAt(0) == 'o') {
                kolejka = 1;
            } else if (input.charAt(0) == 'x') {
                kolejka = 2;
            } else {
                System.out.println("Nieprawidłowy znak! Wpisz 'x' lub 'o' : ");
                start();
            }
            gra_zwykla();
        } else gra_zwykla();
    }

    public static void zmienKolejke() {
        if (kolejka == 1) {
            kolejka = 2;
        } else kolejka = 1;
    }
    public static String kolejkanaStringa(int kolejka){
        if (kolejka==1) return "O";
        else return "X";
    }

    public static void gra_zwykla() {
        if (rPozostalo > 0) {
            System.out.print("Kolejka gracza: " + kolejkanaStringa(kolejka) + " Wpisz adres pola: ");

            int [] wspolrzedne = dekoder();

            postawienieZnaku(wspolrzedne);
        } else przestawianie();
    }

    public static int[] dekoder() {//dekodujemy ruch np B1 na pole w tablicy pól jako [1][1]
        Scanner sc = new Scanner(System.in);
        String gdzie = sc.next();
        char[] litery = {'a', 'b', 'c'};
        int[] wsp = new int[2];
        for (int i = 0; i < 3; i++) {
            if (litery[i] == gdzie.charAt(0)) {
                wsp[0] = i;
            }
        }
        wsp[1] = Character.getNumericValue(gdzie.charAt(1)) - 1;
        return wsp;
    }
        public static void postawienieZnaku(int[] wspolrzedne){

        if (wspolrzedne[0] >= 0 && wspolrzedne[1] >= 0 && wspolrzedne[1] <= 2) {

            if (czyDozwolonyRuch(wspolrzedne)) {
                tablicaPol[wspolrzedne[0]][wspolrzedne[1]] = kolejka;
                rPozostalo--;
                zmienKolejke();
                rysujPlansze();
            } else {
                System.out.println("To miejsce jest już niedostępne!");
                start();

            }
        } else
            System.out.println("Błąd! Wpisz poprany adres pola! ");
        gra_zwykla();


    }

    public static boolean czyDozwolonyRuch(int[] wsppolrzedna) {
        return tablicaPol[wsppolrzedna[0]][wsppolrzedna[1]] != 1 && tablicaPol[wsppolrzedna[0]][wsppolrzedna[1]] != 2;

    }

    public static void przestawianie() {
        if (przesunPozostalo > 0) {
            rysujPlansze();
            System.out.print("Kolej na gracza " + kolejkanaStringa(kolejka) + ". Wskaż znak do przesunięcia: ");
            int[] skad=dekoder();
//            Scanner sc = new Scanner(System.in);
//            String skad = sc.next();
//            System.out.println("skąd: " + skad);
//            char[] litery2 = {'a', 'b', 'c'};
//            int skadindex1 = -1;
//            int skadindex2 = -1;
//            for (int i = 0; i < 3; i++) {
//                if (litery2[i] == skad.charAt(0)) {
//                    skadindex1 = i;
//                }
//            }
//            skadindex2 = Character.getNumericValue(skad.charAt(1)) - 1;

            if (tablicaPol[skad[0]][skad[1]] == kolejka) { // sprawdzamy czy przestawiany znak należy do gracza który ma obecnie kolejkę
                System.out.print("Dokąd?: ");
                int[] dokad = new int[2];
//                String dokad = sc.next();
//                char[] litery3 = {'a', 'b', 'c'};
//                int dokadindex1 = -1;
//                int dokadindex2 = -1;
//                for (int i = 0; i < 3; i++) {
//                    if (litery3[i] == dokad.charAt(0)) {
//                        dokadindex1 = i;
//                    }
//                }
//                dokadindex2 = Character.getNumericValue(dokad.charAt(1)) - 1;

                dokad=dekoder();

//                System.out.println("SKądI1: "+skadindex1);
//                System.out.println("Skad2: "+skadindex1);
//                System.out.println("Dok1: "+ dokadindex1);
//                System.out.println("Dok2: "+ dokadindex2);
                if (tablicaPol[dokad[0]][dokad[1]] == 0 && (skad[0] == dokad[0] || skad[1] == dokad[1])) {
                    tablicaPol[skad[0]][skad[1]] = 0;
                    tablicaPol[dokad[0]][dokad[1]] = kolejka;
                    przesunPozostalo--;
                    zmienKolejke();
                    przestawianie();

                } else
                    System.out.println("Nie możesz przesunąć znaku w to miejsce! Przesunąć znak można tylko poziomo lub pionowo, a docelowe miejsce musi być puste.");
                przestawianie();
            } else System.out.println("Ten znak nie należy do ciebie lub został błędnie wpisany!: ");
            przestawianie();
        }
        sprawdzWygrana();
    }

    public static void sprawdzWygrana() {
        String rezultRzad = "";
        String rezultCol = "";
        String rezultPskos = "";
        String rezultLskos = "";

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rezultRzad = rezultRzad + tablicaPol[i][j];
                rezultCol = rezultCol + tablicaPol[j][i];
            }
            rezultPskos=rezultPskos + tablicaPol[i][i];


            if (rezultRzad.contains("111") || rezultCol.contains("111") || rezultPskos.contains("111")) {
                System.out.println("Wygrywa gracz O");
                System.exit(1);
            } else if (rezultRzad.contains("222") || rezultCol.contains("222") || rezultPskos.contains("222")) {
                System.out.println("Wygrywa gracz X");
                System.exit(1);

            }

            rezultRzad = "";
            rezultCol = "";
        }
        rezultLskos = String.valueOf(tablicaPol[0][2]) + tablicaPol[1][1] + tablicaPol[2][0];
        if(rezultLskos.contains("111"))
        {
            System.out.println("Wygrywa gracz O");
            System.exit(1);
        }
        else if (rezultLskos.contains("222")){
            System.out.println("Wygrywa gracz X");
            System.exit(1);
        }
        else
            rysujPlansze();
            System.out.println("REMIS");
            System.exit(1);

            }


        }





