package br.com.sintaxerror.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import java.awt.TextArea;
import javax.swing.JButton;

public class TelaPagamento extends JFrame {

	private JPanel contentPane;
	private JLabel lblPagamentos;
	private JLabel lblCodigo;
	private JTextField txtCod;
	private JLabel lblCodPaciente;
	private JTextField txtCodPac;
	private JLabel lblNome;
	private JLabel lblValor;
	private JTextField txtValor;
	private JLabel lblData;
	private JFormattedTextField txtDta;
	private JLabel lblFormaPag;
	private JComboBox cbmFormPag;
	private TextArea textArea;
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
					TelaPagamento frame = new TelaPagamento();
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
	public TelaPagamento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblPagamentos = new JLabel("Pagamentos");
		lblPagamentos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPagamentos.setBounds(10, 11, 121, 26);
		contentPane.add(lblPagamentos);
		
		lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(10, 48, 46, 14);
		contentPane.add(lblCodigo);
		
		txtCod = new JTextField();
		txtCod.setBounds(57, 45, 86, 20);
		contentPane.add(txtCod);
		txtCod.setColumns(10);
		
		lblCodPaciente = new JLabel("Cod. Paciente:");
		lblCodPaciente.setBounds(168, 48, 86, 14);
		contentPane.add(lblCodPaciente);
		
		txtCodPac = new JTextField();
		txtCodPac.setBounds(251, 45, 86, 20);
		contentPane.add(txtCodPac);
		txtCodPac.setColumns(10);
		
		lblNome = new JLabel("Nome do Paciente");
		lblNome.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		lblNome.setBounds(10, 73, 327, 26);
		contentPane.add(lblNome);
		
		lblValor = new JLabel("Valor:");
		lblValor.setBounds(208, 113, 46, 14);
		contentPane.add(lblValor);
		
		txtValor = new JTextField();
		txtValor.setBounds(251, 110, 86, 20);
		contentPane.add(txtValor);
		txtValor.setColumns(10);
		
		lblData = new JLabel("Data:");
		lblData.setBounds(10, 113, 46, 14);
		contentPane.add(lblData);
		
		txtDta = new JFormattedTextField();
		txtDta.setBounds(57, 110, 106, 20);
		contentPane.add(txtDta);
		
		lblFormaPag = new JLabel("Forma Pag:");
		lblFormaPag.setBounds(10, 152, 73, 14);
		contentPane.add(lblFormaPag);
		
		cbmFormPag = new JComboBox();
		cbmFormPag.setBounds(78, 148, 259, 22);
		contentPane.add(cbmFormPag);
		
		textArea = new TextArea();
		textArea.setBounds(353, 11, 383, 160);
		contentPane.add(textArea);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(119, 181, 91, 81);
		contentPane.add(btnCadastrar);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(222, 181, 91, 81);
		contentPane.add(btnAlterar);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(326, 181, 91, 81);
		contentPane.add(btnConsultar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(433, 181, 91, 81);
		contentPane.add(btnExcluir);
		
		btnListar = new JButton("Listar");
		btnListar.setBounds(534, 181, 91, 81);
		contentPane.add(btnListar);
	}
}
