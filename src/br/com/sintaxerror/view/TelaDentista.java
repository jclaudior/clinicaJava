package br.com.sintaxerror.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.TextArea;
import java.awt.Button;
import javax.swing.JButton;

public class TelaDentista extends JFrame {

	private JPanel contentPane;
	private JLabel lblCadastroDentista;
	private JLabel lblCodigo;
	private JTextField txtCod;
	private JLabel lblNome;
	private JTextField txtNome;
	private JLabel lblEspecialidade;
	private JComboBox cbmEsp;
	private TextArea txtMostrar;
	private JButton btnCadastrar;
	private JButton btnAlterar;
	private JButton btnConsultar;
	private JButton btnExcluir;
	private JButton btnListar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDentista frame = new TelaDentista();
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
	public TelaDentista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCadastroDentista = new JLabel("Cadastro Dentista");
		lblCadastroDentista.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCadastroDentista.setBounds(10, 11, 136, 14);
		contentPane.add(lblCadastroDentista);
		
		lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(10, 40, 46, 14);
		contentPane.add(lblCodigo);
		
		txtCod = new JTextField();
		txtCod.setBounds(58, 36, 86, 20);
		contentPane.add(txtCod);
		txtCod.setColumns(10);
		
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(153, 40, 46, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(197, 37, 255, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		lblEspecialidade = new JLabel("Especialidade:");
		lblEspecialidade.setBounds(10, 65, 86, 14);
		contentPane.add(lblEspecialidade);
		
		cbmEsp = new JComboBox();
		cbmEsp.setBounds(95, 61, 357, 22);
		contentPane.add(cbmEsp);
		
		txtMostrar = new TextArea();
		txtMostrar.setBounds(10, 89, 442, 160);
		contentPane.add(txtMostrar);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(5, 255, 83, 74);
		contentPane.add(btnCadastrar);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(95, 255, 83, 74);
		contentPane.add(btnAlterar);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(185, 255, 83, 74);
		contentPane.add(btnConsultar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(275, 255, 83, 74);
		contentPane.add(btnExcluir);
		
		btnListar = new JButton("Listar");
		btnListar.setBounds(369, 255, 83, 74);
		contentPane.add(btnListar);
	}
}
