On rappele l'existance des functional interfaces suivantes :
- BiFunction<T,U,R>, Represents a function  (R apply(T t, U u)) that accepts two arguments and produces a result
- Consumer<T>, Represents an operation (void	accept(T t)) that accepts a single input argument and returns no result.
- Function<T,R>, Represents a function (R	apply(T t)) that accepts one argument and produces a result.
- Predicate<T>, Represents a predicate (boolean	test(T t))  of one argument.
- Supplier<T>, Represents a supplier (T	get()) of results.

De plus on on rappele les methods suivantes de la class Stream :
- <R> R	collect(Supplier<R> supplier, BiConsumer<R,? super T> accumulator, BiConsumer<R,R> combiner)
- Stream<T>	filter(Predicate<? super T> predicate)
- <R> Stream<R>	map(Function<? super T,? extends R> mapper)
- T	reduce(T identity, BinaryOperator<T> accumulator)
- <U> U	reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)


# Jeu de la Vie
Le jeu de la vie est une simutation qui se déroule sur une grille à deux dimensions de Cellules. 
A un temps t, chaque cellule peut être soit morte ou vivante.  
Au temps t+1 les cellules évoluent de la manière suivante :
- Une cellule morte possédant exactement trois voisines vivantes devient vivante
- Une cellule vivante possédant deux ou trois voisines vivantes le reste, sinon elle meurt.

1. Développer la/les classes représentant une Cellule.  On doit pouvoir connaitre l'état actuel de la cellule et son état suivant.
2. Entre deux cellules mortes il n'y a pas de différence, Il n'y a donc pas de raison d'instancier  plusieurs fois des cellules mortes. 
Même constat entre deux cellules vivantes. Modifiez votre code en prenant en compte ces remarques pour qu'il soit mieux optimissé en memoire.

#  Mutable Identity
Soit la method suivante de la class Stream :
```java
public T reduce(T identity, BinaryOperator<T> accumulator) {
     T result = identity;
     for (T element : this.stream)
         result = accumulator.apply(result, element)
     return result;
}
```
1. Pourquoi la variable identity doit être immutable?
2. Implémentez une solution où la class T est mutable. La method doit s'appeler collect.
3. On essaie d'écrire une version de collect en parallele. On a obtenu cela:
```java
public T collect(T identity, BinaryOperator<T> accumulator, BinaryOperator<T> combiner) {
    this.stream
      .split()
      .map(subStream -> subStream.reduce(T, accumulator))
      // then combine
}
```
Quelle est le problème avec la variable identity? 
Comment le résoudre? Modifiez le code de la question précédente avec votre solution?
- *Si vous pensez n'avoir pas de problème indiquez le*

# Manipulation de Stream
Soit la Class suivante
```java
public class Consultant {    
  public String id;    
  public int salaireAnnuel; // Ce que l'esn paie à son consultant    
  public int TJM; // Ce que le consultant rapport par jour travaillé à son esn   
  ...
} 
```
Ecrivez une method qui prend un Stream de Consultant : 
1. et qui renvoie un Stream de consultant où tous les consultants sont augmentés de 10%.
2. et qui renvoie un Stream de consultant qui sont rentable (en considérant qu'un consultant travaille 200 jours par an.)
4. et qui renvoie le bénéfice de ces consultants.

# SlowVal
Crée une class thread safe SlowVal qui permet à une thread d'initialisé un String et à d'autres de récupérer cette variables.
Par exemple:
```java
SlowVal<String> s = new Stream<>();

new Thread(() ->  {
  s.set("coucou");
}).run();

new Thread(() ->  {
  System.out.println(s.get());
}).run();
```
la method get doit être bloquante tant que la method set n'a pas été appelé. Les methods doivent être le moins bloquant possible.
