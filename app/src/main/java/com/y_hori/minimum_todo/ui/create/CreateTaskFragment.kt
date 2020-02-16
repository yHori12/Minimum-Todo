package com.y_hori.minimum_todo.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.y_hori.minimum_todo.R
import com.y_hori.minimum_todo.data.enum.Deadline
import com.y_hori.minimum_todo.databinding.FragmentCreateTaskBinding
import com.y_hori.minimum_todo.viewmodels.MainViewModel
import com.y_hori.minimum_todo.utils.InjectorUtils
import com.y_hori.minimum_todo.utils.startShakeAnimation

class CreateTaskFragment : Fragment() {

    private lateinit var binding: FragmentCreateTaskBinding
    private val mainViewModel: MainViewModel by activityViewModels {
        InjectorUtils.provideMainViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_create_task, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.apply {
            this.viewModel = mainViewModel
            this.lifecycleOwner = viewLifecycleOwner
            this.title.doAfterTextChanged { input ->
                if (input.toString().isEmpty()) return@doAfterTextChanged
                mainViewModel.updateTitle(input.toString())
            }
            this.description.doAfterTextChanged { input ->
                if (input.toString().isEmpty()) return@doAfterTextChanged
                mainViewModel.updateDescription(input.toString())
            }
            var selectedDeadline:Deadline? = null

            this.switchNotification.setOnCheckedChangeListener { _, isChecked ->
                when(isChecked) {
                    true -> {
                        spinner.visibility = View.VISIBLE
                        selectedDeadline = Deadline.ONE_MINUTE
                        imvNotification.startShakeAnimation()
                    }
                    else -> {
                        spinner.visibility = View.INVISIBLE
                        selectedDeadline = null
                    }
                }
            }
            this.spinner.apply {
                setItems(Deadline.listOfTitle())
                setOnItemSelectedListener { _, position, _, _ ->
                    selectedDeadline = Deadline.values()[position]
                }
            }
            this.buttonCreate.setOnClickListener {
                mainViewModel.createTask(selectedDeadline)
            }
        }
        subscribeUi()
    }

    private fun subscribeUi() {
        mainViewModel.liveEventShowErrorDialog.observe(viewLifecycleOwner) { flag ->
            if (flag) {
                Snackbar.make(
                    binding.linearLayout,
                    getString(R.string.text_error_input_title),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        mainViewModel.liveEventCreateTaskSuccess.observe(viewLifecycleOwner) { flag ->
            if (flag) {
                view?.findNavController()
                    ?.navigate(CreateTaskFragmentDirections.actionCreateTaskFragmentToMainFragment())
            }
        }
    }
}
