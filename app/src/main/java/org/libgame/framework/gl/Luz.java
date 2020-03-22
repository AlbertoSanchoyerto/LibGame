package org.libgame.framework.gl;

import javax.microedition.khronos.opengles.GL10;

import org.libgame.framework.gl.GLGraficos;

/**
 * @class Luz
 * @brief clase Luz
 */
public class Luz
{
    final float[] ambiente;
    final float[] difusa;
    final float[] posicion;
    final GLGraficos glGraficos;    
    
    public Luz(GLGraficos glGraficos, boolean esDireccional)
	{
        this.glGraficos = glGraficos;
        ambiente = new float[] {0.2f, 0.2f, 0.2f, 1.0f};
        difusa = new float[] {1.0f, 1.0f, 1.0f, 1.0f};
        posicion = new float[] {0, 0, 0, esDireccional?0:1};        
    }
    
    public boolean esDireccional()
	{
        return posicion[3] == 0;
    }
    
    public void ponAmbiente(float r, float g, float b)
	{
        ambiente[0] = r;
        ambiente[1] = g;
        ambiente[2] = b;
    }
    
    public void ponDifusa(float r, float g, float b)
	{
        difusa[0] = r;
        difusa[1] = g;
        difusa[2] = b;
    }
    
    public void ponPosicion(float x, float y, float z)
	{
        posicion[0] = x;
        posicion[1] = y;
        posicion[2] = z;
    }
    
    public void activa(int numLuz)
	{
        GL10 gl = glGraficos.cogeGL();
        gl.glEnable(numLuz);
        gl.glLightfv(numLuz, GL10.GL_AMBIENT, ambiente, 0);
        gl.glLightfv(numLuz, GL10.GL_DIFFUSE, difusa, 0);
        gl.glLightfv(numLuz, GL10.GL_POSITION, posicion, 0);
    }       
}
