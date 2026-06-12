package ubaya.project.habittracker.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)  // sama pola kelas
    fun insert(user: Users)

    // cek login
    @Query("SELECT * FROM Users WHERE username = :username AND password = :password LIMIT 1")
    fun login(username: String, password: String): Users?

    // cek jumlah user — untuk insert default pertama kali
    @Query("SELECT COUNT(*) FROM Users")
    fun countUser(): Int
}