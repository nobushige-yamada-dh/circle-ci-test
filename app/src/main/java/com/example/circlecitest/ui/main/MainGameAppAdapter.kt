package com.example.circlecitest.ui.main

import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.circlecitest.R
import com.example.circlecitest.databinding.ItemMainGameAppBinding
import com.example.circlecitest.ui.MainThreadExecutor
import java.util.concurrent.Executors

class MainGameAppAdapter(
        private val fragment: MainFragment,
        private val viewModel: MainViewModel
) : RecyclerView.Adapter<MainGameAppAdapter.ViewHolder>() {

    class ViewHolder(
            val binding: ItemMainGameAppBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val nameTextView = itemView.findViewById<TextView>(R.id.name)
        val iconImageView = itemView.findViewById<ImageView>(R.id.icon)
    }

    private val bgExecutor = Executors.newSingleThreadExecutor()
    private val uiExecutor = MainThreadExecutor()
    private val layoutInflater = LayoutInflater.from(fragment.context)
    private val packageManager = fragment.context!!.packageManager

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
        val gameApp = viewModel.items.value?.get(position)
        holder.binding.gameApp = gameApp
        holder.nameTextView.text = ""
        holder.iconImageView.setImageDrawable(null)
        gameApp?.also {
            try {
                val appInfo = packageManager.getApplicationInfo(it.applicationId, 0)
                holder.nameTextView.text = appInfo.loadLabel(packageManager)
                bgExecutor.execute {
                    val icon = packageManager.getApplicationIcon(appInfo)
                    uiExecutor.execute {
                        if (holder.adapterPosition == position) {
                            holder.iconImageView.setImageDrawable(icon)
                        }
                    }
                }
            } catch (e: PackageManager.NameNotFoundException) {
                // FIXME: reload
            }
        }
    }

    override fun getItemCount() = viewModel.items.value?.size ?: 0
}
