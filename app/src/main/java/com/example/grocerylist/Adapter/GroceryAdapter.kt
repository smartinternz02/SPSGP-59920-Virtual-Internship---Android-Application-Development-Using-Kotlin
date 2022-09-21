package com.example.grocerylist.Adapter

import android.graphics.drawable.AnimationDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.grocerylist.Database.Entity.GroceryItems
import com.example.grocerylist.R
import com.example.grocerylist.UI.GroceryViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.groceryadapter.view.*


class GroceryAdapter(var list: List<GroceryItems>,val viewModel: GroceryViewModel):

    RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.groceryadapter,parent,false)
        return GroceryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        var currentPosition = list[position]
       holder.itemView.txtItemName.text = currentPosition.itemName
       holder.itemView.txtItemPrice.text = "${currentPosition.itemPrice}"
        holder.itemView.txtItemQuantity.text = "${currentPosition.itemQuantity}"

        holder.itemView.ibDelete.setOnClickListener {
            viewModel.delete(currentPosition)
        }

//        To get total cost
        if (position==list.size-1) {
            var totalCost = 0
//            for (i in 0 until list.size) {
//                totalCost += list[i].itemPrice
//            }
            val Total= (holder.itemView.txtItemPrice.text.toString().toInt()*holder.itemView.txtItemQuantity.text.toString().toInt()).toString()

            holder.itemView.txtItemTotalCost.visibility = View.VISIBLE
            holder.itemView.txtTotalCostTitle.visibility=View.VISIBLE
            holder.itemView.txtItemTotalCost.text = "$Total"

        }
        val animationDrawable = holder.itemView.adapt.background as AnimationDrawable
        animationDrawable.apply {
            setEnterFadeDuration(1000)
            setExitFadeDuration(2000)
            start()
        }
    }


    inner class GroceryViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

}