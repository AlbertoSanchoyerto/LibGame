package org.libgame.framework.matematica;

import android.graphics.*;

/**
 * @class TesteoColision
 * @brief clase TesteoColision
 */
public class TesteoColision
{

    public static boolean colisionCirculos(Circulo c1, Circulo c2)
    {

		float distancia = c1.centro.distCuadrada(c2.centro);
		float sumRadios = c1.radio + c2.radio;

		return distancia <= sumRadios * sumRadios;
    }

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

    public static boolean puntoEnCirculo(Circulo c, Vector2 p)
    {

		return c.centro.distCuadrada(p) < c.radio * c.radio;
    }

    public static boolean puntoEnCirculo(Circulo c, float x, float y)
    {

		return c.centro.distCuadrada(x, y) < c.radio * c.radio;
    }

    public static boolean puntoEnRectangulo(Rectangulo r, Vector2 p)
    {

		return r.min.x <= p.x && r.min.x + r.ancho >= p.x && r.min.y <= p.y && r.min.y + r.alto >= p.y;
    }

    public static boolean puntoEnRectangulo(Rectangulo r, float x, float y)
    {

		return r.min.x <= x && r.min.x + r.ancho >= x && r.min.y <= y && r.min.y + r.alto >= y;
    }

}
