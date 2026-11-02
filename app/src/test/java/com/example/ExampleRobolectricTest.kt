package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.ffnames.data.model.FontTransformer
import com.example.ffnames.data.model.NameStyle
import com.example.ffnames.data.model.PreloadedStyles
import com.example.ffnames.data.model.SymbolLibrary
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun testAppNameString() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("FF Stylish Name World", appName)
    }

    @Test
    fun testStarterStylesAreLoaded() {
        val styles = PreloadedStyles.list
        assertTrue("Starter styles must have at least 12 styles", styles.size >= 12)

        // Verify key starter styles from user's starter package
        val royalCrown = styles.find { it.title == "Royal Crown" }
        assertNotNull(royalCrown)
        assertEquals("♛么Siddik么♛", royalCrown?.formatName("Siddik"))

        val shadow = styles.find { it.title == "Shadow" }
        assertNotNull(shadow)
        assertEquals("乂Siddik乂", shadow?.formatName("Siddik"))

        val proPlayer = styles.find { it.title == "Pro Player" }
        assertNotNull(proPlayer)
        assertEquals("亗Siddik亗", proPlayer?.formatName("Siddik"))
    }

    @Test
    fun testFontTransformerSmallCaps() {
        val input = "Siddik"
        val transformed = FontTransformer.transform(input, FontTransformer.FontStyle.SMALL_CAPS)
        assertEquals("ꜱɪᴅᴅɪᴋ", transformed)
    }

    @Test
    fun testFontTransformerSuperscript() {
        val input = "Siddik"
        val transformed = FontTransformer.transform(input, FontTransformer.FontStyle.SUPERSCRIPT)
        assertEquals("ˢⁱᵈᵈⁱᵏ", transformed)
    }

    @Test
    fun testTemplateNameStyle() {
        val style = NameStyle(
            id = "custom_1",
            title = "Test Template",
            category = "Royal",
            template = "꧁༒☬{name}☬༒꧂"
        )
        val result = style.formatName("Raistar")
        assertEquals("꧁༒☬Raistar☬༒꧂", result)
    }

    @Test
    fun testHangulInvisibleSpacePresence() {
        assertEquals("\u3164", SymbolLibrary.INVISIBLE_SPACE)
        assertTrue(SymbolLibrary.categories.any { it.id == "spacers" })
        assertTrue(SymbolLibrary.categories.any { it.id == "ff_badges" })
    }
}
