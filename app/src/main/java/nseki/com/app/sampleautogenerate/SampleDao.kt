package nseki.com.app.sampleautogenerate

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SampleDao {

    @Query("SELECT sqlite_version();")
    suspend fun getVersion(): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIncrementId(sampleEntity: SampleEntity)

    @Insert
    suspend fun insertIncrementId(sampleEntity: List<SampleEntity>)

    @Query("DELETE FROM Sample")
    suspend fun deleteAll()
}
