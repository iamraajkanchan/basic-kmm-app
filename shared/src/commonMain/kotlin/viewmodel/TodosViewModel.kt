package viewmodel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.TodosResponse
import repository.AppRepository

data class TodosUiState(val todos: List<TodosResponse> = emptyList())

class TodosViewModel : ViewModel() {
    private val repository: AppRepository = AppRepository.getInstance()
    private val _uiState: MutableStateFlow<TodosUiState> = MutableStateFlow(TodosUiState())
    val uiState: StateFlow<TodosUiState> get() = _uiState

    init {
        updateTodos()
    }

    private fun updateTodos() {
        viewModelScope.launch {
            _uiState.update { it.copy(todos = repository.getTodos() ?: emptyList()) }
        }
    }

    override fun onCleared() {
        repository.shutHttpClient()
    }
}