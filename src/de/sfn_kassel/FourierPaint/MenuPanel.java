package de.sfn_kassel.FourierPaint;

import javax.swing.*;

/**
 * Created by robin on 27.07.15.
 */
public class MenuPanel extends JPanel {
	private static final long serialVersionUID = -1705327359545988591L;

	public MenuPanel() {
        super();

        this.add(new JButton("test"));
        this.add(new JButton("test2"));

        this.setVisible(true);
    }
}
