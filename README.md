# Dublin Bike App

![Bikeapp](https://user-images.githubusercontent.com/27746978/90322954-ca25ab00-df52-11ea-860b-0cc27eec8282.png)

DublinBike is an app that fetches realtime data of bikes available in dublin city center using [jcdecaux.com] (https://api.jcdecaux.com/) API.


## Features:
* All stations are appears in Google Map.
* Search for station in Google Map.
* Covers all cycle stations in Dublin 
* All stations showing in a list view with cycles and empty spaces available
* Can measure the distance from current location to the closest cycle stand
* Mark station as favourite and see data of one Particular station 


## Used libraries:
* [RxJava](https://github.com/ReactiveX/RxAndroid) and [Retrofit](http://square.github.io/retrofit/) libraries to manage Rest Client
* [ButterKnife](http://jakewharton.github.io/butterknife/) library to bind views and avoid boilerplate views code
* [EventBus](https://github.com/greenrobot/EventBus) library to send data between components and makes code simpler
* [Picasso](http://square.github.io/picasso/) and [Circle ImageView](https://github.com/hdodenhof/CircleImageView) libraries to manage images
* [Mockito](http://site.mockito.org/) library to make unit test and [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/) library to make UI test.

## Design pattern
MVP (Model View Presenter) pattern to keep it simple and make the code testable, robust and easier to maintain

## Build from the source:

In order to build the app you must provide your own API key from jcdecaux.com
Open ListItem.java file  and paste your key instead of ***YOUR_API_KEY*** text in this line:

```
public static final String URL_DATA = "https://api.jcdecaux.com/vls/v1/stations/?apiKey=[YOUR_API_KEY]&contract=dublin"
```

## License:
```
Copyright 2018, Muhammad Taimur

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
