package org.libgame.framework.audio;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import org.libgame.framework.Audio;
import org.libgame.framework.Musica;
import org.libgame.framework.Sonido;

/**
 * @class AndroidAudio
 * @brief clase AndroidAudio manejador de audio
 *
 * Esta clase es la base para crear Musica y Sonido
 */
public class AndroidAudio implements Audio
{
    AssetManager assets;
    SoundPool soundPool;

    public AndroidAudio(Activity activity)
    {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    /**
     * @fn nuevaMusica
     * @param String nombreFichero
     * @brief crea un nueva musica desde assets
     */
    @Override
    public Musica nuevaMusica(String nombreFichero)
    {
        try
        {
            AssetFileDescriptor assetDescriptor = assets.openFd(nombreFichero);

            return new AndroidMusica(assetDescriptor);
        }
        catch (IOException e)
        {
            throw new RuntimeException("No puedo cargar musica '" + nombreFichero + "'");
        }
    }

    /**
     * @fn nuevoSonido
     * @param String nombreFichero
     * @brief crea un nuevo sonido desde assets
     */
    @Override
    public Sonido nuevoSonido(String nombreFichero)
    {
        try
        {
            AssetFileDescriptor assetDescriptor = assets.openFd(nombreFichero);
            int soundId = soundPool.load(assetDescriptor, 0);

            return new AndroidSonido(soundPool, soundId);
        }
        catch (IOException e)
        {
            throw new RuntimeException("No puedo cargar sonido '" + nombreFichero + "'");
        }
    }
}
