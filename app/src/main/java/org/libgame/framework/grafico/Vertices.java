package org.libgame.framework.grafico;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import org.libgame.framework.gl.GLGrafico;

public class Vertices
{

    final GLGrafico glGraficos;
    final boolean tieneColor;
    final boolean tieneTexCoords;
    final int vertexSize;
    final IntBuffer vertices;
    final int[] tmpBuffer;
    final ShortBuffer indices;

    public Vertices(GLGrafico glGraficos, int maxVertices, int maxIndices, boolean tieneColor, boolean tieneTexCoords)
    {

		this.glGraficos = glGraficos;
		this.tieneColor = tieneColor;
		this.tieneTexCoords = tieneTexCoords;
		this.vertexSize = (2 + (tieneColor ?4: 0) + (tieneTexCoords ?2: 0)) * 4;
		this.tmpBuffer = new int[maxVertices * vertexSize / 4];

		ByteBuffer buffer = ByteBuffer.allocateDirect(maxVertices * vertexSize);
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

    public void ponVertices(float[] vertices, int offset, int length)
    {

		this.vertices.clear();
		int len = offset + length;
		for (int i=offset, j=0; i < len; i++, j++) 
			tmpBuffer[j] = Float.floatToRawIntBits(vertices[i]);
		this.vertices.put(tmpBuffer, 0, length);
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

		GL10 gl = glGraficos.cogeGL();

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		vertices.position(0);
		gl.glVertexPointer(2, GL10.GL_FLOAT, vertexSize, vertices);

		if (tieneColor)
		{

			gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
			vertices.position(2);
			gl.glColorPointer(4, GL10.GL_FLOAT, vertexSize, vertices);
		}

		if (tieneTexCoords)
		{

			gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
			vertices.position(tieneColor ?6: 2);
			gl.glTexCoordPointer(2, GL10.GL_FLOAT, vertexSize, vertices);
		}
    }

    public void dibuja(int primitiveType, int offset, int numVertices)
    {

		GL10 gl = glGraficos.cogeGL();

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

		GL10 gl = glGraficos.cogeGL();
		if (tieneTexCoords)
		{

			gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		}
		if (tieneColor)
		{

			gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		}
    }
}
