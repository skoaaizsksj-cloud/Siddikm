package com.example.ffnames.data.model

data class SymbolCategory(
    val id: String,
    val name: String,
    val banglaName: String,
    val icon: String,
    val symbols: List<String>
)

object SymbolLibrary {

    // Free Fire invisible space (Hangul Filler U+3164)
    const val INVISIBLE_SPACE = "\u3164"

    val categories = listOf(
        SymbolCategory(
            id = "ff_badges",
            name = "FF Legendary Badges",
            banglaName = "জনপ্রিয় এফএফ ব্যাজ",
            icon = "亗",
            symbols = listOf(
                "亗", "༒", "꧁", "꧂", "么", "乂", "♛", "♚", "☬", "♨",
                "ঔৣ", "★彡", "彡★", "『", "』", "【", "】", "〖", "〗", "♆",
                "☣", "⚡", "༒☬", "༺", "༻", "炎", "龍", "〆", "𝕶𝖎𝖓𝖌", "ᴮᴼˢˢ"
            )
        ),
        SymbolCategory(
            id = "weapons",
            name = "Guns & Weapons",
            banglaName = "বন্দুক ও অস্ত্র",
            icon = "︻╦̵̵͇̿̿̿̿╤──",
            symbols = listOf(
                "︻╦̵̵͇̿̿̿̿╤──", "╾━╤デ╦︻", "︻デ═一", "⌐╦╦═─", "︻┳═一",
                "▄︻̷̿┻̿═━一", "⚔️", "🗡️", "🏹", "🛡️", "💣", "🔫",
                "†", "‡", "⚔", "⚒", "⚚"
            )
        ),
        SymbolCategory(
            id = "wings",
            name = "Wings & Frames",
            banglaName = "ডানা ও ফ্রেম",
            icon = "༺ ༻",
            symbols = listOf(
                "༺", "༻", "꧁", "꧂", "⎝", "⎠", "⫷", "⫸", "❪", "❫",
                "«", "»", "『", "』", "【", "】", "〔", "〕", "《", "》",
                "〘", "〙", "⟦", "⟧", "〚", "〛", "᪥", "𓆩", "𓆪", "༗"
            )
        ),
        SymbolCategory(
            id = "asian",
            name = "Kanji & Japanese",
            banglaName = "জাপানি ও চাইনিজ ক্যালিগ্রাফি",
            icon = "龍",
            symbols = listOf(
                "龍", "鬼", "神", "侍", "忍", "桜", "炎", "雷", "虎", "狼",
                "零", "夜", "魔", "死", "月", "風", "火", "水", "王", "天",
                "帝", "愛", "光", "闇", "影", "道", "剣", "武"
            )
        ),
        SymbolCategory(
            id = "crowns_stars",
            name = "Crowns & Stars",
            banglaName = "মুকুট ও তারকা",
            icon = "👑",
            symbols = listOf(
                "👑", "♛", "♕", "♚", "♔", "★", "☆", "✦", "✧", "✪",
                "✫", "✬", "✭", "✮", "✯", "✰", "☬", "✵", "✹", "✺",
                "ᯓ", "✨", "💫", "🌠"
            )
        ),
        SymbolCategory(
            id = "hearts",
            name = "Hearts & Love",
            banglaName = "হার্ট ও লাভ",
            icon = "♡",
            symbols = listOf(
                "♡", "♥", "❥", "❦", "❧", "ღ", "დ", "💖", "💕", "💘",
                "❤️", "💔", "💗", "💓", "💞", "💟", "💌", "𓆩♡𓆪", "ꕤ", "ᦗ"
            )
        ),
        SymbolCategory(
            id = "numbers",
            name = "Sub & Superscript Numbers",
            banglaName = "ছোট সংখ্যা",
            icon = "⁰¹²",
            symbols = listOf(
                "⁰", "¹", "²", "³", "⁴", "⁵", "⁶", "⁷", "⁸", "⁹",
                "₀", "₁", "₂", "₃", "₄", "₅", "₆", "₇", "₈", "₉",
                "①", "②", "③", "④", "⑤", "⑥", "⑦", "⑧", "⑨", "⑩",
                "❶", "❷", "❸", "❹", "❺", "❻", "❼", "❽", "❾", "❿"
            )
        ),
        SymbolCategory(
            id = "faces",
            name = "Kaomoji & Faces",
            banglaName = "ইমোজি ও ফেস",
            icon = "( ͡° ͜ʖ ͡°)",
            symbols = listOf(
                "( ͡° ͜ʖ ͡°)", "ʘ‿ʘ", "ᕙ(⇀‸↼‶)ᕗ", "(ง'̀-'́)ง", "(•‿•)",
                "(╯°□°)╯︵ ┻━┻", "(ಥ_ಥ)", "ಠ_ಠ", "(づ｡◕‿‿◕｡)づ",
                "¯\\_(ツ)_/¯", "(^_-)", "(*^▽^*)", "ʕ•ᴥ•ʔ"
            )
        ),
        SymbolCategory(
            id = "spacers",
            name = "Spacers & Invisible Space",
            banglaName = "অদৃশ্য ফাঁকা ও স্পেসার",
            icon = "␣",
            symbols = listOf(
                INVISIBLE_SPACE, "・", "•", "·", "°", "᠎", "―", "—", "_", "|"
            )
        )
    )
}
