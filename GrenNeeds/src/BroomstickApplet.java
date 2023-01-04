//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Event;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class BroomstickApplet extends Applet implements WindowListener {
	public Frame window;
	public BroomPanel gamePanel;
	public Choice players;
	public Choice dive;
	public Choice speed;
	public Choice playerImg;
	public Choice itemImg;
	public Choice fieldImg;
	public Choice bgImg;
	public Choice accel;
	public Choice maxspeed;
	public Choice sound;
	public TextField red;
	public TextField black;
	public TextField gold;
	public TextField goldval;
	public TextField winscore;
	public TextField duration;
	public TextField width;
	public TextField height;

	public TextField newText(Panel var1, String var2, String var3, String var4) {
		String var5 = this.getParameter(var3);
		if (var5 == null) {
			var5 = var4;
		}

		TextField var6 = new TextField(var5, 4);
		var1.add(new Label(var2));
		var1.add(var6);
		return var6;
	}

	public Choice newChoice(Panel var1, String var2, String var3, String var4) {
		Choice var5 = new Choice();
		StringIterator var6 = new StringIterator();
		var6.setString(var4);
		String var7 = var6.nextWord();
		String var8 = this.getParameter(var3);

		boolean var9;
		for(var9 = false; var7 != null; var7 = var6.nextWord()) {
			var5.addItem(var7);
			if (var7.equals(var8)) {
				var9 = true;
			}
		}

		if (!var9) {
			var5.addItem(var8);
		}

		var5.select(var8);
		var1.add(new Label(var2));
		var1.add(var5);
		return var5;
	}

	public void init() {
		System.out.println("\n");
		System.out.println("--== Broomsticks Advanced Demo by Paul Rajlich ==--");
		System.out.println("    copyright (c) 2001, all rights reserved\n");
		this.setBackground(new Color(229, 229, 229));
		this.setLayout(new BorderLayout());
		Panel var1 = new Panel();
		var1.setLayout(new GridLayout(0, 2));
		this.players = this.newChoice(var1, "Number of players: ", "PLAYERS", "2 4");
		this.dive = this.newChoice(var1, "Allow diving: ", "DIVING", "yes no");
		this.accel = this.newChoice(var1, "Acceleration: ", "ACCEL", "1 2 3");
		this.maxspeed = this.newChoice(var1, "Max speed: ", "MAXSPEED", "3 4 5 6 7 8");
		this.width = this.newText(var1, "Field width: ", "GAMEWIDTH", "640");
		this.height = this.newText(var1, "Field height: ", "GAMEHEIGHT", "450");
		this.red = this.newText(var1, "Red balls: ", "RED", "1");
		this.black = this.newText(var1, "Black balls: ", "BLACK", "2");
		this.gold = this.newText(var1, "Gold balls: ", "GOLD", "1");
		this.goldval = this.newText(var1, "Gold points: ", "GOLDPOINTS", "150");
		this.winscore = this.newText(var1, "Score to win: ", "WINSCORE", "50");
		this.duration = this.newText(var1, "Seconds to gold ball: ", "DURATION", "60");
		this.playerImg = this.newChoice(var1, "Players image: ", "PLAYERSIMG", "images/players1.gif images/players2.gif images/players3.gif images/players4.gif images/players5.gif images/players6.gif images/players7.gif images/players8.gif images/players9.gif images/players0.gif images/playersA.gif images/playersB.gif images/playersC.gif images/playersD.gif images/playersE.gif images/playersF.gif images/playersG.gif");
		this.itemImg = this.newChoice(var1, "Items image: ", "ITEMSIMG", "images/items1.gif images/items2.gif images/items3.gif");
		this.fieldImg = this.newChoice(var1, "Field image: ", "FIELDIMG", "images/field1.jpg images/field2.jpg images/field3.jpg none");
		this.bgImg = this.newChoice(var1, "Sky image: ", "SKYIMG", "images/sky1.jpg images/sky2.jpg images/sky3.jpg images/sky4.jpg images/sky5.jpg none");
		this.sound = this.newChoice(var1, "Sound: ", "SOUND", "on off");
		Button var2 = new Button("Press here to start");
		var2.setBackground(new Color(0, 255, 0));
		var1.add(var2);
		var1.add(new Label(""));
		this.add("Center", var1);
		this.repaint();
		this.gamePanel = null;
		this.window = null;
	}

	public boolean action(Event var1, Object var2) {
		String var3 = (String)var2;
		if (var3.equals("Press here to start")) {
			this.openGame();
		}

		return false;
	}

	public void openGame() {
		if (this.window != null) {
			if (this.gamePanel != null) {
				this.gamePanel.stop();
			}

			this.window.dispose();
		}

		this.window = new Frame();
		this.window.setTitle("Broomsticks Full Version by Paul Rajlich");
		this.window.setVisible(true);
		this.window.setResizable(false);
		this.window.addWindowListener(this);
		this.gamePanel = new BroomPanel(this);
		this.gamePanel.start();
		this.gamePanel.repaint();
		int var1 = this.gamePanel.getParam(this.width);
		int var2 = this.gamePanel.getParam(this.height);
		this.window.setSize(var1, var2);
		this.window.setLocation(100, 100);
		this.window.setLayout(new BorderLayout());
		this.window.add("Center", this.gamePanel);
		this.window.show();
	}

	public void windowClosing(WindowEvent var1) {
		System.out.println("applet: window closing");
		if (this.gamePanel != null) {
			this.gamePanel.stop();
		}

		this.window.dispose();
	}

	public void windowClosed(WindowEvent var1) {
		System.out.println("applet: window closed");
	}

	public void windowOpened(WindowEvent var1) {
	}

	public void windowIconified(WindowEvent var1) {
	}

	public void windowDeiconified(WindowEvent var1) {
	}

	public void windowActivated(WindowEvent var1) {
	}

	public void windowDeactivated(WindowEvent var1) {
	}

	public void start() {
		System.out.println("applet: start");
	}

	public void stop() {
		System.out.println("applet: stop");
	}

	public void destroy() {
		System.out.println("applet: destroy");
		if (this.gamePanel != null) {
			this.gamePanel.stop();
		}

		this.window.dispose();
	}

	public String getAppletInfo() {
		return "Broomsticks Applet by Paul Rajlich";
	}

	public BroomstickApplet() {
	}
}
