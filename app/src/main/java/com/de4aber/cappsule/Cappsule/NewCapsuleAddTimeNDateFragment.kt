package com.de4aber.cappsule.Cappsule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.de4aber.cappsule.R
import com.de4aber.cappsule.User.LoggedUserViewModel
import kotlinx.android.synthetic.main.fragment_new_capsule_add_time_n_date.*


/**
 * A simple [Fragment] subclass.
 * Use the [NewCapsuleAddTimeNDateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewCapsuleAddTimeNDateFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_new_capsule_add_time_n_date, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tpTime_capsuleSegment.setIs24HourView(true)
        tpTime_capsuleSegment.setOnTimeChangedListener { timePicker, hour, minute ->  onTimeChanged(hour, minute)}

        dpDate_capsuleSegment.setOnDateChangedListener { datePicker, year, month, day -> onDateChanged(year, (month+1), day)  }
    }

    private fun onDateChanged(year: Int, month: Int, day: Int) {
        loggedUserViewModel.dateNewCapsule = "$year/$month/$day"
    }

    private fun onTimeChanged(hour: Int, minute: Int) {
        loggedUserViewModel.timeNewCapsule = "$hour:$minute"

    }

    companion object {

        fun newInstance() =
            NewCapsuleAddTimeNDateFragment()
    }
}