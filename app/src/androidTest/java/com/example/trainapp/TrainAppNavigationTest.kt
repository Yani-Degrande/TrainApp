package com.example.trainapp
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.trainapp.ui.TrainApp
import com.example.trainapp.ui.navigation.Destinations
import com.example.trainapp.ui.util.TrainNavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TrainAppNavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupTrainAppNavHost() {
        composeTestRule.setContent {
            navController =
                TestNavHostController(LocalContext.current).apply {
                    navigatorProvider.addNavigator(ComposeNavigator())
                }
            TrainApp(navController = navController, navigationType = TrainNavigationType.BOTTOM_NAVIGATION)
        }
    }

    @Test
    fun trainAppNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Destinations.Start.name)
        composeTestRule.onNodeWithStringId(R.string.train_app_title)
    }

    @Test
    fun trainAppNavHost_clickTeams_navigatesToTeamsScreen() {
        // Perform a click on the Teams NavigationBarItem
        composeTestRule.onNodeWithContentDescriptionId(R.string.groups)
            .performClick()
        navController.assertCurrentRouteName(Destinations.Teams.name)
    }

    @Test
    fun trainAppNavHost_clickTrains_navigatesToTeamsScreen() {
        // Perform a click on the Teams NavigationBarItem
        composeTestRule.onNodeWithContentDescriptionId(R.string.trains)
            .performClick()
        navController.assertCurrentRouteName(Destinations.Train.name)
    }

    @Test
    fun trainAppUserClicksOnTrainComponent_navigatesToTrainComponentDetailScreen() {
        // Navigate to the Train Overview screen
        composeTestRule.onNodeWithContentDescriptionId(R.string.trains)
            .performClick()
        navController.assertCurrentRouteName(Destinations.Train.name)

        // Perform a click on the TrainComponent with testTag TrainCard-254
        composeTestRule.onNodeWithTag("TrainCard-265")
            .performClick()

        // Verify that the TrainComponentDetail screen is displayed
        navController.assertCurrentRouteName("Train/{trainId}")

        // Verify that the TrainComponentDetail screen is displaying the correct TrainComponent
        composeTestRule.onNodeWithContentDescription("Type 13")
            .assertExists()
    }
}
