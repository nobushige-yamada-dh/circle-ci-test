package com.example.circlecitest.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.circlecitest.data.GameApp
import com.example.circlecitest.databinding.ItemMainGameAppBinding
import java.util.ArrayList

class MainGameAppAdapter(
        context: Context,
        private val viewModel: MainViewModel
) : RecyclerView.Adapter<MainGameAppAdapter.ViewHolder>() {

    class ViewHolder(
            val binding: ItemMainGameAppBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private val layoutInflater = LayoutInflater.from(context)

    var gameAppList: List<GameApp> = ArrayList(0)
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMainGameAppBinding.inflate(layoutInflater, parent, false)
        binding.viewModel = viewModel
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.gameApp = gameAppList[position]
    }

    override fun getItemCount() = gameAppList.size
}
