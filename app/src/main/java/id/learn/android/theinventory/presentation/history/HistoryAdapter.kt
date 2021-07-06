package id.learn.android.theinventory.presentation.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.learn.android.theinventory.databinding.ItemHistoryBinding
import id.learn.android.theinventory.databinding.ItemPeminjamanBinding
import id.learn.android.theinventory.domain.model.Peminjaman

class HistoryAdapter() : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>(){
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder = HistoryViewHolder(
        ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val currentData = list[position]
        holder.bind(currentData)
    }

    override fun getItemCount() = list.size

    inner class HistoryViewHolder(val binding: ItemHistoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: Peminjaman){
            binding.peminjaman = data
            binding.status = if (data.status.equals("selesai") || data.status.equals("bermasalah")) data.status else "-"
            binding.root.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }
    }
}