![Logo](https://github.com/ivkond-mc/boulders/blob/1.21.4/docs/logo.png?raw=true)

[![Licence](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/license/mit)
[![Java 21](https://img.shields.io/badge/java-21%2B-blue)](https://adoptium.net/temurin/releases/?version=21)
[![Minecraft](https://img.shields.io/badge/minecraft-1.21.4-blue)](https://www.minecraft.net/article/minecraft-java-edition-1-21-4)
[![Build status](https://img.shields.io/github/actions/workflow/status/ivkond-mc/boulders/gradle-publish.yml?branch=release/1.21.4)](https://github.com/ivkond-mc/boulders/actions/workflows/gradle-publish.yml?branch=release/1.21.4)

---

# 🏠 About

No more wasting time digging for your first resources! *Boulders* adds a little surface boulders filled with deepslate
and ores, so you can gear up quickly and jump straight into the adventure.

* Find some ores right on the surface, no caves needed!
* Spend less time mining and more time discovering the world.
* Get tools and armor faster than ever!

> The original idea behind this mod and part of its source code were shamelessly borrowed from
> the [Oritech](https://github.com/Rearth/Oritech) mod. All credit and thanks should go to the original author!

# 🔧 Configuration

Many aspects of boulder generation can be customized using datapacks. Each boulder is defined as a `configured_feature`,
and its properties can be overridden in the following JSON files:

* `data/boulders/worldgen/configured_feature/boulder_common.json`
* `data/boulders/worldgen/configured_feature/boulder_uncommon.json`
* `data/boulders/worldgen/configured_feature/boulder_rare.json`

Available properties:

* `material` – The material used to generate the boulder (block identifier).
* `ratio` – The proportion of the material compared to ores.
* `radius` – The minimum and maximum radius of the boulder. The min value must be at least 1, and max can go up to 64 (
  creating an exceptionally large boulder!).
* `ores` – A list of ore identifiers. These can include both vanilla and modded ores.

# 📢 Feedback

Feel free to reach out in our Discord if you encounter any bugs, have questions, or would like to share suggestions for
improving the mod: [https://discord.gg/CJFGMTB6kN](https://discord.gg/CJFGMTB6kN)
