package de.mm20.launcher2.ui.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun KeyboardWithBackground() {
    var inputText by remember { mutableStateOf("") }  // State to store typed text

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Input display box
            InputTextBox(inputText = inputText)

            Spacer(modifier = Modifier.height(16.dp))

            // Qwerty keyboard with click listeners
            QwertyKeyboard(
                onKeyPress = { key -> inputText += key },
                onBackspace = {
                    if (inputText.isNotEmpty()) {
                        inputText = inputText.dropLast(1)
                    }
                }
            )
        }
    }
}

@Composable
fun InputTextBox(inputText: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(2.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .background(Color(0xFF1C1C1C), RoundedCornerShape(8.dp))  // Dark background for the input box
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (inputText.isEmpty()) "Enter text..." else inputText,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun QwertyKeyboard(onKeyPress: (Char) -> Unit, onBackspace: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        KeyboardRow(
            letters = listOf('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'),
            modifier = Modifier.fillMaxWidth(),
            onKeyPress = onKeyPress
        )

        KeyboardRow(
            letters = listOf('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'),
            modifier = Modifier.fillMaxWidth(0.9f),
            onKeyPress = onKeyPress
        )

        KeyboardRowWithBackspace(
            letters = listOf('z', 'x', 'c', 'v', 'b', 'n', 'm'),
            modifier = Modifier.fillMaxWidth(0.8f),
            onKeyPress = onKeyPress,
            onBackspace = onBackspace
        )
    }
}

@Composable
fun KeyboardRow(letters: List<Char>, modifier: Modifier, onKeyPress: (Char) -> Unit) {
    Row(
        modifier = modifier
            .padding(horizontal = 4.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        letters.forEach { letter ->
            KeyboardKey(letter = letter, onClick = { onKeyPress(letter) })
        }
    }
}

@Composable
fun KeyboardRowWithBackspace(
    letters: List<Char>,
    modifier: Modifier,
    onKeyPress: (Char) -> Unit,
    onBackspace: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 2.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        letters.forEach { letter ->
            KeyboardKey(letter = letter, onClick = { onKeyPress(letter) })
        }
        BackspaceKey(onClick = onBackspace)
    }
}

@Composable
fun KeyboardKey(letter: Char, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(32.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .background(Color(0x80000000), RoundedCornerShape(8.dp))
            .padding(2.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = letter.toString(),
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal
        )
    }
}

@Composable
fun BackspaceKey(onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(32.dp)
            .padding(2.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = "⌫",
            fontSize = 22.sp,
            color = Color.White,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KeyboardWithBackground()
}
