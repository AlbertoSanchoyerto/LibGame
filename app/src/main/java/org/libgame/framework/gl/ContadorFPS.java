package org.libgame.framework.gl;

import android.util.Log;

public class ContadorFPS
{
    long tiempoInicio = System.nanoTime();
    int fotogramas = 0;

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
