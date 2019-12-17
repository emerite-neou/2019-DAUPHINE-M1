# Reduce

### Echauffement

Avec une boucle for simple :
1. Ecrivez une method qui prend une liste de d'entier positif et renvoie le nombre maximum.
2. Ecrivez une method qui prend une liste de d'entier positif et renvoie la somme.
3. Ecrivez une method qui prend une liste de d'entier positif et renvoie la somme des carrés.
4. Ecrivez une method qui prend une liste de d'entier positif et renvoie la somme des inverses.
5. Vous avez surement utilisé la method get, que peut être le "problème" si on laisse n'importe quel type de list en parametre?

### Generalisation du Pattern : la Method Reduce

1. Quelle est le soucis avec les methods précédentes? Quelle est la partie variable du code? 
Arrangez-vous pour passer cette partie en parametre d'une method pour géneraliser ce pattern.
2. En fait il y a une autre partie qui peut être variable (mais pas dans les exemple précédent): la valeur de départ. Mettez-la en parametre.
3. Réecrivez les methods de l'echauffement pour testez votre solution.


### Parallelisation 

#### Pour Commencez avec les Threads
1. Partez de votre première version pour calculer une somme. 
   - Modifiez votre code pour que la boucle soit faite dans une thread. Comment executer la thread puis attendre la fin de son éxecution?
   - Quelle est le problème avec votre variable d'accumulation? Comment reglez ce problème?
   - Affichez la somme pour vérifier le résultat.
2. Au lieu de calculer la somme dans une seule thread, utilisez deux threads: 
une calcul la première moitié et l'autre la second, quand les deux thread ont fini combinez les deux résultats. 
*Le temps d'éxecution ne sera p-e pas meilleur*
3. Utilisez des thread n'est pas vraiment propre (à cause des variables d'accumulations).
Un ExecutorService à une method submit qui permet d'éxecuter un Callable (une function qui renvoie une valeur).
Modifiez votre code pour utilisez un ExecutorService, les methods de la class Executors permettent de créer un ExecutorService.
*Note on pourrait aussi créer une class qui implémente Runnable et qui a une method get*

#### Parallelisation de Reduce
1. Modifiez votre method reduce pour que le parcours de la liste soit divisé en deux. Comment combinez les deux résultats obtenu?
2. Pourquoi faire 2 threads alors qu'on pourrait en faire n? 
Modifiez votre code pour que les thread ne gère que des listes de N éléments maximum.


# Collect

1. Avec une boucle for, ecrivez une method qui prend une liste de d'entier qui renvoie une list ne contenant que les nombre pair.
2. Même question mais en utilisant votre method reduce. Une list est-elle mutable? 
Quelle opération dans votre method reduce est maintenant inutile?



