import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) throws Exception {
        int val = 0;
        boolean klar = false;
        int[] bussPlatser = { 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0 };
        Long[] pernsonumerPlatser = { 200509081234l, 199605061234l, 199811213456l, 202411011234l, 197806143456l, 200809303456l, 0l, 0l, 0l, 0l, 0l, 0l,
                0l, 200102243456l, 0l, 200003091234l, 0l, 0l, 0l, 0l };
        String[] namnPlatser = { "a", "b", "c", "d", "e", "f", "", "", "", "", "",
                "", "", "g", "", "h", "", "", "", "" };
        Scanner TB = new Scanner(System.in);

        while (true) {
            val = 0;
            System.out.println("");
            System.out.println("Skriv sifran som korisponderar till alternativet.");
            System.out.println(
                    "1. Boka en plats.\n2. Se obokade platser.\n3. Se Bokning\n4. Ta bort bokning.\n5. Se vinsten av bokade platser.\n6. Se sorterad bokning.\n7. Avsluta bokningen");
            System.out.print("Ange: ");

            val = VALMETOD(klar, val, TB, 1, 7);

            switch (val) {
                case 1:
                    BokaPlats(bussPlatser, pernsonumerPlatser, namnPlatser, klar, val, TB);
                    break;
                case 2:
                    SeObokadePlatser(bussPlatser);
                    break;
                case 3:
                    SeBokning(TB, namnPlatser, pernsonumerPlatser);
                    break;
                case 4:
                    TaBortBokning(TB, namnPlatser, pernsonumerPlatser, bussPlatser);
                    break;
                case 5:
                    SeVinsten(pernsonumerPlatser);
                    break;
                case 6:
                    SeSorteradBokning(pernsonumerPlatser, namnPlatser);
                    break;
                case 7:
                    AvslutaBokning(TB);
                    break;
            }

        }

    }

    static void BokaPlats(int[] bussPlatser, Long[] pernsonumerPlatser, String[] namnPlatser, Boolean klar, int val,
            Scanner TB) {
        int antalLedigaPlatser = 0;
        for (int i = 0; i < bussPlatser.length - 1; i++) {
            if (bussPlatser[i] == 0) {
                antalLedigaPlatser++;
            }
        }

        if (antalLedigaPlatser == 0) {
            System.out.println("Ledsen det finns inga platser kvar att boka på denna buss!");
        } else {
            System.out.println("Det finns " + antalLedigaPlatser + " platser Kvar på bussen.");
            System.out.println("Vill du ha en fönsterplats?");
            System.out.println("1. Ja\n2. Nej");

            val = VALMETOD(klar, val, TB, 1, 2);

            if (val == 1) { // Fönsterplats1

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
                klar = false;
                val = 0;
                while (klar == false) {
                    try {
                        val = TB.nextInt();
                        klar = true;
                        if (val % 4 != 0 && (val - 1) % 4 != 0 || bussPlatser[val - 1] != 0) {
                            klar = false;
                            System.out.println("Ange en sifra som korispoderar till en av platserna!");
                            System.out.print("Ange: ");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Skriv bara in sifror!");
                        System.out.print("Ange: ");
                        klar = false;
                        if (TB.hasNext())
                            TB.next();
                    }
                }
                val--;
                bussPlatser[val] = 1;
                AngeInformation(val, namnPlatser, pernsonumerPlatser);

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
                klar = false;
                val = 0;
                while (klar == false) {
                    try {
                        val = TB.nextInt();
                        klar = true;

                        if (val % 2 != 0 && val % 4 == 0 || (val - 1) % 2 != 0 && val % 4 == 0
                                || bussPlatser[val - 1] != 0) {
                            klar = false;
                            System.out.println("Ange en sifra som korispoderar till en av Platserna!");
                            System.out.print("Ange: ");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Skriv bara in sifror!");
                        System.out.print("Ange: ");
                        klar = false;
                        if (TB.hasNext())
                            TB.next();
                    }
                }
                val--;
                bussPlatser[val] = 1;
                AngeInformation(val, namnPlatser, pernsonumerPlatser);

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

    static void SeBokning(Scanner TB, String[] namnPlatser, Long[] pernsonumerPlatser) {
        boolean klar = false;
        long personNumer = 0;
        String personNumerSträng;
        String inmatning = "";
        String formateratSvar;

        System.out.println("För att se din plats måste du ange antingen dit fulla namn eller personumeret.");
        System.out.println("1. namn\n2. Personumer");
        int val = VALMETOD(false, 0, TB, 1, 2);

        if (TB.hasNextLine()) {
            TB.nextLine();
        }

        if (val == 1) {
            System.out.print("Ange fullt namn: ");
            inmatning = TB.nextLine();
            formateratSvar = inmatning.replaceAll("[0123456789!@£$¤%&/{()=^¨~*'-_.:,;`+#?]", "");

            for (int i = 0; i < namnPlatser.length; i++) {
                if (namnPlatser[i].equals(formateratSvar)) {
                    klar = true;
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

            if (klar == false) {
                System.out.println("Det verkar inte finas någon bokning med det namnet!");
            }

        } else {
            System.out.print("Ange ditt personumer:");
            while (klar == false) {
                try {
                    personNumer = TB.nextLong();
                    klar = true;
                    personNumerSträng = Long.toString(personNumer);

                    if (personNumerSträng.length() != 12) {
                        klar = false;
                        System.out.println("Ditt personumer kan bara vara 12 sifror");
                        System.out.println("Ange ditt personumer:");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Skriv bara in sifror!");
                    System.out.print("Ange ditt personumer: ");
                    klar = false;
                    if (TB.hasNext())
                        TB.next();
                }
            }

            klar = false;
            for (int i = 0; i < pernsonumerPlatser.length; i++) {
                if (pernsonumerPlatser[i] == personNumer) {
                    klar = true;
                    System.out.println("Du har bokat plats numer " + i + 1 + "");
                    if (i % 2 == 0 && i % 4 != 0 || (i - 1) % 2 == 0 && i % 4 != 0) {
                        System.out.println("Det är en mitt plats");
                    } else {
                        System.out.println("Det är en fönsterplats");
                    }
                    break;
                }
            }

            if (klar == false)
                System.out.println("Det verkar inte finas någon bokning med det personumret!");

        }

    }

    static void TaBortBokning(Scanner TB, String[] namnPlatser, Long[] pernsonumerPlatser, int[] bussPlatser) {
        boolean klar = false;
        long personNumer = 0;
        String personNumerSträng;
        String inmatning;
        String formateratSvar;

        System.out.println("För att ta bort din bokning måste du antingen ange namnet för platsen eller personumeret.");
        System.out.println("1. namn\n2. Personumer");
        int val = VALMETOD(false, 0, TB, 1, 2);

        if (TB.hasNextLine()) {
            TB.nextLine();
        }

        if (val == 1) {
            System.out.print("Ange fult namn:");
            inmatning = TB.nextLine().toLowerCase();
            formateratSvar = inmatning.replaceAll("[0123456789!@£$¤%&/{()=^¨~*'-_.:,;`+#?]", "");

            for (int i = 0; i < namnPlatser.length; i++) {
                if (namnPlatser[i].equals(formateratSvar)) {
                    klar = true;
                    namnPlatser[i] = "0";
                    pernsonumerPlatser[i] = 0l;
                    bussPlatser[i] = 0;
                    System.out.println("Din bokning är nu bort tagen.");
                    break;
                }
            }

            if (klar == false) {
                System.out.println("Det verkar inte finas någon bokning med det namnet!");
            }

        } else {
            System.out.print("Ange ditt personumer:");
            while (klar == false) {
                try {
                    personNumer = TB.nextLong();
                    klar = true;
                    personNumerSträng = Long.toString(personNumer);

                    if (personNumerSträng.length() != 12) {
                        klar = false;
                        System.out.println("Ditt personumer kan bara vara 12 sifror");
                        System.out.println("Ange ditt personumer:");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Skriv bara in sifror!");
                    System.out.print("Ange ditt personumer: ");
                    klar = false;
                    if (TB.hasNext())
                        TB.next();
                }
            }

            klar = false;
            for (int i = 0; i < pernsonumerPlatser.length; i++) {
                if (pernsonumerPlatser[i] == personNumer) {
                    klar = true;
                    pernsonumerPlatser[i] = 0l;
                    namnPlatser[i] = "0";
                    System.out.println("Din bokning är nu bort tagen.");
                    break;
                }
            }

            if (klar == false)
                System.out.println("Det verkar inte finas någon bokning med det personumret!");

        }

    }

    static void SeVinsten(Long[] personumerPlatser) {
        double summa = 0;
        LocalDate dagensDatum = LocalDate.now();
        String dagensDatumSträng = dagensDatum.toString();
        dagensDatumSträng = dagensDatumSträng.replaceAll("-", "");

        for (int i = 0; i < personumerPlatser.length - 1; i++) {
            if (personumerPlatser[i] != 0) {
                Long personNumer = personumerPlatser[i];
                String personNumerSträng = personNumer.toString();

                String födelseÅr = personNumerSträng.substring(0, 8);
                int födelseÅrInt = Integer.parseInt(födelseÅr);
                int idagStrInt = Integer.parseInt(dagensDatumSträng);
                if (idagStrInt - födelseÅrInt > 180000) {
                    summa = summa + 299.90;       
                } else {
                    summa = summa + 149.90;
                }
            }
        }

        System.out.println("Intäkterna blev " + summa + "kr.");
    }

    static void SeSorteradBokning(Long[] pernsonumerPlatser, String[] namnPlatser) {
        int[] platser = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };
        Long[] temporäraPersonPlatser = Arrays.copyOf(pernsonumerPlatser, pernsonumerPlatser.length);
        boolean klar = false;
        long tal1;
        long tal2 = 0;
        int iteration;
        boolean undantag = false;
        do {
            klar = false;
            undantag = false;
            for (int i = 0; i < temporäraPersonPlatser.length - 1; i++) {

                if (temporäraPersonPlatser[i] != 0l) {
                    tal1 = temporäraPersonPlatser[i] / 1000;
                    iteration = 0;

                    do {
                        iteration++;
                        if (iteration + i == temporäraPersonPlatser.length) {
                            undantag = true;
                            iteration--;
                        } else {
                            tal2 = temporäraPersonPlatser[i + iteration] / 1000;
                        }

                    } while (temporäraPersonPlatser[i + iteration] == 0l && undantag == false);

                    if (undantag != true) {
                        if (tal1 > tal2) {
                            int behållareInt = platser[i];
                            long behållareLong = temporäraPersonPlatser[i];
                            platser[i] = platser[i + iteration];
                            platser[i + iteration] = behållareInt;
                            temporäraPersonPlatser[i] = temporäraPersonPlatser[i + iteration];
                            temporäraPersonPlatser[i + iteration] = behållareLong;
                            klar = true;
                        }
                    }
                }
            }

        } while (klar);

        for (int i = 0; i < temporäraPersonPlatser.length; i++) {
            if (!namnPlatser[i].equals("")) {// Mardörm, horibelt hemskt, kanshe nödigt konpliserat.
        System.out.println(
        "" + namnPlatser[i] + " " + temporäraPersonPlatser[i] + " plats numer " + platser[i] + "");
        }
        }

    }

    static void AvslutaBokning(Scanner TB) {
        TB.close();
        System.exit(0);
    }

    static void AngeInformation(int val, String[] namnPlatser, Long[] personNumerPlatser) {
        Scanner TB = new Scanner(System.in);
        boolean klar = false;
        long personNumer = 1;
        String personNumerSträng = "";
        String namn = null;
        String formateratNamn = null;

        System.out.println("För att boka en plats måste du ange dit fulla namn och personumer.");
        System.out.println("Ange namn:");

        namn = TB.nextLine().toLowerCase();
        formateratNamn = namn.replaceAll("[0123456789!@£$¤%&/{()=^¨~*'-_.:,;`+#?]", "");
        namnPlatser[val] = formateratNamn;

        System.out.print("Ange personumer:");
        while (klar == false) {
            try {
                personNumer = TB.nextLong();
                klar = true;
                personNumerSträng = Long.toString(personNumer);

                if (personNumerSträng.length() != 12) {
                    klar = false;
                    System.out.println("Ditt personumer kan bara vara 12 sifror");
                    System.out.println("Ange personumer:");
                }

            } catch (InputMismatchException e) {
                System.out.println("Skriv bara in sifror!");
                System.out.print("Ange personumer: ");
                klar = false;
                if (TB.hasNext())
                    TB.next();
            }
        }
        personNumerPlatser[val] = personNumer;
    }

    static int VALMETOD(boolean klar, int val, Scanner TB, int Minstval, int Störstval) {
        klar = false;
        val = 0;
        while (klar == false) {
            try {
                val = TB.nextInt();
                klar = true;
                if (val < Minstval || val > Störstval) {
                    klar = false;
                    System.out.println("Ange en sifra som korispoderar till en av valen!");
                    System.out.print("Ange: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Skriv bara in sifror!");
                System.out.print("Ange: ");
                klar = false;
                if (TB.hasNext())
                    TB.next();
            }
        }
        klar = false;
        return val;
    }

}
