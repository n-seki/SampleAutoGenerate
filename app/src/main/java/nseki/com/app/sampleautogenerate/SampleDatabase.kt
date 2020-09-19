package nseki.com.app.sampleautogenerate

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [SampleEntity::class],
    version = 1,
    exportSchema = true
)
abstract class SampleDatabase : RoomDatabase() {

    abstract fun sampleDao(): SampleDao

    companion object {

        private const val DATABASE_NAME = "sample_table"
        private var INSTANCE: SampleDatabase? = null

        fun getInstance(context: Context): SampleDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): SampleDatabase {
            return Room.databaseBuilder(
                context,
                SampleDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}
