package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.MainBoard;

public class ComboController implements ActionListener {
	MainBoard gui;			//View

	public ComboController(MainBoard gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gui.drawIcon();		// used for displaying card image
	}

}
