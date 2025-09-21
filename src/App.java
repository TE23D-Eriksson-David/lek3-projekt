import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) throws Exception {
        int Val = 0;
        boolean Klar = false;
        int[] bussPlatser = { 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0 };
        Long[] pernsonumerPlatser = { 200509081234l, 199605061234l, 199811213456l, 202411011234l, 197806143456l, 200809303456l, 0l, 0l, 0l, 0l, 0l, 0l,
                0l, 200102243456l, 0l, 200003091234l, 0l, 0l, 0l, 0l };
        String[] NamnPlatser = { "a", "b", "c", "d", "e", "f", "", "", "", "", "",
                "", "", "g", "", "h", "", "", "", "" };
        Scanner TB = new Scanner(System.in);

        while (true) {
            Val = 0;
            System.out.println("");
            System.out.println("Skriv sifran som korisponderar till alternativet.");
            System.out.println(
                    "1. Boka en plats.\n2. Se obokade platser.\n3. Se Bokning\n4. Ta bort bokning.\n5. Se vinsten av bokade platser.\n6. Se sorterad bokning.\n7. Avsluta bokningen");
            System.out.print("Ange: ");

            Val = VALMETOD(Klar, Val, TB, 1, 7);

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
                    TaBortBokning(TB, NamnPlatser, pernsonumerPlatser, bussPlatser);
                    break;
                case 5:
                    SeVinsten(pernsonumerPlatser);
                    break;
                case 6:
                    SeSorteradBokning(pernsonumerPlatser, NamnPlatser);
                    break;
                case 7:
                    AvslutaBokning(TB);
                    break;
            }

        }

    }

    static void BokaPlats(int[] bussPlatser, Long[] pernsonumerPlatser, String[] NamnPlatser, Boolean Klar, int Val,
            Scanner TB) {
        int AntalLedigaPlatser = 0;
        for (int i = 0; i < bussPlatser.length - 1; i++) {
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
                    System.out.print("|" + y + "| :Ledig: ");
                } else {
                    System.out.print("|" + y + "|   :X:  ");
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
        long Pnumer = 0;
        String PnumerString;
        String Input = "";
        String FormateratInput;

        System.out.println("För att se din plats måste du ange antingen dit fulla namn eller personumeret.");
        System.out.println("1. Namn\n2. Personumer");
        int Val = VALMETOD(false, 0, TB, 1, 2);

        if (TB.hasNextLine()) {
            TB.nextLine();
        }

        if (Val == 1) {
            System.out.print("Ange fullt namn: ");
            Input = TB.nextLine();
            System.out.println(Input);

            FormateratInput = Input.replaceAll("[0123456789!@£$¤%&/{()=^¨~*'-_.:,;`+#?]", "");
            System.out.println(FormateratInput);

            for (int i = 0; i < NamnPlatser.length; i++) {
                System.out.println(NamnPlatser[i]);
                if (NamnPlatser[i].equals(FormateratInput)) {
                    Klar = true;
                    System.out.println();
                    if (i % 2 == 0 && i % 4 != 0 || (i - 1) % 2 == 0 && i % 4 != 0) {
                        i++;
                        System.out.println("Du har bokat plats numer " + i + "");
                        System.out.println("Det är en mitt plats");
                    } else {
                        i++;
                        System.out.println("Du har bokat plats numer " + i + "");
                        System.out.println("Det är en fönsterplats");
                    }
                    break;
                }
            }

            if (Klar == false) {
                System.out.println("Det verkar inte finas någon bokning med det namnet!");
            }

        } else {
            System.out.print("Ange ditt personumer:");
            while (Klar == false) {
                try {
                    Pnumer = TB.nextLong();
                    Klar = true;
                    PnumerString = Long.toString(Pnumer);
                    System.out.println(PnumerString); // TA bortt

                    if (PnumerString.length() != 12) {
                        Klar = false;
                        System.out.println("Ditt personumer kan bara vara 12 sifror");
                        System.out.println("Ange ditt personumer:");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Skriv bara in sifror!");
                    System.out.print("Ange ditt personumer: ");
                    Klar = false;
                    if (TB.hasNext())
                        TB.next();
                }
            }

            Klar = false;
            for (int i = 0; i < pernsonumerPlatser.length; i++) {
                if (pernsonumerPlatser[i] == Pnumer) {
                    Klar = true;
                    System.out.println("Du har bokat plats numer " + i + 1 + "");
                    if (i % 2 == 0 && i % 4 != 0 || (i - 1) % 2 == 0 && i % 4 != 0) {
                        System.out.println("Det är en mitt plats");
                    } else {
                        System.out.println("Det är en fönsterplats");
                    }
                    break;
                }
            }

            if (Klar == false)
                System.out.println("Det verkar inte finas någon bokning med det personumret!");

        }

    }

    static void TaBortBokning(Scanner TB, String[] NamnPlatser, Long[] pernsonumerPlatser, int[] bussPlatser) {
        boolean Klar = false;
        long Pnumer = 0;
        String PnumerString;
        String Input;
        String FormateratInput;

        System.out.println("För att ta bort din bokning måste du antingen ange namnet för platsen eller personumeret.");
        System.out.println("1. Namn\n2. Personumer");
        int Val = VALMETOD(false, 0, TB, 1, 2);

        if (TB.hasNextLine()) {
            TB.nextLine();
        }

        if (Val == 1) {
            System.out.print("Ange fult namn:");
            Input = TB.nextLine().toLowerCase();
            FormateratInput = Input.replaceAll("[0123456789!@£$¤%&/{()=^¨~*'-_.:,;`+#?]", "");

            for (int i = 0; i < NamnPlatser.length; i++) {
                if (NamnPlatser[i].equals(FormateratInput)) {
                    Klar = true;
                    NamnPlatser[i] = "0";
                    pernsonumerPlatser[i] = 0l;
                    bussPlatser[i] = 0;
                    System.out.println("Din bokning är nu bort tagen.");
                    break;
                }
            }

            if (Klar == false) {
                System.out.println("Det verkar inte finas någon bokning med det namnet!");
            }

        } else {
            System.out.print("Ange ditt personumer:");
            while (Klar == false) {
                try {
                    Pnumer = TB.nextLong();
                    Klar = true;
                    PnumerString = Long.toString(Pnumer);
                    System.out.println(PnumerString); // TA bortt

                    if (PnumerString.length() != 12) {
                        Klar = false;
                        System.out.println("Ditt personumer kan bara vara 12 sifror");
                        System.out.println("Ange ditt personumer:");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Skriv bara in sifror!");
                    System.out.print("Ange ditt personumer: ");
                    Klar = false;
                    if (TB.hasNext())
                        TB.next();
                }
            }

            Klar = false;
            for (int i = 0; i < pernsonumerPlatser.length; i++) {
                if (pernsonumerPlatser[i] == Pnumer) {
                    Klar = true;
                    pernsonumerPlatser[i] = 0l;
                    NamnPlatser[i] = "0";
                    System.out.println("Din bokning är nu bort tagen.");
                    break;
                }
            }

            if (Klar == false)
                System.out.println("Det verkar inte finas någon bokning med det personumret!");

        }

    }

    static void SeVinsten(Long[] personumerPlatser) {
        double Summa = 0;
        LocalDate idag = LocalDate.now();
        String idagStr = idag.toString();
        idagStr = idagStr.replaceAll("-", "");

        for (int i = 0; i < personumerPlatser.length - 1; i++) {
            if (personumerPlatser[i] != 0) {
                Long Pnumer = personumerPlatser[i];
                System.out.println(Pnumer);
                String PnumerString = Pnumer.toString();

                String Födelseår = PnumerString.substring(0, 8);
                System.out.println(Födelseår);
                int FödelseårInt = Integer.parseInt(Födelseår);
                int idagStrInt = Integer.parseInt(idagStr);
                System.out.println(FödelseårInt);
                System.out.println(idagStrInt);
                System.out.println(idagStrInt - FödelseårInt);
                if (idagStrInt - FödelseårInt > 180000) {
                    Summa = Summa + 299.90;
                    System.out.println("v");
                } else {
                    System.out.println("b");
                    Summa = Summa + 149.90;
                }
            }
        }

        System.out.println("Intäkterna blev " + Summa + "kr.");
    }

    static void SeSorteradBokning(Long[] pernsonumerPlatser, String[] NamnPlatser) {
        int[] platser = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };
        Long[] tempPPlatser = Arrays.copyOf(pernsonumerPlatser, pernsonumerPlatser.length);
        boolean Klar = false;
        long tal1;
        long tal2 = 0;
        int iteration;
        boolean Exeption = false;
        do {
            Klar = false;
            Exeption = false;
            for (int i = 0; i < tempPPlatser.length - 1; i++) {

                if (tempPPlatser[i] != 0l) {
                    tal1 = tempPPlatser[i] / 1000;
                    iteration = 0;

                    do {
                        iteration++;
                        if (iteration + i == tempPPlatser.length) {
                            Exeption = true;
                            iteration--;
                            System.out.println("max");
                        } else {
                            tal2 = tempPPlatser[i + iteration] / 1000;
                            System.out.println(tempPPlatser[i + iteration]);
                        }

                    } while (tempPPlatser[i + iteration] == 0l && Exeption == false);
                    System.out.println("igenom");

                    // System.out.println(tal1);
                    if (Exeption != true) {
                        if (tal1 > tal2) {
                            int Itemp = platser[i];
                            long temp = tempPPlatser[i];
                            platser[i] = platser[i + iteration];
                            platser[i + iteration] = Itemp;
                            tempPPlatser[i] = tempPPlatser[i + iteration];
                            tempPPlatser[i + iteration] = temp;
                            Klar = true;
                            System.out.println("iteration");
                        }
                    }
                }
            }

        } while (Klar);

        for (int i = 0; i < tempPPlatser.length; i++) {
            if (!NamnPlatser[i].equals("")) {// Mardörm, horibelt hemskt, kanshe nödigt konpliserat.
        System.out.println(
        "" + NamnPlatser[i] + " " + tempPPlatser[i] + " plats numer " + platser[i] + "");
        }
        }

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
        NP[Val] = FormateratNamn;

        System.out.print("Ange personumer:");
        while (Klar == false) {
            try {
                Pnumer = TB.nextLong();
                Klar = true;
                PnumerString = Long.toString(Pnumer);

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
        PN[Val] = Pnumer;
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
        Klar = false;
        return Val;
    }

}
