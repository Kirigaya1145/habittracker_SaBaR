package ubaya.project.habittracker.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import ubaya.project.habittracker.R
import ubaya.project.habittracker.databinding.FragmentNewHabitBinding
import ubaya.project.habittracker.model.Habit
import ubaya.project.habittracker.viewmodel.ListViewModel

class EditHabitFragment : Fragment() {
    private lateinit var binding: FragmentNewHabitBinding
    private lateinit var viewModel: ListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewHabitBinding.inflate(inflater, container, false)
        val items = listOf("Exercise", "Book", "Water", "Food")
        val adapter = ArrayAdapter(requireContext(), R.layout.dropdownlist_icon_choice, items)
        binding.ddlIcon.setAdapter(adapter)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)
        binding.textView4.text = "Edit Habit"
        binding.button.text = "Edit Habit"
        arguments?.let {
            val args = EditHabitFragmentArgs.fromBundle(it)
            viewModel.fetchHabitById(args.habitId.toString())
        }
        viewModel.selectedHabitLD.observe(viewLifecycleOwner) { habit ->
            if (habit != null) {
                binding.habit = habit
                binding.ddlIcon.setText(habit.icon?.lowercase() ?: "exercise", false)
            }
        }
        binding.button.setOnClickListener {
            val currentHabit = binding.habit

            if (currentHabit != null) {
                currentHabit.icon = binding.ddlIcon.text.toString().lowercase()
                if (currentHabit.nama.isEmpty() || currentHabit.desc.isEmpty() || currentHabit.status.isEmpty()) {
                    Toast.makeText(requireContext(), "Semua field wajib diisi!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                viewModel.updateHabit(currentHabit)
                Toast.makeText(requireContext(), "Habit Updated Successfully", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(it).popBackStack()
            }
        }
    }
}