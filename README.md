# To-Do List App (Jetpack Compose)

## Overview
This project is a To-Do List Android application built using Jetpack Compose.  
It allows users to view tasks, add details or notes, and track progress across multiple screens while maintaining proper state management using ViewModels.

---

## Features

### Screen 1: Task List
- Displays a scrollable list of 10 tasks using a LazyColumn.
- Clicking a task navigates to the Task Details screen, passing relevant data.
- Uses a ViewModel to hold the task list and any applied filters.

---

### Screen 2: Task Details
- Saves user input in a shared ViewModel that is lifecycle-aware.  
- Uses rememberSaveable to preserve input across configuration changes.  
- Uses LaunchedEffect to trigger side effects such as toast messages or calculations.  
- Optionally uses DisposableEffect for cleanup when the composable is removed.  
- Includes a Back button to return to the task list.

---

### Screen 3: Logs / Notes / History
- Implements Bottom Navigation with two tabs:
  - Items → shows screen 1  
  - Notes → shows all notes that have been added  
- Includes a search bar to dynamically filter saved data by title or note.  
- Includes a Floating Action Button to add new notes without navigating to the Task Details screen
- All state (saved logs, search query, selected item) is managed through ViewModel to survive configuration changes.  

---

## How to Run
1. Close the repo
   ```git clone https://github.com/Plegik26/To-Do-Android-app```
2. Open the project in Android Studio.  
