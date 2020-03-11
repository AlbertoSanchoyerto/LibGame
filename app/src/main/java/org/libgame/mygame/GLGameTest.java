package org.libgame.mygame;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import org.libgame.framework.Game;
import org.libgame.framework.gl.GLPantalla;
import org.libgame.framework.gl.GLGame;
import org.libgame.framework.gl.GLGrafico;
import org.libgame.framework.*;

public class GLGameTest extends GLGame
{

	@Override
	public Pantalla cogePantallaInicial()
	{
		return new TestScreen(this);
	}

	class TestScreen extends Pantalla
	{
		GLGrafico glGrafico;
		Random rand = new Random();

		public TestScreen(Game game)
		{
			super(game);
			glGrafico = ((GLGame) game).cogeGLGrafico();
		}

		@Override
		public void presenta(float deltaTime)
		{
			GL10 gl = glGrafico.cogeGL();
			gl.glClearColor(rand.nextFloat(), rand.nextFloat(),
							rand.nextFloat(), 1);
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			
		}

		@Override
		public void actualiza(float deltaTime)
		{
		}

		@Override
		public void pausa()
		{
		}

		@Override
		public void resumen()
		{
		}

		@Override
		public void libera()
		{
		}
	}
}
