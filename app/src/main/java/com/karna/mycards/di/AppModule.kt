package com.karna.mycards.di

import android.app.Application
import androidx.room.Room
import com.karna.mycards.data.data_source.CardDatabase
import com.karna.mycards.data.repository.CardRepository
import com.karna.mycards.data.repository.CardRepositoryImpl
import com.karna.mycards.domain.use_case.AddCard
import com.karna.mycards.domain.use_case.CardUseCases
import com.karna.mycards.domain.use_case.DeleteCard
import com.karna.mycards.domain.use_case.GetCard
import com.karna.mycards.domain.use_case.GetCards
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

    @Provides
    @Singleton
    fun provideCardRepository(db: CardDatabase): CardRepository {
        return CardRepositoryImpl(db.cardDao)
    }

    @Provides
    @Singleton
    fun provideCardUseCases(cardRepository: CardRepository): CardUseCases {
        return CardUseCases(
            getCards = GetCards(cardRepository),
            getCard = GetCard(cardRepository),
            addCard = AddCard(cardRepository),
            deleteCard = DeleteCard(cardRepository)
        )
    }

}