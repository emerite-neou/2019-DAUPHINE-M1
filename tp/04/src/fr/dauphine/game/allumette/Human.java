package fr.dauphine.game.allumette;

import java.util.Scanner;

public class Human implements Player {
    @Override
    public int choseANumber(int n) {
        int res;
        Scanner in = new Scanner(System.in);

        do {
            res = in.nextInt();
        } while (res < 0 || res > 3);
        return res;
    }
}
