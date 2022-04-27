package com.de4aber.cappsule.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.de4aber.cappsule.R
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    val sampleRepo = SampleRepo()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        sampleRepo.getAll(object : ICallback{
            override fun onUsersReady(users: List<BEUser>) {
                txtUsername.text = users[0].name
            }
        })
    }
}