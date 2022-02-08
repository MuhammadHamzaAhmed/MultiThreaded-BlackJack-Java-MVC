package model;

import model.interfaces.PlayingCard;

public class Node {
	PlayingCard card;
	Node next;
	public Node(PlayingCard card,Node next) {
		this.card = card;
		this.next = next;
	}
}
