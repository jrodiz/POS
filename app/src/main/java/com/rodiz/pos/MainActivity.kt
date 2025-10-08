package com.rodiz.pos

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodiz.pos.ui.theme.POSTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Toast.makeText(this, "Hola Mariela", Toast.LENGTH_SHORT).show()


        setContent {
            POSTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },

                    floatingActionButton = {
                        SmallFloatingActionButton(
                            onClick = {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        mainViewModel.optionList.joinToString(", "),
                                        withDismissAction = true,
                                        duration = SnackbarDuration.Indefinite
                                    )
                                }
                            }
                        ) {
                            Icon(Icons.Filled.Done, contentDescription = "")
                        }
                    }
                ) { innerPadding ->
                    Container(
                        name = "Rodiz y/o Montoya",
                        modifier = Modifier.padding(innerPadding),
                        optionList = mainViewModel.optionList
                    )
                }
            }
        }
    }
}

@Composable
fun Container(name: String, modifier: Modifier = Modifier, optionList: List<String>, onClick: () -> Unit = {}) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )

        LazyColumn {
            items(optionList) { option ->
                ElevatedCard(
                    modifier = Modifier.fillMaxSize().height(50.dp).padding(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                    onClick = {

                    }
                ) {
                    Text(
                        modifier = Modifier.wrapContentHeight().align(Alignment.CenterHorizontally),
                        text = "Hello $option!"
                    )
                }
            }
        }
    }
}