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
- [Manual](#user-manual)
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

<p align="center">
<img width="260" height="530" alt="image" src="https://github.com/user-attachments/assets/e91ebc98-7d90-47e7-9985-5af8e0419f84" />
<img width="260" height="530" alt="image" src="https://github.com/user-attachments/assets/16fde13e-9390-4de4-8604-0678f9878f69" />
<img width="260" height="530" alt="image" src="https://github.com/user-attachments/assets/7ac687f6-b614-455c-afe1-1359125547d6" />
</p>
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

## User Manual

To use the app, first download the latest release of the APK file [here](https://github.com/moatassim0/movie-review/releases/tag/APK) Once you have the APK, transfer it to your Android device if needed and install it.

### 1. Registering an Account

When you open the application for the first time, you will need to create an account. Registration requires a username, email address, password, and gender to sign up.

<p align="center">
  <img width="320" height="640" alt="image" src="https://github.com/user-attachments/assets/2a6ccb9e-c077-4549-8af4-02d16b8cf45f" />

</p>

---

### 2. Opening the Application

After registering, you will be directed to the Home screen. You will find a selection of curated categories such as "Most Watched," "Recently Added," and "Highly Rated" to help new users discover popular titles.

<p align="center">
  <img width="320" height="640" alt="image" src="https://github.com/user-attachments/assets/52034a58-9557-4b5a-a07e-955af681d0ab" />

</p>

---

### 3. Adding Movies to Favorites

Open any movie from the Home or Movies page and tap **Favorite** to save it to your profile. This marks the movie as a personal favorite within your account.

<p align="center">
  <img width="320" height="640" alt="image" src="https://github.com/user-attachments/assets/d1066d7f-2d67-4788-93bb-42d6d32f913e" />

</p>

---

### 4. Rating and Writing Reviews

Select any movie from the collection, assign it a star rating, and write your personal review in the text field. Reviews are saved to the system and linked to your profile upon submission.

<p align="center">
  <img width="320" height="640" alt="image" src="https://github.com/user-attachments/assets/08ea2a05-a1cb-4fb9-8c31-1915684731c1" />

</p>

---

### 5. Profile Page

Tap the user icon in the bottom navigation bar to access your Profile page. It displays all movies you have watched, rated, and reviewed, keeping a complete record of your activity on the app.

<p align="center">
  <img width="320" height="640" alt="image" src="https://github.com/user-attachments/assets/da57b35d-8269-43ea-81e7-77e9cd0c9c78" />

</p>

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
