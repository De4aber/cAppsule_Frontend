package com.de4aber.cappsule.Friendlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.de4aber.cappsule.R
import kotlinx.android.synthetic.main.fragment_add_friend.*

/**
 * A simple [Fragment] subclass.
 * Use the [AddFriendFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFriendFragment : Fragment() {

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

        btnBack_addFriend.setOnClickListener { onClickBack() }
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