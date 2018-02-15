import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class MigrationInterface extends JFrame {

	private JPanel contentPane;
	private JTextField dbnamesrc;
	private JTextField urlsrc;
	private JTextField usersrc;
	private JPasswordField passwordsrc;
	private JLabel lblClassname;
	private JLabel lblUrl;
	private JLabel lblUser;
	private JLabel lblPassword;
	private JLabel lblDestination;
	private JTextField dbnamedst;
	private JTextField urldst;
	private JTextField userdst;
	private JPasswordField passworddst;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_5;
	private JComboBox source;
	private JComboBox destination;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MigrationInterface frame = new MigrationInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MigrationInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSource = new JLabel("Source :");
		lblSource.setForeground(new Color(0, 0, 128));
		lblSource.setBackground(new Color(0, 0, 128));
		lblSource.setFont(new Font("American Typewriter", Font.PLAIN, 20));
		lblSource.setBounds(120, 2, 118, 37);
		contentPane.add(lblSource);
		
		dbnamesrc = new JTextField();
		dbnamesrc.setBounds(120, 78, 179, 37);
		contentPane.add(dbnamesrc);
		dbnamesrc.setColumns(10);
		
		urlsrc = new JTextField();
		urlsrc.setBounds(120, 138, 179, 37);
		contentPane.add(urlsrc);
		urlsrc.setColumns(10);
		
		usersrc = new JTextField();
		usersrc.setBounds(120, 200, 179, 37);
		contentPane.add(usersrc);
		usersrc.setColumns(10);
		
		passwordsrc = new JPasswordField();
		passwordsrc.setBounds(120, 258, 179, 37);
		contentPane.add(passwordsrc);
		
		lblClassname = new JLabel("DataBase Name :");
		lblClassname.setBounds(10, 78, 117, 37);
		contentPane.add(lblClassname);
		
		lblUrl = new JLabel("Url :");
		lblUrl.setBounds(10, 138, 99, 37);
		contentPane.add(lblUrl);
		
		lblUser = new JLabel("User :");
		lblUser.setBounds(10, 200, 99, 37);
		contentPane.add(lblUser);
		
		lblPassword = new JLabel("Password :");
		lblPassword.setBounds(10, 258, 99, 37);
		contentPane.add(lblPassword);
		
		lblDestination = new JLabel("Destination :");
		lblDestination.setForeground(new Color(0, 0, 128));
		lblDestination.setFont(new Font("American Typewriter", Font.PLAIN, 20));
		lblDestination.setBackground(new Color(0, 0, 128));
		lblDestination.setBounds(440, 2, 146, 37);
		contentPane.add(lblDestination);
		
		dbnamedst = new JTextField();
		dbnamedst.setBackground(SystemColor.activeCaption);
		dbnamedst.setColumns(10);
		dbnamedst.setBounds(440, 78, 179, 37);
		contentPane.add(dbnamedst);
		
		urldst = new JTextField();
		urldst.setColumns(10);
		urldst.setBounds(440, 138, 179, 37);
		contentPane.add(urldst);
		
		userdst = new JTextField();
		userdst.setColumns(10);
		userdst.setBounds(440, 200, 179, 37);
		contentPane.add(userdst);
		
		passworddst = new JPasswordField();
		passworddst.setBounds(440, 258, 179, 37);
		contentPane.add(passworddst);
		
		label_1 = new JLabel("Password :");
		label_1.setBounds(338, 258, 99, 37);
		contentPane.add(label_1);
		
		label_2 = new JLabel("User :");
		label_2.setBounds(338, 200, 99, 37);
		contentPane.add(label_2);
		
		label_3 = new JLabel("Url :");
		label_3.setBounds(338, 138, 99, 37);
		contentPane.add(label_3);
		
		label_5 = new JLabel("DataBase Name :");
		label_5.setBounds(338, 78, 117, 37);
		contentPane.add(label_5);
		
		JButton migrate = new JButton("Migrate");
		migrate.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String Source = (String) source.getSelectedItem(),
						Urlsrc = urlsrc.getText(),
						Usersrc = usersrc.getText(),
						Passwordsrc = passwordsrc.getText(),
						Destination = (String) destination.getSelectedItem(), 
						Urldst = urldst.getText(), 
						Userdst = userdst.getText(), 
						Passworddst = passworddst.getText(),
						Dbnamesrc = dbnamesrc.getText(),
						Dbnamedst = dbnamedst.getText();
						
					Main.migrate(Dbnamesrc, Source, Urlsrc, Usersrc, Passwordsrc,Dbnamedst, Destination, Urldst, Userdst, Passworddst);
			}
		});
		migrate.setFont(new Font("PT Sans Caption", Font.PLAIN, 16));
		migrate.setForeground(new Color(51, 153, 102));
		migrate.setBackground(new Color(0, 204, 204));
		migrate.setBounds(120, 320, 493, 37);
		contentPane.add(migrate);
		
		source = new JComboBox();
		source.setBackground(new Color(238, 238, 238));
		source.setModel(new DefaultComboBoxModel(new String[] {"MySQL", "PostgreSQL", "Oracle", "Sqlite", "SQL Server"}));
		source.setBounds(120, 36, 179, 27);
		contentPane.add(source);
		
		destination = new JComboBox();
		destination.setBackground(SystemColor.window);
		destination.setModel(new DefaultComboBoxModel(new String[] {"MySQL", "PostgreSQL", "Oracle", "Sqlite", "SQL Server"}));
		destination.setBounds(440, 36, 179, 27);
		contentPane.add(destination);
	}
}
