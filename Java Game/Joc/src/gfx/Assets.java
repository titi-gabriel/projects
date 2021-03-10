package gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 160, height = 160; //latime, inaltime standard

    public static Font font30, fontScore; //fontul pt text

    //referinte catre elementele grafice
    public static BufferedImage earth1, earth2, earth3,
            sign,
            cactus,
            box,
            tree,
            BG,BG2,foxBG1, foxBG2, gaaraBG1, gaaraBG2, menuBG, helpBG,loadingBG, deathBG,
            pergament,
            menu, menutext,
            transparent,
            loadingtext,
            inventoryscreen,
            naruto2,
            healthbar, chakrabar, hud, hudBoss1, hudBoss2,
            lava1, lava2, lava3, lava4, lava5, lava6, lava7, lavabox, warning, skull, lavatree, bush1, bush2;

    public static BufferedImage[] naruto_right, naruto_left, naruto_jump_right,naruto_jump_left,
            naruto_stance_right,naruto_stance_left,orb, startButton,helpButton, punchRight, punchLeft, kickRight, kickLeft,
            kunaiLeft, kunaiRight, kunaiFlyRight, kunaiFlyLeft,
            narutoRasenganRight,narutoRasenganLeft, rasengan,
            gaaraStance, gaaraAttack, sandBall, statue, fox, foxAttack, energyBall;


    //Functia initializaza referintele catre elementele grafice utilizate.
    public static void init()
    {
        //initializare fonturi
        font30 = FontLoader.loadFont("res/textures/njnaruto.ttf", 30);
        fontScore = FontLoader.loadFont("res/textures/njnaruto.ttf", 130);

        // Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/naruto.png"));
        SpriteSheet sheet1 = new SpriteSheet(ImageLoader.loadImage("/textures/narutobasics.png"));

        // Se obtin subimaginile corespunzatoare elementelor necesare.
        naruto_right = new BufferedImage[6];
        naruto_left = new BufferedImage[6];
        naruto_jump_right = new BufferedImage[4];
        naruto_jump_left = new BufferedImage[4];
        naruto_stance_right = new BufferedImage[6];
        naruto_stance_left = new BufferedImage[6];
        orb = new BufferedImage[4];
        punchRight = new BufferedImage[4];
        punchLeft = new BufferedImage[4];
        kickLeft = new BufferedImage[5];
        kickRight = new BufferedImage[5];
        kunaiLeft = new BufferedImage[5];
        kunaiRight = new BufferedImage[5];
        kunaiFlyLeft = new BufferedImage[2];
        kunaiFlyRight = new BufferedImage[2];
        startButton = new BufferedImage[2];
        helpButton = new BufferedImage[2];
        narutoRasenganRight = new BufferedImage[9];
        narutoRasenganLeft = new BufferedImage[9];
        rasengan = new BufferedImage[2];
        gaaraAttack = new BufferedImage[4];
        gaaraStance = new BufferedImage[5];
        sandBall = new BufferedImage[4];
        statue = new BufferedImage[2];
        fox = new BufferedImage[8];
        foxAttack = new BufferedImage[12];
        energyBall = new BufferedImage[6];

        for(int i=0;i<2;i++)
        {
            startButton[i] = sheet1.crop(i*2*width,11*height,2*width,height);
        }
        for(int i=0;i<2;i++)
        {
            helpButton[i] = sheet1.crop((i+2)*2*width,11*height,2*width,height);
        }
        for(int i=0;i<6;i++)
        {
            naruto_stance_right[i] = sheet1.crop(i*width,0*height,width,height );

        }
        for(int i=0;i<6;i++)
        {
            naruto_stance_left[i] = sheet1.crop((5-i)*width ,1*height,width,height );
        }
        for(int i=0;i<6;i++)
        {
            naruto_right[i] = sheet1.crop(i*width,2*height,width,height );
        }
        for(int i=0;i<6;i++)
        {
            naruto_left[i] = sheet1.crop((5-i)*width ,3*height,width,height );
        }
        for(int i=0;i<4;i++)
        {
            naruto_jump_right[i] = sheet1.crop(i*width,4*height,width,height );
        }
        for(int i=0;i<4;i++)
        {
            naruto_jump_left[i] = sheet1.crop((3-i)*width ,5*height,width,height );
        }
        for(int i=0; i<4;i++)
        {
            orb[i] = sheet1.crop(i*width,10*height, width,height);
        }
        for(int i=0;i<4;i++)
        {
            punchRight[i] = sheet1.crop(i*width,8*height,width,height);
        }
        for(int i=0;i<4;i++)
        {
            punchLeft[i] = sheet1.crop((3-i)*width ,9*height,width,height );
        }
        for(int i=0;i<5;i++)
        {
            kickRight[i] = sheet1.crop(i*width,12*height,width,height);
        }
        for(int i=0;i<5;i++)
        {
            kickLeft[i] = sheet1.crop((4-i)*width ,13*height,width,height );
        }

        for(int i=0;i<5;i++)
        {
            kunaiRight[i] = sheet1.crop(i*width,6*height,width,height);
        }
        for(int i=0;i<5;i++)
        {
            kunaiLeft[i] = sheet1.crop((4-i)*width ,7*height,width,height );
        }

        for(int i=0;i<2;i++)
        {
            kunaiFlyRight[i] = sheet1.crop(i*width,14*height,width,height);
        }
        for(int i=0;i<2;i++)
        {
            kunaiFlyLeft[i] = sheet1.crop((1-i)*width ,15*height,width,height );
        }

        for(int i=0;i<9;i++)
        {
            narutoRasenganRight[i] = sheet1.crop(i*width,16*height,width,height);
        }

        for(int i=0;i<9;i++)
        {
            narutoRasenganLeft[i] = sheet1.crop((8-i)*width,19*height,width,height);
        }

        for(int i=0;i<2;i++)
        {
            rasengan[i] = sheet1.crop(i*width,23*height,width,height);
        }

        for(int i=0;i<5;i++)
        {
            gaaraStance[i] = sheet1.crop((i+8)*width,0,width,height);
        }

        for(int i=0;i<4;i++)
        {
            gaaraAttack[i] = sheet1.crop((i+8)*width,2*height,width,height);
        }

        for(int i=0;i<4;i++)
        {
            sandBall[i] = sheet1.crop((i+8)*width,height,width,height);
        }

        for(int i=0;i<12;i++)
        {
            foxAttack[i] = sheet1.crop((i+2)*width,14*height,width,height);
        }
        for(int i=0;i<8;i++)
        {
            fox[i] = sheet1.crop((i+2)*width ,15*height,width,height );
        }
        for(int i=0; i<6;i++)
        {
            energyBall[i] = sheet1.crop(i*width,17*height, width,height);
        }

        naruto2 = sheet.crop(0*width,0,width,height);
        earth1 = sheet.crop(2*width,0,width,height);
        earth2 = sheet.crop(3*width,0,width,height);
        earth3 = sheet.crop(4*width,0,width,height);
        sign = sheet.crop(5*width,0,width,height);
        BG = ImageLoader.loadImage("/textures/bg.png");
        BG2 = ImageLoader.loadImage("/textures/bg2.png");
        foxBG1 = ImageLoader.loadImage("/textures/foxBG1.png");
        foxBG2 = ImageLoader.loadImage("/textures/foxBG2.png");
        gaaraBG1 = ImageLoader.loadImage("/textures/gaaraBG1.png");
        gaaraBG2 = ImageLoader.loadImage("/textures/gaaraBG2.png");
        menuBG = ImageLoader.loadImage("/textures/bgmenu.png");
        helpBG = ImageLoader.loadImage("/textures/bghelp.png");
        deathBG = ImageLoader.loadImage("/textures/bgdeath.png");
        loadingBG = ImageLoader.loadImage("/textures/bgloading.png");
        menu = sheet.crop(0,height,width,height);
        menutext = sheet.crop(0,2*height,4*width,height);
        loadingtext = sheet.crop(0,3*height,2*width,height);
        cactus = sheet.crop(6*width,0,width,height);
        tree = sheet.crop(0,4*height,2*width,2*height);
        box = sheet.crop(7*width,0, width, height);
        pergament = sheet1.crop(0,24*height,width,height);
        transparent = sheet.crop(7*width,5*height,width,height);
        inventoryscreen = sheet1.crop(width, 24*height, 2*width, height);
        healthbar = sheet1.crop(0,4505, 300,2);
        chakrabar = sheet1.crop(0,4548, 300,2);
        hud = sheet1.crop(0,4400, 440,78);
        hudBoss1 = sheet1.crop(0,4320, 440,78);
        hudBoss2 = sheet1.crop(480,4320, 440,78);
        statue[0] = sheet1.crop(8*width, 3*height,width,height);
        statue[1] = sheet1.crop(9*width, 3*height,width,height);
        lava1 = sheet1.crop(7*width,8*height, width,height);
        lava2 = sheet1.crop(8*width,8*height, width,height);
        lava3 = sheet1.crop(9*width,8*height, width,height);
        lava4 = sheet1.crop(10*width,8*height, width,height);
        lava5 = sheet1.crop(7*width,9*height, width,height);
        lava6 = sheet1.crop(8*width,9*height, width,height);
        lava7 = sheet1.crop(9*width,9*height, width,height);
        lavabox = sheet1.crop(11*width,8*height, width,height);
        warning = sheet1.crop(7*width,7*height, width,height);
        skull = sheet1.crop(8*width,7*height, width,height);
        lavatree = sheet1.crop(9*width,7*height, width,height);
        bush1 = sheet1.crop(10*width,7*height, width,height);
        bush2 = sheet1.crop(11*width,7*height, width,height);

    }

}
