package kg.unicapp.botttomtest2.airdrops

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kg.unicapp.botttomtest2.R

class AirdropAdapter(private val dropsDataList: ArrayList<DropsData>): RecyclerView.Adapter<AirdropAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dropsData: DropsData = dropsDataList[position]
        holder.airdrop.text = dropsData.airdrop

    }

    override fun getItemCount(): Int {
        return dropsDataList.size
    }


    public class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val airdrop: TextView = itemView.findViewById(R.id.tvAirdrop)

    }


}