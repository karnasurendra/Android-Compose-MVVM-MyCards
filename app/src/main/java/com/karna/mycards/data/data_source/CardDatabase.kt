package com.karna.mycards.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karna.mycards.data.model.Card

@Database(
    entities = [Card::class],
    version = 1
)
abstract class CardDatabase : RoomDatabase() {

    abstract val cardDao:CardDao

    companion object {
        const val CARD_DATABASE = "cards_db"
    }

}