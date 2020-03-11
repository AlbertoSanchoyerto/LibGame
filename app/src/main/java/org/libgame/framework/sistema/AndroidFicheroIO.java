package org.libgame.framework.sistema;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.*;

import android.content.res.AssetManager;
import android.os.Environment;

import org.libgame.framework.FicheroIO;
import android.util.*;

public class AndroidFicheroIO implements FicheroIO
{

    AssetManager assets;
    String rutaAlmacenamientoExterno;

    public AndroidFicheroIO(AssetManager assets)
    {

		this.assets = assets;
		this.rutaAlmacenamientoExterno = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    @Override
    public InputStream leerAsset(String nombreFichero) throws IOException
    {
		
		return assets.open(nombreFichero);
    }

    @Override
    public InputStream leerFichero(String nombreFichero) throws IOException
    {

		return new FileInputStream(rutaAlmacenamientoExterno + nombreFichero);
    }

    @Override
    public OutputStream escribirFichero(String nombreFichero) throws IOException
    {

		return new FileOutputStream(rutaAlmacenamientoExterno + nombreFichero);
    }
}
