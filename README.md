# Puppy on diet

## A Simulation Game

This application is a game which animal-lovers will like.

The ultimate goal of this game is helping an obese puppy to lose
weight, and the player will be the owner. Your choices will determine 
if the puppy can become healthy again.

I got the inspiration from a mobile game called *Travel Frog*,
which is a kind of Simulation Game. I want to adopt some interesting 
features from *Travel Frog*, and add some unique elements into
my application.

**Feature Systems**
- Feeding System
- Workout System

## User Stories

- As a user, I want to be able to add a dish to puppy's food recipe.
- As a user, I want to view all dishes in puppy's food recipe.
- As a user, I want to delete a dish from the recipe.
- As a user, I want to check the calories of a dish.
- As a user, I want to see the total calories in the recipe.
- As a user, I want to be able to save recipe to file
- As a user, I want to be able to optionally load my recipe from file when the program starts.

### Instructions for Grader
- Click "New Game"  to start a new game.
- In the main menu, click "Feed Dog". You can add any non-negative amounts of dishes into a recipe.
- After entering amounts, click "Preview" to check the total calories contained in the recipe.
- Click "Save Recipe" to save this recipe, you can check the saved recipe in the main menu by clicking "View Saved Recipe".
- Start a game can see the background.
- You can reload data by restarting game and clicking "Resume Game".

### Phase 4: Task 2
###### Test and design a class that is robust.
   - In UI-GUI class, load() method catches IOException and ParseException when Reader cannot load data from the appointed file.
###### Include a type hierarchy in your code other than the one that uses the Saveable interface introduced in Phase 2.
   - In model, PlayWithDog and WalkDog, two classes extend Workout class which is an abstract class.