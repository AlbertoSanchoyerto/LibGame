package org.libgame.framework.util;

/**
 * @author Alberto Sanchoyerto
 * @version 1.0z Build 9000 Feb 23, 2016.
 */
public class Color
{

    /** Description of convertir(int r, int g, int b, int a)
     * 
     * @param r			Red
     * @param g			Green
     * @param b			Blue
     * @param a			Alfa
     * @return			Color
     */
    public static int convertir(int r, int g, int b, int a)
    {
        return ((a & 0xff) << 24) |
	    ((r & 0xff) << 16) |
	    ((g & 0xff) << 8) |
	    ((b & 0xff));               
    }
}
