package com.karna.mycards.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.room.Room
import com.karna.mycards.data.data_source.CardDatabase
import com.karna.mycards.data.repository.CardRepository
import com.karna.mycards.data.repository.CardRepositoryImpl
import com.karna.mycards.domain.use_case.AddCard
import com.karna.mycards.domain.use_case.CardUseCases
import com.karna.mycards.domain.use_case.DeleteCard
import com.karna.mycards.domain.use_case.GetCard
import com.karna.mycards.domain.use_case.GetCards
import com.karna.mycards.presentation.util.proto.UserInfo
import com.karna.mycards.presentation.util.proto.UserInfoSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val CURRENT_USER_DATA_STORE_FILE_NAME = "user_prefs.pb"

    @Singleton
    @Provides
    fun provideAboutUserData(@ApplicationContext appContext: Context): DataStore<UserInfo> {
        return DataStoreFactory.create(
            serializer = UserInfoSerializer,
            produceFile = { appContext.dataStoreFile(CURRENT_USER_DATA_STORE_FILE_NAME) },
        )
    }

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