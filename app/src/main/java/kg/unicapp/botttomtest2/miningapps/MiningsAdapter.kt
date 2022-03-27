package kg.unicapp.botttomtest2.miningapps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kg.unicapp.botttomtest2.R
import kg.unicapp.botttomtest2.airdrops.DropsData

class MiningsAdapter(private val appsDataList: ArrayList<AppsData>): RecyclerView.Adapter<MiningsAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_apps, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val appsData: AppsData = appsDataList[position]
        holder.mApps.text = appsData.mApps

    }

    override fun getItemCount(): Int {
        return appsDataList.size
    }


    public class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mApps: TextView = itemView.findViewById(R.id.tvApps)

    }
}
