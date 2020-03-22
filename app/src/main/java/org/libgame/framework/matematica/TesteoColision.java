package org.libgame.framework.matematica;

import android.graphics.*;

/**
 * @class TesteoColision
 * @brief clase TesteoColision
 */
public class TesteoColision
{
	/**
     * @fn colisionRectangulos
     * @brief verifica la colision entre dos Rectangulos
     * @param Rectangulo r1
     * @param Rectangulo r2
     * @return boolean colision
     */
    public static boolean colisionRectangulos(Rectangulo r1, Rectangulo r2)
    {
		if (r1.min.x < r2.min.x + r2.ancho &&
			r1.min.x + r1.ancho > r2.min.x &&
			r1.min.y < r2.min.y + r2.alto &&
			r1.min.y + r1.alto > r2.min.y)
		{
			return true;
		}
		else
		{
			return false;
		}
    }
	
	/**
     * @fn colisionCirculos
     * @brief verifica la colision entre dos Circulos
     * @param Circulo c1
     * @param Circulo c2
     * @return boolean colision
     */
    public static boolean colisionCirculos(Circulo c1, Circulo c2)
    {
		float distancia = c1.centro.distCuadrada(c2.centro);
		float sumRadios = c1.radio + c2.radio;

		return distancia <= sumRadios * sumRadios;
    }

	/**
     * @fn colisionCirculoRectangulo
     * @brief verifica la colision entre un Circulo y un Rectangulo
     * @param Circulo c
     * @param Rectangulo r
     * @return boolean colision
     */
    public static boolean colisionCirculoRectangulo(Circulo c, Rectangulo r)
    {
		float closestX = c.centro.x;
		float closestY = c.centro.y;

		if (c.centro.x < r.min.x)
		{
			closestX = r.min.x; 
		} 
		else if (c.centro.x > r.min.x + r.ancho)
		{
			closestX = r.min.x + r.ancho;
		}

		if (c.centro.y < r.min.y)
		{
			closestY = r.min.y;
		} 
		else if (c.centro.y > r.min.y + r.alto)
		{
			closestY = r.min.y + r.alto;
		}

		return c.centro.distCuadrada(closestX, closestY) < c.radio * c.radio;           
    }
	
	/**
     * @fn colisionEsferas
     * @brief verifica la colision entre Esferas
     * @param Esfera e1
     * @param Esfera e2
     * @return boolean colision
     */
	public static boolean colisionEsferas(Esfera e1, Esfera e2)
	{
		float distance = e1.centro.distCuadrada(e2.centro);
		float radiusSum = e1.radio + e2.radio;
		return distance <= radiusSum * radiusSum;
	}
	
	/**
     * @fn puntoEnRectangulo(Circulo c, Vector2 p)
     * @brief verifica si un punto esta dentro de un Rectangulo
     * @param Rectangulo r
     * @param Vector2 p
     * @return boolean colision
     */
    public static boolean puntoEnRectangulo(Rectangulo r, Vector2 p)
    {
		return r.min.x <= p.x && r.min.x + r.ancho >= p.x && r.min.y <= p.y && r.min.y + r.alto >= p.y;
    }

	/**
     * @fn puntoEnRectangulo(Rectangulo r, float x, float y)
     * @brief verifica si un punto esta dentro de un Circulo
     * @param Rectangulo r
     * @param float x
	 * @param float y
     * @return boolean colision
     */
    public static boolean puntoEnRectangulo(Rectangulo r, float x, float y)
    {
		return r.min.x <= x && r.min.x + r.ancho >= x && r.min.y <= y && r.min.y + r.alto >= y;
    }

	/**
     * @fn puntoEnCirculo(Circulo c, Vector2 p)
     * @brief verifica si un punto esta dentro de un Circulo
     * @param Circulo c
     * @param Vector2 p
     * @return boolean colision
     */
    public static boolean puntoEnCirculo(Circulo c, Vector2 p)
    {
		return c.centro.distCuadrada(p) < c.radio * c.radio;
    }

	/**
     * @fn puntoEnCirculo(Circulo c, float x, float y)
     * @brief verifica si un punto esta dentro de un Circulo
     * @param Circulo c
     * @param float x
	 * @param float y
     * @return boolean colision
     */
    public static boolean puntoEnCirculo(Circulo c, float x, float y)
    {
		return c.centro.distCuadrada(x, y) < c.radio * c.radio;
    }

	/**
     * @fn puntoEnEsfera(Esfera e, Vector3 p)
     * @brief verifica si un punto esta dentro de una Esfera
     * @param Esfera e
     * @param Vector3 p
     * @return boolean colision
     */
	public static boolean puntoEnEsfera(Esfera e, Vector3 p)
	{
	    return e.centro.distCuadrada(p) < e.radio * e.radio;
	}

	/**
     * @fn puntoEnEsfera(Esfera r, float x, float y,float z)
     * @brief verifica si un punto esta dentro de una Esfera
     * @param Esfera e
     * @param float x
	 * @param float y
     * @param float z
     * @return boolean colision
     */
	public static boolean puntoEnEsfera(Esfera e, float x, float y, float z)
	{
	    return e.centro.distCuadrada(x, y, z) < e.radio * e.radio;
	}
}
