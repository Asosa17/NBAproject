package net.azarquiel.nba.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.nba.R
import net.azarquiel.openweather.model.Equipo
import net.azarquiel.openweather.model.Player
import net.azarquiel.recyclerviewNBA.adapter.CustomAdapterPlayers
import java.net.URL

class PlayersActivity : AppCompatActivity() {
    private lateinit var equipo: Equipo
    private lateinit var adapter: CustomAdapterPlayers
    private lateinit var rv2: RecyclerView
    private lateinit var ivTeam: ImageView
    private lateinit var nameTeam: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players)

        equipo=intent.getSerializableExtra("equipo") as Equipo
        rv2=findViewById<RecyclerView>(R.id.rv2)
        ivTeam=findViewById<ImageView>(R.id.ivTeam)
        Picasso.get().load("${equipo.teamLogoUrl}").into(ivTeam)
        nameTeam=findViewById<TextView>(R.id.nameTeam)
        nameTeam.text=equipo.name

        initrv()
        getDatos()
    }
    private fun getDatos() {
        GlobalScope.launch() {
            val jsontxt = URL("http://www.ies-azarquiel.es/paco/apinba/players/team?name=${equipo.name}").readText(Charsets.UTF_8)
            launch(Dispatchers.Main) {
                val result= Gson().fromJson(jsontxt, Array<Player>::class.java)
                adapter.setPlayers(result.toList())
            }
        }

    }

    private fun initrv() {
        adapter = CustomAdapterPlayers(this,R.layout.rowplayer)
        rv2.adapter=adapter
        rv2.layoutManager= LinearLayoutManager(this)

    }
}