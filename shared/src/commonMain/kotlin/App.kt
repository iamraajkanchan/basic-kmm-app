import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import viewmodel.BirdViewModel
import views.BirdCard

@Composable
fun App() {
    MaterialTheme {
        val viewModel: BirdViewModel = getViewModel(Unit, viewModelFactory { BirdViewModel() })
        BirdCard(viewModel) { /*callback.invoke()*/ }
    }
}

expect fun getPlatformName(): String