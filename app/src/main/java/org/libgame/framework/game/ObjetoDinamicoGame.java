package org.libgame.framework.game;

import org.libgame.framework.matematica.Vector2;

/**
 * @class ObjetoDinamicoGame
 * @brief clase ObjetoDinamicoGame
 * objetos basicos del juego que son mobiles
 *
 * Esta clase ObjetoDinamicoGame es derivado de ObjetoGame y
 * por lo tanto define un elemento de juego pero este es mobil
 *
 * En estos elementos se definen además de los definidos en
 * ObjetoGame, se definen velicidad con Vector2 y su aceleracion
 * con vector2
 */
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
