package model;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard{
	private Suit suit;
	private Value value;
	private int score;
	
	public PlayingCardImpl(Suit suit,Value value,int score) {
		this.suit=suit;
		this.value=value;
		this.score=score;
	}
	
	@Override
	public Suit getSuit() {
		return suit;
	}

	@Override
	public Value getValue() {
		return value;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public boolean equals(PlayingCard card) {
		if(card.getSuit().equals(suit) && 
		   card.getScore() == score &&
		   card.getValue().equals(value))return true;
		return false;
	}

	@Override
	public String toString() {
		return "suit=" + suit + ", value=" + value + ", score=" + score ;
	}
	
	

}
