package cl.bootcamp.backenddeveloper.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import cl.bootcamp.backenddeveloper.model.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase :
        RoomDatabase() {
            abstract fun contactDao(): ContactDao

            companion object {
                @Volatile
                private var INSTANCE: ContactDatabase? = null

                private val MIGRATION_1_2 = object : Migration(1, 2) {
                    override fun migrate(db: SupportSQLiteDatabase) {
                        TODO("Not yet implemented")
                    }
                }

                fun getDatabase(context: Context):
                        ContactDatabase{
                    return INSTANCE ?: synchronized(this) {
                        val instance= Room.databaseBuilder(
                            context.applicationContext,
                            ContactDatabase::class.java,
                            "contact_database"
                        )
                            .addMigrations(MIGRATION_1_2)
                            .build()
                        INSTANCE = instance
                        instance
                    }
                }
            }
        }
