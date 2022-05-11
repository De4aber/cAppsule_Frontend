package com.de4aber.cappsule

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_add_location3.*
import kotlinx.android.synthetic.main.activity_see_cappsule_on_map.*

class SeeCappsuleOnMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private val TAG = "xyz"

    private lateinit var mMap: GoogleMap

    private lateinit var client: FusedLocationProviderClient
    private var zoomLevel: Float = 18.0f

    private var currentLatitude: Double = 0.0
    private var currentLongitude: Double = 0.0

    private var wantZoom: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_cappsule_on_map)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //Initialize fused location
        client = LocationServices.getFusedLocationProviderClient(this)

        btnMapZoom.setOnClickListener { onClickZoom() }
        btnMapBack.setOnClickListener { finish() }
    }

    private fun ZoomOnCurrentLocation(coords: LatLng) {
        val viewPoint = CameraUpdateFactory.newLatLngZoom(coords, zoomLevel)

        // zoomlevel 0..21, where 0 is the world and 21 is single street
        Log.d(TAG, "Will zoom to easv to level $zoomLevel")
        mMap.animateCamera(viewPoint)
    }

    private fun getCurrentLocation(){
        val task = client.lastLocation

        if(ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
            return
        }
        task.addOnSuccessListener {
            if(it != null){
                currentLatitude = it.latitude
                currentLongitude = it.longitude
                var coords = LatLng(currentLatitude, currentLongitude)
                mMap.addMarker(MarkerOptions().position(coords).title("Your location"))
                if(wantZoom){
                    ZoomOnCurrentLocation(coords)
                    wantZoom = false
                }
            }
        }
    }

    private fun onClickZoom(){
        wantZoom = true
        getCurrentLocation()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

    }
}