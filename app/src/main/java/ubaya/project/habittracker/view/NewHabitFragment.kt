package ubaya.project.habittracker.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ubaya.project.habittracker.R
import ubaya.project.habittracker.databinding.FragmentDashboardBinding
import ubaya.project.habittracker.databinding.FragmentNewHabitBinding

class NewHabitFragment : Fragment() {

    private lateinit var binding: FragmentNewHabitBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

}