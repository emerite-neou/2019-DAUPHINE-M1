package fr.dauphine.collections;

public class BST <E extends Comparable<E>>{
	private final E val;
	private BST<E> lc;
	private BST<E> rc;
	
	public BST(E e) {
		this.val = e;
		lc = null;
		rc = null;
	}
	
	public void add(E e) {
		if (val.compareTo(e) <= 0 && lc == null) lc = new BST<E>(e);
		else if (val.compareTo(e) <= 0 && lc != null) lc.add(e);
		else if (val.compareTo(e) > 0 && rc == null) rc = new BST<E>(e);
		else if (val.compareTo(e) > 0 && rc != null) rc.add(e);
	}
	
	public static void printInc(BST<?> bst) {
		if (bst.lc != null) printInc(bst.lc);
		System.out.println(bst.val);
		if (bst.rc != null) printInc(bst.rc);
	}
	
	public static void printDec(BST<?> bst) {
		if (bst.rc != null) printDec(bst.rc);
		System.out.println(bst.val);
		if (bst.lc != null) printDec(bst.lc);
	}

}
