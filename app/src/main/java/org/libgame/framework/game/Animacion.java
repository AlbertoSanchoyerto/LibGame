package org.libgame.framework.game;

import org.libgame.framework.grafico.*;

/**
 * @class Animacion
 * @brief clase Animacion
 */
public class Animacion
{
    public static final int ANIMACION_CON_BUCLE = 0;
    public static final int ANIMACION_SIN_BUCLE = 1;

    final TexturaRegion[] fotogramas;
    final float duration;

    public Animacion(float duration, TexturaRegion ... fotogramas)
    {
        this.duration = duration;
        this.fotogramas = fotogramas;
    }

    /**
     * @fn cogeFotograma
     * @param float tiempoEstado
     * @param int modo (ANIMACION_CON_BUCLE/ANIMACION_SIN_BUCLE)
     * @brief coge el fotograma corespondiente de la animacion segun su tiempo
     */
    public TexturaRegion cogeFotograma(float tiempoEstado, int modo)
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
