package com.bnyro.translate.ui.views

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bnyro.translate.BuildConfig
import com.bnyro.translate.R
import com.bnyro.translate.models.NavigationModel
import com.bnyro.translate.obj.AboutIcon
import com.bnyro.translate.ui.components.RoundIconButton
import com.bnyro.translate.ui.components.StyledIconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutPage(
    viewModel: NavigationModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier
            .statusBarsPadding(),
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    StyledIconButton(
                        imageVector = Icons.Rounded.ArrowBack
                    ) {
                        viewModel.showAbout = false
                    }
                },
                actions = {
                    StyledIconButton(
                        imageVector = Icons.Rounded.Settings
                    ) {
                        viewModel.showOptions = true
                    }
                }
            )
        },
        content = {
            Column {
                Spacer(modifier = Modifier.height(it.calculateTopPadding()))

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    item {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                modifier = Modifier.size(150.dp),
                                painter = painterResource(R.mipmap.ic_launcher_round),
                                contentDescription = stringResource(R.string.app_name)
                            )
                            Spacer(modifier = Modifier.height(48.dp))
                            BadgedBox(
                                badge = {
                                    Badge(
                                        modifier = Modifier.animateContentSize(tween(800)),
                                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                                        contentColor = MaterialTheme.colorScheme.tertiary
                                    ) {
                                        Text(
                                            text = BuildConfig.VERSION_CODE.toString()
                                        )
                                    }
                                }
                            ) {
                                Text(
                                    text = stringResource(R.string.app_name),
                                    style = MaterialTheme.typography.displaySmall
                                )
                            }
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(48.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            aboutIcons.forEach {
                                RoundIconButton(
                                    backgroundColor = MaterialTheme.colorScheme.onTertiary,
                                    contentDescription = it.contentDescription,
                                    iconResourceId = it.iconResourceId
                                ) {
                                    context.startActivity(
                                        Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse(it.href)
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

val aboutIcons = listOf(
    AboutIcon(
        R.string.github,
        R.drawable.ic_github,
        "https://github.com/Bnyro/TranslateYou"
    ),
    AboutIcon(
        R.string.author,
        R.drawable.ic_author,
        "https://bnyro.github.io"
    ),
    AboutIcon(
        R.string.license,
        R.drawable.ic_license,
        "https://gnu.org/licenses/gpl-3.ß.html"
    )
)