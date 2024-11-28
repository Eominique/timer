package com.eominik.timer.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rewards")
data class Reward(
    val iconKey: String,
    val title: String,
    val chanceInPercent: Int,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
)