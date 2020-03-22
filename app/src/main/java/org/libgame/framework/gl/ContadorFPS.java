package org.libgame.framework.gl;

import android.util.Log;

/**
 * @class ContadorFPS
 * @brief clase ContadorFPS
 */
public class ContadorFPS
{
    long tiempoInicio = System.nanoTime();
    int fotogramas = 0;

    /**
     * @fn logFrame
     * @brief escribe en el log los fotogramas por segundo
     */
    public void logFrame()
    {
        fotogramas++;
        if (System.nanoTime() - tiempoInicio >= 1000000000)
        {
            Log.d("Contador FPS", "fps: " + fotogramas);
            fotogramas = 0;
            tiempoInicio = System.nanoTime();
        }
    }
}
