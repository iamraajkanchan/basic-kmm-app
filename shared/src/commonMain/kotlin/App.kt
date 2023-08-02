import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.JsonConvertException
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import model.BirdImageResponse
import org.jetbrains.compose.resources.ExperimentalResourceApi

@Composable
fun App() {
    MaterialTheme {
        val scope = rememberCoroutineScope()
        var greetingText by remember { mutableStateOf("Hello, World!") }
        var showImage by remember { mutableStateOf(false) }
        LaunchedEffect(Unit) {
            scope.launch {
                println(getImages())
            }
        }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                greetingText = "Hello, ${getPlatformName()}"
                showImage = !showImage
            }) {
                Text(greetingText)
            }
            AnimatedVisibility(showImage) {
                KamelImage(
                    asyncPainterResource(data = "https://sebastianaigner.github.io/demo-image-api/pigeon/vladislav-nikonov-yVYaUSwkTOs-unsplash.jpg"),
                    contentDescription = "Pigeon"
                )
            }
        }
    }
}

val httpClient = HttpClient {
    install(ContentNegotiation) {
        json()
    }
}

suspend fun getImages(): List<BirdImageResponse>? {
    return try {
        httpClient.get("https://sebastianaigner.github.io/demo-image-api/pictures.json")
            .body()
    } catch (e: JsonConvertException) {
        e.printStackTrace()
        null
    }
}

expect fun getPlatformName(): String