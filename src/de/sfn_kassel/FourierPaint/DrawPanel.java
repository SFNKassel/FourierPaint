package de.sfn_kassel.FourierPaint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;

/**
 * Created by robin on 19.07.15.
 */
public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = -2006651230453773384L;
	private BufferedImage img;
	private IBrush brush;
	private boolean mousePressed;
	private int prevX = 0;
	private int prevY = 0;

	public DrawPanel(int x) throws IOException {
		super();

		img = new BufferedImage(x, x, BufferedImage.TYPE_3BYTE_BGR);
		img.createGraphics().setColor(Color.white);
		img.createGraphics().fillRect(0, 0, x, x);
		
//		img = ImageIO.read(new File("moon.jpg"));
		
		brush = new Pencil(2);

		mousePressed = false;

		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		this.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int size = Math.min(this.getWidth(), this.getHeight());

		g.drawImage(img, 0, 0, size, size, null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		mousePressed = true;
		brush.applyTo(img, convertCoordinate(e.getX()), convertCoordinate(e.getY()));
		prevX = convertCoordinate(e.getX());
		prevY = convertCoordinate(e.getY());
		this.updateUI();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mousePressed = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		brush.applyTo(img, prevX, prevY, convertCoordinate(e.getX()), convertCoordinate(e.getY()));
		prevX = convertCoordinate(e.getX());
		prevY = convertCoordinate(e.getY());
		this.updateUI();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (mousePressed) {
			brush.applyTo(img, prevX, prevY, convertCoordinate(e.getX()), convertCoordinate(e.getY()));
			prevX = convertCoordinate(e.getX());
			prevY = convertCoordinate(e.getY());
			this.updateUI();
		}
	}

	private int convertCoordinate(int coord) {
		double scale = (double) Math.min(this.getWidth(), this.getHeight()) / (double) img.getHeight();

		return (int) ((double) coord / scale);
	}

	public void setRaster(Raster r) {
		this.img.setData(r);
		this.updateUI();
	}

	public double[] getPixels() {
		double data[] = new double[img.getWidth() * img.getWidth() * 3];

		data = img.getRaster().getPixels(0, 0, img.getHeight(), img.getWidth(), data);

		double ret[] = new double[img.getWidth() * img.getWidth()];

		for (int i = 0; i < img.getWidth() * img.getWidth(); i++) {
			ret[i] = ((data[i * 3] + data[i * 3 + 1] + data[i * 3 + 2]) / 3)  / 255;
		}

		return ret;
	}

	public void setPixels(double[] data) {
		double convertedData[] = new double[img.getWidth() * img.getWidth() * 3];

		for (int i = 0; i < convertedData.length / 3; i++) {
			convertedData[i * 3] = data[i] * 255;
			convertedData[i * 3 + 1] = data[i] * 255;
			convertedData[i * 3 + 2] = data[i] * 255;
		}

		img.getRaster().setPixels(0, 0, img.getHeight(), img.getWidth(), convertedData);

		this.updateUI();
	}

	public void reset() {
		img = new BufferedImage(img.getWidth(), img.getWidth(), BufferedImage.TYPE_3BYTE_BGR);
		this.updateUI();
	}
}
