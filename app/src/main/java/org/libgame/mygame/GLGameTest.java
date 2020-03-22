package org.libgame.mygame;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import org.libgame.framework.Game;
import org.libgame.framework.gl.GLPantalla;
import org.libgame.framework.gl.GLGame;
import org.libgame.framework.gl.GLGraficos;
import org.libgame.framework.*;

/**
 * @class GLGameTest
 * @brief la clase GLGameTest ejemplo secillo de 2D
 */
public class GLGameTest extends GLGame
{

	@Override
	public Pantalla cogePantallaInicial()
	{
		return new TestPantalla(this);
	}

	class TestPantalla extends GLPantalla
	{
		Random rand = new Random();

		public TestPantalla(Game game)
		{
			super(game);
		}

		@Override
		public void presenta(float deltaTime)
		{
			GL10 gl = glGraficos.cogeGL();
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
