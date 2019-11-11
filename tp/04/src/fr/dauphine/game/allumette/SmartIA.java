package fr.dauphine.game.allumette;

public class SmartIA implements Player {

    @Override
    public int choseANumber(int n) {
        int res = n%4;
        if (res == 0) return 1;
        else return res;
    }
}
