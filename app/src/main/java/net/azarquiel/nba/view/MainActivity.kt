package net.azarquiel.nba.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.nba.R
import net.azarquiel.openweather.model.Equipo
import net.azarquiel.openweather.model.Result
import net.azarquiel.recyclerviewNBA.adapter.CustomAdapter
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: CustomAdapter
    private lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv=findViewById<RecyclerView>(R.id.rv)
        initrv()
        getDatos()
    }

    private fun getDatos() {
        GlobalScope.launch() {
            val jsontxt = URL("http://www.ies-azarquiel.es/paco/apinba/teams").readText(Charsets.UTF_8)
            launch(Dispatchers.Main) {
                val result= Gson().fromJson(jsontxt, Array<Equipo>::class.java)
                adapter.setEquipos(result.toList())
            }
        }

    }

    private fun initrv() {
        adapter = CustomAdapter(this,R.layout.row)
        rv.adapter=adapter
        rv.layoutManager= LinearLayoutManager(this)

    }
    fun onclickteam(v: View){
        val equipo = v.tag as Equipo
        val intent= Intent(this, PlayersActivity::class.java)
        intent.putExtra("equipo",equipo)
        startActivity(intent)
    }
}