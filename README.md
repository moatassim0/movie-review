# Movie Review Android App

Android Studio project for browsing movies, tracking watched/favorite titles, and writing movie reviews.

## Overview

This app is a Java-based Android application that uses a local SQLite database to manage:
- user accounts (registration and login)
- movie metadata and directors
- user reviews and ratings
- favorite and watched movie states

The main user flow is:
1. Register or log in
2. Browse movies from the Home and Directory screens
3. Open a movie details page
4. Mark movies as seen/favorite and submit reviews
5. View personal recent favorites and reviews

## Features

- Authentication screens (`MainActivity`, `Registration`)
- Home dashboard with:
  - Most Watched
  - Highly Rated
  - Recently Added
- Directory screen with grid layout and category-based sorting
- Movie details screen with:
  - Poster and metadata (genre, duration, year, restriction, views, director)
  - Seen/Favorite toggles
  - Review submission (text + rating)
  - Reviews list via `RecyclerView`
- Personal screen showing:
  - Recent favorites
  - Recent reviewed movies

## Tech Stack

- Language: Java
- UI: Android Views + XML layouts
- Database: SQLite (`SQLiteOpenHelper`)
- Build system: Gradle (Kotlin DSL)
- Android SDK:
  - `compileSdk` 34
  - `targetSdk` 34
  - `minSdk` 24

## Project Structure

- `app/src/main/java/com/example/myapplication/`
  - `MainActivity` - login entry point
  - `Registration` - user signup
  - `home` - dashboard categories
  - `Directory` - full movie listing by category
  - `Movie` - movie details, favorites/seen/reviews
  - `Personal` - user-centric sections
  - `Database` - all SQLite schema and queries
  - RecyclerView adapters/models for directory and reviews
- `app/src/main/res/layout/` - XML screens and row layouts
- `app/src/main/res/drawable*` - icons and movie assets
- `app/src/main/AndroidManifest.xml` - activity declarations and launcher

## Getting Started

### Prerequisites

- Android Studio (latest stable recommended)
- Android SDK 34 installed
- JDK 8+ (project source/target compatibility is Java 8)

### Run in Android Studio

1. Open Android Studio.
2. Select **Open** and choose this project folder.
3. Wait for Gradle sync to finish.
4. Run the `app` configuration on an emulator or Android device.

## Build From Command Line

From the project root:

```bash
./gradlew assembleDebug
```

On Windows PowerShell:

```powershell
.\gradlew.bat assembleDebug
```

## Notes

- The app stores data locally in SQLite (`project.db`).
- Sample movie images are resolved from drawable resources using IDs like `movie_<id>`.
- The app currently uses a local-only data model (no remote API/backend).

## Future Improvements

- Add input validation hardening and error handling in registration/login.
- Add database migration strategy and seed data management.
- Improve architecture separation (repository/viewmodel pattern).
- Add unit/UI tests for critical user flows.

