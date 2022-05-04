package com.de4aber.cappsule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_create_capsule.*

class CreateCapsuleActivity : AppCompatActivity() {
    var friends = FriendListTemp()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_capsule)
        setupFriendList()
        createTest.setOnClickListener { onClickTest() }
        timePickerTime.setIs24HourView(true)
        btnOpenPictureMode.setOnClickListener { onClickTakePhoto() }
    }

    private fun onClickTakePhoto() {
        val intent = Intent(this, TakePhotoActivity::class.java)
        startActivity(intent);
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
        Log.d("TAG", capsule.toString())
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