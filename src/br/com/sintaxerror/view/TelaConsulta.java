package br.com.sintaxerror.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.sintaxerror.dao.ConsultaDAO;
import br.com.sintaxerror.dao.DentistaDAO;
import br.com.sintaxerror.dao.PacienteDAO;
import br.com.sintaxerror.model.Consulta;
import br.com.sintaxerror.model.Dentista;
import br.com.sintaxerror.model.Paciente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import java.awt.TextArea;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TelaConsulta extends JFrame {

	private JPanel contentPane;
	private JLabel lblConsulta;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblCpfPaciente;
	private JLabel lblPaciente;
	private JLabel lblCodDentista;
	private JTextField txtCodDent;
	private JLabel lblDentista;
	private JFormattedTextField txtCPF;
	private JLabel lblData;
	private JFormattedTextField txtData;
	private JLabel lblHora;
	private JFormattedTextField txtHora;
	private JLabel lblObeservaes;
	private TextArea txtObs;
	private TextArea txtMostar;
	private JButton btnCadastrar;
	private JButton btnAlterar;
	private JButton btnConsultar;
	private JButton btnExcluir;
	private JButton btnListar;
	private JButton btnLimpar;
	private Paciente paciente;
	private Dentista dentista;
	private Consulta consulta;
	private PacienteDAO pacienteDAO;
	private DentistaDAO dentistaDAO;
	private ConsultaDAO consultaDAO;
	private SimpleDateFormat formatDate;
	private SimpleDateFormat formatDateSql;
	private MaskFormatter ftmData;// Atributo formatador para data
	private MaskFormatter ftmCpf;// Atributo formatador para cpf
	private MaskFormatter ftmHora;// Atributo formatador para hora
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsulta frame = new TelaConsulta();
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
	public TelaConsulta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 923, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblConsulta = new JLabel("Consulta");
		lblConsulta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblConsulta.setBounds(10, 0, 98, 20);
		contentPane.add(lblConsulta);

		lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(44, 42, 46, 14);
		contentPane.add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(92, 39, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		lblCpfPaciente = new JLabel("CPF Paciente:");
		lblCpfPaciente.setBounds(10, 67, 80, 14);
		contentPane.add(lblCpfPaciente);

		lblPaciente = new JLabel("Nome Paciente");
		lblPaciente.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		lblPaciente.setBounds(222, 64, 272, 20);
		contentPane.add(lblPaciente);

		lblCodDentista = new JLabel("Cod Dentista:");
		lblCodDentista.setBounds(10, 92, 80, 14);
		contentPane.add(lblCodDentista);

		txtCodDent = new JTextField();
		txtCodDent.setBounds(92, 89, 120, 20);
		contentPane.add(txtCodDent);
		txtCodDent.setColumns(10);

		lblDentista = new JLabel("Nome Dentista");
		lblDentista.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		lblDentista.setBounds(222, 89, 272, 20);
		contentPane.add(lblDentista);

		try {
			ftmCpf = new MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro mascara CPF");
		}
		txtCPF = new JFormattedTextField(ftmCpf);
		txtCPF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				mostrarPaciente();
			}
		});
		txtCPF.setBounds(92, 64, 120, 20);
		contentPane.add(txtCPF);

		lblData = new JLabel("Data:");
		lblData.setBounds(222, 42, 34, 14);
		contentPane.add(lblData);
		
		try {
			ftmData = new MaskFormatter("##/##/####");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro mascara DATA");
		}
		txtData = new JFormattedTextField(ftmData);
		txtData.setBounds(266, 39, 120, 20);
		contentPane.add(txtData);

		lblHora = new JLabel("Hora:");
		lblHora.setBounds(396, 42, 46, 14);
		contentPane.add(lblHora);
		
		try {
			ftmHora = new MaskFormatter("##:##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro mascara Hora");
		}
		txtHora = new JFormattedTextField(ftmHora);
		txtHora.setBounds(438, 39, 55, 20);
		contentPane.add(txtHora);

		lblObeservaes = new JLabel("Obeserva\u00E7\u00F5es:");
		lblObeservaes.setBounds(10, 117, 108, 14);
		contentPane.add(lblObeservaes);

		txtObs = new TextArea();
		txtObs.setBounds(6, 137, 487, 67);
		contentPane.add(txtObs);

		txtMostar = new TextArea();
		txtMostar.setBounds(500, 33, 405, 171);
		contentPane.add(txtMostar);

		btnCadastrar = new JButton();
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					paciente = new Paciente();
					pacienteDAO = new PacienteDAO();
					paciente = pacienteDAO.consultar(txtCPF.getText());

					dentista = new Dentista();
					dentistaDAO = new DentistaDAO();
					dentista = dentistaDAO.consultar(Integer.parseInt(txtCodDent.getText()));

					consulta = new Consulta();
					consulta.setPaciente(paciente);
					consulta.setDentista(dentista);
					consulta.setCodConsulta(Integer.parseInt(txtCodigo.getText()));
					
					formatDate = new SimpleDateFormat("dd/MM/yyyy");
					formatDateSql = new SimpleDateFormat("yyyy-MM-dd");
					Date date = formatDate.parse(txtData.getText());
					consulta.setDia(formatDateSql.format(date));
					consulta.setHorario(txtHora.getText());
					consulta.setObs(txtObs.getText());
					
					consultaDAO = new ConsultaDAO();
					consultaDAO.salvar(consulta);

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao gravar consulta!\n" + e.getMessage());
				}

			}
		});
		btnCadastrar.setToolTipText("Cadastrar");
		ImageIcon iconSalvar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/salvar.png"));
		iconSalvar.setImage(iconSalvar.getImage().getScaledInstance(50, 50, 50));
		btnCadastrar.setIcon(iconSalvar);
		btnCadastrar.setBounds(201, 224, 83, 74);
		contentPane.add(btnCadastrar);

		btnAlterar = new JButton();
		btnAlterar.setToolTipText("Alterar");
		ImageIcon iconAlterar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/alterar.png"));
		iconAlterar.setImage(iconAlterar.getImage().getScaledInstance(50, 50, 50));
		btnAlterar.setIcon(iconAlterar);
		btnAlterar.setBounds(294, 224, 83, 74);
		contentPane.add(btnAlterar);

		btnConsultar = new JButton();
		btnConsultar.setToolTipText("Consultar");
		ImageIcon iconConsultar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/consultar.png"));
		iconConsultar.setImage(iconConsultar.getImage().getScaledInstance(50, 50, 50));
		btnConsultar.setIcon(iconConsultar);
		btnConsultar.setBounds(384, 224, 83, 74);
		contentPane.add(btnConsultar);

		btnExcluir = new JButton();
		btnExcluir.setToolTipText("Excluir");
		ImageIcon iconExcluir = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/excluir.png"));
		iconExcluir.setImage(iconExcluir.getImage().getScaledInstance(50, 50, 50));
		btnExcluir.setIcon(iconExcluir);
		btnExcluir.setBounds(474, 224, 83, 74);
		contentPane.add(btnExcluir);

		btnListar = new JButton();
		btnListar.setToolTipText("Listar");
		ImageIcon iconListar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/listar.png"));
		iconListar.setImage(iconListar.getImage().getScaledInstance(50, 50, 50));
		btnListar.setIcon(iconListar);
		btnListar.setBounds(564, 224, 83, 74);
		contentPane.add(btnListar);

		btnLimpar = new JButton();
		btnLimpar.setToolTipText("Limpar");
		ImageIcon iconLimpar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/limpar.png"));
		iconLimpar.setImage(iconLimpar.getImage().getScaledInstance(50, 50, 50));
		btnLimpar.setIcon(iconLimpar);
		btnLimpar.setBounds(654, 224, 83, 74);
		contentPane.add(btnLimpar);
	}
	
	public void mostrarPaciente() {
		try {
			paciente = new Paciente();
			pacienteDAO = new PacienteDAO();
			paciente = pacienteDAO.consultar(txtCPF.getText());
			lblPaciente.setText(paciente.getNome());
			
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao mostrar Paciente!");
		}
	}
	
	public void mostrarDentista() {
		try {
			dentista = new Dentista();
			dentistaDAO = new DentistaDAO();
			dentista = dentistaDAO.consultar(Integer.parseInt(txtCodDent.getText()));
			lblDentista.setText(dentista.getNomeDentista());
			
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao mostrar Dentista!");
		}
	}
}
