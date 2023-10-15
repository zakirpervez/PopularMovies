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
<p>type(scope): <commit_description>-[ticket/issue_number].</p>

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

<p>commit_description: A short, concise summary of the changes.</p>

<p>ticket/issue_number: Represent JIRA/Github/Gitlab ticket/issue number.</p>

Note: As I don't have any JIRA/Github/Gitlab ticket/issue number, I am adding simple ticket no.


