package ubaya.project.habittracker.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HabitDao {

    @Insert
    fun insert(habit: Habit)

    @Update
    fun update(habit: Habit)

    @Query("SELECT * FROM Habit")
    fun getAllHabits(): List<Habit>

    @Query("DELETE FROM Habit WHERE id = :habitId")
    fun deleteById(habitId: String)

    @Query("SELECT * FROM Habit WHERE id = :habitId LIMIT 1")
    fun getHabitById(habitId: String): Habit?
}