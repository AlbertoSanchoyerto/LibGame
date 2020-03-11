package org.libgame.framework.game;

import org.libgame.framework.matematica.Rectangulo;
import org.libgame.framework.matematica.Vector2;

public class ObjetoGame
{

    public final Vector2 posicion;
    public final Rectangulo bounds;

    public ObjetoGame(float x, float y, float ancho, float alto)
    {

		this.posicion = new Vector2(x, y);
		this.bounds = new Rectangulo(x - ancho / 2, y - alto / 2, ancho, alto);
    }
}
