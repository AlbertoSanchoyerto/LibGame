package org.libgame.framework.gl;

import javax.microedition.khronos.opengles.GL10;

/**
 * @class PuntoLuz
 * @brief clase PuntoLuz
 */
public class PuntoLuz
{
    float[] ambiente = { 0.2f, 0.2f, 0.2f, 1.0f };
    float[] difusa = { 1.0f, 1.0f, 1.0f, 1.0f };
    float[] especular = { 0.0f, 0.0f, 0.0f, 1.0f };
    float[] posicion = { 0, 0, 0, 1 };
    int ultimaIdLuz = 0;

    /**
     * @fn ponAmbiente
     * @brief configura el color ambiente a la luz
     * @param float r
     * @param float g
     * @param float b
     * @param float a
     */
    public void ponAmbiente(float r, float g, float b, float a)
    {
        ambiente[0] = r;
        ambiente[1] = g;
        ambiente[2] = b;
        ambiente[3] = a;
    }

    /**
     * @fn ponDifusa
     * @brief configura el color difusa a la luz
     * @param float r
     * @param float g
     * @param float b
     * @param float a
     */
    public void ponDifusa(float r, float g, float b, float a)
    {
        difusa[0] = r;
        difusa[1] = g;
        difusa[2] = b;
        difusa[3] = a;
    }

    /**
     * @fn ponEspecular
     * @brief configura el color especular a la luz
     * @param float r
     * @param float g
     * @param float b
     * @param float a
     */
    public void ponEspecular(float r, float g, float b, float a)
    {
        especular[0] = r;
        especular[1] = g;
        especular[2] = b;
        especular[3] = a;
    }

    /**
     * @fn ponPosicion
     * @brief pone la posicion de la luz en el espacio 3D
     * @param float x
     * @param float y
     * @param float z
     */
    public void ponPosicion(float x, float y, float z)
    {
        posicion[0] = x;
        posicion[1] = y;
        posicion[2] = z;
    }

    /**
     * @fn activa
     * @brief activa la luz (enciende)
     * @param GL10 gl
     * @param int idLuz
     */
    public void activa(GL10 gl, int idLuz)
    {
        gl.glEnable(idLuz);
        gl.glLightfv(idLuz, GL10.GL_AMBIENT, ambiente, 0);
        gl.glLightfv(idLuz, GL10.GL_DIFFUSE, difusa, 0);
        gl.glLightfv(idLuz, GL10.GL_SPECULAR, especular, 0);
        gl.glLightfv(idLuz, GL10.GL_POSITION, posicion, 0);
        ultimaIdLuz = idLuz;
    }

    /**
     * @fn desactiva
     * @brief desactiva la luz (apaga)
     * @param GL10 gl
     */
    public void desactiva(GL10 gl)
    {
        gl.glDisable(ultimaIdLuz);
    }
}
