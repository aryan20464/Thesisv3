package gmHash;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by chakri on 1/6/15.
 */
public class gmHash
{
    public void dataset2()
    {
        try
        {
            ArrayList<Integer> id = new ArrayList<>();
            ArrayList<String> diary_no = new ArrayList<>();
            ArrayList<String> pHash = new ArrayList<>();
            ArrayList<String> dhash = new ArrayList<>();

            /* This is connection for getting all records in the gmHash in the ascending order of the pHash*/
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select distinct(pHash), id, diarienummer from gmHash order by pHash asc");

            /* This is connection for getting unique pHash 'es in the ascending order of the pHash*/
            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery("select distinct(pHash) from gmHash order by pHash asc");

            while(rs.next())
            {
                pHash.add(rs.getString(1));
                id.add(rs.getInt(2));
                diary_no.add(rs.getString(3));
            }

            while(rs1.next())
            {
                dhash.add(rs1.getString(1));
            }

            for(int i=0;i<pHash.size();i++)
            {
               // System.out.println(id.get(i)+" "+pHash.get(i)+"  "+"  "+diary_no.get(i));
            }

            System.out.println("\n-------------------------------------------- calculated dhashes------------------------------------------------------------------------------------------------------------------------------------ \n");

            for(int i=0;i<dhash.size();i++)
            {
                //System.out.println(dhash.get(i));
            }

            System.out.println("\n--------------------------------------------my calci--------------------------------------------------------------------------------------------------------------------------------------------------------\n");

            for(int i=0;i<dhash.size();i++)
            {
                for(int j=0;j<pHash.size();j++)
                {
                    if(dhash.get(i).equals(pHash.get(j)))
                    {
                       // System.out.println(pHash.get(i)+ " "+ id.get(i)+"  "+diary_no.get(i));
                    }

                }
            }
            System.out.println("distincts");

            for(int i=0;i<dhash.size();i++)
            {
                int k=0;
                int dc=0;
                String s = dhash.get(i);
                PreparedStatement cmre = con.prepareStatement("select count(pHash) from gmHash where pHash=?");
                cmre.setString(1,s);
                ResultSet rsr = cmre.executeQuery();
                while(rsr.next())
                {
                    //System.out.println(rsr.getInt(1)+" "+s);
                    dc = rsr.getInt(1);
                }
                for(int j=0;j<pHash.size();j++)
                {
                    if(pHash.get(j).equals(s))
                    {
                        k=j;
                        break;
                    }
                }
                if(dc>=2) {
                    System.out.println("Series" + (i + 1) + "with count" + dc);
                    for (int g = k; g < (k + dc); g++) {

                        System.out.println(pHash.get(g) + " " + id.get(g) + " " + diary_no.get(g));
                    }
                    System.out.println("\n");
                }

            }
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
