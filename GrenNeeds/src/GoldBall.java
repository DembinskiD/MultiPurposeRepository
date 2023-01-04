//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

class GoldBall extends Ball {
    GoldBall(BroomPanel var1, int var2, int var3, int var4) {
        super(var1, var2, var3, var4);
        this.init();
        super.smart = 1;
    }

    public void init() {
        System.out.println("GoldBall init()");
        super.w = 8;
        super.h = 8;
        super.isGoldBall = true;
        super.maxSpeed = 2.0F * super.bFrame.maxspeed;
        super.accel = 2.0F * super.bFrame.accel;
    }

    public void move() {
        for(int var1 = 0; var1 < super.bFrame.players.length; ++var1) {
            Person var2 = super.bFrame.players[var1];
            int var3 = (int)(super.x - var2.x);
            int var4 = (int)(super.y - var2.y);
            if (Math.abs(var3) < 100 && Math.abs(var4) < 100) {
                int var5 = FlyingObject.random.nextInt() % super.smart;
                if (var5 == 0) {
                    if (!(var2.x >= super.x)) {
                        this.right();
                    }

                    if (var2.x > super.x) {
                        this.left();
                    }

                    if (var2.y > super.y) {
                        this.up();
                    }

                    if (!(var2.y >= super.y)) {
                        this.down();
                    }
                }
            }
        }

        super.move();
    }

    public void down() {
        super.velocityY += super.accel;
        if (super.velocityY > super.maxSpeed) {
            super.velocityY = super.maxSpeed;
        }

    }
}
