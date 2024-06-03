package com.karna.mycards.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karna.mycards.domain.model.Card

@Database(
    entities = [Card::class],
    version = 1,
    exportSchema = false
)
abstract class CardDatabase : RoomDatabase() {

    abstract val cardDao:CardDao

    companion object {
        const val CARD_DATABASE = "cards_db"
    }

}