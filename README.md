# Movie Review Android App

An Android application for browsing movies, tracking watched and favorite titles, and writing movie reviews.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Building from the Command Line](#building-from-the-command-line)
- [Notes](#notes)
- [Future Improvements](#future-improvements)

---

## Overview

This app is a Java-based Android application that uses a local SQLite database to manage user accounts, movie metadata, reviews, and personal tracking states.

**Main user flow:**

1. Register or log in
2. Browse movies from the Home and Directory screens
3. Open a movie details page
4. Mark movies as seen or favorite and submit a review
5. View your recent favorites and reviews on the Personal screen

---

## Features

### Authentication
- Login screen (`MainActivity`)
- Registration screen (`Registration`)

### Home Dashboard
- Most Watched section
- Highly Rated section
- Recently Added section

### Directory
- Full movie listing in a grid layout
- Category-based sorting

### Movie Details
- Poster and metadata: genre, duration, year, age restriction, view count, director
- Seen and Favorite toggles
- Review submission (text input and star rating)
- Reviews list rendered via `RecyclerView`

### Personal Screen
- Recent favorite movies
- Recently reviewed movies

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| UI | Android Views + XML layouts |
| Database | SQLite via `SQLiteOpenHelper` |
| Build System | Gradle (Kotlin DSL) |
| Compile SDK | 34 |
| Target SDK | 34 |
| Minimum SDK | 24 (Android 7.0 Nougat) |

---

## Project Structure

```
app/src/main/
├── java/com/example/myapplication/
│   ├── MainActivity.java          # Login entry point
│   ├── Registration.java          # User signup
│   ├── home.java                  # Dashboard with categorized sections
│   ├── Directory.java             # Full movie listing by category
│   ├── Movie.java                 # Movie details, seen/favorite toggles, reviews
│   ├── Personal.java              # User-centric favorites and review history
│   ├── Database.java              # SQLite schema and all query logic
│   └── (adapters + models)        # RecyclerView adapters for directory and reviews
├── res/
│   ├── layout/                    # XML screen and row layouts
│   └── drawable*/                 # Icons and movie poster assets
└── AndroidManifest.xml            # Activity declarations and launcher config
```

---

## Getting Started

### Prerequisites

| Requirement | Details |
|---|---|
| Android Studio | Latest stable release recommended |
| Android SDK | Version 34 |
| JDK | Version 8 or higher (project uses Java 8 source/target compatibility) |

### Run in Android Studio

1. Open Android Studio.
2. Select **Open** and choose this project folder.
3. Wait for the Gradle sync to complete.
4. Select the `app` run configuration.
5. Run on an emulator or a connected Android device (API level 24 or higher).

---

## Building from the Command Line

From the project root, run:

**macOS / Linux:**
```bash
./gradlew assembleDebug
```

**Windows (PowerShell):**
```powershell
.\gradlew.bat assembleDebug
```

The output APK will be generated at:
```
app/build/outputs/apk/debug/app-debug.apk
```

---

## Notes

- All data is stored locally in a SQLite database (`project.db`). There is no remote backend or API integration.
- Movie poster images are resolved from drawable resources using IDs in the format `movie_<id>`.
- The app is entirely offline and self-contained.

---

## Future Improvements

- Add input validation and error handling for the registration and login flows.
- Implement a database migration strategy and structured seed data management.
- Refactor toward a clean architecture pattern using the Repository and ViewModel layers (MVVM).
- Add unit tests and UI tests for critical flows such as login, movie detail rendering, and review submission.
- Integrate a remote movie API (e.g., TMDB) to replace or supplement the local dataset.
