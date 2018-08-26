package me.brunoeleodoro.techcycle.main;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import me.brunoeleodoro.techcycle.R;
import me.brunoeleodoro.techcycle.models.Directions;
import me.brunoeleodoro.techcycle.models.Route;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Path;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, MainView {

    private GoogleMap mMap;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        presenter = new MainPresenterImpl();
        presenter.setView(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        this, R.raw.estilo));
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        //manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, new Listener(googleMap));
        manager.requestSingleUpdate(LocationManager.GPS_PROVIDER, new Listener(googleMap), null);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.i("script map", "latlng="+latLng.latitude+","+latLng.longitude);
            }
        });
        //LatLng latLng = new LatLng(-2333,-44444);
        //mMap.addPolyline(new PolylineOptions().addAll())
    }

    @Override
    public void error(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setListDirections(List<Directions> direcoes) {

        int i = 0;
        mMap.clear();
        while(i < direcoes.size())
        {
            Directions direction = direcoes.get(i);
            List<LatLng> lats_lngs = direction.getLat_lngs();
            Log.i("script", "lats_lngs size="+lats_lngs.size());
            PolylineOptions polylineOptions = new PolylineOptions();
            int k = 0;
            while(k < lats_lngs.size())
            {
                Log.i("script", "lat="+lats_lngs.get(k).latitude + ", lng="+lats_lngs.get(k).longitude);
                polylineOptions.add(lats_lngs.get(k));
                polylineOptions.color(Color.parseColor(direction.getCor()));
                polylineOptions.width(5);
                polylineOptions.geodesic(true);
                k++;
            }

            mMap.addPolyline(polylineOptions);

            i++;
        }
        Directions direction = direcoes.get(direcoes.size() - 1);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(direction.getLat_lngs().get(direction.getLat_lngs().size() - 1).latitude, direction.getLat_lngs().get(direction.getLat_lngs().size() - 1).longitude), 14.0f ));

        Toast.makeText(this, "Finished", Toast.LENGTH_SHORT).show();
    }

    class Listener implements LocationListener {

        GoogleMap googleMap;

        public Listener(GoogleMap googleMap) {
            this.googleMap = googleMap;
        }

        @Override
        public void onLocationChanged(Location location) {
            googleMap.clear();
            googleMap.addMarker(new MarkerOptions().position(
                    new LatLng(location.getLatitude(), location.getLongitude())));
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()), 12f));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 1.0f ));

            LatLng pointA = new LatLng(location.getLatitude(), location.getLongitude());
            //LatLng pointA = new LatLng(-23.61617455261591,-46.72864213585854);
            LatLng pointB = new LatLng(-23.569681, -46.658331);

            Route route = new Route(pointA, pointB);
            presenter.findRoute(route);
            Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }
}
