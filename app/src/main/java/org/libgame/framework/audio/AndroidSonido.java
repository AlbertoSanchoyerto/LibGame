package org.libgame.framework.audio;

import android.media.SoundPool;

import org.libgame.framework.Sonido;

public class AndroidSonido implements Sonido
{

    int idSonido;
    SoundPool soundPool;

    public AndroidSonido(SoundPool soundPool, int idSonido)
    {

		this.idSonido = idSonido;
		this.soundPool = soundPool;
    }

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
