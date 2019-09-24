package com.example.circlecitest.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.circlecitest.databinding.ItemMainGameAppBinding

class MainGameAppAdapter(
        private val fragment: MainFragment,
        private val viewModel: MainViewModel
) : RecyclerView.Adapter<MainGameAppAdapter.ViewHolder>() {

    class ViewHolder(
            val binding: ItemMainGameAppBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private val layoutInflater = LayoutInflater.from(fragment.context)

    init {
        viewModel.items.observe(fragment, Observer {
            notifyDataSetChanged()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMainGameAppBinding.inflate(layoutInflater, parent, false)
        binding.pm = fragment.context!!.packageManager
        binding.viewModel = viewModel
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.gameApp = viewModel.items.value?.get(position)
    }

    override fun getItemCount() = viewModel.items.value?.size ?: 0
}
