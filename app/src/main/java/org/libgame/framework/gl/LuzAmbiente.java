package org.libgame.framework.gl;

import javax.microedition.khronos.opengles.GL10;

/**
 * @class LuzAmbiente
 * @brief clase LuzAmbiente
 */
public class LuzAmbiente
{
	float[] color = {0.2f, 0.2f, 0.2f, 1};
	
	public void ponColor(float r, float g, float b, float a)
	{
		color[0] = r;
		color[1] = g;
		color[2] = b;
		color[3] = a;		
	}	
	
	public void activa(GL10 gl)
	{
		gl.glLightModelfv(GL10.GL_LIGHT_MODEL_AMBIENT, color, 0);
	}
}
