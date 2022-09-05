package com.example.appsorbv6.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.appsorbv6.databinding.FragmentTopicListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TopicListFragment : Fragment() {

    private var _binding: FragmentTopicListBinding? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentTopicListBinding.inflate(inflater, container, false)


        binding.fab.setOnClickListener {  view ->
            Navigation.findNavController(view).navigate(
             TopicListFragmentDirections.actionTopicListFragmentToFragmentTopicForm("")
            )
        Log.d("message","na press")
        }

        setHasOptionsMenu(true)
        return binding.root

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TopicListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}


