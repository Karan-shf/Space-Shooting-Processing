import processing.core.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Main extends PApplet {

    public static int speedX=5;
    public static PApplet processing;
    public static int GameCondition = -5;
    public static int GameSpeed = 2;
    public static int GameScore = 0;
    public static int Lifes = 5;
    public static int Wave = 1;
    public static int SbulletsNum = 6;
    public static int welcomeStage=1;
    public static ArrayList<Block> Wave1_Blocks = new ArrayList<Block>();
    public static ArrayList<Block> Wave2_Blocks = new ArrayList<Block>();
    public static ArrayList<Block> Wave3_Blocks = new ArrayList<Block>();
    public static SpaceShip spaceShip;
    public static boolean savedInDatabase = false;
    public static Boss boss;
    public static Boss miniBoss1;
    public static Boss miniBoss2;
    public static Boss miniBoss3;
    public static PImage Game_BG1;
    public static PImage Game_BG2;
    public static PImage Game_BG3;
    public static PImage GameOver_BG;
    public static PImage Menu_BG;


    public static void main(String[] args) {
        PApplet.main("Main", args);
    }

    @Override
    public void setup() {
        processing = this;
        Block.CraeteBlocks();
        spaceShip = new SpaceShip(mouseX,255,255,255);
        boss = new Boss(300,300,-400,100000,232, 59, 117);
        Game_BG1 = loadImage("src_4/images/stars1.png");
        Game_BG2 = loadImage("src_4/images/stars2.png");
        Game_BG3 = loadImage("src_4/images/stars3.png");
        GameOver_BG = loadImage("src_4/images/GameOver.jpg");
        Menu_BG = loadImage("src_4/images/menu.jpg");
    }
    
    @Override
    public void settings() {
        size(750,700);
    }
    
    @Override
    public void draw() {
        // System.out.println(GameScore);
        switch (GameCondition) {
            case -5:
                switch (welcomeStage) {
                    case 1:
                        WelcomeMenu();
                        break;
                    case 2:
                        ChooseGameSpeed();
                        break;
                    case 3:
                        //top scores
                        showTopScores();
                        break;
                    case 4:
                        //help
                        HelpPleaaaaase();
                        break;
                }
                break;
            case 0:
                if (frameCount%30>=0 && frameCount%30<10){
                    background(Game_BG1);
                } else if (frameCount>=10 && frameCount%30<20) {
                    background(Game_BG2);
                } else {
                    background(Game_BG3);
                }
                stroke(209,59,187);
                line(0,670,1100,670);
                switch (Wave) {
                    case 1:
                        PlayGame(Wave1_Blocks);
                        break;
                    case 2:
                    if (Block.wave2time<=210) {
                            SpaceShip.bullets.clear();
                            SpaceShip.specialBullets.clear();
                            spaceShip.showObject();
                            spaceShip.setSpaceShipX(mouseX);
                            fill(205,0,0);
                            textSize(120);
                            if (frameCount%30>=15 && frameCount%30<30) {
                                text("wave "+Wave+" !!!!",140,350);
                            }
                            Block.wave2time++;
                        }  else {
                            PlayGame(Wave2_Blocks);
                        }
                        break;
                    case 3:
                    if (Block.wave3time<=210) {
                            SpaceShip.bullets.clear();
                            SpaceShip.specialBullets.clear();
                            spaceShip.showObject();
                            spaceShip.setSpaceShipX(mouseX);
                            fill(205,0,0);
                            textSize(120);
                            if (frameCount%30>=15 && frameCount%30<30) {
                                text("wave "+Wave+" !!!!",140,350);
                            }
                            Block.wave3time++;
                        } else {
                            PlayGame(Wave3_Blocks);
                        }
                        break;
                    case 4:
                    if (Block.wave4time<=210) {
                            SpaceShip.bullets.clear();
                            SpaceShip.specialBullets.clear();
                            spaceShip.showObject();
                            spaceShip.setSpaceShipX(mouseX);
                            changeSpaceShipColor();
                            fill(205,0,0);
                            textSize(90);
                            if (frameCount%60>=30 && frameCount%60<60) {
                                text("Boss Incoming !!!!",50,320);
                                text("Be Aware !!!",50,400);
                            }
                            Block.wave4time++;
                        } else {
                            BossFight();
                        }
                        break;
                }
                
                break;
            case -1:
            
                GameOver();
                break;
            case 1:
                
                GameWon();
                break;    
        }
    }

    public void WelcomeMenu() {
        
        background(Menu_BG);
        
        fill(255);
        textSize(60);
        text("Welcome to Space Shooting",25,100);

        int boxR = 0;
        int boxG = 255;
        int boxB = 0;
        
        if (mouseX>260 && mouseX<260+200 && mouseY>200 && mouseY<200+80) {
            boxR = 25;
            boxG = 166;
            boxB = 168;

            if (mousePressed && mouseButton==LEFT) {
                boxR = 145;
                boxG = 16;
                boxB = 61;
            }

        }

        fill(boxR, boxG, boxB);
        rect(260, 200, 200, 80);
        fill(ARGB);
        textSize(40);
        text("Start Game", 265, 250);

        boxR = 0;
        boxG = 255;
        boxB = 0;

        if (mouseX>260 && mouseX<260+200 && mouseY>320 && mouseY<320+80) {
            boxR = 25;
            boxG = 166;
            boxB = 168;

            if (mousePressed && mouseButton==LEFT) {
                boxR = 145;
                boxG = 16;
                boxB = 61;
            }
        } 

        fill(boxR, boxG, boxB);
        rect(260, 320, 200, 80);
        fill(ARGB);
        textSize(40);
        text("Settings", 290, 370);

        boxR = 0;
        boxG = 255;
        boxB = 0;

        if (mouseX>260 && mouseX<260+200 && mouseY>440 && mouseY<440+80) {
            boxR = 25;
            boxG = 166;
            boxB = 168;

            if (mousePressed && mouseButton==LEFT) {
                boxR = 145;
                boxG = 16;
                boxB = 61;
            }
        } 

        fill(boxR, boxG, boxB);
        rect(260, 440, 200, 80);
        fill(ARGB);
        textSize(35);
        text("View Top", 295, 475);
        text("5 Scores", 295, 510);

        boxR = 0;
        boxG = 255;
        boxB = 0;

        if (mouseX>260 && mouseX<260+200 && mouseY>560 && mouseY<560+80) {
            boxR = 25;
            boxG = 166;
            boxB = 168;

            if (mousePressed && mouseButton==LEFT) {
                boxR = 145;
                boxG = 16;
                boxB = 61;
            }
        } 

        fill(boxR, boxG, boxB);
        rect(260, 560, 200, 80);
        fill(ARGB);
        textSize(40);
        text("-Help-", 305, 610);
    }

    @Override
    public void mouseReleased() {
        if (GameCondition==-5) {
            switch (welcomeStage) {
                case 1:
                    if (mouseX>260 && mouseX<260+200 && mouseY>200 && mouseY<200+80) {
                        GameCondition=0;
                    }
                    if (mouseX>260 && mouseX<260+200 && mouseY>320 && mouseY<320+80) {
                        //settings
                        welcomeStage=2;
                    }
                    if (mouseX>260 && mouseX<260+200 && mouseY>440 && mouseY<440+80) {
                        //top 5 scores
                        welcomeStage=3;
                    }
                    if (mouseX>260 && mouseX<260+200 && mouseY>560 && mouseY<560+80) {
                        //help help
                        welcomeStage=4;
                    }
                    break;
                case 2:
                    if (mouseX>40 && mouseX<40+200 && mouseY>500 && mouseY<500+80) {
                        welcomeStage=1;
                    }
                    break;
                case 3:
                    if (mouseX>30 && mouseX<30+200 && mouseY>500 && mouseY<500+80) {
                        welcomeStage=1;
                    }
                    break;
                case 4:
                    if (mouseX>20 && mouseX<20+200 && mouseY>610 && mouseY<610+60) {
                        welcomeStage=1;
                    }
                    break;
                    
            }
        }
    }

    public void HelpPleaaaaase() {
        background(Menu_BG);
        fill(255);
        textSize(60);
        text("Dodge Blocks",10,50);
        stroke(255);
        line(5, 70, 500, 70);
        textSize(40);
        text("move your mouse left and right to", 10, 110);
        text("avoid the blocks", 10, 150);

        textSize(60);
        text("Shoot Blocks",10,220);
        stroke(255);
        line(5, 230, 500, 230);
        textSize(40);
        text("left click to shoot bullets", 10, 270);
        text("hold right click to shoot special bullets", 10, 310);
        text("  (you can only use 6 special bullets)", 10, 350);
        text("*", 10, 360);

        textSize(60);
        text("Final Boss",10,420);
        stroke(255);
        line(5, 430, 500, 430);
        textSize(40);
        text("you'll die immediately upon crash with boss", 10, 470);
        text("main boss will be devide into 3 mini bosses", 10, 510);
        text("after defeat", 10, 550);
        text("  (you can't use special attack in boss fight)", 10, 590);
        text("*", 10, 600);

        noStroke();
        int boxR = 156;
        int boxG = 11;
        int boxB = 42;

        if (mouseX>20 && mouseX<20+200 && mouseY>610 && mouseY<610+60) {
            boxR = 25;
            boxG = 166;
            boxB = 168;
            if (mousePressed && mouseButton==LEFT) {
                boxR = 78;
                boxG = 78;
                boxB = 78;
            }
        }

        fill(boxR, boxG, boxB);
        rect(20, 610, 200, 60);
        fill(0);
        textSize(60);
        text("Back", 50, 660);
    }

    public void ChooseGameSpeed() {

        background(Menu_BG);

        int Slow_box_R=0;
        int Slow_box_G=0;
        int Slow_box_B=0;
        int Normal_box_R=0;
        int Normal_box_G=0;
        int Normal_box_B=0;
        int Fast_box_R=0;
        int Fast_box_G=0;
        int Fast_box_B=0;

        switch (GameSpeed) {
            case 1:
                Slow_box_R=8;
                Slow_box_G=153;
                Slow_box_B=8;
                Normal_box_R=173;
                Normal_box_G=10;
                Normal_box_B=48;
                Fast_box_R=173;
                Fast_box_G=10;
                Fast_box_B=48;
                break;
            case 2:
                Slow_box_R=173;
                Slow_box_G=10;
                Slow_box_B=48;
                Normal_box_R=8;
                Normal_box_G=153;
                Normal_box_B=8;
                Fast_box_R=173;
                Fast_box_G=10;
                Fast_box_B=48;
                break;
            case 3:
                Slow_box_R=173;
                Slow_box_G=10;
                Slow_box_B=48;
                Normal_box_R=173;
                Normal_box_G=10;
                Normal_box_B=48;
                Fast_box_R=8;
                Fast_box_G=153;
                Fast_box_B=8;
                break;
        }

        fill(255);
        textSize(80);
        text("Choose Game Speed",30,100);
        stroke(255);
        line(20, 120, 730, 120);

        
        fill(Slow_box_R, Slow_box_G, Slow_box_B);
        rect(40, 150, 180, 80);
        fill(0);
        textSize(60);
        text("Slow", 65, 210);

        if (mouseX>40 && mouseX <40+180 && mouseY>150 && mouseY<150+80) {
            if (mousePressed && mouseButton==LEFT) {
                GameSpeed=1;
            }
        }

        fill(Normal_box_R, Normal_box_G, Normal_box_B);
        rect(270, 150, 180, 80);
        fill(0);
        textSize(55);
        text("Normal", 275, 210);

        if (mouseX>270 && mouseX <270+180 && mouseY>150 && mouseY<150+80) {
            if (mousePressed && mouseButton==LEFT) {
                GameSpeed=2;
            }
        }

        fill(Fast_box_R, Fast_box_G, Fast_box_B);
        rect(500, 150, 180, 80);
        fill(0);
        textSize(60);
        text("Fast", 535, 210);

        if (mouseX>500 && mouseX <500+180 && mouseY>150 && mouseY<150+80) {
            if (mousePressed && mouseButton==LEFT) {
                GameSpeed=3;
            }
        }

        noStroke();
        int boxR = 156;
        int boxG = 11;
        int boxB = 42;

        if (mouseX>40 && mouseX<40+200 && mouseY>500 && mouseY<500+80) {
            boxR = 25;
            boxG = 166;
            boxB = 168;
            if (mousePressed && mouseButton==LEFT) {
                boxR = 78;
                boxG = 78;
                boxB = 78;
            }
        }
        fill(boxR, boxG, boxB);
        rect(40, 500, 200, 80);
        fill(0);
        textSize(60);
        text("Back", 75, 560);

    }

    public void showTopScores() {
        background(Menu_BG);
        ArrayList<Integer> highscores = CheckHighScore();
        
        fill(197, 230, 34);
        textSize(80);
        text("Top 5 Scores:",30,100);
        stroke(156, 11, 69);
        line(20, 120, 500, 120);
        textSize(60);
        text("1. "+highscores.get(0),30,200);
        text("2. "+highscores.get(1),30,260);
        text("3. "+highscores.get(2),30,320);
        text("4. "+highscores.get(3),30,380);
        text("5. "+highscores.get(4),30,440);

        noStroke();

        int boxR = 156;
        int boxG = 11;
        int boxB = 42;

        if (mouseX>30 && mouseX<30+200 && mouseY>500 && mouseY<500+80) {
            boxR = 25;
            boxG = 166;
            boxB = 168;
            if (mousePressed && mouseButton==LEFT) {
                boxR = 78;
                boxG = 78;
                boxB = 78;
            }
        }

        fill(boxR, boxG, boxB);
        rect(30, 500, 200, 80);
        fill(ARGB);
        textSize(50);
        text("Back",70,555);
        
    }

    public void PlayGame(ArrayList <Block> wave_Blocks) {

        ConstructSbullets();
        MoveSbullets();
        CheckSbulletCrash(wave_Blocks);

        for (Block block : wave_Blocks) {
            block.SetColor();
            block.showObject();
            
        }
        MoveBlocks(wave_Blocks);

        spaceShip.setSpaceShipX(mouseX);
        if (!spaceShip.timeBoolean) {
            spaceShip.showObject();
        } else {
            spaceShip.time++;
            if (spaceShip.time%30<=30 && spaceShip.time%30>=15) {
                spaceShip.showObject();
            }
            if (spaceShip.time>150) {
                spaceShip.time=0;
                spaceShip.timeBoolean = false;
            }
        }

        if (mousePressed && mouseButton==LEFT) {
            if (frameCount%10==0) {

                Bullet bullet1 = new Bullet(spaceShip.getSpaceShipX()-2);
                Bullet bullet2 = new Bullet(spaceShip.getSpaceShipX()+2);
                
                SpaceShip.bullets.add(bullet1);
                SpaceShip.bullets.add(bullet2);
            }

            // bullet1.showObject();
            // bullet2.showObject();
        }

        for (Bullet bullet : SpaceShip.bullets) {
            bullet.showObject();
            bullet.setBulletY(bullet.getBulletY()-3);
        }

        CheckCrash(wave_Blocks);
        CheckWave();
        RemoveOutlineBullets();
        changeSpaceShipColor();
        fill(26, 9, 176);
        textSize(30);
        text("Score: "+GameScore,10,40);
        text("Lifes: "+Lifes,10,70);
        text("Wave: "+Wave,10,100);
        text("Special Attack: "+SbulletsNum,10,130);
        PrintGameSpeed();
    }

    public void BossFight() {

        switch (boss.stage) {

            case 1:

                if (mousePressed && mouseButton==LEFT) {

                    if (frameCount%10==0) {

                        Bullet bullet1 = new Bullet(spaceShip.getSpaceShipX()-2);
                        Bullet bullet2 = new Bullet(spaceShip.getSpaceShipX()+2);
                        
                        SpaceShip.bullets.add(bullet1);
                        SpaceShip.bullets.add(bullet2);
                    }

                }

                for (Bullet bullet : SpaceShip.bullets) {
                    bullet.showObject();
                    bullet.setBulletY(bullet.getBulletY()-3);
                } 
                
                boss.showObject();
                CheckBossCrash(boss);
                // MoveBoss(boss);

                spaceShip.setSpaceShipX(mouseX);
                spaceShip.showObject();
                RemoveOutlineBullets();

                if (boss.getHP()<=0) {
                    GameScore+=10000;
                    boss.stage=2;
                }

                fill(26, 9, 176);
                textSize(30);
                text("Score: "+GameScore,10,40);
                text("Lifes: "+Lifes,10,70);
                text("Wave: "+Wave,10,100);
                
                fill(255, 0, 0);
                rect(125, 50, (boss.getHP()/100000)*500 , 30);
                // length= (float) (boss.getHP()/10000);
                // System.out.println(length);
                // length*=5000;

                //(float)(boss.getHP()/100000)*500
                break;
            case 2:
                miniBoss1 = new Boss(100, boss.getBossX(), boss.getBossY(), 5000,232, 59, 117);
                miniBoss2 = new Boss(100, boss.getBossX(), boss.getBossY(), 5000,232, 59, 117);
                miniBoss3 = new Boss(100, boss.getBossX(), boss.getBossY(), 5000,232, 59, 117);
                miniBoss1.downX=false;
                miniBoss2.downY=false;
                boss.stage=3;
                break;
            case 3:

                if (mousePressed && mouseButton==LEFT) {

                    if (frameCount%10==0) {

                        Bullet bullet1 = new Bullet(spaceShip.getSpaceShipX()-2);
                        Bullet bullet2 = new Bullet(spaceShip.getSpaceShipX()+2);
                        
                        SpaceShip.bullets.add(bullet1);
                        SpaceShip.bullets.add(bullet2);
                    }

                }

                for (Bullet bullet : SpaceShip.bullets) {
                    bullet.showObject();
                    bullet.setBulletY(bullet.getBulletY()-3);
                } 
                
                if (miniBoss1.getHP()>0) {
                    miniBoss1.showObject();
                    // MoveBoss(miniBoss1);
                    CheckBossCrash(miniBoss1);
                    fill(255, 0, 0);
                    rect(225, 50, (miniBoss1.getHP()/5000)*300 , 15);
                    
                    
                } else if (!boss.defeated) {
                    GameScore+=1000;
                    boss.defeated = true;
                }
                if (miniBoss2.getHP()>0) {
                    miniBoss2.showObject();
                    // MoveBoss(miniBoss2);
                    CheckBossCrash(miniBoss2);
                    fill(255, 0, 0);
                    rect(225, 70, (miniBoss2.getHP()/5000)*300 , 15);
                } else if (!boss.defeated) {
                    GameScore+=1000;
                    boss.defeated = true;
                }
                if (miniBoss3.getHP()>0) {
                    miniBoss3.showObject();
                    // MoveBoss(miniBoss3);
                    CheckBossCrash(miniBoss3);
                    fill(255, 0, 0);
                    rect(225, 90, (miniBoss3.getHP()/5000)*300 , 15);
                } else if (!boss.defeated) {
                    GameScore+=1000;
                    boss.defeated = true;
                }

                if (miniBoss1.getHP()<=0 && miniBoss2.getHP()<=0 && miniBoss3.getHP()<=0) {
                    GameCondition = 1;
                }

                spaceShip.setSpaceShipX(mouseX);
                spaceShip.showObject();
                RemoveOutlineBullets();

                fill(26, 9, 176);
                textSize(30);
                text("Score: "+GameScore,10,40);
                text("Lifes: "+Lifes,10,70);
                text("Wave: "+Wave,10,100);
                // fill(255, 0, 0);
                // rect(50, 50, ( (float) (boss.getHP()/10000))*500 , 30);
                break;
            default:
                break;
        }
    }

    public void MoveBlocks(ArrayList<Block> Blocks) {
        for (Block block : Blocks) {
            block.setBlockY(block.getBlockY()+(int)GameSpeed);
        }
    }

    // public void MoveBoss(Boss stageBoss) {
    //     if (stageBoss.downY) {
    //         // BossY+=2;
    //         stageBoss.setBossY(stageBoss.getBossY()+2);
    //     } else {
    //         // BossY-=2;
    //         stageBoss.setBossY(stageBoss.getBossY()-2);
    //     }
    //     if (boss.downX) {
    //         // BossX-=2;
    //         stageBoss.setBossX(stageBoss.getBossX()-2);
    //     } else {
    //         // BossX+=2;
    //         stageBoss.setBossX(stageBoss.getBossX()+2);
    //     }
    //     if (stageBoss.getBossY()+stageBoss.getExtent()/2==700) {
    //         stageBoss.downY = false;
    //     } 
    //     if (stageBoss.getBossY()-stageBoss.getExtent()/2==0) {
    //         stageBoss.downY= true;
    //     }
    //     if (stageBoss.getBossX()-stageBoss.getExtent()/2==0) {
    //         stageBoss.downX = false;
    //     }
    //     if (stageBoss.getBossX()+stageBoss.getExtent()/2==750) {
    //         stageBoss.downX = true;
    //     }
    // }

    public void CheckWave(){
        if (Wave1_Blocks.size()==0) {
            Wave = 2;
            // GameSpeed=3;
        }
        if (Wave1_Blocks.size()==0 && Wave2_Blocks.size()==0) {
            Wave = 3;
            // GameSpeed=4;
        }
        if (Wave1_Blocks.size()==0 && Wave2_Blocks.size()==0 && Wave3_Blocks.size()==0) {
            Wave = 4;
        }
    }

    public void CheckCrash(ArrayList<Block> Blocks){
       

        for (Block block : Blocks) {

            //check bullet crash
            for (int i=0;i<SpaceShip.bullets.size();i++) {
                
                if (SpaceShip.bullets.get(i).getBulletX()+Bullet.getBulletWidth()>block.getBlockX() && SpaceShip.bullets.get(i).getBulletX()<block.getBlockX()+Block.getBlockWidth()
                 && SpaceShip.bullets.get(i).getBulletY()+Bullet.getBulletHeight()>block.getBlockY() && SpaceShip.bullets.get(i).getBulletY()<block.getBlockY()+Block.getBlockHeight() ) {
                    block.SetHP(block.GetHP()-Bullet.getDamage());
                    switch (GameSpeed){
                        case 1:
                            GameScore+=5;
                            break;
                        case 2:
                            GameScore+=10;
                            break;
                        case 3:
                            GameScore+=20;
                            break;
                    }
                    SpaceShip.bullets.remove(i);
                }
            }

            //check main body crash
            if (spaceShip.getSpaceShipX()-35<block.getBlockX()+Block.getBlockWidth() && spaceShip.getSpaceShipX()+35>block.getBlockX()
             && 646<block.getBlockY()+Block.getBlockHeight() && 670>block.getBlockY()) {
                
                if (!block.killed && !spaceShip.timeBoolean) { 
                    Lifes--;
                    GameScore-=100;
                    background(250,0,0);
                    block.killed=true;

                    spaceShip.timeBoolean=true;
                }
            }

            //check left wing crash
            if (spaceShip.getSpaceShipX()-35<block.getBlockX()+Block.getBlockWidth() && spaceShip.getSpaceShipX()-25>block.getBlockX()
             && 545+70<block.getBlockY()+Block.getBlockHeight() && 545+70+35>block.getBlockY()) {
               
                if (!block.killed && !spaceShip.timeBoolean) { 
                    Lifes--;
                    GameScore-=100;
                    background(250,0,0);
                    block.killed=true;

                    spaceShip.timeBoolean=true;
                }

            }

            //check right wing crash
            if (spaceShip.getSpaceShipX()+25<block.getBlockX()+Block.getBlockWidth() && spaceShip.getSpaceShipX()+35>block.getBlockX()
             && 545+70<block.getBlockY()+Block.getBlockHeight() && 545+70+35>block.getBlockY()) {
               
                if (!block.killed && !spaceShip.timeBoolean) { 
                    Lifes--;
                    GameScore-=100;
                    background(250,0,0);
                    block.killed=true;

                    spaceShip.timeBoolean=true;
                }
            }

            if (Lifes<=0) {
                GameCondition = -1;
            }
            
        }

        // remove destroyed blocks
        for (int i = 0; i < Blocks.size(); i++) {
            if (Blocks.get(i).GetHP()<=0) {
                Blocks.remove(i);
                GameScore+=200;
                break;
            }
            if (Blocks.get(i).getBlockY() > 700) {
                Blocks.remove(i);
                break;
            }
        }
        
    }

    public void CheckBossCrash(Boss stageBoss) {

        //check bullet crash
        for (int i=0;i<SpaceShip.bullets.size();i++) {


            // System.out.println("help help");
            
            if (SpaceShip.bullets.get(i).getBulletX()+Bullet.getBulletWidth()>stageBoss.getBossX()-stageBoss.getExtent()/2 && SpaceShip.bullets.get(i).getBulletX()<stageBoss.getBossX()+stageBoss.getExtent()/2
                && SpaceShip.bullets.get(i).getBulletY()+Bullet.getBulletHeight()>stageBoss.getBossY()-stageBoss.getExtent()/2 && SpaceShip.bullets.get(i).getBulletY()<stageBoss.getBossY()+stageBoss.getExtent()/2 -30) {
                stageBoss.setHP(stageBoss.getHP()-Bullet.getDamage());
                GameScore+=10;
                SpaceShip.bullets.remove(i);
                // System.out.println("hit hit hit");
            }
        }



        //check main body crash
        if (spaceShip.getSpaceShipX()-35<stageBoss.getBossX()+stageBoss.getExtent()/2 && spaceShip.getSpaceShipX()+35>stageBoss.getBossX()-stageBoss.getExtent()/2
            && 646<stageBoss.getBossY()+stageBoss.getExtent()/2 && 670>stageBoss.getBossY()-stageBoss.getExtent()/2) {
            
            GameCondition=-1;
            
        } 

        // //check left wing crash
        if (spaceShip.getSpaceShipX()-35<stageBoss.getBossX()+stageBoss.getExtent()/2 && spaceShip.getSpaceShipX()-25>stageBoss.getBossX()-stageBoss.getExtent()/2
            && 545+70<stageBoss.getBossY()+stageBoss.getExtent()/2 && 545+70+35>stageBoss.getBossY()-stageBoss.getExtent()/2) {
            
            GameCondition=-1;
             
        } 

        // //check right wing crash
        if (spaceShip.getSpaceShipX()+25<stageBoss.getBossX()+stageBoss.getExtent()/2 && spaceShip.getSpaceShipX()+35>stageBoss.getBossX()-stageBoss.getExtent()/2
            && 545+70<stageBoss.getBossY()+stageBoss.getExtent()/2 && 545+70+35>stageBoss.getBossY()-stageBoss.getExtent()/2) {
                 
            GameCondition=-1;
            
        }
        
    }

    public void RemoveOutlineBullets() {
        for (int i = 0; i < SpaceShip.bullets.size(); i++) {
            if (SpaceShip.bullets.get(i).getBulletY()<0) {
                SpaceShip.bullets.remove(i);
            }
        }
    }

    public void ConstructSbullets(){
        if (mousePressed && mouseButton==RIGHT) {
            if (SbulletsNum>0) {
                if (frameCount%30==0) {
                    SpecialBullet specialBullet;
                    if (SbulletsNum%2==0) {
                        specialBullet = new SpecialBullet(spaceShip.getSpaceShipX()-35+10/2, 615-10/2, 10, 99, 14, 156);
                    } else {
                        specialBullet = new SpecialBullet(spaceShip.getSpaceShipX()+25+10/2, 615-10/2, 10, 99, 14, 156);
                    }
                    SbulletsNum--;
                    SpaceShip.specialBullets.add(specialBullet);
                }
            }
        }
    }

    public void MoveSbullets(){
        for (SpecialBullet specialBullet : SpaceShip.specialBullets) {
            
            specialBullet.showObject();
            if (specialBullet.move) {
                specialBullet.setSpecialBulletY(specialBullet.getSpecialBulletY()-1);
            }
            
        }
    }

    public void CheckSbulletCrash(ArrayList<Block> Blocks) {
        for (SpecialBullet specialBullet : SpaceShip.specialBullets) {
            for (Block block : Blocks) {
                if (specialBullet.getSpecialBulletX()+specialBullet.getExtent()/2>block.getBlockX() && specialBullet.getSpecialBulletX()-specialBullet.getExtent()/2<block.getBlockX()+Block.getBlockWidth()
                 && specialBullet.getSpecialBulletY()+specialBullet.getExtent()/2>block.getBlockY() && specialBullet.getSpecialBulletY()-specialBullet.getExtent()/2<block.getBlockY()+Block.getBlockHeight()) {
                    specialBullet.specialPower(block);
                    // specialBullet.time++;
                    specialBullet.timeBoolean=true;
                    specialBullet.move= false;
                    // System.out.println("hit hit hit");
                }
            }
            if (specialBullet.timeBoolean) {
                specialBullet.time++;
            }
        }
        for (int i = 0; i < SpaceShip.specialBullets.size(); i++) {
            if (SpaceShip.specialBullets.get(i).getSpecialBulletY()<=0 || SpaceShip.specialBullets.get(i).time>300){
                SpaceShip.specialBullets.remove(i);
            }
        }
    }

    public void GameOver(){
        background(GameOver_BG);
        fill(255);
        PFont font = createFont("src_4/fonts/Pixeled.ttf", 50);
        
        // textSize(70);
        // text("You Lost! :(",30,250);
        // textSize(40);
        textFont(font);
        text("Your Score: "+GameScore,10,550);
    }

    public void GameWon() {

        ArrayList<Integer> highscores = CheckHighScore();
       
        background(0,0,0);
        PFont font = createFont("Pixeled.ttf", 50);
        textFont(font);
        if (GameScore>=highscores.get(0)){
            if (frameCount%60>=0 && frameCount%60<30) {
                fill(181, 24, 31);
                textSize(40);
                text("NEW HIGH SCORE!!!",30,80);
            }
        }
        fill(19, 110, 43);
        textSize(50);
        text("Congratulations",30,160);
        text("You Won!!",30,230);
        textSize(35);
        text("Your Final Score: "+GameScore,30,300);
        text("Remaining Lives "+Lifes,30,360);
        
        if (!savedInDatabase) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SpaceShooting",
                "root", "root1234");
                String sql = "INSERT INTO Scores VALUES (?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, GameScore);
                preparedStatement.executeUpdate();
                connection.close();
               
                savedInDatabase = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
        highscores = CheckHighScore();
        fill(126, 18, 130);
        text("Top 5 Scores:",30,430);
        textSize(30);
        text("1. "+highscores.get(0),30,480);
        text("2. "+highscores.get(1),30,530);
        text("3. "+highscores.get(2),30,580);
        text("4. "+highscores.get(3),30,630);
        text("5. "+highscores.get(4),30,680);

    }

    public void PrintGameSpeed() {

        textSize(30);

        switch (GameSpeed) {
            case 1:
                text("Game Speed: Slow",10,160);
                break;
            case 2:
                text("Game Speed: Normal",10,160);
                break;
            case 3:
                text("Game Speed: Fast",10,160);
                break;
        }

    }

    public ArrayList<Integer> CheckHighScore() {
        ArrayList<Integer> HighScores = new ArrayList<Integer>();
        String sql = "SELECT Score FROM Scores ORDER BY Score DESC LIMIT 5" ;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SpaceShooting",
            "root", "root1234");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                HighScores.add(resultSet.getInt(1));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HighScores;
    }

    public void changeSpaceShipColor(){

        switch (Lifes) {
            
            case 5:
                spaceShip.setRGB(255,255,255);
                break;
            case 4:
                spaceShip.setRGB(235, 176, 167);
                break;
            case 3:
                spaceShip.setRGB(230, 146, 133);
                break;
            case 2:
                spaceShip.setRGB(227, 111, 93);
                break;
            case 1:
                spaceShip.setRGB(219, 69, 46);
                break;
        }
           
    }
    
}
