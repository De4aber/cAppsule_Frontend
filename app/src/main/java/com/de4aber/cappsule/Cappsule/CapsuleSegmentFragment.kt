import android.app.Activity
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.de4aber.cappsule.AddLocationActivity
import com.de4aber.cappsule.R
import com.de4aber.cappsule.User.LoggedUserViewModel
import com.de4aber.cappsule.Utility.BECapsuleText
import com.de4aber.cappsule.Utility.BEFriend
import com.de4aber.cappsule.Utility.FriendListTemp
import kotlinx.android.synthetic.main.activity_create_capsule.lvFriends
import kotlinx.android.synthetic.main.fragment_capsule_segment.*


/**
 * A simple [Fragment] subclass.
 * Use the [CapsuleSegmentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val TAG= "CapsuleSegmentFragment"
class CapsuleSegmentFragment : Fragment() {

    private val loggedUserViewModel : LoggedUserViewModel by lazy {
        ViewModelProvider(requireActivity()).get(LoggedUserViewModel::class.java)
    }
    var friends = FriendListTemp()
    private lateinit var pictureUri: String
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_capsule_segment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFriendList()
        btnTest_capsuleSegment.setOnClickListener { onClickTest() }
        tpTime_capsuleSegment.setIs24HourView(true)
        btnPhoto_capsuleSegment.setOnClickListener { onClickTakePhoto() }
        btnMap_capsuleSegment.setOnClickListener { onClickOpenMap() }
    }

    private fun onClickOpenMap() {
        val mInt = Intent(requireContext(), AddLocationActivity::class.java)
        mapLauncher.launch(mInt)
    }

    private fun onClickTakePhoto() {
        val cInt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(cInt)
    }

    var mapLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            if(data?.getSerializableExtra("longitude") != null && data.getSerializableExtra("latitude") != null){
                latitude = (data.getSerializableExtra("latitude") as Double)
                longitude = (data.getSerializableExtra("longitude") as Double)
            }
        }
    }

    var cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val bp : Bitmap = data!!.extras!!.get("data") as Bitmap
            imgPhoto_capsuleSegment.setImageBitmap(bp)
        }
    }

    private fun onClickTest() {
        //Set receivers
        var receivers = mutableListOf<BEFriend>(BEFriend("bent"))
        var capsule = BECapsuleText(receivers)
        //Set text
        capsule.message = txtMessage_capsuleSegment.text.toString()
        //Set time
        capsule.time = tpTime_capsuleSegment.currentHour.toString() + ":" + tpTime_capsuleSegment.currentMinute.toString()
        //Set date
        var date = ""
        date = date + dpDate_capsuleSegment.dayOfMonth + "-"
        date = date + (dpDate_capsuleSegment.month + 1) + "-"
        date += dpDate_capsuleSegment.year
        capsule.date = date
        //Set picture
        if(this::pictureUri.isInitialized){
            capsule.pictureUri = pictureUri
        }
        //Set location
        capsule.latitude = latitude
        capsule.longitude = longitude
        //Print capsule
        Log.d(TAG, capsule.toString())
    }

    private fun asListMap(src: MutableList<BEFriend>): List<Map<String, String?>> {
        return src.map{ person -> hashMapOf("name" to person.name) }
    }

    private fun setupFriendList() {
        val adapter = SimpleAdapter(
            requireContext(),
            asListMap(friends.getAll()),
            R.layout.friend_list_unit,
            arrayOf("name"),
            intArrayOf(R.id.name)
        )

        lvFriends.adapter = adapter
    }

    companion object {
        fun newInstance() :CapsuleSegmentFragment{
            return CapsuleSegmentFragment()
        }
    }
}