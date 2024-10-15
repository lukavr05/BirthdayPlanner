This program takes in a list of cafes, restaurants and activities and outputs them based on specific rules provided by the brief.

The goal of this project is to implement a program that generates a personalised itinerary for a friend’s birthday. The day will include three types of activities: main activities (such as an escape room), cafe stops, and restaurants. For personalisation, it takes an input word (e.g. a person’s name) as its argument and creates a list of activities in which the first letter(s) of each item spell the given word.

The birthday planner relies on the ActivityStore class for mapping sequences of one or more characters to lists of activities. Mathematically, the structure corresponds to a partial function from strings to sets of strings.

Activities in the ActivityStore are “main” activities. Activities in CafeStore and Restaurant store are “eating” activities. To ensure balanced plans are generated:

 - each plan starts with a cafe or a main activity;
 - eating activities do not occur twice in a row;
 - main activities appear at most twice in a row (but can appear alone); and
 - there is at most one restaurant activity.

 ActivityStore has another constructor that loads a activity list and has another argument. This argument gives the maximum prefix length (e.g., 2 or 3). While reading the file, the constructor then, for each item, adds associations for all prefixes up to that maximum length. Items shorter than, or of equal length to the current prefix length are be ignored. For instance, for a prefix length of 3 and the activity “bungee jumping”, all of the following mappings are added.

    b ↦ bungee jumping
    bu ↦ bungee jumping
    bun ↦ bungee jumping
