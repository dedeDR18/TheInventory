package id.learn.android.theinventory.presentation.admin.peminjaman

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.learn.android.theinventory.databinding.ItemPeminjamanBinding
import id.learn.android.theinventory.domain.model.Peminjaman

class PeminjamanAdminAdapter : RecyclerView.Adapter<PeminjamanAdminAdapter.StatusPeminjamanViewHolder>(){
    val list = ArrayList<Peminjaman>()
    var onItemClick: ((data: Peminjaman) -> Unit)? = null

    fun clearData(){
        list.clear()
        notifyDataSetChanged()
    }

    fun setData(listData: List<Peminjaman>){
        list.clear()
        list.addAll(listData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusPeminjamanViewHolder = StatusPeminjamanViewHolder(
        ItemPeminjamanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: StatusPeminjamanViewHolder, position: Int) {
        val currentData = list[position]
        holder.bind(currentData)
    }

    override fun getItemCount() = list.size

    inner class StatusPeminjamanViewHolder(val binding: ItemPeminjamanBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: Peminjaman){
            binding.peminjaman = data
            binding.root.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }
    }
}