package org.libgame.framework.gl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import org.libgame.framework.gl.GLGraficos;
import org.libgame.framework.gl.Vertices3;

/**
 * @class Vertices3VBO
 * @brief clase Vertices3VBO
 */
public class Vertices3VBO extends Vertices3
{	
	int vboHandle;
	
	public Vertices3VBO(GLGraficos GLGraficos, int maxVertices, int maxIndices,
			boolean tieneColor, boolean tieneCoordsTex, boolean tieneNormales)
	{
		super(GLGraficos, maxVertices, maxIndices, tieneColor, tieneCoordsTex, tieneNormales);
		createHandle();
	}
	
	public void createHandle()
	{
		int[] tmp = new int[1];
		GL11 gl = (GL11)glGraficos.cogeGL();
		gl.glGenBuffers(1, tmp, 0);
		vboHandle = tmp[0];
	}

	public void ponVertices(float[] vertices, int offset, int length)
	{
		super.ponVertices(vertices, offset, length);
		
		GL11 gl = (GL11)glGraficos.cogeGL();
		gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, vboHandle);
		gl.glBufferData(GL11.GL_ARRAY_BUFFER, this.vertices.limit() * 4, this.vertices, GL11.GL_STATIC_DRAW);
		gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, 0);
	}

	public void une()
	{
		GL11 gl = (GL11)glGraficos.cogeGL();

		gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, vboHandle);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);			
		gl.glVertexPointer(3, GL10.GL_FLOAT, tamVertex, 0);

		if (tieneColor)
		{
			gl.glEnableClientState(GL10.GL_COLOR_ARRAY);			
			gl.glColorPointer(4, GL10.GL_FLOAT, tamVertex, 3*4);
		}

		if (tieneCoordsTex)
		{
			gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);			
			gl.glTexCoordPointer(2, GL10.GL_FLOAT, tamVertex, (tieneColor ? 7 : 3) * 4);
		}

		if (tieneNormales)
		{
			gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
			int offset = 3;
			if (tieneColor)
				offset += 4;
			if (tieneCoordsTex)
				offset += 2;			
			gl.glNormalPointer(GL10.GL_FLOAT, tamVertex, offset * 4);
		}
	}

	public void dibuja(int tipoPrimitiva, int offset, int numVertices)
	{
		GL10 gl = glGraficos.cogeGL();

		if (indices != null)
		{
			indices.position(offset);
			gl.glDrawElements(tipoPrimitiva, numVertices,
					GL10.GL_UNSIGNED_SHORT, indices);
		} else {
			gl.glDrawArrays(tipoPrimitiva, offset, numVertices);
		}
	}

	public void desune()
	{
		GL11 gl = (GL11)glGraficos.cogeGL();
		if (tieneCoordsTex)
			gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		if (tieneColor)
			gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

		if (tieneNormales)
			gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		
		gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, 0);
	}
	
	public int cogeNumIndices()
	{
		return indices.limit();
	}
	
	public int cogeNumVertices()
	{
		return vertices.limit() / (tamVertex / 4);
	}
}
