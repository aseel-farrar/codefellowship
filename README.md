# Labs 16 and 17: CodeFellowship Login, Profiles & Posts

> ## Routes:
* `@GetMapping("/signupPage")` ->> render Signup Page
* `@GetMapping("/login")` ->> render login Page
* `@PostMapping("/signup")` --> create new Application user
* `@GetMapping("/myProfile")` --> render the logged in user profile page
* `@PostMapping("/myProfile")` --> render the profile page ***after creating new post***.
* `@GetMapping("/users/{id}")` --> render the profile page for specific user by its ***id***.


> ## Templates
* `index` --> home page (used to handel the main route`/`)
* `error` --> used to handling whitelabel error.
* `fragments` --> used to store all the fragment used in this web app.
* `login` --> used to preview the login form.
* `signup` --> used to preview the signup form.
* `profile` --> used to preview the user details and post