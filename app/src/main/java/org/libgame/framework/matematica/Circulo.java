package org.libgame.framework.matematica;

/**
 * @class Circulo
 * @brief clase Circulo
 */
public class Circulo
{
    public final Vector2 centro = new Vector2();
    public float radio;

    public Circulo(float x, float y, float radio)
    {
        this.centro.pon(x, y);
        this.radio = radio;
    }
}
