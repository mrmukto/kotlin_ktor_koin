# kotlin_ktor_koin
A simple Android app built with Kotlin, Jetpack Compose, Ktor Client, and Koin for dependency injection .It fetches posts from the [JSONPlaceholder](https://jsonplaceholder.typicode.com) API and displays them in a Compose list.

---

## 🚀 Features
- Jetpack Compose UI
- Ktor Client for REST API calls
- Custom interceptors (headers & logging)
- Repository + ViewModel architecture
- Koin for dependency injection
- Result wrapper for **Loading / Success / Error** states

---

## 📂 Project Structure
app/
└─ src/main/java/com/example/ktorapp/
├─ data/
│ ├─ model/ # Post.kt
│ ├─ remote/ # ApiService, ApiServiceImpl, KtorClientProvider
│ └─ repository/ # PostRepository
├─ di/ # Koin Modules
├─ ui/ # PostViewModel + Compose UI
├─ util/ # RetryUtils, Result wrapper
├─ App.kt # Application class (startKoin)
└─ MainActivity.kt # Entry point, Compose UI



---

## ⚙️ Setup

### 1. Add Permissions
In `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.INTERNET" />

### 2. Dependencies

In build.gradle (app):

// Ktor
implementation("io.ktor:ktor-client-core:2.3.4")
implementation("io.ktor:ktor-client-cio:2.3.4")
implementation("io.ktor:ktor-client-content-negotiation:2.3.4")
implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4")
implementation("io.ktor:ktor-client-logging:2.3.4")

// Koin
implementation("io.insert-koin:koin-android:3.4.3")
implementation("io.insert-koin:koin-androidx-compose:3.4.3")

// Jetpack Compose
implementation(platform("androidx.compose:compose-bom:2024.06.00"))
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")
implementation("androidx.compose.ui:ui-tooling-preview")
debugImplementation("androidx.compose.ui:ui-tooling")

3. Start Koin

App.kt:

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}


Register in AndroidManifest.xml:
<application android:name=".App" ... >

🖼️ UI Flow

Loading → Shows CircularProgressIndicator
Success → Displays list of posts
Error → Shows error message

📦 API

Using JSONPlaceholder:

GET /posts → fetch all posts
GET /posts/{id} → fetch single post
🔮 Future Improvements

Offline caching with Room DB
Pagination support
Dark mode styling
Unit tests with Ktor MockEngine


---
