package br.com.sintaxerror.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.sintaxerror.dao.DentistaDAO;
import br.com.sintaxerror.model.Dentista;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.TextArea;
import java.awt.Button;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

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
	private JButton btnLimpar;
	private Dentista dentista;
	private DentistaDAO dentistadao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDentista frame = new TelaDentista();
					frame.setVisible(true);
					frame.setTitle("Dentista");
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
		setBounds(100, 100, 568, 389);
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
		txtNome.setBounds(197, 37, 344, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		lblEspecialidade = new JLabel("Especialidade:");
		lblEspecialidade.setBounds(10, 65, 86, 14);
		contentPane.add(lblEspecialidade);
		
		cbmEsp = new JComboBox();
		cbmEsp.setModel(new DefaultComboBoxModel(new String[] {"clareamento dental\t", "implante dentario", "dor de dente", "ortodontia", "endodontia", "protese dentaria", "aparelho ortodontico", "periodontia", "tratamento de canal", "odontopediatria", "aparelho dentario", "protese", "implante", "aparelho de dente", "aparelho dental", "dentes de porcelana", "dentadura", "clareamento", "protese fixa"}));
		cbmEsp.setBounds(95, 61, 446, 22);
		contentPane.add(cbmEsp);
		
		txtMostrar = new TextArea();
		txtMostrar.setBounds(10, 89, 531, 160);
		contentPane.add(txtMostrar);
		
		btnCadastrar = new JButton();
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//============================
				try {
					if (txtCod.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Instira um Codigo válido!");
						return;
					}
					if (txtNome.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Instira um nome válido!");
						return;
					}
			
					if (cbmEsp.getSelectedIndex() == 0){
						JOptionPane.showMessageDialog(null, "Escolha a especialidade válida!");
					}
				
					dentistadao  = new DentistaDAO();
					if (dentistadao.consultar(Integer.parseInt(txtCod.getText())) !=null) {
				JOptionPane.showMessageDialog(null, "Código cadastrado, insira um novo. ");
					}
				
					dentista = new Dentista ();
					dentista.setCodDentista(Integer.parseInt(txtCod.getText()));
					dentista.setNomeDentista(txtNome.getText());
					dentista.setEspecialidade(String.valueOf(cbmEsp.getSelectedItem()));
				
					
					dentistadao = new DentistaDAO();
					dentistadao.salvar(dentista);
					JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao gravar o dentista!\n" + e.getMessage());
				}
				
				//===========================
			}
		});
		btnCadastrar.setToolTipText("Cadastrar");
		ImageIcon iconSalvar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/salvar.png"));
		iconSalvar.setImage(iconSalvar.getImage().getScaledInstance(50, 50, 50));
		btnCadastrar.setIcon(iconSalvar);
		btnCadastrar.setBounds(5, 255, 83, 74);
		contentPane.add(btnCadastrar);
		
		btnAlterar = new JButton();
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (txtCod.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Instira um Codigo válido!");
						return;
					}
					if (txtNome.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Instira um nome válido!");
						return;
					}
			
					if (cbmEsp.getSelectedIndex() == 0){
						JOptionPane.showMessageDialog(null, "Escolha a especialidade válida!");
					}
				
				
					dentista = new Dentista ();
					dentista.setCodDentista(Integer.parseInt(txtCod.getText()));
					dentista.setNomeDentista(txtNome.getText());
					dentista.setEspecialidade(String.valueOf(cbmEsp.getSelectedItem()));
				
					
					dentistadao = new DentistaDAO();
					dentistadao.alterar(dentista);
					JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao alterar o dentista!\n" + e.getMessage());
				}
			}
		});
		btnAlterar.setToolTipText("Alterar");
		ImageIcon iconAlterar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/alterar.png"));
		iconAlterar.setImage(iconAlterar.getImage().getScaledInstance(50, 50, 50));
		btnAlterar.setIcon(iconAlterar);
		btnAlterar.setBounds(98, 255, 83, 74);
		contentPane.add(btnAlterar);
		
		btnConsultar = new JButton();
		btnConsultar.setToolTipText("Consultar");
		ImageIcon iconConsultar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/consultar.png"));
		iconConsultar.setImage(iconConsultar.getImage().getScaledInstance(50, 50, 50));
		btnConsultar.setIcon(iconConsultar);
		btnConsultar.setBounds(188, 255, 83, 74);
		contentPane.add(btnConsultar);
		
		btnExcluir = new JButton();
		btnExcluir.setToolTipText("Excluir");
		ImageIcon iconExcluir = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/excluir.png"));
		iconExcluir.setImage(iconExcluir.getImage().getScaledInstance(50, 50, 50));
		btnExcluir.setIcon(iconExcluir);
		btnExcluir.setBounds(278, 255, 83, 74);
		contentPane.add(btnExcluir);
		
		btnListar = new JButton();
		btnListar.setToolTipText("Listar");
		ImageIcon iconListar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/listar.png"));
		iconListar.setImage(iconListar.getImage().getScaledInstance(50, 50, 50));
		btnListar.setIcon(iconListar);
		btnListar.setBounds(368, 255, 83, 74);
		contentPane.add(btnListar);
		
		btnLimpar = new JButton();
		btnLimpar.setToolTipText("Limpar");
		ImageIcon iconLimpar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/limpar.png"));
		iconLimpar.setImage(iconLimpar.getImage().getScaledInstance(50, 50, 50));
		btnLimpar.setIcon(iconLimpar);
		btnLimpar.setBounds(458, 255, 83, 74);
		contentPane.add(btnLimpar);
	}
}
