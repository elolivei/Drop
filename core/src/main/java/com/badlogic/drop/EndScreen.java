package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

import java.awt.Button;

public class EndScreen extends ScreenAdapter {

    Drop game;
    int placar;

    public EndScreen(Drop game, int placar) {
        this.game = game;
        this.placar = placar;
    }

    @Override
    public void render(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
            game.setScreen(new FirstScreen(game));
        if (Gdx.input.isTouched() && Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
            game.setScreen(new FirstScreen(game));

        Gdx.gl.glClearColor(.25f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        if(placar>0) {
            game.font.draw(game.batch, "Você Venceu!", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
        }
        else{
            game.font.draw(game.batch, "Você Perdeu!", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
        }
        game.font.draw(game.batch, "Press enter ou Left button to restart.", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .25f);
        game.batch.end();

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
