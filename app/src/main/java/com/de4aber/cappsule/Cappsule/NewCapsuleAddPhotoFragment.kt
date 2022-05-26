package com.de4aber.cappsule.Cappsule

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.de4aber.cappsule.R
import com.de4aber.cappsule.User.LoggedUserViewModel
import kotlinx.android.synthetic.main.fragment_new_capsule_add_photo.*


/**
 * A simple [Fragment] subclass.
 * Use the [NewCapsuleAddPhotoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewCapsuleAddPhotoFragment : Fragment() {

    private val PERMISSION_REQUEST_CODE = 1

    private val loggedUserViewModel : LoggedUserViewModel by lazy {
        ViewModelProvider(requireActivity()).get(LoggedUserViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkCameraPermissions()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_capsule_add_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgPhoto_fragNewCapsuleAddPhoto.setOnClickListener { onClickTakePhoto() }
    }
    private fun onClickTakePhoto() {
        val cInt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(cInt)
    }

    var cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val bp : Bitmap = data!!.extras!!.get("data") as Bitmap
            loggedUserViewModel.photoNewCapsule = bp
            imgPhoto_fragNewCapsuleAddPhoto.setImageBitmap(bp)
        }
    }

    private fun checkCameraPermissions(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return
        val permissions = mutableListOf<String>()
        if ( ! isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE) ) permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if ( ! isGranted(Manifest.permission.CAMERA) ) permissions.add(Manifest.permission.CAMERA)
        if (permissions.size > 0)
            ActivityCompat.requestPermissions(requireActivity(), permissions.toTypedArray(), PERMISSION_REQUEST_CODE)
    }

    private fun isGranted(permission: String): Boolean =
        ActivityCompat.checkSelfPermission(requireActivity(), permission) == PackageManager.PERMISSION_GRANTED

    companion object {
        fun newInstance() =
            NewCapsuleAddPhotoFragment()
    }
}