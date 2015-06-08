package linked;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import SABform.SABcheck;


/**
 * Created by chakri on 1/6/15.
 */
public class clinked
{
    /**
     * this method is used for finding the linked cases in the linked table. Every crime in the this is present in burgleform
     */

    public void clinked_connect()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny", "root", "2113");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select distinct(Combined),Spat,id1,id2 from linked order by Spat asc");

            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny", "root", "2113");
            Statement st1 = con1.createStatement();
            ResultSet rs1 = st1.executeQuery("select distinct(Spat),Combined,id1,id2 from linked order by Spat asc");

            Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny", "root", "2113");
            Statement st2 = con2.createStatement();
            ResultSet rs2 = st2.executeQuery("select idval from burgleform");

            ArrayList<String> org = new ArrayList<>();
            while(rs2.next())
            {
                org.add(rs2.getString(1));
            }

            ArrayList<String> id1 = new ArrayList<>();
            ArrayList<String> id1c = new ArrayList<>();
            ArrayList<String> id2 = new ArrayList<>();
            ArrayList<String> id2c = new ArrayList<>();
            ArrayList<Float> combi = new ArrayList<>();
            ArrayList<Float> combic = new ArrayList<>();
            ArrayList<BigDecimal> spat = new ArrayList<>();
            ArrayList<BigDecimal> spatc = new ArrayList<>();

            while(rs.next())
            {
                id1.add(rs.getString(3));
                id2.add(rs.getString(4));
                combi.add(rs.getFloat(1));
                spat.add(rs.getBigDecimal(2));
            }
            while(rs1.next())
            {
                id1c.add(rs1.getString(3));
                id2c.add(rs1.getString(4));
                combic.add(rs1.getFloat(2));
                spatc.add(rs1.getBigDecimal(1));
            }


            System.out.println("dataset 1");
            System.out.println("S.no\tid1\tid2\tCombined\tSpatial.");
            for(int i=0;i<id1.size();i++)
            {
                System.out.println("Series "+(i+1) + "\t" + id1.get(i) + " \t " + id2.get(i) + "\t " + combi.get(i) + "\t " + spat.get(i));
            }
            System.out.println("\n dataset 2");
            for(int i=0;i<id1c.size();i++)
            {
                System.out.println(i+1 +" "+id1c.get(i)+" "+id2c.get(i)+" "+combic.get(i)+" "+spatc.get(i));
            }

            /*
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
            */
            //NOTE: Uncomment above code and comment the below function call if necessary it is done in this manner to make it
            // as code more readable as per modules
            SABcheck.check_linked(id1,id2);


            for(int i=0;i<id1c.size();i++)
            {
                if(!id1.get(i).equals(id1c.get(i)) ||
                        !id2.get(i).equals(id2c.get(i))||
                        !combi.get(i).equals(combic.get(i))||
                        !spat.get(i).equals(spatc.get(i)))
                {
                    System.out.println("not same");
                }
                else
                {
                    System.out.println(i+"same");
                }
            }


        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}