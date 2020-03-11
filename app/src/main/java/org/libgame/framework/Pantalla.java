package org.libgame.framework;

public abstract class Pantalla
{

    protected final Game game;

    public Pantalla(Game game)
    {

	this.game = game;
    }
    
    public abstract void actualiza(float deltaTime);

    public abstract void presenta(float deltaTime);

    public abstract void pausa();

    public abstract void resumen();

    public abstract void libera();
}
