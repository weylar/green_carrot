package com.android.greencarot.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.greencarot.databinding.ItemUserBinding
import com.android.greencarot.model.User


class UserAdapter(
    private val dataSet: List<User>,
) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val data = dataSet[position]
        viewHolder.binding.run {
            tvName.text = data.name
        }
    }


    override fun getItemCount() = dataSet.size


}


