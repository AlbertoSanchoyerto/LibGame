package org.libgame.framework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @interface FicheroIO
 * @brief interface de FicheroIO
 */
public interface FicheroIO
{
    public InputStream leerAsset(String nombreFichero) throws IOException;

    public InputStream leerFichero(String nombreFichero) throws IOException;

    public OutputStream escribirFichero(String nombreFichero) throws IOException;
}
