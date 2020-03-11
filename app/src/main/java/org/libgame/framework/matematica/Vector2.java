package org.libgame.framework.matematica;

import android.util.FloatMath;

public class Vector2
{

    public static float A_RADIANES = (1 / 180.0f) * (float)Math.PI;
    public static float A_GRADOS = (1 / (float)Math.PI) * 180;
    public float x, y;

    public Vector2()
    {    
    }       

    public Vector2(float x, float y)
    {

		this.x = x;
		this.y = y;
    }

    public Vector2(Vector2 otro)
    {

		this.x = otro.x;
		this.y = otro.y;
    }

    public Vector2 copia()
    {

		return new Vector2(x, y);
    }       

    public Vector2 pon(float x, float y)
    {

		this.x = x;
		this.y = y;

		return this;
    }

    public Vector2 pon(Vector2 otro)
    {

		this.x = otro.x;
		this.y = otro.y;

		return this;
    }

    public Vector2 suma(float x, float y)
    {

		this.x += x;
		this.y += y;

		return this;
    }

    public Vector2 suma(Vector2 otro)
    {

		this.x += otro.x;
		this.y += otro.y;

		return this;
    }

    public Vector2 resta(float x, float y)
    {

		this.x -= x;
		this.y -= y;

		return this;
    }

    public Vector2 resta(Vector2 otro)
    {

		this.x -= otro.x;
		this.y -= otro.y;

		return this;
    }

    public Vector2 multiplica(float escala)
    {

		this.x *= escala;
		this.y *= escala;

		return this;
    }

    public float len()
    {   

		return (float)Math.sqrt(x * x + y * y);
    }

    public Vector2 normaliza()
    {

		float len = len();
		if (len != 0)
		{

			this.x /= len;
			this.y /= len;
		}
		return this;
    }       

    public float angulo()
    {

		float angulo = (float)Math.atan2(y, x) * A_GRADOS;

		if (angulo < 0)
		{

			angulo += 360;
		}
		return angulo;
    }       

    public Vector2 rota(float angle)
    {

		float rad = angle * A_RADIANES;
		float cos = (float)Math.cos(rad);
		float sin = (float)Math.sin(rad);

		float nuevaX = this.x * cos - this.y * sin;
		float nuevaY = this.x * sin + this.y * cos;

		this.x = nuevaX;
		this.y = nuevaY;

		return this;
    }

    public float distancia(Vector2 otro)
    {

		float distanciaX = this.x - otro.x;
		float distanciaY = this.y - otro.y;  

		return (float)Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);
    }   

    public float dist(float x, float y)
    {

		float distanciaX = this.x - x;
		float distanciaY = this.y - y;    

		return (float)Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);
    }   

    public float distanciaCuadrada(Vector2 otro)
    {

		float distanciaX = this.x - otro.x;
		float distanciaY = this.y - otro.y;   

		return distanciaX * distanciaX + distanciaY * distanciaY;
    }

    public float distanciaCuadrada(float x, float y)
    {

		float distanciaX = this.x - x;
		float distanciaY = this.y - y;        

		return distanciaX * distanciaX + distanciaY * distanciaY;
    }       
}
