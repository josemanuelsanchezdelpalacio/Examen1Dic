package com.iessanalberto.dam2.examen1dic.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iessanalberto.dam2.examen1dic.data.allEvents
import com.iessanalberto.dam2.examen1dic.data.listadoPersonas
import com.iessanalberto.dam2.examen1dic.models.EventCard
import com.iessanalberto.dam2.examen1dic.models.Evento
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ViewModelEvento : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun onDatosEvento(titulo: String, autor: String, recurso: String, descripcion: String, fecha: String, time: String) {
        _uiState.update { currentState ->
            currentState.copy(
                titulo = titulo,
                autor = autor,
                recurso = recurso,
                description = descripcion,
                fecha = fecha,
                time = time
            )
        }
    }

    fun onAutor(autor: String) {
        _uiState.update { currentState ->
            currentState.copy(autor = autor)
        }
    }

    fun onAddEvento(context: Context) {
        val autor = listadoPersonas.find { it.nombre == _uiState.value.autor }

        if (autor != null) {
            if (_uiState.value.titulo.isNotEmpty() && _uiState.value.autor.isNotEmpty() && _uiState.value.recurso.isNotEmpty() && _uiState.value.description.isNotEmpty() && _uiState.value.fecha.isNotEmpty() && _uiState.value.time.isNotEmpty()) {

                val nuevoEvento = Evento(
                    title = _uiState.value.titulo,
                    author = _uiState.value.autor,
                    date = _uiState.value.fecha,
                    hour = _uiState.value.time,
                    recurso = _uiState.value.recurso,
                    description = _uiState.value.description
                )
                allEvents.add(nuevoEvento)
                Toast.makeText(context, "Nuevo evento añadido", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Alguno de los campos está vacío", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "El autor no existe en la lista", Toast.LENGTH_SHORT).show()
        }
    }


    fun onDeleteEvento(context: Context) {
        val autor = _uiState.value.autor

        // si el autor existe
        if (autor.isNotEmpty()) {
            //busca el evento a traves del autor en la lista de eventos y lo borro
            val eventosBorrados =
                allEvents.filter { eventoBuscado -> eventoBuscado.author == autor }
            allEvents.removeAll(eventosBorrados)
            Toast.makeText(context, "Evento borrado", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "El autor no existe", Toast.LENGTH_SHORT).show()
        }
    }
}
