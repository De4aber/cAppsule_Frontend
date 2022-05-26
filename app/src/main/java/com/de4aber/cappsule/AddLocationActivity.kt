package com.de4aber.cappsule

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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

class AddLocationActivity : AppCompatActivity(), OnMapReadyCallback {

    private val TAG = "xyz"

    private var selectedLocation = LatLng(0.0, 0.0)
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    private lateinit var mMap: GoogleMap

    private lateinit var client: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_location3)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        btnSaveLocation.setOnClickListener { Save() }
        btnLocationBack.setOnClickListener { Back() }
        btnLocationZoomCurrent.setOnClickListener { getCurrentLocation() }

        //Initialize fused location
        client = LocationServices.getFusedLocationProviderClient(this)

    }

    private fun ZoomOnCurrentLocation(coords: LatLng) {

        val viewPoint = CameraUpdateFactory.newLatLngZoom(coords, 18f)

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

                var coords = LatLng(it.latitude, it.longitude)
                ZoomOnCurrentLocation(coords)
            }
        }
    }

    private fun Back() {
        finish()
    }

    private fun Save() {
        val data = Intent().apply {
            putExtra("latitude", latitude)
            putExtra("longitude", longitude)
        }
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        createPoint()
    }

    private fun createPoint(){
        mMap.setOnMapClickListener {
            mMap.clear()
            selectedLocation = it
            longitude = selectedLocation.longitude
            latitude = selectedLocation.latitude
            mMap.addMarker(MarkerOptions().position(selectedLocation).title("Selected location"))
            Log.d(TAG, selectedLocation.toString())
        }
    }
}