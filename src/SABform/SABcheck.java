package SABform;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by chakri on 7/6/15.
 */
public class SABcheck
{
    /**
     * this method is used for checking crimes in the linked and gmHash with burgleform
     */

    public static void check_linked(ArrayList<String> id1, ArrayList<String> id2)
    {
        try {
            Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny", "root", "2113");
            Statement st2 = con2.createStatement();
            ResultSet rs2 = st2.executeQuery("select idval from burgleform");

            ArrayList<String> org = new ArrayList<>();
            while(rs2.next())
            {
                org.add(rs2.getString(1));
            }

            for(int m=0;m<29;m++)
            {
                System.out.println("entry "+(m+1)+" "+id1.get(m)+" "+id2.get(m));
                for(int k=0;k<org.size();k++)
                {
                    if(org.get(k).equalsIgnoreCase(id1.get(m)))
                    {
                        System.out.println("this entry is present "+id1.get(m)+"at : "+k);
                    }
                    if(org.get(k).equalsIgnoreCase(id2.get(m)))
                    {
                        System.out.println("this entry is present "+id2.get(m)+"at : "+k);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("SQLexcpetion in SAB check file in check linked function");
            e.printStackTrace();
        }

    }

    public static void check_gmHash_entries(ArrayList<ArrayList> gmhash_series)
    {
        try {
            Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny", "root", "2113");
            Statement st2 = con2.createStatement();
            ResultSet rs2 = st2.executeQuery("select idval from burgleform");

            ArrayList<String> org = new ArrayList<>();
            while (rs2.next()) {
                org.add(rs2.getString(1));
            }
            System.out.println("\n this is the series present in the sab form \n");
            //remove this if needed
            ArrayList<ArrayList> finalouter = new ArrayList<>();



            //till here
            int no_crimes=0;
            for(int m=0;m<456;m++)
            {
                ArrayList<String> finalinner = new ArrayList<>();//remove
                finalinner.add(gmhash_series.get(m).get(0).toString());//remove
                int display=0;int inncnt=0;
                for(int n=1;n<gmhash_series.get(m).size();n++)
                {
                    inncnt=0;
                    for(int bf=0;bf<9110;bf++)
                    {
                        if(gmhash_series.get(m).get(n).equals(org.get(bf)))
                        {
                            System.out.println("Entry present "+gmhash_series.get(m).get(n));
                            finalinner.add(gmhash_series.get(m).get(n).toString());
                            display=1;inncnt++;
                        }
                    }
                }
                if(display==1)
                {
                    System.out.println("\nSeries : "+ m +" phash "+gmhash_series.get(m).get(0)+"crimes list:"+gmhash_series.get(m)+"\n");
                    no_crimes++;
                    finalouter.add(finalinner);//remove
                }
            }
            //System.out.println(gmhash_series.get(452));
            System.out.println("\n no of crimes series are:"+no_crimes);
            System.out.println("\n \ntest sereis "+finalouter.size());
            for(int j=0;j<finalouter.size();j++)
            {
                System.out.println(j);
                System.out.print(finalouter.get(j));
                System.out.println("\n");
            }

            //Final dataset for gmHash
            System.out.println("The final dataset ");
            int finalcount=0;int x=0;
            for(int j=0;j<finalouter.size();j++)
            {
                if(finalouter.get(j).size()>2)
                {
                    x++;
                    System.out.println("series "+x+" \t "+finalouter.get(j)+"\n");
                    //System.out.println("series "+j+" \t "+finalouter.get(j)+"\n"); if you want to verify with above output for series number
                    finalcount++;
                }
            }
            System.out.println("\n Final dataset size:"+finalcount);



        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
