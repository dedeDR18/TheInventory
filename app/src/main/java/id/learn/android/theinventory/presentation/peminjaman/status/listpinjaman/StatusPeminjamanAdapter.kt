package id.learn.android.theinventory.presentation.peminjaman.status.listpinjaman

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.learn.android.theinventory.databinding.ItemPeminjamanBinding
import id.learn.android.theinventory.databinding.ItemSoundBinding
import id.learn.android.theinventory.domain.model.Barang
import id.learn.android.theinventory.domain.model.Peminjaman
import id.learn.android.theinventory.utils.ListBarang

class StatusPeminjamanAdapter : RecyclerView.Adapter<StatusPeminjamanAdapter.StatusPeminjamanViewHolder>(){
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