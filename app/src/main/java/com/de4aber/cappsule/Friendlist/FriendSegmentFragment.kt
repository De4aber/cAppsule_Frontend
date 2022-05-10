package com.de4aber.cappsule.Friendlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.de4aber.cappsule.R
import com.de4aber.cappsule.User.LoggedUserViewModel


private const val TAG = "FriendSegmentFragment"


/**
 * A simple [Fragment] subclass.
 * Use the [FriendSegmentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FriendSegmentFragment : Fragment() {



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
        return inflater.inflate(R.layout.fragment_friend_segment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentFriendListFragment = childFragmentManager.findFragmentById(R.id.fragFriendList)
        if (currentFriendListFragment == null){
            childFragmentManager
                .beginTransaction()
                .add(R.id.fragFriendList, FriendListFragment.newInstance())
                .commit()
        }
    }

    companion object {
        fun newInstance(): FriendSegmentFragment {
            return FriendSegmentFragment()
        }
    }
}