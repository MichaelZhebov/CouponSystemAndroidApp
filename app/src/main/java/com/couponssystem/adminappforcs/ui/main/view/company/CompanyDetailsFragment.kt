package com.couponssystem.adminappforcs.ui.main.view.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.couponssystem.adminappforcs.R
import com.couponssystem.adminappforcs.data.api.ApiHelper
import com.couponssystem.adminappforcs.data.api.NetworkService
import com.couponssystem.adminappforcs.databinding.CompanyDetailsFragmentBinding
import com.couponssystem.adminappforcs.ui.base.ViewModelFactory
import com.couponssystem.adminappforcs.ui.main.viewmodel.company.CompanyDetailsViewModel
import com.couponssystem.adminappforcs.utils.Status
import kotlinx.android.synthetic.main.company_details_fragment.*

class CompanyDetailsFragment : Fragment() {

    private lateinit var viewModel: CompanyDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Company Details"
        val binding: CompanyDetailsFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.company_details_fragment, container, false)
        val id: Long = arguments?.let {
            CompanyDetailsFragmentArgs.fromBundle(
                it
            ).id
        }!!
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(NetworkService.retrofitService()), id)
        ).get(CompanyDetailsViewModel::class.java)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        update.setOnClickListener(View.OnClickListener {
            enable()
        })
        cancel.setOnClickListener(View.OnClickListener {
            disable()
        })
        delete.setOnClickListener(View.OnClickListener {
            showDialog()
        })
        updateAllow.setOnClickListener(View.OnClickListener {
            viewModel.updateCompany().observe(this.viewLifecycleOwner, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            userName.text = viewModel.user.fullName
                            disable()
                            Toast.makeText(
                                this.context,
                                "Company Updated", Toast.LENGTH_SHORT
                            ).show()
                        }
                        Status.ERROR -> {
                            Toast.makeText(this.context, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                        }
                    }
                }
            })
        })
    }

    fun enable() {
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
        builder.setTitle("Delete Company")
        builder.setMessage("Do you really want to delete this company?")

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            viewModel.deleteCompany().observe(this.viewLifecycleOwner, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            Toast.makeText(
                                this.context,
                                "Company Deleted", Toast.LENGTH_SHORT
                            ).show()
                            findNavController().navigate(R.id.companyFragment)
                        }
                        Status.ERROR -> {
                            Toast.makeText(this.context, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                        }
                    }
                }
            })

        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }
}