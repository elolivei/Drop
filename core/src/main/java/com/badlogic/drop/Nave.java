package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Nave {
    // Variaveis associadas ao estado da nave
    private Texture nave;
    private Sprite sprite;
    private  float spriteX;
    private  float spriteY;
    private float xSpeed = 120;
    private int escalaX;


    Nave(){
        // Cria Sprite com a Textura da nave
        nave = new Texture("playership2_red.png");
        sprite = new Sprite(nave);
        spriteX=320;
        spriteY=20;
        escalaX= Gdx.graphics.getWidth() /640;
    }
    public void atualiza(){
        // Atualização do estado da nave
        // Movimentação da nave usando mouse ou tela senssivel a toque.
        if (Gdx.input.isTouched()) {
            int valX = Gdx.input.getX() /escalaX;
            //Gdx.app.log("Tag", "mouse x "+String.valueOf(valX));

            if( spriteX > valX  && xSpeed > 0)
                xSpeed *= -1; //Inverte a direção
            if( spriteX < valX && xSpeed < 0)
                xSpeed *= -1; //Inverte a direção

            spriteX += xSpeed *  Gdx.graphics.getDeltaTime();
        }

        /* movimentação da nave em plataformas que suportam teclado */
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            spriteX--;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            spriteX++;
        }
        /* Nave atingiu a borda */
        if(spriteX < 0 ){
            spriteX = 0 ;
        }
        if(spriteX > 640 ){
            spriteX = 640 ;
        }
    }
    void desenha(SpriteBatch batch){
        sprite.setPosition(spriteX, spriteY);
        sprite.draw(batch);
    }
    Rectangle getBoundingRectangle() {
        return sprite.getBoundingRectangle();
    }

    void dispose(){
        nave.dispose();
    }
}
