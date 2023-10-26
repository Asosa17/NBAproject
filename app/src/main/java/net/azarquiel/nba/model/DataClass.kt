package net.azarquiel.openweather.model

data class Result( var equipos: List<Equipo>)
data class Equipo( var id:Int,var name:String,var teamLogoUrl:String,var record:String)
