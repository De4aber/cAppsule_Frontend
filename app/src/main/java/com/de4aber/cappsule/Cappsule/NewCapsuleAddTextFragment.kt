package com.de4aber.cappsule.Cappsule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.de4aber.cappsule.R
import com.de4aber.cappsule.User.LoggedUserViewModel
import kotlinx.android.synthetic.main.fragment_new_capsule_add_text.*

/**
 * A simple [Fragment] subclass.
 * Use the [NewCapsuleAddTextFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewCapsuleAddTextFragment : Fragment() {
    private val loggedUserViewModel : LoggedUserViewModel by lazy {
        ViewModelProvider(requireActivity()).get(LoggedUserViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtMessage_fragNewCapsuleAddText.doOnTextChanged { text, start, before, count ->
            onMessageTyped(text) }
    }

    private fun onMessageTyped(text: CharSequence?) {
        loggedUserViewModel.messageNewCapsule = text.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_capsule_add_text, container, false)
    }

    companion object {

        fun newInstance() =
            NewCapsuleAddTextFragment()
    }
}