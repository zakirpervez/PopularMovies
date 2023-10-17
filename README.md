<h1>PopularMovies</h1>
<p>The PopularMovies app helps you find classic favourite movies and lets you see more information about them.</p>

<h2>Getting Started</h2>

<h3>Software Stack:</h3>
1. Android Studio
2. Java
3. Kotlin
4. XML
5. Material Design
6. Constraint Layout
7. Jetpack Navigation Component
8. Glide
9. Retrofit
10. Hilt Dagger
11. Coroutines
12. Android Architecture Components
13. MVVM
14. Junit4

<h3>Git branching strategy</h3>

1. `main` - Main branch, always stable and ready to deploy. 
2. All the feature branches should be created from master branch. 
3. All the feature branches should be merged into master branch.

Note: As I am single developer, I am not creating any feature branches. I am directly pushing the code to master branch.

<h3>Commit guidelines</h3>
<p>Commit should always be make in following format</p>
<p>type(scope): [commit-details-message]-[ticket/issue_number]</p>

<p>type: Type should be represent the nature of commit. It can be one of the following.</p>
1. feat: A new code which is return for the feature.
2. fix: A bug fix.
3. refactor: A modification inside the existing code.
4. docs: Documentation only changes.
5. release: Release of new version.
6. test: Adding or updating tests.
7. chore: Updating build tasks, package manager configs, etc; no production code change.

<p>scope: Scope indicates the module, component, or part of the project the commit affects. It can be one of the following.</p>
1. Component
2. BugFix 
3. Module
4. App
5. Api

<p>commit-details-message: A short, concise summary of the changes.</p>

<p>ticket/issue_number: Represent JIRA/Github/Gitlab ticket/issue number.</p>

Note: As I don't have any JIRA/Github/Gitlab ticket/issue number, I am assuming a ticket no TN:10000 and adding it in my commits.

<h3>Architecture Used</h3>
This is application is built using a MVVM architecture.

![mvvm_architecture](https://github.com/zakirpervez/PopularMovies/assets/16011892/3648fe0f-821f-4384-92a5-4a2a2005c4b5)

<h3>Testing</h3>
1. Unit Testing: Unit testing is done using Junit4 and mockK.
2. Instrumentation Testing: No Instrument testing is done [Note: Please let me know if you want me to add the instrument test cases.]

<h3>App Screens</h3>
1. Splash Screen: Stay for 3 seconds and then navigate to Home Screen.
![Splash_screen](https://github.com/zakirpervez/PopularMovies/assets/16011892/0e2e9b09-5e39-406c-b94e-4a9e15e1dba7)

2. Home Screen: Show list of movies. User can click any movie to check its details.
![homescreen](https://github.com/zakirpervez/PopularMovies/assets/16011892/318a3011-0cd1-4147-abe9-afc8bd0b0f85)

3. Movie Details Screen: Show movie details like title, release date, rating, overview etc.
![details](https://github.com/zakirpervez/PopularMovies/assets/16011892/bda2e02e-a4ec-4e0e-be78-9c73a17fc65e)





