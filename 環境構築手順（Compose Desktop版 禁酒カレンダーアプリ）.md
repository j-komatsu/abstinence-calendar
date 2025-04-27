# 環境構築手順（Compose Desktop版 禁酒カレンダーアプリ）

## 1. 前提条件

| 項目       | バージョン例        | 備考                        |
|:-----------|:--------------------|:-----------------------------|
| JDK        | 17 または 21          | Kotlin 1.9以上に対応している必要あり |
| Gradle     | ラッパー同梱予定      | 手動インストール不要          |
| Kotlin     | 1.9以上              |                                |
| IDE        | IntelliJ IDEA（推奨） | Community版でOK（Composeサポートあり） |

---

## 2. 必要なツールインストール

### JDKインストール
- AdoptOpenJDK / Amazon Corretto / Azul Zulu などでJDK 17 or 21をインストールする。
- バージョン確認コマンド：

```bash
java -version
```

### IntelliJ IDEA インストール
- https://www.jetbrains.com/idea/ よりダウンロード
- プラグイン「Kotlin」は標準で入っているが、念のため確認

---

## 3. プロジェクトセットアップ手順

### 3.1 リポジトリクローン

```bash
git clone https://github.com/j-komatsu/abstinence-calendar.git
cd abstinence-calendar
```

### 3.2 Gradleプロジェクト構成を準備
次のような構成でプロジェクトファイルを作成する。

```plaintext
build.gradle.kts
settings.gradle.kts
gradle/wrapper/
src/main/kotlin/
src/main/resources/
```

※これらはアプリケーション生成時に自動作成する予定

---

## 4. Compose Multiplatform Desktop設定

### 4.1 build.gradle.kts（サンプル）

```kotlin
plugins {
    kotlin("jvm") version "1.9.10"
    id("org.jetbrains.compose") version "1.5.0"
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
}

compose.desktop {
    application {
        mainClass = "MainKt"
    }
}
```

---

## 5. 実行方法

- IntelliJ IDEAでプロジェクトを開き、`Main.kt` を右クリックして「Run」実行
- またはコマンドラインから

```bash
./gradlew run
```

で起動できる

---

## 6. よくあるエラーと対策

| エラー内容                | 対応方法                               |
|:--------------------------|:--------------------------------------|
| Java versionが合わない       | JDK 17 or 21に変更する                 |
| Composeプラグインが見つからない | build.gradle.ktsの設定ミスを確認         |
| kotlin-serialization関連エラー | dependenciesに正しく追加されているか確認 |

---

## 7. 参考リンク

- [Compose Multiplatform公式ドキュメント](https://github.com/JetBrains/compose-multiplatform)
- [Kotlin公式](https://kotlinlang.org/)
- [IntelliJ IDEA公式](https://www.jetbrains.com/idea/)

---
