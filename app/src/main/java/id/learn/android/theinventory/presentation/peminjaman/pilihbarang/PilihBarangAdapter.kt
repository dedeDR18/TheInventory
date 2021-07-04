package id.learn.android.theinventory.presentation.peminjaman.pilihbarang

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.learn.android.theinventory.databinding.ItemListBarangBinding
import id.learn.android.theinventory.domain.model.Barang
import id.learn.android.theinventory.utils.ListBarang

class PilihBarangAdapter : RecyclerView.Adapter<PilihBarangAdapter.ListBarangViewHolder>(){
    val list = ArrayList<ListBarang>()
    var onAddButtonCLick: ((data:ListBarang) -> Unit)? = null
    var onMinusButtonCLick: ((data: ListBarang) -> Unit)? = null


    fun clearData(){
        list.clear()
        notifyDataSetChanged()
    }

    fun setData(listData: List<ListBarang>){
        list.clear()
        list.addAll(listData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListBarangViewHolder = ListBarangViewHolder(
        ItemListBarangBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ListBarangViewHolder, position: Int) {
        val currentData = list[position]
        holder.bind(currentData, position)

    }

    override fun getItemCount() = list.size

    inner class ListBarangViewHolder(private val binding: ItemListBarangBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: ListBarang, position:Int){
                binding.barang = data

                binding.btnKurangi.apply {
                    isEnabled = data.itemCount != 0
                    setOnClickListener {
                        data.itemCount -= 1
                        notifyItemChanged(position)
                        onMinusButtonCLick?.invoke(data)
                    }
                }
                binding.btnTambah.setOnClickListener {
                    onAddButtonCLick?.invoke(data)
                    data.itemCount += 1
                    notifyItemChanged(position)
                }
        }
    }

}