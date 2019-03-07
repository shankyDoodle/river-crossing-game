package com.shankydoodle.rivercrossing;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class rivercrossing extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture boat, startButton;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.jpg");
		boat = new Texture("boat.png");
        startButton = new Texture("start2.png");
	}

	@Override
	public void render () {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float boatWidth = screenWidth/4;
        float boatHeight = screenHeight/4;

		batch.begin();

		batch.draw(background, 0, 0, screenWidth, screenHeight);
		batch.draw(boat, screenWidth-boatWidth-150, 0, boatWidth,boatHeight);
		batch.draw(startButton, 100, screenHeight-350, screenWidth/13, screenWidth/13);




		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
}
