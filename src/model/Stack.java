package model;

import model.interfaces.PlayingCard;

public class Stack {
	Node top;
	int size;
	
	public void push(PlayingCard card) {
		top = new Node(card, top);
		size++;
	}
	
	public PlayingCard pop() {
		if(top == null){
			return null;
		}
		PlayingCard card = top.card;
		top = top.next;
		size--;
		return card;
	}
	
	public boolean isEmpty() {
		if(top == null)
			return true;
		return false;
	}
	
	public int size() {
		return size;
	}
}
