package de.mm20.launcher2.ui.launcher.widgets.clock

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import de.mm20.launcher2.preferences.ClockWidgetAlignment
import de.mm20.launcher2.preferences.ClockWidgetColors
import de.mm20.launcher2.preferences.ClockWidgetStyle
import de.mm20.launcher2.preferences.ui.ClockWidgetSettings
import de.mm20.launcher2.ui.R
import de.mm20.launcher2.ui.base.LocalTime
import de.mm20.launcher2.ui.locals.LocalPreferDarkContentOverWallpaper

@Composable
fun CustomWidget(
    modifier: Modifier = Modifier,
    fillScreenHeight: Boolean,
    editMode: Boolean = false,
) {
    val viewModel: ClockWidgetVM = viewModel()
    val context = LocalContext.current
    val compact by viewModel.compactLayout.collectAsState()
    val clockStyle by viewModel.customStyle.collectAsState()
    val color by viewModel.color.collectAsState()
    val alignment by viewModel.alignment.collectAsState()
    val time = LocalTime.current

    val darkColors =
        color == ClockWidgetColors.Auto && LocalPreferDarkContentOverWallpaper.current || color == ClockWidgetColors.Dark

    val contentColor =
        if (darkColors) {
            Color(0, 0, 0, 180)
        } else {
            Color.White
        }

    LaunchedEffect(time) {
        viewModel.updateTime(time)
    }

    val partProvider by remember { viewModel.getActivePart(context) }.collectAsStateWithLifecycle(
        null
    )

    AnimatedContent(false, label = "ClockWidget")
    {
        if (it) {
            var configure by remember { mutableStateOf(false) }
            var customConfigure by remember { mutableStateOf(false) }

            Column {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shadowElevation = 2.dp,
                    tonalElevation = 2.dp,
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.size(4.dp))
                            Text(
                                text = stringResource(id = R.string.preference_screen_clockwidget),
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 8.dp),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                            IconButton(onClick = {
                                configure = true
                            }) {
                                Icon(
                                    imageVector = Icons.Rounded.Tune,
                                    contentDescription = stringResource(R.string.settings)
                                )
                            }
                        }
                        Row(
                            modifier = Modifier.padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.size(4.dp))
                            Text(
                                text = stringResource(id = R.string.preference_screen_clockwidget2),
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 8.dp),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                            IconButton(onClick = {
                                configure = true
                            }) {
                                Icon(
                                    imageVector = Icons.Rounded.Settings,
                                    contentDescription = stringResource(R.string.settings)
                                )
                            }
                        }
                    }

                }
                HorizontalDivider()
                if (configure) {
                    ConfigureClockWidgetSheet(
                        onDismiss = { configure = false },
                    )
                }
            }
        } else {
            Column(modifier = modifier) {
                Box(
                    modifier = Modifier
                        .then(if (fillScreenHeight) Modifier.weight(1f) else Modifier)
                        .fillMaxWidth()
                        .padding(horizontal = if (compact == true) 0.dp else 24.dp),
                    contentAlignment = when (alignment) {
                        ClockWidgetAlignment.Center -> Alignment.Center
                        ClockWidgetAlignment.Top -> Alignment.TopCenter
                        else -> Alignment.BottomCenter
                    }
                ) {
                    CompositionLocalProvider(
                        LocalContentColor provides contentColor
                    ) {
                        if (compact == false) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Box(
                                    modifier = Modifier.clickable(
                                        enabled = clockStyle !is ClockWidgetStyle.Custom,
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() }
                                    ) {
                                        viewModel.launchClockApp(context)
                                    }
                                ) {
                                    Clock(clockStyle, false, darkColors)
                                }
                            }
                        }
                        if (compact == true) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 8.dp, bottom = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Box(
                                    modifier = Modifier.clickable(
                                        enabled = clockStyle !is ClockWidgetStyle.Custom,
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() }
                                    ) {
                                        viewModel.launchClockApp(context)
                                    }
                                ) {
                                    Clock(clockStyle, true, darkColors)
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}