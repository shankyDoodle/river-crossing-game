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
        sGoButton = new Sprite(goButton);

        homeButton = new Texture("home.png");
        sHomeButton = new Sprite(homeButton);
        sHomeButton.setPosition(screenWidth-300, screenHeight-350);
        sHomeButton.setSize(screenWidth/13, screenWidth/13);

        restartButton = new Texture("restart.png");
        sRestartButton = new Sprite(restartButton);

        boatXRightLimit = screenWidth-boatWidth-150;
        boatXLeftLimit = 150;
        boatX = boatXRightLimit;
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
        sHomeButton.draw(batch);

        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();

            if(touchY > 170 && touchY< 330){
                if(touchX > 120 && touchX < 290){
                    System.out.println("go button clicked");
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



        batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
}
