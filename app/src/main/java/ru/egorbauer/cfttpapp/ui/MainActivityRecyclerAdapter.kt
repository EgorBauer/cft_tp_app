package ru.egorbauer.cfttpapp.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.egorbauer.cfttpapp.databinding.RvItemCardBinding
import coil.load
import ru.egorbauer.cfttpapp.domain.entity.User


class MainActivityRecyclerAdapter(
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<MainActivityRecyclerAdapter.ViewHolder>() {

    private var userList: MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainActivityRecyclerAdapter.ViewHolder {
        return ViewHolder(
            RvItemCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick
        )
    }

    inner class ViewHolder(
        private val binding: RvItemCardBinding,
        private val onItemClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: User) {
            val res = data.location.substringBeforeLast(",").substringBeforeLast(",")
            with(binding) {
                ivPhoto.load(data.picture) {
                    crossfade(true)
                }
                tvName.text = data.name
                tvAddress.text = res
                tvPhoneNumber.text = data.phone
                root.setOnClickListener {
                    onItemClick(adapterPosition)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: MainActivityRecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setUpdatedData(userList: List<User>) {
        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()
    }
}