package com.de4aber.cappsule.Home

import CapsuleSegmentFragment
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.de4aber.cappsule.R
import com.de4aber.cappsule.User.LoggedUserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_open_cappsule.*

private const val CAPSULE_ID = "capsuleID"

/**
 * A simple [Fragment] subclass.
 * Use the [OpenCapsuleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val TAG = "OpenCapsuleFragment"
class OpenCapsuleFragment : Fragment() {

    private val loggedUserViewModel : LoggedUserViewModel by lazy {
        ViewModelProvider(requireActivity()).get(LoggedUserViewModel::class.java)
    }
    private var capsuleId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            capsuleId = it.getInt(CAPSULE_ID, -1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_open_cappsule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_back_fragOpenCapsule.setOnClickListener { onClickBack()}
        if(capsuleId==-1){
            Toast.makeText(requireContext(), "no capsule loaded", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "no capsule Id")
            txt_username_fragOpenCapsule.text = "ERROR"
            txt_cappsuleText_fragOpenCapsule.text = "ERROR"
            btn_reply_fragOpenCapsule.isEnabled = false

        }
        else{
            loggedUserViewModel.getCapsuleById(capsuleId).observe(viewLifecycleOwner) { c->
                txt_username_fragOpenCapsule.text = c.senderUsername
                txt_cappsuleText_fragOpenCapsule.text = c.message
                btn_reply_fragOpenCapsule.setOnClickListener { onClickReply() }
            }
        }
    }

    private fun onClickReply() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragShowing, CapsuleSegmentFragment.newInstance())
            .commit()
    }

    private fun onClickBack() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragShowing, HomeSegmentFragment.newInstance())
            .commit()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param capsuleId Parameter 1.
         * @return A new instance of fragment OpenCapsuleFragment.
         */
        @JvmStatic
        fun newInstance(capsuleId: Int) =
            OpenCapsuleFragment().apply {
                arguments = Bundle().apply {
                    putInt(CAPSULE_ID, capsuleId)
                }
            }
    }
}