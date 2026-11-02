package com.example.ffnames.data.model

/**
 * Transforms plain text into various stylish Unicode fonts popular in gaming and Free Fire.
 */
object FontTransformer {

    enum class FontStyle(val displayName: String, val sample: String) {
        NORMAL("Normal", "Abc 123"),
        SMALL_CAPS("Small Caps", "ᴀʙᴄ 123"),
        SUPERSCRIPT("Superscript", "ᴬᵇᶜ ¹²³"),
        FRAKTUR_BOLD("Gothic Bold", "𝕬𝖇𝖈 123"),
        FRAKTUR_NORMAL("Gothic", "𝔄𝔟𝔠 123"),
        DOUBLE_STRUCK("Double Struck", "𝔸𝕓𝕔 𝟙𝟚𝟛"),
        BOLD_SERIF("Bold Serif", "𝐀𝐛𝐜 𝟏𝟐𝟑"),
        BOLD_SANS("Bold Sans", "𝗔𝗯𝗰 𝟭𝟮𝟯"),
        ITALIC_SERIF("Italic Serif", "𝐴𝑏𝑐 123"),
        SCRIPT_BOLD("Script / Cursive", "𝓐𝓫𝓬 123"),
        CIRCLED("Bubble / Circled", "Ⓐⓑⓒ ①②③"),
        CIRCLED_BLACK("Black Bubble", "🅐🅑🅒 ➊➋➌"),
        SQUARED("Squared", "🄰🄱🄲 123"),
        SQUARED_BLACK("Black Squared", "🅰🅱🅲 123"),
        MONOSPACE("Monospace", "𝙰𝚋𝚌 𝟷𝟸𝟹"),
        WIDE("Wide Space", "Ａｂｃ １２３"),
        INVERTED("Upside Down", "ɔq∀ Ɛᄅ⇂"),
        STRIKETHROUGH("Strikethrough", "A̶b̶c̶ 1̶2̶3̶"),
        UNDERLINE("Underline", "A̲b̲c̲ 1̲2̲3̲")
    }

    private val SMALL_CAPS_MAP = mapOf(
        'a' to 'ᴀ', 'b' to 'ʙ', 'c' to 'ᴄ', 'd' to 'ᴅ', 'e' to 'ᴇ', 'f' to 'ꜰ',
        'g' to 'ɢ', 'h' to 'ʜ', 'i' to 'ɪ', 'j' to 'ᴊ', 'k' to 'ᴋ', 'l' to 'ʟ',
        'm' to 'ᴍ', 'n' to 'ɴ', 'o' to 'ᴏ', 'p' to 'ᴘ', 'q' to 'ꞯ', 'r' to 'ʀ',
        's' to 'ꜱ', 't' to 'ᴛ', 'u' to 'ᴜ', 'v' to 'ᴠ', 'w' to 'ᴡ', 'x' to 'x',
        'y' to 'ʏ', 'z' to 'ᴢ',
        'A' to 'ᴀ', 'B' to 'ʙ', 'C' to 'ᴄ', 'D' to 'ᴅ', 'E' to 'ᴇ', 'F' to 'ꜰ',
        'G' to 'ɢ', 'H' to 'ʜ', 'I' to 'ɪ', 'J' to 'ᴊ', 'K' to 'ᴋ', 'L' to 'ʟ',
        'M' to 'ᴍ', 'N' to 'ɴ', 'O' to 'ᴏ', 'P' to 'ᴘ', 'Q' to 'ꞯ', 'R' to 'ʀ',
        'S' to 'ꜱ', 'T' to 'ᴛ', 'U' to 'ᴜ', 'V' to 'ᴠ', 'W' to 'ᴡ', 'X' to 'x',
        'Y' to 'ʏ', 'Z' to 'ᴢ'
    )

    private val SUPERSCRIPT_MAP = mapOf(
        'a' to 'ᵃ', 'b' to 'ᵇ', 'c' to 'ᶜ', 'd' to 'ᵈ', 'e' to 'ᵉ', 'f' to 'ᶠ',
        'g' to 'ᵍ', 'h' to 'ʰ', 'i' to 'ⁱ', 'j' to 'ʲ', 'k' to 'ᵏ', 'l' to 'ˡ',
        'm' to 'ᵐ', 'n' to 'ⁿ', 'o' to 'ᵒ', 'p' to 'ᵖ', 'q' to 'ᑫ', 'r' to 'ʳ',
        's' to 'ˢ', 't' to 'ᵗ', 'u' to 'ᵘ', 'v' to 'ᵛ', 'w' to 'ʷ', 'x' to 'ˣ',
        'y' to 'ʸ', 'z' to 'ᶻ',
        'A' to 'ᴬ', 'B' to 'ᴮ', 'C' to 'ᶜ', 'D' to 'ᴰ', 'E' to 'ᴱ', 'F' to 'ᶠ',
        'G' to 'ᴳ', 'H' to 'ᴴ', 'I' to 'ᴵ', 'J' to 'ᴶ', 'K' to 'ᴷ', 'L' to 'ᴸ',
        'M' to 'ᴹ', 'N' to 'ᴺ', 'O' to 'ᴼ', 'P' to 'ᴾ', 'Q' to 'ᑫ', 'R' to 'ᴿ',
        'S' to 'ˢ', 'T' to 'ᵀ', 'U' to 'ᵁ', 'V' to 'ⱽ', 'W' to 'ᵂ', 'X' to 'ˣ',
        'Y' to 'ʸ', 'Z' to 'ᶻ',
        '0' to '⁰', '1' to '¹', '2' to '²', '3' to '³', '4' to '⁴',
        '5' to '⁵', '6' to '⁶', '7' to '⁷', '8' to '⁸', '9' to '⁹'
    )

    fun transform(text: String, style: FontStyle): String {
        if (text.isEmpty() || style == FontStyle.NORMAL) return text

        return when (style) {
            FontStyle.NORMAL -> text
            FontStyle.SMALL_CAPS -> text.map { SMALL_CAPS_MAP[it] ?: it }.joinToString("")
            FontStyle.SUPERSCRIPT -> text.map { SUPERSCRIPT_MAP[it] ?: it }.joinToString("")
            FontStyle.FRAKTUR_BOLD -> shiftMath(text, 0x1D56C, 0x1D586, 0x1D7CE)
            FontStyle.FRAKTUR_NORMAL -> shiftMath(text, 0x1D504, 0x1D51E, 0)
            FontStyle.DOUBLE_STRUCK -> shiftMath(text, 0x1D538, 0x1D552, 0x1D7D8)
            FontStyle.BOLD_SERIF -> shiftMath(text, 0x1D400, 0x1D41A, 0x1D7CE)
            FontStyle.BOLD_SANS -> shiftMath(text, 0x1D5D4, 0x1D5EE, 0x1D7EC)
            FontStyle.ITALIC_SERIF -> shiftMath(text, 0x1D434, 0x1D44E, 0)
            FontStyle.SCRIPT_BOLD -> shiftMath(text, 0x1D4D0, 0x1D4EA, 0)
            FontStyle.CIRCLED -> shiftCircled(text)
            FontStyle.CIRCLED_BLACK -> shiftCircledBlack(text)
            FontStyle.SQUARED -> shiftSquared(text)
            FontStyle.SQUARED_BLACK -> shiftSquaredBlack(text)
            FontStyle.MONOSPACE -> shiftMath(text, 0x1D670, 0x1D68A, 0x1D7F6)
            FontStyle.WIDE -> shiftWide(text)
            FontStyle.INVERTED -> flipText(text)
            FontStyle.STRIKETHROUGH -> text.map { "$it\u0336" }.joinToString("")
            FontStyle.UNDERLINE -> text.map { "$it\u0332" }.joinToString("")
        }
    }

    private fun shiftMath(text: String, upperBase: Int, lowerBase: Int, digitBase: Int): String {
        val sb = StringBuilder()
        for (ch in text) {
            when {
                ch in 'A'..'Z' && upperBase > 0 -> {
                    val codePoint = upperBase + (ch - 'A')
                    sb.append(String(Character.toChars(codePoint)))
                }
                ch in 'a'..'z' && lowerBase > 0 -> {
                    val codePoint = lowerBase + (ch - 'a')
                    sb.append(String(Character.toChars(codePoint)))
                }
                ch in '0'..'9' && digitBase > 0 -> {
                    val codePoint = digitBase + (ch - '0')
                    sb.append(String(Character.toChars(codePoint)))
                }
                else -> sb.append(ch)
            }
        }
        return sb.toString()
    }

    private fun shiftCircled(text: String): String {
        val sb = StringBuilder()
        for (ch in text) {
            when {
                ch in 'A'..'Z' -> sb.append(String(Character.toChars(0x24B6 + (ch - 'A'))))
                ch in 'a'..'z' -> sb.append(String(Character.toChars(0x24D0 + (ch - 'a'))))
                ch in '1'..'9' -> sb.append(String(Character.toChars(0x2460 + (ch - '1'))))
                ch == '0' -> sb.append('⓪')
                else -> sb.append(ch)
            }
        }
        return sb.toString()
    }

    private fun shiftCircledBlack(text: String): String {
        val sb = StringBuilder()
        for (ch in text) {
            when {
                ch in 'A'..'Z' -> sb.append(String(Character.toChars(0x1F150 + (ch - 'A'))))
                ch in 'a'..'z' -> sb.append(String(Character.toChars(0x1F150 + (ch - 'a'))))
                ch in '1'..'9' -> sb.append(String(Character.toChars(0x2776 + (ch - '1'))))
                else -> sb.append(ch)
            }
        }
        return sb.toString()
    }

    private fun shiftSquared(text: String): String {
        val sb = StringBuilder()
        for (ch in text) {
            when {
                ch in 'A'..'Z' -> sb.append(String(Character.toChars(0x1F130 + (ch - 'A'))))
                ch in 'a'..'z' -> sb.append(String(Character.toChars(0x1F130 + (ch - 'a'))))
                else -> sb.append(ch)
            }
        }
        return sb.toString()
    }

    private fun shiftSquaredBlack(text: String): String {
        val sb = StringBuilder()
        for (ch in text) {
            when {
                ch in 'A'..'Z' -> sb.append(String(Character.toChars(0x1F170 + (ch - 'A'))))
                ch in 'a'..'z' -> sb.append(String(Character.toChars(0x1F170 + (ch - 'a'))))
                else -> sb.append(ch)
            }
        }
        return sb.toString()
    }

    private fun shiftWide(text: String): String {
        val sb = StringBuilder()
        for (ch in text) {
            when (ch) {
                ' ' -> sb.append("　")
                in '!'..'~' -> sb.append((ch.code + 0xFEE0).toChar())
                else -> sb.append(ch)
            }
        }
        return sb.toString()
    }

    private fun flipText(text: String): String {
        val flipMap = mapOf(
            'a' to 'ɐ', 'b' to 'q', 'c' to 'ɔ', 'd' to 'p', 'e' to 'ǝ', 'f' to 'ɟ',
            'g' to 'ƃ', 'h' to 'ɥ', 'i' to 'ᴉ', 'j' to 'ɾ', 'k' to 'ʞ', 'l' to 'l',
            'm' to 'ɯ', 'n' to 'u', 'o' to 'o', 'p' to 'd', 'q' to 'b', 'r' to 'ɹ',
            's' to 's', 't' to 'ʇ', 'u' to 'n', 'v' to 'ʌ', 'w' to 'ʍ', 'x' to 'x',
            'y' to 'ʎ', 'z' to 'z',
            'A' to '∀', 'B' to 'ᗺ', 'C' to 'Ɔ', 'D' to 'ᗡ', 'E' to 'Ǝ', 'F' to 'Ⅎ',
            'G' to '⅁', 'H' to 'H', 'I' to 'I', 'J' to 'ſ', 'K' to 'ʞ', 'L' to '˥',
            'M' to 'W', 'N' to 'N', 'O' to 'O', 'P' to 'Ԁ', 'Q' to 'Ό', 'R' to 'ᴚ',
            'S' to 'S', 'T' to '┴', 'U' to '∩', 'V' to 'Λ', 'W' to 'M', 'X' to 'X',
            'Y' to '⅄', 'Z' to 'Z',
            '1' to '⇂', '2' to 'ᄅ', '3' to 'Ɛ', '4' to 'ㄣ', '5' to 'ϛ',
            '6' to '9', '7' to 'ㄥ', '8' to '8', '9' to '6', '0' to '0'
        )
        return text.reversed().map { flipMap[it] ?: it }.joinToString("")
    }
}
