package OODCwk; 


/**
 * Details of your team
 * 
 * @author (Wojtek Baranowski)
 * @version (a version number or a date)
 */
public class Teamwork
{
    private String[] details = new String[7];
    
    public Teamwork()
    {   // in each line replace the contents of the String 
        // with the details of your team
        // It will help us if the surname of programmer1 comes
        // alphabetically before the surname of programmer2
        details[0] = "Bing Bong";
        details[1] = "Baranowski";
        details[2] = "Wojtek";
        details[3] = "18025815";
        details[4] = "Khan";
        details[5] = "Shehiryar Sabir";
        details[6] = "18040210";
    }
    
    public String[] getTeamDetails()
    {
        return details;
    }
    
    public void displayDetails()
    {
        for(String temp:details)
        {
            System.out.println(temp.toString());
        }
    }
}
        
