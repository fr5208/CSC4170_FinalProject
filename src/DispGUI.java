
import java.awt.*;
import java.awt.event.*;

public class DispGUI extends Frame implements ActionListener
{
	private boolean loggedIn = false;
	Label loggedInLabel = new Label("Not Logged In");
	Button initDbButton = new Button("Initalize Database");
	TextField loginField = new TextField(32);
	TextField passField = new TextField(32);
	Button loginButton = new Button("Login");
	TextField author1Field = new TextField(64);
	TextField author2Field = new TextField(64);
	TextField author3Field = new TextField(64);
	TextField paperIDField = new TextField(64);
	Button assignPapersButton = new Button("Assign Papers");
	
	DispGUI()
	{
		addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent we) {dispose();} });
		
		add(initDbButton);
		initDbButton.addActionListener(this);
		initDbButton.setActionCommand("Initialize Database");
		initDbButton.setBounds(30, 100, 180, 30);
		
		add(loginField);
		
		add(passField);
		
		add(loginButton);
		loginButton.addActionListener(this);
		loginButton.setActionCommand("Login");
		loginButton.setBounds(30, 100, 180, 30);
		
		add(author1Field);
		add(author2Field);
		add(author3Field);
		add(paperIDField);
		
		author1Field.setText("Author 1");
		author2Field.setText("Author 2");
		author3Field.setText("Author 3");
		paperIDField.setText("Paper ID");
		
		add(assignPapersButton);
		assignPapersButton.addActionListener(this);
		assignPapersButton.setActionCommand("Assign Papers");
		assignPapersButton.setBounds(30, 100, 180, 30);
		
		add(loggedInLabel);
		
		setVisible(true);
		setSize(500, 500);
		setLayout(new FlowLayout());
	}

	public void setLoggedIn(int n)
	{
		if(n == 0)
		{
			loggedIn = false;
			loggedInLabel.setText("Not Logged In");
		}
		else if(n == 1)
		{
			loggedIn = true;
			loggedInLabel.setText("Logged In");
		}
	}
	
	public String getLogin()
	{
		return loginField.getText();
	}
	
	public String getPass()
	{
		return passField.getText();
	}
	
	public String getAuthor1()
	{
		return author1Field.getText();
	}
	
	public String getAuthor2()
	{
		return author2Field.getText();
	}
	
	public String getAuthor3()
	{
		return author3Field.getText();
	}
	
	public String getPaperID()
	{
		return paperIDField.getText();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		switch (ae.getActionCommand())
		{
			case "Initialize Database":
				Main.initDb();
				break;
			case "Login":
				setLoggedIn(Main.dbLogin());
				break;
			case "Assign Papers":
				if(loggedIn == true)
				{
					Main.assignAuthors();
				}
				break;
		}
	}
}
