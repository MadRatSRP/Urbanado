package idd.urbanido.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import idd.urbanido.R
import idd.urbanido.adapters.UsersAdapter
import idd.urbanido.interfaces.fragments.UsersMVP
import idd.urbanido.model.registered_user.RegisteredUsers
import idd.urbanido.presenters.fragments.UsersPresenter
import idd.urbanido.repositories.UsersRepository
import kotlinx.android.synthetic.main.fragment_users.*
import kotlinx.android.synthetic.main.fragment_users.view.*
import ui.util.linearManager

class UsersView: Fragment(), UsersMVP.View {
    private var usersAdapter: UsersAdapter? = null

    private var usersPresenter: UsersPresenter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupMVP()

        usersUsersList.linearManager()

        usersShowUserInformation.setOnClickListener {
            context?.let { usersPresenter?.getUserInformation(it) }
        }

        usersShowUsersList.setOnClickListener {
            context?.let { usersPresenter?.getUsersList(it) }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                          savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.fragment_users, container, false)
        context?.let { usersAdapter = UsersAdapter(it) }

        view.usersUsersList.adapter = usersAdapter
        return view
    }

    override fun setupMVP() {
        usersPresenter = UsersPresenter(this, UsersRepository())
    }

    override fun showSnack(text: String){
        view?.let { Snackbar.make(it, text, Snackbar.LENGTH_SHORT).show() }
    }

    override fun showUsers(users: List<RegisteredUsers>) {
        usersAdapter?.updateUsersList(users)
        usersUsersList.adapter = usersAdapter
    }

    override fun showUser(name: String, email: String, password: String, phone: String) {
        userNameValue.text = name
        userEmailValue.text = email
        userPasswordValue.text = password
        userPhoneValue.text = phone
    }

    override fun onDestroyView() {
        usersAdapter = null

        super.onDestroyView()
    }
}