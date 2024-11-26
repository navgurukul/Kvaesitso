package de.mm20.launcher2.ui.launcher.searchbar

import android.content.Intent
import android.content.pm.LauncherApps
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.HelpOutline
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Wallpaper
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.getSystemService
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import de.mm20.launcher2.ktx.tryStartActivity
import de.mm20.launcher2.permissions.PermissionGroup
import de.mm20.launcher2.permissions.PermissionsManager
import de.mm20.launcher2.ui.R
import de.mm20.launcher2.ui.component.MissingPermissionBanner
import de.mm20.launcher2.ui.launcher.LauncherScaffoldVM
import de.mm20.launcher2.ui.launcher.widgets.WidgetsVM
import de.mm20.launcher2.ui.settings.SettingsActivity
import de.mm20.launcher2.ui.settings.search.SearchSettingsScreenVM
import org.koin.androidx.compose.inject
import org.koin.core.component.inject

@Composable
fun RowScope.SearchBarMenu(
    searchBarValue: String,
    onInputClear: () -> Unit,
) {
    val viewModel: SearchSettingsScreenVM = viewModel()
    val context = LocalContext.current
    var showOverflowMenu by remember { mutableStateOf(false) }
    val rightIcon = AnimatedImageVector.animatedVectorResource(R.drawable.anim_ic_menu_clear)
    val launcherVM: LauncherScaffoldVM = viewModel()
    val widgetsVM: WidgetsVM = viewModel()
    val permissionsManager: PermissionsManager by inject()

    IconButton(onClick = {
        if (searchBarValue.isNotBlank()) onInputClear()
        else showOverflowMenu = true
    }) {
        Icon(
            painter = rememberAnimatedVectorPainter(
                rightIcon,
                atEnd = searchBarValue.isNotEmpty()
            ),
            contentDescription = stringResource(if (searchBarValue.isNotBlank()) R.string.action_clear else R.string.action_more_actions),
            tint = LocalContentColor.current
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

        //  *** REMOVED EDIT WIDGET FROM SEARCH BAR MENU ***
        val editButton by widgetsVM.editButton.collectAsState()
        val searchOpen by launcherVM.isSearchOpen
        if (!searchOpen && editButton == true) {
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
        }
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
        val colorScheme = MaterialTheme.colorScheme
        DropdownMenuItem(
            text = {
                Text(stringResource(R.string.set_as_default_laucher))
                   },
            onClick = {
                context.tryStartActivity(Intent(Settings.ACTION_HOME_SETTINGS))
//                viewModel.requestAppShortcutsPermission(context as AppCompatActivity)

            },
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.HelpOutline, contentDescription = null)
            }

        )
    }
}