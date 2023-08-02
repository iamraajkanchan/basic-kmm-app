package views

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun NoDataFoundText() {
    Text(
        text = "No Data Found!",
        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
    )
}