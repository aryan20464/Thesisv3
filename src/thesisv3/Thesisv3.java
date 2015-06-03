/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesisv3;

/*
The program follows the same order as the SAB file format the categories are also defined in the same order...
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import dbase.dbase;
import jaccard.jaccard;
import linked.clinked;
import gmHash.gmHash;


/**
 *
 * @author chakri
 */
public class Thesisv3 
{
    /**
     * @param args the command line arguments
     */
    
    int id, idval,count=0;
    /**
     *  It calculates the median for the form field  - "2. Type of residential area"
     * 
     * @throws IOException 
     */   
        public void cal_residential_area() throws IOException //  completed no revisit required
    {
     
        int s=0;
        ArrayList<Integer> neighbone  = new ArrayList<>();
        ArrayList<Integer> neighbsev = new ArrayList<>();
        ArrayList<Integer> neighbourless = new ArrayList<>();
        ArrayList<Integer> corner = new ArrayList<>();
        ArrayList<Integer> woods = new ArrayList<>();
        
        try
        {
             Connection conr = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
             Statement cmr = conr.createStatement();
             ResultSet rsr = cmr.executeQuery("select neighbone, neighbsev, neighbourless, corner, woods from burgleform");
             
              while(rsr.next())
             { 
               neighbone.add((int)rsr.getByte("neighbone"));
               neighbsev.add((int)rsr.getByte("neighbsev"));
               neighbourless.add((int)rsr.getByte("neighbourless"));
               corner.add((int)rsr.getByte("corner"));
               woods.add((int)rsr.getByte("woods"));
             }
        
              median_of_crimes_known(neighbone);
              median_of_crimes_known(neighbsev);
              median_of_crimes_known(neighbourless);
              median_of_crimes_known(corner);
              median_of_crimes_known(woods);       
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        
    }
      public void type_of_residency() throws IOException // completed no revisit required
    { 
        ArrayList<Integer> r1 = new ArrayList<>();
        ArrayList<Integer> r2 = new ArrayList<>();
        ArrayList<Integer> r3 = new ArrayList<>();
        ArrayList<Integer> r4 = new ArrayList<>();
        ArrayList<Integer> r5 = new ArrayList<>();
        ArrayList<Integer> r6 = new ArrayList<>();
        ArrayList<Integer> r7 = new ArrayList<>();
        ArrayList<Integer> r8 = new ArrayList<>();
        ArrayList<Integer> r9 = new ArrayList<>();
        ArrayList<Integer> r10 = new ArrayList<>();
        ArrayList<Integer> r11 = new ArrayList<>();
        ArrayList<Integer> r12 = new ArrayList<>();
        try
        {
            Connection rcon = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
            Statement rst = rcon.createStatement();      //one till here    |                                        | one other field from here
            ResultSet rset = rst.executeQuery("select stdhigh,stdnorm,stdlow,villa,farm,townhouse,aptowned, aptrental,multilvl, singlelvl,apttop,aptbottom from burgleform");
            while(rset.next())
            {
                r1.add((int)rset.getByte("stdhigh"));
                r2.add((int)rset.getByte("stdnorm"));
                r3.add((int)rset.getByte("stdlow")); 
                // till here one field
                r4.add((int)rset.getByte("villa"));
                r5.add((int)rset.getByte("farm"));
                r6.add((int)rset.getByte("townhouse"));
                r7.add((int)rset.getByte("aptowned"));
                r8.add((int)rset.getByte("aptrental"));
                //another field from here
                r9.add((int)rset.getByte("multilvl")); 
                r10.add((int)rset.getByte("singlelvl"));
                r11.add((int)rset.getByte("apttop"));
                r12.add((int)rset.getByte("aptbottom"));
            }
            invalid_median_res(r1,"stdhigh");
            invalid_median_res(r2,"stdnorm");
            invalid_median_res(r3,"stdlow");
            median_of_crimes_known(r4);
            median_of_crimes_known(r5);
            median_of_crimes_known(r6);
            median_of_crimes_known(r7);
            median_of_crimes_known(r8);
            median_of_crimes_known(r9);
            median_of_crimes_known(r10);
            median_of_crimes_known(r11);
            median_of_crimes_known(r12);
        }
        catch(SQLException s)
        {
            System.out.println(s);
        }
        
    }
    public void cal_standard_object() throws IOException // completed no revisit required
    {
        
        ArrayList<Integer> so = new ArrayList<>();
        ArrayList<Integer> so1 = new ArrayList<>();
        ArrayList<Integer> so2 = new ArrayList<>();
        ArrayList<Integer> so3 = new ArrayList<>();
        ArrayList<Integer> so4 = new ArrayList<>();
        ArrayList<Integer> so5 = new ArrayList<>();
        ArrayList<Integer> so6 = new ArrayList<>();
        ArrayList<Integer> so7 = new ArrayList<>();
        ArrayList<Integer> so8 = new ArrayList<>();
        
        try
        {
             Connection sobj = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
             Statement sob = sobj.createStatement();
             ResultSet sor = sob.executeQuery("select mailemptied, intlighted, extlighting, streetlighting, vehicleondriveway, grassnowmaint, dogorsign, activeinneighbwatch, signalarm from burgleform");
             while(sor.next())
             { 
               so.add((int)sor.getByte("mailemptied"));
               so1.add((int)sor.getByte("intlighted"));
               so2.add((int)sor.getByte("extlighting"));
               so3.add((int)sor.getByte("streetlighting"));
               so4.add((int)sor.getByte("vehicleondriveway"));
               so5.add((int)sor.getByte("grassnowmaint"));
               so6.add((int)sor.getByte("dogorsign"));
               so7.add((int)sor.getByte("activeinneighbwatch"));
               so8.add((int)sor.getByte("signalarm"));
             }
             
              median_of_crimes_known(so);
              median_of_crimes_known(so1);
              median_of_crimes_known(so2);
              median_of_crimes_known(so3);
              median_of_crimes_known(so4);   
              median_of_crimes_known(so5);
              median_of_crimes_known(so6);
              invalid_median(so7,"activeinneighbwatch");
              invalid_median(so8,"signalarm");
                
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        
    }
   
    
    public void larm_status() throws IOException // completed no revisit required
    {
        ArrayList<Integer> ls1 = new ArrayList<>();
        ArrayList<Integer> ls2 = new ArrayList<>();
        ArrayList<Integer> ls3 = new ArrayList<>();
        ArrayList<Integer> ls4 = new ArrayList<>();
        ArrayList<Integer> ls5 = new ArrayList<>();       
        
        try
        {
        Connection conl = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
        Statement stl = conl.createStatement();
        ResultSet rsls = stl.executeQuery("select activated,triggered,sabotaged,disabled,noalarm from burgleform");
        while(rsls.next())
        {
            
            ls1.add((int)rsls.getByte("activated"));
            ls2.add((int)rsls.getByte("triggered"));
            ls3.add((int)rsls.getByte("sabotaged"));
            ls4.add((int)rsls.getByte("disabled"));
            ls5.add((int)rsls.getByte("noalarm"));
        }
        
        median_larm(ls1);
        median_of_crimes_known(ls2);
        median_of_crimes_known(ls3);
        median_larm_four(ls4);
        median_of_crimes_known(ls5);
        
        
        }
        catch(SQLException sl)
        {
            System.out.println("sql exception in larm status");
        }
    }
    public void median_larm_four(ArrayList<Integer> lm1)
    {
        int sx=0,median;
         if(lm1.size()%2!=0)
        {
            median=lm1.get(lm1.size()/2);
            System.out.println("Median is "+median+ " For value of n="+lm1.size());
            sx=lm1.size()/2;
             
        }
        else
        {
            median=(lm1.get(lm1.size()/2)+lm1.get((lm1.size()-1)/2))/2;
            System.out.println("Median is "+median+ " For value of n="+lm1.size());
            sx=(lm1.size()/2+(lm1.size()-1)/2)/2; 
        }
        
         System.out.println("the median of the disabled field is before adjustment "+median +"at"+sx);
        
             int d1=0,d2=0,c2=0,highcnt=0,op=0;
             try
             {
             Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
             Statement st3 = con2.createStatement();
             ResultSet rs3 = st3.executeQuery("select count(disabled) from burgleform where disabled=1");
             while(rs3.next())
             {
                 d1 = rs3.getInt(1);
             }
             
             Connection con3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
             Statement st4 = con3.createStatement();
             ResultSet rs4 = st4.executeQuery("select count(disabled) from burgleform where disabled=0");
             while(rs4.next())
             {
                 d2=rs4.getInt(1);
             }
             c2=9110-(d1+d2);
             /*highcnt = cal_max(d1,d2,c2);
             if(highcnt==d1)
             {
                 op=1;
             }
             if(highcnt==d2)
             {
                 op=2;
             }
             else
             {
                 op=3;
             }
             System.out.println("The highest among the median larm of disabled case"+cal_max(d1,d2,c2));
             }
             catch(SQLException sqa)
             {
               System.out.println(sqa);
             }
             int med,cn=0;
        if(median!=op)
        {
         if(op==1)
         {
            System.out.println("The adjustment is done in the median value of 0");
             for(int i=sx;i>=sx-100;i--)
             {
               if(i==1)
               {
                   median=1;cn=i;
                   break;
                   
               }
             }
         }
         if(op==2)
         {
             System.out.println("The adjustement is done in the median vlaue of 1");
             for(int i=sx;i>=sx-100;i--)
             {
                 if(i==0)
                 {
                     median=0;cn=i;
                     break;
                 }
             }
         }
         if(op==3)
         {
             System.out.println("the adjustment is done in the median value of null");
             for(int i=sx;i>=sx-100;i--)
             {
                 if(i!=0 || i!=1)
                 {
                     median=3; cn=i;
                     break;
                 }
             }
         }
          System.out.println("The median of disabled one after adjustment is "+median+"and it occurred at "+cn);
          System.out.println("---------------------------------------------------------");
        */
             }
             catch(SQLException se)
             {
                 System.out.println("SQLException in median larm four"+se);
             }
                       
    }
    public void median_larm(ArrayList<Integer> lm)
    {
        int mvalue=0,a1=0,b1=0,d1=0,d2=0,c1=0,c2=0;
        if(lm.size()%2!=0)
            {
                int median=lm.get(lm.size()/2);
                System.out.println("Median is "+median+ " For value of n="+lm.size());
                mvalue=lm.size()/2;
            }
            else
            {
                int median=(lm.get(lm.size()/2)+lm.get((lm.size()-1)/2))/2;
                System.out.println("Median is "+median+ " For value of n="+lm.size());
                mvalue=(lm.size()/2+(lm.size()-1)/2)/2;
            }
         System.out.println("The value of median is :"+ mvalue);
         try
         {
             
             {
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
             Statement st1 = con.createStatement();
             ResultSet rsla = st1.executeQuery("select count(activated) from burgleform where activated=1");
             while(rsla.next())
             {
                 a1 = rsla.getInt(1);
             }
             Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
             Statement st2 = con1.createStatement();
             ResultSet rs2 = st2.executeQuery("select count(activated) from burgleform where activated=0");
             while(rs2.next())
             {
                  b1= rs2.getInt(1);
             }
             c1 = 9110-(a1+b1);
             System.out.println("The highest among the median larm of activated case"+cal_max(a1,b1,c1));
             }
             
         }
         catch(SQLException sqla)
         {
             System.out.println(sqla);
         }
    }
    /**
     * It calculates the maximum of the three numbers and returns the highest number to the caller function
     * 
     * @param k1     this is the argument or the first number taken for the comparison
     * @param k2     this is the argument or the second number taken for the comparison
     * @param k3     this is the argument or the third number taken for the comparison
     * @return 
     */
    
    public int cal_max(int k1,int k2,int k3)
    {
        if(k1>k2&&k1>k3)
        {
            System.out.println("The value 1 is highest");
            return k1;
        }
        if(k2>k1&&k2>k3)
        {
            System.out.println("the value 0 is hightest");
            return k2;
        }
        else
        {
            System.out.println("the value null is highest");
            return k3;
            
        }
        
    }
    
    public void plaintiff_status() throws IOException // completed no revisit required
    {
        ArrayList<Integer> ps1 = new ArrayList<>();
        ArrayList<Integer> ps2 = new ArrayList<>();
        ArrayList<Integer> ps3 = new ArrayList<>();
        ArrayList<Integer> ps4 = new ArrayList<>();
        ArrayList<Integer> ps5 = new ArrayList<>();
        ArrayList<Integer> ps6 = new ArrayList<>();
        ArrayList<Integer> ps7 = new ArrayList<>();
        ArrayList<Integer> ps8 = new ArrayList<>();
        ArrayList<Integer> ps9 = new ArrayList<>();
        ArrayList<Integer> ps10 = new ArrayList<>();
        ArrayList<Integer> ps11 = new ArrayList<>();
        ArrayList<Integer> ps12 = new ArrayList<>();
        
        try
        {
            Connection placon = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
            Statement stpla = placon.createStatement();
            ResultSet rspla = stpla.executeQuery("select ownbusiness,incompanyregist,plannedabsence,spontabsence,homedurcrime,househldsrvc,homevisit,unknowncall,absenceonnet,kidsathome,tradeannounce,vhclatairp from burgleform");
            while(rspla.next())
            {
                ps1.add((int)rspla.getByte("ownbusiness"));
                ps2.add((int)rspla.getByte("incompanyregist"));
                ps3.add((int)rspla.getByte("plannedabsence"));
                ps4.add((int)rspla.getByte("spontabsence"));
                ps5.add((int)rspla.getByte("homedurcrime"));
                ps6.add((int)rspla.getByte("househldsrvc"));
                ps7.add((int)rspla.getByte("homevisit"));
                ps8.add((int)rspla.getByte("unknowncall"));
                ps9.add((int)rspla.getByte("absenceonnet"));
                ps10.add((int)rspla.getByte("kidsathome"));
                ps11.add((int)rspla.getByte("tradeannounce"));
                ps12.add((int)rspla.getByte("vhclatairp"));
            }   
                median_of_crimes_known(ps1);
                median_of_crimes_known(ps2);
                median_of_crimes_known(ps3);
                median_of_crimes_known(ps4);
                median_of_crimes_known(ps5);
                median_of_crimes_known(ps6);
                median_of_crimes_known(ps7);
                median_of_crimes_known(ps8);
                median_of_crimes_known(ps9);
                median_of_crimes_known(ps10);
                median_of_crimes_known(ps11);
                median_of_crimes_known(ps12);     
                
             
        }
        catch(SQLException se)
        {
            System.out.println(se+"An sql exception in plaintiff status");
        }
    }
    
   public void goods_col9() throws IOException
   {
       ArrayList<Integer> g1 = new ArrayList<>();
       ArrayList<Integer> g2 = new ArrayList<>();
       ArrayList<Integer> g3 = new ArrayList<>();
       ArrayList<Integer> g4 = new ArrayList<>();
       ArrayList<Integer> g5 = new ArrayList<>();
       ArrayList<Integer> g6 = new ArrayList<>();
       ArrayList<Integer> g7 = new ArrayList<>();
       ArrayList<Integer> g8 = new ArrayList<>();
       ArrayList<Integer> g9 = new ArrayList<>();
       ArrayList<Integer> g10 = new ArrayList<>();
       ArrayList<Integer> g11 = new ArrayList<>();
       ArrayList<Integer> g12 = new ArrayList<>();
       ArrayList<Integer> g13 = new ArrayList<>();
       ArrayList<Integer> g14 = new ArrayList<>();
       ArrayList<Integer> g15 = new ArrayList<>();
       ArrayList<Integer> g16 = new ArrayList<>();
       ArrayList<Integer> g17 = new ArrayList<>();
       ArrayList<Integer> g18 = new ArrayList<>();
       try
       {
           Connection gconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
           Statement gst = gconn.createStatement();
           ResultSet grs = gst.executeQuery("select bulkygoods, nonbulkygoods, creddebcard, cellphone, alcoholtobacco, electronics, goldjewellery,cash, clothes, medicine,toys, weapons, safe, perfume, vehiclekeys,passid,sect82other, sect82na from burgleform");
           while(grs.next())
           {
               g1.add((int)grs.getByte("bulkygoods"));
               g2.add((int)grs.getByte("nonbulkygoods"));
               g3.add((int)grs.getByte("creddebcard"));
               g4.add((int)grs.getByte("cellphone"));
               g5.add((int)grs.getByte("alcoholtobacco"));
               g6.add((int)grs.getByte("electronics"));
               g7.add((int)grs.getByte("goldjewellery"));
               g8.add((int)grs.getByte("cash"));
               g9.add((int)grs.getByte("clothes"));
               g10.add((int)grs.getByte("medicine"));
               g11.add((int)grs.getByte("toys"));
               g12.add((int)grs.getByte("weapons"));
               g13.add((int)grs.getByte("safe"));
               g14.add((int)grs.getByte("perfume"));
               g15.add((int)grs.getByte("vehiclekeys"));
               g16.add((int)grs.getByte("passid"));
               g17.add((int)grs.getByte("sect82other"));
               g18.add((int)grs.getByte("sect82na"));
               
           }
           invalid_median_goods9(g1,"bulkygoods");
           invalid_median_goods9(g2,"nonbulkygoods");
           invalid_median_goods9(g3,"creddebcard");
           invalid_median_goods9(g4,"cellphone");
           median_of_crimes_known(g5);
           median_of_crimes_known(g6);
           median_of_crimes_known(g7);
           median_of_crimes_known(g8);
           median_of_crimes_known(g9);
           median_of_crimes_known(g10);
           median_of_crimes_known(g11);
           median_of_crimes_known(g12);
           median_of_crimes_known(g13);
           median_of_crimes_known(g14);
           median_of_crimes_known(g15);
           median_of_crimes_known(g16);
           median_of_crimes_known(g17);
           median_of_crimes_known(g18);
           
           
       }
       catch(SQLException se)
       {
           System.out.println("the sql exception in the goods column "+se);
           
       }
         
   }
   public void ransacked_status() throws IOException
   {
       ArrayList<Integer> r1 = new ArrayList<>();
       ArrayList<Integer> r2 = new ArrayList<>();
        try
       {
           Connection rcon = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
           Statement rst = rcon.createStatement();
           ResultSet rrs = rst.executeQuery("select messy,bigmess from burgleform");
           while(rrs.next())
           {
                r1.add((int)rrs.getByte("messy"));
                r2.add((int)rrs.getByte("bigmess"));
           }
           invalid_ransacked(r1);
           median_of_crimes_known(r2);
       }
        catch(SQLException e)
        {
            System.out.println("SQLException in ransacked status"+e);
        }
       
   }
   public void disp() throws ClassNotFoundException, SQLException
   {
       /*String[] arra = { "witness ", 
 "tips ", 
 "searchablegoods ", 
 "sect101na ", 
 "camerafotage ", 
 "traceother ", 
 "nobpu "
};
       for(int i=1;i<=7;i++)
       {
          System.out.println("ArrayList<Integer>os"+i+"=new ArrayList<>();");
       }
       
       
       for(int i=1;i<=7;i++)
       {
           System.out.println("os"+i+".add((int)osrs.getByte(\""+arra[i-1] +"\"));");
       }*/
       
           Class.forName("com.mysql.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("select neighbourless,neighbone,neighbsev,corner,woods,stdhigh,stdnorm,stdlow,villa,farm,townhouse,aptowned,aptrental,multilvl,singlelvl,apttop,aptbottom,activated,triggered,sabotaged,disabled,noalarm,mailemptied,intlighted,extlighting,streetlighting,vehicleondriveway,grassnowmaint,dogorsign,activeinneighbwatch,signalarm,sect41na,ownbusiness,incompanyregist,plannedabsence,spontabsence,homedurcrime,househldsrvc,homevisit,unknowncall,absenceonnet,kidsathome,tradeannounce,vhclatairp,crippleelderly,prevbreakin,sect54na,viewcover,noviewcover,basement,groundlvl,abovegroundlvl,sect62unknown,toolfromplace,escrouteprepd,drills,breaks,illegalkey,breakswindowin,unlocked,ventpos,sect64other,sect64unknown,patiodoor,mirrorpatiodoor,balconydoor,cellardoor,door,window,triplepanewindow,mailslot,sect65other,sect65unknown,carefulsearch,messy,bigmess,bulkygoods,nonbulkygoods,creddebcard,cellphone,alcoholtobacco,electronics,goldjewellery,cash,clothes,medicine,toys,weapons,safe,perfume,vehiclekeys,passid,sect82other,sect82na,fingerprint,dna,shoes,gloves,tires,visiblefibre,compareglass,goodstosearch,toolmark,sect91na,smallmark,medmark,largemark,lte5marks,gte6marks,colormark,comparecolor,witness,tips,searchablegoods,sect101na,camerafotage,traceother,nobpu from burgleform");
           System.out.print(rs.getFetchSize());
   }
   public void accessobject() throws IOException
   {
        ArrayList<Integer> ao1 = new ArrayList<>();
        ArrayList<Integer> ao2 = new ArrayList<>();
        ArrayList<Integer> ao3 = new ArrayList<>();
        ArrayList<Integer> ao4 = new ArrayList<>();
        ArrayList<Integer> ao5 = new ArrayList<>();
        ArrayList<Integer> ao6 = new ArrayList<>();
        ArrayList<Integer> ao7 = new ArrayList<>();
        ArrayList<Integer> ao8 = new ArrayList<>();
        ArrayList<Integer> ao9 = new ArrayList<>();
        ArrayList<Integer> ao10 = new ArrayList<>();
        ArrayList<Integer> ao11 = new ArrayList<>();
        ArrayList<Integer> ao12 = new ArrayList<>();
        ArrayList<Integer> ao13 = new ArrayList<>();
        ArrayList<Integer> ao14 = new ArrayList<>();
        ArrayList<Integer> ao15 = new ArrayList<>();
        ArrayList<Integer> ao16 = new ArrayList<>();
        ArrayList<Integer> ao17 = new ArrayList<>();
        ArrayList<Integer> ao18 = new ArrayList<>();
        ArrayList<Integer> ao19 = new ArrayList<>();
        ArrayList<Integer> ao20 = new ArrayList<>();
        ArrayList<Integer> ao21 = new ArrayList<>();
        ArrayList<Integer> ao22 = new ArrayList<>();
        ArrayList<Integer> ao23 = new ArrayList<>();
        ArrayList<Integer> ao24 = new ArrayList<>();
        ArrayList<Integer> ao25 = new ArrayList<>();
        ArrayList<Integer> ao26 = new ArrayList<>();
        ArrayList<Integer> ao27 = new ArrayList<>();
        try
        {
           Connection acon = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");   
           Statement aost = acon.createStatement();
           ResultSet aosr = aost.executeQuery("select viewcover,noviewcover,basement,groundlvl,abovegroundlvl,sect62unknown,toolfromplace,escrouteprepd,drills,breaks,illegalkey,breakswindowin,unlocked,ventpos,sect64other,sect64unknown,patiodoor,mirrorpatiodoor,balconydoor,cellardoor,door,window,triplepanewindow,mailslot,sect65other,sect65unknown from burgleform");
           while(aosr.next())
           {
         
ao2.add((int)aosr.getByte("viewcover"));
ao3.add((int)aosr.getByte("noviewcover"));
ao4.add((int)aosr.getByte("basement"));
ao5.add((int)aosr.getByte("groundlvl"));
ao6.add((int)aosr.getByte("abovegroundlvl"));
ao7.add((int)aosr.getByte("sect62unknown"));
ao8.add((int)aosr.getByte("toolfromplace"));
ao9.add((int)aosr.getByte("escrouteprepd"));
ao10.add((int)aosr.getByte("drills"));
ao11.add((int)aosr.getByte("breaks"));
ao12.add((int)aosr.getByte("illegalkey"));
ao13.add((int)aosr.getByte("breakswindowin"));
ao14.add((int)aosr.getByte("unlocked"));
ao15.add((int)aosr.getByte("ventpos"));
ao16.add((int)aosr.getByte("sect64other"));
ao17.add((int)aosr.getByte("sect64unknown"));
ao18.add((int)aosr.getByte("patiodoor"));
ao19.add((int)aosr.getByte("mirrorpatiodoor"));
ao20.add((int)aosr.getByte("balconydoor"));
ao21.add((int)aosr.getByte("cellardoor"));
ao22.add((int)aosr.getByte("door"));
ao23.add((int)aosr.getByte("window"));
ao24.add((int)aosr.getByte("triplepanewindow"));
ao25.add((int)aosr.getByte("mailslot"));
ao26.add((int)aosr.getByte("sect65other"));
ao27.add((int)aosr.getByte("sect65unknown"));
           }
          median_of_crimes_known(ao2);
          median_of_crimes_known(ao3);
          median_of_crimes_known(ao4);
          invalid_accessobject(ao5,"groundlvl");
          median_of_crimes_known(ao6);
          invalid_accessobject(ao7,"sect62unknown");
          median_of_crimes_known(ao8);
          median_of_crimes_known(ao9);
          invalid_accessobject(ao10,"drills");
          median_of_crimes_known(ao11);
          invalid_accessobject(ao12,"illegalkey");
          median_of_crimes_known(ao13);
          invalid_accessobject(ao14,"unlocked");
          invalid_accessobject(ao15,"ventpos");
          invalid_accessobject(ao16,"sect64other");
          invalid_accessobject(ao17,"sect64unknown");
          median_of_crimes_known(ao18);
          invalid_accessobject(ao19,"mirrorpatiodoor");
          median_of_crimes_known(ao20);
          invalid_accessobject(ao21,"cellardoor");
          median_of_crimes_known(ao22);
          median_of_crimes_known(ao23);
          invalid_accessobject(ao24,"triplepanewindow");
          invalid_accessobject(ao25,"mailslot");
          invalid_accessobject(ao26,"sect65other");
          invalid_accessobject(ao27,"sect65unknown");
          
        }
        catch(SQLException aoe)
        {
            System.out.println("Access object exception "+aoe);
        }
   }
   public void trace_evidence() throws IOException
   {
        ArrayList<Integer> te1 = new ArrayList<>();
        ArrayList<Integer> te2 = new ArrayList<>();
        ArrayList<Integer> te3 = new ArrayList<>();
        ArrayList<Integer> te4 = new ArrayList<>();
        ArrayList<Integer> te5 = new ArrayList<>();
        ArrayList<Integer> te6 = new ArrayList<>();
        ArrayList<Integer> te7 = new ArrayList<>();
        ArrayList<Integer> te8 = new ArrayList<>();
        ArrayList<Integer> te9 = new ArrayList<>();
        ArrayList<Integer> te10 = new ArrayList<>();
        ArrayList<Integer> te11 = new ArrayList<>();
        ArrayList<Integer> te12 = new ArrayList<>();
        ArrayList<Integer> te13 = new ArrayList<>();
        ArrayList<Integer> te14 = new ArrayList<>();
        ArrayList<Integer> te15 = new ArrayList<>();
        ArrayList<Integer> te16 = new ArrayList<>();
        ArrayList<Integer> te17 = new ArrayList<>();
        
        try
        {
            Connection tecon = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
            Statement test = tecon.createStatement();
            ResultSet ters = test.executeQuery("select fingerprint,dna,shoes,gloves,tires,visiblefibre,compareglass,goodstosearch,toolmark,sect91na,smallmark,medmark,largemark,lte5marks,gte6marks,colormark,comparecolor from burgleform");
            while(ters.next())
            {
                te1.add((int)ters.getByte("fingerprint"));
te2.add((int)ters.getByte("dna"));
te3.add((int)ters.getByte("shoes"));
te4.add((int)ters.getByte("gloves"));
te5.add((int)ters.getByte("tires"));
te6.add((int)ters.getByte("visiblefibre"));
te7.add((int)ters.getByte("compareglass"));
te8.add((int)ters.getByte("goodstosearch"));
te9.add((int)ters.getByte("toolmark"));
te10.add((int)ters.getByte("sect91na"));
te11.add((int)ters.getByte("smallmark"));
te12.add((int)ters.getByte("medmark"));
te13.add((int)ters.getByte("largemark"));
te14.add((int)ters.getByte("lte5marks"));
te15.add((int)ters.getByte("gte6marks"));
te16.add((int)ters.getByte("colormark"));
te17.add((int)ters.getByte("comparecolor"));
            }
            median_of_crimes_known(te1);
            median_of_crimes_known(te2);
            median_of_crimes_known(te3);
            median_of_crimes_known(te4);
            invalid_traceevidence(te5,"tires");
            invalid_traceevidence(te6,"visiblefibre");
            invalid_traceevidence(te7,"compareglass");
            median_of_crimes_known(te8);
            median_of_crimes_known(te9);
            median_of_crimes_known(te10);
            invalid_traceevidence(te11,"smallmark");
            invalid_traceevidence(te12,"medmark");
            invalid_traceevidence(te13,"largemark");
            invalid_traceevidence(te14,"lte5marks");
            invalid_traceevidence(te15,"gte6marks");
            median_of_crimes_known(te16);
            invalid_traceevidence(te17,"comparecolor");

            
            
        }
        catch(SQLException tee)
        {
            System.out.println("Exception in trace evidence"+tee);            
        }


       
   }
   public void other_status() throws IOException
   {
        ArrayList<Integer>os1=new ArrayList<>();
        ArrayList<Integer>os2=new ArrayList<>();
        ArrayList<Integer>os3=new ArrayList<>();
        ArrayList<Integer>os4=new ArrayList<>();
        ArrayList<Integer>os5=new ArrayList<>();
        ArrayList<Integer>os6=new ArrayList<>();
        ArrayList<Integer>os7=new ArrayList<>();
        try
            {
                
                Connection unk = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
                Statement stu = unk.createStatement();
                ResultSet osrs = stu.executeQuery("select witness,tips,searchablegoods,sect101na,camerafotage,traceother,nobpu from burgleform");
                while(osrs.next())
                {
                    os1.add((int)osrs.getByte("witness"));
                    os2.add((int)osrs.getByte("tips"));
                    os3.add((int)osrs.getByte("searchablegoods"));
                    os4.add((int)osrs.getByte("sect101na"));
                    os5.add((int)osrs.getByte("camerafotage"));
                    os6.add((int)osrs.getByte("traceother"));
                    os7.add((int)osrs.getByte("nobpu"));
                }
                median_of_crimes_known(os1);
                median_of_crimes_known(os2);
                median_of_crimes_known(os3);
                median_of_crimes_known(os4);
                invalid_otherstatus(os5,"camerafotage");
                invalid_otherstatus(os6,"traceother");
                invalid_otherstatus(os7,"nobpu");
            }
        catch(SQLException se)
        {
            System.out.println("SQLException in other status "+se);
        }
       
   }
   public void invalid_otherstatus(ArrayList<Integer> osal, String field_name)
   {
       int mvalue;int a=0,b=0,c=0,d=0;
            if(osal.size()%2!=0)
            {
                int median=osal.get(osal.size()/2);
                System.out.println("Median is "+median+ " For value of n="+osal.size());
                mvalue=osal.size()/2;
                
            }
            else
            {
                int median=(osal.get(osal.size()/2)+osal.get((osal.size()-1)/2))/2;
                System.out.println("Median is "+median+ " For value of n="+osal.size());
                mvalue=(osal.size()/2+(osal.size()-1)/2)/2;
            }
            
            try
            {
                System.out.println("The value of median is \n "+mvalue);
                Connection unk = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
                Statement stu = unk.createStatement();
                ResultSet rsunk = stu.executeQuery("select count("+field_name+") from burgleform where "+field_name+"=0");
            
            while(rsunk.next())
            {
                a=  rsunk.getInt(1);
            }
              
            Connection unk1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
            Statement stu1 = unk1.createStatement();
            ResultSet rsunk1 = stu.executeQuery("select count("+field_name+") from burgleform where "+field_name+"=1");
            
            while(rsunk1.next())
            {
                b= rsunk1.getInt(1);
            }
            
            int cal1=9110-(a+b);
            System.out.println("The count 1 of "+field_name+" is : "+a);
            System.out.println("The count 0 of "+field_name+" is : "+b);
            System.out.println("The count null of "+field_name+" is : "+cal1+"\n");
            }
            catch(SQLException se)
            {
                System.out.println("SQLException in invalid access log is :  "+se);
            } 
       
   }
   public void invalid_traceevidence(ArrayList<Integer> teal, String field_name)
   {
     int mvalue;int a=0,b=0,c=0,d=0;
            if(teal.size()%2!=0)
            {
                int median=teal.get(teal.size()/2);
                System.out.println("Median is "+median+ " For value of n="+teal.size());
                mvalue=teal.size()/2;
                
            }
            else
            {
                int median=(teal.get(teal.size()/2)+teal.get((teal.size()-1)/2))/2;
                System.out.println("Median is "+median+ " For value of n="+teal.size());
                mvalue=(teal.size()/2+(teal.size()-1)/2)/2;
            }
            
            try
            {
                System.out.println("The value of median is \n "+mvalue);
                Connection unk = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
                Statement stu = unk.createStatement();
                ResultSet rsunk = stu.executeQuery("select count("+field_name+") from burgleform where "+field_name+"=0");
            
            while(rsunk.next())
            {
                a=  rsunk.getInt(1);
            }
              
            Connection unk1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
            Statement stu1 = unk1.createStatement();
            ResultSet rsunk1 = stu.executeQuery("select count("+field_name+") from burgleform where "+field_name+"=1");
            
            while(rsunk1.next())
            {
                b= rsunk1.getInt(1);
            }
            
            int cal1=9110-(a+b);
            System.out.println("The count 1 of "+field_name+" is : "+a);
            System.out.println("The count 0 of "+field_name+" is : "+b);
            System.out.println("The count null of "+field_name+" is : "+cal1+"\n");
            }
            catch(SQLException se)
            {
                System.out.println("SQLException in invalid access log is :  "+se);
            }   
   }
   public void invalid_accessobject(ArrayList<Integer> af,String field_name)
   {
            int mvalue;int a=0,b=0,c=0,d=0;
            if(af.size()%2!=0)
            {
                int median=af.get(af.size()/2);
                System.out.println("Median is "+median+ " For value of n="+af.size());
                mvalue=af.size()/2;
                
            }
            else
            {
                int median=(af.get(af.size()/2)+af.get((af.size()-1)/2))/2;
                System.out.println("Median is "+median+ " For value of n="+af.size());
                mvalue=(af.size()/2+(af.size()-1)/2)/2;
            }
            
            try
            {
                System.out.println("The value of median is \n "+mvalue);
                Connection unk = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
                Statement stu = unk.createStatement();
                ResultSet rsunk = stu.executeQuery("select count("+field_name+") from burgleform where "+field_name+"=0");
            
            while(rsunk.next())
            {
                a=  rsunk.getInt(1);
            }
              
            Connection unk1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
            Statement stu1 = unk1.createStatement();
            ResultSet rsunk1 = stu.executeQuery("select count("+field_name+") from burgleform where "+field_name+"=1");
            
            while(rsunk1.next())
            {
                b= rsunk1.getInt(1);
            }
            
            int cal1=9110-(a+b);
            System.out.println("The count 1 of "+field_name+" is : "+a);
            System.out.println("The count 0 of "+field_name+" is : "+b);
            System.out.println("The count null of "+field_name+" is : "+cal1);
            }
            catch(SQLException se)
            {
                System.out.println("SQLException in invalid access log is :  "+se);
            }
   }
   public void invalid_ransacked(ArrayList<Integer> rs)
   {
       int mvalue; int cal1=0,cal2=0,cal3=0;
       if(rs.size()%2!=0)
            {
                int median=rs.get(rs.size()/2);
                System.out.println("Median is "+median+ " For value of n="+rs.size());
                mvalue=rs.size()/2;
            }
            else
            {
                int median=(rs.get(rs.size()/2)+rs.get((rs.size()-1)/2))/2;
                System.out.println("Median is "+median+ " For value of n="+rs.size());
                mvalue=(rs.size()/2+(rs.size()-1)/2)/2;
            }
         System.out.println("The value of median is :"+ mvalue);
        try
        {
         Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");   
         Statement gst = con1.createStatement();
         ResultSet gsr = gst.executeQuery("select count(messy) from burgleform where messy=1");
         while(gsr.next())
         {
             cal1 = gsr.getInt(1);
         }
         Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");   
         Statement gst2 = con2.createStatement();
         ResultSet gsr2 = gst2.executeQuery("select count(messy) from burgleform where messy=0");
         while(gsr2.next())
         {
             cal2 = gsr2.getInt(1);
         }
         cal3= 9110-(cal1+cal2);
         
         System.out.println("count values of messy zero " + cal2);
          System.out.println("count values of messy one " + cal1);
           System.out.println("count values of messy null " + cal3);
         
        }
        catch(SQLException ig)
        {
            System.out.println("The sql exception occurred in goods median of invalid "+ig);
        }
   }
    public void invalid_median_goods9(ArrayList<Integer> good1, String getvar)
    {
        int cal1=0,cal2=0,cal3;
        String st = "select ";
        String st1= "from burgleform where";
        String st2 = "=1";
        String fullst = st+getvar+st1+getvar+st2;
        int mvalue=0;
        if(good1.size()%2!=0)
            {
                int median=good1.get(good1.size()/2);
                System.out.println("Median is "+median+ " For value of n="+good1.size());
                mvalue=good1.size()/2;
            }
            else
            {
                int median=(good1.get(good1.size()/2)+good1.get((good1.size()-1)/2))/2;
                System.out.println("Median is "+median+ " For value of n="+good1.size());
                mvalue=(good1.size()/2+(good1.size()-1)/2)/2;
            }
         System.out.println("The value of median is :"+ mvalue);
        try
        {
         Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");   
         Statement gst = con1.createStatement();
         ResultSet gsr = gst.executeQuery("select count("+getvar+") from burgleform where "+getvar+"=1");
         while(gsr.next())
         {
             cal1 = gsr.getInt(1);
         }
         Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");   
         Statement gst2 = con2.createStatement();
         ResultSet gsr2 = gst2.executeQuery(" select count("+getvar+") from burgleform where "+getvar+"=0");
         while(gsr2.next())
         {
             cal2 = gsr2.getInt(1);
         }
         cal3= 9110-(cal1+cal2);
         
         System.out.println("the values of"+getvar+"\n1. cal1 "+cal1+"\ncal2 "+cal2+"\ncal3 "+cal3);
         
        }
        catch(SQLException ig)
        {
            System.out.println("The sql exception occurred in goods median of invalid "+ig);
        }
        
    }
    
   
    
    
    public void invalid_median_res(ArrayList<Integer> rm, String field)
    {
            int mvalue,median;int a=0,b=0,c=0,d=0;
            if(rm.size()%2!=0)
            {
                median=rm.get(rm.size()/2);
                System.out.println("Median is "+median+ " For value of n="+rm.size());
                mvalue=rm.size()/2;
            }
            else
            {
                median=(rm.get(rm.size()/2)+rm.get((rm.size()-1)/2))/2;
                System.out.println("Median is "+median+ " For value of n="+rm.size());
                mvalue=(rm.size()/2+(rm.size()-1)/2)/2;
            }
            System.out.println("The value of median is "+mvalue);
            try
            {
            Connection unk = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
            Statement stu = unk.createStatement();
            ResultSet rsunk = stu.executeQuery("select count("+field+") from burgleform where "+field+"=1");
            
            while(rsunk.next())
            {
                a= rsunk.getInt(1);
            }
            
            Connection unk1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
            Statement stu1 = unk1.createStatement();
            ResultSet rsunk1 = stu1.executeQuery("select count("+field+") from burgleform where "+field+"=0");
            
            while(rsunk1.next())
            {
                b= rsunk1.getInt(1);
            }
            
            int cal1=9110-(a+b);
            System.out.println("The count 1 of "+field+" is : "+a);
            System.out.println("The count 0 of "+field+" is : "+b);
            System.out.println("The count null of "+field+" is : "+cal1);
            
            System.out.println("\n");
            // The calculation of the settling the median as per the value of the wildcard entries.
            int f = maximum(a,b,cal1);
            System.out.println("the function call over"+f);
            /*if(median!=f)
            {
                if(f==1)
                {
                  for(int i=mvalue;i<9110;i++)
                  {
                      if(rm.get(i)==1)
                      {
                          mvalue=1;
                          median = i;
                          System.out.println("the new median is "+mvalue+"at "+median);
                          break;
                      }
                  }
                }
                if(f==0)
                {
                   for(int i=mvalue;i<9110;i++)
                  {
                      if(rm.get(i)==0)
                      {
                          mvalue=0;
                          median = i;
                          System.out.println("the new median is "+mvalue+"at "+median);
                          break;
                      }
                  } 
                }
                if(f==3)
                {
                    for(int i=mvalue;i<9110;i++)
                  {
                      if(rm.get(i)==1 || rm.get(i)==0)
                      {
                          
                      }
                      else
                      {
                          mvalue= -1;
                          median = i;
                          System.out.println("the new median is "+mvalue+"at "+median);
                          break;
                      }
                  }
                }
            }*/
            
         } //check out for the calculated count of grouping it using a wild-card entries for efficient calculation of median
        
        catch (SQLException ex)
        {
            Logger.getLogger(Thesisv3.class.getName()).log(Level.SEVERE, null, ex);
        }
            finally
            {
                System.out.println("\n");
            }
    }
    public int maximum(int a, int b, int c)
    {
        if(a>b&&a>c)
            return 1;
        if(b>a&&b>c)
            return 0;
        
        return 3;
        
    }
    public void invalid_median(ArrayList<Integer> um,String field1)
    {
                    
            int mvalue;int a=0,b=0,c=0,d=0;
            ArrayList<Integer> skm = new ArrayList<>();
            ArrayList<Integer> skm1 = new ArrayList<>();
            if(um.size()%2!=0)
            {
                int median=um.get(um.size()/2);
                System.out.println("Median is "+median+ " For value of n="+um.size());
                mvalue=um.size()/2;
                
            }
            else
            {
                int median=(um.get(um.size()/2)+um.get((um.size()-1)/2))/2;
                System.out.println("Median is "+median+ " For value of n="+um.size());
                mvalue=(um.size()/2+(um.size()-1)/2)/2;
            }
            
            try
            {
                System.out.println("The value of median is "+mvalue);
                Connection unk = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
                Statement stu = unk.createStatement();
                ResultSet rsunk = stu.executeQuery("select count("+field1+") from burgleform where "+field1+"=0");
            
            while(rsunk.next())
            {
                a=  rsunk.getInt(1);
            }
              
            Connection unk1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
            Statement stu1 = unk1.createStatement();
            ResultSet rsunk1 = stu.executeQuery("select count("+field1+") from burgleform where "+field1+"=1");
            
            while(rsunk1.next())
            {
                b= rsunk1.getInt(1);
            }
            
            int cal1=9110-(a+b);
            System.out.println("The count 1 of "+field1+" is : "+a);
            System.out.println("The count 0 of "+field1+" is : "+b);
            System.out.println("The count null of "+field1+" is : "+cal1);
            /*
            Connection unk2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
            Statement stu2= unk2.createStatement();
            ResultSet rsunk2 = stu2.executeQuery("select count(signalarm) from burgleform where signalarm=1");
            
            while(rsunk2.next())
            {
                c = rsunk.getInt(1);
            }
            
            Connection unk3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
            Statement stu3= unk3.createStatement();
            ResultSet rsunk3 = stu3.executeQuery("select count(signalarm) from burgleform where signalarm=0");
            
            while(rsunk3.next())
            {
                d = rsunk.getInt(1);
            }
            
            int cal2=9110-(c+d); 
            /*
            while(rsunk.next())
            {
               skm.add((int)rsunk.getByte("activeinneighbwatch"));
               skm1.add((int)rsunk.getByte("signalarm"));
               System.out.println("------");
               System.out.println(skm.get(1));
               System.out.println(skm1.get(2));
               System.out.println("------");
            }*/
            
         } 
        
        catch (SQLException ex)
        {
            Logger.getLogger(Thesisv3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void median_of_crimes_known(ArrayList<Integer> a) throws IOException
    { 
        int sx;
        if(a.size()%2!=0)
        {
            int median=a.get(a.size()/2);
            System.out.println("Median is "+median+ " For value of n="+a.size());
            sx=a.size()/2;
             
        }
        else
        {
            int median=(a.get(a.size()/2)+a.get((a.size()-1)/2))/2;
            System.out.println("Median is "+median+ " For value of n="+a.size());
            sx=(a.size()/2+(a.size()-1)/2)/2; 
        }
        
        
        try
            {
                 System.out.println("The value of median " + sx);
                 Connection calc = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
                 PreparedStatement cmr2 = calc.prepareStatement("select * from burgleform where id="+sx);
                 
                 ResultSet rsr = cmr2.executeQuery();
                 while(rsr.next())
                 {
                     System.out.println("----------------");
                     for(int p=2;p<=141;p++)
                     {
                       System.out.print(rsr.getString(p)+"\t");
                     }
                    System.out.println("\n ");
          
                 }
            }  
            
            catch(SQLException se)
            {
                System.out.println(se);
            }
       
    }
  
    
    public static void main(String[] args) throws IOException, ClassNotFoundException,SQLException
    {
        // TODO code application logic here
        
        Thesisv3 t = new Thesisv3();
        dbase d = new dbase();
        d.dbase_connect();
        System.out.println("want to calculate the median \n1.Residential median 2.Standard object 3.Type of residency 4. Larm 5. Plaintiff status 6. Ransacked ");
        System.out.println("7. Access Object 8. Goods status 9.TraceEvidence 10. Other status 11. Display 12. Exit 13.JC 14. Linked cases 15. gmHash");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        
        if(a==1)
        {
           t.cal_residential_area();
        }
        if(a==2)
        {
            t.cal_standard_object();           
        }
        if(a==3)
        {
            t.type_of_residency();
        }
        if(a==4)
        {
            t.larm_status();
        }
        if(a==5)
        {
            t.plaintiff_status();
        }
        if(a==6)
        {
            t.ransacked_status();
        }
        if(a==7)
        {
           t.accessobject(); 
        }
        if(a==8)
        {
            t.goods_col9();           
        }
        if(a==9)
        {
            t.trace_evidence();
        }
        if(a==10)
        {
            t.other_status();
        }
        if(a==11)
        {
            t.disp();
        }
        if(a==12)
        {
            System.out.println("Query is finished the results are displayed above. :)");
            System.exit(0);
        }
        if(a==13)
        {
            jaccard j = new jaccard();
            j.jc_con();
            
        }
        if(a==14)
        {
            clinked c = new clinked();
            c.clinked_connect();
        }
        if(a==15)
        {
            gmHash g = new gmHash();
            g.dataset2();
        }
    } 
}
