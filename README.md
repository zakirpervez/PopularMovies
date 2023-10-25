<h1>PopularMovies</h1>
<p>The PopularMovies app helps you find classic favourite movies and lets you see more information about them.</p>

<h2>Getting Started</h2>
<p>Discover the latest and greatest in the world of cinema with our Popular Movies app. Whether you're a film enthusiast or simply looking for your next movie night selection, this app is your one-stop destination for all things movies.</p>

<h3>Software Stack:</h3>
<ol>
<li>Android SDK</li>
<li>Kotlin</li>
<li>XML</li>
<li>Constraint/Motion Layout</li>
<li>Jetpack Navigation Component</li>
<li>Jetpack Paging</li>
<li>Android Architecture Components</li>
<li>Glide</li>
<li>Retrofit</li>
<li>Hilt Dagger</li>
<li>Coroutines</li>
<li>MVVM</li>
<li>Junit4</li>
<li>MockK</li>
</ol>

<h3>Git branching strategy</h3>
<p>A Git branching strategy is a set of rules and guidelines for managing and organizing branches in a Git repository. 
It helps teams collaborate, maintain code quality, and streamline the development process. 
There are several branching strategies like Truncate based development, Git Flow etc.</p>
<p>In this project I am going to use the truncate based development strategy as I am a single in this project due to which it make more sense to use it.</p>
<ol>
<li>`main` - Main branch, always stable and ready to deploy. </li>
<li>All the feature branches should be created from master branch. </li>
<li>All the feature branches should be merged into master branch.</li>
</ol>

*Note: As I am single developer, I am not creating any feature branches. I am directly pushing the code to master branch.*

<h3>Commit guidelines</h3>
<p>Commit should always be make in following format</p>
<p>type(scope): [commit-details-message]-[ticket/issue_number]</p>

<p>type: Type should be represent the nature of commit. It can be one of the following.</p>
<ol>
<li>feat: A new code which is return for the feature.</li>
<li>fix: A bug fix.</li>
<li>refactor: A modification inside the existing code.</li>
<li>docs: Documentation only changes.</li>
<li>release: Release of new version.</li>
<li>test: Adding or updating tests.</li>
<li>chore: Updating build tasks, package manager configs, etc; no production code change.</li>
</ol>

<p>scope: Scope indicates the module, component, or part of the project the commit affects. It can be one of the following.</p>
<ol>
<li>Component</li>
<li>BugFix </li>
<li>Module</li>
<li>App</li>
<li>Api</li>
</ol>

<p>commit-details-message: A short, concise summary of the changes.</p>

<p>ticket/issue_number: Represent JIRA/Github/Gitlab ticket/issue number.</p>

* Note: 
<ol>
<li>There is no git pre-hooks added.</li>
<li>As I don't have any JIRA/Github/Gitlab ticket/issue number, I am assuming a ticket no TN:10000 and adding it in my commits.</li>
<li>No CI/CD integration [Gitlab/Github/Bitrise/Bamboo/Fastlane etc would be good candidates]</li>
</ol>
*

<h3>Architecture Used</h3>
This is application is built using a MVVM architecture.

![mvvm_architecture](https://github.com/zakirpervez/PopularMovies/assets/16011892/6a215e81-9461-4b6b-ba4f-022c63e5c1ce)

<h3>Testing</h3>
<ol>
<li>Unit Testing: Unit test cases are added.</li>
<li>Instrumentation Testing: No instrumentation test cases are added.</li>
</ol>

<h3>Security</h3>
<ol>
<li>Network security config added</li>
<li>Proguard/Dexguard is not added</li>
</ol>
*Note: Not adding the proguard/dexguard.*

<h3>Interceptor/CodeAnalysis/Logging/Memory Detection Tools</h3>
<p>Integrated following tools inside the app</p>
<ol>
<li><b>lint:</b><p>Lint is a static code analysis tool provided by Android Studio. It helps identify issues in Android project code that may cause runtime errors, security vulnerabilities, performance problems, or other code quality problems. Lint performs a wide range of checks, including identifying unused resources, detecting layout performance issues, highlighting potential security vulnerabilities, and more. It provides suggestions and warnings to help developers write better code and follow best practices.</p></li>
<li><b>kt-lint:</b><p>KtLint is a Kotlin-specific code style checker and formatter. It enforces a consistent coding style in Kotlin projects by defining coding rules and then applying those rules to the source code. KtLint helps maintain a uniform and readable codebase by automatically formatting code and highlighting deviations from the defined style rules. It is often used in conjunction with Kotlin projects to ensure code consistency and adherence to a project's coding standards.</p></li>
<li><b>detekt:</b><p> Detekt is a static code analysis tool for Kotlin that helps developers identify issues and enforce coding standards in Kotlin codebases. Similar to Lint for Android, Detekt analyzes Kotlin code for various issues, such as complexity, code smells, potential bugs, and anti-patterns. It provides a set of configurable rules to detect issues and can be customized to match the coding standards and guidelines of a project.</p></li>
<li><b>leak canary:</b>
<p>LeakCanary is a memory leak detection library for Android applications. It helps developers identify and diagnose memory leaks in their apps. Memory leaks can lead to increased memory consumption and application crashes, so LeakCanary is a valuable tool for finding and fixing such issues. It automatically detects and reports memory leaks, providing detailed information about the leaking objects and their references, making it easier to address the problem.</p>
<img src="/Users/Zakir.Mohammad/Documents/personal/projects/popularmovies/PopularMovies/screen_shots/leak_canary_sample_sreen.png">
</li>
<li><b>Timber:</b><p>Timber is a popular logging library for Android applications. It provides a simple and efficient way to log messages and debug information in your Android app. Timber offers several benefits over the standard Android logging methods</p></li>
</ol>

*Note : No other network interceptor and crashing tools are added [Chuck/Stetho/Crashlytics/Sentry]*

<h3>App Screens</h3>
<ol>
<li>
Splash Screen: Stay for 3 seconds and then navigate to Home Screen.<br>
<img src="https://github.com/zakirpervez/PopularMovies/assets/16011892/38ba398c-e602-4667-b7b3-301bec6cdfb8" width="360px" height="640px"/>
</li>

<li>
Home Screen: Show list of movies. User can click any movie to check its details.<br>
<img src="https://github.com/zakirpervez/PopularMovies/assets/16011892/318a3011-0cd1-4147-abe9-afc8bd0b0f85" width="360px" height="640px"/>
</li>

<li>
Movie Details Screen: Show movie details like title, release date, rating, overview etc.<br>
<img src="https://github.com/zakirpervez/PopularMovies/assets/16011892/bda2e02e-a4ec-4e0e-be78-9c73a17fc65e" width="360px" height="640px"/>
</li>

<li>
No data screen: Display a message or take action when the API doesn't return any data or when an error occurs.<br>
<img src="https://github.com/zakirpervez/PopularMovies/assets/16011892/08ff2a59-8ed5-452d-836f-275973cc7c1e" width="360px" height="640px"/>
</li>

<li>
No internet connectivity screen: Display a message when there is no internet connectivity changes<br>
<img src="https://github.com/zakirpervez/PopularMovies/assets/16011892/5a1c5533-1795-4701-b3c4-ebaa35bc7c92" width="360px" height="640px"/>
</li>
</ol>
