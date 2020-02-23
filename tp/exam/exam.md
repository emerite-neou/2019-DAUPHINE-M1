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
```java
public interface Cell {
	public boolean isAlive();
	public Cell getNextState(int aliveNeighbours);
}

public class AliveCell implements Cell {
	@Override
	public boolean isAlive() {
		return true;
	}

	@Override
	public Cell getNextState(int aliveNeighbours) {
		if (aliveNeighbours == 2 || aliveNeighbours == 3) return new AliveCell();
		else return new DeadCell();
	}
}

public class DeadCell implements Cell {	
	@Override
	public boolean isAlive() {
		return false;
	}

	@Override
	public Cell getNextState(int aliveNeighbours) {
		if (aliveNeighbours == 3) return new AliveCell();
		else return new DeadCell();
	}
}	

```


2. Entre deux cellules mortes il n'y a pas de différence, Il n'y a donc pas de raison d'instancier  plusieurs fois des cellules mortes. 
Même constat entre deux cellules vivantes. Modifiez votre code en prenant en compte ces remarques pour qu'il soit mieux optimissé en memoire.
```java
public interface Cell {
	public boolean isAlive();
	public Cell getNextState(int aliveNeighbours);
	
	public static Cell getDeadCell() {
		return DeadCell.getDeadCell();
	}
	
	public static Cell getAliveCell() {
		return AliveCell.getAliveCell();
	}
}

class AliveCell implements Cell {
	private final static AliveCell myAliveCell = new AliveCell();
	
	private AliveCell() {};
	
      /*default visibility on purpose*/
	static Cell getAliveCell() {
		return myAliveCell;
	}
	
	@Override
	public boolean isAlive() {
		return true;
	}

	@Override
	public Cell getNextState(int aliveNeighbours) {
		if (aliveNeighbours == 2 || aliveNeighbours == 3) return myAliveCell;
		else return Cell.getDeadCell();
	}
}

 class DeadCell implements Cell {
	private final static DeadCell myDeadCell = new DeadCell();
	
	private DeadCell() {};
	
	/*default visibility on purpose*/
	public static Cell getDeadCell() {
		return myDeadCell;
	}

			
	@Override
	public boolean isAlive() {
		return false;
	}

	@Override
	public Cell getNextState(int aliveNeighbours) {
		if (aliveNeighbours == 3) return Cell.getAliveCell();
		else return new DeadCell();
	}
}	
```


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
```
Car on peut l a modifié dans l'argument accumulator
```

2. Implémentez une solution où la class T est mutable. La method doit s'appeler collect.
```java
public T collect(T identity, BiConsumer<T,T> accumulator) {
     T result = identity;
     for (T element : this.stream)
         // pas besoin de faire l'affectation car on peut modifier directement la variable  
         // et comme on n'a pas besoin de renvoyer de valeur on utiliser un BiConsumer et non un BinaryOperator
         accumulator.accept(result, element)
     return result;
}
```


3. On essaie d'écrire une version de collect en parallele. On a obtenu cela:
```java
public T collect(T identity, BinaryOperator<T> accumulator, BinaryOperator<T> combiner) {
    this.stream
      .split()
      .map(subStream -> subStream.reduce(identity, accumulator))
      // then combine
}
```
Quelle est le problème avec la variable identity? 
```
la variable identity est modifié, donc apres le calcul du premier split les caculs ne sont plus bon.

le code de collect peut être derouler de la manirere suivante 
splits = this.stream.split()
splits[0].reduce(identity, accumulator)
// ici la valeur identity n'est plus forcement la même qu'au debut de la method 
// car splits[0].reduce(identity, accumulator) l a  p-e modifie
splits[1].reduce(identity, accumulator)
```

Comment le résoudre? Modifiez le code de la question précédente avec votre solution.
- *Si vous pensez n'avoir pas de problème indiquez le*
```java
public T collect(Supplier<T> supplier, BiConsumer<T,T> accumulator) {
     T result = supplier.get();
     for (T element : this.stream)
         accumulator.accept(result, element)
     return result;
}
```


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
```java
	public static Stream<Consultant> raiseAll(Stream<Consultant> consultants) {
		return consultants
				.map(consultant -> {
					consultant.salaireAnnuel *= 1.1;
					return consultant;
				})
		;
	}
```

2. et qui renvoie un Stream de consultant qui sont rentable (en considérant qu'un consultant travaille 200 jours par an.)
```java
	public static Stream<Consultant> moneyMakers(Stream<Consultant> consultants) {
		return consultants
				.filter(consultant -> {
					return consultant.TJM*200 < consultant.salaireAnnuel;
				})
		;
	}	
```

3. et qui renvoie le bénéfice de ces consultants.
```java
public static int money(Stream<Consultant> consultants) {
	return consultants
			.map(consultant -> consultant.salaireAnnuel - consultant.TJM*200)
			.reduce(0, (a,b)-> a+b)
	;
}	
```
# SlowVal
Crée une class thread safe SlowVal qui permet à une thread d'initialisé un String et à d'autres de récupérer cette variables.
Par exemple:
```java
SlowVal<String> s = new SlowVal<>();

new Thread(() ->  {
  s.set("coucou");
}).start();

new Thread(() ->  {
  System.out.println(s.get());
}).start();
```
la method get doit être bloquante tant que la method set n'a pas été appelé. Les methods doivent être le moins bloquant possible.
```java
// avec synchornised
public class SlowVal {
	private String val = null;
	private final Object lock = new Object();
	
	public void set(String val) {
		if ( val == null ) {
			synchronized (lock) {
				if ( val != null ) throw new IllegalStateException();
				this.val = val;
				lock.notify();
			}
		} else {
                  throw new IllegalStateException();
            }
	}

	public String get() throws InterruptedException {
		if ( val == null ) {
			synchronized (lock) {
				while(val == null) lock.wait();
			}
		}
		return val;
	}	
}
```


```java
// avec des reentrantlock
public class SlowVal {
	private String val = null;

	private final ReentrantLock lock = new ReentrantLock();
	private final Condition isSet = lock.newCondition();
	
	
	public void set(String val) {
		if ( this.val == null ) {
			lock.lock();
			if ( this.val != null ) throw new IllegalStateException();
			this.val = val;
			isSet.notify();
			lock.unlock();
		} else {
                  throw new IllegalStateException();
            }
	}

	public String get() throws InterruptedException {
		if ( this.val == null ) {
			lock.lock();
			while(this.val == null) isSet.wait();
			lock.unlock();
		}
		return val;
	}	
}
```

