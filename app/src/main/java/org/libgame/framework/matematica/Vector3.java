package org.libgame.framework.matematica;

import android.opengl.Matrix;
import android.util.FloatMath;

/**
 * @class Vector3
 * @brief clase Vector3 vectores en 3D
 *
 * clase que define una posición X, Y, Z y
 * las operaciones copia, suma, resta...
 */
public class Vector3
{
    private static final float[] matriz = new float[16];
    private static final float[] inVec = new float[4];
    private static final float[] outVec = new float[4];
    public float x, y, z;

    public Vector3()
    {
    }

    public Vector3(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(Vector3 otro)
    {
        this.x = otro.x;
        this.y = otro.y;
        this.z = otro.z;
    }

    /**
     * @fn copia
     * @brief copia un vector
     * @return Vector3 vector
     */
    public Vector3 copia()
    {
        return new Vector3(x, y, z);
    }

    /**
     * @fn pon(float x, float y, float z)
     * @brief configura el vector con (x,y,z)
     * @return Vector3 vector
     */
    public Vector3 pon(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    /**
     * @fn pon(Vector3 otro)
     * @brief configura el vector con otro Vector3
     * @return Vector3 vector
     */
    public Vector3 pon(Vector3 otro)
    {
        this.x = otro.x;
        this.y = otro.y;
        this.z = otro.z;
        return this;
    }

    /**
     * @fn suma(float x, float y, float z)
     * @brief suma (x,y,z) al vector
     * @return Vector3 vector
     */
    public Vector3 suma(float x, float y, float z)
    {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    /**
     * @fn suma(Vector3 otro)
     * @brief suma al vector los de otro vector3
     * @return Vector3 vector
     */
    public Vector3 suma(Vector3 otro)
    {
        this.x += otro.x;
        this.y += otro.y;
        this.z += otro.z;
        return this;
    }

    /**
     * @fn resta(float x, float y, float z)
     * @brief resta (x,y,z) al vector
     * @return Vector3 vector
     */
    public Vector3 resta(float x, float y, float z)
    {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    /**
     * @fn resta(Vector3 otro)
     * @brief resta al vector los de otro vector
     * @return Vector3 vector
     */
    public Vector3 resta(Vector3 otro)
    {
        this.x -= otro.x;
        this.y -= otro.y;
        this.z -= otro.z;
        return this;
    }

    /**
     * @fn multiplica
     * @brief multiplico por escala al vector
     * @return Vector3 vector
     */
    public Vector3 muliplica(float escala)
    {
        this.x *= escala;
        this.y *= escala;
        this.z *= escala;
        return this;
    }

    /**
     * @fn len
     * @return len
     */
    public float len()
    {
        return (float)Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * @fn normaliza
     * @brief obtiene la normla del vector
     * @return Vector3 vector de la normal
     */
    public Vector3 normaliza()
    {
        float len = len();
        if (len != 0) {
            this.x /= len;
            this.y /= len;
            this.z /= len;
        }
        return this;
    }

    /**
     * @fn rotar
     * @brief rota el vector3 en (angulo,axisX,axisY,axisZ)
     * @param float angulo
     * @param float axisX
     * @param float axisY
     * @param float axisZ
     * @return Vector3 vector
     */
    public Vector3 rotar(float angulo, float axisX, float axisY, float axisZ)
    {
        inVec[0] = x;
        inVec[1] = y;
        inVec[2] = z;
        inVec[3] = 1;
        Matrix.setIdentityM(matriz, 0);
        Matrix.rotateM(matriz, 0, angulo, axisX, axisY, axisZ);
        Matrix.multiplyMV(outVec, 0, matriz, 0, inVec, 0);
        x = outVec[0];
        y = outVec[1];
        z = outVec[2];
        return this;
    }

    /**
     * @fn dist(Vector3 otro)
     * @brief obtiene la distancia a otro vector3
     * @return float distancia
     */
    public float dist(Vector3 otro)
    {
        float distX = this.x - otro.x;
        float distY = this.y - otro.y;
        float distZ = this.z - otro.z;
        return (float)Math.sqrt(distX * distX + distY * distY + distZ * distZ);
    }

    /**
     * @fn dist(float x, float y, float z)
     * @brief obtiene la distancia a (x,y,z)
     * @return float distancia
     */
    public float dist(float x, float y, float z)
    {
        float distX = this.x - x;
        float distY = this.y - y;
        float distZ = this.z - z;
        return (float)Math.sqrt(distX * distX + distY * distY + distZ * distZ);
    }

    /**
     * @fn distCuadrada(Vector3 otro)
     * @brief obtiene la distancia cuadrada a otro vector3
     * @return float distancia cuadrada
     */
    public float distCuadrada(Vector3 otro)
    {
        float distX = this.x - otro.x;
        float distY = this.y - otro.y;
        float distZ = this.z - otro.z;
        return distX * distX + distY * distY + distZ * distZ;
    }

    /**
     * @fn distCuadrada(float x, float y, float z)
     * @brief obtiene la distancia cuadrada a (x,y,z)
     * @return float distancia cuadrada
     */
    public float distCuadrada(float x, float y, float z)
    {
        float distX = this.x - x;
        float distY = this.y - y;
        float distZ = this.z - z;
        return distX * distX + distY * distY + distZ * distZ;
    }
}
