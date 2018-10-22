/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.FuncionarioDAO;
import Model.Funcionario;
import Valida.ValidaCPF;
import Valida.ValidaData;
import Valida.ValidaEmail;
import com.sun.java.swing.plaf.windows.WindowsButtonUI;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.plaf.metal.MetalTabbedPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dc
 */
public class FuncionarioView extends javax.swing.JInternalFrame {

    Funcionario funcionario = new Funcionario();
    FuncionarioDAO funcionariodao = new FuncionarioDAO();
    List<Funcionario> listafuncionario = new ArrayList<>();
    
    public FuncionarioView() {
        initComponents();
        PainelFuncionario.setUI(new  MetalTabbedPaneUI());
        BtnSair1.setUI(new WindowsButtonUI());
        BtnAlterar.setUI(new WindowsButtonUI());
        BtnCancelar.setUI(new WindowsButtonUI());
        BtnExcluir.setUI(new WindowsButtonUI());
        BtnNovo.setUI(new WindowsButtonUI());
        BtnSair.setUI(new WindowsButtonUI());
        BtnSalvar.setUI(new WindowsButtonUI());
        TblFuncionario.setUI(new BasicTableUI());
        
        TxtId.setVisible(false);
        PainelFuncionario.setSelectedIndex(1);
        PrepararCancelarSalvar();
        atualizaTabelaFuncionario();
        DesativarCampos();
    }
    
    public void atualizaTabelaFuncionario(){
        funcionario = new Funcionario();
        try {
            listafuncionario = funcionariodao.listaTodos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        String dados[][] = new String[listafuncionario.size()][6];
            int i = 0;
            for (Funcionario funcionario : listafuncionario) {
                dados[i][0] = String.valueOf(funcionario.getId());
                dados[i][1] = funcionario.getNome();
                dados[i][2] = funcionario.getRg();
                dados[i][3] = funcionario.getCpf();
                dados[i][4] = funcionario.getNascimento();
                dados[i][5] = funcionario.getTelefone1();
           
                i++;
            }
            String tituloColuna[] = {"Id", "Nome", "RG", "CPF","Nascimento","Fone Principal"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            TblFuncionario.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            TblFuncionario.getColumnModel().getColumn(0).setMaxWidth(0);
            TblFuncionario.getColumnModel().getColumn(0).setMinWidth(0);
            TblFuncionario.getColumnModel().getColumn(0).setPreferredWidth(0);
            
            TblFuncionario.getColumnModel().getColumn(1).setPreferredWidth(400);
            TblFuncionario.getColumnModel().getColumn(2).setPreferredWidth(100);
            TblFuncionario.getColumnModel().getColumn(3).setPreferredWidth(100);
            TblFuncionario.getColumnModel().getColumn(4).setPreferredWidth(100);
            TblFuncionario.getColumnModel().getColumn(5).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            TblFuncionario.getColumnModel().getColumn(4).setCellRenderer(centralizado);
            TblFuncionario.setRowHeight(35);
            TblFuncionario.updateUI();
    }
    
    public void atualizaTabelaFuncionarioBusca(){
        funcionario = new Funcionario();
       
        
        String dados[][] = new String[listafuncionario.size()][6];
            int i = 0;
            for (Funcionario funcionario : listafuncionario) {
                dados[i][0] = String.valueOf(funcionario.getId());
                dados[i][1] = funcionario.getNome();
                dados[i][2] = funcionario.getRg();
                dados[i][3] = funcionario.getCpf();
                dados[i][4] = funcionario.getNascimento();
                dados[i][5] = funcionario.getTelefone1();
           
                i++;
            }
            String tituloColuna[] = {"Id", "Nome", "RG", "CPF","Nascimento","Fone Principal"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            TblFuncionario.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            TblFuncionario.getColumnModel().getColumn(0).setMaxWidth(0);
            TblFuncionario.getColumnModel().getColumn(0).setMinWidth(0);
            TblFuncionario.getColumnModel().getColumn(0).setPreferredWidth(0);
            
            TblFuncionario.getColumnModel().getColumn(1).setPreferredWidth(400);
            TblFuncionario.getColumnModel().getColumn(2).setPreferredWidth(100);
            TblFuncionario.getColumnModel().getColumn(3).setPreferredWidth(100);
            TblFuncionario.getColumnModel().getColumn(4).setPreferredWidth(100);
            TblFuncionario.getColumnModel().getColumn(5).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            TblFuncionario.getColumnModel().getColumn(4).setCellRenderer(centralizado);
            TblFuncionario.setRowHeight(35);
            TblFuncionario.updateUI();
    }
    
    public void DesativarCampos(){
        TxtNomeCompleto.setEnabled(false);
        TxtRG.setEnabled(false);
        TxtCPF.setEnabled(false);
        TxtNascimento.setEnabled(false);
        TxtTelefone1.setEnabled(false);
        TxtTelefone2.setEnabled(false);
        TxtEmail.setEnabled(false);
        TxtCep.setEnabled(false);
        TxtCidade.setEnabled(false);
        TxtBairro.setEnabled(false);
        TxtEndereco.setEnabled(false);
        TxtNumero.setEnabled(false);
        TxtLogin.setEnabled(false);
        TxtSenha.setEnabled(false);
        TxtSenhaRepetida.setEnabled(false); 
        RadioAdm.setEnabled(false);
        RadioFunci.setEnabled(false);
    }
    
    public void AtivarCampos(){
        TxtNomeCompleto.setEnabled(true);
        TxtRG.setEnabled(true);
        TxtCPF.setEnabled(true);
        TxtNascimento.setEnabled(true);
        TxtTelefone1.setEnabled(true);
        TxtTelefone2.setEnabled(true);
        TxtEmail.setEnabled(true);
        TxtCep.setEnabled(true);
        TxtCidade.setEnabled(true);
        TxtBairro.setEnabled(true);
        TxtEndereco.setEnabled(true);
        TxtNumero.setEnabled(true);
        TxtLogin.setEnabled(true);
        TxtSenha.setEnabled(true);
        TxtSenhaRepetida.setEnabled(true); 
        RadioAdm.setEnabled(true);
        RadioFunci.setEnabled(true);
    }
    
    public void PrepararNovo(){
        BtnSair.setEnabled(false);
        BtnSair1.setEnabled(false);
        BtnAlterar.setEnabled(false);
        BtnExcluir.setEnabled(false);
        BtnNovo.setEnabled(false);
        BtnCancelar.setEnabled(true);
        BtnSalvar.setEnabled(true);
        TblFuncionario.setEnabled(false);
        TblFuncionario.clearSelection();
    }
    
    public void PrepararCancelarSalvar(){
        BtnNovo.setEnabled(true);
        BtnAlterar.setEnabled(false);
        BtnCancelar.setEnabled(false);
        BtnSalvar.setEnabled(false);
        TblFuncionario.setEnabled(true);
        BtnExcluir.setEnabled(false);
        BtnSair.setEnabled(true);
        BtnSair1.setEnabled(true);
    }
    
    public void LimparCampos(){
        TxtId.setText("");
        TxtNomeCompleto.setText("");
        TxtRG.setText("");
        TxtCPF.setValue("");
        TxtNascimento.setValue("");
        TxtTelefone1.setValue("");
        TxtTelefone2.setValue("");
        TxtEmail.setText("");
        TxtCep.setValue("");
        TxtCidade.setText("");
        TxtNumero.setText("");
        TxtEndereco.setText("");
        TxtBairro.setText("");
        TxtLogin.setText("");
        TxtSenha.setText("");
        TxtSenhaRepetida.setText("");
        RadioAdm.setSelected(false);
        RadioFunci.setSelected(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        GgrupoRadio = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        PainelFuncionario = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        TxtNomeCompleto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TxtRG = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TxtTelefone1 = new javax.swing.JFormattedTextField();
        TxtTelefone2 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        TxtEmail = new javax.swing.JTextField();
        TxtId = new javax.swing.JTextField();
        TxtNascimento = new javax.swing.JFormattedTextField();
        TxtCPF = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        TxtEndereco = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        TxtCidade = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        TxtCep = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        TxtNumero = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        TxtBairro = new javax.swing.JTextField();
        BtnCancelar = new javax.swing.JButton();
        BtnSalvar = new javax.swing.JButton();
        BtnNovo = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        TxtLogin = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        TxtSenha = new javax.swing.JPasswordField();
        TxtSenhaRepetida = new javax.swing.JPasswordField();
        jPanel6 = new javax.swing.JPanel();
        RadioFunci = new javax.swing.JRadioButton();
        RadioAdm = new javax.swing.JRadioButton();
        BtnSair = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblFuncionario = new javax.swing.JTable();
        BtnAlterar = new javax.swing.JButton();
        BtnExcluir = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        TxtPesquisa = new javax.swing.JTextField();
        BtnSair1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        setMinimumSize(new java.awt.Dimension(998, 600));
        setPreferredSize(new java.awt.Dimension(998, 700));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(410, 307));
        jPanel1.setMinimumSize(new java.awt.Dimension(410, 307));
        jPanel1.setPreferredSize(new java.awt.Dimension(410, 307));

        PainelFuncionario.setBackground(new java.awt.Color(255, 255, 255));
        PainelFuncionario.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        PainelFuncionario.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        PainelFuncionario.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        PainelFuncionario.setPreferredSize(new java.awt.Dimension(950, 400));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados Pessoais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 14))); // NOI18N

        TxtNomeCompleto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel1.setText("Nome Completo: ");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel2.setText("RG: ");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel3.setText("CPF: ");

        TxtRG.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel4.setText("Data de nascimento:");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel5.setText("Fone Principal:");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel6.setText("Fone:");

        try {
            javax.swing.text.MaskFormatter mascTelefone = new javax.swing.text.MaskFormatter("(**)* ####-####");
            mascTelefone.setValidCharacters("0123456789 ");
            TxtTelefone1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mascTelefone));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtTelefone1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        try {
            javax.swing.text.MaskFormatter mascTelefone = new javax.swing.text.MaskFormatter("(**)* ####-####");
            mascTelefone.setValidCharacters("0123456789 ");
            TxtTelefone2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mascTelefone));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtTelefone2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel7.setText("E-mail:");

        TxtEmail.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        TxtId.setEnabled(false);

        try {
            TxtNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtNascimento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        try {
            TxtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtCPF.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TxtId, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TxtNomeCompleto)
                            .addComponent(TxtEmail))
                        .addGap(53, 53, 53))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(TxtRG)
                            .addComponent(TxtTelefone1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(TxtTelefone2, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(TxtCPF))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(TxtNascimento)
                                .addGap(53, 53, 53))))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TxtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtNomeCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtTelefone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtTelefone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados Complementares", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 14))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel8.setText("Endereço:");

        TxtEndereco.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel9.setText("Cidade: ");

        TxtCidade.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel10.setText("CEP:");

        try {
            TxtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtCep.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        TxtCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtCepActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel11.setText("Número:");

        TxtNumero.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel13.setText("Bairro:");

        TxtBairro.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(TxtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(TxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(TxtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(TxtCidade))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(168, 168, 168))
                    .addComponent(TxtBairro))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BtnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnCancelar.setText("CANCELAR");
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        BtnSalvar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnSalvar.setText("SALVAR");
        BtnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarActionPerformed(evt);
            }
        });

        BtnNovo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnNovo.setText("NOVO");
        BtnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNovoActionPerformed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acesso", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 14))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel16.setText("Login:");

        TxtLogin.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel17.setText("Senha:");

        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel19.setText("Repetir senha:");

        TxtSenha.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        TxtSenhaRepetida.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtSenha)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel19))
                        .addGap(0, 54, Short.MAX_VALUE))
                    .addComponent(TxtLogin)
                    .addComponent(TxtSenhaRepetida))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtSenhaRepetida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nível", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 14))); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(115, 120));

        RadioFunci.setBackground(new java.awt.Color(255, 255, 255));
        GgrupoRadio.add(RadioFunci);
        RadioFunci.setText("Funcionário");

        RadioAdm.setBackground(new java.awt.Color(255, 255, 255));
        GgrupoRadio.add(RadioAdm);
        RadioAdm.setText("Administrador");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RadioAdm)
                    .addComponent(RadioFunci))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadioFunci)
                .addGap(18, 18, 18)
                .addComponent(RadioAdm)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        BtnSair.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnSair.setText("SAIR");
        BtnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(BtnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnCancelar)
                        .addComponent(BtnSalvar)
                        .addComponent(BtnNovo))
                    .addComponent(BtnSair))
                .addGap(31, 31, 31))
        );

        PainelFuncionario.addTab("    Cadastrar    ", jPanel3);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(945, 0));

        TblFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TblFuncionario.setRowHeight(24);
        TblFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblFuncionarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblFuncionario);

        BtnAlterar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnAlterar.setText("ALTERAR");
        BtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlterarActionPerformed(evt);
            }
        });

        BtnExcluir.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnExcluir.setText("EXCLUIR");
        BtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExcluirActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(250, 250, 250));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 14))); // NOI18N

        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel18.setText("Nome:");

        TxtPesquisa.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        TxtPesquisa.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                TxtPesquisaCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel18)
                .addGap(59, 59, 59)
                .addComponent(TxtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(261, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(TxtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        BtnSair1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnSair1.setText("SAIR");
        BtnSair1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSair1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(BtnSair1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAlterar)
                    .addComponent(BtnExcluir)
                    .addComponent(BtnSair1))
                .addGap(13, 13, 13))
        );

        PainelFuncionario.addTab("    Consultar    ", jPanel2);

        jLabel14.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel14.setText("FUNCIONÁRIO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(PainelFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PainelFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 977, Short.MAX_VALUE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja cancelar o cadastro ? Isso ira apagar todos os dados ja inseridos. ");
        if(confirma == 0){
            DesativarCampos();
            LimparCampos();
            PrepararCancelarSalvar();
        }    
    }//GEN-LAST:event_BtnCancelarActionPerformed

    private void TxtCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtCepActionPerformed

    private void BtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExcluirActionPerformed
       if(TxtId.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "Selecione um dado para exlui-lo!","erro", JOptionPane.WARNING_MESSAGE);
       }
       else{
           funcionario.setId(Integer.parseInt(TxtId.getText()));
           int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir: "+ TxtNomeCompleto.getText());
           if(confirma == 0){
               
               try {
                   funcionariodao.Excluir(funcionario);
                   JOptionPane.showMessageDialog(null, "Excluido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                   LimparCampos();
               } catch (SQLException ex) {
                   Logger.getLogger(ClienteView.class.getName()).log(Level.SEVERE, null, ex);
               }
                 atualizaTabelaFuncionario();
                BtnExcluir.setEnabled(false);
                BtnAlterar.setEnabled(false);
           }
       }
    }//GEN-LAST:event_BtnExcluirActionPerformed

    private void BtnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNovoActionPerformed
       AtivarCampos();
       PrepararNovo();
       LimparCampos();
    }//GEN-LAST:event_BtnNovoActionPerformed

    private void BtnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarActionPerformed
        funcionario = new Funcionario();
        
         // Verificações de campos obrigatorios
        if(TxtNomeCompleto.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Campo Nome Completo obrigatorio !","erro", JOptionPane.WARNING_MESSAGE);
            TxtNomeCompleto.requestFocus();
            return;
        }
        if(TxtNascimento.getText().equals("  /  /    ")){
            JOptionPane.showMessageDialog(null, "Campo Data de nascimento obrigatorio !","erro", JOptionPane.WARNING_MESSAGE);
            TxtNascimento.requestFocus();
            return;
        }
        if(TxtRG.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Campo RG obrigatorio !","erro", JOptionPane.WARNING_MESSAGE);
            TxtRG.requestFocus();
            return;
        }
        if(TxtCPF.getText().equals("   .   .   -  ")){
            JOptionPane.showMessageDialog(null, "Campo CPF obrigatorio !","erro", JOptionPane.WARNING_MESSAGE);
            TxtCPF.requestFocus();
            return;
        }
        if(TxtTelefone1.getText().equals("(  )     -    ")){
            JOptionPane.showMessageDialog(null, "Necessário um telefone de contato !","erro", JOptionPane.WARNING_MESSAGE);
            TxtTelefone1.requestFocus();
            return;
        }
        if(TxtLogin.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Necessário um login !","erro", JOptionPane.WARNING_MESSAGE);
            TxtLogin.requestFocus();
            return;
        }
        if(TxtSenha.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Necessário uma senha !","erro", JOptionPane.WARNING_MESSAGE);
            TxtSenha.requestFocus();
            return;
        }
        
        //Validação do CPF
        Boolean ValidacaoCPF = ValidaCPF.isCPF(TxtCPF.getText());
        if(ValidacaoCPF == false){
            JOptionPane.showMessageDialog(null, "CPF invalido !","erro", JOptionPane.WARNING_MESSAGE);
            TxtCPF.requestFocus();
            return;
        }
        //Iniciação da validão de data verdadeira;
        Boolean ValidacaoData = ValidaData.ValidarData(TxtNascimento.getText());
        if(ValidacaoData == false){
            JOptionPane.showMessageDialog(null, "Data Invalida. Digite uma data verdadeira!");
            TxtNascimento.requestFocus();
            return;
        }
           
        if(!TxtEmail.getText().isEmpty()){
             //Validao Email
            Boolean ValidaoEmail = ValidaEmail.ValidarEmail(TxtEmail.getText());
            if(ValidaoEmail == false){
               JOptionPane.showMessageDialog(null, "Email invalido!");
               TxtEmail.requestFocus();
               return;
            }
        }
       
        Boolean auxnivel;
        if (RadioAdm.isSelected() == true) {
            auxnivel = true;
        }else{
            auxnivel = false;
        }
        
        
        if(TxtSenha.getText().equals(TxtSenhaRepetida.getText())){
            
            if(TxtId.getText().isEmpty()){
                //Salva tudo digitado no campo de texto para o objeto e salva no banco de dados
                funcionario.setNome(TxtNomeCompleto.getText());
                funcionario.setNascimento(TxtNascimento.getText());
                funcionario.setRg(TxtRG.getText());
                funcionario.setCpf(TxtCPF.getText());
                funcionario.setTelefone1(TxtTelefone1.getText());
                funcionario.setTelefone2(TxtTelefone2.getText());
                funcionario.setEmail(TxtEmail.getText());
                funcionario.setRua(TxtEndereco.getText());
                funcionario.setNumero(TxtNumero.getText());
                funcionario.setCep(TxtCep.getText());
                funcionario.setCidade(TxtCidade.getText());
                funcionario.setBairro(TxtBairro.getText());
                funcionario.setLogin(TxtLogin.getText());
                funcionario.setSenha(TxtSenha.getText());
                funcionario.setNivel(auxnivel);
                
                try {
                    funcionariodao.Salvar(funcionario);
                    JOptionPane.showMessageDialog(null, "Gravado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE); 
                } catch (SQLException ex) {
                    Logger.getLogger(FuncionarioView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                //Salva tudo que foi alterado nos campos de texto para o objeto e salva no banco de dados
                funcionario.setId(Integer.parseInt(TxtId.getText()));
                funcionario.setNome(TxtNomeCompleto.getText());
                funcionario.setNascimento(TxtNascimento.getText());
                funcionario.setRg(TxtRG.getText());
                funcionario.setCpf(TxtCPF.getText());
                funcionario.setTelefone1(TxtTelefone1.getText());
                funcionario.setTelefone2(TxtTelefone2.getText());
                funcionario.setEmail(TxtEmail.getText());
                funcionario.setRua(TxtEndereco.getText());
                funcionario.setNumero(TxtNumero.getText());
                funcionario.setCep(TxtCep.getText());
                funcionario.setCidade(TxtCidade.getText());
                funcionario.setBairro(TxtBairro.getText());
                funcionario.setLogin(TxtLogin.getText());
                funcionario.setSenha(TxtSenha.getText());
                funcionario.setNivel(auxnivel);
                
                try {
                    funcionariodao.Alterar(funcionario);
                    JOptionPane.showMessageDialog(null, "Alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE); 
                } catch (SQLException ex) {
                    Logger.getLogger(FuncionarioView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
                atualizaTabelaFuncionario();
                DesativarCampos();
                LimparCampos();
                PrepararCancelarSalvar();
            
        }
        else{
            JOptionPane.showMessageDialog(null, "Senhas não correspondentes!", "Sucesso", JOptionPane.INFORMATION_MESSAGE); 
        }
        
        
        
    }//GEN-LAST:event_BtnSalvarActionPerformed

    private void BtnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_BtnSairActionPerformed

    private void BtnSair1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSair1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_BtnSair1ActionPerformed

    private void TblFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblFuncionarioMouseClicked
        LimparCampos();
        funcionario = new Funcionario();
         
        TxtId.setText(TblFuncionario.getValueAt(TblFuncionario.getSelectedRow(),0).toString());
        String identificador = TxtId.getText();
        int id = Integer.parseInt(identificador);
        funcionario.setId(id);
        
        try {
            funcionario = funcionariodao.Buscar(id);
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TblFuncionario.getTableHeader().setReorderingAllowed(false);
        TxtNomeCompleto.setText(funcionario.getNome());
        TxtNascimento.setText(funcionario.getNascimento());
        TxtRG.setText(funcionario.getRg());
        TxtCPF.setText(funcionario.getCpf());
        TxtTelefone1.setText(funcionario.getTelefone1());
        TxtTelefone2.setText(funcionario.getTelefone2());
        TxtEmail.setText(funcionario.getEmail());
        TxtCep.setText(funcionario.getCep());
        TxtCidade.setText(funcionario.getCidade());
        TxtBairro.setText(funcionario.getBairro());
        TxtEndereco.setText(funcionario.getRua());
        TxtNumero.setText(funcionario.getNumero());
        TxtLogin.setText(funcionario.getLogin());
        TxtSenha.setText(funcionario.getSenha());
        if (funcionario.getNivel() == true) {
            RadioAdm.setSelected(true);
        }else{
            RadioFunci.setSelected(true);
        }
        
        BtnAlterar.setEnabled(true);
        BtnExcluir.setEnabled(true);
    }//GEN-LAST:event_TblFuncionarioMouseClicked

    private void TxtPesquisaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_TxtPesquisaCaretUpdate
        listafuncionario = null;
        if(TxtPesquisa.getText().equals("")){
            atualizaTabelaFuncionario();
        }
        else{
            try {
                listafuncionario = funcionariodao.BuscarNome(TxtPesquisa.getText());
                if(listafuncionario == null){
                    JOptionPane.showMessageDialog(null, "Nenhum Cliente encontrado!","", JOptionPane.WARNING_MESSAGE);
                    atualizaTabelaFuncionario();
                }else{
                    atualizaTabelaFuncionarioBusca();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClienteView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_TxtPesquisaCaretUpdate

    private void BtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlterarActionPerformed
        AtivarCampos();
        PrepararNovo();
        PainelFuncionario.setSelectedIndex(0);
    }//GEN-LAST:event_BtnAlterarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAlterar;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnExcluir;
    private javax.swing.JButton BtnNovo;
    private javax.swing.JButton BtnSair;
    private javax.swing.JButton BtnSair1;
    private javax.swing.JButton BtnSalvar;
    private javax.swing.ButtonGroup GgrupoRadio;
    private javax.swing.JTabbedPane PainelFuncionario;
    private javax.swing.JRadioButton RadioAdm;
    private javax.swing.JRadioButton RadioFunci;
    private javax.swing.JTable TblFuncionario;
    private javax.swing.JTextField TxtBairro;
    private javax.swing.JFormattedTextField TxtCPF;
    private javax.swing.JFormattedTextField TxtCep;
    private javax.swing.JTextField TxtCidade;
    private javax.swing.JTextField TxtEmail;
    private javax.swing.JTextField TxtEndereco;
    private javax.swing.JTextField TxtId;
    private javax.swing.JTextField TxtLogin;
    private javax.swing.JFormattedTextField TxtNascimento;
    private javax.swing.JTextField TxtNomeCompleto;
    private javax.swing.JTextField TxtNumero;
    private javax.swing.JTextField TxtPesquisa;
    private javax.swing.JTextField TxtRG;
    private javax.swing.JPasswordField TxtSenha;
    private javax.swing.JPasswordField TxtSenhaRepetida;
    private javax.swing.JFormattedTextField TxtTelefone1;
    private javax.swing.JFormattedTextField TxtTelefone2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
