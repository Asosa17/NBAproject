package net.azarquiel.openweather.model

import java.io.Serializable

data class Result( var equipos: List<Equipo>)
data class Equipo( var id:Int,var name:String,var teamLogoUrl:String,var record:String):Serializable
data class Result2( var players: List<Player>)
data class Player(var firstName:String, var lastName:String, var position:String, var headShotUrl:String)

