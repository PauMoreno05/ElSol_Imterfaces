package com.example.elsol_imterfaces

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.elsol_imterfaces.ui.theme.ElSol_ImterfacesTheme
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.foundation.shape.RoundedCornerShape

enum class Screen {
    Home,
    Info
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ElSol_ImterfacesTheme {
                DrawerAndBottomBarScreen()
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    BottomAppBar() { }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedDrawerContent(onNavigate: (Screen) -> Unit) {
    ModalDrawerSheet {
        Column(
            modifier = Modifier.width(300.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.solsolet),
                contentDescription = "Imagen del menu",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(165.dp)
            )

            Divider()

            NavigationDrawerItem(
                label = { Text("Build") },
                selected = false,
                icon = { Icon(Icons.Outlined.Build, contentDescription = null) },
                onClick = { onNavigate(Screen.Home) }
            )
            NavigationDrawerItem(
                label = { Text("Info") },
                selected = false,
                icon = { Icon(Icons.Outlined.Info, contentDescription = null) },
                onClick = { onNavigate(Screen.Info) }
            )
            NavigationDrawerItem(
                label = { Text("Email") },
                selected = false,
                icon = { Icon(Icons.Outlined.Email, contentDescription = null) },
                onClick = { onNavigate(Screen.Home) }
            )
            Spacer(Modifier.height(12.dp))
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(paddingValues: PaddingValues) {
    var isDownloading by remember { mutableStateOf(false) }
    var downloadProgress by remember { mutableStateOf(0.0f) }
    var showIndicator by remember { mutableStateOf(false) }

    var showDatePicker by remember { mutableStateOf(false) }

    LaunchedEffect(isDownloading) {
        if (isDownloading) {
            showIndicator = true
            downloadProgress = 0.0f

            for (i in 1..20) {
                delay(100)
                downloadProgress = i / 20f
            }

            delay(500)
            showIndicator = false
            isDownloading = false
            downloadProgress = 0.0f
        }
    }

    if (showDatePicker) {
        val dateState = rememberDatePickerState()
        val scope = rememberCoroutineScope()

        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDatePicker = false
                        val selectedDateMillis = dateState.selectedDateMillis
                        if (selectedDateMillis != null) {
                            val selectedDate = Date(selectedDateMillis)
                            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                            scope.launch {
                                println("Fecha seleccionada: ${formatter.format(selectedDate)}")
                            }
                        }
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = dateState)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            onClick = {
                if (!isDownloading) {
                    isDownloading = true
                }
            },
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6A1B9A),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .width(200.dp)
                .padding(bottom = 24.dp)
        ) {
            Text(text = "Download more info")
        }

        if (showIndicator) {
            CircularProgressIndicator(
                progress = { downloadProgress },
                modifier = Modifier.width(64.dp),
                color = Color(0xFF6A1B9A),
                strokeWidth = 6.dp
            )
        }

        Spacer(Modifier.height(if (showIndicator) 32.dp else 96.dp))

        Button(
            onClick = {
                showDatePicker = true
            },
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6A1B9A)
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .width(250.dp)
        ) {
            Text("Visit Planetarium. Select date", color = Color.White)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SolarImageCard(
    image: SolImage,
    onCopy: (SolImage) -> Unit,
    onDelete: (SolImage) -> Unit,
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            onCardClick(image.name)
        }
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(image.id),
                contentDescription = image.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = image.name,
                    modifier = Modifier.weight(1f)
                )


                Box {
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menú de acciones")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Copiar") },
                            onClick = {
                                onCopy(image)
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Eliminar") },
                            onClick = {
                                onDelete(image)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun BottomAppBarWithDrawerControl(onOpenDrawer: () -> Unit) {
    var itemCount by remember { mutableStateOf(0) }

    BottomAppBar(
        actions = {
            IconButton(onClick = onOpenDrawer) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Abrir Menú Lateral")
            }
            IconButton(
                onClick = { itemCount++ }
            ) {
                BadgedBox(
                    badge = {
                        if (itemCount > 0) {
                            Badge(
                                containerColor = Color.Gray,
                                contentColor = Color.White
                            ) {
                                Text("$itemCount")
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favoritos",
                    )
                }
            }

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* do something */ },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Add, "Localized description")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerAndBottomBarScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val imagesState = remember { mutableStateListOf(*solImages.toTypedArray()) }
    var currentScreen by remember { mutableStateOf(Screen.Home) }
    val snackbarHostState = remember { SnackbarHostState() }

    val navigateTo: (Screen) -> Unit = { screen ->
        currentScreen = screen
        scope.launch { drawerState.close() }
    }

    val onCopyImage: (SolImage) -> Unit = { image ->
        val newImage = image.copy(
            id = image.id,
            name = image.name
        )
        imagesState.add(newImage)
    }

    val onDeleteImage: (SolImage) -> Unit = { image ->
        imagesState.remove(image)
    }

    val onImageCardClick: (String) -> Unit = { imageName ->
        scope.launch {
            snackbarHostState.showSnackbar(
                message = imageName,
                withDismissAction = false,
            )
        }
    }

    ModalNavigationDrawer(
        drawerContent = { DetailedDrawerContent(onNavigate = navigateTo) },
        drawerState = drawerState
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            bottomBar = {
                BottomAppBarWithDrawerControl(
                    onOpenDrawer = {
                        scope.launch { drawerState.open() }
                    }
                )
            },
        ) { innerPadding ->
            when (currentScreen) {
                Screen.Home -> {
                    LazyVerticalGrid(
                        contentPadding = innerPadding,
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(imagesState, key = { it.name + it.id.toString() + System.identityHashCode(it) }) { image ->
                            SolarImageCard(
                                image = image,
                                onCopy = onCopyImage,
                                onDelete = onDeleteImage,
                                onCardClick = onImageCardClick
                            )
                        }
                    }
                }
                Screen.Info -> {
                    InfoScreen(paddingValues = innerPadding)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ElSol_ImterfacesTheme {
        Greeting("Android")
    }
}