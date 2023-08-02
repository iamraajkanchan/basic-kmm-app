import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import viewmodel.TodosViewModel
import views.NoDataFoundText

@Composable
fun Todos() {
    val viewModel: TodosViewModel = getViewModel(Unit, viewModelFactory { TodosViewModel() })
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (uiState.todos.isNotEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(uiState.todos) {
                    Column(modifier = Modifier.wrapContentWidth().wrapContentHeight()) {
                        Text(text = it.title ?: "No Title Found")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = it.body ?: "No Body Found")
                    }
                }
            }
        } else {
            NoDataFoundText()
        }
    }
}