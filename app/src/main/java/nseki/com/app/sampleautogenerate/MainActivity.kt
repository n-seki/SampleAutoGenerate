package nseki.com.app.sampleautogenerate

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            val database = SampleDatabase.getInstance(this@MainActivity)
            val sampleDao = database.sampleDao()
            val sqliteVersion = sampleDao.getVersion()

            withContext(Dispatchers.Main) {
                findViewById<TextView>(R.id.sqlite_version).text = "SQLite v${sqliteVersion}"
            }
        }

        findViewById<Button>(R.id.insert_increment_id).setOnClickListener {
            lifecycleScope.launch {
                val sampleData = SampleEntity(name = UUID.randomUUID().toString())
                SampleDatabase.getInstance(this@MainActivity).sampleDao().insertIncrementId(sampleData)
            }
        }

        findViewById<Button>(R.id.insert_increment_id_many).setOnClickListener {
            lifecycleScope.launch {
                val sampleData = (0..2).map {
                    SampleEntity(name = UUID.randomUUID().toString())
                }
                SampleDatabase.getInstance(this@MainActivity).sampleDao().insertIncrementId(sampleData)
            }
        }

        findViewById<Button>(R.id.insert_with_id).setOnClickListener {
            lifecycleScope.launch {
                val sampleData = SampleEntity(id = 1, name = UUID.randomUUID().toString())
                SampleDatabase.getInstance(this@MainActivity).sampleDao().insertIncrementId(sampleData)
            }
        }

        findViewById<Button>(R.id.delete_all).setOnClickListener {
            lifecycleScope.launch {
                SampleDatabase.getInstance(this@MainActivity).sampleDao().deleteAll()
            }
        }
    }
}
