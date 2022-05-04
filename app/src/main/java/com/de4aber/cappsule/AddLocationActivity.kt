package com.de4aber.cappsule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_add_location.*

class AddLocationActivity : AppCompatActivity(), OnMapReadyCallback {

    private val TAG = "xyz"

    private val EASV = LatLng(55.488230, 8.446936)
    private val BAKER = LatLng(55.485386, 8.451585)
    private val ROUND = LatLng(55.473939, 8.435959)

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_location)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    fun onClickSave() {
        TODO("Not yet implemented")
    }

    fun onClickBack() {
        TODO("Not yet implemented")
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val easvOptions = MarkerOptions().position(EASV).title("EASV")
        val bakerOptions = MarkerOptions().position(BAKER).title("Baker").icon(
            BitmapDescriptorFactory.fromResource(
                R.drawable.ic_logo
            )
        )
        val roundAboutOptions = MarkerOptions().position(ROUND).title("Round-about")
        val easvMarker = mMap.addMarker(easvOptions)
        mMap.addMarker(bakerOptions)
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