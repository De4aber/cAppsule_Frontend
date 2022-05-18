package com.de4aber.cappsule.Cappsule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.de4aber.cappsule.Friend.FriendDTO
import com.de4aber.cappsule.Friendlist.FriendRequestListFragment
import com.de4aber.cappsule.R
import com.de4aber.cappsule.User.LoggedUserViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [NewCapsuleToFriendListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewCapsuleToFriendListFragment : Fragment() {

    private var adapter: FriendAdapter? = null
    private lateinit var friendRecyclerView: RecyclerView

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
        val view = inflater.inflate(R.layout.fragment_new_capsule_to_friend_list, container, false)
        friendRecyclerView=
            view.findViewById(R.id.recyclerviewFriends_newCapsuleToFriendsList) as RecyclerView
        friendRecyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loggedUserViewModel.getFriends().observe(viewLifecycleOwner) { fr ->
            updateUI(fr)
        }
    }

    fun updateUI(friends: List<FriendDTO>){
        adapter = FriendAdapter(friends)
        friendRecyclerView.adapter = adapter
    }



    private inner class FriendHolder(view: View)
        : RecyclerView.ViewHolder(view){
        private lateinit var friend: FriendDTO
        private val txtUsername: TextView = itemView.findViewById(R.id.txtUsername_listItemNewCapsuleToFriend)


        fun bind(friend: FriendDTO){
            this.friend = friend
            txtUsername.text = this.friend.username
        }

    }

    private inner class FriendAdapter(var friends:List<FriendDTO>) : RecyclerView.Adapter<FriendHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendHolder {
            val view = layoutInflater.inflate(R.layout.list_item_new_capsule_to_friend, parent, false)
            return FriendHolder(view)
        }

        override fun onBindViewHolder(holder: FriendHolder, position: Int) {
            val friend = friends[position]
            holder.bind(friend)
        }

        override fun getItemCount(): Int = friends.size
    }

    companion object {

        fun newInstance() =
            NewCapsuleToFriendListFragment()
    }
}