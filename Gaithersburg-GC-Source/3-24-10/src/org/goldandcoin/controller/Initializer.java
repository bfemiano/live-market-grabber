package org.goldandcoin.controller;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.goldandcoin.view.DisplayFrame;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

public class Initializer {
	
	public static void main(String args[]) {
		DisplayFrame frame = new DisplayFrame("Gaithersburg G&C Inventory Keeper");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(new Dimension(900, 550));
	}

}
