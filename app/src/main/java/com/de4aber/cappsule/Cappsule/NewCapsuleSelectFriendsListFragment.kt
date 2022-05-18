package com.de4aber.cappsule.Cappsule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.de4aber.cappsule.R



/**
 * A simple [Fragment] subclass.
 * Use the [NewCapsuleSelectFriendsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewCapsuleSelectFriendsListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_capsule_select_friends_list, container, false)
    }

    companion object {

        fun newInstance() =
            NewCapsuleSelectFriendsListFragment()

    }
}