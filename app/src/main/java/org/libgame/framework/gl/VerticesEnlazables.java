package org.libgame.framework.gl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import org.libgame.framework.gl.GLGrafico;

public class VerticesEnlazables
{
    final GLGrafico glGrafico;
    final boolean tieneColor;
    final boolean tieneCoordsTex;
    final int tamVertex;
    final FloatBuffer vertices;
    final ShortBuffer indices;

    public VerticesEnlazables(GLGrafico glGrafico, int maxVertices, int maxIndices, boolean tieneColor, boolean tieneCoordsTex)
	{
        this.glGrafico = glGrafico;
        this.tieneColor = tieneColor;
        this.tieneCoordsTex = tieneCoordsTex;
        this.tamVertex = (2 + (tieneColor ?4: 0) + (tieneCoordsTex ?2: 0)) * 4;

        ByteBuffer buffer = ByteBuffer.allocateDirect(maxVertices * tamVertex);
        buffer.order(ByteOrder.nativeOrder());
        vertices = buffer.asFloatBuffer();

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

    public void ponVertices(float[] vertices, int offset, int length)
	{
        this.vertices.clear();
        this.vertices.put(vertices, offset, length);
        this.vertices.flip();
    }

    public void ponIndices(short[] indices, int offset, int length)
	{
        this.indices.clear();
        this.indices.put(indices, offset, length);
        this.indices.flip();
    }

	public void une()
	{
		GL10 gl = glGrafico.cogeGL();

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		vertices.position(0);
		gl.glVertexPointer(2, GL10.GL_FLOAT, tamVertex, vertices);

		if (tieneColor)
		{
			gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
			vertices.position(2);
			gl.glColorPointer(4, GL10.GL_FLOAT, tamVertex, vertices);
		}

		if (tieneCoordsTex)
		{
			gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
			vertices.position(tieneColor ?6: 2);
			gl.glTexCoordPointer(2, GL10.GL_FLOAT, tamVertex, vertices);
		}
	}

	public void dibuja(int primitiveType, int offset, int numVertices)
	{        
		GL10 gl = glGrafico.cogeGL();

		if (indices != null)
		{
			indices.position(offset);
			gl.glDrawElements(primitiveType, numVertices, GL10.GL_UNSIGNED_SHORT, indices);
		}
		else
		{
			gl.glDrawArrays(primitiveType, offset, numVertices);
		}        
	}

	public void desune()
	{
		GL10 gl = glGrafico.cogeGL();
		if (tieneCoordsTex)
			gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		if (tieneColor)
			gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
	}
}
