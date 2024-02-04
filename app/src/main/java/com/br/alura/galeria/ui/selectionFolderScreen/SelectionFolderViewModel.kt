package com.br.alura.galeria.ui.selectionFolderScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.alura.galeria.dataStore.UserPreferencesDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectionFolderViewModel @Inject constructor(
    private val dataStore: UserPreferencesDataStore
) : ViewModel() {

    private val _uiState = MutableStateFlow(SelectionFolderScreenUiState())
    val uiState: StateFlow<SelectionFolderScreenUiState>
        get() = _uiState.asStateFlow()

    fun savePath(path: String) {
        viewModelScope.launch {
            dataStore.saveLastSelectedPath(path)
            _uiState.value = _uiState.value.copy(
                savedPath = true
            )
        }
    }

    fun setLoading(loading: Boolean) {
        _uiState.value = _uiState.value.copy(
            loading = loading
        )
    }
}