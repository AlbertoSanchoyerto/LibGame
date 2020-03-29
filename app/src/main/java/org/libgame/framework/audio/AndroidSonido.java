package org.libgame.framework.audio;

import android.media.SoundPool;

import org.libgame.framework.Sonido;

/**
 * @class AndroidSonido
 * @brief clase Sonido
 *
 * esta clase se crea a partir de un fichero en assets de sonido
 *
 * y reproduce el sonido a un volumen
 */
public class AndroidSonido implements Sonido
{
    int idSonido;
    SoundPool soundPool;

    public AndroidSonido(SoundPool soundPool, int idSonido)
    {
        this.idSonido = idSonido;
        this.soundPool = soundPool;
    }

    /**
     * @fn reproduce
     * @param volumen
     * @brief reproduce el sonido
     */
    @Override
    public void reproduce(float volumen)
    {
        soundPool.play(idSonido, volumen, volumen, 0, 0, 1);
    }

    @Override
    public void libera()
    {
        soundPool.unload(idSonido);
    }

}
