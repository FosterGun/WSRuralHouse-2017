package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.RuralHouse;
import businessLogic.ApplicationFacadeInterfaceWS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton btnAddRuralHouse = null;
	private JButton btnSetAvailability = null;
	private JButton btnQueryAvailability = null;
	
	private static ApplicationFacadeInterfaceWS appFacadeInterface;

	public static ApplicationFacadeInterfaceWS getBusinessLogic(){
		return appFacadeInterface;
	}

	public static void setBussinessLogic (ApplicationFacadeInterfaceWS afi){
		appFacadeInterface = afi;
	}

	protected JLabel lblSelectOption;
	private JRadioButton rdbtnEnglish;
	private JRadioButton rdbtnEuskara;
	private JRadioButton rdbtnCastellano;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * This is the default constructor
	 */
	public MainGUI() {
		super();
//		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent e) {
//				ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();
//				try {
//					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
//				}
//				System.exit(1);
//			}
//		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 290);
		Locale.setDefault(new Locale("en"));
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); //Get screen dimension
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2); //Set the screen location to the center of the screen
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridLayout(5, 1, 0, 0));
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBtnAddRuralHouse());
			jContentPane.add(getBtnSetAvailability());
			jContentPane.add(getBtnQueryAvailability());
			jContentPane.add(getPanel());
		}
		return jContentPane;
	}

	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnAddRuralHouse() {
		if (btnAddRuralHouse == null) {
			btnAddRuralHouse = new JButton();
			btnAddRuralHouse.setText(ResourceBundle.getBundle("Etiquetas").getString("AddRuralHouse"));
			btnAddRuralHouse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialog newRuralHouse = new NewRuralHouseWindow();
					newRuralHouse.setVisible(true);
				}
			});
		}
		return btnAddRuralHouse;
	}

	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnSetAvailability() {
		if (btnSetAvailability == null) {
			btnSetAvailability = new JButton();
			btnSetAvailability.setText(ResourceBundle.getBundle("Etiquetas").getString("SetAvailability"));
			btnSetAvailability.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();
					Vector<RuralHouse> rhs = facade.getAllRuralHouses();
					JFrame a = new SetAvailabilityGUI(rhs);
					a.setVisible(true);
				}
			});
		}
		return btnSetAvailability;
	}

	/**
	 * This method initializes boton3
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnQueryAvailability() {
		if (btnQueryAvailability == null) {
			btnQueryAvailability = new JButton();
			btnQueryAvailability.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailability"));
			btnQueryAvailability.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new QueryAvailabilityGUI();
					a.setVisible(true);
				}
			});
		}
		return btnQueryAvailability;
	}


	private JLabel getLblNewLabel() {
		if (lblSelectOption == null) {
			lblSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			lblSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblSelectOption.setForeground(Color.BLACK);
			lblSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblSelectOption;
	}
	private JRadioButton getRdbtnEnglish() {
		if (rdbtnEnglish == null) {
			rdbtnEnglish = new JRadioButton("English");
			rdbtnEnglish.setSelected(true);
			rdbtnEnglish.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rdbtnEnglish.isSelected()) {
						Locale.setDefault(new Locale("en"));
						System.out.println("Locale: "+Locale.getDefault());
						redraw();		
					}
				}
			});
			buttonGroup.add(rdbtnEnglish);
		}
		return rdbtnEnglish;
	}
	private JRadioButton getRdbtnEuskara() {
		if (rdbtnEuskara == null) {
			rdbtnEuskara = new JRadioButton("Euskara");
			rdbtnEuskara.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(rdbtnEuskara.isSelected()) {
						Locale.setDefault(new Locale("eus"));
						System.out.println("Locale: "+Locale.getDefault());
						redraw();
					}
				}
			});
			buttonGroup.add(rdbtnEuskara);
		}
		return rdbtnEuskara;
	}
	private JRadioButton getRdbtnCastellano() {
		if (rdbtnCastellano == null) {
			rdbtnCastellano = new JRadioButton("Castellano");
			rdbtnCastellano.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rdbtnCastellano.isSelected()) {
						Locale.setDefault(new Locale("es"));
						System.out.println("Locale: "+Locale.getDefault());
						redraw();
					}
				}
			});
			buttonGroup.add(rdbtnCastellano);
		}
		return rdbtnCastellano;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getRdbtnEuskara());
			panel.add(getRdbtnCastellano());
			panel.add(getRdbtnEnglish());
		}
		return panel;
	}

	private void redraw() {
		lblSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		btnAddRuralHouse.setText(ResourceBundle.getBundle("Etiquetas").getString("AddRuralHouse"));
		btnSetAvailability.setText(ResourceBundle.getBundle("Etiquetas").getString("SetAvailability"));
		btnQueryAvailability.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailability"));		
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

} // @jve:decl-index=0:visual-constraint="0,0"

