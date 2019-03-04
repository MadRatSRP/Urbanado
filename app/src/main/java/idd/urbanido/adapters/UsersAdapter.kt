package idd.urbanido.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import idd.urbanido.R
import idd.urbanido.model.Users
import idd.urbanido.model.registered_user.RegisteredUsers

class UsersAdapter(private val context: Context): RecyclerView.Adapter<UsersAdapter.UsersHolder>() {

    private val users = ArrayList<RegisteredUsers>()

    fun updateUsersList(new_users: List<RegisteredUsers>) {
        users.clear()
        users.addAll(new_users)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.list_users, parent, false)
        return UsersHolder(view)
    }

    override fun onBindViewHolder(holder: UsersHolder, position: Int) {
        holder.name.text = users[position].name
        holder.email.text = users[position].email
        holder.password.text = users[position].password
        holder.phone.text = users[position].phone
    }

    override fun getItemCount(): Int {
        return users.size
    }

    inner class UsersHolder internal constructor(view: View):
                RecyclerView.ViewHolder(view) {
        internal val name: TextView = view.findViewById(R.id.usersNameValue)
        internal val email: TextView = view.findViewById(R.id.usersEmailValue)
        internal val password: TextView = view.findViewById(R.id.usersPasswordValue)
        internal val phone: TextView = view.findViewById(R.id.usersPhoneValue)
    }
}