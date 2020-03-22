package org.libgame.framework;

/**
 * @interface Game
 * @brief interface de Game
 */
public interface Game
{
    public Control cogeControl();

    public FicheroIO cogeFicheroIO();

    public Graficos cogeGraficos();

    public Audio cogeAudio();

    public void ponPantalla(Pantalla pantalla);

    public Pantalla cogePantallaActual();

    public Pantalla cogePantallaInicial();
}
