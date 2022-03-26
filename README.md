# Interswitch Technical Test #



## Project Structure ##
    .
    ├── app
    │   |-- build.gradle       
       # main build script
    │   └── src
    │       ├── androidTest             # Android test assets, resources and code
    │       ├── test                    # Unit tests
    │       ├── main
    │       │   ├── java       
    │       │   │    ├── data           # handles data sources(remote and cached data)
    │       │   │    ├── domain         # business logic 
    │       │   │    └── presentation   # UI views and viewmodel
    │       │   └── res                 # main project resources
    │       └── debug                   # debug variant
    │            └── assets             # debug asssets
    ├── buildSrc                        # custom plugins or tasks shared across modules
    └── gradle.properties               # properties imported by the build script


## Build Instructions ##

1. Make sure you've installed [Android Studio](https://developer.android.com/studio/index.html).
2. In Android Studio, open the project from the local repository. This will auto-generate `local.properties` with the SDK location.
3. Recommended: The project uses JDK11 to build the app and run the tests. Some tests won't pass on the JDK embedded in Android Studio (JDK8). You might want to set JAVA_HOME and JDK location in Android Studio to JDK11.
4. Go to Tools → AVD Manager and create an emulated device or connect a physical device via ADB.
2. Run.

Notes:

* While loading/building the app in Android Studio ignore the prompt to update the gradle plugin version as that will probably introduce build errors. On the other hand, feel free to update if you are planning to work on ensuring the compatibility of the newer version.

## App Screenshots ##

![app screens](https://user-images.githubusercontent.com/37341054/160259901-913aa44d-f4c2-464f-b559-04c36a0c9d9a.jpg) 

## Run Tests ##

The project has both unit and instrumental tests. To test the project from the command line:

    $ ./gradlew test                  # assemble, install and run unit tests
    $ ./gradlew connectedAndroidTest  # assemble, install and run Instrumented tests

## License ##

    MIT License

    Copyright (c) 2022 Oluka Denis

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
