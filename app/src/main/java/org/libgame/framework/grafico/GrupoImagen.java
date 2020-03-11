package org.libgame.framework.grafico;

import javax.microedition.khronos.opengles.GL10;

import android.util.FloatMath;

import org.libgame.framework.gl.GLGrafico;
import org.libgame.framework.matematica.Vector2;

public class GrupoImagen
{

    final float[] verticesBuffer;
    int bufferIndice;
    final Vertices vertices;
    int numSprites;    

    public GrupoImagen(GLGrafico glGraficos, int maxSprites)
    {

		this.verticesBuffer = new float[maxSprites * 4 * 4];        
		this.vertices = new Vertices(glGraficos, maxSprites * 4, maxSprites * 6, false, true);
		this.bufferIndice = 0;
		this.numSprites = 0;

		short[] indices = new short[maxSprites * 6];
		int len = indices.length;
		short j = 0;

		for (int i = 0; i < len; i += 6, j += 4)
		{

			indices[i + 0] = (short)(j + 0);
			indices[i + 1] = (short)(j + 1);
			indices[i + 2] = (short)(j + 2);
			indices[i + 3] = (short)(j + 2);
			indices[i + 4] = (short)(j + 3);
			indices[i + 5] = (short)(j + 0);
		}

		vertices.ponIndices(indices, 0, indices.length);                
    }

    public void iniciaLote(Textura textura)
    {

		textura.une();
		numSprites = 0;
		bufferIndice = 0;
    }

    public void finalizaLote()
    {

		vertices.ponVertices(verticesBuffer, 0, bufferIndice);
		vertices.une();
		vertices.dibuja(GL10.GL_TRIANGLES, 0, numSprites * 6);
		vertices.desune();
    }

    public void dibujaSprite(float x, float y, float ancho, float alto, TexturaRegion region)
    {

		float medioAncho = ancho / 2;
		float medioAlto = alto / 2;
		float x1 = x - medioAncho;
		float y1 = y - medioAlto;
		float x2 = x + medioAncho;
		float y2 = y + medioAlto;

		verticesBuffer[bufferIndice++] = x1;
		verticesBuffer[bufferIndice++] = y1;
		verticesBuffer[bufferIndice++] = region.u1;
		verticesBuffer[bufferIndice++] = region.v2;

		verticesBuffer[bufferIndice++] = x2;
		verticesBuffer[bufferIndice++] = y1;
		verticesBuffer[bufferIndice++] = region.u2;
		verticesBuffer[bufferIndice++] = region.v2;

		verticesBuffer[bufferIndice++] = x2;
		verticesBuffer[bufferIndice++] = y2;
		verticesBuffer[bufferIndice++] = region.u2;
		verticesBuffer[bufferIndice++] = region.v1;

		verticesBuffer[bufferIndice++] = x1;
		verticesBuffer[bufferIndice++] = y2;
		verticesBuffer[bufferIndice++] = region.u1;
		verticesBuffer[bufferIndice++] = region.v1;

		numSprites++;
    }

    public void dibujaSprite(float x, float y, float ancho, float alto, float angulo, TexturaRegion region)
    {

		float medioAncho = ancho / 2;
		float medioAlto = alto / 2;

		float rad = angulo * Vector2.A_RADIANES;
		float cos = (float)Math.cos(rad);
		float sin = (float)Math.sin(rad);

		float x1 = -medioAncho * cos - (-medioAlto) * sin;
		float y1 = -medioAncho * sin + (-medioAlto) * cos;
		float x2 = medioAncho * cos - (-medioAlto) * sin;
		float y2 = medioAncho * sin + (-medioAlto) * cos;
		float x3 = medioAncho * cos - medioAlto * sin;
		float y3 = medioAncho * sin + medioAlto * cos;
		float x4 = -medioAncho * cos - medioAlto * sin;
		float y4 = -medioAncho * sin + medioAlto * cos;

		x1 += x;
		y1 += y;
		x2 += x;
		y2 += y;
		x3 += x;
		y3 += y;
		x4 += x;
		y4 += y;

		verticesBuffer[bufferIndice++] = x1;
		verticesBuffer[bufferIndice++] = y1;
		verticesBuffer[bufferIndice++] = region.u1;
		verticesBuffer[bufferIndice++] = region.v2;

		verticesBuffer[bufferIndice++] = x2;
		verticesBuffer[bufferIndice++] = y2;
		verticesBuffer[bufferIndice++] = region.u2;
		verticesBuffer[bufferIndice++] = region.v2;

		verticesBuffer[bufferIndice++] = x3;
		verticesBuffer[bufferIndice++] = y3;
		verticesBuffer[bufferIndice++] = region.u2;
		verticesBuffer[bufferIndice++] = region.v1;

		verticesBuffer[bufferIndice++] = x4;
		verticesBuffer[bufferIndice++] = y4;
		verticesBuffer[bufferIndice++] = region.u1;
		verticesBuffer[bufferIndice++] = region.v1;

		numSprites++;
    }
}
