import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.de4aber.cappsule.R

/**
 * A simple [Fragment] subclass.
 * Use the [CapsuleSegmentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CapsuleSegmentFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_capsule_segment, container, false)
    }

    companion object {
        fun newInstance() :CapsuleSegmentFragment{
            return CapsuleSegmentFragment()
        }
    }
}