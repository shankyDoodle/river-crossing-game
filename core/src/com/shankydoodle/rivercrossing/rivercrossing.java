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
    float characterWidth, characterHeight;
    float charInBoatY, charOnGround;

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

        setDimensionsOfMovables();
	}

	public void setDimensionsOfMovables(){
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float boatWidth = screenWidth/4;
        float boatHeight = screenHeight/4;


        boatX = boatXRightLimit;

        boy1X = screenWidth-((characterWidth+5)*6);
        girl1X = screenWidth-((characterWidth+10)*5);

        boy2X = screenWidth-((characterWidth+5)*4);
        girl2X = screenWidth-((characterWidth+10)*3);

        boy3X = screenWidth-((characterWidth+5)*2);
        girl3X = screenWidth-((characterWidth+10)*1);

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
        batch.draw(homeButton, screenWidth-300, screenHeight-350, screenWidth/13, screenWidth/13);
        batch.draw(restartButton, screenWidth-500, screenHeight-350, screenWidth/13, screenWidth/13);




        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();

            System.out.printf("%f", touchY);
            System.out.println("im touchY");

            if(touchY > 170 && touchY< 330){
                if(touchX > 120 && touchX < 290){
                    if(boatState == 0 ){
                        if(boatX >= boatXRightLimit){
                            boatState = 1;
                        }else if(boatX <= boatXLeftLimit){
                            boatState = 2;
                        }
                    }

                }else if(touchX > 2240 && touchX<2390){
                    System.out.println("reset button clicked");
                }else if(touchX > 2450 && touchX<2600){
                    System.out.println("home button clicked");
                }
            }else if(touchY > 700 && touchY< 930){
                //detect character clicks

            }


        }

        if (boatState == 1) {
            boatX -= 10;
        } else if (boatState == 2) {
            boatX += 10;
        }

        if(boatX <= boatXLeftLimit || boatX >= boatXRightLimit){
            boatState = 0;
        }

        batch.draw(boat, boatX, 0, boatWidth,boatHeight);

        batch.draw(boy1, boy1X, charOnGround, characterWidth, characterHeight);
        batch.draw(girl1, girl1X, charOnGround, characterWidth, characterHeight);

        batch.draw(boy2, boy2X, charOnGround, characterWidth, characterHeight);
        batch.draw(girl2, girl2X, charOnGround, characterWidth, characterHeight);

        batch.draw(boy3, boy3X, charOnGround, characterWidth, characterHeight);
        batch.draw(girl3, girl3X, charOnGround, characterWidth, characterHeight);


        batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
}
