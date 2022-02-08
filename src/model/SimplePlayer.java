package model;

import model.interfaces.Player;

public class SimplePlayer implements Player{
	private String playerID;
	private String name;
	private int point;
	private int bet;
	private int result;
	
	public SimplePlayer(String playerID, String name, int point) {
		this.playerID=playerID;
		this.name=name;
		this.point=point;
	}

	@Override
	public String getPlayerName() {
		return name;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.name=playerName;
	}

	@Override
	public int getPoints() {
		return point;
	}

	@Override
	public void setPoints(int points) {
		this.point=points;
	}

	@Override
	public String getPlayerId() {
		return playerID;
	}

	@Override
	public boolean setBet(int bet) {
		if(bet>=0) {
			this.bet=bet;
			return true;
		}
		return false;
	}

	@Override
	public int getBet() {
		return bet;
	}

	@Override
	public void resetBet() {
		this.bet=0;
	}

	@Override
	public int getResult() {
		return result;
	}

	@Override
	public void setResult(int result) {
		this.result=result;
	}

	@Override
	public boolean equals(Player player) {
		return this.playerID.equals(player.getPlayerId());
	}

	@Override
	public int compareTo(Player player) {
		return player.getPlayerId().compareTo(this.playerID);
	}

	@Override
	public String toString() {
		return "playerID=" + playerID + ", name=" + name + ", point=" + point + ", bet=" + bet
				+ ", result=" + result ;
	}
	
	
}
