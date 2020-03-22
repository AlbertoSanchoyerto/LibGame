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

/**
 * @class AndroidFicheroIO
 * @brief manejador del FicheroIO (sistema de ficheros)
 */
public class AndroidFicheroIO implements FicheroIO
{
    AssetManager assets;
    String rutaAlmacenamientoExterno;

    public AndroidFicheroIO(AssetManager assets)
    {
        this.assets = assets;
        this.rutaAlmacenamientoExterno = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    /**
     * @fn leerAsset
     * @brief lee un fichero de assets
     */
    @Override
    public InputStream leerAsset(String nombreFichero) throws IOException
    {
        return assets.open(nombreFichero);
    }

    /**
     * @fn leerFichero
     * @brief lee un fichero del almacenamiento externo
     */
    @Override
    public InputStream leerFichero(String nombreFichero) throws IOException
    {
        return new FileInputStream(rutaAlmacenamientoExterno + nombreFichero);
    }

    /**
     * @fn escribirFichero
     * @brief escribe un fichero al almacenamiento externo
     */
    @Override
    public OutputStream escribirFichero(String nombreFichero) throws IOException
    {
        return new FileOutputStream(rutaAlmacenamientoExterno + nombreFichero);
    }
}
