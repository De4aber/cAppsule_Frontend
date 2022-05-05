package com.de4aber.cappsule.User

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.de4aber.cappsule.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FriendListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FriendListFragment : Fragment() {
    //region Variables and Values
    val sampleRepo = UserRepo()
    private var adapter: FriendAdapter? = null
    private lateinit var friendRecyclerView: RecyclerView

    //endregion
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_friend_list, container, false)
        friendRecyclerView=
            view.findViewById(R.id.friend_recycler_view) as RecyclerView
        friendRecyclerView.layoutManager = LinearLayoutManager(context)

        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sampleRepo.getAll(object : ICallback{
            override fun onUsersReady(users: List<BEUser>) {
                updateUI(users)
            }
        })
    }

    fun updateUI(friends: List<BEUser>){
        adapter = FriendAdapter(friends)
        friendRecyclerView.adapter = adapter
    }

    private inner class FriendHolder(view: View)
        : RecyclerView.ViewHolder(view){
        private lateinit var friend: BEUser
        private val txtId: TextView = itemView.findViewById(R.id.txt_UserId)
        private val txtUsername: TextView = itemView.findViewById(R.id.txt_Username)

        fun bind(friend: BEUser){
            this.friend = friend
            txtId.text =  this.friend.id.toString()
            txtUsername.text = this.friend.name
        }

    }

    private inner class FriendAdapter(var users:List<BEUser>) : RecyclerView.Adapter<FriendHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendHolder {
            val view = layoutInflater.inflate(R.layout.list_item_friend, parent, false)
            return FriendHolder(view)
        }

        override fun onBindViewHolder(holder: FriendHolder, position: Int) {
            val friend = users[position]
            holder.bind(friend)
        }

        override fun getItemCount(): Int = users.size
    }

    companion object {
        fun newInstance(): FriendListFragment {
            return FriendListFragment()
        }
    }
}