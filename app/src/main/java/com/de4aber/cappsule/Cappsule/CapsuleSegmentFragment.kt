import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import com.de4aber.cappsule.Cappsule.NewCapsuleAddLocationFragment
import com.de4aber.cappsule.Cappsule.NewCapsuleAddPhotoFragment
import com.de4aber.cappsule.Cappsule.NewCapsuleAddTextFragment
import com.de4aber.cappsule.Cappsule.NewCapsuleAddTimeNDateFragment
import com.de4aber.cappsule.R
import com.de4aber.cappsule.User.LoggedUserViewModel
import com.de4aber.cappsule.Utility.FriendListTemp
import kotlinx.android.synthetic.main.fragment_capsule_segment.*


/**
 * A simple [Fragment] subclass.
 * Use the [CapsuleSegmentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val TAG= "CapsuleSegmentFragment"
class CapsuleSegmentFragment : Fragment() {

    lateinit var exampleFragTextNPhoto: FragmentContainerView
    lateinit var exampleLocationNTime: FragmentContainerView

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
        val view = inflater.inflate(R.layout.fragment_capsule_segment, container, false)
        exampleFragTextNPhoto = view.findViewById(R.id.fragExampleText_newCapsule)
        exampleLocationNTime = view.findViewById(R.id.fragExampleTime_newCapsule)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnTest_capsuleSegment.setOnClickListener { onClickTest() }
        switchPictureNText_newCapsuleSegment.setOnCheckedChangeListener { compoundButton, b -> onSwitchedPicture(b) }
        switchTimeNPlace_newCapsuleSegment.setOnCheckedChangeListener { compoundButton, b -> onSwitchedLocation(b) }

    }

    private fun onSwitchedLocation(b: Boolean) {
        exampleLocationNTime.isGone =true
        loggedUserViewModel.isTimeNewCapsule = !b
        if(b){
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragtimeNPlace_newCapsule, NewCapsuleAddLocationFragment.newInstance())
                .commit()
        }
        else{
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragtimeNPlace_newCapsule, NewCapsuleAddTimeNDateFragment.newInstance())
                .commit()
        }
    }

    private fun onSwitchedPicture(b: Boolean) {
        exampleFragTextNPhoto.isGone =true
        loggedUserViewModel.isTextNewCapsule = !b
        if(b){
            btnTest_capsuleSegment.isEnabled = false
            Toast.makeText(context,"Sending photos is not implemented", Toast.LENGTH_SHORT).show()

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragTextNPicture_newCapsule, NewCapsuleAddPhotoFragment.newInstance())
                .commit()

        }
        else{
            btnTest_capsuleSegment.isEnabled = true
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragTextNPicture_newCapsule, NewCapsuleAddTextFragment.newInstance())
                .commit()
        }

    }

    private fun onClickTest() {
        //Set receivers
        val sendingCapsules = loggedUserViewModel.newCapsule(requireContext())

        for (cap in sendingCapsules){
            cap.observe(viewLifecycleOwner){c->
                //Print capsule
                Log.d(TAG, c.toString())
            }
        }

    }


    companion object {
        fun newInstance() :CapsuleSegmentFragment{
            return CapsuleSegmentFragment()
        }
    }
}