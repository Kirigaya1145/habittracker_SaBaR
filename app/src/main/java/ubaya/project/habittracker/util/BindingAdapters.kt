package ubaya.project.habittracker.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseMethod
import ubaya.project.habittracker.R

@BindingAdapter("habitIcon")
fun setHabitIcon(view: ImageView, iconName: String?) {
    val icon = when (iconName) {
        "exercise" -> R.drawable.ic_exercise
        "water"    -> R.drawable.ic_water_drop
        "book"     -> R.drawable.ic_book
        "food"     -> R.drawable.ic_food
        else       -> R.drawable.ic_habit_star
    }
    view.setImageResource(icon)
}
object Converters {
    @InverseMethod("stringToInt")
    @JvmStatic
    fun intToString(value: Int): String {
        // Karena Int primitif tidak mungkin null, langsung konversi ke String
        return value.toString()
    }

    @JvmStatic
    fun stringToInt(value: String): Int {
        // Jika text kosong, langsung kembalikan angka 0 agar tidak crash
        if (value.isBlank()) return 0
        return value.toIntOrNull() ?: 0
    }
}