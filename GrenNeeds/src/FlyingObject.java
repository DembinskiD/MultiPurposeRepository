//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Graphics;
import java.util.Random;

class FlyingObject {
    public int initX;
    public int initY;
    public float x;
    public float y;
    public int w;
    public int h;
    int right;
    int bottom;
    BroomPanel bFrame;
    Graphics g;
    public float velocityX;
    public float velocityY;
    public boolean catchable;
    public boolean isGoldBall;
    public boolean dive;
    public boolean pass;
    public float accel;
    public float maxSpeed;
    static Random random = new Random();
    public int smart;

    FlyingObject(BroomPanel var1, int var2, int var3) {
        this.x = (float)var2;
        this.y = (float)var3;
        this.initX = var2;
        this.initY = var3;
        this.bFrame = var1;
        this.g = this.bFrame.getGraphics();
        this.velocityX = 0.0F;
        this.velocityY = 0.0F;
        this.catchable = false;
        this.isGoldBall = false;
        this.pass = false;
        this.accel = this.bFrame.accel;
        this.maxSpeed = this.bFrame.maxspeed;
        this.dive = this.bFrame.dive;
        this.right = this.bFrame.width - 11;
        this.bottom = this.bFrame.height - 21;
    }

    public void reset() {
        this.x = (float)this.initX;
        this.y = (float)this.initY;
        this.velocityX = 0.0F;
        this.velocityY = 0.0F;
    }

    public void draw(Graphics var1) {
    }

    public void move() {
        this.x += this.velocityX;
        this.y += this.velocityY;
        if (!(this.velocityY >= 2.0F)) {
            this.velocityY += 0.1F;
        }

        this.bounds();
    }

    public void bounds() {
        boolean var1 = false;
        if (!(this.x >= 11.0F)) {
            this.x = 11.0F;
            this.velocityX = -this.velocityX;
            var1 = true;
        }

        if (this.x > (float)(this.right - this.w)) {
            this.x = (float)(this.right - this.w);
            this.velocityX = -this.velocityX;
            var1 = true;
        }

        if (!(this.y >= 31.0F)) {
            this.y = 31.0F;
            this.velocityY = -this.velocityY;
            if (this.velocityY == 0.0F) {
                this.velocityY += 0.1F;
            }

            var1 = true;
        }

        if (this.y > (float)(this.bottom - this.h - 10)) {
            this.y = (float)(this.bottom - this.h - 10);
            this.velocityY = 0.0F;
            this.velocityX = 0.0F;
            var1 = true;
        }

        if (var1) {
            this.pass = false;
        }

    }

    public void left() {
        this.velocityX -= this.accel;
        if (!(this.velocityX >= -this.maxSpeed)) {
            this.velocityX = -this.maxSpeed;
        }

    }

    public void right() {
        this.velocityX += this.accel;
        if (this.velocityX > this.maxSpeed) {
            this.velocityX = this.maxSpeed;
        }

    }

    public void up() {
        this.velocityY -= this.accel;
        if (!(this.velocityY >= -this.maxSpeed)) {
            this.velocityY = -this.maxSpeed;
        }

    }

    public void down() {
        if (this.dive) {
            this.velocityY += this.accel;
            if (this.velocityY > this.maxSpeed) {
                this.velocityY = this.maxSpeed;
            }

        }
    }
}
