# Diner des Philosophes

![Diner des Philosophes](https://upload.wikimedia.org/wikipedia/commons/thumb/7/7b/An_illustration_of_the_dining_philosophers_problem.png/800px-An_illustration_of_the_dining_philosophers_problem.png)

On suppose une table ronde, avec 5 convives. 
Chacun a un plat de spaghettis devant lui. 
Entre chaque plat, il y a une fourchette. 
Un convive peut manger son plat en prenant deux fourchettes, celle à sa gauche et celle à sa droite. 
Voici un code qui simule un diner :
```java
public class Diner {
	private final ReentrantLock[] forks;

	public Diner(int nbPlates) {
		forks = new ReentrantLock[nbPlates];
		for (int i = 0; i < nbPlates; ++i) forks[i] = new ReentrantLock();
	}

	public void eat(int guyIndex) {
		ReentrantLock forkLeft = forks[guyIndex];
		ReentrantLock forkRight = forks[(guyIndex + 1) % forks.length];

		forkLeft.lock(); // essaie de prend la fourchette a gauche
		try {
			Thread.sleep(5);// minisieste
			forkRight.lock(); // essaie de  prend la fourchette a gauche
			try {
        // les deux fourchettes sont en mains, on peut manger
				System.out.println("Guy " + guyIndex + " is eating.");
			} finally {
				forkRight.unlock(); // repose la fourchette de droite
			}
		} catch (InterruptedException e) {
		} finally {
			forkLeft.unlock(); // repose la fourchette de gauche
		}
	}



	public static void main(String[] args) {
		int nbGuys = 5;
		Diner d = new Diner(nbGuys);
		for (int i = 0; i < nbGuys; ++i) {
			final int guy = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					d.eat(guy);
				}
			}).start();
		}
	}
}
```
1. Quelle est le problème avec le code suivant?
2. Corrigez le code 
   - En utilisant un tryLock (indiquer combien il a fallu d’essais infructueux avant qu’un convive ait pu commencer à manger). Essayez de réduire le nombre d'essais.
   - En modifiant l'ordre dans lequel les fourchettes sont utilisées. 
