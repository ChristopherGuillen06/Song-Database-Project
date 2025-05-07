package com.Chris.SongProject;

import java.util.Comparator;
import java.util.Iterator;

public class MyList<E extends Comparable<E>> implements Iterable<E>{

	Object[] array;
	int index;
	int modCount;
	
	public MyList() {
		this.array = new Object[10];
		this.index = 0;
		this.modCount = 0;
	}
	
	public void add(E addMe) {
		if(index < array.length) {
			array[index] = addMe;
			index++;
		}else {
			Object[] newArray = new Object[array.length *2];
			for(int i = 0; i < array.length; i++) {
				newArray[i] = array[i];
			}
			newArray[index] = addMe;
			index++;
			this.array = newArray;
		}
		this.modCount++;
	}
	
	public void remove(int i) {
		if(i < 0 || i > index) {
			throw new IndexOutOfBoundsException();
		}
		for(int j = i; j < index; j++) {
			array[j] = array[j+1];
		}
		this.modCount++;
		index--;
	}
	
	public int getModCount() {
		return this.modCount;
	}
	
	public E get(int i) {
	    if (i < 0 || i >= index) { 
	        throw new IndexOutOfBoundsException();
	    }
	    return (E) array[i];
	}

	
	public boolean contains(E findMe) {
		for(int i = 0; i < index; i++) {
			if(array[i].equals(findMe)) {
				return true;
			}
		}
		return false;
	}
	
	public void printList() {
		int counter = 0;
		for(int i = 0; i < index; i++) {
			counter++;
			System.out.print(array[i] + " ");
			if(counter == 5) {
				System.out.println();
				counter = 0;
			}
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new MyListIterator<E>(this);
	}
	
	//Compare Method
	private class Compare<E extends Comparable<E>>{
		Comparator<E> comp;
		public Compare(Comparator<E> comp) {
			this.comp = comp;
		}
		
		public int compareT(E one, E two) {
			if (this.comp != null) {
				return comp.compare(one, two);	
			} else {
				return one.compareTo(two);
			}
		}
	}
	//sort method 
	public void sort() {
		Compare<E> comp = new Compare<>(null);
		bubbleSort(comp);
	}
	
	public void sort(Comparator<E> comparator) {
		Compare <E> comp = new Compare<>(comparator);
		bubbleSort(comp);
	}
	
	public int size() {
	    return index;
	}

	
	private void bubbleSort(Compare<E> comp) {
		for(int i = 0; i < index; i++) {
			for (int j = 0; j < index - 1; j++) {
				if(comp.compareT((E)array[j], (E)array[j+1]) > 0) {
					E temp = (E)array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
				
				
			}
		}
	}
}
