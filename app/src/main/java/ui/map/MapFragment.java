package ui.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import idd.urbanido.R;
import ui.form.FormFragment;

import static androidx.core.content.ContextCompat.checkSelfPermission;

public class MapFragment extends Fragment
    implements MapVP.View, OnMapReadyCallback {

    private SupportMapFragment map;
    private MapPresenter mapPresenter;

    public static String coordinates = null;
    private static final String TAG = "MapActivity";

    private FloatingActionButton fab;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setMVP();

        fab.setOnClickListener((View v) -> {
            mapPresenter.addFragment(FormFragment.newInstance());
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.map);
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        fab = view.findViewById(R.id.fab);

        map = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.googleMap);

        if (map != null) {
            map.getMapAsync( this);
        }
        return view;
    }


    private void convertLocationToAddress(LatLng point) {
        String addressText;
        String errorMessage = "";

        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());

        List<Address> addresses = null;

        try {
            addresses = geocoder.getFromLocation(
                    point.latitude,
                    point.longitude,
                    1
            );
        } catch (IOException ioException) {
            // Network or other I/O issues
            errorMessage = getString(R.string.network_service_error);
            Log.e(TAG, errorMessage, ioException);
        } catch (IllegalArgumentException illegalArgumentException) {
            // Invalid long / lat
            errorMessage = getString(R.string.invalid_long_lat);
            Log.e(TAG, errorMessage + ". " +
                    "Latitude = " + point.latitude +
                    ", Longitude = " +
                    point.longitude, illegalArgumentException);
        }

        // No address was found
        if (addresses == null || addresses.size() == 0) {
            if (errorMessage.isEmpty()) {
                errorMessage = getString(R.string.no_address_found);
                Log.e(TAG, errorMessage);
            }
            addressText = errorMessage;

        } else {
            Address address = addresses.get(0);
            ArrayList<String> addressFragments = new ArrayList<>();

            // Fetch the address lines, join them, and return to thread
            for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                addressFragments.add(address.getAddressLine(i));
            }
            Log.i(TAG, getString(R.string.address_found));
            addressText =
                    TextUtils.join(System.getProperty("line.separator"),
                            addressFragments);
        }
        FormFragment.address = addressText;
    }


    @Override
    public void setFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.main_container, fragment).commit();
    }

    @Override
    public void setMVP() {
        mapPresenter = new MapPresenter(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng bryansk = new LatLng(53.280458, 34.2275525);
        googleMap.addMarker(new MarkerOptions().position(bryansk)
                .title("Marker in Bryansk"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(bryansk));
        //mMap.animateCamera(CameraUpdate);

        if(googleMap != null) {
            if (checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                /*|| checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)*/ {

                googleMap.setMyLocationEnabled(true);
            }
        }

        googleMap.setMinZoomPreference(14);


        googleMap.setOnMapClickListener((LatLng point) -> {
            googleMap.clear();
            googleMap.addMarker(new MarkerOptions().position(point));

            FormFragment.koord = point.latitude + ";" + point.longitude;
            convertLocationToAddress(point);
        });


            /*map.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
                @Override
                public void onCameraMoveStarted(int reason) {
                    if (reason == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE) {
                    }
                }
            });

            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    Log.d(TAG, "onMarkerClick");
                    //startMap();
                    return true;
                }
            });*/
    }
}
