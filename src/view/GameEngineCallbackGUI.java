package view;

import javax.swing.JOptionPane;

import Controller.Controller;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {

	private MainBoard frame;
	private Controller con;

	public GameEngineCallbackGUI(Controller con) {
		frame = new MainBoard(con);
		frame.setVisible(true);
		this.con = con;
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		frame.setIcon(player, card);
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		frame.setIcon(player, card);
		JOptionPane.showMessageDialog(null, "You are Busted");
	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		player.setResult(result);
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		JOptionPane.showMessageDialog(null, card.toString());
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		JOptionPane.showMessageDialog(null, card.toString());
	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		if (engine != null) {
			JOptionPane.showMessageDialog(null, "House Result:" + result);
			for (Player player : con.getEngine().getAllPlayers()) {
				if (player.getResult() == result)
					JOptionPane.showMessageDialog(null, player.getPlayerName() + "Draw");
				else if (player.getResult() < result)
					JOptionPane.showMessageDialog(null, player.getPlayerName() + " lost");
				else
					JOptionPane.showMessageDialog(null, player.getPlayerName() + " Won");
			}
		}
	}

	public String getPlayerID() {
		String str = JOptionPane.showInputDialog("Enter Player ID");
		if(str==null)
			return getPlayerID();
		if (!str.matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Invalid ID");
			return getPlayerID();
		} else
			return str;
	}

	public String getPlayerName() {
		String str = JOptionPane.showInputDialog("Enter Player Name");
		if(str==null)
			return getPlayerName();
		if (!str.matches("[a-zA-Z_]+")) {
			JOptionPane.showMessageDialog(null, "Invalid Name");
			return getPlayerName();
		} else
			return str;
	}

	public int getBet(int point) {
		try {
			String str=JOptionPane.showInputDialog("Enter Bet");
			if(str==null)
				return getBet(point);
			int x = Integer.parseInt(str);
			if (x < 0 || x > point) {
				JOptionPane.showMessageDialog(null, "Invalid Bet");
				return getBet(point);
			}
			return x;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid Bet");
			return getBet(point);
		}
	}

	public MainBoard getFrame() {
		return frame;
	}

	public int getInitialBettingPoint() {
		try {
			int x = Integer.parseInt(JOptionPane.showInputDialog("Enter initial Betting point: "));
			if (x < 0) {
				JOptionPane.showMessageDialog(null, "Invalid Bet");
				return getInitialBettingPoint();
			}
			return x;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid Bet");
			return getInitialBettingPoint();
		}
	}

	public void addPlayer(Player player) {
		frame.add(player);
		
	}

	public void displayMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg);
		
	}

}
