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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
fun DetailedDrawerContent() {
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
                onClick = { /* Handle click */ }
            )
            NavigationDrawerItem(
                label = { Text("Info") },
                selected = false,
                icon = { Icon(Icons.Outlined.Info, contentDescription = null) },
                onClick = { /* Handle click */ }
            )
            NavigationDrawerItem(
                label = { Text("Email") },
                selected = false,
                icon = { Icon(Icons.Outlined.Email, contentDescription = null) },
                onClick = { /* Handle click */ }
            )
            Spacer(Modifier.height(12.dp))
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SolarImageCard(image: SolImage, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = {  }
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
                            onClick = { expanded = false }
                        )
                        DropdownMenuItem(
                            text = { Text("Eliminar") },
                            onClick = { expanded = false }
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

    ModalNavigationDrawer(
        drawerContent = { DetailedDrawerContent() },
        drawerState = drawerState
    ) {
        Scaffold(
            bottomBar = {
                BottomAppBarWithDrawerControl(
                    onOpenDrawer = {
                        scope.launch { drawerState.open() }
                    }
                )
            },
        ) { innerPadding ->
            LazyVerticalGrid(
                contentPadding = innerPadding,
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(solImages) { image ->
                    SolarImageCard(image = image)
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