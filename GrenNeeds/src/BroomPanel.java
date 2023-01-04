//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.applet.AudioClip;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;

class BroomPanel extends Panel implements Runnable {
	public AudioClip scoreClip;
	public AudioClip grabClip;
	public AudioClip bumpClip;
	public AudioClip winClip;
	public BroomstickApplet applet;
	public Image[][][] img;
	public Image basket;
	public Image basketH;
	public Image[] ballImg;
	String playersStr;
	String itemsStr;
	String fieldStr;
	String bgStr;
	public Image playersImg;
	public Image itemsImg;
	public Image introImg;
	public Image[] bgImg;
	public Image[] backImg;
	public Image fdImg;
	public Image fieldImg;
	public int backToggle;
	public Frame f;
	int duration;
	int winScore;
	long startTime;
	Color blue;
	Color green;
	Color sky;
	Color yellow;
	Color gold;
	Image offImage;
	Graphics offgc;
	MediaTracker tracker;
	public Person[] players;
	public Ball[] balls;
	public int[] teamScore;
	public boolean[] teamBasket;
	int width;
	int height;
	int midW;
	int midH;
	public boolean started;
	public boolean gameover;
	public boolean teams;
	int redBalls;
	int blackBalls;
	int goldBalls;
	int goldval;
	public float accel;
	public float maxspeed;
	int timer;
	int sleepMS;
	public boolean soundToggle;
	public boolean dive;
	public Thread thread;

	public BroomPanel(BroomstickApplet var1) {
		this.applet = var1;
		this.setBackground(Color.white);
		this.setFont(new Font("Helvetica", 0, 12));
		this.setSize(this.getParam(this.applet.width), this.getParam(this.applet.height));
		Dimension var2 = this.size();
		this.width = var2.width - 10;
		this.height = var2.height - 50;
		this.midW = this.width / 2;
		this.midH = this.height / 2;
		this.soundToggle = false;
		this.backToggle = 1;
		this.started = false;
		this.gameover = false;
		this.offImage = null;
		this.offgc = null;
		this.timer = 0;
		this.green = new Color(0, 164, 0);
		this.blue = new Color(0, 128, 255);
		this.sky = new Color(216, 215, 255);
		this.yellow = new Color(128, 128, 0);
		this.gold = new Color(255, 255, 0);
		String var3 = this.applet.players.getSelectedItem();
		if (var3.equals("4")) {
			this.teams = true;
		} else {
			this.teams = false;
		}

		String var4 = this.applet.dive.getSelectedItem();
		if (var4.equals("yes")) {
			this.dive = true;
		} else {
			this.dive = false;
		}

		String var5 = this.applet.sound.getSelectedItem();
		if (var5.equals("on")) {
			this.soundToggle = true;
		}

		this.accel = (float)this.getParam(this.applet.accel);
		this.maxspeed = (float)this.getParam(this.applet.maxspeed);
		this.redBalls = this.getParam(this.applet.red);
		this.blackBalls = this.getParam(this.applet.black);
		this.goldBalls = this.getParam(this.applet.gold);
		this.goldval = this.getParam(this.applet.goldval);
		this.winScore = this.getParam(this.applet.winscore);
		this.duration = this.getParam(this.applet.duration) * 1000;
		this.playersStr = this.applet.playerImg.getSelectedItem();
		this.itemsStr = this.applet.itemImg.getSelectedItem();
		this.fieldStr = this.applet.fieldImg.getSelectedItem();
		this.bgStr = this.applet.bgImg.getSelectedItem();
		if (this.bgStr.equals("none")) {
			this.backToggle = 0;
		}

		this.applet.window.setCursor(3);
		this.loadImages();
		this.loadSounds();
		this.balls = new Ball[this.redBalls + this.blackBalls + this.goldBalls];

		int var6;
		for(var6 = 0; var6 < this.redBalls; ++var6) {
			this.balls[var6] = new Ball(this, 2, this.midW, this.midH - 20);
			this.balls[var6].catchable = true;
		}

		for(var6 = 0; var6 < this.blackBalls; ++var6) {
			this.balls[this.redBalls + var6] = new Ball(this, 1, this.midW, this.midH + 20);
			this.balls[this.redBalls + var6].maxSpeed *= 1.5F;
		}

		for(var6 = 0; var6 < this.goldBalls; ++var6) {
			this.balls[this.redBalls + this.blackBalls + var6] = new GoldBall(this, 0, this.midW, 50);
			this.balls[this.redBalls + this.blackBalls + var6].catchable = true;
			this.balls[this.redBalls + this.blackBalls + var6].alive = false;
			this.balls[this.redBalls + this.blackBalls + var6].smart = 1;
		}

		if (this.teams) {
			this.players = new Person[4];
		} else {
			this.players = new Person[2];
		}

		this.players[0] = new Person(this.balls[0], this, 1, 100, this.midH);
		this.players[0].setKeys(101, 120, 115, 102, 49, 116, 50);
		this.players[0].setInfoXY(20, this.height - 15);
		this.players[1] = new Person(this.balls[0], this, 4, this.width - 100, this.midH);
		this.players[1].setKeys(1004, 1005, 1006, 1007, 10, 0, 8);
		this.players[1].setInfoXY(this.width - 150, this.height - 15);
		this.players[1].side = 1;
		if (this.teams) {
			this.players[2] = new Person(this.balls[0], this, 2, this.width - 200, this.midH);
			this.players[2].setKeys(1000, 1001, 127, 1003, 1025, 1002, 1023);
			this.players[2].setInfoXY(this.midW, this.height - 15);
			this.players[3] = new Person(this.balls[0], this, 3, 200, this.midH);
			this.players[3].setKeys(105, 109, 106, 108, 55, 112, 56);
			this.players[3].setInfoXY(this.midW - 150, this.height - 15);
			this.players[2].side = 1;
		}

		this.teamScore = new int[2];
		this.teamScore[0] = 0;
		this.teamScore[1] = 0;
		this.teamBasket = new boolean[2];
		this.teamBasket[0] = false;
		this.teamBasket[1] = false;
		this.thread = null;
	}

	public int getParam(TextField var1) {
		return Integer.valueOf(var1.getText());
	}

	public int getParam(Choice var1) {
		String var2 = var1.getSelectedItem();
		return Integer.valueOf(var2);
	}

	public void loadSounds() {
		this.scoreClip = this.applet.getAudioClip(this.applet.getCodeBase(), "snd/score.au");
		this.grabClip = this.applet.getAudioClip(this.applet.getCodeBase(), "snd/grab.au");
		this.bumpClip = this.applet.getAudioClip(this.applet.getCodeBase(), "snd/bump.au");
		this.winClip = this.applet.getAudioClip(this.applet.getCodeBase(), "snd/win.au");
	}

	public void loadImages() {
		System.out.println("loading images...");
		this.tracker = new MediaTracker(this.applet);
		this.playersImg = this.applet.getImage(this.applet.getCodeBase(), this.playersStr);
		this.itemsImg = this.applet.getImage(this.applet.getCodeBase(), this.itemsStr);
		this.introImg = this.applet.getImage(this.applet.getCodeBase(), "images/title.gif");
		this.fdImg = this.applet.getImage(this.applet.getCodeBase(), this.fieldStr);
		this.tracker.addImage(this.playersImg, 0);
		this.tracker.addImage(this.itemsImg, 0);
		this.tracker.addImage(this.introImg, 0);
		this.tracker.addImage(this.fdImg, 0);
		ImageFilter[] var1 = new ImageFilter[5];
		ImageProducer[] var2 = new ImageProducer[5];
		this.bgImg = new Image[5];
		this.backImg = new Image[5];

		for(int var3 = 1; var3 < 2; ++var3) {
			if (var3 == 1) {
				this.bgImg[var3] = this.applet.getImage(this.applet.getCodeBase(), this.bgStr);
			} else {
				this.bgImg[var3] = this.applet.getImage(this.applet.getCodeBase(), "images/sky" + var3 + ".jpg");
			}

			this.tracker.addImage(this.bgImg[var3], 0);
			var1[var3] = new AreaAveragingScaleFilter(this.width - 22, this.height - 52);
			var2[var3] = new FilteredImageSource(this.bgImg[var3].getSource(), var1[var3]);
			this.backImg[var3] = this.createImage(var2[var3]);
			this.tracker.addImage(this.backImg[var3], 0);
		}

		AreaAveragingScaleFilter var10 = new AreaAveragingScaleFilter(this.width - 22, 25);
		FilteredImageSource var4 = new FilteredImageSource(this.fdImg.getSource(), var10);
		this.fieldImg = this.createImage(var4);
		this.tracker.addImage(this.fieldImg, 0);
		this.img = new Image[10][2][2];

		int var5;
		for(var5 = 0; var5 < 10; ++var5) {
			System.out.println("model " + var5);

			for(int var6 = 0; var6 < 2; ++var6) {
				for(int var7 = 0; var7 < 2; ++var7) {
					CropImageFilter var8;
					if (var5 < 5) {
						var8 = new CropImageFilter(var6 * 80 + var7 * 40 + 1, var5 * 40 + 41, 39, 39);
					} else {
						var8 = new CropImageFilter(var6 * 80 + var7 * 40 + 161, (var5 - 5) * 40 + 41, 39, 39);
					}

					FilteredImageSource var9 = new FilteredImageSource(this.playersImg.getSource(), var8);
					this.img[var5][var6][var7] = this.createImage(var9);
					this.tracker.addImage(this.img[var5][var6][var7], 0);
				}
			}
		}

		this.ballImg = new Image[3];

		for(var5 = 0; var5 < 3; ++var5) {
			CropImageFilter var12 = new CropImageFilter(1, var5 * 40 + 1, 39, 39);
			FilteredImageSource var14 = new FilteredImageSource(this.itemsImg.getSource(), var12);
			this.ballImg[var5] = this.createImage(var14);
			this.tracker.addImage(this.ballImg[var5], 0);
		}

		CropImageFilter var11 = new CropImageFilter(1, 121, 39, 39);
		FilteredImageSource var13 = new FilteredImageSource(this.itemsImg.getSource(), var11);
		this.basket = this.createImage(var13);
		this.tracker.addImage(this.basket, 0);
		CropImageFilter var15 = new CropImageFilter(41, 121, 39, 39);
		FilteredImageSource var16 = new FilteredImageSource(this.itemsImg.getSource(), var15);
		this.basketH = this.createImage(var16);
		this.tracker.addImage(this.basketH, 0);
	}

	public void start() {
		if (this.thread == null) {
			this.thread = new Thread(this);
		}

		this.thread.start();
	}

	public void stop() {
		this.thread = null;
	}

	public boolean mouseDown(Event var1, int var2, int var3) {
		this.started = true;
		this.gameover = false;
		this.teamScore[0] = 0;
		this.teamScore[1] = 0;
		this.startTime = System.currentTimeMillis();

		for(int var4 = 0; var4 < this.goldBalls; ++var4) {
			this.balls[this.redBalls + this.blackBalls + var4].alive = false;
		}

		this.paint(this.getGraphics());
		this.requestFocus();
		return true;
	}

	public boolean keyDown(Event var1, int var2) {
		if (var2 == 98) {
			++this.backToggle;
			if (this.backToggle > 1) {
				this.backToggle = 0;
			}
		}

		if (var2 == 110) {
			if (this.soundToggle) {
				this.soundToggle = false;
			} else {
				this.soundToggle = true;
			}

			this.paint(this.getGraphics());
		}

		for(int var3 = 0; var3 < this.players.length; ++var3) {
			this.players[var3].handleKeyEvent(var1, var2);
		}

		return false;
	}

	public void update(Graphics var1) {
		if (this.offgc == null) {
			System.out.println("w: " + this.width + " h: " + this.height);
			this.offImage = this.createImage(this.width - 22, this.height - 52);
			this.offgc = this.offImage.getGraphics();
			this.offgc.translate(-11, -31);
		}

		if (!this.started) {
			this.offgc.drawImage(this.backImg[1], 11, 31, this);
			this.offgc.drawImage(this.fieldImg, 11, this.height - 46, this);
		} else if (this.backToggle > 0) {
			this.offgc.drawImage(this.backImg[this.backToggle], 11, 31, this);
			this.offgc.drawImage(this.fieldImg, 11, this.height - 46, this);
		} else {
			this.offgc.setColor(this.sky);
			this.offgc.fillRect(11, 31, this.width - 22, this.height - 52);
			this.offgc.setColor(this.green);
			this.offgc.fillRect(11, this.height - 46, this.width - 22, 25);
			this.offgc.setColor(Color.black);
			this.offgc.drawLine(11, this.height - 46, this.width - 11, this.height - 46);
			this.offgc.drawLine(41, this.height - 46, 11, this.height - 21);
			this.offgc.drawLine(this.width - 51, this.height - 46, this.width - 11, this.height - 21);
		}

		if (this.goldBalls > 0) {
			this.drawTime(this.offgc);
		}

		this.drawBaskets(this.offgc);
		this.drawScene(this.offgc);
		var1.drawImage(this.offImage, 11, 31, this);
	}

	void drawBaskets(Graphics var1) {
		int var2 = this.midH - 15;
		int var3 = this.height - (var2 + 39) - 31;
		if (this.teamBasket[1]) {
			var1.drawImage(this.basketH, 21, var2, this);
			var1.setColor(Color.black);
			var1.drawRect(28, var2 + 39, 3, var3);
			var1.setColor(this.gold);
			var1.fillRect(29, var2 + 39, 2, var3);
		} else {
			var1.drawImage(this.basket, 21, var2, this);
			var1.setColor(Color.black);
			var1.drawRect(28, var2 + 39, 3, var3);
			var1.setColor(this.yellow);
			var1.fillRect(29, var2 + 39, 2, var3);
		}

		if (this.teamBasket[0]) {
			var1.drawImage(this.basketH, this.width - 41, var2, this);
			var1.setColor(Color.black);
			var1.drawRect(this.width - 34, var2 + 39, 3, var3);
			var1.setColor(this.gold);
			var1.fillRect(this.width - 33, var2 + 39, 2, var3);
		} else {
			var1.drawImage(this.basket, this.width - 41, var2, this);
			var1.setColor(Color.black);
			var1.drawRect(this.width - 34, var2 + 39, 3, var3);
			var1.setColor(this.yellow);
			var1.fillRect(this.width - 33, var2 + 39, 2, var3);
		}
	}

	public void drawIntro(Graphics var1, int var2, int var3) {
		var1.drawImage(this.introImg, var2, var3, this);
		var1.drawImage(this.img[7][1][0], var2 + 3, var3 + 46, this);
		var1.drawImage(this.img[4][0][0], var2 + 168, var3 + 7, this);
		var1.drawImage(this.img[3][0][1], var2 + 230, var3, this);
		var1.drawImage(this.img[6][1][1], var2 + 297, var3 + 46, this);
	}

	public void drawScene(Graphics var1) {
		if (!this.tracker.checkAll(true)) {
			var1.setColor(Color.black);
			var1.drawString("Loading images, please wait...", this.midW - 75, 200);
			this.drawIntro(var1, this.midW - 175, 90);
		} else if (!this.started) {
			this.applet.window.setCursor(1);
			var1.setColor(Color.black);
			if (!this.gameover) {
				var1.drawString("Click here to start.", this.midW - 50, 200);
			} else {
				var1.drawString("Game over. Click here to play again.", this.midW - 75, 200);
			}

			var1.drawString("It's easier to just click on the keys rather than hold them down.", this.midW - 175, 330);
			var1.drawString("Click on your up key several times to start flying.", this.midW - 140, 345);
			if (this.teams) {
				var1.drawString("Blue Team", 100, 240);
				var1.drawString("use E, S, F keys", 100, 260);
				var1.drawString("use I, J, L keys", 100, 275);
			} else {
				var1.drawString("Blue Player", 100, 240);
				var1.drawString("use E, S, F keys", 100, 260);
				var1.drawString("use 1 to switch player", 100, 275);
				var1.setColor(Color.red);
				var1.drawString("Press T for computer control", 100, 290);
				var1.drawString("(use S and F to adjust skill)", 100, 305);
				var1.setColor(Color.black);
			}

			if (this.teams) {
				var1.drawString("Green Team", this.width - 250, 240);
				var1.drawString("use arrow-keys", this.width - 250, 260);
				var1.drawString("use Del, Home, PgDn", this.width - 250, 275);
			} else {
				var1.drawString("Green Player", this.width - 250, 240);
				var1.drawString("use arrow keys", this.width - 250, 260);
				var1.drawString("use ENTER to switch player", this.width - 250, 275);
			}

			this.drawIntro(var1, this.midW - 175, 90);
		} else {
			int var2;
			for(var2 = 0; var2 < this.players.length; ++var2) {
				this.players[var2].draw(var1);
			}

			for(var2 = 0; var2 < this.balls.length; ++var2) {
				if (this.balls[var2].alive) {
					this.balls[var2].draw(var1);
				}
			}

		}
	}

	public void drawTime(Graphics var1) {
		float var2 = (float)(System.currentTimeMillis() - this.startTime) / (float)this.duration;
		if (!((double)var2 > 1.0)) {
			int var3 = (int)(200.0F * var2);
			var1.setColor(this.yellow);
			var1.fillRect(this.midW - 100, 30, 200 - var3, 15);
			var1.setColor(Color.black);
			var1.drawRect(this.midW - 100, 30, 200, 15);
			var1.drawString("time:", this.midW - 20, 43);
			var1.drawLine(this.midW + 100 - var3, 30, this.midW + 100 - var3, 45);
		}
	}

	public void drawScores(Graphics var1) {
		this.drawScores(var1, 2);
	}

	public void drawScores(Graphics var1, int var2) {
		if (var2 == 0) {
			var1.setColor(this.gold);
		} else {
			var1.setColor(this.blue);
		}

		var1.fillRect(48, 8, 100, 15);
		if (var2 == 1) {
			var1.setColor(this.gold);
		} else {
			var1.setColor(this.green);
		}

		var1.fillRect(this.width - 152, 8, 100, 15);
		var1.setColor(Color.black);
		var1.drawRect(48, 8, 100, 15);
		var1.drawRect(this.width - 152, 8, 100, 15);
		var1.drawString("Score: " + this.teamScore[0], 50, 20);
		var1.drawString("Score: " + this.teamScore[1], this.width - 150, 20);
	}

	public void paint(Graphics var1) {
		System.out.println("paint()");
		var1.setColor(this.getBackground());
		var1.fillRect(0, 0, this.width, this.height);
		this.drawScene(var1);
		this.drawScores(var1);
		var1.drawString("Broomsticks by Paul Rajlich", this.midW - 75, 20);
		var1.drawRect(10, 30, this.width - 21, this.height - 51);

		for(int var2 = 0; var2 < this.players.length; ++var2) {
			this.players[var2].drawInfo(var1);
		}

		if (!this.teams) {
			if (this.soundToggle) {
				var1.drawString("n for sound off, b to change background", this.midW - 125, this.height - 5);
				return;
			}

			var1.drawString("n for sound on, b to change background", this.midW - 125, this.height - 5);
		}

	}

	public void gameOver() {
		this.started = false;
		this.gameover = true;
		if (this.soundToggle) {
			this.winClip.play();
		}

		int var1;
		for(var1 = 0; var1 < this.players.length; ++var1) {
			this.players[var1].reset();
		}

		for(var1 = 0; var1 < this.balls.length; ++var1) {
			this.balls[var1].reset();
		}

	}

	public void run() {
		System.out.println("Game thread started...\n");
		this.sleepMS = 30;

		while(Thread.currentThread() == this.thread) {
			long var3 = System.currentTimeMillis();
			if (this.started) {
				this.checkCollisions();
				this.checkCaught();
				this.moveFlyers();
				if (this.timer > 0) {
					this.timer += -1;
					if (this.timer == 1) {
						this.drawScores(this.getGraphics());
					}
				}

				if (this.goldBalls > 0 && var3 - this.startTime > (long)this.duration) {
					for(int var5 = 0; var5 < this.goldBalls; ++var5) {
						this.balls[this.redBalls + this.blackBalls + var5].alive = true;
					}
				}
			}

			this.repaint();
			long var9 = System.currentTimeMillis() - var3;
			long var1;
			if (this.started) {
				var1 = (long)this.sleepMS;
			} else {
				var1 = 1000L;
			}

			if (var9 < var1) {
				try {
					Thread.sleep(var1 - var9);
				} catch (InterruptedException var8) {
					break;
				}
			}
		}

		System.out.println("thread done.");
	}

	public void moveFlyers() {
		int var1;
		for(var1 = 0; var1 < this.players.length; ++var1) {
			this.players[var1].move();
		}

		for(var1 = 0; var1 < this.balls.length; ++var1) {
			if (this.balls[var1].alive) {
				this.balls[var1].move();
			}
		}

	}

	public void checkCaught() {
		this.teamBasket[0] = false;
		this.teamBasket[1] = false;

		int var1;
		for(var1 = 0; var1 < this.balls.length; ++var1) {
			this.balls[var1].lastCaught = this.balls[var1].caught;
			this.balls[var1].caught = false;
		}

		for(var1 = 0; var1 < this.players.length; ++var1) {
			Person var2 = this.players[var1];

			for(int var3 = 0; var3 < this.balls.length; ++var3) {
				Ball var4 = this.balls[var3];
				if (var4.alive && var4.catchable) {
					int var5 = (int)(var2.x + 8.0F - var4.x);
					int var6 = (int)(var2.y + 8.0F - var4.y);
					if (Math.abs(var5) < 20 && Math.abs(var6) < 20) {
						if (var2.velocityX > 0.0F) {
							var4.x = var2.x + 18.0F;
						} else {
							var4.x = var2.x + 8.0F;
						}

						var4.y = var2.y + 15.0F;
						if (var4.isGoldBall) {
							this.teamScore[var2.side] += this.goldval;
							this.drawScores(this.getGraphics(), var2.side);
							this.gameOver();
						}

						this.teamBasket[var2.side] = true;
						if (this.teams && var2.passBall) {
							var4.pass = true;
							Person var7;
							if (var2.side == 0) {
								if (var1 == 0) {
									var7 = this.players[3];
								} else {
									var7 = this.players[0];
								}
							} else if (var1 == 1) {
								var7 = this.players[2];
							} else {
								var7 = this.players[1];
							}

							float var8 = var7.x - var2.x;
							float var9 = var7.y - var2.y;
							float var10 = (float)Math.sqrt((double)(var8 * var8 + var9 * var9));
							var4.velocityX = var8 / var10 * 8.0F;
							var4.velocityY = var9 / var10 * 8.0F;
							var4.x += 6.0F * var4.velocityX + (float)(FlyingObject.random.nextInt() % 5);
							var4.y += 6.0F * var4.velocityY + (float)(FlyingObject.random.nextInt() % 5);
						}

						var4.caught = true;
						if (this.soundToggle && !var4.lastCaught) {
							this.grabClip.play();
						}

						if (var2.side == 0 && var2.x > (float)(this.width - 17 - var2.w) || var2.side == 1 && !(var2.x >= 17.0F)) {
							var6 = (int)(var4.y - (float)this.midH);
							if (Math.abs(var6) < 20) {
								this.teamScore[var2.side] += 10;
								this.drawScores(this.getGraphics(), var2.side);
								this.timer = 15;
								if (this.soundToggle) {
									this.scoreClip.play();
								}

								var4.x = (float)this.midW;
								if (this.goldBalls == 0 && this.teamScore[var2.side] >= this.winScore) {
									this.gameOver();
									this.drawScores(this.getGraphics(), var2.side);
								}
							}
						}
					}
				}
			}

			var2.passBall = false;
		}

	}

	public void checkCollisions() {
		int var1;
		int var2;
		Person var3;
		int var5;
		int var6;
		for(var1 = 0; var1 < this.players.length; ++var1) {
			for(var2 = 0; var2 < this.players.length; ++var2) {
				if (var1 != var2) {
					var3 = this.players[var1];
					Person var4 = this.players[var2];
					var5 = (int)(var3.x - var4.x);
					var6 = (int)(var3.y - var4.y);
					if (Math.abs(var5) < var3.w && Math.abs(var6) < var3.h) {
						if (this.soundToggle && !(var3.y >= (float)(var3.bottom - var3.h - 50))) {
							this.bumpClip.play();
						}

						if (!(var3.y >= var4.y)) {
							var4.y = 1000.0F;
						} else if (var4.y > var3.y) {
							var3.y = 1000.0F;
						}
					}
				}
			}
		}

		for(var1 = 0; var1 < this.players.length; ++var1) {
			for(var2 = 0; var2 < this.balls.length; ++var2) {
				var3 = this.players[var1];
				Ball var7 = this.balls[var2];
				if (var7.alive && !var7.catchable) {
					var5 = (int)(var3.x + 8.0F - var7.x);
					var6 = (int)(var3.y + 8.0F - var7.y);
					if (Math.abs(var5) < 20 && Math.abs(var6) < 20) {
						if (this.soundToggle && !(var3.y >= (float)(var3.bottom - var3.h - 50))) {
							this.bumpClip.play();
						}

						var3.y = 1000.0F;
					}
				}
			}
		}

	}
}
