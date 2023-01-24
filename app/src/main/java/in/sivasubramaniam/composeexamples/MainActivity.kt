package `in`.sivasubramaniam.composeexamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import `in`.sivasubramaniam.composeexamples.ui.theme.ComposeExamplesTheme

class MainActivity : ComponentActivity() {
    private val years = 1900..2100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExamplesTheme {
                val state = rememberLazyGridState(
                    initialFirstVisibleItemIndex = 2023 - 1900
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    content = {
                        items(years.toList()) { year ->
                            TextButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(text = "$year")
                            }
                        }
                    },
                    state = state
                )
            }
        }
    }
}