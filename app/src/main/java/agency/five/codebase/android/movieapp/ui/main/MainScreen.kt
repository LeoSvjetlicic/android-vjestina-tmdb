package agency.five.codebase.android.movieapp.ui.main

import agency.five.codebase.android.movieapp.ui.favorites.FavoritesRoute
import agency.five.codebase.android.movieapp.ui.home.HomeRoute
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsRoute
import agency.five.codebase.android.movieapp.R
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import agency.five.codebase.android.movieapp.navigation.MOVIE_ID_KEY
import agency.five.codebase.android.movieapp.navigation.MovieDetailsDestination
import agency.five.codebase.android.movieapp.navigation.NavigationItem

@SuppressLint("RememberReturnType")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var showBottomBar by remember { mutableStateOf(true) }
    var showBackIcon = !showBottomBar
    Scaffold(
        topBar = {
            TopBar(
                navigationIcon = {
                    if (showBackIcon) {
                        BackIcon(onBackClick = {
                            navController.popBackStack()
                            showBottomBar = !showBottomBar
                        })
                    }
                }
            )
        },
        bottomBar = {
            if (showBottomBar)
                BottomNavigationBar(
                    destinations = listOf(
                        NavigationItem.HomeDestination,
                        NavigationItem.FavoritesDestination,
                    ),
                    onNavigateToDestination = {
                        navController.navigate(it.route) {
                            popUpTo(it.route) {
                                inclusive = true
                            }
                        }
                    },
                    currentDestination = navBackStackEntry?.destination
                )
        }
    ) { padding ->
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationItem.HomeDestination.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(
                    NavigationItem.HomeDestination.route
                ) {
                    HomeRoute(
                        onMovieCardClick = {
                            navController.navigate(
                                MovieDetailsDestination.createNavigationRoute(1)
                            )
                            showBottomBar = !showBottomBar
                        },
                        onFavoriteButtonClick = {}
                    )
                }
                composable(
                    NavigationItem.FavoritesDestination.route
                ) {
                    FavoritesRoute(
                        onMovieCardClick = {
                            navController.navigate(
                                MovieDetailsDestination.createNavigationRoute(1)
                            )
                            showBottomBar = !showBottomBar
                        },
                        onFavoriteButtonClick = {},
                    )
                }
                composable(
                    route = MovieDetailsDestination.route,
                    arguments = listOf(navArgument(MOVIE_ID_KEY) { type = NavType.IntType }),
                ) {
                    MovieDetailsRoute()
                }
            }
        }
    }
}

@Composable
private fun TopBar(
    navigationIcon: @Composable (() -> Unit)? = null,
) {
    AsyncImage(
        model = R.drawable.tmdb_logo,
        contentDescription = null,
        modifier = Modifier
            .background(Color(8, 8, 118))
            .height(50.dp)
            .padding(start = 70.dp, end = 70.dp)
    )
    if (navigationIcon != null) {
        navigationIcon?.invoke()
    }
}

@Composable
private fun BackIcon(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = R.drawable.back,
        contentDescription = null,
        modifier = modifier
            .size(40.dp)
            .padding(top = 12.dp, start = 5.dp)
            .clickable { onBackClick() }
    )
}

@Composable
private fun BottomNavigationBar(
    destinations: List<NavigationItem>,
    onNavigateToDestination: (NavigationItem) -> Unit,
    currentDestination: NavDestination?,
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background
    ) {
        destinations.forEach { destination ->
            AddItem(
                destination = destination,
                onNavigateToDestination = { onNavigateToDestination(destination) },
                currentDestination = currentDestination
            )
        }
    }

}


@Composable
fun RowScope.AddItem(
    destination: NavigationItem,
    onNavigateToDestination: (NavigationItem) -> Unit,
    currentDestination: NavDestination?,
) {
    BottomNavigationItem(
        modifier = Modifier,
        label = {
            Text(
                text = stringResource(id = destination.labelId),
                fontSize = 10.sp
            )
        },
        icon = {
            Image(
                modifier = Modifier.fillMaxHeight(0.25F),
                painter = painterResource(
                    id =
                    if (currentDestination?.hierarchy?.any {
                            it.route == destination.route
                        } == true)
                        destination.selectedIconId
                    else
                        destination.unselectedIconId
                ),
                contentDescription = destination.labelId.toString(),
                contentScale = ContentScale.FillHeight
            )
        },
        selected = currentDestination?.route == destination.route,
        onClick = { onNavigateToDestination(destination) }
    )
}

@Preview
@Composable
private fun MainScreenViewState() {
    MainScreen()
}
