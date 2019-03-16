package com.shankydoodle.rivercrossing;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class rivercrossing extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture boat;
	Texture goButton, homeButton, restartButton;

	Sprite sGoButton, sHomeButton, sRestartButton;

	int boatState = 0; // 1 for rowing left && 2 for rowing right.
    float boatX;

    float boatXLeftLimit;
    float boatXRightLimit;

    Texture boy1, girl1, boy2, girl2, boy3, girl3;
    float boy1X, girl1X, boy2X, girl2X, boy3X, girl3X;
    float boy1Y, girl1Y, boy2Y, girl2Y, boy3Y, girl3Y;
    float characterWidth, characterHeight;
    float charInBoatY, charOnGround;
    float char1InBoatX, char2InBoatX;

    //static right & left x of characters;
    float boy1SRX, girl1SRX, boy2SRX, girl2SRX, boy3SRX, girl3SRX;
    float boy1SLX, girl1SLX, boy2SLX, girl2SLX, boy3SLX, girl3SLX;

    int boatLeftHuman, boatRightHuman;

	@Override
	public void create () {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float boatWidth = screenWidth/4;
        float boatHeight = screenHeight/4;

		batch = new SpriteBatch();
		background = new Texture("bg.jpg");
		boat = new Texture("boat.png");

        goButton = new Texture("start2.png");
        homeButton = new Texture("home.png");
        restartButton = new Texture("restart.png");

        boatXRightLimit = screenWidth-boatWidth-150;
        boatXLeftLimit = 150;


        boy1 = new Texture("boy1.png");
        boy2 = new Texture("boy2.png");
        boy3 = new Texture("boy3.png");

        girl1 = new Texture("girl1.png");
        girl2 = new Texture("girl2.png");
        girl3 = new Texture("girl3.png");



        characterWidth = boy1.getWidth()/5;
        characterHeight = boy1.getHeight()/5;

        charInBoatY = 100;
        charOnGround = 400;

        boatX = boatXRightLimit;

        char1InBoatX = boatX + 100;
        char2InBoatX = boatX + boatWidth - 150 - characterWidth;

        boy1SRX = screenWidth-((characterWidth+5)*6);
        girl1SRX = screenWidth-((characterWidth+10)*5);
        boy1SLX = ((characterWidth+5)*6)- characterWidth;;
        girl1SLX = ((characterWidth+10)*5)- characterWidth;

        boy2SRX = screenWidth-((characterWidth+5)*4);
        girl2SRX = screenWidth-((characterWidth+10)*3);
        boy2SLX = ((characterWidth+5)*4)- characterWidth;
        girl2SLX = ((characterWidth+10)*3)- characterWidth;


        boy3SRX = screenWidth-((characterWidth+5)*2);
        girl3SRX = screenWidth-((characterWidth+25)*1);
        boy3SLX = ((characterWidth+5)*2)- characterWidth;
        girl3SLX = ((characterWidth+25)*1) - characterWidth;


        //codes for human characters goes from 1 to 6;
        boatLeftHuman = 0;
        boatRightHuman = 0;

        setDimensionsOfMovables();
	}

	public void setDimensionsOfMovables(){
        float screenWidth = Gdx.graphics.getWidth();
        float boatWidth = screenWidth/4;


        boatX = boatXRightLimit;

        boy1X = boy1SRX;
        girl1X = girl1SRX;

        boy2X = boy2SRX;
        girl2X = girl2SRX;

        boy3X = boy3SRX;
        girl3X = girl3SRX;

        //------------------
        boy1Y = charOnGround;
        girl1Y = charOnGround;

        boy2Y = charOnGround;
        girl2Y = charOnGround;

        boy3Y = charOnGround;
        girl3Y = charOnGround;

        char1InBoatX = boatX + 100;
        char2InBoatX = boatX + boatWidth - 150 - characterWidth;

    }

	@Override
	public void render () {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float boatWidth = screenWidth/4;
        float boatHeight = screenHeight/4;

		batch.begin();

		batch.draw(background, 0, 0, screenWidth, screenHeight);

		batch.draw(goButton, 100, screenHeight-350, screenWidth/13, screenWidth/13);
        batch.draw(restartButton, screenWidth-300, screenHeight-350, screenWidth/13, screenWidth/13);


        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();

            System.out.printf("%f", touchY);
            System.out.println("im touchY");

            if (touchY > 170 && touchY < 330) {
                if (touchX > 120 && touchX < 290) {
                    if (boatState == 0 && (boatLeftHuman!=0 || boatRightHuman!=0)) {
                        if (boatX >= boatXRightLimit) {
                            boatState = 1;
                        } else if (boatX <= boatXLeftLimit) {
                            boatState = 2;
                        }
                    }
                } else if (touchX > 2450 && touchX < 2600) {
                    setDimensionsOfMovables();
                }
            } else if (touchY > 700 && touchY < 930) {

                if(boatLeftHuman == 0 || boatRightHuman == 0){
                    if(boatX == boatXRightLimit){
                        float localBoatCharX = boatLeftHuman == 0 ? char1InBoatX : char2InBoatX;

                        if (boy1SRX < touchX && touchX < (boy1SRX + characterWidth) && boy1Y == charOnGround) {
                            boy1Y = charInBoatY;
                            boy1X = localBoatCharX;
                            updateBoatCharacterState(1);

                        }
                        else if (girl1SRX < touchX && touchX < (girl1SRX + characterWidth) && girl1Y == charOnGround) {
                            girl1Y = charInBoatY;
                            girl1X = localBoatCharX;
                            updateBoatCharacterState(2);

                        }
                        else if (boy2SRX < touchX && touchX < (boy2SRX + characterWidth) && boy2Y == charOnGround) {
                            boy2Y = charInBoatY;
                            boy2X = localBoatCharX;
                            updateBoatCharacterState(3);
                        }
                        else if (girl2SRX < touchX && touchX < (girl2SRX + characterWidth) && girl2Y == charOnGround) {
                            girl2Y = charInBoatY;
                            girl2X = localBoatCharX;
                            updateBoatCharacterState(4);
                        }
                        else if (boy3SRX < touchX && touchX < (boy3SRX + characterWidth) && boy3Y == charOnGround) {
                            boy3Y = charInBoatY;
                            boy3X = localBoatCharX;
                            updateBoatCharacterState(5);
                        }
                        else if (girl3SRX < touchX && touchX < (girl3SRX + characterWidth) && girl3Y == charOnGround) {
                            girl3Y = charInBoatY;
                            girl3X = localBoatCharX;
                            updateBoatCharacterState(6);
                        }
                    }else if(boatX <= boatXLeftLimit){
                        float localBoatCharX = boatLeftHuman == 0 ? char1InBoatX : char2InBoatX;

                        if (boy1SLX < touchX && touchX < (boy1SLX + characterWidth) && boy1Y == charOnGround) {
                            boy1Y = charInBoatY;
                            boy1X = localBoatCharX;
                            updateBoatCharacterState(1);

                        }
                        else if (girl1SLX < touchX && touchX < (girl1SLX + characterWidth) && girl1Y == charOnGround) {
                            girl1Y = charInBoatY;
                            girl1X = localBoatCharX;
                            updateBoatCharacterState(2);

                        }
                        else if (boy2SLX < touchX && touchX < (boy2SLX + characterWidth) && boy2Y == charOnGround) {
                            boy2Y = charInBoatY;
                            boy2X = localBoatCharX;
                            updateBoatCharacterState(3);
                        }
                        else if (girl2SLX < touchX && touchX < (girl2SLX + characterWidth) && girl2Y == charOnGround) {
                            girl2Y = charInBoatY;
                            girl2X = localBoatCharX;
                            updateBoatCharacterState(4);
                        }
                        else if (boy3SLX < touchX && touchX < (boy3SLX + characterWidth) && boy3Y == charOnGround) {
                            boy3Y = charInBoatY;
                            boy3X = localBoatCharX;
                            updateBoatCharacterState(5);
                        }
                        else if (girl3SLX < touchX && touchX < (girl3SLX + characterWidth) && girl3Y == charOnGround) {
                            girl3Y = charInBoatY;
                            girl3X = localBoatCharX;
                            updateBoatCharacterState(6);
                        }

                    }
                }

            } else if (1000 < touchY && touchY < 1200) {
                if (boatState == 0) {
                    System.out.printf("%d", boatLeftHuman);
                    System.out.println("im hgfjsd");

                    if (boatLeftHuman != 0 && char1InBoatX < touchX && touchX < (char1InBoatX + characterWidth)) {
                        System.out.println("im clicked");
                        if (boatX == boatXRightLimit) {
                            switch ((boatLeftHuman)) {
                                case 1:
                                    boy1X = boy1SRX;
                                    boy1Y = charOnGround;
                                    break;
                                case 2:
                                    girl1X = girl1SRX;
                                    girl1Y = charOnGround;
                                    break;
                                case 3:
                                    boy2X = boy2SRX;
                                    boy2Y = charOnGround;
                                    break;
                                case 4:
                                    girl2X = girl2SRX;
                                    girl2Y = charOnGround;
                                    break;
                                case 5:
                                    boy3X = boy3SRX;
                                    boy3Y = charOnGround;
                                    break;
                                case 6:
                                    girl3X = girl3SRX;
                                    girl3Y = charOnGround;
                                    break;
                            }
                            boatLeftHuman = 0;

                        } else if (boatX <= boatXLeftLimit) {
                            System.out.println("im clicked left");

                            switch ((boatLeftHuman)) {
                                case 1:
                                    boy1X = boy1SLX;
                                    boy1Y = charOnGround;
                                    break;
                                case 2:
                                    girl1X = girl1SLX;
                                    girl1Y = charOnGround;
                                    break;
                                case 3:
                                    boy2X = boy2SLX;
                                    boy2Y = charOnGround;
                                    break;
                                case 4:
                                    girl2X = girl2SLX;
                                    girl2Y = charOnGround;
                                    break;
                                case 5:
                                    boy3X = boy3SLX;
                                    boy3Y = charOnGround;
                                    break;
                                case 6:
                                    girl3X = girl3SLX;
                                    girl3Y = charOnGround;
                                    break;
                            }
                            boatLeftHuman = 0;
                        }

                    }

                    if (boatRightHuman != 0 && char2InBoatX < touchX && touchX < (char2InBoatX + characterWidth)) {
                        if (boatX == boatXRightLimit) {
                            switch ((boatRightHuman)) {
                                case 1:
                                    boy1X = boy1SRX;
                                    boy1Y = charOnGround;
                                    break;
                                case 2:
                                    girl1X = girl1SRX;
                                    girl1Y = charOnGround;
                                    break;
                                case 3:
                                    boy2X = boy2SRX;
                                    boy2Y = charOnGround;
                                    break;
                                case 4:
                                    girl2X = girl2SRX;
                                    girl2Y = charOnGround;
                                    break;
                                case 5:
                                    boy3X = boy3SRX;
                                    boy3Y = charOnGround;
                                    break;
                                case 6:
                                    girl3X = girl3SRX;
                                    girl3Y = charOnGround;
                                    break;
                            }
                        } else if (boatX <= boatXLeftLimit) {
                            switch ((boatRightHuman)) {
                                case 1:
                                    boy1X = boy1SLX;
                                    boy1Y = charOnGround;
                                    break;
                                case 2:
                                    girl1X = girl1SLX;
                                    girl1Y = charOnGround;
                                    break;
                                case 3:
                                    boy2X = boy2SLX;
                                    boy2Y = charOnGround;
                                    break;
                                case 4:
                                    girl2X = girl2SLX;
                                    girl2Y = charOnGround;
                                    break;
                                case 5:
                                    boy3X = boy3SLX;
                                    boy3Y = charOnGround;
                                    break;
                                case 6:
                                    girl3X = girl3SLX;
                                    girl3Y = charOnGround;
                                    break;
                            }
                        }

                        boatRightHuman = 0;
                    }
                }

            }


        }

        if (boatState == 1) {
            boatX -= 10;
            handleMoveCharacterWithBoat();

        } else if (boatState == 2) {
            boatX += 10;
            handleMoveCharacterWithBoat();

        }

        if(boatX <= boatXLeftLimit || boatX >= boatXRightLimit){
            boatState = 0;
            updateCharXsInBoat();
        }


        batch.draw(boy1, boy1X, boy1Y, characterWidth, characterHeight);
        batch.draw(girl1, girl1X, girl1Y, characterWidth, characterHeight);

        batch.draw(boy2, boy2X, boy2Y, characterWidth, characterHeight);
        batch.draw(girl2, girl2X, girl2Y, characterWidth, characterHeight);

        batch.draw(boy3, boy3X, boy3Y, characterWidth, characterHeight);
        batch.draw(girl3, girl3X, girl3Y, characterWidth, characterHeight);


        batch.draw(boat, boatX, 0, boatWidth,boatHeight);


        batch.end();
	}

	public void updateBoatCharacterState(int n){
        if(boatLeftHuman == 0){
            boatLeftHuman = n;
        }else {
            boatRightHuman = n;
        }
    }

    public void handleMoveCharacterWithBoat(){
        if(boatLeftHuman !=0){
            if(boatState == 1){
                switch ((boatLeftHuman)){
                    case 1: boy1X -= 10; break;
                    case 2: girl1X -= 10; break;
                    case 3: boy2X -= 10; break;
                    case 4: girl2X -= 10; break;
                    case 5: boy3X -= 10; break;
                    case 6: girl3X -= 10; break;
                }
            }else if(boatState == 2){
                switch ((boatLeftHuman)){
                    case 1: boy1X += 10; break;
                    case 2: girl1X += 10; break;
                    case 3: boy2X += 10; break;
                    case 4: girl2X += 10; break;
                    case 5: boy3X += 10; break;
                    case 6: girl3X += 10; break;
                }
            }

        }

        if(boatRightHuman != 0){
            if(boatState == 1){
                switch ((boatRightHuman)){
                    case 1: boy1X -= 10; break;
                    case 2: girl1X -= 10; break;
                    case 3: boy2X -= 10; break;
                    case 4: girl2X -= 10; break;
                    case 5: boy3X -= 10; break;
                    case 6: girl3X -= 10; break;
                }
            }else if(boatState == 2){
                switch ((boatRightHuman)){
                    case 1: boy1X += 10; break;
                    case 2: girl1X += 10; break;
                    case 3: boy2X += 10; break;
                    case 4: girl2X += 10; break;
                    case 5: boy3X += 10; break;
                    case 6: girl3X += 10; break;
                }
            }

        }
    }

    public void updateCharXsInBoat(){
        float screenWidth = Gdx.graphics.getWidth();
        float boatWidth = screenWidth/4;

        char1InBoatX = boatX + 100;
        char2InBoatX = boatX + boatWidth - 150 - characterWidth;
    }

	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
}
