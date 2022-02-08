package view;
import javax.swing.*;

import Controller.ComboController;
import Controller.Controller;
import model.CardIcon;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MainBoard extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBox;
	private JPanel contentPane;
	private Controller con;
	private JButton nextCard;
	private JButton addPlayer;
	private JButton result;
	private JButton bet;
	private JButton clearBet;
	private JLabel heart;
	private JLabel diamond;
	private JLabel spade;
	private JLabel card1;
	private JLabel card2;
	private JLabel card3;
	private JLabel card4;
	private JPanel Game;
	private JLabel Name_1;
	private JLabel Bet_1;
	private JLabel point_1;
	private JLabel Id_1;
	private JButton check;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JButton remove;
	private JButton start;
	private JComboBox<String> comboBox1;
	private ArrayList<CardIcon> list;
	
	public MainBoard(Controller con) {
		UIManager.put("TabbedPane.selected", new Color(0,0,0,0));
		list=new ArrayList<>();
		this.con=con;
		contentPane=new JPanel();
		
		setLocationRelativeTo(null);
		setSize(649,641);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(contentPane);
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setLayout(null);
		
		JLabel club = new JLabel("");
		club.setBounds(410, 0, 50, 50);
		contentPane.add(club);
		club.setIcon(new ImageIcon(resizeImage(new ImageIcon("./img/clubs.png").getImage(), 50, 50)));
		
		heart = new JLabel("");
		heart.setBounds(470, 0, 50, 50);
		contentPane.add(heart);
		heart.setIcon(new ImageIcon(resizeImage(new ImageIcon("./img/hearts.png").getImage(), 50, 50)));
		
		diamond = new JLabel("");
		diamond.setBounds(200, 0, 50, 50);
		contentPane.add(diamond);
		diamond.setIcon(new ImageIcon(resizeImage(new ImageIcon("./img/diamonds.png").getImage(), 50, 50)));
		
		spade = new JLabel("");
		spade.setBounds(130, 0, 50, 50);
		contentPane.add(spade);
		spade.setIcon(new ImageIcon(resizeImage(new ImageIcon("./img/spades.png").getImage(), 50, 50)));
		
		JLabel mainLabel = new JLabel("Black Jack",SwingConstants.CENTER);
		mainLabel.setForeground(new Color(255, 255, 224));
		mainLabel.setFont(new Font("Kokonor", Font.BOLD, 30));
		mainLabel.setBounds(0, 1, 649, 51);
		contentPane.add(mainLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 76, 643, 521);
		contentPane.add(tabbedPane);
		
		Game = new JPanel();
		Game.setBackground(Color.gray);
		tabbedPane.addTab("Game", null, Game, null);
		Game.setLayout(null);
		
		nextCard = new JButton("Next Card");
		nextCard.setBounds(516, 35, 100, 30);
		Game.add(nextCard);
		
		addPlayer = new JButton("Add Player");
		addPlayer.setBounds(10, 5, 100, 30);
		Game.add(addPlayer);
		
		result = new JButton("Result");
		result.setBounds(516, 65, 100, 30);
		Game.add(result);
		
		bet = new JButton("Set Bet");
		bet.setBounds(10, 35, 100, 30);
		Game.add(bet);
		
		clearBet = new JButton("Clear Bet");
		clearBet.setBounds(10, 65, 100, 30);
		Game.add(clearBet);
		
		card1 = new JLabel("");
		card1.setBorder(null);
		card1.setBounds(122, 15, 170, 220);
		Game.add(card1);
		
		remove = new JButton("Remove");
		remove.setBounds(10, 95, 100, 30);
		Game.add(remove);
		
		comboBox = new JComboBox<>();
		comboBox.addActionListener(new ComboController(this));
		comboBox.setBounds(480, 7, 136, 27);
		Game.add(comboBox);
		
		start = new JButton("start");
		start.setBounds(10, 127, 100, 30);
		Game.add(start);
		
		card2 = new JLabel("");
		card2.setBorder(null);
		card2.setBounds(304, 15, 170, 220);
		Game.add(card2);
		
		card3 = new JLabel("");
		card3.setBorder(null);
		card3.setBounds(122, 250, 170, 220);
		Game.add(card3);
		
		card4 = new JLabel("");
		card4.setBorder(null);
		card4.setBounds(304, 250, 170, 220);
		Game.add(card4);
		start.addActionListener(con);
		remove.addActionListener(con);
		clearBet.addActionListener(con);
		bet.addActionListener(con);
		result.addActionListener(con);
		addPlayer.addActionListener(con);
		nextCard.addActionListener(con);
		
		nextCard.setVisible(false);
		result.setVisible(false);
		
		JPanel Summary = new JPanel();
		Summary.setBackground(Color.GRAY);
		tabbedPane.addTab("Summary", null, Summary, null);
		tabbedPane.setBackground(new Color(0,0,0,0));
		Summary.setLayout(null);
		
		JLabel Name = new JLabel("Name");
		Name.setBounds(78, 31, 61, 16);
		Summary.add(Name);
		
		JLabel Bet = new JLabel("Bet");
		Bet.setBounds(78, 94, 61, 16);
		Summary.add(Bet);
		
		JLabel point = new JLabel("Point");
		point.setBounds(78, 122, 61, 16);
		Summary.add(point);
		
		JLabel Id = new JLabel("ID");
		Id.setBounds(78, 59, 61, 16);
		Summary.add(Id);
		
		Name_1 = new JLabel("");
		Name_1.setBounds(190, 31, 80, 16);
		Summary.add(Name_1);
		
		Bet_1 = new JLabel("");
		Bet_1.setBounds(190, 94, 80, 16);
		Summary.add(Bet_1);
		
		point_1 = new JLabel("");
		point_1.setBounds(190, 122, 80, 16);
		Summary.add(point_1);
		
		Id_1 = new JLabel("");
		Id_1.setBounds(190, 59, 80, 16);
		Summary.add(Id_1);
		
		check = new JButton("Check");
		check.setBounds(191, 193, 117, 29);
		Summary.add(check);
		
		comboBox1 = new JComboBox<String>();
		comboBox1.setBounds(367, 6, 136, 27);
		Summary.add(comboBox1);
		
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 586, 649, 27);
		contentPane.add(panel);
		
		lblNewLabel = new JLabel("Black Jack");
		lblNewLabel.setForeground(Color.WHITE);
		panel.add(lblNewLabel);
        
        check.addActionListener(con);
	}
	
	private Image resizeImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
	
	public JComboBox<String> getPlayer(){
		return comboBox;
	}
	
	public JComboBox<String> getPlayer1(){
		return comboBox1;
	}

	public Controller getCon() {
		return con;
	}

	public JButton getStart() {
		return start;
	}
	
	public JButton getNextCard() {
		return nextCard;
	}


	public JButton getAddPlayer() {
		return addPlayer;
	}


	public JButton getResult() {
		return result;
	}

	public JButton getBet() {
		return bet;
	}

	public JButton getClearBet() {
		return clearBet;
	}
	
	public JButton getCheck() {
		return check;
	}

	public void setName_1(String name_1) {
		Name_1.setText(name_1);;
	}

	public void setBet_1(String bet_1) {
		Bet_1.setText(bet_1);
	}

	public void setPoint_1(String point_1) {
		this.point_1.setText(point_1);;
	}

	public void setId_1(String id_1) {
		Id_1.setText(id_1);;
	}
	
	public void setIcon(Player name,PlayingCard c1) {
		CardIcon icon = null;
		for(CardIcon i:list) {
			if(i.getPlayer().equals(name)) {
				icon=i;
			}
		}
		icon.setIconName("./img/cards/"+c1.getSuit()+"_"+c1.getValue()+".png");
		drawIcon();
	}
	
	public void drawIcon() {
		if(comboBox.getItemCount()==0)
			return;
		String str=(String) comboBox.getSelectedItem();
		for(CardIcon x:list) {
			Image img;
			if(x.getPlayer().getPlayerName().equals(str)) {
				String[] ic=x.getIconName();
				img=new ImageIcon(ic[0]).getImage();
				img=resizeImage(img, 170, 220);
				card1.setIcon(new ImageIcon(img));
				img=new ImageIcon(ic[1]).getImage();
				img=resizeImage(img, 170, 220);
				card2.setIcon(new ImageIcon(img));
				img=new ImageIcon(ic[2]).getImage();
				img=resizeImage(img, 170, 220);
				card3.setIcon(new ImageIcon(img));
				img=new ImageIcon(ic[3]).getImage();
				img=resizeImage(img, 170, 220);
				card4.setIcon(new ImageIcon(img));
			}
		}
		Game.repaint();
	}

	public JButton getRemove() {
		return remove;
	}

	public void add(Player player) {
		list.add(new CardIcon(player));
	}

	public void reset() {
		for(CardIcon x:list) {
			x.reset();
			drawIcon();
		}
		
	}

	public void resetPlayer() {
		list=new ArrayList<CardIcon>();
	}
}
