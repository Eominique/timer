package com.eominik.timer.di

import android.app.Application
import androidx.room.Room
import com.eominik.timer.data.RewardDao
import com.eominik.timer.data.TimerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  AppModule {
    @Provides
    fun provideReward(db : TimerDatabase) : RewardDao = db.rewardDao()

    @Singleton
    @Provides
    fun provideDatabase(
        app: Application,
        callback: TimerDatabase.Callback,
    ): TimerDatabase = Room.databaseBuilder(app, TimerDatabase::class.java, "it_database")
        .addCallback(callback)
        .build()

    @ApplicationScope
    @Singleton
    @Provides
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())


}



@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope