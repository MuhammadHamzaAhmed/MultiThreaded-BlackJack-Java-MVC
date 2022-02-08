package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackGUI;

public class Controller implements ActionListener {
	private GameEngineImpl engine;					// Model
	private GameEngineCallbackGUI gui;				// View
	private ArrayList<Player> bustedPlayer;			// busted Player list

	public Controller() {
		this.gui = new GameEngineCallbackGUI(this);
		this.engine = new GameEngineImpl();
		engine.addGameEngineCallback(gui);
		bustedPlayer = new ArrayList<Player>();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.equals(gui.getFrame().getAddPlayer())) {   // add player button action
			addPlayer();
		} else if (button.equals(gui.getFrame().getBet())) {   // set bet button action
			setBet();
		} else if (button.equals(gui.getFrame().getClearBet())) {   // Clear bet button action
			clear();
		} else if (button.equals(gui.getFrame().getNextCard())) {   // deal player button action
			setNextCard();
		} else if (button.equals(gui.getFrame().getResult())) {   // result button action
			result();
		} else if (button.equals(gui.getFrame().getCheck())) {   // summary check button action
			summary();
		} else if (button.equals(gui.getFrame().getRemove())) {  // remove player button action
			remove();
		} else if (button.equals(gui.getFrame().getStart())) {   // start game button
			start();
		}
	}

	private void start() {
		ArrayList<Player> lis = (ArrayList<Player>) engine.getAllPlayers();									// List of player
		for (Player play : lis) {																			// Iterating through list
			if (play.getBet() == 0) {																		// checking Bet
				gui.displayMsg("Place Bet for Player: " + play.getPlayerName());							// bet not placed
				return;																						// returning method
			}
			play.setResult(0);																				// setting player result to 0
		}
		gui.getFrame().getNextCard().setVisible(true);														// displaying Next card button
		gui.getFrame().getResult().setVisible(true);														// displaying result button
		gui.getFrame().getStart().setVisible(false);														// hiding start button
		gui.getFrame().getAddPlayer().setVisible(false);													// hiding add player button
		gui.getFrame().getRemove().setVisible(false);														// hiding remove button
		gui.getFrame().getBet().setVisible(false);															// hiding set bet button
		gui.getFrame().getClearBet().setVisible(false);														// hiding clear bet button
		bustedPlayer = new ArrayList<>();																	// initializing new list
	}

	private void remove() {
		String name = (String) gui.getFrame().getPlayer().getSelectedItem();								// Selected Player in combo box
		ArrayList<Player> lis = (ArrayList<Player>) engine.getAllPlayers();									// list of player
		Player player = null;																				// initializing player to null
		for (Player play : lis) {																			// iterating through loop
			if (play.getPlayerName().equals(name)) {														// checking player
				player = play;																				// initializing player
				break;																						// breaking loop
			}
		}
		if (player != null) {																				// checking player
			lis.remove(player);																				// removing player
			bustedPlayer.remove(player);																	// removing player in busted list
			gui.getFrame().getPlayer().removeAllItems();													// combo box element 
			gui.getFrame().getPlayer1().removeAllItems();													// combo box element
			for (Player play : lis) {																		// iterating loop
				gui.getFrame().getPlayer().addItem(play.getPlayerName());									// adding in combo box
				gui.getFrame().getPlayer1().addItem(play.getPlayerName());									// adding in combo box 2
			}
			gui.displayMsg(player.getPlayerName() + " is removed");											// dialog box 
		}
	}

	private void clear() {																					// clearing bet
		ArrayList<Player> lis = (ArrayList<Player>) engine.getAllPlayers();									// getting list
		for (Player play : lis) {																			// iterating list
			play.setBet(0);																					// removing bet
			play.setResult(0);																				// setting result to 0
		}
		gui.displayMsg("Bet cleared");																		// displaying massage
	}

	private void summary() {
		String name = (String) gui.getFrame().getPlayer1().getSelectedItem();								// name from combo box 1
		ArrayList<Player> lis = (ArrayList<Player>) engine.getAllPlayers();									// list player
		for (Player player : lis) {																			// iterating loop
			if (player.getPlayerName().equals(name)) {														// checking player
				gui.getFrame().setName_1(player.getPlayerName());											// displaying summary
				gui.getFrame().setId_1(player.getPlayerId());
				gui.getFrame().setBet_1("" + player.getBet());
				gui.getFrame().setPoint_1("" + player.getPoints());
				break;
			}
		}
	}

	private void result() {																					// Checking result
		ArrayList<Player> lis = (ArrayList<Player>) engine.getAllPlayers();
		for (Player play : lis) {																			// iterating loop
			if (!bustedPlayer.contains(play)) {																// checking if any player not busted
				gui.displayMsg(play.getPlayerName() + " is not busted");									// player not busted
				return;																						// returning from method
			}
		}
		for (Player play : lis) {																			// iterating through list
			gui.result(play, play.getResult(), engine);														// setting player result
		}
		engine.dealHouse(0);																				// dealing house
		gui.getFrame().getNextCard().setVisible(false);														// Hiding next card button
		gui.getFrame().getResult().setVisible(false);														// hiding result button
		gui.getFrame().getStart().setVisible(true);															// showing start button
		gui.getFrame().getAddPlayer().setVisible(true);														// showing add player button
		gui.getFrame().getRemove().setVisible(true);														// showing remove button
		gui.getFrame().getBet().setVisible(true);															// showing set bet button
		gui.getFrame().getClearBet().setVisible(true);														// showing clear bet button
		gui.getFrame().reset();																				// reseting frame
	}

	private void setNextCard() {
		String name = (String) gui.getFrame().getPlayer().getSelectedItem();								// name of player
		ArrayList<Player> lis = (ArrayList<Player>) engine.getAllPlayers();									// list of player
		final Player player ;																				// final player
		for (Player play : lis) {																			//iterating list
			if (play.getPlayerName().equals(name) && !bustedPlayer.contains(play)) {						// checking player
				player = play;																				// initializing player
				new Thread() {																				// new thread
					@Override
					public void run() {
						engine.dealPlayer(player, 100);														// dealing player
						bustedPlayer.add(player);															// busting player
					}	
				}.start();																					// starting thread
				break;
			}
		}
	}

	private void setBet() {
		String name = (String) gui.getFrame().getPlayer().getSelectedItem();
		ArrayList<Player> lis = (ArrayList<Player>) engine.getAllPlayers();
		if (lis.isEmpty()) {
			gui.displayMsg( "Add Player");
			return;
		}
		Player player = null;
		for (Player play : lis) {
			if (play.getPlayerName().equals(name)) {
				player = play;
				break;
			}
		}
		player.setBet(gui.getBet(player.getPoints()));
	}

	// This function adds players
	private void addPlayer() {
		Player player = new SimplePlayer(gui.getPlayerID(), gui.getPlayerName(), gui.getInitialBettingPoint());
		engine.addPlayer(player);
		gui.getFrame().getPlayer().removeAllItems();
		gui.getFrame().getPlayer1().removeAllItems();
		gui.getFrame().resetPlayer();
		ArrayList<Player> play=(ArrayList<Player>) engine.getAllPlayers();
		for(Player p:play) {
			gui.getFrame().getPlayer().addItem(p.getPlayerName());
			gui.getFrame().getPlayer1().addItem(p.getPlayerName());
			gui.addPlayer(p);
		}
	}

	public GameEngine getEngine() {
		return engine;
	}
}