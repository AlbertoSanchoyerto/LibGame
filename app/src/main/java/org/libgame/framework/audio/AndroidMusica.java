package org.libgame.framework.audio;

import java.io.IOException;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import org.libgame.framework.Musica;

public class AndroidMusica implements Musica, OnCompletionListener
{

    MediaPlayer mediaPlayer;
    boolean estaPreparado = false;

    public AndroidMusica(AssetFileDescriptor assetDescriptor)
    {

		mediaPlayer = new MediaPlayer();
		try
		{

			mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),
									  assetDescriptor.getStartOffset(),
									  assetDescriptor.getLength());
			mediaPlayer.prepare();
			estaPreparado = true;
			mediaPlayer.setOnCompletionListener(this);
		}
		catch (Exception e)
		{

			throw new RuntimeException("No puede cargar musica");
		}
    }

    @Override
    public void libera()
    {

		if (mediaPlayer.isPlaying())
		{

			mediaPlayer.stop();
		}
		mediaPlayer.release();
    }

    @Override
    public boolean estaRepitiendo()
    {

		return mediaPlayer.isLooping();
    }

    @Override
    public boolean estaReproduciendo()
    {

		return mediaPlayer.isPlaying();
    }

    @Override
    public boolean estaDetenido()
    {

		return !estaPreparado;
    }

    @Override
    public void pausa()
    {

		if (mediaPlayer.isPlaying())
		{

			mediaPlayer.pause();
		}
    }

    @Override
    public void reproduce()
    {

		if (mediaPlayer.isPlaying())
		{

			return;
		}

		try
		{

			synchronized (this)
			{
				if (!estaPreparado)
				{

					mediaPlayer.prepare();
				}
				mediaPlayer.start();
			}
		}
		catch (IllegalStateException e)
		{

			e.printStackTrace();
		}
		catch (IOException e)
		{

			e.printStackTrace();
		}
    }

    @Override
    public void ponRepetir(boolean estaRepitiendo)
    {

		mediaPlayer.setLooping(estaRepitiendo);
    }

    @Override
    public void ponVolumen(float volumen)
    {

		mediaPlayer.setVolume(volumen, volumen);
    }

    @Override
    public void detiene()
    {

		mediaPlayer.stop();
		synchronized (this)
		{

			estaPreparado = false;
		}
    }

    @Override
    public void onCompletion(MediaPlayer arg0)
    {

		synchronized (this)
		{

			estaPreparado = false;
		}
    }
}
