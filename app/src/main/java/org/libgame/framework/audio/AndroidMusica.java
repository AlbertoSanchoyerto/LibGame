package org.libgame.framework.audio;

import java.io.IOException;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import org.libgame.framework.Musica;

/**
 * @class AndroidMusica
 * @brief clase Musica
 */
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

    /**
     * @fn estaRepitiendo
     * @brief verifica si esta configurada la repeticion
     */
    @Override
    public boolean estaRepitiendo()
    {
        return mediaPlayer.isLooping();
    }

    /**
     * @fn estaReproduciendo
     * @brief verifica si se esta reproduciendo
     */
    @Override
    public boolean estaReproduciendo()
    {
        return mediaPlayer.isPlaying();
    }

    /**
     * @fn estaDetenido
     * @brief verifica si se esta detenido
     */
    @Override
    public boolean estaDetenido()
    {
        return !estaPreparado;
    }

    /**
     * @fn pausa
     * @brief pausa la musica
     */
    @Override
    public void pausa()
    {
        if (mediaPlayer.isPlaying())
        {

            mediaPlayer.pause();
        }
    }

    /**
     * @fn reproduce
     * @brief reproduce la musica
     */
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

    /**
     * @fn ponRepetir
	 * @param boolean estaRepitiendo
     * @brief cofigura la repetición de la musica
     */
    @Override
    public void ponRepetir(boolean estaRepitiendo)
    {
        mediaPlayer.setLooping(estaRepitiendo);
    }

    /**
     * @fn ponVolumen
	 * @param float volumen
     * @brief cofigura el volumen de la musica
     */
    @Override
    public void ponVolumen(float volumen)
    {
        mediaPlayer.setVolume(volumen, volumen);
    }

    /**
     * @fn detiene
     * @brief detinee el sonido
     */
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
