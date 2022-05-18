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



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_capsule_segment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnTest_capsuleSegment.setOnClickListener { onClickTest() }

    }

    private fun onClickTest() {
        //Set receivers
        var capsule = loggedUserViewModel.newCapsule()

        //Print capsule
        Log.d(TAG, capsule.toString())
    }


    companion object {
        fun newInstance() :CapsuleSegmentFragment{
            return CapsuleSegmentFragment()
        }
    }
}