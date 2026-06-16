package ubaya.project.habittracker.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Users(
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "password")
    val password: String,
    @ColumnInfo(name = "is_loggedIn")
    var isLoggedIn: Int = 0
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}