package ubaya.project.habittracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Habit(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "nama")
    var nama: String,

    @ColumnInfo(name = "desc")
    var desc: String,

    @ColumnInfo(name = "icon")
    var icon: String,

    @ColumnInfo(name = "targets")
    var targets: Int,

    @ColumnInfo(name = "status")
    var status: String,

    @ColumnInfo(name = "progress")
    var progress: Int
)