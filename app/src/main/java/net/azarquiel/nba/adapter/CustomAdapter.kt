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
import net.azarquiel.openweather.model.Equipo

class CustomAdapter(val context: Context,
                    val layout: Int
                    ) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var dataList: List<Equipo> = emptyList()

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

    internal fun setEquipos(equipos: List<Equipo>) {
        this.dataList = equipos
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Equipo){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val rowFoto = itemView.findViewById(R.id.rowFoto) as ImageView
            val rowName = itemView.findViewById(R.id.rowName) as TextView
            val rowid = itemView.findViewById(R.id.rowlname) as TextView
            val rowRecord = itemView.findViewById(R.id.rowPosition) as TextView

            rowName.text="${dataItem.name}"
            rowid.text="${dataItem.id}"
            rowRecord.text="Récord: ${dataItem.record}"
            Picasso.get().load("${dataItem.teamLogoUrl}").into(rowFoto)

            itemView.tag = dataItem

        }

    }
}