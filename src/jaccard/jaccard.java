/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaccard;

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

/**
 *
 * @author chakri
 */
public class jaccard
{
   public void jc_con() throws IOException
   {
       try 
       {
           Class.forName("com.mysql.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("select count(*) from burgleform");
           while(rs.next())
           {
               System.out.println("The value of the count of records is  : "+rs.getInt(1));
           }
            System.out.println("want to calculate the median \n");
            System.out.println("1.Residential median 2.Standard object 3.Type of residency 4. Larm 5. Plaintiff status");
            System.out.println("6. Ransacked 7. Access Object 8. Goods status 9.TraceEvidence 10. Other status");
       
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
           int a = Integer.parseInt(br.readLine());
           ArrayList<int[]> a1 = new ArrayList<>();
           
           
           if(a==1)
           {
              ResultSet rs1 = st.executeQuery("select neighbourless,neighbone,neighbsev,corner,woods,stdhigh,stdnorm,stdlow,villa,farm,townhouse,aptowned,aptrental,multilvl,singlelvl,apttop,aptbottom,activated,triggered,sabotaged,disabled,noalarm,mailemptied,intlighted,extlighting,streetlighting,vehicleondriveway,grassnowmaint,dogorsign,activeinneighbwatch,signalarm,sect41na,ownbusiness,incompanyregist,plannedabsence,spontabsence,homedurcrime,househldsrvc,homevisit,unknowncall,absenceonnet,kidsathome,tradeannounce,vhclatairp,crippleelderly,prevbreakin,sect54na,viewcover,noviewcover,basement,groundlvl,abovegroundlvl,sect62unknown,toolfromplace,escrouteprepd,drills,breaks,illegalkey,breakswindowin,unlocked,ventpos,sect64other,sect64unknown,patiodoor,mirrorpatiodoor,balconydoor,cellardoor,door,window,triplepanewindow,mailslot,sect65other,sect65unknown,carefulsearch,messy,bigmess,bulkygoods,nonbulkygoods,creddebcard,cellphone,alcoholtobacco,electronics,goldjewellery,cash,clothes,medicine,toys,weapons,safe,perfume,vehiclekeys,passid,sect82other,sect82na,fingerprint,dna,shoes,gloves,tires,visiblefibre,compareglass,goodstosearch,toolmark,sect91na,smallmark,medmark,largemark,lte5marks,gte6marks,colormark,comparecolor,witness,tips,searchablegoods,sect101na,camerafotage,traceother,nobpu from burgleform");
              while(rs1.next())
              {
                int[] xc = new int[118];
                for(int b=0;b<118;b++)
                {
                    xc[b] = (int)rs1.getByte(b+1);
                }
                a1.add(xc);
                            
              }int count=0,count1=0;
              System.out.println("The size of arraylist "+a1.size());
              for(int i=0;i<4553;i++)
              { 
                  count++;
                  System.out.println("The distance between "+i+" and median is : "+cal_distance(a1.get(i),a1.get(4553)));
                 
              }
              for(int i=4554;i<9110;i++)
              {
                  count1++;
                  System.out.println("The distance between "+i+" and median is : "+cal_distance(a1.get(i),a1.get(4553)));
              }
              System.out.println("The count of 1st "+count);
              System.out.println("The count of 2nd "+count1);
              
              
           }  
            
       } 
       
       catch (ClassNotFoundException | SQLException ex) 
       {
           Logger.getLogger(jaccard.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   public float cal_distance(int[] a,int[] b)
   {
       int[] unionval = new int[118];
       int[] interval = new int[118];
       for(int m=0;m<a.length;m++)
       {
           unionval[m] = compareunion(a[m],b[m]);
           interval[m] = compareinter(a[m],b[m]);
       }
       int sum=0,sum1=0;
       for(int m=0;m<a.length;m++)
       {
         sum=sum+unionval[m]  ;
       }
       for(int m=0;m<a.length;m++)
       {
         sum1=sum1+interval[m]  ;
       }
       System.out.println(sum1+" <-sum 1  sum->"+sum);
       return ((float)sum1/sum);
   }
   
   public int compareunion(int o,int t)
   {
       if(o==0 && t==0)return 0;
       else return 1;
       
   }
   public int compareinter(int o,int t)
   {
       if(o==1 && t==1)return 1;
       else return 0;
   }
}
