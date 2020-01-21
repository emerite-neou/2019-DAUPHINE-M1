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

1. Rendez votre class thread safe.

# Produceurs/consommateurs

## Probleme 

### Produceur
1. Ecrivez et executez dans un main (sans passer par des classes) une thread qui affiche périodiquement hello.
2. On souhaite maintenant avoir d'autres threads affichant un message. Modifiez votre code pour avoir une seconde thread. On doit pouvoir différencier les threads par leur message (soit les threads affichent un message différent, soit les threads affichent un id).
Votre code ne doit pas contenir de copier/coller.
3. Modifiez votre code pour que la périodicité des messages soient paramétrable.

### Queue vs List
On souhaite séparer la création du message et son affichage (pour simuler la reception d'une requete HTML et son traitement par exemple). Le principe est que des threads vont mettre des messages dans un buffer et que d'autres threads vont lire ce buffer (et afficher les messages).
1. Quelles types d'objet peuvent faire office de buffer? 
2. On veut traiter les éléments dans leur ordre d'insertion (le premier message a être affiché doit être le premier message inséré, etc...). Quelle type d'objet est le plus adapter?

### Consommateur
1. Modifiez votre code pour que vos threads n'affichent plus de messages mais qu'elles les inserent dans une ArrayBlockingQueue.
2. Ajoutez une thread qui retire un message de votre ArrayBlockingQueue et qui les affichent.
3. Modifiez le nombre de threads produceurs et de threads consommatteurs
4. Créez des class pour vos deux types de threads en implémentant Runnable (et pas en extends Thread).

## ThreadSafeQueue
On a des thread produceurs qui créent des variables et des thread consommateurs qui utilisent ses variables.
Le but de cet exercise est de créer une queue qui permet l'ecriture et la lecture de ses variables de manière thread safe
(ie réécrire java.util.concurrent.BlockingQueue).

### Gérer une Queue Pleine avec des Exeptions

1. Ecrire la class ThreadSafeQueue, son construteur doit prendre le nombre maximum d'éléments que peut contenir la class. 
2. Ajoutez la method add qui ajoute un élément à la queue. La method doit lancer une execption si la queue est pleine.
3. Ajoutez la method remove qui retire un element de la queue et le renvoie. La method doit lancer une execption si la queue est vide.

### Gérer une Queue Pleine en bloquant

1. Ajoutez la method put qui ajoute un élément à queue. La method doit être bloquante si la queue est pleine.
2. Ajoutez la method take qui retire un element de la queue et le renvoie. La method doit être bloquante si la queue est vide.
3. Assurez-vous vos method put/take soient les moins bloquantes possibles.

### Avec des Locks
1. Comment crée une condition à partir d'un Lock.
2. Quel est l'avantage d'utiliser les methods await et signal plutot que wait et notify.
3. Ecrivez une nouvelle class ThreadSafeQueue avec des Locks et des Conditions.
4. Testez votre code. 

# Exchanger
On souhaite crée une class thread safe que deux threads peuvent se partager pour échanger deux variables.
Par exemple le code suivant :
```java
Exchanger<String> e = new Exchanger();

new Thread(() -> {
  try {
    System.out.println("t1 recoit un cadeau de " + e.exchange("t1"));
  } catch (InterruptedException e1) {
    e1.printStackTrace();
  }
}).start();

new Thread(() -> {
  try {
    System.out.println("t2 recoit un cadeau de " + e.exchange("t2"));
  } catch (InterruptedException e1) {
    e1.printStackTrace();
  }  
}).start();
```
doit afficher 
```
t2 recoit un cadeau de t1
t1 recoit un cadeau de t2
```
ou
```
t1 recoit un cadeau de t2
t2 recoit un cadeau de t1
```


1. Créez la class Exchanger. Assurez-vous que la method exchange soit la moins bloquand possible. 
Pour rendre les choses plus facile la class est a utilisation unique (on ne peut appeler exchange que 2 fois).
De plus si on exchange le même object la method ne fonctionne pas.
