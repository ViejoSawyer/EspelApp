package com.proyecto.albanbassante.espelapp;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TestLocalizacionActivity extends ActionBarActivity implements LocationListener {

    LocationManager handle; //Gestor del servicio de localización
    private boolean servicioActivo;

    private Button botonActivar;
    private TextView longitud;
    private TextView latitud;
    private TextView proveedor;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_localizacion);

        botonActivar = (Button)findViewById(R.id.BotonActivar);
        latitud = (TextView)findViewById(R.id.latitud);
        longitud = (TextView)findViewById(R.id.longitud);
        proveedor = (TextView)findViewById(R.id.proveedor);

        servicioActivo = false;
        //El botón activar permitirá activar y desactivar el servicio.
        botonActivar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (servicioActivo) {
                    pararServicio();
                } else {
                    iniciarServicio();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_localizacion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void pararServicio(){
        //Se para el servicio de localización
        servicioActivo = false;
        botonActivar.setText("Activar");
        //Se desactivan las notificaciones
        handle.removeUpdates(this);
    }
    public void iniciarServicio(){
        //Se activa el servicio de localización
        servicioActivo = true;
        botonActivar.setText("Desactivar");

        //Crea el objeto que gestiona las localizaciones
        handle = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        Criteria c = new Criteria();
        c.setAccuracy(Criteria.ACCURACY_FINE);
        //obtiene el mejor proveedor en función del criterio asignado
        //(la mejor precisión posible)
        provider = handle.getBestProvider(c, true);
        proveedor.setText(provider);
        //Se activan las notificaciones de localización con los parámetros: proveedor, tiempo mínimo de actualización, distancia mínima, Locationlistener
        handle.requestLocationUpdates(provider, 10000, 1, this);
        //Obtenemos la última posición conocida dada por el proveedor
        Location loc = handle.getLastKnownLocation(provider);
        muestraPosicionActual(loc);
    }
    public void muestraPosicionActual(Location loc){
        if(loc == null){//Si no se encuentra localización, se mostrará "Desconocida"
            longitud.setText("Desconocida");
            latitud.setText("Desconocida");
        }else{//Si se encuentra, se mostrará la latitud y longitud
            latitud.setText(String.valueOf(loc.getLatitude()));
            latitud.setText(String.valueOf(loc.getLongitude()));
        }
    }
    @Override
    public void onLocationChanged(Location location) {
        // Se ha encontrado una nueva localización
        muestraPosicionActual(location);
    }

    @Override
    public void onProviderDisabled(String provider) {
        // Proveedor deshabilitado
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Proveedor habilitado
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //Ha cambiado el estado del proveedor
    }
}
