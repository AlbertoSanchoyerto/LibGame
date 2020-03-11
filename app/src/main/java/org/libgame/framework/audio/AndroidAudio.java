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

/** Clase para manejar el audio
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

    /** Carga una nueva musica
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

    /** Carga un nuevo sonido
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
