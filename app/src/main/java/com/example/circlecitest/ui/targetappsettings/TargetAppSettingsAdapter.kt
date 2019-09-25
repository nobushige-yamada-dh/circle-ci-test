package com.example.circlecitest.ui.targetappsettings

import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.circlecitest.R
import com.example.circlecitest.databinding.ItemTargetAppSettingsTargetAppBinding
import com.example.circlecitest.ui.MainThreadExecutor
import java.util.concurrent.Executors

class TargetAppSettingsAdapter(
        private val fragment: TargetAppSettingsFragment,
        private val viewModel: TargetAppSettingsViewModel
) : RecyclerView.Adapter<TargetAppSettingsAdapter.ViewHolder>() {

    class ViewHolder(
            val binding: ItemTargetAppSettingsTargetAppBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val nameTextView = itemView.findViewById<TextView>(R.id.name)!!
        val iconImageView = itemView.findViewById<ImageView>(R.id.icon)!!
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
        val binding = ItemTargetAppSettingsTargetAppBinding.inflate(layoutInflater, parent, false)
        binding.viewModel = viewModel
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = viewModel.items.value?.get(position)
        holder.binding.item = item
        holder.nameTextView.text = ""
        holder.iconImageView.setImageDrawable(null)
        item?.also {
            try {
                val appInfo = packageManager.getApplicationInfo(it.gameApp.applicationId, 0)
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
