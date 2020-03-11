package org.libgame.framework;

public interface Game
{

    public Control cogeControl();

    public FicheroIO cogeFicheroIO();

    public Grafico cogeGrafico();
    
    public Audio cogeAudio();

    public void ponPantalla(Pantalla pantalla);

    public Pantalla cogePantallaActual();

    public Pantalla cogePantallaInicial();
}
