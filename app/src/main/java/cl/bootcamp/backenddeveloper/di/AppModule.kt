package cl.bootcamp.backenddeveloper.di

import android.content.Context
import cl.bootcamp.backenddeveloper.repository.ContactRepository
import cl.bootcamp.backenddeveloper.room.ContactDao
import cl.bootcamp.backenddeveloper.room.ContactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun ProvideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): ContactDatabase {
        return ContactDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideContactDao(database: ContactDatabase): ContactDao {
        return database.contactDao()
    }

    @Provides
    @Singleton
    fun provideContactRepository(dao: ContactDao): ContactRepository {
        return ContactRepository(dao)
    }
}