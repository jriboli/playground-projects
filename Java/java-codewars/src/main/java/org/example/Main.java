package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {

            // Press Shift+F9 to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Ctrl+F8.
            System.out.println("i = " + i);
        }

        DescendingOrder_7kyu test = new DescendingOrder_7kyu();
        //System.out.println(test.sortDesc(35987));
        //System.out.println(test.sortDesc(865497321));

        CountCharacters_6kyu test2 = new CountCharacters_6kyu();
        //System.out.println(test2.count("aaab
        // bbypoukn"));
        //System.out.println(test2.count(""));

        MexicanWave_6kyu test3 = new MexicanWave_6kyu();
        //System.out.println(test3.wave("hello"));
        //System.out.println(test3.wave("two words"));

        PickPeaks_5kyu test4 = new PickPeaks_5kyu();
        System.out.println(test4.getPeaks(new int[]{1,2,3,6,4,1,2,3,2,1}));
        System.out.println(test4.getPeaks(new int[]{3,2,3,6,4,1,2,3,2,1,2,3}));
        System.out.println(test4.getPeaks(new int[]{3,2,3,6,4,1,2,3,2,1,2,2,2,1}));
        System.out.println(test4.getPeaks(new int[]{2,1,3,1,2,2,2,2,1}));
    }
}