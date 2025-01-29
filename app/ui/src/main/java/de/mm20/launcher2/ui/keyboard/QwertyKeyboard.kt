package de.mm20.launcher2.ui.keyboard

import android.content.Intent
import android.provider.Settings
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.HelpOutline
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Wallpaper
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.mm20.launcher2.ktx.tryStartActivity
import de.mm20.launcher2.preferences.ClockWidgetColors
import de.mm20.launcher2.ui.R
import de.mm20.launcher2.ui.launcher.LauncherScaffoldVM
import de.mm20.launcher2.ui.launcher.search.SearchVM
import de.mm20.launcher2.ui.locals.LocalPreferDarkContentOverWallpaper
import de.mm20.launcher2.ui.settings.SettingsActivity
import kotlinx.coroutines.CoroutineScope
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
        MenuKey(modifier = Modifier.weight(1f))
        letters.forEach { letter ->
            KeyboardKey(letter = letter, onKeyPress = onKeyPress, enabled = true, modifier = Modifier.weight(1f))
        }
//        MicrophoneKey(onSpeechInput = onKeyPress, modifier = Modifier.weight(1f))
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
    val launcherVM: LauncherScaffoldVM = viewModel()
    val color = launcherVM.color.collectAsState()

    val darkColors =
        color.value == ClockWidgetColors.Auto && LocalPreferDarkContentOverWallpaper.current || color.value == ClockWidgetColors.Dark

    val contentColors =
        if (darkColors) {
            Color(0, 0, 0, 180)
        } else {
            Color.White
        }

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
            color = contentColors,

            fontStyle = FontStyle.Normal
        )
    }
}

@Composable
fun MicrophoneKey(onSpeechInput: (String)-> Unit, modifier: Modifier) {
    val launcherVM: LauncherScaffoldVM = viewModel()
    val color = launcherVM.color.collectAsState()

    val darkColors =
        color.value == ClockWidgetColors.Auto && LocalPreferDarkContentOverWallpaper.current || color.value == ClockWidgetColors.Dark

    val contentColors =
        if (darkColors) {
            Color(0, 0, 0, 180)
        } else {
            Color.White
        }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(40.dp)
            .clickable { onSpeechInput("") }
            .padding(2.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_mic_24),
            contentDescription = "Microphone",
            tint = contentColors
        )
    }
}

@Composable
fun MenuKey(
    modifier: Modifier,
) {
    val context = LocalContext.current
    var showOverflowMenu by remember { mutableStateOf(false) }
    val launcherVM: LauncherScaffoldVM = viewModel()
    val color = launcherVM.color.collectAsState()

    val darkColors =
        color.value == ClockWidgetColors.Auto && LocalPreferDarkContentOverWallpaper.current || color.value == ClockWidgetColors.Dark

    val contentColors =
        if (darkColors) {
            Color(0, 0, 0, 180)
        } else {
            Color.White
        }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(40.dp)
            .clickable { showOverflowMenu = true }
            .padding(1.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_more_vert_24),
            contentDescription = "Menu",
            tint = contentColors
        )
    }

    DropdownMenu(expanded = showOverflowMenu, onDismissRequest = { showOverflowMenu = false }) {
        DropdownMenuItem(
            onClick = {
                context.startActivity(
                    Intent.createChooser(
                        Intent(Intent.ACTION_SET_WALLPAPER),
                        null
                    )
                )
                showOverflowMenu = false
            },
            text = {
                Text(stringResource(R.string.wallpaper))
            },
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Wallpaper, contentDescription = null)
            }
        )
        DropdownMenuItem(
            onClick = {
                launcherVM.setWidgetEditMode(editMode = true)
                showOverflowMenu = false
            },
            text = {
                Text(stringResource(R.string.menu_edit_widgets))
            },
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Edit, contentDescription = null)
            }
        )
        DropdownMenuItem(
            onClick = {
                context.startActivity(Intent(context, SettingsActivity::class.java))
                showOverflowMenu = false
            },
            text = {
                Text(stringResource(R.string.settings))
            },
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Settings, contentDescription = null)
            }
        )
        DropdownMenuItem(
            text = {
                Text(stringResource(R.string.set_as_default_laucher))
            },
            onClick = {
                context.tryStartActivity(Intent(Settings.ACTION_HOME_SETTINGS))
            },
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.HelpOutline, contentDescription = null)
            }
        )
    }
}


