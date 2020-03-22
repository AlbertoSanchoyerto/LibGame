package org.libgame.framework;

/**
 * @interface Audio
 * @brief interface de Audio
 */
public interface Audio
{
    public Musica nuevaMusica(String nombre_fichero);

    public Sonido nuevoSonido(String nombre_fichero);
}
