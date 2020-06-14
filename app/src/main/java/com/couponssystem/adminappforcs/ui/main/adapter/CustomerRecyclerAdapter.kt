package com.couponssystem.adminappforcs.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.couponssystem.adminappforcs.R
import com.couponssystem.adminappforcs.data.model.User
import com.couponssystem.adminappforcs.ui.main.view.customer.CustomerListFragmentDirections
import kotlinx.android.synthetic.main.item_user.view.*
import com.couponssystem.adminappforcs.ui.main.adapter.CustomerRecyclerAdapter.DataViewHolder



class CustomerRecyclerAdapter(private val users : ArrayList<User>) : RecyclerView.Adapter<DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user : User) {
            itemView.apply {
                text_view_fname.text = user.fullName
                text_view_email.text = user.email
            }
        }

        fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
            itemView.setOnClickListener {
                event.invoke(getAdapterPosition(), getItemViewType())
            }
            return this
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
        holder.itemView.setOnClickListener(View.OnClickListener {
            val passId = CustomerListFragmentDirections.actionCustomerFragmentToCustomerDetailsFragment(users[position].id)
            holder.itemView.findNavController().navigate(passId)
        })
    }

    fun addUsers(users : List<User>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }
}