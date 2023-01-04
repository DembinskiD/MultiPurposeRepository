//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;

class Person extends FlyingObject {
    public int model;
    public int side;
    public boolean isRobot;
    Ball target;
    int upKey;
    int downKey;
    int leftKey;
    int rightKey;
    int modelKey;
    int robotKey;
    int passKey;
    int infoX;
    int infoY;
    boolean passBall;

    Person(Ball var1, BroomPanel var2, int var3, int var4, int var5) {
        super(var2, var4, var5);
        this.model = var3;
        this.target = var1;
        this.isRobot = false;
        super.smart = 15;
        this.init();
        this.upKey = 45;
        this.downKey = 45;
        this.leftKey = 45;
        this.rightKey = 45;
        this.modelKey = 45;
        this.robotKey = 45;
        this.passKey = 45;
        this.infoX = 0;
        this.infoY = 0;
        this.passBall = false;
    }

    public void setInfoXY(int var1, int var2) {
        this.infoX = var1;
        this.infoY = var2;
    }

    public void setKeys(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
        this.upKey = var1;
        this.downKey = var2;
        this.leftKey = var3;
        this.rightKey = var4;
        this.modelKey = var5;
        this.robotKey = var6;
        this.passKey = var7;
    }

    public void init() {
        System.out.println("Person init()");
        super.w = 38;
        super.h = 38;
        this.side = 0;
    }

    public void handleKeyEvent(Event var1, int var2) {
        if (this.isRobot) {
            if (var2 == this.leftKey) {
                this.dumber();
            }

            if (var2 == this.rightKey) {
                this.smarter();
            }

            if (var2 == this.robotKey) {
                this.isRobot = false;
                super.velocityX = 0.0F;
                super.bFrame.paint(super.bFrame.getGraphics());
            }

            if (var2 == this.passKey) {
                this.passBall = true;
                return;
            }
        } else {
            if (var2 == this.upKey) {
                this.up();
            }

            if (var2 == this.downKey) {
                this.down();
            }

            if (var2 == this.leftKey) {
                this.left();
            }

            if (var2 == this.rightKey) {
                this.right();
            }

            if (var2 == this.robotKey) {
                this.isRobot = true;
                super.bFrame.paint(super.bFrame.getGraphics());
            }

            if (var2 == this.modelKey) {
                this.switchModel();
            }

            if (var2 == this.passKey) {
                this.passBall = true;
            }
        }

    }

    public void toggleRobot() {
        if (this.isRobot) {
            this.isRobot = false;
            super.velocityX = 0.0F;
        } else {
            this.isRobot = true;
        }

        super.bFrame.paint(super.bFrame.getGraphics());
    }

    public void smarter() {
        super.smart -= 5;
        if (super.smart <= 1) {
            super.smart = 1;
        }

        this.drawInfo(super.bFrame.getGraphics());
    }

    public void dumber() {
        super.smart += 5;
        if (super.smart >= 30) {
            super.smart = 30;
        }

        this.drawInfo(super.bFrame.getGraphics());
    }

    public void move() {
        if (this.isRobot) {
            int var1 = FlyingObject.random.nextInt() % super.smart;
            if (var1 == 0) {
                if (super.bFrame.teamBasket[this.side]) {
                    if (this.side == 0) {
                        if (!(super.x >= (float)(super.bFrame.width - 50))) {
                            this.right();
                        }

                        if (super.y > (float)(super.bFrame.midH - 10)) {
                            this.up();
                        }
                    } else {
                        if (super.x > 50.0F) {
                            this.left();
                        }

                        if (super.y > (float)(super.bFrame.midH - 10)) {
                            this.up();
                        }
                    }
                } else {
                    if (!(this.target.y >= super.y)) {
                        this.up();
                    }

                    if (!(Math.abs(this.target.y - super.y) >= 100.0F)) {
                        if (!(this.target.x >= super.x - 10.0F)) {
                            this.left();
                        } else if (this.target.x > super.x + 10.0F) {
                            this.right();
                        }
                    }

                    if (this.target.y > super.y) {
                        this.down();
                    }
                }
            }
        }

        super.move();
    }

    public void switchModel() {
        ++this.model;
        if (this.model > 4) {
            this.model = 0;
        }

    }

    public void drawInfo(Graphics var1) {
        var1.setColor(super.bFrame.getBackground());
        var1.fillRect(this.infoX, this.infoY, 145, 15);
        if (!this.isRobot) {
            var1.setColor(Color.black);
            if (super.bFrame.teams) {
                if (this.upKey == 1004) {
                    var1.drawString("arrow-keys ENTER BACK", this.infoX, this.infoY + 10);
                } else if (this.upKey == 1000) {
                    var1.drawString("Home Del PD Ins PU NUM", this.infoX, this.infoY + 10);
                } else if (this.upKey == 101) {
                    var1.drawString("E X S F 1 2 T", this.infoX, this.infoY + 10);
                } else {
                    var1.drawString("I J L 7 8 P", this.infoX, this.infoY + 10);
                }
            } else if (this.upKey == 1004) {
                var1.drawString("arrow-keys ENTER", this.infoX, this.infoY + 10);
            } else if (this.upKey == 1000) {
                var1.drawString("Home Del PD Ins PU", this.infoX, this.infoY + 10);
            } else if (this.upKey == 101) {
                var1.drawString("E X S F 1 T", this.infoX, this.infoY + 10);
            } else {
                var1.drawString("I J L 7 P", this.infoX, this.infoY + 10);
            }
        } else {
            var1.setColor(Color.red);
            var1.fillRect(this.infoX + 30, this.infoY, 35 - super.smart, 10);
            var1.setColor(Color.black);
            var1.drawRect(this.infoX + 30, this.infoY, 34, 10);
            var1.drawString("skill:", this.infoX, this.infoY + 10);
            if (this.upKey == 1000) {
                var1.drawString("Del PD PU", this.infoX + 70, this.infoY + 10);
            } else if (this.upKey == 101) {
                var1.drawString("S F T", this.infoX + 70, this.infoY + 10);
            } else {
                var1.drawString("J L P", this.infoX + 70, this.infoY + 10);
            }
        }
    }

    public void draw(Graphics var1) {
        int var2;
        if (super.velocityX > 0.0F) {
            var2 = 0;
        } else if (!(super.velocityX >= 0.0F)) {
            var2 = 1;
        } else {
            var2 = this.side;
        }

        byte var3;
        if (super.velocityY >= 0.0F) {
            var3 = 0;
        } else {
            var3 = 1;
        }

        if (this.side == 0) {
            var1.drawImage(super.bFrame.img[this.model][var3][var2], (int)super.x, (int)super.y, super.bFrame);
        } else {
            var1.drawImage(super.bFrame.img[this.model + 5][var3][var2], (int)super.x, (int)super.y, super.bFrame);
        }
    }
}
