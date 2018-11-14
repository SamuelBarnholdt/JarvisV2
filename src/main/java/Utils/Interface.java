package Utils;

import java.io.IOException;
import java.util.Scanner;
import Jarvis.Jarvis;
import java.io.Console;

public class Interface {
    public Interface() throws Exception{
        this.jarvis = new Jarvis("Barnholdt");
    }
    protected Jarvis jarvis;

    public void askQuestion() throws Exception{
        while(true){
            Scanner s = new Scanner(System.in);
            System.out.println("GoalsLeft/GoalsAt 'grade'/Description 'goal'/UpdateGoals/Reset");
            String answer = s.nextLine();
            if ((answer.toUpperCase().matches("(GOALSAT)\\s\\D\\d{2}(\\d)?"))) {
                int index = answer.length() - 1;
                jarvis.availableAtGrade((Character.getNumericValue(answer.charAt(index))));
            }
            if (answer.toUpperCase().equals("GOALSLEFT")) StringUtils.printGoals(jarvis.readMap());

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
                    System.out.println("Wrong username or password./No goals completed.");
                    System.out.println();
                }

            }

            if ((answer.toUpperCase().matches("(DESC)\\s\\D\\d(\\d)?"))) {
                try{
                String goal = answer.substring(5).toUpperCase();
                jarvis.getDescription(goal);
                }
                catch(Exception e){
                    System.out.println("Goal does not exist || Bad tech");
                    System.out.println();

                }
            }
            if (answer.toUpperCase().equals("RESET")) jarvis.resetGoals();

            }


    }
}
