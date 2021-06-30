package id.learn.android.theinventory.presentation.databarang

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.learn.android.theinventory.databinding.ItemSoundBinding
import id.learn.android.theinventory.domain.model.Barang

class BarangAdapter : RecyclerView.Adapter<BarangAdapter.BarangViewHolder>(){
    val list = ArrayList<Barang>()

    fun clearData(){
        list.clear()
        notifyDataSetChanged()
    }

    fun setData(listData: List<Barang>){
        list.clear()
        list.addAll(listData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder = BarangViewHolder(
        ItemSoundBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val currentData = list[position]
        holder.bind(currentData)
    }

    override fun getItemCount() = list.size

    inner class BarangViewHolder(val binding: ItemSoundBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: Barang){
            binding.barang = data
        }
    }
}