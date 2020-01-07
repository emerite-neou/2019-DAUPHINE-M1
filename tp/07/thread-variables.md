# Pattern Singleton

le pattern singleton permet de n'instancié une class qu'une seule fois. 
Le principe est de garder en mémoire cette instanciation et de n'utiliser que celle-là.

### Simple Singleton 

1. Créer une class Connection vide.
2. Comment empecher une utilisateur d'utiliser le constructeur de la class Connection?
3. Ajoutez une method static getConnection qui renvoie un Connection. La method doit toujours renvoyer la même instance.

### Lazy Singleton

1. Si ce n'est pas déjà fait arrangez-vous pour Connection ne soit instancié que si on en a besoin (ie getConnection est appelé).
2. Testez votre class: Lancer deux threads appelant getConnection et comparer les valeurs envoyé (executez votre code plusieurs fois).

### ThreadSafe Lazy Singleton

1. Votre code actuel est-il thread safe? Que se passe-t-il si deux thread éxecute getConnection en même temps pour la première fois?



# Produceurs/consommateurs
On a des thread produceurs qui créent des variables et des thread consommateurs qui utilisent ses variables.
Le but de cet exercise est de créer une queue qui permet l'ecriture et la lecture de ses variables de manière thread safe
(ie réécrire java.util.concurrent.BlockingQueue).

### Gérer une Queue Pleine avec des Exeptions

1. Ecrire la class ThreadSafeQueue, son construteur doit prendre le nombre maximum d'éléments que peut contenir la class. 
2. Ajoutez la method add qui ajoute un élément à la queue. La method doit lancer une execption si la queue est pleine.
3. Ajoutez la method remove qui retire un element de la queue et le renvoie. La method doit lancer une execption si la queue est pleine.

### Gérer une Queue Pleine en bloquant

1. Ajoutez la method put qui ajoute un élément à queue. La method doit être bloquante si la queue est pleine.
2. Ajoutez la method take qui retire un element de la queue et le renvoie. La method doit être bloquante si la queue est vide.
3. Assurez-vous vos method put/take soient les moins bloquantes possibles.

### Avec des Locks
1. Comment crée une condition à partir d'un Lock.
2. Quel est l'avantage d'utiliser les methods await et signal plutot que wait et notify.
3. Ecrivez une nouvelle class ThreadSafeQueue avec des Locks et des Conditions.
4. Testez votre code. 
