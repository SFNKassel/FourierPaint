package de.sfn_kassel.FourierPaint;

import javax.swing.*;

import de.sfn_kassel.FourierPaint.fourier_transformation.Complex;
import de.sfn_kassel.FourierPaint.fourier_transformation.FourierTransformation;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by robin on 19.07.15.
 */
public class FourierPaint extends JFrame {
	private static final long serialVersionUID = 2820642198067777953L;
	private DrawPanel fourierPanel;
	private DrawPanel picturePanel;
	private MenuPanel menuPanel;
	private FourierTransformation ft;

	public FourierPaint() throws IOException {
		super();

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(800, 600);

		ft = new FourierTransformation(16, 16);

		Container cp = this.getContentPane();

		cp.setLayout(new BorderLayout());

		JPanel drawPanel = new JPanel();

		drawPanel.setLayout(new BoxLayout(drawPanel, BoxLayout.LINE_AXIS));

		fourierPanel = new DrawPanel(9);
		picturePanel = new DrawPanel(16);
		menuPanel = new MenuPanel();

		fourierPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		picturePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		cp.add(menuPanel, BorderLayout.PAGE_START);
		cp.add(drawPanel, BorderLayout.CENTER);

		drawPanel.add(fourierPanel);
		drawPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		drawPanel.add(picturePanel);
		
		this.setTitle("FourierPaint");

		cp.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				picturePanel.reset();
				fourierPanel.reset();
			}
		});
		
		picturePanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				ArrayList<Complex> out = ft.executeForward(picturePanel.getPixels());
				int size = (int) Math.sqrt((int) out.size());
				double data[] = new double[(size * size)];

				for (int i = 0; i < data.length; i++) {
					data[i] = 1 - Math.min(((1 - Math.log(Math.sqrt(out.get(i).real * out.get(i).real + out.get(i).imaginary * out.get(i).imaginary))) / 4) - 1, 1);
				}

				fourierPanel.setPixels(data);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		fourierPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				ArrayList<Complex> out = new ArrayList<>();

				double data[] = fourierPanel.getPixels();
				
				int size = (int) Math.sqrt(data.length);
				
				for (int i = 0; i < data.length; i++) {
					if(((i % size) <= (size / 2)) && ((i / size) <= (size/2))) {
						out.add(new Complex(data[i], 0));
					} else {
						out.add(new Complex());
					}
				}
			

				picturePanel.setPixels(ft.executeBackward(out));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		this.setVisible(true);
	}
}
