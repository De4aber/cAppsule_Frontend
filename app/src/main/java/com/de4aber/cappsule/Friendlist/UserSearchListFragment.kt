package com.de4aber.cappsule.Friendlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.de4aber.cappsule.R
import com.de4aber.cappsule.User.LoggedUserViewModel
import com.de4aber.cappsule.User.SearchForUserIsFriendDTO
import com.de4aber.cappsule.User.UserLimitedInfoDTO

private const val ARG_PARAM_SEARCHSTRING = "searchstring"
class UserSearchListFragment : Fragment() {

    private var searchString: String? = null
    private var adapter: UserSearchAdapter? = null
    private lateinit var friendRecyclerView: RecyclerView

    private val loggedUserViewModel : LoggedUserViewModel by lazy {
        ViewModelProvider(requireActivity()).get(LoggedUserViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            searchString = it.getString(ARG_PARAM_SEARCHSTRING)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_search_list, container, false)
        friendRecyclerView=
            view.findViewById(R.id.fragUserSeachList_RecyclerView) as RecyclerView
        friendRecyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loggedUserViewModel.getUsersBySearchIsFriends()
            .observe(viewLifecycleOwner) { fr -> updateUI(fr) }
    }

    fun updateUI(friends: List<SearchForUserIsFriendDTO>){
        adapter = UserSearchAdapter(friends)
        friendRecyclerView.adapter = adapter
    }

    private inner class FriendHolder(view: View)
        : RecyclerView.ViewHolder(view){
        private lateinit var user: SearchForUserIsFriendDTO
        private val txtUsername: TextView = itemView.findViewById(R.id.txtUsername_ListItem_UserSearch)
        private val btnAddUser: Button = itemView.findViewById(R.id.btnAdd_ListItem_UserSearch)


        fun bind(user: SearchForUserIsFriendDTO){
            this.user = user
            txtUsername.text = this.user.username

            if(user.isFriend){
                btnAddUser.text = "Added"
                btnAddUser.isEnabled = false
            }
            btnAddUser.setOnClickListener { onClickRequestUser()}
        }

        private fun onClickRequestUser() {

            //TODO
        }

    }

    private inner class UserSearchAdapter(var friends: List<SearchForUserIsFriendDTO>) : RecyclerView.Adapter<FriendHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendHolder {
            val view = layoutInflater.inflate(R.layout.list_item_user_search, parent, false)
            return FriendHolder(view)
        }

        override fun onBindViewHolder(holder: FriendHolder, position: Int) {
            val friend = friends[position]
            holder.bind(friend)
        }

        override fun getItemCount(): Int = friends.size
    }

    companion object {
        fun newInstance(search:String): UserSearchListFragment {
            return UserSearchListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_SEARCHSTRING, search)
                }
            }
        }
    }
}