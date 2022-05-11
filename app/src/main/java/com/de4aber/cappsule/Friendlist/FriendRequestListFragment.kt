package com.de4aber.cappsule.Friendlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.de4aber.cappsule.Friend.FriendRequestReceiverDTO
import com.de4aber.cappsule.R
import com.de4aber.cappsule.User.LoggedUserViewModel

class FriendRequestListFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_friend_request_list, container, false)
        friendRecyclerView=
            view.findViewById(R.id.fragFriendRequestList_RecyclerView) as RecyclerView
        friendRecyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loggedUserViewModel.getFriendRequests()
            .observe(viewLifecycleOwner) { fr -> updateUI(fr) }
    }

    fun updateUI(friends: List<FriendRequestReceiverDTO>){
        adapter = FriendAdapter(friends)
        friendRecyclerView.adapter = adapter
    }

    private inner class FriendHolder(view: View)
        : RecyclerView.ViewHolder(view){
        private lateinit var friend: FriendRequestReceiverDTO
        private val txtUsername: TextView = itemView.findViewById(R.id.txtUsername_ListItemFriendRequest)
        private val btnAcceptFriendRequest: Button = itemView.findViewById(R.id.btnAccept_friendRequest)


        fun bind(friend: FriendRequestReceiverDTO){
            this.friend = friend
            txtUsername.text = this.friend.UserRequesting
            btnAcceptFriendRequest.setOnClickListener { onClickAcceptFriend()}
        }

        private fun onClickAcceptFriend() {

            loggedUserViewModel.acceptFriendRequest(
                friend.id
            ).observe(viewLifecycleOwner) {
                btnAcceptFriendRequest.text = "Accepted"
                btnAcceptFriendRequest.isEnabled = false
            }

        }

    }

    private inner class FriendAdapter(var friends: List<FriendRequestReceiverDTO>) : RecyclerView.Adapter<FriendHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendHolder {
            val view = layoutInflater.inflate(R.layout.list_item_friendrequest, parent, false)
            return FriendHolder(view)
        }

        override fun onBindViewHolder(holder: FriendHolder, position: Int) {
            val friend = friends[position]
            holder.bind(friend)
        }

        override fun getItemCount(): Int = friends.size
    }

    companion object {
        fun newInstance(): FriendRequestListFragment {
                    return FriendRequestListFragment()
            }
    }
}