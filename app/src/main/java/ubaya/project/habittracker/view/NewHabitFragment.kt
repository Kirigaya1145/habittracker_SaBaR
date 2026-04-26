package ubaya.project.habittracker.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import ubaya.project.habittracker.databinding.FragmentNewHabitBinding
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import ubaya.project.habittracker.R
import ubaya.project.habittracker.databinding.ActivityMainBinding
import ubaya.project.habittracker.model.Habit
import ubaya.project.habittracker.viewmodel.ListViewModel

class NewHabitFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentNewHabitBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = FragmentNewHabitBinding.inflate(inflater, container, false)
        val items = listOf("Exercise", "Book", "Water", "Food")
        val adapter = ArrayAdapter(requireContext(), R.layout.dropdownlist_icon_choice,items)
        binding.ddlIcon.setAdapter(adapter)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        binding.button.setOnClickListener {
            val name = binding.txtName.text.toString()
            val desc = binding.txtDesc.text.toString()
            val goal = binding.txtGoal.text.toString().toIntOrNull() ?: 0
            val unit = binding.txtUnit.text.toString()
            val icon = binding.ddlIcon.text.toString().lowercase()
            val newHabit = Habit(
                id = System.currentTimeMillis().toString(),
                nama = name,
                desc = desc,
                icon = icon,
                targets = goal,
                status = unit,
                progress = 0
            )
            viewModel.addHabit(newHabit)
            Navigation.findNavController(it).popBackStack()
        }
    }
}