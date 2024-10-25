package com.badlogic.drop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {

    // game
    final Drop game;

    // Variaveis inicialização da area de fundo do jogo
    private SpriteBatch batch;
    private Texture image;

    //Classe Nave
    Nave nave;
    //Classe Gema
    Gema gem1;
    Gema gem2;

    // Variaveis associada a camera
    private OrthographicCamera camera;
    private Viewport viewport;

    //Variaveis que capturam informações da tela
    private float density;
    private int screenWidth;
    private int screenHeight;
    private int escalaX;
    private int escalaY;

    //Variaveis associadas ao placar
    private BitmapFont font;
    private int placar;

    //Variaveis de soms do game
    // som gerado quando a gema atinge o chão
    Sound crash;
    // som gerado quando a gema eh capturada
    Sound pic;

    public FirstScreen(final Drop game) {
        this.game = game;
        batch = new SpriteBatch();

        // Textura da Imagem de fundo
        image = new Texture("grass.png");

        //cria nave
        nave = new Nave();

        //Cria um objeto da classe Gema
        gem1 = new Gema();
        gem2 = new Gema();

        // Configura a câmera e o viewport
        camera = new OrthographicCamera();
        viewport = new FitViewport(640, 480, camera);
        viewport.apply();

        // Centraliza a câmera
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        // Obtem parametros de tela
        density = Gdx.graphics.getDensity();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        escalaX = screenWidth / 640;
        escalaY = screenHeight / 480;
        // Atualiza o viewport
        viewport.update(screenWidth, screenHeight);

        // Inicializa Placar
        placar = 0;
        font = new BitmapFont();

        // Se retirar o comentário habilita o log (aba logcat)
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        //Gdx.app.log("Tag", "largura "+String.valueOf(screenWidth));
        //Gdx.app.log("Tag", "altura "+String.valueOf(screenHeight));
        //dx.app.log("Tag", "densidade "+String.valueOf(density));

        //carregando arquivos de audio
        crash = Gdx.audio.newSound(Gdx.files.internal("RUIDOSESTRANHOS8.mp3"));
        pic = Gdx.audio.newSound(Gdx.files.internal("PEGAITEMMOLE.mp3"));
    }
    @Override
    public void render(float delta) {

        //Atualiza Nave
        nave.atualiza();

        //atualiza a posição da gema na tela
        gem1.atualiza(crash);
        gem2.atualiza(crash);

        // Gera registro de log remova comentário
        //Gdx.app.log("Tag", "Debug message"+String.valueOf(spriteX));

        //Redesenha a tela com os estados atualizados
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(image, 0, 0, screenWidth, screenHeight);
        font.draw(batch, "Placar: " + String.valueOf(placar), screenWidth / escalaX - 80, screenHeight / escalaY - 10);
        nave.desenha(batch);
        gem1.desenha(batch);
        gem2.desenha(batch);
        batch.end();

        //detecta colisão e atualiza estado do placar e posição da gema
        Rectangle rect1 = nave.getBoundingRectangle();
        Rectangle rect2 = gem1.getBoundingRectangle();
        Rectangle rect3 = gem2.getBoundingRectangle();

        //atualiza a posição da gema na tela
        if(gem1.atualiza(crash))
            placar -=1;
        if(gem2.atualiza(crash))
            placar -=1;

        if (rect1.overlaps(rect2)) {
            // Colisão detectada
            placar += 2;
            gem1.reinicia();
            pic.play(0.5f);
        }
        if (rect1.overlaps(rect3)) {
            // Colisão detectada
            placar += 2;
            gem2.reinicia();
            pic.play(0.5f);
        }
        // Testa fim do jogo
        if(placar < 0 || placar >= 10 )
            game.setScreen(new EndScreen(game,placar));
    }

        @Override
        public void dispose() {
            batch.dispose();
            image.dispose();
            nave.dispose();
            gem1.dispose();
            gem1.dispose();
            crash.dispose();
            pic.dispose();
        }

    @Override
    public void show() {
        // Prepare your screen here.
    }

    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

}
