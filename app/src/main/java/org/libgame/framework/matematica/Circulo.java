package org.libgame.framework.matematica;

/**
 * @class Circulo
 * @brief clase Circulo
 *
 * esta clase define un circulo con la posición
 * con un centro Vector2 y un radio
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
