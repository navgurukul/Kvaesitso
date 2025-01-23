package de.mm20.launcher2.ui.launcher.search.common.grid

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.mm20.launcher2.search.SavableSearchable
import de.mm20.launcher2.ui.layout.BottomReversed
import de.mm20.launcher2.ui.locals.LocalGridSettings
import kotlin.math.ceil

@Composable
fun SearchResultGrid(
    items: List<SavableSearchable>,
    modifier: Modifier = Modifier,
    showLabels: Boolean = LocalGridSettings.current.showLabels,
    columns: Int = LocalGridSettings.current.columnCount,
    reverse: Boolean = false,
    highlightedItem: SavableSearchable? = null,
    transitionKey: Any? = items
) {
    AnimatedContent(
        targetState = items to transitionKey,
        modifier = modifier
            .wrapContentSize() // Ensure parent fills the screen and takes available space
            .padding(4.dp),
        transitionSpec = {
            fadeIn() togetherWith fadeOut()
        },
        contentKey = { it.second }
    ) { (items, _) ->
        Column(
            verticalArrangement = Arrangement.Bottom
        ) {
            val rows = ceil(items.size / columns.toFloat()).toInt()
            for (i in 0 until rows) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                ) {
                    for (j in 0 until columns) {
                        val item = items.getOrNull(i * columns + j)
                        if (item != null) {
                            Box(
                                modifier = Modifier
                                    .weight(1f) // Ensure each item gets equal space
                                    .wrapContentSize()
                                    .padding(4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                key(item.key) {
                                    GridItem(
                                        modifier = Modifier.wrapContentSize(),
                                        item = item,
                                        showLabels = showLabels,
                                        highlight = item.key == highlightedItem?.key
                                    )
                                }
                            }
                        } else {
                            Spacer(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
