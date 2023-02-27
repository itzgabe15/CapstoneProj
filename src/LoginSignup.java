import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

class connectionUA{
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	connectionUA(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://capstoneproject.ck3v6fmgoed4.us-east-2.rds.amazonaws.com/PasswordManager","admin","itsc4155group3");
			System.out.println(" Connection up and runnin ");
		}catch(Exception e){}
			
	}

	boolean search(String text3, String text6)throws Exception{
		ps = con.prepareStatement("SELECT * FROM accountmanager WHERE username = ? AND password = ?");
		ps.setString(1,text3);
		ps.setString(2, text6);
		rs =ps.executeQuery();
		if(rs.next())
			return true;
		else
			return false;
	}
	
	
	void insert(String text3, String text4, String text5, String text6, String text7) throws Exception {
		ps = con.prepareStatement("INSERT INTO accountmanager(username, firstname, lastname, password, confirmpassword)" + " VALUES (?,?,?,?,?)");
		ps.setString(1, text3);
		ps.setString(2, text4);
		ps.setString(3, text5);
		ps.setString(4, text6);
		ps.setString(5, text7);
		ps.executeUpdate();
	
		
	}
}

class SignUp extends JFrame{
	JTextField text3,text4,text5,text6,text7;
	JButton butt3;
	JLabel label3,label6,label7,label8,label9,label10;
    SignUp(){
    	setLayout(null);
        
        
        label3 = new JLabel("Sign Up");
        label3.setBounds(100,10,300,30);
        add(label3);
        
        label6 = new JLabel("User Name");
        label6.setBounds(25,60,300,30);
        add(label6);
        
        label7 = new JLabel("First Name");
        label7.setBounds(25,100,300,30);
        add(label7);
        
        label8 = new JLabel("Last Name");
        label8.setBounds(25,140,300,30);
        add(label8);
        
        label9 = new JLabel("Password");
        label9.setBounds(25,180,300,30);
        add(label9);
        
        label10 = new JLabel("confirm Password");
        label10.setBounds(5,220,300,30);
        add(label10);
        
        text3 = new JTextField(60);
        text4 = new JTextField(60);
        text5 = new JTextField(60);
        text6 = new JPasswordField(60);
        text7 = new JPasswordField(60);
        butt3 =  new JButton("Sign Up");
        
        text3.setBounds(110,60,120,30);
        text4.setBounds(110,100,120,30);
        text5.setBounds(110,140,120,30);
        text6.setBounds(110,180,120,30);
        text7.setBounds(110,220,120,30);
        butt3.setBounds(120,280,80,30);
        
        butt3.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent ae) {
        		try {
        			connectionUA con = new connectionUA();
        			con.insert(text3.getText().toString(), text4.getText().toString(), text5.getText().toString(), text6.getText().toString(), text7.getText().toString());
        			JFrame cFrame = new JFrame();
        			JOptionPane.showMessageDialog(cFrame," Yo you are signed up");
        			dispose();
        		}catch(Exception e) {}
        		}
        	});
        
        
        
        add(text3);
        add(text4);
        add(text5);
        add(text6);
        add(text7);
        add(butt3);
    }
        
}


class Login extends JFrame{
	JTextField text1, text2;
	JButton butt1, butt2;
	JLabel label1, label2,label4,label5;
    Login(){
    	setLayout(null);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        label1 = new JLabel("Login");
        label1.setBounds(100,10,300,30);
        add(label1);
       
        label4 = new JLabel("User Name");
        label4.setBounds(25,60,300,30);
        add(label4);
        
        label5 = new JLabel("Password");
        label5.setBounds(30,100,300,30);
        add(label5);
        
        text1 = new JTextField(60);
        text2 = new JPasswordField(60);
        butt1 =  new JButton("Login");
        butt2 =  new JButton("Sign Up");
        
        text1.setBounds(100,60,120,30);
        text2.setBounds(100,100,120,30);
        butt1.setBounds(120,140,80,30);
        butt2.setBounds(120,170,80,30);
        
        label2 = new JLabel("");
        label2.setBounds(250,80,300,70);
        add(label2);
        
        add(text1);
        add(text2);
        add(butt1);
        add(butt2);
        
        /*
        butt1.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent ae){
        		boolean matched = false;
        		String text3 = text1.getText().toString();
        		String text6 = text2.getText().toString();
        		
        		try {
        			connectionUA con = new connectionUA();
        			if(con.search(text3, text6))
        				matched = true;
        		}catch(Exception e) {}
        		if(matched) {
        			label2.setText("welcom to the PM");
                    ArrayList<String> testArrayList = new ArrayList<>();
                    testArrayList.add("one");
                    PasswordListFrame passwordList = new PasswordListFrame(testArrayList);
                    //passwordList.setBounds(400,200,500,300);
                    //passwordList.setVisible(true);
                    PMFrame pmFrame = new PMFrame();
                }
        		else
        			label2.setText("YO! WRONG YOU!");
        	}
        });
        */
        
        butt2.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent ae){
        		SignUp sign = new SignUp();
        		sign.setVisible(true);
        		sign.setBounds(200,200,450,450);
        		
        	}
        });
        
       
    }
}
