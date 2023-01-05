//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Graphics;

class Ball extends FlyingObject {
    int model;
    boolean caught;
    boolean lastCaught;
    boolean alive;

    Ball(BroomPanel var1, int var2, int var3, int var4) {
        super(var1, var3, var4);
        this.model = var2;
        this.alive = true;
        this.caught = false;
        this.lastCaught = false;
        this.init();
    }

    public void init() {
        System.out.println("Ball init()");
        super.w = 16;
        super.h = 16;
    }

    public void move() {
        if (super.pass) {
            super.move();
        } else {
            int var1 = FlyingObject.random.nextInt() % 20;
            if (var1 == 0) {
                this.up();
            }

            if (var1 == 1) {
                this.right();
            }

            if (var1 == 2) {
                this.left();
            }

            if (super.y > (float)(super.bFrame.height - 90)) {
                this.up();
            }

            super.move();
        }
    }

    public void draw(Graphics var1) {
        var1.drawImage(super.bFrame.ballImg[this.model], (int)super.x, (int)super.y, super.bFrame);
    }
}
