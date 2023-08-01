/**
 * Contains the necessary methods to create one instance of a Decal object
 * 
 * @author   Luis K. Pena
 * @version  1.0
 * @since    04/20/2022
 */
 
 
 public class Decal
 {
 
   //Instantiating private variables
   
   private int decal;
   private int license;

   
   //Constructor for the Decal object
   
   public Decal(int userDecal, int userLicense)
   {
      decal = userDecal;
      license = userLicense;
   }
   
   
   //Setters for Decal object data
   
   public void setDecal(int newDecal)
   {
      decal = newDecal;
   }
   
   public void setLicense(int newLicense)
   {
      license = newLicense;
   }
   
   
   //Getters for Decal object data
   
   public int getDecal()
   {
      return decal;
   }
   
   public int getLicense()
   {
      return license;
   }
   
   
   //toString override method;
   
   public String toString()
   {
      return "Plate #: " + license;
   }   
 }