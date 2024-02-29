package ru.egorbauer.cfttpapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.transform.CircleCropTransformation
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
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: User) {
            with(binding) {
                ivPhoto.load(data.picture) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
                tvName.text = data.name
                tvAddress.text = data.location
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

    fun setUpdatedData(userList: List<User>) {
        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()
    }
}