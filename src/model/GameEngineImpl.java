package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {
	private ArrayList<Player> list;
	private GameEngineCallback view;
	Stack card;
	public GameEngineImpl() {
		list = new ArrayList<>();
		card=new Stack();
		getShuffledHalfDeck();
	}

	@Override
	public void dealPlayer(Player player, int delay) throws IllegalArgumentException {
		int result=0;
		PlayingCard c1=card.pop();
		PlayingCard c2=card.pop();
		while(true) {
			if(card.size()<5) card=getShuffledHalfDeck();
			result+=c1.getScore();
			if(result+c2.getScore()>=GameEngine.BUST_LEVEL) {
				view.bustCard(player, c1, this);
				break;
			}
			view.nextCard(player, c1, this);
			c1=c2;
			c2=card.pop();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		player.setResult(result);
		view.result(player, player.getResult(), this);
	}

	@Override
	public void dealHouse(int delay) throws IllegalArgumentException {
		
		int result=0;
		PlayingCard c1=card.pop();
		PlayingCard c2=card.pop();
		while(true) {
			if(card.size()<5) card=getShuffledHalfDeck();
			result+=c1.getScore();
			if(result+c2.getScore()>=GameEngine.BUST_LEVEL) {
				view.houseBustCard(c1, this);
				break;
			}
			view.nextHouseCard(c1, this);
			c1=c2;
			c2=card.pop();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		view.houseResult(result,null);
		view.houseResult(result, this);
		for(Player x:list) {
			applyWinLoss(x, result);
		}
		
	}

	@Override
	public void applyWinLoss(Player player, int houseResult) {
		if(player.getResult()>houseResult) 
			player.setPoints(player.getPoints()+player.getBet());
		else if(player.getResult()<houseResult)
			player.setPoints(player.getPoints()-player.getBet());
		player.resetBet();
	}

	@Override
	public void addPlayer(Player player) {
		for (Player x : list) {
			if (x.getPlayerId().equals(player.getPlayerId()) ||
					x.getPlayerName().equals(player.getPlayerName())) {
				x.setBet(player.getBet());
				x.setPlayerName(player.getPlayerName());
				x.setPoints(player.getPoints());
				x.setResult(player.getResult());
				return;
			}
		}
		list.add(player);
	}

	@Override
	public Player getPlayer(String id) {
		for (Player x : list) {
			if (x.getPlayerId().equals(id)) {
				return x;
			}
		}
		return null;
	}

	@Override
	public boolean removePlayer(Player player) {
		return list.remove(player);
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		return player.setBet(bet);
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		view=gameEngineCallback;
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.view=null;
		return this.view!=null;
	}

	@Override
	public Collection<Player> getAllPlayers() {
		return list;
	}

	@Override
	public Stack getShuffledHalfDeck() {
		List<PlayingCard> lis=new ArrayList<>();
		int i=0;
		int j=0;
		PlayingCard.Suit suit[]= {PlayingCard.Suit.CLUBS,PlayingCard.Suit.DIAMONDS,PlayingCard.Suit.HEARTS,
				PlayingCard.Suit.SPADES};
		PlayingCard.Value value[]= {PlayingCard.Value.EIGHT,PlayingCard.Value.NINE,PlayingCard.Value.TEN,
									PlayingCard.Value.JACK,PlayingCard.Value.QUEEN,PlayingCard.Value.KING,
									PlayingCard.Value.ACE};
		int score[]= {8,9,10,10,10,10,11};
		while(i<4) {
			j=0;
			while(j<7) {
				lis.add(new PlayingCardImpl(suit[i], value[j], score[j]));
				j++;
			}
			i++;
		}
		Collections.shuffle(lis);
		for(PlayingCard x:lis) {
			card.push(x);
		}
		return card;
	}

}
