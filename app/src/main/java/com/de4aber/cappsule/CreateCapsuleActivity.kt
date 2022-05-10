package com.de4aber.cappsule

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SimpleAdapter
import androidx.activity.result.contract.ActivityResultContracts
import com.de4aber.cappsule.Utility.AddLocationFragmentThing
import com.de4aber.cappsule.Utility.BECapsuleText
import com.de4aber.cappsule.Utility.BEFriend
import com.de4aber.cappsule.Utility.FriendListTemp
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_create_capsule.*

class CreateCapsuleActivity : AppCompatActivity() {
    var friends = FriendListTemp()
    private val TAG = "xyz"
    private lateinit var pictureUri: String
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_capsule)
        setupFriendList()
        createTest.setOnClickListener { onClickTest() }
        timePickerTime.setIs24HourView(true)
        btnOpenPictureMode.setOnClickListener { onClickTakePhoto() }
        btnOpenMapToSelectLocation.setOnClickListener { onClickOpenMap() }
    }

    private fun onClickOpenMap() {
        val intent = Intent(this, AddLocationActivity::class.java)
        getResult.launch(intent)
    }

    private fun onClickTakePhoto() {
        val intent = Intent(this, TakePhotoActivity::class.java)
        getResult.launch(intent)
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                if(it.data?.getSerializableExtra("picture") != null){
                    pictureUri = (it.data?.getSerializableExtra("picture") as String?).toString()
                    val uri: Uri = Uri.parse(pictureUri)
                    imgPhoto.setImageURI(uri)
                }
                if(it.data?.getSerializableExtra("longitude") != null && it.data?.getSerializableExtra("latitude") != null){
                    latitude = (it.data?.getSerializableExtra("latitude") as Double)
                    longitude = (it.data?.getSerializableExtra("longitude") as Double)
                    Log.d(TAG, "${latitude}.${longitude} VIRKER DETTT!?!?!?")
                }
            }
        }

    private fun onClickTest() {
        var recievers = mutableListOf<BEFriend>(BEFriend("bent"))
        var capsule = BECapsuleText(recievers)
        capsule.time = timePickerTime.currentHour.toString() + ":" + timePickerTime.currentMinute.toString()
        var date = ""
        date = date + datePickerDate.dayOfMonth + "-"
        date = date + (datePickerDate.month + 1) + "-"
        date += datePickerDate.year
        capsule.date = date
        if(this::pictureUri.isInitialized){
            capsule.pictureUri = pictureUri
        }
        capsule.latitude = latitude
        capsule.longitude = longitude
        Log.d(TAG, "${capsule.latitude}.${capsule.longitude} HER VIRKER DET IKKE TROR JEGGG!")
        Log.d(TAG, capsule.toString())
    }

    private fun asListMap(src: MutableList<BEFriend>): List<Map<String, String?>> {
        return src.map{ person -> hashMapOf("name" to person.name) }
    }

    private fun setupFriendList() {
        val adapter = SimpleAdapter(
            this,
            asListMap(friends.getAll()),
            R.layout.friend_list_unit,
            arrayOf("name"),
            intArrayOf(R.id.name)
        )

        lvFriends.adapter = adapter
    }
}