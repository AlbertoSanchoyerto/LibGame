package org.libgame.framework.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AndroidVistaRapida extends SurfaceView implements Runnable
{

    AndroidGame game;
    Bitmap framebuffer;
    Thread renderThread = null;
    SurfaceHolder holder;
    volatile boolean corriendo = false;

    public AndroidVistaRapida(AndroidGame game, Bitmap framebuffer)
    {

		super(game);
		this.game = game;
		this.framebuffer = framebuffer;
		this.holder = getHolder();
    }

    public void resume()
    {

		corriendo = true;
		renderThread = new Thread(this);
		renderThread.start();         
    }      

    public void run()
    {

		Rect dstRect = new Rect();
		long tiempoInicial = System.nanoTime();
		while (corriendo)
		{

			if (!holder.getSurface().isValid())
			{

				continue;
			}

			float deltaTime = (System.nanoTime() - tiempoInicial) / 1000000000.0f;
			tiempoInicial = System.nanoTime();

			game.cogePantallaActual().actualiza(deltaTime);
			game.cogePantallaActual().presenta(deltaTime);

			Canvas canvas = holder.lockCanvas();
			canvas.getClipBounds(dstRect);
			canvas.drawBitmap(framebuffer, null, dstRect, null);                           
			holder.unlockCanvasAndPost(canvas);
		}
    }

    public void pause()
    {

		corriendo = false;                        
		while (true)
		{

			try
			{

				renderThread.join();
				break;
			}
			catch (InterruptedException e)
			{

				// retry
			}
		}
    }
}
