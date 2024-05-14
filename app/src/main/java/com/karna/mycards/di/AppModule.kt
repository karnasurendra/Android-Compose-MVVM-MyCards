package com.karna.mycards.di

import android.app.Application
import androidx.room.Room
import com.karna.mycards.data.data_source.CardDatabase
import com.karna.mycards.data.repository.CardRepository
import com.karna.mycards.data.repository.CardRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCardDatabase(app: Application): CardDatabase {
        return Room.databaseBuilder(app, CardDatabase::class.java, CardDatabase.CARD_DATABASE)
            .build()
    }

    fun provideCardRepository(db: CardDatabase): CardRepository {
        return CardRepositoryImpl(db.cardDao)
    }

}