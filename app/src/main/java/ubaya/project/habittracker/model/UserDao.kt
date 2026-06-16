package ubaya.project.habittracker.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)  // sama pola kelas
    fun insert(user: Users)

    @Query("SELECT * FROM Users WHERE username = :username AND password = :password LIMIT 1")
    fun login(username: String, password: String): Users?

    @Query("SELECT COUNT(*) FROM Users")
    fun countUser(): Int
    @Query("UPDATE Users SET is_loggedIn = 1 WHERE username = :username")
    fun setLoggedIn(username: String)

    @Query("UPDATE Users SET is_loggedIn = 0")
    fun setLoggedOut()

    @Query("SELECT * FROM Users WHERE is_loggedIn = 1 LIMIT 1")
    fun getLoggedInUser(): Users?
}