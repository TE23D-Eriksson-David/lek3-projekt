import java.util.Scanner;
import java.util.InputMismatchException;

public class App {
    public static void main(String[] args) throws Exception {
        int Val = 0;
        boolean Klar = false;
        int[] bussPlatser = { 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        int[] pernsonumerPlatser = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        String[] NamnPlatser = { null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null };

        while (true) {
            System.out.println(
                    "1. Boka en plats.\n2. Se obokade platser.\n3. Se Bokning\n4. Ta bort bokning\n5. Se vinsten av bokade platser.\n6. Avsluta bokningen");
            System.out.print("Ange: ");
            Scanner TB = new Scanner(System.in);

            Val = VALMETOD(Klar, Val, TB, 1, 6);

            switch (Val) {
                case 1:
                    BokaPlats(bussPlatser, pernsonumerPlatser, NamnPlatser, Klar, Val, TB);
                    break;
                case 2:
                    SeObokadePlatser();
                    break;
                case 3:
                    SeBokning();
                    break;
                case 4:
                    TaBortBokning();
                    break;
                case 5:
                    SeVinsten();
                    break;
                case 6:
                    AvslutaBokning();
                    break;
            }

            TB.close();
        }

    }

    static void BokaPlats(int[] bussPlatser, int[] pernsonumerPlatser, String[] NamnPlatser, Boolean Klar, int Val,
            Scanner TB) {
        int AntalLedigaPlatser = 0;
        for (int i = 0; i < bussPlatser.length; i++) {
            if (bussPlatser[i] == 0) {
                AntalLedigaPlatser++;
            }
        }

        if (AntalLedigaPlatser == 0) {
            System.out.println("Ledsen det finns inga platser kvar att boka på denna buss!");
        } else {
            System.out.println("Det finns " + AntalLedigaPlatser + " platser Kvar på bussen.");
            System.out.println("Vill du ha en fönsterplats?");
            System.out.println("1. Ja\n2. Nej");

            Val = VALMETOD(Klar, Val, TB, 1, 2);

            if (Val == 1) {
                
                int x = 0;
                int y = 0;
                for (int i = 0; i < bussPlatser.length; i++) {
                    x++;
                    y++;
                    if (x == 2) {
                        System.out.print("|"+y+"| :X: ");
                    }
                    if (x == 1) {
                        if (bussPlatser[i] == 0) {
                            System.out.print("|"+y+"| :Ledig: ");
                        } else {
                            System.out.print("|"+y+"|   :X:   ");
                        }
                    }

                    if (x == 4) {
                        x = 0;
                        if (bussPlatser[i] == 0) {
                            System.out.println("|"+y+"| :Ledig: ");
                        } else {
                            System.out.println("|"+y+"|   :X:  ");
                        }
                    }

                    if (x == 3)
                        System.out.print("|"+y+"| :X:  ");
                }





            } else {
                int x = 0;
                int y = 0;
                for (int i = 0; i < bussPlatser.length; i++) {
                    x++;
                    y++;
                    if (x == 1) {
                        System.out.print("|"+y+"| :X:   ");
                    }
                    if (x == 2) {
                        if (bussPlatser[i] == 0) {
                            System.out.print("|"+y+"| :Ledig: ");
                        } else {
                            System.out.print("|"+y+"|   :X:   ");
                        }
                    }

                    if (x == 3) {
                        if (bussPlatser[i] == 0) {
                            System.out.print("|"+y+"|  :Ledig: ");
                        } else {
                            System.out.print("|"+y+"|    :X:   ");
                        }
                    }
                    if (x == 4) {
                        System.out.println("|"+y+"|  :X:   ");
                        x = 0;
                    }
                }
            }

        }

            



        System.exit(AntalLedigaPlatser);
    }

    static void SeObokadePlatser() {

    }

    static void SeBokning() {

    }

    static void TaBortBokning() {

    }

    static void SeVinsten() {

    }

    static void AvslutaBokning() {

    }

    static int VALMETOD(boolean Klar, int Val, Scanner TB, int Minstval, int Störstval) {
        Klar = false;
        Val = 0;
        while (Klar == false) {
            try {
                Val = TB.nextInt();
                Klar = true;
                if (Val < Minstval || Val > Störstval) {
                    Klar = false;
                    System.out.println("Ange en sifra som korispoderar till en av valen!");
                    System.out.print("Ange: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Skriv bara in sifror!");
                System.out.print("Ange: ");
                Klar = false;
                if (TB.hasNext())
                    TB.next();
            }
        }
        return Val;
    }

}
