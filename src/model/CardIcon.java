package model;

import model.interfaces.Player;

public class CardIcon {  // this class store Card image path in array for each player
	private Player player;	// Player
	private int index;		// for adding new path
	private String[] iconName;	// array of path of each card

	public CardIcon(Player player) { //constructor
		this.player = player;
		index = 0;
		iconName = new String[4];
	}

	public Player getPlayer() { // getter
		return player;
	}

	public String[] getIconName() { //getter
		return iconName;
	}

	public void setPlayer(Player player) {//setter
		this.player = player;
	}

	public void setIconName(String name) {//setter
		if(index>=4)
			index=0;
		iconName[index++] = name;
	}

	public void reset() { //reset array
		iconName = new String[4];
		index=0;
	}

}
