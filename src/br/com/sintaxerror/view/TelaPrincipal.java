package br.com.sintaxerror.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnPaciente;
	private JMenu mnDentista;
	private JMenu mnPagamento;
	private JMenu mnSobre;
	private JLabel lblcopyrightSintaxError;
	private JLabel lblSistemaDeGerenciamento;
	private JButton btnNewButton;
	private JMenu mnConsulta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
					frame.setTitle("Gerenciamento Odontologico");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 706, 21);
		contentPane.add(menuBar);
		
		mnPaciente = new JMenu("Paciente");
		mnPaciente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TelaPaciente telaPaciente = new TelaPaciente();
				telaPaciente.setVisible(true);
				telaPaciente.setTitle("Paciente");
				telaPaciente.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		
		menuBar.add(mnPaciente);
		
		mnDentista = new JMenu("Dentista");
		mnDentista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaDentista telaDentista = new TelaDentista();
				telaDentista.setVisible(true);
				telaDentista.setTitle("Dentista");
				telaDentista.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		menuBar.add(mnDentista);
		
		mnPagamento = new JMenu("Pagamento");
		mnPagamento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaPagamento telaPagamento = new TelaPagamento();
				telaPagamento.setVisible(true);
				telaPagamento.setTitle("Pagamento");
				telaPagamento.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		
		mnConsulta = new JMenu("Consulta");
		mnConsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TelaConsulta telaConsulta = new TelaConsulta();
				telaConsulta.setVisible(true);
				telaConsulta.setTitle("Consulta");
				telaConsulta.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		menuBar.add(mnConsulta);
		menuBar.add(mnPagamento);
		
		mnSobre = new JMenu("Sobre");
		menuBar.add(mnSobre);
		
		lblcopyrightSintaxError = new JLabel("@Copyright Sintax Error");
		lblcopyrightSintaxError.setBounds(287, 418, 147, 14);
		contentPane.add(lblcopyrightSintaxError);
		
		lblSistemaDeGerenciamento = new JLabel("Sistema de Gerenciamento Cl\u00EDnica Odontologica");
		lblSistemaDeGerenciamento.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSistemaDeGerenciamento.setBounds(20, 26, 414, 28);
		contentPane.add(lblSistemaDeGerenciamento);
		
		btnNewButton = new JButton("");
		ImageIcon iconDentista = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/dentista.jpg"));
		iconDentista.setImage(iconDentista.getImage().getScaledInstance(600, 300, 300));
		btnNewButton.setIcon(iconDentista);
		btnNewButton.setBounds(20, 65, 686, 342);
		contentPane.add(btnNewButton);
		
	}
}
