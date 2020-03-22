package org.libgame.framework.sistema;

import android.content.*;
import android.hardware.*;
import android.view.*;
import java.io.*;

/**
 * @class AndroidCamera
 * @brief clase AndroidCamera
 */
public class AndroidCamera extends SurfaceView
implements SurfaceHolder.Callback
{
    Camera camera;
    Camera.Parameters parametros;

    public AndroidCamera(Context context)
    {
        super(context);
        getHolder().addCallback(this);
        getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder)
    {
        camera = Camera.open();
		camera.setDisplayOrientation(90);
    }

    public void surfaceChanged(SurfaceHolder holder, int formato, int ancho, int alto)
    {
        try
		{
            camera.setPreviewDisplay(holder);
        }
		catch ( IOException e )
		{
            e.printStackTrace();
        }
        camera.startPreview();
    }

    public void surfaceDestroyed(SurfaceHolder holder)
    {
        camera.stopPreview();
        camera.release();
        camera = null;
    }
}
