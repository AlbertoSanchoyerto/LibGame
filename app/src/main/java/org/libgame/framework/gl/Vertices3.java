package org.libgame.framework.gl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import org.libgame.framework.gl.GLGraficos;

/**
 * @class Vertices3
 * @brief clase Vertices3 vertices 3D
 */
public class Vertices3
{
    final GLGraficos glGraficos;
    final boolean tieneColor;
    final boolean tieneCoordsTex;
    final boolean tieneNormales;
    final int tamVertex;
    final IntBuffer vertices;
    final int[] tmpBuffer;
    final ShortBuffer indices;

    public Vertices3(GLGraficos glGraficos, int maxVertices, int maxIndices,
                     boolean tieneColor, boolean tieneCoordsTex, boolean tieneNormales)
    {
        this.glGraficos = glGraficos;
        this.tieneColor = tieneColor;
        this.tieneCoordsTex = tieneCoordsTex;
        this.tieneNormales = tieneNormales;
        this.tamVertex = (3 + (tieneColor ? 4 : 0) + (tieneCoordsTex ? 2 : 0) + (tieneNormales ? 3
            : 0)) * 4;
        this.tmpBuffer = new int[maxVertices * tamVertex / 4];

        ByteBuffer buffer = ByteBuffer.allocateDirect(maxVertices * tamVertex);
        buffer.order(ByteOrder.nativeOrder());
        vertices = buffer.asIntBuffer();

        if (maxIndices > 0)
        {
            buffer = ByteBuffer.allocateDirect(maxIndices * Short.SIZE / 8);
            buffer.order(ByteOrder.nativeOrder());
            indices = buffer.asShortBuffer();
        }
        else
        {
            indices = null;
        }
    }

    /**
     * @fn ponVertices
     * @param float[] vertices
     * @param int offset
     * @param int length
     */
    public void ponVertices(float[] vertices, int offset, int length)
    {
        this.vertices.clear();
        int len = offset + length;
        for (int i = offset, j = 0; i < len; i++, j++)
            tmpBuffer[j] = Float.floatToRawIntBits(vertices[i]);
        this.vertices.put(tmpBuffer, 0, length);
        this.vertices.flip();
    }

    /**
     * @fn ponIndices
     * @param short[] indices
     * @param int offset
     * @param int length
     */
    public void ponIndices(short[] indices, int offset, int length)
    {
        this.indices.clear();
        this.indices.put(indices, offset, length);
        this.indices.flip();
    }

    /**
     * @fn une
     */
    public void une()
    {
        GL10 gl = glGraficos.cogeGL();

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        vertices.position(0);
        gl.glVertexPointer(3, GL10.GL_FLOAT, tamVertex, vertices);

        if (tieneColor)
        {
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            vertices.position(3);
            gl.glColorPointer(4, GL10.GL_FLOAT, tamVertex, vertices);
        }

        if (tieneCoordsTex)
        {
            gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
            vertices.position(tieneColor ? 7 : 3);
            gl.glTexCoordPointer(2, GL10.GL_FLOAT, tamVertex, vertices);
        }

        if (tieneNormales)
        {
            gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
            int offset = 3;
            if (tieneColor)
                offset += 4;
            if (tieneCoordsTex)
                offset += 2;
            vertices.position(offset);
            gl.glNormalPointer(GL10.GL_FLOAT, tamVertex, vertices);
        }
    }

    /**
     * @fn dibuja
     * @param int tipoPrimitiva
     * @param int offset
     * @param int numVertices
     */
    public void dibuja(int tipoPrimitiva, int offset, int numVertices)
    {
        GL10 gl = glGraficos.cogeGL();

        if (indices != null)
        {
            indices.position(offset);
            gl.glDrawElements(tipoPrimitiva, numVertices,
                              GL10.GL_UNSIGNED_SHORT, indices);
        }
        else
        {
            gl.glDrawArrays(tipoPrimitiva, offset, numVertices);
        }
    }

    /**
     * @fn desune
     */
    public void desune()
    {
        GL10 gl = glGraficos.cogeGL();
        if (tieneCoordsTex)
            gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        if (tieneColor)
            gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

        if (tieneNormales)
            gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
    }

    /**
     * @fn cogeNumIndices
     * @return int numero de indices
     */
    public int cogeNumIndices()
    {
        return indices.limit();
    }

    /**
     * @fn cogeNumVertices
     * @return int numero de vertices
     */
    public int cogeNumVertices()
    {
        return vertices.limit() / (tamVertex / 4);
    }
}
