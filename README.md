# frustration-card-game-companion
This is not a game. It's an Android app to support
playing of Frustration (card game).

Find it on Play Store (Android phones):
https://play.google.com/store/apps/details?id=com.dimiaug.frustration

You need 2 standard 52-card decks and at least 3
players. Reach me for playing instructions. :-D

# notes on design and architecture
You will find here a very-well-structured, clean
code with strong separation of concerns.

Currently it miserably (and purposely) lacks clean
architecture concepts. :-)

# Play Store published app: privacy policy
The only user data collected are players names,
used to help user identify players points in the
game's table. This data is stored locally in the
smartphone and it's never transferred to other apps
or to a server. Data is never used by the developer
to any means.

# PLAN
- introduce koin (replace with kotlin where needed to accommodate koin)
- - work play fragment (DONE) 
- - work viewmodel
- - work repository
- - work database
- apply clean architecture concepts
- introduce Jetpack Compose