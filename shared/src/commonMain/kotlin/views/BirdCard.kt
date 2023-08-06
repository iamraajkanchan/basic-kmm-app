package views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import model.BirdResponse
import viewmodel.BirdViewModel

@Composable
fun BirdCard(viewModel: BirdViewModel, callback: () -> Unit) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            // Start of Buttons
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .height(50.dp)
                    .padding(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                for (category in uiState.categories) {
                    category?.let {
                        Button(onClick = {
                            viewModel.selectCategory(category)
                        }) {
                            Text(text = category)
                        }
                    }
                }
                Button(onClick = { callback() }) {
                    Text(text = "Todos")
                }
            }
        }
        // End of Buttons
        // Start of Vertical Grid
        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.wrapContentWidth().wrapContentHeight()
                )
            }
        } else {
            if (uiState.selectedBirds.isNotEmpty()) {
                AnimatedVisibility(visible = uiState.selectedBirds.isNotEmpty()) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp),
                        content = {
                            items(uiState.selectedBirds) {
                                BirdImageCell(it)
                            }
                        }
                    )
                }
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    NoDataFoundText()
                }
            }
        }
        // End of Vertical Grid
    }
}

@Composable
fun BirdImageCell(response: BirdResponse) {
    KamelImage(
        asyncPainterResource(data = "https://sebi.io/demo-image-api/${response.path}"),
        contentDescription = "${response.category} by ${response.author}",
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.0f),
        contentScale = ContentScale.Crop
    )
}