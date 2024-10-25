package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Gema {
    private Texture gem;
    private Sprite spriteGem;
    private  float gemX;
    private  float gemY;
    private float gemSpeed = -120;
    private int escalaX;

    // construtor Cria Sprite com a textura da gema
    Gema() {
        gem = new Texture("gemyellow.png");
        spriteGem = new Sprite(gem);
        escalaX= Gdx.graphics.getWidth() /640;
        reinicia();
    }
    // Atualiza o estado da gema
    Boolean atualiza(Sound crash) {
        gemY += gemSpeed * Gdx.graphics.getDeltaTime();
        if (gemY < 0) {
            reinicia();
            crash.play(0.5f);
            return true;
        }
        return false;
    }
    void reinicia() {
        gemY = 450;
        gemX = MathUtils.random(0, (float) Gdx.graphics.getWidth() / escalaX - spriteGem.getWidth());
    }
    void desenha(SpriteBatch batch){
        spriteGem.setPosition(gemX, gemY);
        spriteGem.draw(batch);
    }

    Rectangle getBoundingRectangle() {
        return spriteGem.getBoundingRectangle();
    }

    void dispose(){
        gem.dispose();
    }
}
