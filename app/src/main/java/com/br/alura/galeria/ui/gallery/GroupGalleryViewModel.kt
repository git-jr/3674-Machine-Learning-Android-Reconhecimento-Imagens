package com.br.alura.galeria.ui.gallery

import androidx.lifecycle.ViewModel
import com.br.alura.galeria.ImageUriManager
import com.br.alura.galeria.data.ImageCategoryRepository
import com.br.alura.galeria.data.ImageGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GroupGalleryViewModel @Inject constructor(
    private val imageUriManager: ImageUriManager,
    private val imageCategoryRepository: ImageCategoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(GroupGalleryUiState())
    val uiState: StateFlow<GroupGalleryUiState>
        get() = _uiState.asStateFlow()


    private fun groupImages() {
        val imagesWithLabels = _uiState.value.imageWithLabels
        val groupsList = mutableListOf<ImageGroup>()

        ImageCategoryRepository().getAvailableGroups().forEach { group ->
            val images = imagesWithLabels.filter { imageWithLabels ->
                imageWithLabels.labels.any { label ->
                    label in group.keyWords
                }
            }

            if (images.isNotEmpty()) {
                groupsList.add(
                    ImageGroup(
                        name = group.name,
                        images = images
                    )
                )
            }
        }

        _uiState.value = _uiState.value.copy(
            groups = groupsList,
            isLoading = false
        )
    }
}