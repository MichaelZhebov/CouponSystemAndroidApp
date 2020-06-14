package com.couponssystem.adminappforcs.ui.main.view.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.couponssystem.adminappforcs.R
import com.couponssystem.adminappforcs.data.api.ApiHelper
import com.couponssystem.adminappforcs.data.api.NetworkService
import com.couponssystem.adminappforcs.databinding.CustomerDetailsFragmentBinding
import com.couponssystem.adminappforcs.ui.base.ViewModelFactory
import com.couponssystem.adminappforcs.ui.main.viewmodel.customer.CustomerDetailsViewModel
import kotlinx.android.synthetic.main.customer_details_fragment.*

class CustomerDetailsFragment : Fragment() {

    private lateinit var viewModel: CustomerDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Customer Details"
        val binding : CustomerDetailsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.customer_details_fragment, container, false)
        val id : Long = arguments?.let { CustomerDetailsFragmentArgs.fromBundle(
            it
        ).id}!!
        viewModel = ViewModelProviders.of(this, ViewModelFactory(ApiHelper(NetworkService.retrofitService()), id)).get(
            CustomerDetailsViewModel::class.java)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        update.setOnClickListener(View.OnClickListener {
            editeble()
        })
        cancel.setOnClickListener(View.OnClickListener {
            disable()
        })
        delete.setOnClickListener(View.OnClickListener {
            showDialog()
        })
        updateAllow.setOnClickListener(View.OnClickListener {
            viewModel.updateCompany()
            userName.text = viewModel.user.fullName
            disable()
            Toast.makeText(this.context,
                "Customer Updated", Toast.LENGTH_SHORT).show()
        })
    }

    fun editeble() {
        userName.visibility = View.GONE
        editUserName.visibility = View.VISIBLE
        activeSwitch.isClickable = true
        buttonsUpdDel.visibility = View.GONE
        buttonsCanDel.visibility = View.VISIBLE
    }

    fun disable() {
        userName.visibility = View.VISIBLE
        editUserName.visibility = View.GONE
        activeSwitch.isClickable = false
        buttonsUpdDel.visibility = View.VISIBLE
        buttonsCanDel.visibility = View.GONE
    }

    fun showDialog() {
        val builder = AlertDialog.Builder(this.context!!)
        builder.setTitle("Delete Customer")
        builder.setMessage("Do you really want to delete this customer?")

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            viewModel.deleteCompany()
            Toast.makeText(this.context,
                "Customer Deleted", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.customerFragment)
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

}