package com.example.activitatcasa1.bd

import android.content.Context
import com.example.activitatcasa1.pojos.Cliente
import com.example.activitatcasa1.bd.MiBD


class MiBancoOperacional protected constructor(context: Context?) {
    private val miBD: MiBD?

    init {
        miBD = MiBD.getInstance(context)
    }

    // Operacion Login: Verifica que el cliente existe y que su contraseña es correcta. Recibira un cliente
    // que solo contendrá el nif y la password.
    fun login(c: Cliente): Cliente? {
        val aux: Cliente? = miBD?.clienteDAO?.search(c) as? Cliente
        return if (aux == null) {
            null
        } else if (aux.getClaveSeguridad().equals(c.getClaveSeguridad())) {
            aux
        } else {
            null
        }
    }

    // Operacion changePassword: Cambia la password del cliente. Recibirá el cliente de la aplicación con la password cambiada.
    // Si devuelve un 1 es que ha verificado el cambio de password como correcto y todo ha ido bien, mientras que si devuelve
    // mientras que si devuelve un 0 no ha verificado el cambio de password como correcto y ha habido un error al cambiarlo.
    fun changePassword(c: Cliente?): Int {
        val resultado: Int? = miBD?.clienteDAO?.update(c)
        return if (resultado == 0) {
            0
        } else {
            1
        }
    }

    // Operacion getCuentas: Obtiene un ArrayList de las cuentas de un cliente que recibe como parámetro





    companion object {
        private var instance: MiBancoOperacional? = null

        //***************************************
        // Interfaz publica de la API del banco
        //***************************************
        // Constructor del banco. Obtiene una instancia del mismo para operar
        fun getInstance(context: Context?): MiBancoOperacional? {
            if (instance == null) {
                instance = MiBancoOperacional(context)
            }
            return instance
        }
    }
}