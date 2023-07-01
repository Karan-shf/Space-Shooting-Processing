import java.util.concurrent.ThreadLocalRandom;

public class Block implements ISpaceObject{

    private static int BlockWidth = 40;
    private static int BlockHeight = 60;
    
    private int BlockX;
    private int BlockY;

    private int HP;

    public static int speedY; 

    private int R;
    private int G;
    private int B;

    public boolean killed;

    public static int wave2time=0;
    public static int wave3time=0;
    public static int wave4time=0;

    public Block(int blockX, int blockY,int Hp) {
        BlockX = blockX;
        BlockY = blockY;
        HP = Hp;
        // scored = false;
        killed = false;
    }

    public static void CraeteBlocks(){
        speedY = -80;

        for (int i=0;i<30;i++) {
            Main.Wave1_Blocks.add(
                new Block(ThreadLocalRandom.current().nextInt(0,350),
                speedY, 
                ThreadLocalRandom.current().nextInt(500,1000))
            );
            speedY -= 50;
            Main.Wave1_Blocks.add(
                new Block(ThreadLocalRandom.current().nextInt(351,700),
                speedY, 
                ThreadLocalRandom.current().nextInt(500,1000))
            );
            speedY -= 100;
            // Main.Wave1_Blocks.add(
            //     new Block(ThreadLocalRandom.current().nextInt(501,750),
            //     speedY, 
            //     ThreadLocalRandom.current().nextInt(500,1000))
            // );
            
        }

        speedY = -80;


        for (int i=0;i<30;i++) {
            Main.Wave2_Blocks.add(
                new Block(ThreadLocalRandom.current().nextInt(0,350),
                speedY, 
                ThreadLocalRandom.current().nextInt(1500,2000))
            );
            speedY -= 50;
            Main.Wave2_Blocks.add(
                new Block(ThreadLocalRandom.current().nextInt(351,700),
                speedY, 
                ThreadLocalRandom.current().nextInt(1500,2000))
            );
            speedY -= 100;
        }

        speedY = -80;

        for (int i=0;i<30;i++) {
            Main.Wave3_Blocks.add(
                new Block(ThreadLocalRandom.current().nextInt(0,350),
                speedY, 
                ThreadLocalRandom.current().nextInt(2500,3000))
            );
            speedY -= 50;
            Main.Wave3_Blocks.add(
                new Block(ThreadLocalRandom.current().nextInt(351,700),
                speedY, 
                ThreadLocalRandom.current().nextInt(2500,3000))
            );
            speedY -= 100;
        }
    }
    
    public static int getBlockWidth() {
        return BlockWidth;
    }
    
    public static int getBlockHeight() {
        return BlockHeight;
    }
    
    public int getBlockX() {
        return BlockX;
    }

    public void setBlockX(int blockX){
        BlockX = blockX;
    }
    
    public int getBlockY() {
        return BlockY;
    }

    public void setBlockY(int blockY) {
        BlockY = blockY;
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

    public void SetRGB(int r,int g,int b) {
        R=r;
        G=g;
        B=b;
    }

    public int GetHP() {
        return HP;
    }

    public void SetHP(int Hp) {
        HP = Hp;
    }

    public void SetColor() {
        if (HP<=200 && HP>0) {
            SetRGB(139, 242, 5);

        } else if (HP<=400 && HP>200) {
            SetRGB(159, 242, 5);

        } else if (HP<=600 && HP>400) {
            SetRGB(183, 242, 5);

        } else if (HP<=800 && HP>600) {
            SetRGB(199, 242, 5);

        } else if (HP<=1000 && HP>800) {
            SetRGB(218, 242, 5);

        } else if (HP<=1400 && HP>1200) {
            SetRGB(242, 242, 5);

        } else if (HP<=1600 && HP>1400) {
            SetRGB(242, 212, 5);

        } else if (HP<=1800 && HP>1600) {
            SetRGB(242, 180, 5);

        } else if (HP<=2000 && HP>1800) {
            SetRGB(242, 159, 5);

        } else if (HP<=2200 && HP>2000) {
            SetRGB(242, 131, 5);

        } else if (HP<=2400 && HP>2200) {
            SetRGB(242, 108, 5);

        } else if (HP<=2600 && HP>2400) {
            SetRGB(242, 84, 5);

        } else if (HP<=2800 && HP>2600) {
            SetRGB(242, 64, 5);

        } else if (HP<=3000 && HP>2800) {
            SetRGB(212, 32, 4);
        }
    }

    @Override
    public void showObject() {
        Main.processing.fill(R, G, B);
        Main.processing.noStroke();
        Main.processing.rect(BlockX,BlockY,Block.getBlockWidth(),Block.getBlockHeight());
        Main.processing.fill(0);
        Main.processing.textSize(20);
        Main.processing.text(Integer.toString(HP),BlockX,BlockY+(BlockHeight/2));
    }
    
}
