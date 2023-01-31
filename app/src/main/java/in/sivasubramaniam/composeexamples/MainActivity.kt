package `in`.sivasubramaniam.composeexamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import `in`.sivasubramaniam.composeexamples.ui.theme.ComposeExamplesTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExamplesTheme {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}