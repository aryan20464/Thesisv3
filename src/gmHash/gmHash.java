package gmHash;

import java.sql.*;
import java.util.ArrayList;
import SABform.SABcheck;

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

            ArrayList<ArrayList> gmhash_series = new ArrayList<>();

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
            int count=0;
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
                    System.out.println("Series" + (i + 1) + "with count" + dc +"   Series "+(count+1));
                    count++;
                    ArrayList<String> series_att = new ArrayList<>();
                    series_att.add(pHash.get(k));
                    for (int g = k; g < (k + dc); g++)
                    {
                        System.out.println(pHash.get(g) + " " + id.get(g) + " " + diary_no.get(g));
                        series_att.add(diary_no.get(g));
                    }
                    System.out.println("\n");
                    gmhash_series.add(series_att);
                }
            }


            System.out.println(gmhash_series.size());

            for(int out=0;out<gmhash_series.size();out++)
            {
                System.out.println("Series validated : "+ (out+1));
                for(int inn=0;inn<gmhash_series.get(out).size();inn++)
                {
                    System.out.println(gmhash_series.get(out).get(inn));
                }
            }

            Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny", "root", "2113");
            Statement st2 = con2.createStatement();
            ResultSet rs2 = st2.executeQuery("select idval from burgleform");

            ArrayList<String> org = new ArrayList<>();

            while(rs2.next())
            {
                org.add(rs2.getString(1));
            }

            //checking the sab form data:
            SABcheck.check_gmHash_entries(gmhash_series);

            /*
            System.out.println("Checked datta with SAB form");
            for(int m=0;m<456;m++)
            {
                System.out.println("Series : "+ m +" phash "+gmhash_series.get(m).get(0));
                for(int n=1;n<gmhash_series.get(m).size();n++)
                {
                    for(int bf=0;bf<9110;bf++)
                    {
                        if(gmhash_series.get(m).get(n).equals(org.get(bf)))
                        {
                            System.out.println("Entry present "+gmhash_series.get(m).get(n));
                        }
                    }
                }
            }
            System.out.println(gmhash_series.get(452));*/

            //NOTE: Uncomment above code and comment the below function call if necessary it is done in this manner to make it
            // as code more readable as per modules

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
