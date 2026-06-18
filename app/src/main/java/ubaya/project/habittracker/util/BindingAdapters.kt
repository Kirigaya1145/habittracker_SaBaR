package ubaya.project.habittracker.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
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