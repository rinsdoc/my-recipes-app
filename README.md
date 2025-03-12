# My Recipes App 🍳

A Android recipe application that allows users to discover, save, and manage their favorite recipes. Built with MVVM architecture and Firebase integration for real-time data synchronization.

❗`This was a project for the subject Interface Design`❗

## ✨ Features

### 🥗 Recipe Management
- Browse a curated collection of recipes
- View detailed recipe information including:
  - Ingredients with measurements
  - Step-by-step cooking instructions
  - Calorie information

### 🎲 Random Recipe Discovery
- Discover new recipes with a single tap
- View complete details including ingredients and instructions
- Save discovered recipes to favorites instantly

### ❤️ Favorites System
- Save favorite recipes for quick access
- Real-time synchronization across devices
- Easy toggling of favorite status in both detail and random recipe views

### 🔐 User Authentication
- Secure login and registration system
- Password management

### 🎯 Personalization
- User profiles with customizable settings
- Dark/Light theme toggle

### 📱 User Interface
- Intuitive navigation with side drawer
- Responsive layout optimized for all screen sizes
- Smooth animations and transitions

## 🚀 Getting Started

### ⚙️ Prerequisites
- Android Studio 4.0+
- Android SDK 21+
- Google Play Services

### 📥 Installation
1. Clone the repository:
   ```
   git clone https://github.com/rinsdoc/my-recipes-app.git
   ```
2. Open the project in Android Studio
3. Connect your Firebase project:
   - Add your `google-services.json` file to the app module
   - Configure Firebase Authentication and Realtime Database

### 🔧 Configuration
- Update Firebase database rules for proper security
- Configure proguard rules for release builds

## 🏗️ Technical Overview

### 📐 Architecture
The app follows the **MVVM (Model-View-ViewModel)** architecture pattern for clear separation of concerns:
- **Model**: Data classes and repositories that handle data operations
- **View**: Activities and Fragments that display UI elements
- **ViewModel**: Classes that manage UI-related data and business logic

### 🔄 Data Flow
1. User actions trigger View calls to ViewModel methods
2. ViewModels retrieve or update data through Repositories
3. Repositories interact with Firebase services
4. LiveData objects in ViewModels are updated
5. Observers in Views update the UI based on LiveData changes

### 🗄️ Firebase Structure
- `users/`: User profile information
- `recipes/`: Recipe data including ingredients and instructions
- `userFavorites/`: User-specific favorite recipes

### 📂 Code Organization
- **Packages by feature**: Each feature has its own set of models, views, and viewmodels
- **Clean architecture principles**: Separation of concerns and dependency inversion

## 🧩 Key Components

### 📦 Repositories
- `DashboardRepository`: Manages recipe data retrieval and operations, including random recipe selection
- `FavouriteRepository`: Handles user favorites functionality
- `UserRepository`: Manages user authentication and profile data

### 🧠 ViewModels
- `DashboardViewModel`: Provides recipe data to the UI
- `DetailViewModel`: Manages recipe detail views and favorite status
- `RandomViewModel`: Manages random recipe selection and display
- `FavouriteViewModel`: Manages user's favorite recipes
- `ProfileViewModel`: Handles user profile data
- `LoginViewModel` & `RegisterViewModel`: Manage authentication processes

### 👁️ Views
- `SplashActivity`: Entry point with animation and authentication check
- `MainActivity`: Container for main app fragments
- `DashboardFragment`: Displays recipe list
- `DetailFragment`: Shows detailed recipe information
- `RandomFragment`: Provides random recipe discovery functionality
- `FavouritesFragment`: Displays user's favorite recipes
- `ProfileFragment`: Manages user profile settings

## 🛠️ Technologies Used

### 🔥 Firebase Integration
- **Firebase Authentication**: For user login and registration
- **Firebase Realtime Database**: For storing recipe and user data
- **Firebase Firestore**: For additional user profile information

### 🤖 Android Components
- **LiveData**: For observable data holders
- **ViewModel**: For lifecycle-aware data management
- **Navigation Component**: For simplified in-app navigation
- **Data Binding**: For declarative UI elements bound to data sources
- **View Binding**: For type-safe view access

### 📚 Third-Party Libraries
- **Glide**: For efficient image loading and caching
- **Material Design Components**: For modern UI elements
