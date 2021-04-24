package com.guizaotech.listaseries.model

class Episode (
        val id: Long = 0,
        val name: String = "",
        val number: Int = 0,
        val season: Int = 0,
        var summary: String = "",
        val image: Image,

        )