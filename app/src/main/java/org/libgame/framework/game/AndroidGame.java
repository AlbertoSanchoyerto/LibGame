package org.libgame.framework.game;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import org.libgame.framework.Audio;
import org.libgame.framework.FicheroIO;
import org.libgame.framework.Game;
import org.libgame.framework.Grafico;
import org.libgame.framework.Control;
import org.libgame.framework.Pantalla;

import org.libgame.framework.sistema.AndroidFicheroIO;
import org.libgame.framework.grafico.AndroidGraficos;
import org.libgame.framework.audio.AndroidAudio;

import org.libgame.framework.sistema.*;

/**
 * @class AndroidGame
 * @brief clase AndroidGame base de Game
 */
public abstract class AndroidGame extends Activity implements Game
{
    AndroidVistaRapida vistaRapida;
    Graficos graficos;
    Audio audio;
    Control control;
    FicheroIO ficheroIO;
    Pantalla pantalla;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        boolean esLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        int frameBufferAncho = esLandscape ? 480 : 320;
        int frameBufferAlto = esLandscape ? 320 : 480;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferAncho, frameBufferAlto, Config.RGB_565);

        float scaleX = (float) frameBufferAncho / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferAlto / getWindowManager().getDefaultDisplay().getHeight();

        vistaRapida = new AndroidVistaRapida(this, frameBuffer);
        graficos = new AndroidGraficos(getAssets(), frameBuffer);
        ficheroIO = new AndroidFicheroIO(getAssets());
        audio = new AndroidAudio(this);
        control = new AndroidControl(this, vistaRapida, scaleX, scaleY);
        pantalla = cogePantallaActual();
        setContentView(vistaRapida);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        pantalla.resumen();
        vistaRapida.resume();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        vistaRapida.pause();
        pantalla.pausa();

        if (isFinishing())
        {
            pantalla.libera();
        }
    }

    /**
     * @fn cogeControl
     * @return Control
     */
    @Override
    public Control cogeControl()
    {
        return control;
    }

    /**
     * @fn cogeFicheroIO
     * @return FicheroIO
     */
    @Override
    public FicheroIO cogeFicheroIO()
    {
        return ficheroIO;
    }

    /**
     * @fn cogeGraficos
     * @return Graficos
     */
    @Override
    public Graficos cogeGraficos()
    {
        return graficos;
    }

    /**
     * @fn cogeAudio
     * @return Audio
     */
    @Override
    public Audio cogeAudio()
    {
        return audio;
    }

    /**
     * @fn ponPantalla
     * @param Pantalla pantalla
     */
    @Override
    public void ponPantalla(Pantalla pantalla)
    {
        if (pantalla == null)
        {
            throw new IllegalArgumentException("La pantalla no puede ser null");
        }

        this.pantalla.pausa();
        this.pantalla.libera();
        pantalla.resumen();
        pantalla.actualiza(0);
        this.pantalla = pantalla;
    }

    /**
     * @fn cogePantallaActual
     * @return Pantalla
     */
    public Pantalla cogePantallaActual()
    {
        return pantalla;
    }   
}
