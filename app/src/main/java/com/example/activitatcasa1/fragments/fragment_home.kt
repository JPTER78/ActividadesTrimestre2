package com.example.activitatcasa1.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.activitatcasa1.R
import com.example.activitatcasa1.activities.MenuInternoActivity
import com.example.activitatcasa1.bd.MiBancoOperacional
import com.example.activitatcasa1.databinding.FragmentHomeBinding
import com.example.activitatcasa1.pojos.Cliente

class fragment_home : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val campoNif = binding.editTextText.text.toString()
            val campoContrasenya = binding.editTextTextPassword.text.toString()

            val banco = MiBancoOperacional.getInstance(requireContext())

            val clienteTemporal = Cliente(campoNif, campoContrasenya)

            val clienteValidado = banco?.login(clienteTemporal)

            if (clienteValidado != null) {
                // Cliente validado correctamente
                val intent = Intent(requireContext(), MenuInternoActivity::class.java)
                intent.putExtra("cliente", clienteValidado)
                startActivity(intent)
            } else {
                // Error en el login
                Toast.makeText(requireContext(), "NIF o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
