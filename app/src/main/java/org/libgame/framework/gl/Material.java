package org.libgame.framework.gl;

import javax.microedition.khronos.opengles.GL10;

/**
 * @class Material
 * @brief clase Material
 */
public class Material
{
	float[] ambiente = { 0.2f, 0.2f, 0.2f, 1.0f };
	float[] difusa = { 1.0f, 1.0f, 1.0f, 1.0f };
	float[] especular = { 0.0f, 0.0f, 0.0f, 1.0f };	

	public void ponAmbiente(float r, float g, float b, float a)
	{
		ambiente[0] = r;
		ambiente[1] = g;
		ambiente[2] = b;
		ambiente[3] = a;
	}

	public void ponDifusa(float r, float g, float b, float a)
	{
		difusa[0] = r;
		difusa[1] = g;
		difusa[2] = b;
		difusa[3] = a;
	}

	public void ponEspecular(float r, float g, float b, float a)
	{
		especular[0] = r;
		especular[1] = g;
		especular[2] = b;
		especular[3] = a;
	}

	public void activa(GL10 gl)
	{
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, ambiente, 0);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, difusa, 0);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, especular, 0);					
	}
}
