# sliding-bar

[View Releases and Changelog](https://github.com/cybercoder-naj/sliding-bar/releases)

[![MIT License](https://img.shields.io/apm/l/atomic-design-ui.svg?)](https://github.com/tterb/atomic-design-ui/blob/master/LICENSEs)
[![JitPack Build](https://jitpack.io/v/cybercoder-naj/sliding-bar.svg)](https://jitpack.io/#cybercoder-naj/sliding-bar)

Slider Composable for the Jetpack Compose UI-toolkit for Android Application Development

## Demo

[![Demo gif]](images/demo.gif)

## Installation

Add JitPack url to your `settings.gradle` file

```bash
  repositories {
    maven { url 'https://jitpack.io' }
  }
```

And the dependency in your app `build.gradle` file

```bash
  dependencies {
    implementation "com.github.cybercoder-naj:sliding-bar:$version"
  }
```

## Usage/Examples

```kotlin
SlidingBar(
    value = value,
    onValueChanged = { value = it },
    modifier = Modifier
        .padding(horizontal = 64.dp)
        .fillMaxWidth(),
    colors = SlidingBarDefaults.colors(
        colorPrimary = Color.Green,
        colorTrack = Color.Blue
    ),
    valueRange = 0f..30f,
    stepSize = 1f
)
```

## License

[MIT](LICENSE)

