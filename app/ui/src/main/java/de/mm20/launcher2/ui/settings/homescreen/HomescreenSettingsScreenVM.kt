package de.mm20.launcher2.ui.settings.homescreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import de.mm20.launcher2.preferences.ui.ClockWidgetSettings
import de.mm20.launcher2.preferences.ui.UiSettings
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class HomescreenSettingsScreenVM(
    private val uiSettings: UiSettings,
    private val clockWidgetSettings: ClockWidgetSettings,
) : ViewModel() {

    var showClockWidgetSheet by mutableStateOf(false)

    val dock = clockWidgetSettings.dock
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    fun setDock(dock: Boolean) {
        viewModelScope.launch {
            clockWidgetSettings.setDock(dock)
        }
    }

    val widgetEditButton = uiSettings.widgetEditButton
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    fun setWidgetEditButton(editButton: Boolean) {
        viewModelScope.launch {
            uiSettings.setWidgetEditButton(editButton)
        }
    }

    companion object : KoinComponent {
        val Factory = viewModelFactory {
            initializer {
                HomescreenSettingsScreenVM(
                    uiSettings = get(),
                    clockWidgetSettings = get(),
                )
            }
        }
    }
}