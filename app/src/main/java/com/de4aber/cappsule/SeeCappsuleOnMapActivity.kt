package com.de4aber.cappsule

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.de4aber.cappsule.Utility.BECapsuleText
import com.de4aber.cappsule.Utility.BEFriend
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_see_cappsule_on_map.*
import kotlin.math.log

class SeeCappsuleOnMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private val TAG = "xyz"

    private lateinit var mMap: GoogleMap

    private lateinit var client: FusedLocationProviderClient
    private var zoomLevel: Float = 18.0f

    private var currentLatitude: Double = 0.0
    private var currentLongitude: Double = 0.0

    private var wantZoom: Boolean = false

    private var liste = listOf<BEFriend>(BEFriend("bob"), BEFriend("bent"))

    private lateinit var selectedMarker: Marker

    private lateinit var currentLocationMarker: Marker

    val allCappsules = listOf<BECapsuleText>(
        BECapsuleText(liste),
        BECapsuleText(liste)
    )

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
        btnOpenCapsuleFromMap.setOnClickListener { onClickOpen() }

        allCappsules[0].latitude = 55.471793424146234
        allCappsules[0].longitude = 8.451112645531
        allCappsules[0].cappsuleID = 1
        allCappsules[1].latitude = 55.4677613612147
        allCappsules[1].longitude = 8.453494446915784
        allCappsules[1].cappsuleID = 2
    }

    private fun onClickOpen() {
        if(!this::selectedMarker.isInitialized){
            Toast.makeText(this, "Please select a marker to open it!", Toast.LENGTH_SHORT).show()
            return
        }
        if(selectedMarker.equals(currentLocationMarker)){
            Toast.makeText(this, "You cannot open your own location!", Toast.LENGTH_SHORT).show()
            return
        }
        Log.d(TAG, "cool, du gjorde det")
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
                currentLocationMarker = mMap.addMarker(MarkerOptions().position(coords).title("Your location"))
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

        setupOnClickMarkers()
        setupAllCapsules()
    }

    private fun setupOnClickMarkers(){
        mMap.setOnMarkerClickListener { marker ->
            selectedMarker = marker
            Log.d(TAG,selectedMarker.toString())
            false
        }
    }

    private fun setupAllCapsules(){
        var i = 1
        allCappsules.forEach {
            var loc = LatLng(it.latitude,it.longitude)
            var marker = mMap.addMarker(MarkerOptions().position(loc).title("${i}"))
            marker.tag = it
            i++
            hack(marker)
        }
    }
    private fun hack(marker: Marker){
        currentLocationMarker = marker
    }
}