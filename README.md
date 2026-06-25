# PawFinder

PawFinder is an Android application designed to help urgent shelter animals reach potential adopters, foster homes, and local supporters.

The app focuses on animals that may have limited time remaining in shelters and makes it easier for users to discover, save, share, and respond to urgent cases in their area.

## Project Purpose

Animal shelters often struggle to quickly reach people who may be able to adopt, foster, or support newly admitted and urgent animals.

PawFinder aims to create a simple platform where users can:

- Discover urgent shelter pets nearby
- View important information about each animal
- Contact shelters
- Save animals for later
- Share urgent listings
- Find community resources and local support

## Current Features

- Loading screen with PawFinder branding
- Main dashboard
- Urgent pet cards
- Pet images and descriptions
- Search and filter interface
- Community shortcut cards
- Marketplace interface
- Floating bottom navigation
- Custom colors, fonts, icons, and image assets

## Planned Features

- Pet detail pages
- Adoption and foster actions
- Saved pets
- Location-based results
- Urgent pet alerts
- Shelter contact information
- User profiles and badges
- Community forums
- Local pet resources

## Technology Stack

- Kotlin
- Android Studio
- Jetpack Compose
- Material 3
- Gradle
- Git
- GitHub
- GitHub Actions

## Project Structure

```text
app/
└── src/
    └── main/
        ├── java/
        │       └── com/williamlin/petrescue/
        └── res/

.github/
├── ISSUE_TEMPLATE/
├── workflows/
└── pull_request_template.md
```

## Getting Started

### Requirements

Before running the project, install:

* Android Studio
* Android SDK
* Git
* A configured Android emulator or physical Android device

### Clone the Repository

```bash
git clone https://github.com/williamlinplayzlegitpiano/pawfinder.git
```

Move into the project directory:

```bash
cd PetRescue
```
    
### Open the Project

1. Open Android Studio.
2. Select **Open**.
3. Choose the cloned project folder.
4. Wait for Gradle synchronization to finish.
5. Select an Android emulator or connected device.
6. Click **Run**.

## Development Workflow

All work should be connected to a GitHub issue.

### 1. Create or Select an Issue

Each feature, bug, documentation update, or project improvement should have its own issue.

Examples:

```text
Add marketplace screen
Fix pet card image sizing
Update project README
Add GitHub issue templates
```

### 2. Create a Branch

Create the branch from the latest version of `main`.

```bash
git switch main
git pull origin main
git switch -c prefix/branch-name
```

Common prefixes:

```text
feature/   New user-facing feature
fix/       Bug fix
docs/      Documentation
chore/     Repository maintenance
test/      Test changes
refactor/  Code restructuring
ci/        Continuous integration
```

Example:

```bash
git switch -c feature/pet-details
```

### 3. Commit the Changes

```bash
git add .
git commit -m "Add pet details screen"
```

Commit messages should clearly describe the change.

### 4. Push the Branch

```bash
git push -u origin feature/pet-details
```

### 5. Open a Pull Request

The pull request should target:

```text
base: main
compare: your-branch-name
```

Link the pull request to its issue using:

```text
Closes #ISSUE_NUMBER
```

Example:

```text
Closes #15
```

When the pull request is merged, GitHub will automatically close the linked issue.

### 6. Review and Merge

Before merging:

* Confirm GitHub Actions passes
* Review the changed files
* Confirm testing is documented
* Request approval when required
* Merge into `main`

### 7. Clean Up Locally

After the pull request is merged:

```bash
git switch main
git pull origin main
git branch -d branch-name
```

## Branch Naming Examples

```text
feature/marketplace
feature/pet-details
fix/loading-screen-crash
docs/add-readme
chore/add-github-templates
test/add-navigation-tests
refactor/extract-pet-card
ci/update-android-workflow
```

## Project Status

PawFinder is currently in active development.

The project is being built as an Android MVP, with the current focus on interface development, marketplace functionality, project organization, and development workflow improvements.

## Contributors

* William Lin
* Alvin Zhou

## License

A license has not yet been selected for this project.
