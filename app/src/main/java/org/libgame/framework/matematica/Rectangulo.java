package org.libgame.framework.matematica;

/**
 * @class Rectangulo
 * @brief clase Rectangulo
 *
 * esta clase define un rectangulo con 
 * la posicion min Vector2 y sus medidas
 * de ancho y alto.
 *
 * la posición min es la esquina superior
 * izquierda del rectangulo.
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
