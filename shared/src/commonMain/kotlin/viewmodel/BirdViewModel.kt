package viewmodel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.BirdResponse
import repository.AppRepository

data class BirdUiState(
    val birds: List<BirdResponse> = emptyList(),
    val selectCategory: String? = null
) {
    val categories: Set<String?> = birds.map { it.category }.toSet()
    val selectedBirds: List<BirdResponse> =
        if (selectCategory != null) birds.filter { it.category == selectCategory } else birds
}

class BirdViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<BirdUiState> = MutableStateFlow(BirdUiState())
    val uiState: StateFlow<BirdUiState> get() = _uiState
    private val repository: AppRepository = AppRepository.getInstance()

    init {
        updateBirds()
    }

    private fun updateBirds() {
        viewModelScope.launch {
            _uiState.update { it.copy(birds = repository.getBirds(), selectCategory = "PIGEON") }
        }
    }

    fun selectCategory(category: String) {
        _uiState.update { it.copy(selectCategory = category) }
    }

    override fun onCleared() {
        repository.shutHttpClient()
    }

}