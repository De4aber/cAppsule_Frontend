package com.de4aber.cappsule.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.de4aber.cappsule.Cappsule.Capsule
import com.de4aber.cappsule.Friend.FriendDTO
import com.de4aber.cappsule.R
import com.de4aber.cappsule.User.LoggedUserViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [HomeSegmentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val TAG = "HomeSegmentFragment"
class HomeSegmentFragment : Fragment() {

    private var adapter: CapsuleAdapter? = null
    private lateinit var capsuleRecyclerView: RecyclerView

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
        val view = inflater.inflate(R.layout.fragment_home_segment, container, false)
        capsuleRecyclerView = view.findViewById(R.id.recyclerViewCapsules_HomeSegment)
        capsuleRecyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loggedUserViewModel.receiveCapsules().observe(viewLifecycleOwner){
            cap -> updateUI(cap)
        }
    }

    fun updateUI(capsules: List<Capsule>){
        adapter = CapsuleAdapter(capsules)
        capsuleRecyclerView.adapter = adapter
    }

    private inner class CapsuleHolder(view: View)
        : RecyclerView.ViewHolder(view){
        private lateinit var capsule: Capsule
        private val txtUsername: TextView = itemView.findViewById(R.id.txtNewCapsule_ListItemCapsule)


        fun bind(capsule1: Capsule){
            this.capsule = capsule1
            txtUsername.text = this.capsule.senderUsername
        }

    }

    private inner class CapsuleAdapter(var capsules:List<Capsule>) : RecyclerView.Adapter<CapsuleHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CapsuleHolder {
            val view = layoutInflater.inflate(R.layout.list_item_capsule, parent, false)
            return CapsuleHolder(view)
        }

        override fun onBindViewHolder(holder: CapsuleHolder, position: Int) {
            val capsule = capsules[position]
            holder.bind(capsule)
        }

        override fun getItemCount(): Int = capsules.size
    }

    companion object {
        fun newInstance() :HomeSegmentFragment{
            return HomeSegmentFragment()
        }
    }
}