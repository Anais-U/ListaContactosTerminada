package cl.bootcamp.backenddeveloper.di

import android.content.Context
import cl.bootcamp.backenddeveloper.repository.ContactRepository
import cl.bootcamp.backenddeveloper.room.ContactDao
import cl.bootcamp.backenddeveloper.room.ContactDatabase
import com.google.android.datatransport.runtime.dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideDatabase(context: Context):
            ContactDatabase{
        return ContactDatabase.getDatabase(context)
    }
    @Provides
    fun provideContactDao(database:ContactDatabase) = database.contactDao()
    @Provides
    fun provideContactRepository(dao: ContactDao) = ContactRepository(dao)

}