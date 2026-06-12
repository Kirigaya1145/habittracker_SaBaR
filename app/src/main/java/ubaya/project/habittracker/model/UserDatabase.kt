package ubaya.project.habittracker.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Users::class), version = 1)
abstract class UserDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao

    companion object{
        @Volatile private var instance: UserDatabase? = null
        private val LOCK = Any()

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                UserDatabase::class.java,
                "user_database").build()
        operator fun invoke(context: Context): UserDatabase{
            return instance?: synchronized(LOCK){
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
    }
}