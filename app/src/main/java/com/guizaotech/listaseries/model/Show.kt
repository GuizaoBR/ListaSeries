package com.guizaotech.listaseries.model

class Show (
    val id: Long = 0,
    val url: String = "",
    val name: String = "",
    val type: String = "",
    val language: String = "",
    val summary: String = "",
    val genres : List<String>,
    val image: Image,
    val schedule: Schedule,
    //falta lista de episódios

)