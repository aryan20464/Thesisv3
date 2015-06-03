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


/**
 * Created by chakri on 1/6/15.
 */
public class clinked
{
    public void clinked_connect()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select distinct(Combined),Spat,id1,id2 from linked order by Spat asc");

            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny", "root", "2113");
            Statement st1 = con1.createStatement();
            ResultSet rs1 = st1.executeQuery("select distinct(Spat),Combined,id1,id2 from linked order by Spat asc");

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
            for(int i=0;i<id1.size();i++)
            {
                System.out.println(i + 1 + " " + id1.get(i) + " " + id2.get(i) + " " + combi.get(i) + " " + spat.get(i));
            }
            System.out.println("\n dataset 2");
            for(int i=0;i<id1c.size();i++)
            {
                System.out.println(i+1 +" "+id1c.get(i)+" "+id2c.get(i)+" "+combic.get(i)+" "+spatc.get(i));
            }

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