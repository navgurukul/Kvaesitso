package de.mm20.launcher2.ui.settings.homescreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import de.mm20.launcher2.ui.R
import de.mm20.launcher2.ui.component.preferences.Preference
import de.mm20.launcher2.ui.component.preferences.PreferenceCategory
import de.mm20.launcher2.ui.component.preferences.PreferenceScreen
import de.mm20.launcher2.ui.component.preferences.SwitchPreference
import de.mm20.launcher2.ui.launcher.widgets.clock.ConfigureClockWidgetSheet

@Composable
fun HomescreenSettingsScreen() {
    val viewModel: HomescreenSettingsScreenVM = viewModel(factory = HomescreenSettingsScreenVM.Factory)

    val context = LocalContext.current

    val dock by viewModel.dock.collectAsStateWithLifecycle(null)
    val editButton by viewModel.widgetEditButton.collectAsStateWithLifecycle(null)

    PreferenceScreen(title = stringResource(id = R.string.preference_screen_homescreen)) {
        item {
            PreferenceCategory(stringResource(R.string.preference_clockwidget_favorites_part)) {
                AnimatedVisibility(visible = dock == null) {
                    SwitchPreference(
                        title = stringResource(R.string.preference_clockwidget_favorites_part),
                        summary = stringResource(R.string.preference_clockwidget_favorites_part_summary),
                        value = dock == true,
                        onValueChanged = {
                            viewModel.setDock(it)
                        },
                    )
                }
            }
        }

        item {
            PreferenceCategory(
                title = stringResource(id = R.string.preference_category_widgets)
            ) {
                Preference(
                    title = stringResource(R.string.preference_screen_clockwidget),
                    summary = stringResource(R.string.preference_screen_clockwidget_summary),
                    onClick = {
                        viewModel.showClockWidgetSheet = true
                    }
                )
                SwitchPreference(
                    title = stringResource(id = R.string.preference_edit_button),
                    summary = stringResource(id = R.string.preference_widgets_edit_button_summary),
                    value = editButton == true,
                    onValueChanged = {
                        viewModel.setWidgetEditButton(it)
                    }
                )
            }
        }
    }

    if (viewModel.showClockWidgetSheet) {
        ConfigureClockWidgetSheet(onDismiss = { viewModel.showClockWidgetSheet = false })
    }
}
