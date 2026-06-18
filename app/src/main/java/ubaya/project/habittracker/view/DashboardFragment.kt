package ubaya.project.habittracker.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import ubaya.project.habittracker.R
import androidx.recyclerview.widget.LinearLayoutManager
import ubaya.project.habittracker.databinding.FragmentDashboardBinding
import ubaya.project.habittracker.viewmodel.ListViewModel

class DashboardFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private lateinit var habitAdapter: HabitListAdapter
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)
//        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        habitAdapter = HabitListAdapter(arrayListOf(), viewModel)

        binding.recExerciseView.layoutManager = LinearLayoutManager(context)
        binding.recExerciseView.adapter = habitAdapter

        observeViewModel()

        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }



        binding.fab.setOnClickListener {
            Navigation.findNavController(it).navigate(
                R.id.action_dashboardFragment_to_newhabitFragment
            )
        }

    }
    fun observeViewModel() {
        viewModel.habitLD.observe(viewLifecycleOwner, Observer{
            habitAdapter.updateHabitList(it)
            binding.refreshLayout.isRefreshing = false
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.progressLoad.visibility = View.VISIBLE
                binding.recExerciseView.visibility = View.GONE
                binding.txtError.visibility = View.GONE
            } else {
                binding.progressLoad.visibility = View.GONE
                binding.recExerciseView.visibility = View.VISIBLE
            }
        })
    }
    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }
}