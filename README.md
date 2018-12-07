# BookListingAppUdacity
BookListingAppUdacity is an app in which you can search for a book and it displays a list of books according to your query.

### Features
* The user can enter a word or phrase to serve as a search query. The app fetches book data related to the query via an HTTP request from the Google Books API, using a class such as HttpUriRequest or HttpURLConnection.
* The app checks whether the device is connected to the internet and responds appropriately. The result of the request is validated to account for a bad server response or lack of server response.
* The network call occurs off the UI thread using an AsyncTask or similar threading object.
* The JSON response is parsed correctly, and relevant information is stored in the app.
* The ListView is properly populated with the information parsed from the JSON response.
* When there is no data to display, the app shows a default TextView that informs the user how to populate the list.
* The intent of this project is to give you practice writing raw Java code using the necessary classes provided by the Android framework; therefore, the use of external libraries for core functionality will not be permitted to complete this project.
