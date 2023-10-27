package net.azarquiel.recyclerviewNBA.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.nba.R
import net.azarquiel.openweather.model.Player

/**
 * Created by pacopulido on 9/10/18.
 */
class CustomAdapterPlayers(val context: Context,
                           val layout: Int
                    ) : RecyclerView.Adapter<CustomAdapterPlayers.ViewHolder>() {

    private var dataList: List<Player> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setPlayers(players: List<Player>) {
        this.dataList = players
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Player){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val rowFoto = itemView.findViewById(R.id.rowFoto) as ImageView
            val rowName = itemView.findViewById(R.id.rowName) as TextView
            val rownlname = itemView.findViewById(R.id.rowlname) as TextView
            val rowPosi = itemView.findViewById(R.id.rowPosition) as TextView

            rowName.text="${dataItem.firstName}"
            rownlname.text="${dataItem.lastName}"
            rowPosi.text="${dataItem.position}"
            Picasso.get().load("${dataItem.headShotUrl}").into(rowFoto)

            itemView.tag = dataItem

        }

    }
}