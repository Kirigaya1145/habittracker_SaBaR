package ubaya.project.habittracker.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Users(
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "password")
    val password: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}