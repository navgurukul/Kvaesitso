package de.mm20.launcher2.ui.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import de.mm20.launcher2.ui.launcher.search.SearchVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun QwertyKeyboard(
    searchVM: SearchVM,
    scope: CoroutineScope = rememberCoroutineScope(),
    onKeyPress : (String) -> Unit = { key ->
        val currentQuery = searchVM.searchQuery.value
        if (key == "") { // Backspace key
            searchVM.searchQuery.value = currentQuery.dropLast(1)
        } else {
            searchVM.searchQuery.value = currentQuery + key
        }

        searchVM.isSearchEmpty.value = searchVM.searchQuery.value.isEmpty()
        searchVM.search(searchVM.searchQuery.value, forceRestart = true)

        scope.launch {
            searchVM.searchService.getAllApps().collect { results ->
                searchVM.appResults.value = results.standardProfileApps
            }
        }
    }
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            KeyboardRow(
                letters = listOf('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'),
                onKeyPress = onKeyPress,
                modifier = Modifier.fillMaxWidth()
            )

            KeyboardRow(
                letters = listOf('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'),
                onKeyPress = onKeyPress,
                modifier = Modifier.fillMaxWidth(0.9f)
            )

            KeyboardRowWithBackspace(
                letters = listOf('z', 'x', 'c', 'v', 'b', 'n', 'm'),
                onKeyPress = onKeyPress,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
        }
    }
}

@Composable
fun KeyboardRow(letters: List<Char>, onKeyPress: (String) -> Unit, modifier: Modifier) {
    Row(
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        letters.forEach { letter ->
            KeyboardKey(letter = letter, onKeyPress = onKeyPress,true, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun KeyboardRowWithBackspace(letters: List<Char>,onKeyPress: (String) -> Unit, modifier: Modifier) {
    Row(
        modifier = modifier
            .padding( vertical = 4.dp, horizontal = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        MenuKey(onKeyPress = onKeyPress, modifier = Modifier.weight(1f))
        letters.forEach { letter ->
            KeyboardKey(letter = letter, onKeyPress = onKeyPress, enabled = true, modifier = Modifier.weight(1f))
        }
        BackspaceKey(onKeyPress = onKeyPress, modifier = Modifier.weight(1f))
    }
}
@Composable
fun KeyboardKey(letter: Char, onKeyPress: (String) -> Unit, enabled: Boolean = true, modifier: Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(40.dp)
            .border(0.dp, Color.LightGray, RoundedCornerShape(4.dp))
            .background(if (enabled) Color(0x80000000) else Color.Gray, RoundedCornerShape(8.dp))
            .clickable(enabled) { onKeyPress(letter.toString()) } // Trigger key press
            .padding(4.dp)
    ) {
        Text(
            text = letter.toString(),
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal
        )
    }
}


@Composable
fun BackspaceKey(onKeyPress: (String) -> Unit, modifier: Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(40.dp)
            .clickable { onKeyPress("") }
            .padding(2.dp)
    ) {
        Text(
            text = "⌫",
            fontSize = 26.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal
        )
    }
}


@Composable
fun MenuKey(onKeyPress: (String) -> Unit, modifier: Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(40.dp)
            .clickable {

            }
            .padding(1.dp)
            .background(Color.Gray, RoundedCornerShape(8.dp))

    ) {
        Icon(
            imageVector = Icons.Rounded.Settings, // Replace with your icon
            contentDescription = "Device Info",
            modifier = Modifier.size(20.dp)
        )
    }
}

