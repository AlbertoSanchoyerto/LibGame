package org.libgame.framework.gl;

import javax.microedition.khronos.opengles.GL10;

public class PuntoLuz
{
	float[] ambiente = { 0.2f, 0.2f, 0.2f, 1.0f };
	float[] difusa = { 1.0f, 1.0f, 1.0f, 1.0f };
	float[] especular = { 0.0f, 0.0f, 0.0f, 1.0f };
	float[] posicion = { 0, 0, 0, 1 };
	int ultimaIdLuz = 0;

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

	public void ponPosicion(float x, float y, float z)
	{
		posicion[0] = x;
		posicion[1] = y;
		posicion[2] = z;
	}

	public void activa(GL10 gl, int idLuz)
	{
		gl.glEnable(idLuz);
		gl.glLightfv(idLuz, GL10.GL_AMBIENT, ambiente, 0);
		gl.glLightfv(idLuz, GL10.GL_DIFFUSE, difusa, 0);
		gl.glLightfv(idLuz, GL10.GL_SPECULAR, especular, 0);
		gl.glLightfv(idLuz, GL10.GL_POSITION, posicion, 0);
		ultimaIdLuz = idLuz;
	}

	public void desactiva(GL10 gl)
	{
		gl.glDisable(ultimaIdLuz);
	}
}
