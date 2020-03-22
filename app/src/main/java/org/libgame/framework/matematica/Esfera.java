package org.libgame.framework.matematica;

/**
 * @class Esfera
 * @brief clase Esfera
 */
public class Esfera
{
    public final Vector3 centro = new Vector3();
    public float radio;
    
    public Esfera(float x, float y, float z, float radio) {
        this.centro.pon(x,y,z);
        this.radio = radio;
    }
}
