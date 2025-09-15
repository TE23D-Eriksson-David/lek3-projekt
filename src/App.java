import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) throws Exception {
        int Val = 0;
        boolean Klar = false;
        int[] bussPlatser = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        Long[] pernsonumerPlatser = { 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l };
        String[] NamnPlatser = { null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null };
        Scanner TB = new Scanner(System.in);

        while (true) {
            Val = 0;
            System.out.println(
                    "1. Boka en plats.\n2. Se obokade platser.\n3. Se Bokning\n4. Ta bort bokning\n5. Se vinsten av bokade platser.\n6. Avsluta bokningen");
            System.out.print("Ange: ");

            Val = VALMETOD(Klar, Val, TB, 1, 6);

            switch (Val) {
                case 1:
                    BokaPlats(bussPlatser, pernsonumerPlatser, NamnPlatser, Klar, Val, TB);
                    break;
                case 2:
                    SeObokadePlatser(bussPlatser);
                    break;
                case 3:
                    SeBokning(TB, NamnPlatser, pernsonumerPlatser);
                    break;
                case 4:
                    TaBortBokning();
                    break;
                case 5:
                    SeVinsten();
                    break;
                case 6:
                    AvslutaBokning(TB);
                    break;
            }

        }

    }

    static void BokaPlats(int[] bussPlatser, Long[] pernsonumerPlatser, String[] NamnPlatser, Boolean Klar, int Val,
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

            if (Val == 1) { // Fönsterplats1

                int x = 0;
                int y = 0;
                for (int i = 0; i < bussPlatser.length; i++) {
                    x++;
                    y++;
                    if (x == 2) {
                        System.out.print("|" + y + "| :X: ");
                    }
                    if (x == 1) {
                        if (bussPlatser[i] == 0) {
                            System.out.print("|" + y + "| :Ledig: ");
                        } else {
                            System.out.print("|" + y + "|   :X:   ");
                        }
                    }

                    if (x == 4) {
                        x = 0;
                        if (bussPlatser[i] == 0) {
                            System.out.println("|" + y + "| :Ledig: ");
                        } else {
                            System.out.println("|" + y + "|   :X:  ");
                        }
                    }

                    if (x == 3)
                        System.out.print("|" + y + "| :X:  ");
                }

                System.out.println("Ange sifran för en ledig plats.");
                Klar = false;
                Val = 0;
                while (Klar == false) {
                    try {
                        Val = TB.nextInt();
                        Klar = true;

                        System.out.println(Val % 4);
                        System.out.println((Val - 1) % 4); // Ska tass bort !!
                        if (Val % 4 != 0 && (Val - 1) % 4 != 0 || bussPlatser[Val - 1] != 0) {
                            Klar = false;
                            System.out.println("Ange en sifra som korispoderar till en av platserna!");
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
                Val--;
                bussPlatser[Val] = 1;
                AngeInformation(Val, NamnPlatser, pernsonumerPlatser);

            } else {
                int x = 0;
                int y = 0;
                for (int i = 0; i < bussPlatser.length; i++) {
                    x++;
                    y++;
                    if (x == 1) {
                        System.out.print("|" + y + "| :X:   ");
                    }
                    if (x == 2) {
                        if (bussPlatser[i] == 0) {
                            System.out.print("|" + y + "| :Ledig: ");
                        } else {
                            System.out.print("|" + y + "|   :X:   ");
                        }
                    }

                    if (x == 3) {
                        if (bussPlatser[i] == 0) {
                            System.out.print("|" + y + "|  :Ledig: ");
                        } else {
                            System.out.print("|" + y + "|    :X:   ");
                        }
                    }
                    if (x == 4) {
                        System.out.println("|" + y + "|  :X:   ");
                        x = 0;
                    }
                }

                System.out.println("Ange sifran för en ledig plats.");
                Klar = false;
                Val = 0;
                while (Klar == false) {
                    try {
                        Val = TB.nextInt();
                        Klar = true;

                        if (Val % 2 != 0 && Val % 4 == 0 || (Val - 1) % 2 != 0 && Val % 4 == 0
                                || bussPlatser[Val - 1] != 0) {
                            Klar = false;
                            System.out.println("Ange en sifra som korispoderar till en av Platserna!");
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
                Val--;
                bussPlatser[Val] = 1;
                AngeInformation(Val, NamnPlatser, pernsonumerPlatser);

            }

        }
    }

    static void SeObokadePlatser(int[] bussPlatser) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < bussPlatser.length; i++) {
            x++;
            y++;
            if (x == 1) {
                if (bussPlatser[i] == 0) {
                    System.out.print("|" + y + "| :Ledig: ");
                } else {
                    System.out.print("|" + y + "|   :X:   ");
                }
            }

            if (x == 2) {
                if (bussPlatser[i] == 0) {
                    System.out.println("|" + y + "| :Ledig: ");
                } else {
                    System.out.println("|" + y + "|   :X:  ");
                }
            }

            if (x == 3) {
                if (bussPlatser[i] == 0) {
                    System.out.print("|" + y + "| :Ledig: ");
                } else {
                    System.out.print("|" + y + "|   :X:   ");
                }
            }

            if (x == 4) {
                x = 0;
                if (bussPlatser[i] == 0) {
                    System.out.println("|" + y + "| :Ledig: ");
                } else {
                    System.out.println("|" + y + "|   :X:  ");
                }
            }
        }
    }

    static void SeBokning(Scanner TB, String[] NamnPlatser, Long[] pernsonumerPlatser) {
        boolean Klar = false;
        long Pnumer;
        String PnumerString;
        String Input;
        String FormateratInput;

        System.out.println("För att se din plats måste du ange antingen dit fulla namn eller personumeret.");
        System.out.println("1. Namn\n2. Personumer");
        int Val = VALMETOD(false, 0, TB, 1, 2);

        if (Val == 1) {
            System.out.print("Ange fult namn:");
            Input = TB.nextLine().toLowerCase();
            FormateratInput = Input.replaceAll("[0123456789!@£$¤%&/{()=^¨~*'-_.:,;`+#?]", "");
        
        for (String namn : NamnPlatser) {
            if (namn == FormateratInput) {
                System.out.println();
                break;
            }
        }

        System.out.println("Det verkar inte finas någon bokning med det namnet!");

        } else {
            System.out.print("Ange personumer:");
            while (Klar == false) {
            try {
                Pnumer = TB.nextLong();
                Klar = true;
                PnumerString = Long.toString(Pnumer);
                System.out.println(PnumerString); // TA bortt

                if (PnumerString.length() != 12) {
                    Klar = false;
                    System.out.println("Ditt personumer kan bara vara 12 sifror");
                    System.out.println("Ange personumer:");
                }

            } catch (InputMismatchException e) {
                System.out.println("Skriv bara in sifror!");
                System.out.print("Ange personumer: ");
                Klar = false;
                if (TB.hasNext())
                    TB.next();
            }
        }
        }
       
    }

    static void TaBortBokning() {

    }

    static void SeVinsten() {

    }

    static void SeSorteradBokning() {

    }

    static void AvslutaBokning(Scanner TB) {
        TB.close();
        System.exit(0);
    }

    static void AngeInformation(int Val, String[] NP, Long[] PN) {
        Scanner TB = new Scanner(System.in);
        boolean Klar = false;
        long Pnumer = 1;
        String PnumerString = "";
        String Namn = null;
        String FormateratNamn = null;

        System.out.println("För att boka en plats måste du ange dit fulla namn och personumer.");
        System.out.println("Ange namn:");

        Namn = TB.nextLine().toLowerCase();
        FormateratNamn = Namn.replaceAll("[0123456789!@£$¤%&/{()=^¨~*'-_.:,;`+#?]", "");
        System.out.println(FormateratNamn);
        NP[Val] = FormateratNamn;

        System.out.print("Ange personumer:");
        while (Klar == false) {
            try {
                Pnumer = TB.nextLong();
                Klar = true;
                PnumerString = Long.toString(Pnumer);
                System.out.println(PnumerString); // TA bortt

                if (PnumerString.length() != 12) {
                    Klar = false;
                    System.out.println("Ditt personumer kan bara vara 12 sifror");
                    System.out.println("Ange personumer:");
                }

            } catch (InputMismatchException e) {
                System.out.println("Skriv bara in sifror!");
                System.out.print("Ange personumer: ");
                Klar = false;
                if (TB.hasNext())
                    TB.next();
            }
        }
        System.out.println(Klar);
        PN[Val] = Pnumer;

        // LocalDate idag = LocalDate.now();
        // String idagStr = idag.toString();
        // idagStr = idagStr.replaceAll("-", "");
        // System.out.println(idagStr);
        // String Födelseår = PnumerString.substring(0, 9);
        // int FödelseårInt = Integer.parseInt(Födelseår);
        // int idagStrInt = Integer.parseInt(idagStr);
        // if (idagStrInt - FödelseårInt > 18) {
        // System.out.println(); // Vuxen!!
        // }

        Klar = false;
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
