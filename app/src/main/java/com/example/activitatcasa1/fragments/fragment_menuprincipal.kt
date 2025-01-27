package com.example.activitatcasa1.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.activitatcasa1.R
import com.example.activitatcasa1.activities.SettingsActivity
import com.example.activitatcasa1.databinding.FragmentHomeBinding
import com.example.activitatcasa1.databinding.FragmentMenuprincipalBinding
import com.example.activitatcasa1.pojos.Cliente

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private var _binding: FragmentMenuprincipalBinding? = null
private val binding get() = _binding!!

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_menuprincipal.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_menuprincipal : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuprincipalBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cliente = arguments?.getSerializable("cliente") as? Cliente

        val nombreCliente:String = cliente?.getNombre().toString()

        binding.textViewGitano.text = binding.textViewGitano.text.toString() + " " + nombreCliente

        binding.button6.setOnClickListener {

            val intent = Intent(requireContext(), SettingsActivity::class.java)
            startActivity(intent)

        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_menuprincipal.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_menuprincipal().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}