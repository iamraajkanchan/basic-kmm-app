import androidx.compose.runtime.Composable

actual fun getPlatformName(): String = "Android"

@Composable fun MainView(callback: () -> Unit) = App(callback)
@Composable fun TodosView() = Todos()
