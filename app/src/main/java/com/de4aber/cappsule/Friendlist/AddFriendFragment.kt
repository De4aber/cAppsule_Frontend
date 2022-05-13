package com.de4aber.cappsule.Friendlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.de4aber.cappsule.R
import com.de4aber.cappsule.User.LoggedUserViewModel
import kotlinx.android.synthetic.main.fragment_add_friend.*


/**
 * A simple [Fragment] subclass.
 * Use the [AddFriendFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val TAG = "AddFriendFragment"
class AddFriendFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_add_friend, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(R.id.fragAddFriendList, FriendRequestListFragment.newInstance())
            .commit()

        btnBack_addFriend.setOnClickListener { onClickBack() }
        txtSearch_AddFriend.doOnTextChanged { text, start, before, count ->
            onSearchUsed(text) }
    }

    private fun onSearchUsed(text: CharSequence?) {
        if (text != null) {
            if(text.length >=3){

                loggedUserViewModel.searchwordUser = text.toString()
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragAddFriendList, UserSearchListFragment.newInstance(text.toString()))
                    .commit()
            }
            else if(text.isEmpty()){
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragAddFriendList, FriendRequestListFragment.newInstance())
                    .commit()
            }
        }

    }

    private fun onClickBack() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragShowing, FriendSegmentFragment.newInstance())
            .commit()
    }

    companion object {
        fun newInstance(): AddFriendFragment {
            return AddFriendFragment()
        }

    }
}