package org.libgame.framework.gl;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.opengl.Matrix;

import org.libgame.framework.matematica.Vector3;

/**
 * @class CamaraEuler
 * @brief clase CamaraEuler para graficos en 3D
 */
public class CamaraEuler
{
	final Vector3 posicion = new Vector3();
	float guinada;
	float cabeceo;
	float campoDeVision;
	float relacionAspecto;
	float cerca;
	float legos;

	public CamaraEuler(float campoDeVision, float relacionAspecto, float cerca, float legos) {
		this.campoDeVision = campoDeVision;
		this.relacionAspecto = relacionAspecto;
		this.cerca = cerca;
		this.legos = legos;
	}

	public Vector3 cogePosicion()
	{
		return posicion;
	}
	
	public float cogeGuinada()
	{
		return guinada;
	}
	
	public float cogeCabeceo()
	{
		return cabeceo;
	}

	public void ponAngulos(float guinada, float cabeceo)
	{
		if (cabeceo < -90)
			cabeceo = -90;
		if (cabeceo > 90)
			cabeceo = 90;
		this.guinada = guinada;
		this.cabeceo = cabeceo;
	}

	public void rotar(float incGuinada, float incCabeceo)
	{
		this.guinada += incGuinada;
		this.cabeceo += incCabeceo;
		if (cabeceo < -90)
			cabeceo = -90;
		if (cabeceo > 90)
			cabeceo = 90;
	}

	public void ponMatrices(GL10 gl)
	{
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, campoDeVision, relacionAspecto, cerca, legos);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glRotatef(-cabeceo, 1, 0, 0);
		gl.glRotatef(-guinada, 0, 1, 0);
		gl.glTranslatef(-posicion.x, -posicion.y, -posicion.z);
	}

	final float[] matrix = new float[16];
	final float[] inVec = { 0, 0, -1, 1 };
	final float[] outVec = new float[4];
	final Vector3 direccion = new Vector3();

	public Vector3 cogeDireccion()
	{
		Matrix.setIdentityM(matrix, 0);
		Matrix.rotateM(matrix, 0, guinada, 0, 1, 0);
		Matrix.rotateM(matrix, 0, cabeceo, 1, 0, 0);
		Matrix.multiplyMV(outVec, 0, matrix, 0, inVec, 0);
		direccion.pon(outVec[0], outVec[1], outVec[2]);
		return direccion;
	}
}
