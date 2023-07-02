public class Boss implements ISpaceObject {

    private int Extent;

    private int BossX;
    private int BossY;

    private float HP;

    // private static int speedY; 

    private int R;
    private int G;
    private int B;

    int stage = 1;

    boolean downY = true;
    boolean downX = true;

    boolean defeated = false;

    public Boss(int extent, int bossX, int bossY,int Hp, int r, int g, int b) {
        Extent = extent;
        BossX = bossX;
        BossY = bossY;
        HP = Hp;
        R = r;
        G = g;
        B = b;
    }

    
    public int getExtent() {
        return Extent;
    }
    
    public void setExtent(int extent) {
        Extent = extent;
    }
    
    public int getBossX() {
        return BossX;
    }

    public void setBossX(int bossX) {
        BossX = bossX;
    }

    public int getBossY() {
        return BossY;
    }
    
    public void setBossY(int bossY) {
        BossY = bossY;
    }
    
    public float getHP() {
        return HP;
    }
    
    public void setHP(float hP) {
        HP = hP;
    }

    public int getR() {
        return R;
    }
    
    public int getG() {
        return G;
    }
    
    public int getB() {
        return B;
    }

    public void setRGB(int r, int g, int b) {
        R = r;
        G = g;
        B = b;
    }
    
    @Override
    public void showObject() {
        Main.processing.fill(R, G, B);
        Main.processing.noStroke();
        Main.processing.circle(BossX, BossY, Extent);
        Main.processing.fill(0);
        Main.processing.textSize(45);
        Main.processing.text(Integer.toString((int) HP),BossX-50,BossY+15);
    
        if (downY) {
            BossY+=2;
        } else {
            BossY-=2;
        }
        if (downX) {
            BossX-=2;
        } else {
            BossX+=2;
        }
        if (BossY+Extent/2==700) {
            downY = false;
        } 
        if (BossY-Extent/2==0) {
            downY= true;
        }
        if (BossX-Extent/2==0) {
            downX = false;
        }
        if (BossX+Extent/2==750) {
            downX = true;
        }
    }

}
