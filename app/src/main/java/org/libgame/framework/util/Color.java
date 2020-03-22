package org.libgame.framework.util;

/**
 * @class Color
 * @brief clase Color utilida de conversion de color
 */
public class Color
{
    /**
     * @fn convertir(int r, int g, int b, int a)
     * @brief convierte los valores (r,g,b,a) a un int color
     * @param int r rojo
     * @param int g verde
     * @param int b azul
     * @param int a alfa
     * @return int color
     */
    public static int convertir(int r, int g, int b, int a)
    {
        return ((a & 0xff) << 24) |
        ((r & 0xff) << 16) |
        ((g & 0xff) << 8) |
        ((b & 0xff));               
    }
}
