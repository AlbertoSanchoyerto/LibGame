package org.libgame.framework.game;

import org.libgame.framework.matematica.Vector2;

public class ObjetoDinamicoGame extends ObjetoGame
{

    public final Vector2 velocidad;
    public final Vector2 acceleracion;

    public ObjetoDinamicoGame(float x, float y, float ancho, float alto)
    {

		super(x, y, ancho, alto);

		velocidad = new Vector2();
		acceleracion = new Vector2();
    }
}
