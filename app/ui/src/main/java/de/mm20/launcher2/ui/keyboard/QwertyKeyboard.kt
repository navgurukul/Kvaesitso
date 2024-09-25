package de.mm20.launcher2.ui.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun KeyboardWithBackground() {
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
            QwertyKeyboard()
        }
    }
}

@Composable
fun QwertyKeyboard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        KeyboardRow(
            letters = listOf('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'),
            modifier = Modifier.fillMaxWidth()
        )

        KeyboardRow(
            letters = listOf('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'),
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        KeyboardRowWithBackspace(
            letters = listOf('z', 'x', 'c', 'v', 'b', 'n', 'm'),
            modifier = Modifier.fillMaxWidth(0.8f)
        )
    }
}

@Composable
fun KeyboardRow(letters: List<Char>, modifier: Modifier) {
    Row(
        modifier = modifier
            .padding(horizontal = 4.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        letters.forEach { letter ->
            KeyboardKey(letter = letter)
        }
    }
}

@Composable
fun KeyboardRowWithBackspace(letters: List<Char>, modifier: Modifier) {
    Row(
        modifier = modifier
            .padding(horizontal = 2.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        letters.forEach { letter ->
            KeyboardKey(letter = letter)
        }
        BackspaceKey()
    }
}
@Composable
fun KeyboardKey(letter: Char) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(32.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .background(Color(0x80000000), RoundedCornerShape(8.dp))
            .padding(2.dp)
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
fun BackspaceKey() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(32.dp)
            //.border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            //.background(Color.Transparent, RoundedCornerShape(8.dp))
            .padding(2.dp)
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
