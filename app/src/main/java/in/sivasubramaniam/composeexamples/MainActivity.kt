package `in`.sivasubramaniam.composeexamples

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.sivasubramaniam.composeexamples.ui.theme.ComposeExamplesTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExamplesTheme {
                ScaffoldExample()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeExamplesTheme {
        ScaffoldExample()
    }
}

@Composable
fun ScaffoldExample() {

    // create a scaffold state, set it to closed by default
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))

    // Create a coroutine scope. Opening of
    // Drawer and SnackBar should happen in
    // background thread without blocking main thread
    val coroutineScope = rememberCoroutineScope()

    // derive context object from LocalContext to be used for making a toast
    val context = LocalContext.current

    // Scaffold Composable
    Scaffold(

        // pass the scaffold state
        scaffoldState = scaffoldState,

        // pass the topBar we created
        topBar = {
            TopBar(
                // when the menu is clicked, open the
                // drawer in coroutine scope
                onMenuClicked = {
                    coroutineScope.launch {
                        // to close use -> scaffoldState.drawerState.close()
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },

        // pass the bottomBar we created
        bottomBar = { BottomBar() },

        // pass the body in content parameter
        content = {
            Body()
        },

        // pass the drawer
        drawerContent = {
            Drawer()
        },

        floatingActionButton = {
            // create a floating action button in
            // floatingActionButton parameter of scaffold
            FloatingActionButton(
                onClick = {
                    // when clicked, open snackBar
                    coroutineScope.launch {
                        when (scaffoldState.snackbarHostState.showSnackbar(
                            // message in the snackBar
                            message = "Snack Bar",
                            actionLabel = "Action",
                            duration = SnackbarDuration.Short
                        )) {
                            SnackbarResult.ActionPerformed -> {
                                // when snackBar action is clicked
                                Toast.makeText(context, "Action Performed", Toast.LENGTH_SHORT).show()
                            }

                            SnackbarResult.Dismissed -> {
                                // do something when
                                // snack bar is dismissed
                                Toast.makeText(context, "SnackBar Dismissed", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }) {
                // simple text inside FAB
                Text(text = "X")
            }
        }
    )
}

@Composable
fun TopBar(onMenuClicked: () -> Unit) {

    // TopAppBar Composable
    TopAppBar(
        // Provide Title
        title = {
            Text(
                text = "Scaffold||GFG",
                color = Color.White
            )
        },
        // Provide the navigation Icon (Icon on the left to toggle drawer)
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",

                // When clicked trigger onClick
                // Callback to trigger drawer open
                modifier = Modifier.clickable(onClick = onMenuClicked),
                tint = Color.White
            )
        },
        // background color of topAppBar
        backgroundColor = Color(0xFF0F9D58),
    )
}

@Composable
fun BottomBar() {
    BottomAppBar(backgroundColor = Color(0xFF0F9D58)) {
        Text(
            text = "Bottom App Bar",
            color = Color.White
        )
    }
}

@Composable
fun Drawer() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        repeat(5) { count ->
            Text(
                text = "Text Item $count",
                modifier = Modifier.padding(8.dp),
                color = Color.Black
            )
        }
    }
}

@Composable
fun Body() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(text = "Body Content", color = Color(0xFF0F9D58))
    }
}