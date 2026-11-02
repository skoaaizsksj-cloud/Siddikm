package com.example.ffnames.data.model

object PreloadedStyles {

    val categories = listOf(
        "All",
        "Royal",
        "Killer",
        "Pro",
        "Attitude",
        "Bangla",
        "Love",
        "Clan",
        "Anime",
        "Symbols"
    )

    val list: List<NameStyle> = listOf(
        // Starter styles directly from user's starter package
        NameStyle("d1", "Royal Crown", "Royal", prefix = "♛么", suffix = "么♛"),
        NameStyle("d2", "Shadow", "Killer", prefix = "乂", suffix = "乂"),
        NameStyle("d3", "Angel", "Love", prefix = "꧁ঔৣ", suffix = "ঔৣ꧂"),
        NameStyle("d4", "Pro Player", "Pro", prefix = "亗", suffix = "亗"),
        NameStyle("d5", "Dark Lord", "Attitude", prefix = "༒", suffix = "༒"),
        NameStyle("d6", "Wing Style", "Symbols", prefix = "么꧁", suffix = "꧂么"),
        NameStyle("d7", "Star", "Royal", prefix = "★彡", suffix = "彡★"),
        NameStyle("d8", "Dragon", "Killer", prefix = "乂龍", suffix = "龍乂"),
        NameStyle("d9", "Bengal", "Bangla", prefix = "꧁", suffix = "꧂"),
        NameStyle("d10", "Elite", "Pro", prefix = "『", suffix = "』"),
        NameStyle("d11", "Fire", "Killer", prefix = "🔥", suffix = "🔥"),
        NameStyle("d12", "Heart", "Love", prefix = "♡", suffix = "♡"),

        // Legendary Free Fire Gamer Styles
        NameStyle("ff1", "Grandmaster King", "Royal", template = "꧁༒☬{name}☬༒꧂", fontStyle = FontTransformer.FontStyle.FRAKTUR_BOLD),
        NameStyle("ff2", "Raistar Signature", "Pro", template = "亗 『RAI』{name} 亗"),
        NameStyle("ff3", "Boss Squad", "Clan", prefix = "ᴮᴼˢˢ 乂 ", suffix = " ࿐", fontStyle = FontTransformer.FontStyle.SMALL_CAPS),
        NameStyle("ff4", "Sniper Rifle AWM", "Killer", prefix = "︻╦̵̵͇̿̿̿̿╤── ", suffix = ""),
        NameStyle("ff5", "Bad Boy Toxic", "Attitude", prefix = "亗 ᴮᴬᴰ ᴮᴼᵞ 亗 ", suffix = "", fontStyle = FontTransformer.FontStyle.SMALL_CAPS),
        NameStyle("ff6", "Gothic Emperor", "Royal", prefix = "𝕶𝖎𝖓𝖌 亗 ", suffix = " 亗", fontStyle = FontTransformer.FontStyle.FRAKTUR_BOLD),
        NameStyle("ff7", "Bangla Tigers", "Bangla", template = "꧁༒বাঘ༒꧂ {name}"),
        NameStyle("ff8", "Queen & Princess", "Love", template = "𓆩♡𓆪 {name} 𓆩♡𓆪", fontStyle = FontTransformer.FontStyle.SCRIPT_BOLD),
        NameStyle("ff9", "Ninja Assassin", "Anime", prefix = "〖ƝɪƝᴊᴀ〗", suffix = " 〆", fontStyle = FontTransformer.FontStyle.SMALL_CAPS),
        NameStyle("ff10", "Born 2 Kill B2K", "Killer", prefix = "『B2K』", suffix = " ⚡", fontStyle = FontTransformer.FontStyle.SMALL_CAPS),
        NameStyle("ff11", "Devil Horns", "Attitude", prefix = "꧁𓊈𒆜", suffix = "𒆜𓊉꧂"),
        NameStyle("ff12", "Vincenzo Style", "Pro", template = "꧁༒•{name}•༒꧂", fontStyle = FontTransformer.FontStyle.SMALL_CAPS),
        NameStyle("ff13", "Mayabi Prem", "Bangla", template = "꧁༒মায়াবী༒꧂ {name}"),
        NameStyle("ff14", "Heartbeat Sweet", "Love", prefix = "ღ ", suffix = " ღ", fontStyle = FontTransformer.FontStyle.SCRIPT_BOLD),
        NameStyle("ff15", "God Level Clan", "Clan", prefix = "么 ɢᴏᴅ 乂 ", suffix = " 亗", fontStyle = FontTransformer.FontStyle.SMALL_CAPS),
        NameStyle("ff16", "Joker Smile", "Attitude", prefix = "꧁༒J O K E R༒꧂ ", suffix = ""),
        NameStyle("ff17", "Gunslinger M1887", "Killer", prefix = "▄︻̷̿┻̿═━一 ", suffix = " ⚡"),
        NameStyle("ff18", "Winged Knight", "Symbols", prefix = "༺", suffix = "༻", fontStyle = FontTransformer.FontStyle.DOUBLE_STRUCK),
        NameStyle("ff19", "Tokyo Ghoul", "Anime", prefix = "乂 ᴋᴇɴ 乂 ", suffix = " 鬼", fontStyle = FontTransformer.FontStyle.SMALL_CAPS),
        NameStyle("ff20", "Bangla Nawab", "Bangla", template = "꧁নবাব꧂ {name}"),
        NameStyle("ff21", "Double Struck Pro", "Pro", prefix = "『", suffix = "』", fontStyle = FontTransformer.FontStyle.DOUBLE_STRUCK),
        NameStyle("ff22", "Toxic Attitude", "Attitude", prefix = "乂 Ƭ Ｏ Ｘ Ꮖ Ｃ 乂 ", suffix = ""),
        NameStyle("ff23", "Love Angel", "Love", prefix = "ᦗ 𝐋 𝐨 𝐯 𝐞 ᦗ ", suffix = " ♡"),
        NameStyle("ff24", "Samurai Edge", "Anime", prefix = "侍 乂 ", suffix = " 乂 侍"),
        NameStyle("ff25", "Clan Elite Alpha", "Clan", prefix = "『αℓρнα』", suffix = " ♛"),
        NameStyle("ff26", "Lightning Bolt", "Killer", prefix = "⚡", suffix = "⚡", fontStyle = FontTransformer.FontStyle.BOLD_SANS),
        NameStyle("ff27", "Royal Highness", "Royal", prefix = "👑 ༒ ", suffix = " ༒ 👑"),
        NameStyle("ff28", "Circled Bubbles", "Symbols", prefix = "⫷ ", suffix = " ⫸", fontStyle = FontTransformer.FontStyle.CIRCLED),
        NameStyle("ff29", "Bangla Chele", "Bangla", template = "꧁বাংলার বাঘ꧂ {name}"),
        NameStyle("ff30", "Gothic Skull", "Killer", prefix = "꧁ঔৣ☠️", suffix = "☠️ঔৣ꧂", fontStyle = FontTransformer.FontStyle.FRAKTUR_BOLD),
        NameStyle("ff31", "Monospace Tech", "Symbols", prefix = "【", suffix = "】", fontStyle = FontTransformer.FontStyle.MONOSPACE),
        NameStyle("ff32", "Legendary Heroic", "Pro", prefix = "༺LeGeNd༻ ", suffix = ""),
        NameStyle("ff33", "White444 One Tap", "Pro", prefix = "W H I T E ⚡ ", suffix = " 亗"),
        NameStyle("ff34", "Black Dragon Clan", "Clan", prefix = "龍 乂 ", suffix = " 乂 龍"),
        NameStyle("ff35", "Sad Soul Broken", "Love", prefix = "💔 ᴮᴿᴼᴷᴱᴺ 💔 ", suffix = "", fontStyle = FontTransformer.FontStyle.SMALL_CAPS),
        NameStyle("ff36", "Anonymous Hacker", "Attitude", prefix = "⚚ 𝕬𝖓𝖔𝖓𝖞𝖒𝖔𝖚𝖘 ⚚ ", suffix = "", fontStyle = FontTransformer.FontStyle.FRAKTUR_BOLD),
        NameStyle("ff37", "Bangla Kazi", "Bangla", template = "꧁কাজী꧂ {name}"),
        NameStyle("ff38", "Slayer Blade", "Killer", prefix = "⚔️ 乂 ", suffix = " 乂 ⚔️"),
        NameStyle("ff39", "Demon King", "Attitude", prefix = "⎝⎝✧G O D✧⎠⎠ ", suffix = ""),
        NameStyle("ff40", "Superscript Tiny", "Symbols", prefix = "亗 ", suffix = " 亗", fontStyle = FontTransformer.FontStyle.SUPERSCRIPT)
    )
}
