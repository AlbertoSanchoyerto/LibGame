package org.libgame.framework.game;

import org.libgame.framework.grafico.*;

public class Animation
{
    public static final int ANIMACION_CON_BUCLE = 0;
    public static final int ANIMACION_SIN_BUCLE = 1;

    final TexturaRegion[] fotogramas;
    final float duration;

    public Animation(float duration, TexturaRegion ... fotogramas)
	{
        this.duration = duration;
        this.fotogramas = fotogramas;
    }

    public TexturaRegion cogeFotogramas(float tiempoEstado, int modo)
	{
        int numFotograma = (int)(tiempoEstado / duration);

        if (modo == ANIMACION_SIN_BUCLE)
		{
            numFotograma = Math.min(fotogramas.length - 1, numFotograma);            
        }
		else
		{
            numFotograma = numFotograma % fotogramas.length;
        }        
        return fotogramas[numFotograma];
    }
}
