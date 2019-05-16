package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.DBHelper;
import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Store;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private DBHelper db;
    private List<Store> allStoresList;
    private ListView locationsListView;

    //TODO: private LocationListAdapter locationListAdapter;

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //TODO: inflate listView of Stores database (but only using the longitude and latitude of the store)
//        db = new DBHelper(this);
//        db.importLocationsFromCSV("locations.csv");
//
//        allLocationsList = db.getAllCaffeineLocations();
//        locationsListView = findViewById(R.id.locationsListView);
//        locationListAdapter = new LocationListAdapter(this, R.layout.location_list_item, allLocationsList);
//        locationsListView.setAdapter(locationListAdapter);

        // Load the Map fragment asynchronously
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng currentPosition = new LatLng(33.190802, -117.301805); //specify position in the map

        //Mark our position
        map.addMarker(new MarkerOptions()
                .position(currentPosition)
                .title("Current Location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker)));

        //Move the camera to our position
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(currentPosition)
                .zoom(15.0f)
                .build();

        //Update the camera position
        CameraUpdate cameraUpdate = CameraUpdateFactory
                .newCameraPosition(cameraPosition);

        //Tell map to move to this position
        map.moveCamera(cameraUpdate);

        //TODO: Add all store locations from the database to the map using a for each loop
//        LatLng position;
//        for (Location location : allLocationsList)
//        {
//            position = new LatLng(location.getLatitude(), location.getLongitude());
//            map.addMarker(new MarkerOptions()
//                    .position(position)
//                    .title(location.getName()));
//        }
    }
}
