package com.eominik.timer.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eominik.timer.core.IconKey

@Entity(tableName = "rewards")
data class Reward(
    val name: String,
    val chanceInPercent: Int,
    val iconKey: IconKey,
    val isUnlocked: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
)