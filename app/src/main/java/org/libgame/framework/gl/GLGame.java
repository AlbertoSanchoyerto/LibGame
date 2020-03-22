package org.libgame.framework.gl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

import org.libgame.framework.Audio;
import org.libgame.framework.FicheroIO;
import org.libgame.framework.Game;
import org.libgame.framework.Graficos;
import org.libgame.framework.Control;
import org.libgame.framework.Pantalla;

import org.libgame.framework.sistema.AndroidControl;
import org.libgame.framework.sistema.AndroidFicheroIO;
import org.libgame.framework.audio.AndroidAudio;

/**
 * @class GLGame
 * @brief clase GLGame base de Game para openGL
 */
public abstract class GLGame extends Activity implements Game, Renderer
{
    enum GLGameEstado
    {
        Inicializando,
        Corriendo,
        Pausado,
        Terminado,
        Idle,
    }

    GLSurfaceView glView;    
    GLGraficos glGraficos;
    Audio audio;
    Control control;
    FicheroIO ficheroIO;
    Pantalla pantalla;
    GLGameEstado estado = GLGameEstado.Inicializando;
    Object estadoCambiado = new Object();
    long tiempoInicial = System.nanoTime();
    WakeLock wakeLock;

    @Override 
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        glView = new GLSurfaceView(this);
        glView.setRenderer(this);
        setContentView(glView);

        glGraficos = new GLGraficos(glView);
        ficheroIO = new AndroidFicheroIO(getAssets());
        audio = new AndroidAudio(this);
        control = new AndroidControl(this, glView, 1, 1);
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");        
    }

    public void onResume()
    {
        super.onResume();
        glView.onResume();
        wakeLock.acquire();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    { 
        glGraficos.ponGL(gl);

        synchronized (estadoCambiado)
        {

            if (estado == GLGameEstado.Inicializando)
            {

                pantalla = cogePantallaInicial();
            }

            estado = GLGameEstado.Corriendo;
            pantalla.resumen();
            tiempoInicial = System.nanoTime();
        }        
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto)
    {        
    }

    @Override
    public void onDrawFrame(GL10 gl)
    { 
        GLGameEstado estado = null;

        synchronized (estadoCambiado)
        {
            estado = this.estado;
        }

        if (estado == GLGameEstado.Corriendo)
        {
            float deltaTime = (System.nanoTime() - tiempoInicial) / 1000000000.0f;
            tiempoInicial = System.nanoTime();

            pantalla.actualiza(deltaTime);
            pantalla.presenta(deltaTime);
        }

        if (estado == GLGameEstado.Pausado)
        {
            pantalla.pausa();            
            synchronized (estadoCambiado)
            {
                this.estado = GLGameEstado.Idle;
                estadoCambiado.notifyAll();
            }
        }

        if (estado == GLGameEstado.Terminado)
        {
            pantalla.pausa();
            pantalla.libera();
            synchronized (estadoCambiado)
            {
                this.estado = GLGameEstado.Idle;
                estadoCambiado.notifyAll();
            }            
        }
    }   

    @Override 
    public void onPause()
    {
        synchronized (estadoCambiado)
        {
            if (isFinishing())
            {
                estado = GLGameEstado.Terminado;
            }
            else
            {

                estado = GLGameEstado.Pausado;
            }

            while (true)
            {
                try
                {
                    estadoCambiado.wait();
                    break;
                }
                catch (InterruptedException e)
                {
                }
            }
        }
        wakeLock.release();
        glView.onPause();  
        super.onPause();
    }

    /**
     * @fn cogeGLGraficos
     * @return GLGraficos
     */
    public GLGraficos cogeGLGraficos()
    {
        return glGraficos;
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
     * @deprecated
     * @return Graficos
     */
    @Override
    public Graficos cogeGraficos()
    {
        throw new IllegalStateException("Nosotros utilizamos OpenGL!");
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
     * @brief pone como activa esta Pantalla
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
     * @brief obtiene la Pantalla actual
     * @return Pantalla pantalla
     */
    @Override
    public Pantalla cogePantallaActual()
    {
        return pantalla;
    }   
}
