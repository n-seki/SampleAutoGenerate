package nseki.com.app.sampleautogenerate

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Sample")
data class SampleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)
