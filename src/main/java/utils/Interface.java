package utils;

import java.io.IOException;
import java.util.Scanner;
import jarvis.Jarvis;
import java.io.Console;

public class Interface {
    public Interface() throws Exception{
        this.jarvis = new Jarvis();
    }
    protected Jarvis jarvis;

    public void askQuestion() throws Exception{
        while(true){
            Scanner s = new Scanner(System.in);
            System.out.println();
            System.out.println("GoalsLeft/GoalsAt 'grade'/Description 'goal'/UpdateGoals/Completed/Reset/Quit");
            System.out.println();
            String answer = s.nextLine();

            if (answer.toUpperCase().equals("GOALSLEFT")) StringUtils.printGoals(jarvis.readMap());

            if ((answer.toUpperCase().matches("(GOALSAT)\\s[345]"))) {
                int index = answer.length() - 1;
                jarvis.availableAtGrade((Character.getNumericValue(answer.charAt(index))));
            }
            if (answer.toUpperCase().equals("UPDATEGOALS")) {
                try{
                System.out.println("Username: ");
                String user = s.nextLine() + "@student.uu.se";
                System.out.println("Password: ");
                Console console = System.console();
                char[] password = console.readPassword();
                //String password = Password.readPassword()
                jarvis.updateGoals(user,String.valueOf(password));
                }
                catch(IOException e){
                    System.out.println("Wrong username or password || No goals completed. (remember to not add @student.uu.se)");
                }

            }

            if ((answer.toUpperCase().matches("(DESC)\\s([A-Ka-kM-Rm-rX-Zx-z])\\d{1,3}"))) {
                try{
                String goal = answer.substring(5).toUpperCase();
                jarvis.getDescription(goal);
                }
                catch(Exception e){
                    System.out.println("Goal does not exist || Bad tech");
                }
            }

            if (answer.toUpperCase().equals("RESET")) jarvis.resetGoals();

             if (answer.toUpperCase().equals("COMPLETED")) {
                 System.out.println("Completed goals: " + jarvis.numberOfCompletedGoals() + ".");
                 System.out.println("Goals left: " + jarvis.readMap().size() + ".");
             }

            if (answer.toUpperCase().equals("QUIT")) break;

        }
    }
}
