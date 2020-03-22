package org.libgame.framework.sistema.control;

import android.content.Context;
import android.hardware.*;

/**
 * @class Orientacion
 * @brief clase Orientacion
 */
public class Orientacion implements SensorEventListener
{
    int direccion;
    int cabeceo;
	int alabeo;

    public Orientacion(Context context)
	{
        SensorManager manager = (SensorManager) context
                .getSystemService(Context.SENSOR_SERVICE);
        if (manager.getSensorList(Sensor.TYPE_ORIENTATION).size() != 0) {
            Sensor orientation = manager.getSensorList(
                    Sensor.TYPE_ORIENTATION).get(0);
            manager.registerListener(this, orientation,
                    SensorManager.SENSOR_DELAY_GAME);
        }	
    }
	
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
        // no hace nada aqui
    }

	@Override
	public void onSensorChanged(SensorEvent evento)
	{
		direccion = redondeaGrados( evento.values[0]);
		cabeceo = redondeaGrados( evento.values[1]);
		alabeo = redondeaGrados( evento.values[2]);
	}
	
	/**
	 * @fm cogerDireccion
	 * @return int direccion
	 */
    public int cogerDireccion()
	{
        return direccion;
    }
	
	/**
	 * @fm cogerCabeceo
	 * @return int cabeceo
	 */
    public int cogerCabeceo()
	{
        return cabeceo;
    }
	
	/**
	 * @fm cogerAlabeo
	 * @return int alabeo
	 */
    public int cogerAlabeo()
	{
        return alabeo;
    }
	
	/**
	 * @fn redondeaGrados
	 * @brieffuncion para redondear en negativo los grados dados por el sensor
	 * esto lo utilizo para que no me vailen los datos como locos
	 */
	private int redondeaGrados( float valor )
	{
		return Integer.parseInt(String.format("%3.0f", -valor ));
	}
}
