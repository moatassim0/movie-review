Introduction

CinePhile is the term we chose to title our app as the meaning of the word is used to refer to a person with a passionate interest in films, film theory, and film criticism, the app has been designed to solve a problem targeting a niche audience who enjoy keeping track of their hobby about movies in an organized and appealing structure that is easy to implement, display and share to others who share the same interests. 
The movie review app allows users to browse a wide range of movies, as well as viewing a curated list that is based off of different multiple factors such as rating, release date, views, etc., all available to the user to leave and view reviews, as well as added options to interact with the movie.

User Interface (UI) Design

The user interface has been carefully designed and it is consistent with all of the elements that the app offers. We opted for purple and yellow theme because these two are complementary colors, they form a contrast that works well and made sure this theme was across the app.

It is our intention from the start to ensure smooth navigation and dynamic movie-related content. The application will use a ConstraintLayout as its root layout, which allows flexibility in design and responsiveness towards different screen sizes. The basic structure of navigation is simple, centered on a bottom navigation bar, containing three buttons: Home, Directory, and Personal. Each button corresponds to which page it’s on using a different colored icon to indicate the location the user is currently on. The buttons navigate, on click, to a different Activity or Fragment in order to explore different app sections. The Home screen displays multiple sections of curated selections, "Most Watched”, “Recently Added" and “Highly Rated”; each is a horizontal row of scrollable ImageButton widgets, each button corresponding to a movie image. These are buttons that are clickable and attached to an onClick method, each selection includes a "View All" link to expand the content which will be displayed in the Directory activity that has all the movies available in our database. LinearLayouts have been utilized under the Root ConstrainedLayout to arrange content both vertically and horizontally, providing a clear hierarchy and consistent spacing, and finally the personal page has the same horizontal scroll layout to display reviewed and favorited movies, Overall, the application is designed for ease of use; visually appealing, and focuses on fluid intuitive navigation and dynamic content display.

<img width="306" height="616" alt="image" src="https://github.com/user-attachments/assets/2760c524-05e9-4c1b-ab87-f2d5a58eec63" />
<img width="299" height="615" alt="image" src="https://github.com/user-attachments/assets/0178f1f7-d48e-4f6e-8f90-eed15eb650c7" />
<img width="299" height="619" alt="image" src="https://github.com/user-attachments/assets/97a8e619-d780-4bc5-bd08-c26455aeb320" />

Database Schema and Design

The database is designed for efficient handling of users and movies, this will follow a relational model that can store many types of information across different tables. Key entities are the Users, Directors, Movies, Favorites, Reviews, and Views. The Users table will capture user credentials and account details, and the Movies entity would include a record for every movie, this includes title, duration, release year, genre, restriction and the cumulative number of views and reviews both stored as an integer. Favorites is a weak entity that stores the primary key id’s of a user and a movie to record the user’s favorited movies; Views entity works the same way as Favorites except it has an attribute that checks of a user has watched a certain movie or not. Reviews table is where the detailed reviews and ratings of a movie are stored based on user and movie id’s, and finally Directors is an entire table dedicated to add extra information for every movie’s producer. Access to all this data is managed by SQL queries executed in the app when displaying necessary data in the User Interface.

<img width="974" height="589" alt="image" src="https://github.com/user-attachments/assets/b46ac018-73fb-4388-bbdb-7a0f423a9bcf" />
<img width="975" height="618" alt="image" src="https://github.com/user-attachments/assets/b56689de-8206-4b80-9cce-a74cba0a272e" />

Functionality and Features

The application’s several core functionalities that are essential for providing a dynamic and interactive user experience include managing movie reviews, marking movies as favorites or viewed, and updating user ratings.

The Movie.java activity plays a major role in such functionality because it loads movie details, including buttons for user interaction like "Favourite" and "Seen", and a list of users' reviews using RecyclerView. Each review is represented by ReviewModel object with the name of a user, score, and the content of the review. The app uses various database operations, such as getMovieByMovieID, getReviewsByMovieID, and more, to fetch and manipulate data like movie details, user reviews, and favorite status. For example, the onClickFavouriteButton and onClickSeenButton methods toggle the movie's favorite or seen status in the database and update the UI accordingly. The onClickAddReviewButton lets users submit their reviews, which are then inserted into the database and trigger a refresh of the activity to show the new review at the top of the stack. The updateFavorites method retrieves four recently favorited movies from the database through getUserRecentFavorites and assigns them to corresponding ImageButton views. Each button displays the movie's poster and is tagged with the movie's ID for future reference. Similarly, updateReviewed loads recently reviewed movies using the getUserRecentReviews method and populates the corresponding UI elements.

Testing and Debugging

The app was mainly tested on a Pixel 3a phone running SDK 35, ensuring that it will work on a wide range of devices running different versions of Android. Many test cases were run to ensure separate functionality on specific tasks such as user login, movie review and submissions. Logcat was used most during testing to debug and check for errors; logs on app crashes, UI-related issues, and network problems were checked. Performance testing focused on responsiveness, low load times, and smooth transitions between activities which were the navigation bar buttons at the bottom, Compatibility was pursued for different screen sizes and through multiple iterations of Android versions ranging from SDK 26 until 35. Any bugs found were resolved through log reviews, code refinement, and performance optimization.

User Manual

To be able to use our app, you first have to download the APK file, this will be made possible after requesting for the download link from the team members. After you acquire the APK file, copy it into your android device if required and install it.

After running our app:

1.	Registering an Account:

When you open the application for the first time, you will need to create an account. Registration is simple and straightforward- just provide a username, an email address, password, and your gender to sign up.

<img width="525" height="1139" alt="image" src="https://github.com/user-attachments/assets/f07c11c7-f42e-4f6a-988e-7f69f498bfaa" />

2.	Opening the application:

After registering, you will be directed to the Home screen which is the main interface of the application. You will see the sections of interests in front of you, where you will find a selection of prepared categories such as “Most Watched,” “Recently Added,” and “Highly Rated.” These categories are helpful in showcasing a new user what movies are particularly popular.

<img width="525" height="1103" alt="image" src="https://github.com/user-attachments/assets/61d426ca-cacb-4ddf-91e1-0139d35e60b6" />

3.	Adding Movies to your Favorites:

To make your experience unique, add movies of your choice to your favorites. Once clicking on a movie from the Home/Movies Page, you can click on “Favorite”. This will inform the system that the movie is now saved in your profile.

<img width="505" height="1118" alt="image" src="https://github.com/user-attachments/assets/ca98b15b-32b7-447f-9922-9518db4ab21d" />

4.	Rating/writing Reviews:

CinePhile allows you to interact furthermore with movies by rating them or writing reviews. In order to do this, pick a movie from the collection, give it a star rating, and add your personal creative thoughts in the review. Your reviews will be stored in the system and linked to your profile upon submission.

<img width="510" height="1093" alt="image" src="https://github.com/user-attachments/assets/94213cb9-8a65-4da1-9f39-1531a13f79ff" />

5.	Profile Page:

CinePhile keeps a record of all activities relating to your profile and what you interact with on the app. The Profile page will show when you click the user icon in the bottom navigation bar and all the movies you watched will appear, including all the movies you rated and reviewed. This is how the system works as all of the activities are linked to a profile feature that makes the user’s interactions seamless and easy.

<img width="512" height="1097" alt="image" src="https://github.com/user-attachments/assets/4bf3634d-218f-4326-a361-9eec48ab155d" />









