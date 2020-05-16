package br.com.sintaxerror.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.sintaxerror.dao.ConsultaDAO;
import br.com.sintaxerror.dao.DentistaDAO;
import br.com.sintaxerror.dao.PacienteDAO;
import br.com.sintaxerror.dao.PagamentoDAO;
import br.com.sintaxerror.model.Consulta;
import br.com.sintaxerror.model.Dentista;
import br.com.sintaxerror.model.Paciente;
import br.com.sintaxerror.model.Pagamento;

public class TelaPagamento extends JFrame {

	private JPanel contentPane;
	private JLabel lblPagamentos;
	private JLabel lblCodigo;
	private JTextField txtCod;
	private JLabel lblCodPaciente;
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
	private MaskFormatter ftmData;// Atributo formatador para data
	private MaskFormatter ftmCpf;// Atributo formatador para cpf
	private JFormattedTextField txtCPF;
	private JButton btnLimpar;
	private Pagamento pagamento;// Inports Dao
	private PagamentoDAO pagamentoDAO;// Inports Dao
	
	// Abrindo aplicação
	
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

	// Criando frame
	
	public TelaPagamento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 312);
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
		
		
		lblCodPaciente = new JLabel("CPF Paciente:");
		lblCodPaciente.setBounds(153, 48, 86, 14);
		contentPane.add(lblCodPaciente);
		
		lblNome = new JLabel("Nome do Paciente");
		lblNome.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		lblNome.setBounds(10, 73, 327, 26);
		contentPane.add(lblNome);
		
		lblValor = new JLabel("Valor:");
		lblValor.setBounds(193, 110, 46, 14);
		contentPane.add(lblValor);
		
		txtValor = new JTextField();
		txtValor.setBounds(239, 107, 98, 20);
		contentPane.add(txtValor);
		txtValor.setColumns(10);
		
		lblData = new JLabel("Data:");
		lblData.setBounds(10, 113, 36, 14);
		contentPane.add(lblData);
		
		try {
			ftmData = new MaskFormatter("##/##/####");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro na mascara Data Nascimento\n");
		}
		txtDta = new JFormattedTextField(ftmData);
		txtDta.setBounds(44, 110, 106, 20);
		contentPane.add(txtDta);
		
		lblFormaPag = new JLabel("Forma Pag:");
		lblFormaPag.setBounds(10, 152, 73, 14);
		contentPane.add(lblFormaPag);
		
		cbmFormPag = new JComboBox();
		cbmFormPag.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma Opçao", "Dinheiro", "Cartao"}));
		cbmFormPag.setBounds(78, 148, 259, 22);
		contentPane.add(cbmFormPag);
		
		textArea = new TextArea();
		textArea.setFocusable(false);
		textArea.setFocusTraversalKeysEnabled(false);
		textArea.setEditable(false);
		textArea.setBounds(353, 11, 383, 160);
		contentPane.add(textArea);
		
		btnCadastrar = new JButton();
		btnCadastrar.setToolTipText("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					pagamento = new Pagamento();
					
					Paciente paciente = new Paciente();
					PacienteDAO pacienteDAO = new PacienteDAO();
					paciente = pacienteDAO.consultar(txtCPF.getText());
					
					pagamento.setCodPagamento(Integer.parseInt(txtCod.getText()));
					pagamento.setDia(txtDta.getText());
					pagamento.setValor(Double.parseDouble(txtValor.getText()));
					pagamento.setFormaPagamento((String)cbmFormPag.getSelectedItem());
					
					pagamento.setPaciente(paciente);
					
					pagamentoDAO = new PagamentoDAO();
					pagamentoDAO.salvar(pagamento);
					JOptionPane.showMessageDialog(null, "Salvo com sucess!");
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao salvar!\n" + e.getMessage());
				}
			}
		});
		ImageIcon iconSalvar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/salvar.png"));
		iconSalvar.setImage(iconSalvar.getImage().getScaledInstance(50, 50, 50));
		btnCadastrar.setIcon(iconSalvar);
		btnCadastrar.setBounds(78, 181, 91, 81);
		contentPane.add(btnCadastrar);
		
		btnAlterar = new JButton();
		btnAlterar.setToolTipText("Alterar");
		ImageIcon iconAlterar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/alterar.png"));
		iconAlterar.setImage(iconAlterar.getImage().getScaledInstance(50, 50, 50));
		btnAlterar.setIcon(iconAlterar);
		btnAlterar.setBounds(179, 181, 91, 81);
		contentPane.add(btnAlterar);
		
		btnConsultar = new JButton();
		btnConsultar.setToolTipText("Consultar");
		ImageIcon iconConsultar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/consultar.png"));
		iconConsultar.setImage(iconConsultar.getImage().getScaledInstance(50, 50, 50));
		btnConsultar.setIcon(iconConsultar);
		btnConsultar.setBounds(280, 181, 91, 81);
		contentPane.add(btnConsultar);
		
		btnExcluir = new JButton();
		btnExcluir.setToolTipText("Excluir");
		ImageIcon iconExcluir = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/excluir.png"));
		iconExcluir.setImage(iconExcluir.getImage().getScaledInstance(50, 50, 50));
		btnExcluir.setIcon(iconExcluir);
		btnExcluir.setBounds(381, 181, 91, 81);
		contentPane.add(btnExcluir);
		
		btnListar = new JButton();
		btnListar.setToolTipText("Listar");
		ImageIcon iconListar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/listar.png"));
		iconListar.setImage(iconListar.getImage().getScaledInstance(50, 50, 50));
		btnListar.setIcon(iconListar);
		btnListar.setBounds(483, 181, 91, 81);
		contentPane.add(btnListar);
		
		try {
			ftmCpf = new MaskFormatter("###.###.###-##");
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Erro na mascara CPF\n");
		}
		txtCPF = new JFormattedTextField(ftmCpf);
		txtCPF.setBounds(239, 48, 98, 17);
		contentPane.add(txtCPF);
		
		btnLimpar = new JButton();
		btnLimpar.setToolTipText("Limpar");
		ImageIcon iconLimpar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/limpar.png"));
		iconLimpar.setImage(iconLimpar.getImage().getScaledInstance(50, 50, 50));
		btnLimpar.setIcon(iconLimpar);
		btnLimpar.setBounds(584, 181, 91, 81);
		contentPane.add(btnLimpar);
	}
}
