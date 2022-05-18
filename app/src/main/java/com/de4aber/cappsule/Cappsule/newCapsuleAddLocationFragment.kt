package com.de4aber.cappsule.Cappsule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.de4aber.cappsule.AddLocationActivity
import com.de4aber.cappsule.R
import com.de4aber.cappsule.User.LoggedUserViewModel
import kotlinx.android.synthetic.main.fragment_new_capsule_add_location.*


/**
 * A simple [Fragment] subclass.
 * Use the [NewCapsuleAddLocationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewCapsuleAddLocationFragment : Fragment() {

    private val loggedUserViewModel : LoggedUserViewModel by lazy {
        ViewModelProvider(requireActivity()).get(LoggedUserViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_capsule_add_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnMap_capsuleSegment.setOnClickListener { onClickOpenMap() }
    }
    private fun onClickOpenMap() {
        val mInt = Intent(requireContext(), AddLocationActivity::class.java)
        mapLauncher.launch(mInt)
    }

    var mapLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            if(data?.getSerializableExtra("longitude") != null && data.getSerializableExtra("latitude") != null){
                loggedUserViewModel.latitudeNewCapsule = (data.getSerializableExtra("latitude") as Double)
                loggedUserViewModel.longitudeNewCapsule = (data.getSerializableExtra("longitude") as Double)
            }
        }
    }

    companion object {
        fun newInstance() =
            NewCapsuleAddLocationFragment()
    }
}