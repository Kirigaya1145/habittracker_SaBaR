package ubaya.project.habittracker.util

import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ubaya.project.habittracker.model.UserDatabase

val DB_NAME = "user_database"

fun buildDb(context: Context): UserDatabase{
    val db = UserDatabase.buildDatabase((context))
    return db
}
val MIGRATION_1_2 = object : Migration(1, 2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Users ADD COLUMN is_logged_in INTEGER NOT NULL DEFAULT 0")
    }
}