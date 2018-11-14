Goalcollector for IOOPM2018.

Requirements for usage are:

1. Account at http://auportal.herokuapp.com/achievements .

2. Maven.

3. Chromedriver, see http://chromedriver.chromium.org/ .

4. This repository needs to be located in your user.home directory.


Build (disclaimer: I don't know how maven works)

mvn install

mvn exec:java -Dexec.mainClass=Main

Program:
(case insensitive)

GoalsLeft prints out remaining goals and how many they are.

GoalsAt 'grade' gives you the remaining goals at 'grade' and how many they are.

Desc 'goal' gives you the description of 'goal'.

Completed gives you the amount of goals you have completed and how many goals you have left.

UpdateGoals Updates completed goals. If several users want to check on same devis run reset first. 

Reset resets all goals.

Quit quits.





