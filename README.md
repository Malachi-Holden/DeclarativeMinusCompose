# DeclarativeMinusCompose

We love building beautiful UIs in Jetpack Compose, but some of our apps have constraints that don't allow them to incorporate it. DeclarativeMinusCompose allows for a similar syntax, but is built on Androidx views under the hood. Now you can use the syntax you love without having to worry about the dependency issues caused by incorporating Compose into an older app.

## Including in your app

The library is available as a .aar binary in the releases page. Download the release you want and [follow this guide](https://developer.android.com/studio/projects/android-library#psd-add-aar-jar-dependency) to add it as a dependency.

## Using

#### in Activity

Subclass `ExcompActivity` and in your `onCreate`, replace `setContentView` with `setContent` and give it a lambda, like this

```
class MainActivity : ExCompActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    setContent {
      // Declare UI here
    }
  }
}
```

Within this you can combine layouts and widgets like in Compose

```
setContent {
  VerticalLayout {
    Text("Hello. My name is")
    HorizontalLayout {
      Text("Inigo")
      Text("Montoya")
    }
  }
}
```

To avoid crazy levels of embedding, you can separate out your UI into chunks. Instead of using annotations like in Compose, you use extension functions. You extend the special class Excomp.

```
fun Excomp.InigoMontoyaEnding(){
  HorizontalLayout {
      Text("Inigo")
      Text("Montoya")
    }
}

```

And then in another ExComp context you can call your UI extension function to add it to the UI

```
setContent {
  VerticalLayout {
    Text("Hello. My name is")
    InigoMontoyaEnding()
  }
}

```

#### in a Fragment

This functionality isn't ready yet!
