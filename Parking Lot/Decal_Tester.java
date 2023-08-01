/**
 * Contains the necessary methods to create one intance of a Car object
 * 
 * @author   Luis K. Pena
 * @version  1.0
 * @since    04/20/2022
 */
 
import java.util.*;
 
public class Decal_Tester
{
   public static void main(String[] args)
   {
      
      //Instantiating variables
      
      int min = 10000;
      int max = 99999;
      int userChoice = 1;
      int queueSpot = 0;
      boolean correctDecal = false;
      String carInQueue = "";
      
      
      //Instantiating number randomizer
      
      Random rand = new Random();
      
      
      //Instantiating Scanner object for user input
      
      Scanner menuChoice = new Scanner(System.in);
      
      
      //Instantiating parking lot stacks and queues
      
      Stack<Decal> carsParked = new Stack<>();
      Stack<Decal> tempParking = new Stack<>();
      Queue<Decal> waitList = new LinkedList<>();
      
      
      //Prepopulating parking lot with 10 vehicles
      
      for(int i = 0; i < 10; i++)
      {
         Decal currVehicles = new Decal(rand.nextInt(max-min) + min, rand.nextInt(max-min) + min);
         carsParked.push(currVehicles);
      }
      
        
      //U.I. header
      
      System.out.println("\n-= Welcome to FIU Panther Parking =-");
      

      //While loop for main menu
      
      while(userChoice != 0)
      {
      
         //Instantiating local variables
         
         int totalSpaces = 15 - carsParked.size();
         int licensePlate;
         
         
         //Display method for vehicles in lot
               
         System.out.println("\nVehicles currently parked: \n");
         
         if(carsParked.isEmpty())
         {
            System.out.println("No vehicles."); 
         }
         
         else
         {
            System.out.println(carsParked.toString().replaceAll("\\[|\\]", "")); 
         }
        
         
         //Display method for vehicles in wait list
        
         System.out.println("\nVehicles currently in queue: \n");
                           
         if(waitList.isEmpty())
         {
            System.out.println("No vehicles."); 
         }
         
         else
         {
            System.out.println(waitList.toString().replaceAll("\\[|\\]", "")); 
         }


         //Parking decal generator
         
         System.out.println("\nPlease enter 0 to quit. Otherwise enter '+' if you " 
                           + "wish to park or '-' if you wish to retreive a vehicle, "
                           + "followed by your license plate number: \n");
         
         userChoice = menuChoice.nextInt();
         
         licensePlate = userChoice;
         
         Decal newVehicle = new Decal(rand.nextInt(max-min) + min, 00000);                       
         
         
         //Car park method if there is space available and queue is empty
         
         if(licensePlate > 0 && newVehicle.getDecal() >= 50000  && waitList.isEmpty() 
           && totalSpaces > 0)
         {  

            newVehicle.setLicense(licensePlate);
            
            carsParked.push(newVehicle);
            
            totalSpaces--;
            
            System.out.println("\nYou may park your vehicle. Thank you!");
          }
          
          
          //Queue method if there are no spaces available
            
          else if(licensePlate > 0 && newVehicle.getDecal() >= 50000 && totalSpaces <= 0)
          { 
             newVehicle.setLicense(licensePlate);
             
             waitList.add(newVehicle);
             
             System.out.println("\nThere are currently no parking spaces available.");
             
             System.out.println("\nYou have been added to the queue. "
                               + "There is/are currently " +queueSpot 
                               + " vehicle(s) ahead of you.");
             
             queueSpot++;  
          }
         
         
         //Method to inform user they lack necessary decal
             
         else if(licensePlate > 0 && newVehicle.getDecal() < 50000)
         {
            System.out.println("\nSorry, you do not have the necessary parking permit "
                              + "to park here.");
         }
      
      
         //Vehicle retrieval method
      
         else if(licensePlate < 0)
         {
         
            //Instantiating local variables
            
            int i = carsParked.size() - 1;
            int userSpot = 0;
            String yourVehicle = "";
            
            
            //Method for getting vehicle index in stack
            
            licensePlate *= -1;
            
            for(int j = 0; j < carsParked.size(); j++)
            {
               if(carsParked.get(j).getLicense() == licensePlate)
               {
                  userSpot = j;
                  yourVehicle = carsParked.get(userSpot).toString();
               }
            }
            
            
            //While loop to move vehicles to temporary parking
            
            while(i > userSpot)
            {
               tempParking.push(carsParked.peek());
               carsParked.pop();
               i--;
            }
            
            carsParked.pop();
            
            
            //While loop to move vehicles back to parking garage
            
            while(!tempParking.empty())
            {
               carsParked.add(tempParking.peek());
               tempParking.pop();
            }
            
            System.out.println("\nWe have retreived the following vehicle:\n\n"
                              + yourVehicle); 
            
            if(!waitList.isEmpty())
            {
               carsParked.push(waitList.peek());
               carInQueue = waitList.peek().toString();
               waitList.remove();
               
               System.out.println("\nThe following vehicle has been moved from the "
                             + "wait list to the parking lot:\n\n"
                              + carInQueue); 
           }                                        
         }
         
         
         //Program termination when user quits
         
         if(userChoice == 0)
         {
            System.out.println("\nThank you for using Panther Parking! Have a great day!");
            System.exit(0);
         }
      }
   }
}