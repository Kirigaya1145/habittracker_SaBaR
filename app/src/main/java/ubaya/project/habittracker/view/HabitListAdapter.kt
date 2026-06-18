package ubaya.project.habittracker.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ubaya.project.habittracker.R
import ubaya.project.habittracker.databinding.HabitListItemBinding
import ubaya.project.habittracker.model.Habit
import ubaya.project.habittracker.viewmodel.ListViewModel

class HabitListAdapter (val habitList:ArrayList<Habit>, val viewModel: ListViewModel)
    :RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {

    class HabitViewHolder(var binding: HabitListItemBinding)
        : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = HabitListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: HabitViewHolder,
        position: Int
    ) {
        // Data Binding — set variable langsung ke binding
        holder.binding.habit = habitList[position]
        holder.binding.viewModel = viewModel
        holder.binding.executePendingBindings()

//        // Klik nama habit → navigasi ke Edit Habit
//        holder.binding.txtHabitName.setOnClickListener {
//            val action = DashboardFragmentDirections
//                .actionDashboardFragmentToEditHabitFragment(habitList[position].id)
//            Navigation.findNavController(it).navigate(action)
//        }


//        holder.binding.txtHabitName.text = habitList[position].nama
//        holder.binding.txtHabitDescription.text = habitList[position].desc
//        holder.binding.txtHabitProgress.text = "${habitList[position].progress} / ${habitList[position].targets} ${habitList[position].status}"
//        holder.binding.progressHabit.max = habitList[position].targets
//        holder.binding.progressHabit.progress = habitList[position].progress
//        val icon: Int
//        if(habitList[position].icon == "exercise"){
//            icon = R.drawable.ic_exercise
//        } else if (habitList[position].icon == "water"){
//            icon = R.drawable.ic_water_drop
//        }else if (habitList[position].icon == "book"){
//            icon = R.drawable.ic_book
//        } else if (habitList[position].icon == "food"){
//            icon = R.drawable.ic_food
//        } else{
//            icon = R.drawable.ic_habit_star
//        }
//        holder.binding.imgHabitIcon.setImageResource(icon)
//
//        if(habitList[position].progress >= habitList[position].targets){
//            holder.binding.txtHabitStatus.text = "Completed"
//            holder.binding.imgCompletedMark.visibility = View.VISIBLE
//            holder.binding.viewCompletedAccent.visibility = View.VISIBLE
//            holder.binding.btnPlusProgress.isEnabled = false
//            holder.binding.btnMinusProgress.isEnabled = false
//        }else{
//            holder.binding.txtHabitStatus.text = "In Progress"
//            holder.binding.imgCompletedMark.visibility = View.GONE
//            holder.binding.viewCompletedAccent.visibility = View.GONE
//            holder.binding.btnPlusProgress.isEnabled = true
//            holder.binding.btnMinusProgress.isEnabled = true
//        }
//
//        holder.binding.btnPlusProgress.setOnClickListener {
//            viewModel.increaseProgress(habitList[position])
//        }
//        holder.binding.btnMinusProgress.setOnClickListener {
//            viewModel.decreaseProgress(habitList[position])
//        }
    }

    override fun getItemCount(): Int {
        return habitList.size
    }
    fun updateHabitList(newHabitList: ArrayList<Habit>){
        habitList.clear()
        habitList.addAll(newHabitList)
        notifyDataSetChanged()
    }

}
