package com.de4aber.cappsule.Friendlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.de4aber.cappsule.Friend.FriendDTO
import com.de4aber.cappsule.R
import com.de4aber.cappsule.User.LoggedUserViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [FriendRequestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FriendRequestFragment : Fragment() {



    private val loggedUserViewModel : LoggedUserViewModel by lazy {
        ViewModelProvider(requireActivity()).get(LoggedUserViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friend_request, container, false)
    }



    companion object {
        fun newInstance(): FriendRequestFragment {
            return FriendRequestFragment()
        }
    }
}