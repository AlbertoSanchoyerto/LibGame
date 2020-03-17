package org.libgame.framework.matematica;

import android.opengl.Matrix;
import android.util.FloatMath;

public class Vector3 {
	private static final float[] matriz = new float[16];
	private static final float[] inVec = new float[4];
	private static final float[] outVec = new float[4];
	public float x, y, z;

	public Vector3() {
	}

	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3(Vector3 otro) {
		this.x = otro.x;
		this.y = otro.y;
		this.z = otro.z;
	}

	public Vector3 copia() {
		return new Vector3(x, y, z);
	}

	public Vector3 pon(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	public Vector3 pon(Vector3 otro) {
		this.x = otro.x;
		this.y = otro.y;
		this.z = otro.z;
		return this;
	}

	public Vector3 suma(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public Vector3 suma(Vector3 otro) {
		this.x += otro.x;
		this.y += otro.y;
		this.z += otro.z;
		return this;
	}

	public Vector3 resta(float x, float y, float z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	public Vector3 resta(Vector3 otro) {
		this.x -= otro.x;
		this.y -= otro.y;
		this.z -= otro.z;
		return this;
	}

	public Vector3 muliplica(float escalar) {
		this.x *= escalar;
		this.y *= escalar;
		this.z *= escalar;
		return this;
	}

	public float len() {
		return (float)Math.sqrt(x * x + y * y + z * z);
	}

	public Vector3 normal() {
		float len = len();
		if (len != 0) {
			this.x /= len;
			this.y /= len;
			this.z /= len;
		}
		return this;
	}

	public Vector3 rotar(float angulo, float axisX, float axisY, float axisZ) {
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

	public float dist(Vector3 otro) {
		float distX = this.x - otro.x;
		float distY = this.y - otro.y;
		float distZ = this.z - otro.z;
		return (float)Math.sqrt(distX * distX + distY * distY + distZ * distZ);
	}

	public float dist(float x, float y, float z) {
		float distX = this.x - x;
		float distY = this.y - y;
		float distZ = this.z - z;
		return (float)Math.sqrt(distX * distX + distY * distY + distZ * distZ);
	}

	public float distCuadrada(Vector3 otro) {
		float distX = this.x - otro.x;
		float distY = this.y - otro.y;
		float distZ = this.z - otro.z;
		return distX * distX + distY * distY + distZ * distZ;
	}

	public float distCuadrada(float x, float y, float z) {
		float distX = this.x - x;
		float distY = this.y - y;
		float distZ = this.z - z;
		return distX * distX + distY * distY + distZ * distZ;
	}
}
