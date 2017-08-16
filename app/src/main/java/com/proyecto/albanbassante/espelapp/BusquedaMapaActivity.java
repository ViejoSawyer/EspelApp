package com.proyecto.albanbassante.espelapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.qozix.tileview.TileView;
import com.qozix.tileview.graphics.BitmapDecoderAssets;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.text.DateFormat;
import java.util.Date;

public class BusquedaMapaActivity extends ActionBarActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    String busqueda;
    String[] lugaresPersonas;
    String[] posX;
    String[] posY;

    double top, left, right, bottom;

    int secuencialPos;
    int width;
    int height;

    private static final String TAG = "LocationActivity";
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    Button btnFusedLocation;
    TextView tvLocation;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mCurrentLocation;
    String mLastUpdateTime;

    TileView tileView;

    ImageView markerA;
    ImageView markerB;


    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Log.d(TAG, "onCreate ...............................");
        //show error dialog if GoolglePlayServices not available
        if (!isGooglePlayServicesAvailable()) {
            //finish();
            Toast.makeText(getApplicationContext(), "Es necesario tener el servicio de Google Play activo para usar la localización", Toast.LENGTH_SHORT).show();
        }
        createLocationRequest();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        Bundle extras = getIntent().getExtras();
        busqueda = extras.getString("busqueda");
        lugaresPersonas = getResources().getStringArray(R.array.lugares);
        posX = getResources().getStringArray(R.array.posX);
        posY = getResources().getStringArray(R.array.posY);

        // creating LinearLayout
        LinearLayout linLayout = new LinearLayout(this);
        // specifying vertical orientation
        linLayout.setOrientation(LinearLayout.VERTICAL);
        // creating LayoutParams
        LayoutParams linLayoutParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        linLayout.setBackgroundColor(getResources().getColor(R.color.primary_color));

        // set LinearLayout as a root element of the screen
        setContentView(linLayout, linLayoutParam);

        LinearLayout.LayoutParams lpView = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        lpView.gravity = Gravity.CENTER_HORIZONTAL;

        Button btnLocalizar = new Button(this);
        btnLocalizar.setText("Posición Actual");
        btnLocalizar.setLayoutParams(lpView);
        linLayout.addView(btnLocalizar, lpView);




        tileView = new TileView(this);
        tileView.setDecoder(new BitmapDecoderAssets());

        //Obteniendo la posicion del lugar buscado
        for(int i=0; i<lugaresPersonas.length; i++ ){
            if (busqueda.equals(lugaresPersonas[i])){
                secuencialPos=i;
                break;
            }
        }
        //Carga mapa del primer piso
        if(secuencialPos >= 0 && secuencialPos <= 206){
            width = 6259;
            height = 3716;
            tileView.setSize(width, height);
            tileView.addDetailLevel(1f, "tiles1/1000_%col%_%row%.png", "downsamples1/primerpiso.png");
            tileView.addDetailLevel(0.5f, "tiles1/500_%col%_%row%.png", "downsamples1/primerpiso.png");
            tileView.addDetailLevel(0.25f, "tiles1/250_%col%_%row%.png", "downsamples1/primerpiso.png");
            tileView.addDetailLevel(0.125f, "tiles1/125_%col%_%row%.png", "downsamples1/primerpiso.png");
            SnackbarManager.show(
                    Snackbar.with(getApplicationContext()) // context
                            .text("Primer Piso") // text to be displayed
                            .color(Color.LTGRAY) // change the background color
                            .actionLabel("Ok")
                            .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                    , this);
        }
        //Carga mapa del segundo piso
        if(secuencialPos >= 207 && secuencialPos <= 236){
            width = 6259;
            height = 3733;
            tileView.setSize(width, height);
            tileView.addDetailLevel(1f, "tiles2/1000_%col%_%row%.png", "downsamples2/segundopiso.png");
            tileView.addDetailLevel(0.5f, "tiles2/500_%col%_%row%.png", "downsamples2/segundopiso.png");
            tileView.addDetailLevel(0.25f, "tiles2/250_%col%_%row%.png", "downsamples2/segundopiso.png");
            tileView.addDetailLevel(0.125f, "tiles2/125_%col%_%row%.png", "downsamples2/segundopiso.png");
            SnackbarManager.show(
                    Snackbar.with(getApplicationContext()) // context
                            .text("Segundo Piso") // text to be displayed
                            .color(Color.LTGRAY) // change the background color
                            .actionLabel("Ok")
                            .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                    , this);
        }
        //Carga mapa del terpiso piso
        if(secuencialPos >= 237 && secuencialPos <= 262){
            width = 6250;
            height = 3722;
            tileView.setSize(width, height);
            tileView.addDetailLevel(1f, "tiles3/1000_%col%_%row%.png", "downsamples3/tercerpiso.png");
            tileView.addDetailLevel(0.5f, "tiles3/500_%col%_%row%.png", "downsamples3/tercerpiso.png");
            tileView.addDetailLevel(0.25f, "tiles3/250_%col%_%row%.png", "downsamples3/tercerpiso.png");
            tileView.addDetailLevel(0.125f, "tiles3/125_%col%_%row%.png", "downsamples3/tercerpiso.png");
            SnackbarManager.show(
                    Snackbar.with(getApplicationContext()) // context
                            .text("Tercer Piso") // text to be displayed
                            .color(Color.LTGRAY) // change the background color
                            .actionLabel("Ok")
                            .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                    , this);
        }
        //Carga mapa del cuarto piso
        if(secuencialPos >= 263 && secuencialPos <= 287){
            width = 4214;
            height = 2509;
            tileView.setSize(width, height);
            tileView.addDetailLevel(1f, "tiles4/1000_%col%_%row%.png", "downsamples4/cuartopiso.png");
            tileView.addDetailLevel(0.5f, "tiles4/500_%col%_%row%.png", "downsamples4/cuartopiso.png");
            tileView.addDetailLevel(0.25f, "tiles4/250_%col%_%row%.png", "downsamples4/cuartopiso.png");
            tileView.addDetailLevel(0.125f, "tiles4/125_%col%_%row%.png", "downsamples4/cuartopiso.png");
            SnackbarManager.show(
                    Snackbar.with(getApplicationContext()) // context
                            .text("Cuarto Piso") // text to be displayed
                            .color(Color.LTGRAY) // change the background color
                            .actionLabel("Ok")
                            .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                    , this);
        }

        tileView.setScaleLimits(0, 4);
        tileView.setScale(1);

        markerA = new ImageView(this);
        markerA.setImageResource(R.drawable.marker);
        markerA.setTag("Nice");

        markerB = new ImageView(this);
        markerB.setImageResource(R.drawable.marker);
        markerB.setTag("Nice");

        //Dibujando el marcador
        tileView.post(new Runnable() {
                @Override
                public void run() {
                    tileView.slideToAndCenter(Double.parseDouble(posX[secuencialPos]), Double.parseDouble(posY[secuencialPos]));
                }
            });
        tileView.addMarker(markerA, Double.parseDouble(posX[secuencialPos]), Double.parseDouble(posY[secuencialPos]), -0.5f, -1.0f);
        tileView.addMarker(markerB, Double.parseDouble(posX[secuencialPos]), Double.parseDouble(posY[secuencialPos]), -0.5f, -1.0f);

        btnLocalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNetworkAvailable(h, 2000);
            }
        });

        linLayout.addView(tileView, lpView);
        //setContentView(tileView);
    }

    public static void isNetworkAvailable(final Handler handler, final int timeout) {
        // ask fo message '0' (not connected) or '1' (connected) on 'handler'
        // the answer must be send before before within the 'timeout' (in milliseconds)

        new Thread() {
            private boolean responded = false;
            @Override
            public void run() {
                // set 'responded' to TRUE if is able to connect with google mobile (responds fast)
                new Thread() {
                    @Override
                    public void run() {
                        HttpGet requestForTest = new HttpGet("http://m.google.com");
                        try {
                            new DefaultHttpClient().execute(requestForTest); // can last...
                            responded = true;
                        }
                        catch (Exception e) {
                        }
                    }
                }.start();

                try {
                    int waited = 0;
                    while(!responded && (waited < timeout)) {
                        sleep(100);
                        if(!responded ) {
                            waited += 100;
                        }
                    }
                }
                catch(InterruptedException e) {} // do nothing
                finally {
                    if (!responded) { handler.sendEmptyMessage(0); }
                    else { handler.sendEmptyMessage(1); }
                }
            }
        }.start();
    }

    Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what != 1) { // code if not connected

                Toast.makeText(getApplicationContext(), "Se necesita de una conexión a internet para usar la localización", Toast.LENGTH_SHORT).show();

            } else { // code if connected
                updateUI();
            }
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart fired ..............");
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop fired ..............");
        mGoogleApiClient.disconnect();
        Log.d(TAG, "isConnected ...............: " + mGoogleApiClient.isConnected());
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected - isConnected ...............: " + mGoogleApiClient.isConnected());
        startLocationUpdates();
    }

    protected void startLocationUpdates() {
        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        Log.d(TAG, "Location update started ..............: ");
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "Connection failed: " + connectionResult.toString());
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "Firing onLocationChanged..............................................");
        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        //updateUI();
    }

    private void updateUI() {
        //tileView.defineRelativeBounds(-78.6129109, -0.9344005, -78.6108347, -0.9367265);
        //tileView.defineRelativeBounds(-78.6129108, -0.9346486, -78.6106023, -0.9367264);

        //tileView.defineRelativeBounds(-78.6129109, -0.9346486, -78.6106023, -0.9367265); //Latacunga

        //Latacunga
        left = -78.6129109;
        top = -0.9346486;
        right = -78.6106023;
        bottom = -0.9367265;

        //Quito
        /*left = -78.4907598;
        top = -0.1704534;
        right = -78.4887598;
        bottom = -0.1724534;*/


        tileView.defineRelativeBounds(left, top, right, bottom); //Quito



        Log.d(TAG, "UI update initiated .............");
        if (null != mCurrentLocation) {

            if ((mCurrentLocation.getLongitude() >= left && mCurrentLocation.getLongitude() <= right)
                && (mCurrentLocation.getLatitude() >= bottom && mCurrentLocation.getLatitude() <= top)){

                // Get Location Manager and check for GPS & Network location services
                LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
                if(!lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                        !lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    // Build the alert dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Servicios de localización desactivados");
                    builder.setMessage("Por favor active los servicios de localización (Google y GPS)");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Show location settings when the user acknowledges the alert dialog
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                        }
                    });
                    Dialog alertDialog = builder.create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.show();
                }else{

                    Toast.makeText(getApplicationContext(), "Posición actual", Toast.LENGTH_SHORT).show();
                    tileView.post(new Runnable() {
                        @Override
                        public void run() {
                            tileView.slideToAndCenter(mCurrentLocation.getLongitude(), mCurrentLocation.getLatitude());
                        }
                    });
                    tileView.moveMarker(markerB, mCurrentLocation.getLongitude(), mCurrentLocation.getLatitude(), -0.5f, -1.0f);
                }

            } else {
                Toast.makeText(getApplicationContext(), "No se localiza en la Universidad en este momento", Toast.LENGTH_SHORT).show();
            }



        } else {
            Log.d(TAG, "location is null ...............");
            Toast.makeText(getApplicationContext(), "Localizando. Espere un momento e intente de nuevo", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        Log.d(TAG, "Location update stopped .......................");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
            Log.d(TAG, "Location update resumed .....................");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_busqueda_mapa, menu);
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
}
