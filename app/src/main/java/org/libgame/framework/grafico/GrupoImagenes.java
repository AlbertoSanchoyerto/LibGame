package org.libgame.framework.grafico;

import javax.microedition.khronos.opengles.GL10;

import android.util.FloatMath;

import org.libgame.framework.gl.GLGraficos;
import org.libgame.framework.matematica.Vector2;

/**
 * @class GrupoImagenes
 * @brief clase GrupoImagenes
 */
public class GrupoImagenes
{
    final float[] bufferVertices;
    int indiceBuffer;
    final Vertices vertices;
    int numSprites;    

    public GrupoImagenes(GLGraficos glGraficos, int maxSprites)
    {
        this.bufferVertices = new float[maxSprites * 4 * 4];
        this.vertices = new Vertices(glGraficos, maxSprites * 4, maxSprites * 6, false, true);
        this.indiceBuffer = 0;
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
        indiceBuffer = 0;
    }

    public void finalizaLote()
    {
        vertices.ponVertices(bufferVertices, 0, indiceBuffer);
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

        bufferVertices[indiceBuffer++] = x1;
        bufferVertices[indiceBuffer++] = y1;
        bufferVertices[indiceBuffer++] = region.u1;
        bufferVertices[indiceBuffer++] = region.v2;

        bufferVertices[indiceBuffer++] = x2;
        bufferVertices[indiceBuffer++] = y1;
        bufferVertices[indiceBuffer++] = region.u2;
        bufferVertices[indiceBuffer++] = region.v2;

        bufferVertices[indiceBuffer++] = x2;
        bufferVertices[indiceBuffer++] = y2;
        bufferVertices[indiceBuffer++] = region.u2;
        bufferVertices[indiceBuffer++] = region.v1;

        bufferVertices[indiceBuffer++] = x1;
        bufferVertices[indiceBuffer++] = y2;
        bufferVertices[indiceBuffer++] = region.u1;
        bufferVertices[indiceBuffer++] = region.v1;

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

        bufferVertices[indiceBuffer++] = x1;
        bufferVertices[indiceBuffer++] = y1;
        bufferVertices[indiceBuffer++] = region.u1;
        bufferVertices[indiceBuffer++] = region.v2;

        bufferVertices[indiceBuffer++] = x2;
        bufferVertices[indiceBuffer++] = y2;
        bufferVertices[indiceBuffer++] = region.u2;
        bufferVertices[indiceBuffer++] = region.v2;

        bufferVertices[indiceBuffer++] = x3;
        bufferVertices[indiceBuffer++] = y3;
        bufferVertices[indiceBuffer++] = region.u2;
        bufferVertices[indiceBuffer++] = region.v1;

        bufferVertices[indiceBuffer++] = x4;
        bufferVertices[indiceBuffer++] = y4;
        bufferVertices[indiceBuffer++] = region.u1;
        bufferVertices[indiceBuffer++] = region.v1;

        numSprites++;
    }
}
