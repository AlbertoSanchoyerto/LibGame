package org.libgame.framework.gl;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;

import org.libgame.framework.matematica.Vector3;

/**
 * @class MirarACamara
 * @brief clase MirarACamara
 */
public class MirarACamara
{
	final Vector3 posicion;
	final Vector3 arriba;
	final Vector3 mirarA;
	float campoDeVision;
	float ratioAspecto;
	float cerca;
	float lejos;

	public MirarACamara(float campoDeVision, float ratioAspecto, float cerca, float lejos)
	{
		this.campoDeVision = campoDeVision;
		this.ratioAspecto = ratioAspecto;
		this.cerca = cerca;
		this.lejos = lejos;
		
		posicion = new Vector3();
		arriba = new Vector3(0, 1, 0);
		mirarA = new Vector3(0,0,-1);
	}
	
	public Vector3 cogePosicion()
	{
		return posicion;
	}
	
	public Vector3 cogeArriba()
	{
		return arriba;
	}
	
	public Vector3 cogeMirarA()
	{
		return mirarA;
	}
	
	public void ponMatrices(GL10 gl)
	{
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, campoDeVision, ratioAspecto, cerca, lejos);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, posicion.x, posicion.y, posicion.z, mirarA.x, mirarA.y, mirarA.z, arriba.x, arriba.y, arriba.z);
	}
}
