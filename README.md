# HelloGoldTest

## 1 App Architecture
#### 1.1 General App Architecture
The application uses [Dagger2](https://google.github.io/dagger/) to inject `repositories` (whom maintain data layer and business logic) into different components of the app (fragments, activities, views, services, test cases, presenters, etc...).

**Important Note:** All presenters and classes interacts with the `repositories` **interfaces** only and does not interact with Preference Helper directly.
#### 1.2 Types of Repsotitoeies:
Repositories interfaces can be found under [`data.repositories`](https://github.com/zack-barakat/HelloGoldTest/tree/master/app/src/main/java/com/android/hellogold/test/data/repositories) package. Each repository is responsible for handling its side of the business logic.
Dagger2 should maintain **only one copy** of each repository per app session (Singlton behaviour).
  
* **UserRepository**: Responsible for all user data such as (register user, store user data, fetch email, and etc...).
* **PriceRepository**: Responsible for all gold price data such as (fetching gold spot prices, fetching recent gold prices, and etc...).

#### 1.3 UI componentes architecture
Model View Preseneter known as MVP is the the architecture pattern used to develop HelloGold Test application UI.
**Model:** It is responsible for handling the data part of the application.
**View:** It is responsible for laying out the views with specific data on the screen.
**Presenter:** It is a bridge that connects a Model and a View. It also acts as an instructor to the View.
To read more about MVP Architecture you may refer to these links:
- [MVP Architecture](https://blog.mindorks.com/essential-guide-for-designing-your-android-app-architecture-mvp-part-1-74efaf1cda40#.lkml1yggq)
- [MVP Android Gudelines](https://medium.com/@cervonefrancesco/model-view-presenter-android-guidelines-94970b430ddf)

## 2 Language used
#### 2.1 Kotlin
#### 2.2 Java

## 3 Main Libraries used

* [Retrofit](http://square.github.io/retrofit/) - REST client library for android
* [Dagger2](https://google.github.io/dagger/android.html) - Dependency injection framework
* [Anko](https://github.com/Kotlin/anko) - Set of Kotlin extensions to make android development faster
* [RxJava](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid) - Reactive programming, simplifies work with threading and concurrency in java and android.
* [Mockito](https://github.com/mockito/mockito) and [Robolectric](https://github.com/robolectric/robolectric) - Unit test framework and mocking tools.

## 4 Unit Test

#### All presenters have equivalent test presenets to test mvp intercations and ensure logic is done properly

## 5 App UI flow and functionality
#### There are two screens in the app, Register screen where user can register to the system, and Dashboard screen where user can see latest gold prices.
### Register Screen
<img src="https://user-images.githubusercontent.com/13542406/52912237-7dbfc780-32e9-11e9-962d-c29bb4b37dc0.png" width="200" >

### Dashboard Screen
<img src="https://user-images.githubusercontent.com/13542406/52912236-7d273100-32e9-11e9-8404-afbf36c78be2.png" width="200" >

### To test the the whole flow you can download the apk using this link from bitrise:
https://app.bitrise.io/artifact/12054024/p/1bb35743f3e95cb0b84869ab5f85f634 
