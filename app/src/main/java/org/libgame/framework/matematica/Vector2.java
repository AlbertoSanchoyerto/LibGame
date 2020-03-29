package org.libgame.framework.matematica;

import android.util.FloatMath;

/**
 * @class Vector2
 * @brief clase Vector2 vectores en 2D
 *
 * clase que define una posición X, Y y
 * las operaciones copia, suma, resta...
 */
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

    /**
     * @fn copia
     * @brief copia un vector
     */
    public Vector2 copia()
    {
        return new Vector2(x, y);
    }       

    /**
     * @fn pon(float x, float y)
     * @brief configura el vector con (x,y)
     * @return Vector2 vector
     */
    public Vector2 pon(float x, float y)
    {
        this.x = x;
        this.y = y;

        return this;
    }

    /**
     * @fn pon(Vector2 otro)
     * @brief configura el vector con otro Vector2
     * @return Vector2 vector
     */
    public Vector2 pon(Vector2 otro)
    {
        this.x = otro.x;
        this.y = otro.y;

        return this;
    }

    /**
     * @fn suma(float x, float y)
     * @brief suma (x,y) al vector
     * @return Vector2 vector
     */
    public Vector2 suma(float x, float y)
    {
        this.x += x;
        this.y += y;

        return this;
    }

    /**
     * @fn suma(Vector2 otro)
     * @brief suma al vector los de otro vector
     * @return Vector2 vector
     */
    public Vector2 suma(Vector2 otro)
    {
        this.x += otro.x;
        this.y += otro.y;

        return this;
    }

    /**
     * @fn resta(float x, float y)
     * @brief resta (x,y) al vector
     * @return Vector2 vector
     */
    public Vector2 resta(float x, float y)
    {
        this.x -= x;
        this.y -= y;

        return this;
    }

    /**
     * @fn resta(Vector2 otro)
     * @brief resta al vector los de otro vector
     * @return Vector2 vector
     */
    public Vector2 resta(Vector2 otro)
    {
        this.x -= otro.x;
        this.y -= otro.y;

        return this;
    }

    /**
     * @fn multiplica
     * @brief multiplico por escala al vector
     * @return Vector2 vector
     */
    public Vector2 multiplica(float escala)
    {
        this.x *= escala;
        this.y *= escala;

        return this;
    }

    /**
     * @fn len
     * @return len
     */
    public float len()
    {   
        return (float)Math.sqrt(x * x + y * y);
    }

    /**
     * @fn normaliza
     * @brief obtiene la normla del vector
     * @return Vector2 vector de la normal
     */
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

    /**
     * @fn angulo
     * @brief obtiene el angulo del vector
     * @return float angulo
     */
    public float angulo()
    {
        float angulo = (float)Math.atan2(y, x) * A_GRADOS;

        if (angulo < 0)
        {

            angulo += 360;
        }
        return angulo;
    }       

    /**
     * @fn rota
     * @brief rota el vector el angulo dado
     * @return Vector2 vector
     */
    public Vector2 rotar(float angulo)
    {
        float rad = angulo * A_RADIANES;
        float cos = (float)Math.cos(rad);
        float sin = (float)Math.sin(rad);

        float nuevaX = this.x * cos - this.y * sin;
        float nuevaY = this.x * sin + this.y * cos;

        this.x = nuevaX;
        this.y = nuevaY;

        return this;
    }

    /**
     * @fn dist(Vector2 otro)
     * @brief obtiene la distancia a otro vector
     * @return float distancia
     */
    public float dist(Vector2 otro)
    {
        float distanciaX = this.x - otro.x;
        float distanciaY = this.y - otro.y;  

        return (float)Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);
    }   

    /**
     * @fn dist(float x, float y)
     * @brief obtiene la distancia a (x,y)
     * @return float distancia
     */
    public float dist(float x, float y)
    {
        float distanciaX = this.x - x;
        float distanciaY = this.y - y;    

        return (float)Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);
    }   

    /**
     * @fn distCuadrada(Vector2 otro)
     * @brief obtiene la distancia cuadrada a otro vector
     * @return float distancia cuadrada
     */
    public float distCuadrada(Vector2 otro)
    {
        float distanciaX = this.x - otro.x;
        float distanciaY = this.y - otro.y;   

        return distanciaX * distanciaX + distanciaY * distanciaY;
    }

    /**
     * @fn distCuadrada(float x, float y)
     * @brief obtiene la distancia cuadrada a (x,y)
     * @return float distancia cuadrada
     */
    public float distCuadrada(float x, float y)
    {
        float distanciaX = this.x - x;
        float distanciaY = this.y - y;        

        return distanciaX * distanciaX + distanciaY * distanciaY;
    }       
}
