package com.borka.openstreetmap;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.infowindow.InfoWindow;

import java.util.ArrayList;


public class OsmActivity extends Activity implements LocationListener {

    private LocationManager mLocMgr;
    ArrayList<OverlayItem> anotherOverlayItemArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osm);

        MapView map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);

        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(18);
        GeoPoint startPoint = new GeoPoint(32.79221, 35.53124);

        // mapController.setCenter(startPoint);
        map.getController().animateTo(startPoint);
        Marker marker = new Marker(map);
        marker.setPosition(new GeoPoint(32.79221, 35.53124));
        marker.setIcon(getResources().getDrawable(R.mipmap.ic_launcher));
        marker.setAnchor(Marker.ANCHOR_CENTER, 1.0f);
        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble, map);
        marker.setInfoWindow(infoWindow);
        map.getOverlays().add(marker);
        marker.setTitle("Title of the marker");

        mLocMgr = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 100, this);
        anotherOverlayItemArray = new ArrayList<OverlayItem>();
        anotherOverlayItemArray.add(new OverlayItem(
                "IL", "TIB", new GeoPoint(32.79221, 35.53124)));

        ItemizedIconOverlay<OverlayItem> anotherItemizedIconOverlay
                = new ItemizedIconOverlay<OverlayItem>(
                this, anotherOverlayItemArray, null);
        //  map.getOverlays().add(anotherItemizedIconOverlay);
        map.getController().animateTo(startPoint);
    }

    @Override
    public void onLocationChanged(Location location) {    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {    }

    @Override
    public void onProviderEnabled(String s) {    }

    @Override
    public void onProviderDisabled(String s) {    }

    private class MyInfoWindow extends InfoWindow {
        public MyInfoWindow(int layoutResId, MapView mapView) {
            super(layoutResId, mapView);
        }
        @Override
        public void onOpen(Object o) {
            LinearLayout layout = (LinearLayout) mView.findViewById(R.id.map);
            TextView txtTitle = (TextView) mView.findViewById(R.id.bubble_title);
            ImageView iv=(ImageView)mView.findViewById(R.id.bubble_image);
            iv.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
            iv.setVisibility(View.VISIBLE);
            txtTitle.setText("Title of my marker");

        }

        public void onClose() {
        }
    }
}
