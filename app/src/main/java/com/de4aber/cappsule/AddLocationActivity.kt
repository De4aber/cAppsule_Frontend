package com.de4aber.cappsule

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_add_location3.*

class AddLocationActivity : AppCompatActivity(), OnMapReadyCallback {

    private val TAG = "xyz"

    private val EASV = LatLng(55.488230, 8.446936)
    private val ROUND = LatLng(55.473939, 8.435959)

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_location3)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        btnSaveLocation.setOnClickListener { Save() }
        btnLocationBack.setOnClickListener { Back() }

    }



    private fun Back() {
        finish()
    }

    private fun Save() {
        TODO("Not yet implemented")
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val easvOptions = MarkerOptions().position(EASV).title("EASV")

        val roundAboutOptions = MarkerOptions().position(ROUND).title("Round-about")
        val easvMarker = mMap.addMarker(easvOptions)
        mMap.addMarker(roundAboutOptions)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(EASV))
        mMap.setOnMarkerClickListener(object : GoogleMap.OnMarkerClickListener {
            override fun onMarkerClick(marker: Marker): Boolean {
                if (marker.equals(easvMarker))
                    Log.d(TAG, "EASV is clicked...")
                return false
            }
        })

        setupZoomlevels()
    }

    private fun setupZoomlevels() {
        spinnerZoomLevel.adapter =
            ArrayAdapter.createFromResource(
                this,
                R.array.zoomlevels,
                android.R.layout.simple_spinner_dropdown_item
            )
    }
}