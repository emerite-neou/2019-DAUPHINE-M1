package fr.dauphine.game.allumette;

public class Runner {

    private static Player getPlayer(String s) {
        if (s.equals("ia1")) return new DumbIA();
        else if (s.equals("ia2")) return new SmartIA();
        else if (s.equals("humain")) return new Human();
        else throw  new IllegalArgumentException(s+" is not handled");
    }

    private static void printGameIngo(int n, String player) {
        System.out.println("Il reste "+n);
        System.out.println(player+" doit choisir");

    }

    public static void main(String[] args) {
        int nbrMatches = Integer.parseInt(args[0]);
        Player p1 = getPlayer(args[1]);
        Player p2 = getPlayer(args[2]);
        int lastPlayer = 0;

        while (nbrMatches > 0) {
            printGameIngo(nbrMatches, "P1");
            int val = p1.choseANumber(nbrMatches);
            nbrMatches -= val;
            lastPlayer = 1;

            if ( nbrMatches <= 0 ) break;

            printGameIngo(nbrMatches, "P2");
            int val2 = p2.choseANumber(nbrMatches);
            nbrMatches -= val;
            lastPlayer = 2;
        }

        if (lastPlayer == 1)  System.out.println("joueur 2 gagne");
        else System.out.println("joueur 1 gagne");
    }

}
