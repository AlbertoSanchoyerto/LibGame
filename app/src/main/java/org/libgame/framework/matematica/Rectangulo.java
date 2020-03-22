package org.libgame.framework.matematica;

/**
 * @class Rectangulo
 * @brief clase Rectangulo
 */
public class Rectangulo
{
    public final Vector2 min;
    public float ancho, alto;

    public Rectangulo(float x, float y, float ancho, float alto)
    {
        this.min = new Vector2(x, y);
        this.ancho = ancho;
        this.alto = alto;
    }
}
