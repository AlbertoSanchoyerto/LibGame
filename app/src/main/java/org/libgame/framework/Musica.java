package org.libgame.framework;

public interface Musica
{

    public void reproduce();

    public void detiene();

    public void pausa();

    public void ponRepetir(boolean repetir);

    public void ponVolumen(float volumen);

    public boolean estaReproduciendo();

    public boolean estaDetenido();

    public boolean estaRepitiendo();

    public void libera();
}
