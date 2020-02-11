package com.y_hori.minimum_todo.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.y_hori.minimum_todo.R
import com.y_hori.minimum_todo.databinding.FragmentCreateTaskBinding
import com.y_hori.minimum_todo.utils.InjectorUtils

class CreateTaskFragment : Fragment() {

    companion object {
        fun newInstance() =
            CreateTaskFragment()
    }

    private lateinit var binding:FragmentCreateTaskBinding
    private val createTaskViewModel: CreateTaskViewModel by viewModels {
        InjectorUtils.provideCreateTaskViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_create_task,container,false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.apply {
            this.viewModel = createTaskViewModel
            this.lifecycleOwner = viewLifecycleOwner
            this.title.doAfterTextChanged { input ->
                if (input.toString().isEmpty()) return@doAfterTextChanged
                createTaskViewModel.updateTitle(input.toString())
            }
            description.doAfterTextChanged{ input ->
                if (input.toString().isEmpty()) return@doAfterTextChanged
                createTaskViewModel.updateDescription(input.toString())
            }
            this.buttonCreate.setOnClickListener { view ->
                view.findNavController()
                    .navigate(CreateTaskFragmentDirections.actionCreateTaskFragmentToMainFragment())
            }

        }
    }

}
