package com.de4aber.cappsule.User

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.de4aber.cappsule.LoginViewModel
import com.de4aber.cappsule.MainActivity
import com.de4aber.cappsule.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserListFragment : Fragment() {
    //region Variables and Values

    private var adapter: FriendAdapter? = null
    private lateinit var friendRecyclerView: RecyclerView
    private val loginViewModel :LoginViewModel by lazy {
        ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
    }

    //endregion
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)
        friendRecyclerView=
            view.findViewById(R.id.user_recycler_view) as RecyclerView
        friendRecyclerView.layoutManager = LinearLayoutManager(context)

        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel.userRepo.getAll(object : IUserCallback{
            override fun onUsersReady(users: List<UserDTO>) {
                updateUI(users)
            }
        })
    }

    fun updateUI(friends: List<UserDTO>){
        adapter = FriendAdapter(friends)
        friendRecyclerView.adapter = adapter
    }



    private inner class UserHolder(view: View)
        : RecyclerView.ViewHolder(view){
        private lateinit var user: UserDTO
        private val txtId: TextView = itemView.findViewById(R.id.txtUserId_ListItemUser)
        private val txtUsername: TextView = itemView.findViewById(R.id.txtUsername_ListItemUser)
        private val txtBirthDate: TextView = itemView.findViewById(R.id.txtBirthDate_ListItemUser)
        private val txtCapScore: TextView = itemView.findViewById(R.id.txtCapScore_ListItemUser)


        fun bind(friend: UserDTO){
            this.user = friend
            txtId.text =  this.user.id.toString()
            txtUsername.text = this.user.username
            txtBirthDate.text = this.user.birthdate
            txtCapScore.text = this.user.CapScore.toString()
            itemView.setOnClickListener { onClick() }
        }
         fun onClick() {
             val intent = MainActivity.newIntent(requireContext(), user.id)
             startActivity(intent);
            //Toast.makeText(context, user.username, Toast.LENGTH_SHORT).show()
        }

    }

    private inner class FriendAdapter(var users:List<UserDTO>) : RecyclerView.Adapter<UserHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
            val view = layoutInflater.inflate(R.layout.list_item_user, parent, false)
            return UserHolder(view)
        }



        override fun onBindViewHolder(holder: UserHolder, position: Int) {
            val friend = users[position]
            holder.bind(friend)

        }

        override fun getItemCount(): Int = users.size
    }

    companion object {
        fun newInstance(): UserListFragment {
            return UserListFragment()
        }
    }
}