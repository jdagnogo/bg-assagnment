package com.jdagnogo.blueground.mars.model

data class MarsUnit(val image: String,
                    val title : String,
                    val region : Number,
                    val description : String,
                    val isBooked: Boolean,
                    val rating : Number,
                    val price : Number)