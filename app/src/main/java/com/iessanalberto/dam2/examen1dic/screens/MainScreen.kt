package com.iessanalberto.dam2.examen1dic.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iessanalberto.dam2.examen1dic.data.allEvents
import com.iessanalberto.dam2.examen1dic.data.listadoPersonas
import com.iessanalberto.dam2.examen1dic.models.EventCard
import com.iessanalberto.dam2.examen1dic.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Eventos") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            BottomAppBar (
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
                actions = {
                    Text(text = "Gestión eventos")
                    IconButton(onClick = {
                        navController.navigate(AppScreens.BorrarScreen.route)
                    }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Delete")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(AppScreens.NewEventScreen.route) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "New event")
            }
        }
    ) { paddingValues ->
        Column(){
            MainScreenBodyContent(modifier = Modifier.padding(paddingValues))
        }

    }
}
@Composable
fun MainScreenBodyContent(modifier: Modifier) {

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        items(allEvents){
                event ->
            EventCard(evento = event)
        }
    }

}



/*
* @Composable
fun WordleGame() {
    var targetWord by remember { mutableStateOf("HELLO") }
    var currentGuess by remember { mutableStateOf("_____") }
    var remainingAttempts by remember { mutableStateOf(5) }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Guess the Word!")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Word to guess: $targetWord")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Current Guess: $currentGuess")
        Spacer(modifier = Modifier.height(16.dp))
        BasicTextField(
            value = currentGuess,
            onValueChange = {
                currentGuess = it.take(targetWord.length)
            },
            textStyle = MaterialTheme.typography.h6.copy(color = Color.Black),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(8.dp)
                .clip(MaterialTheme.shapes.medium)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (currentGuess == targetWord) {
                message = "Congratulations! You guessed the word."
            } else {
                remainingAttempts--
                if (remainingAttempts == 0) {
                    message = "Sorry! You ran out of attempts. The correct word was $targetWord."
                }
            }
        }) {
            Text("Check Guess")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(message)
    }
}
* */

/*
* enum class Player { X, O, Empty }

@Composable
fun TicTacToeGame() {
    var board by remember { mutableStateOf(List(3) { List(3) { Player.Empty } }) }
    var currentPlayer by remember { mutableStateOf(Player.X) }
    var winner by remember { mutableStateOf<Player?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Tic Tac Toe", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Grid(board) { row, col ->
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        if (board[row][col] == Player.Empty && winner == null) {
                            board = board.mapIndexed { i, r ->
                                if (i == row) {
                                    r.mapIndexed { j, c ->
                                        if (j == col) currentPlayer else c
                                    }
                                } else {
                                    r
                                }
                            }
                            checkWinner()
                            currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
                        }
                    }
                    .background(MaterialTheme.colorScheme.primary, shape = RectangleShape)
                    .padding(4.dp)
                    .clip(RectangleShape),
                contentAlignment = Alignment.Center
            ) {
                when (board[row][col]) {
                    Player.X -> Text("X", style = MaterialTheme.typography.h6)
                    Player.O -> Text("O", style = MaterialTheme.typography.h6)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (winner != null) {
            Text("Player ${winner.name} wins!", style = MaterialTheme.typography.h6)
        } else {
            Text("Current Player: ${currentPlayer.name}", style = MaterialTheme.typography.h6)
        }
    }
}

@Composable
fun Grid(board: List<List<Player>>, onCellClick: (row: Int, col: Int) -> Unit) {
    for (i in board.indices) {
        Row {
            for (j in board[i].indices) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(MaterialTheme.colorScheme.background, shape = RectangleShape)
                        .padding(4.dp)
                        .clip(RectangleShape)
                        .clickable { onCellClick(i, j) },
                    contentAlignment = Alignment.Center
                ) {
                    when (board[i][j]) {
                        Player.X -> Text("X", style = MaterialTheme.typography.h6)
                        Player.O -> Text("O", style = MaterialTheme.typography.h6)
                    }
                }
            }
        }
    }
}
* */