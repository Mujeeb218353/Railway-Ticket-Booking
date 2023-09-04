package railwaypro;
import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;
public class RailwayPro {
    Connection con;
    Statement st;
    ResultSet rs;
    RailwayPro(){
        try{
      // Driver activate      
      Class.forName("com.mysql.cj.jdbc.Driver");
      // Connection Class activate
      con=DriverManager.getConnection("jdbc:mysql://localhost:3306/railway pro","root","");
      // Statement Class activate
      st=con.createStatement();
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e,
                    "Error!", 1);
        }
    }
        public ResultSet adminlogin(String name,String pass){
            String sql= "select* from register where username='"+name+"'and password='"+pass+"'";
            
            try{
                rs=st.executeQuery(sql);
            }catch(SQLException e){
                System.out.println(e);   
            }
            return rs; 
        }
        public ResultSet forgotPassCheck(String name,String food){
            String sql= "select* from register where name='"+name+"'and food='"+food+"'";
            
            try{
                rs=st.executeQuery(sql);
            }catch(SQLException e){
                System.out.println(e);   
            }
            return rs;
        }
        
        public void updatePass(String pass,String name,String food){
            
            String sql = "UPDATE REGISTER SET password='"+pass+"' WHERE NAME IN ('"+name+"') AND FOOD IN ('"+food+"')";
            try{
                st.executeUpdate(sql);
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
        public void Register(String name,String username,String pass,String food){
        String sql;
        sql="INSERT INTO `register`(`Name`, `Username`, `Password`, `Food`) VALUES "
                + "('"+name+"','"+username+"','"+pass+"','"+food+"')";
        try{
        st.executeUpdate(sql); 
        JOptionPane.showMessageDialog(null, "Registered");
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        }
        public void booking(String class1,String coach,String seat,String seatNo,String date,String name,
        String contact,String cnic,String gender,String age,String fmSt,String toSt,String train){
        String sql;
        sql="INSERT INTO `booking`(`class`, `coach_no`, `seat`, `seatNo`, `date`, `name`,"
                + "`contact`,`cnic`,`gender`,`age`,`From_Station`,`To_Station`,`Train`) "
                + "VALUES ('"+class1+"','"+coach+"',"
                + "'"+seat+"','"+seatNo+"','"+date+"','"+name+"',"
                + "'"+contact+"','"+cnic+"','"+gender+"','"+age+"','"+fmSt+"','"+toSt+"','"+train+"')";
        try{
        st.executeUpdate(sql);
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        }
        public ResultSet ticketMatcher(String date,String seat,
                String seatNo,String class1,String coach,String fromStation,
        String toStation,String train) throws SQLException{
            String sql= "select* from booking where date='"+date+"'and seat='"+seat+"' and seatNo='"+seatNo+"'and "
                    + "class='"+class1+"' and coach_no='"+coach+"'and"
                    + " From_Station='"+fromStation+"' and To_Station='"+toStation+"' and Train='"+train+"'";
            
            try{
                rs=st.executeQuery(sql);
                            if(rs.next() == true){
            JOptionPane.showMessageDialog(null, "Already Booked");
            }
                            else{
                                JOptionPane.showMessageDialog(null, "Vacant");
                            }
            }catch(SQLException e){
                System.out.println(e);   
            }
            
            return rs;
            
        }
//        public void trainTimeUpdate(String name,String contact,String cnic){
//        //Connecting to database
//        String sql;
//        sql="INSERT INTO `persinfo`(`name`,"+
//        "`contact`,`cnic`)VALUES ('"+name+"','"+contact+"','"+cnic+"')";
//        try{
//        st.executeUpdate(sql);
//        JOptionPane.showMessageDialog(null, "Information Added Successfully");
//        }catch(HeadlessException | SQLException e){
//            JOptionPane.showMessageDialog(null, e);
//        }
//        }
        public void insertTrainData(String train,String dep,String arr,
                String frSt,String toSt,String ec,String ac,String bs){
            String sql = "INSERT INTO `"+train+"`(`train_dep`, `train_arr`, `from_st`, `to_st`, `fare_ec`, `fare_ac`, `fare_bs`)"
                    + " VALUES ('"+dep+"','"+arr+"','"+frSt+"','"+toSt+"','"+ec+"','"+ac+"',"
                    + "'"+bs+"')";
            try{
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Succeed...");
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        public ResultSet scheduleShow(String train,String frmSt,String toSt){
            String sql = "SELECT * FROM `"+train+"` WHERE "
                    + "from_st = '"+frmSt+"' and to_st = '"+toSt+"' ";
            try{
                rs=st.executeQuery(sql);
            }catch(SQLException e){
                System.out.println(e);   
            }
            return rs;
        }
        public ResultSet getDataFromTable(String train){
            String sql = "SELECT * FROM `"+train+"`";
            try{
            rs = st.executeQuery(sql);
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
            return rs;
        }
//        public void delt(){
//            try{
//        String sql = "DELETE FROM `pakistan express` WHERE to_st = 'Lahore' and fare_ac = 'B/4750'";
//   
//       st.executeUpdate(sql);
//       JOptionPane.showMessageDialog(null, "Deleted...");
//   }catch(SQLException e){
//       JOptionPane.showMessageDialog(null, e);
//   }
//        }
//        public void update1(){
//            try{
//        String sql = "UPDATE `pakistan express` SET fare_ec='B/250 S/200'"
//                + ",fare_ac='B/500',fare_bs='B/750' WHERE to_st = 'Faisalabad'";
//   
//       st.executeUpdate(sql);
//       JOptionPane.showMessageDialog(null, "updated...");
//   }catch(SQLException e){
//       JOptionPane.showMessageDialog(null, e);
//   }
//        }
    public static void main(String[] args) {
      // RailwayPro rp= new RailwayPro();
      // rp.update1();
    }
    
}
