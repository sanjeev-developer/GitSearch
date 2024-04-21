package com.git.gitsearch

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.git.gitsearch.ui.SetupNavGraph
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ExampleInstrumentedTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            hiltRule.inject()
            SetupNavGraph()
        }
    }

    @Test
    fun testSetupUi() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val et_input_string = context.getString(R.string.enter_user_hint)
        val bt_search_string = context.getString(R.string.enter_user_hint)

        // Verify presence of UI elements
        composeTestRule.onNodeWithText("Search").assertExists()
        composeTestRule.onNodeWithText(et_input_string).performTextInput("octocat")
        composeTestRule.onNodeWithText(bt_search_string).performClick()
        composeTestRule.onNodeWithContentDescription("user_image").assertExists()

        // Check if user image is still visible after clicking search
        composeTestRule.onNodeWithContentDescription("user_image").assertExists()
    }
}
