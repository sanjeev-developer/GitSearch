package com.git.gitsearch

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.git.gitsearch.ui.MainActivity
import com.git.gitsearch.ui.SetupNavGraph
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

//    @get:Rule
//    val hiltRule = HiltAndroidRule(this)
//
//    @get:Rule
//    val composeTestRule = createAndroidComposeRule<MainActivity>()
//    @get:Rule
//    val composeTestRule = createComposeRule()
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

//    @Before
//    fun setUp() {
//        composeTestRule.setContent {
//           // hiltRule.inject()
//            SetupNavGraph()
//        }
//    }

    @Test
    fun testSetupUi() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val et_input_string = context.getString(R.string.enter_user_hint)
        val bt_search_string = context.getString(R.string.search)

        // Verify presence of UI elements
        composeTestRule.onNodeWithText(et_input_string).performTextInput("octocat")
        // Wait for the button to render
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("search_button").performClick()
        Thread.sleep(10000)
       // composeTestRule.onNodeWithContentDescription("user_image").assertExists()
    }
}
