package observer;

import java.util.LinkedList;
import java.util.List;

public class Observable {
	List<Observer> observers;
	
	public Observable() {
		this.observers = new LinkedList<Observer>();
	}
	
	/**
	 * indique qu'il doit être redessiné
	 */
	public void setChanged() {
		this.notifyObservers();
	}
	
	public void addObserver(Observer o) {
		this.observers.add(o);
	}
	
	//public void removeObserver(Observer o);
	
	public void notifyObservers() {
		for(Observer o: this.observers) {
			
		}
	}
}
