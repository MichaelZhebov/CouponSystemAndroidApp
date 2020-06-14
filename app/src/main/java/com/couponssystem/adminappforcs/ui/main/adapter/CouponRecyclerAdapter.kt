package com.couponssystem.adminappforcs.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.couponssystem.adminappforcs.R
import com.couponssystem.adminappforcs.data.model.Coupon
import com.couponssystem.adminappforcs.ui.main.adapter.CouponRecyclerAdapter.DataViewHolder
import com.couponssystem.adminappforcs.ui.main.view.coupon.CouponListFragmentDirections
import kotlinx.android.synthetic.main.item_coupon.view.*


class CouponRecyclerAdapter(private val coupons : ArrayList<Coupon>) : RecyclerView.Adapter<DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(coupon : Coupon) {
            itemView.apply {
                text_view_title.text = coupon.title
                text_view_price.text = "Price: ${coupon.price}$"
                text_view_amount.text = "Amount: ${coupon.amount}"
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
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_coupon, parent, false))

    override fun getItemCount(): Int = coupons.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(coupons[position])
        holder.itemView.setOnClickListener(View.OnClickListener {
            val passId = CouponListFragmentDirections.actionCouponFragmentToCouponDetailsFragment(coupons[position].id)
            holder.itemView.findNavController().navigate(passId)
        })
    }

    fun addCoupon(coupons : List<Coupon>) {
        this.coupons.apply {
            clear()
            addAll(coupons)
        }
    }
}