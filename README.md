<h1>PopularMovies</h1>
<p>The PopularMovies app helps you find classic favourite movies and lets you see more information about them.</p>

<h2>Getting Started</h2>

<h3>Software Stack:</h3>
<ol>
<li>Android SDK</li>
<li>Kotlin</li>
<li>XML</li>
<li>Constraint/Motion Layout</li>
<li>Jetpack Navigation Component</li>
<li>Jetpack Pagging</li>
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

![mvvm_architecture](https://github.com/zakirpervez/PopularMovies/assets/16011892/6a215e81-9461-4b6b-ba4f-022c63e5c1ce)


<h3>Testing</h3>
1. Unit Testing: Unit testing is done using Junit4 and mockK.
2. Instrumentation Testing: No Instrument testing is done [Note: Please let me know if you want me to add the instrument test cases.]

<h3>Testing</h3>
<p>As of now I am not proguarding/dexguarding the apk as well as not providing in api key and network security.</p>

<h3>Code analysis</h3>
<p>Integrated the Ktlint tool for code analysis.</p>

<h3>App Screens</h3>
1. Splash Screen: Stay for 3 seconds and then navigate to Home Screen.
![Splash_screen](https://github.com/zakirpervez/PopularMovies/assets/16011892/ecb36326-03c1-4fee-8a3f-3512eaa8b898)

2. Home Screen: Show list of movies. User can click any movie to check its details.
![Screenshot_1697441042](https://github.com/zakirpervez/PopularMovies/assets/16011892/5b16c41d-c439-418e-8cfb-517735f10272)

3. Movie Details Screen: Show movie details like title, release date, rating, overview etc.
![Screenshot_1697486049](https://github.com/zakirpervez/PopularMovies/assets/16011892/b81b96b6-05e1-49b6-801f-4d6b9b2aad9a)

<h3>Pending Work</h3>
<ul>
<li>No data screen handling</li>
<li>Crashlytics, LeakCanary, Detekt integrations</li>
<li>Code optimization and profiling</li>
<li>Instrumentation test cases</li>
</ul>





