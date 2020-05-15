## Registering Gradle Dependencies

### Introduction
Gradle is android's defacto build system. As well as building our app, we can add dependencies into our app through Gradle. Dependencies are just libraries or re-usable code written by other developers that allow us incorporate some feature into our app. By using libraries you avoid wasting time re-inventing the wheel. However it must be said that you need to careful with libraries you add into your project as some are not well written and tested and may break your app. Moreover they can add unnecessary bloat into your app.

In this lesson we will add some dependencies into our app. All these are quality libraries however some of them may be ommitted as per your wish.



### Step 1
Go over to your app level build.gradle. That is the `build.gradle` file located in the app folder of your project.

Then add the following code in your dependencies closure:

```groovy
    implementation 'com.android.support:appcompat-v7:28.0.0'

    implementation 'com.android.support:cardview-v7:28.0.0'
    implementation 'com.android.support:design:28.0.0'

    implementation 'com.squareup.retrofit2:retrofit:2.4.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.4.0'

    implementation 'com.squareup.picasso:picasso:2.71828'
    implementation 'com.github.ivbaranov:materiallettericon:0.2.3'
    implementation "android.helper:datetimepickeredittext:1.0.0"
    implementation 'io.github.inflationx:calligraphy3:3.1.0'
    implementation 'io.github.inflationx:viewpump:1.0.0'
    implementation 'com.yarolegovich:lovely-dialog:1.1.0'
```

### Explanations

#### 1. AppcompatActivity

```groovy
   implementation 'com.android.support:appcompat-v7:28.0.0'
```
We are using the `implementation` statement to add `appcompat` into our project. Through this package we will get access to `AppcompatActivity`  which will be the super class of all our activities. Read more about [AppcompatActivity here](/android/appcompatactivity).

#### 2. CardView
```groovy
    implementation 'com.android.support:cardview-v7:28.0.0'
```
The second dependency is CardView, a support library that will allow us create cards.

#### 3. Design Support
```groovy
    implementation 'com.android.support:design:28.0.0'
```
This support library will give us access to several classes like [RecyclerView](/android/recyclerview) and [ToolBar](/android/toolbar).

#### 4. Retrofit
```groovy
    implementation 'com.squareup.retrofit2:retrofit:2.4.0'
```
Retrofit is a third party HTTP client library for android.It will allow us talk to our REST API. Read more about [Retrofit here](/android/retrofit)

#### 5. Converter GSON
```groovy
    implementation 'com.squareup.retrofit2:converter-gson:2.4.0'
```
This will work together with Retrofit. It will map our domain objects to JSON Objects.

#### 6. Picasso
```groovy
    implementation 'com.squareup.picasso:picasso:2.71828'
```
Picasso is a popular image loading library.

#### 7. MaterialLetterIcon
```groovy
    implementation 'com.github.ivbaranov:materiallettericon:0.2.3'
```
This is a letter icon library. We can give it a letter or a digit and then it converts it to a beautiful icon which we can show instead of an image.

#### 8. DatePickerEditText
```groovy
    implementation "android.helper:datetimepickeredittext:1.0.0"
```
In our demo app you noticed we could enter a date using an edittext. This is because of this simple library. Obviously you can implement such a functionality from scratch. However this library will save us some time. You click an edittext then a date picker gets shown.

#### 9. Calligraphy
```groovy
    implementation 'io.github.inflationx:calligraphy3:3.1.0'
```
Well this library allows us use custom fonts in our application. You can download any font like Roboto and use them easily in our android via this library.

#### 10. Viewpump
```groovy
    implementation 'io.github.inflationx:viewpump:1.0.0'
```
This library is a requirement of Calligraphy.

#### 11. LovelyDialogs
```groovy
    implementation 'com.yarolegovich:lovely-dialog:1.1.0'
```
This library is one of the best android material dialog libraries.

### What we've Learnt

In this lesson we have learnt how to add dependencies into our build.gradle. We've said those dependencies are just libraries which are just modules of code we can re-use. We've then looked at the individual libraries and listed what they will do to our app.

Click the next button we move to the next lesson.