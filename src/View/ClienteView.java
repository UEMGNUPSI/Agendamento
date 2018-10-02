/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import DAO.ClienteDAO;
import Model.Cliente;
import com.sun.java.swing.plaf.windows.WindowsButtonUI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.plaf.metal.MetalTabbedPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author Dc
 */
public class ClienteView extends javax.swing.JInternalFrame {

    Cliente cliente = new Cliente();
    ClienteDAO clientedao = new ClienteDAO();
    List<Cliente> listacliente = new ArrayList<>();
    
    public ClienteView(){
        initComponents();
        PainelCliente.setUI(new  MetalTabbedPaneUI());
        BtnSair1.setUI(new WindowsButtonUI());
        BtnAlterar.setUI(new WindowsButtonUI());
        BtnCancelar.setUI(new WindowsButtonUI());
        BtnExcluir.setUI(new WindowsButtonUI());
        BtnNovo.setUI(new WindowsButtonUI());
        BtnSair.setUI(new WindowsButtonUI());
        BtnSalvar.setUI(new WindowsButtonUI());
        TblCliente.setUI(new BasicTableUI());
        
        TxtId.setVisible(false);
        BtnAlterar.setEnabled(false);
        BtnExcluir.setEnabled(false);
        PainelCliente.setSelectedIndex(1);
        atualizaTabelaCliente();
        DesativarCampos();
      
        
    }
    
    public void atualizaTabelaCliente(){
        cliente = new Cliente();
        try {
            listacliente = clientedao.listaTodos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        String dados[][] = new String[listacliente.size()][6];
            int i = 0;
            for (Cliente cliente : listacliente) {
                dados[i][0] = String.valueOf(cliente.getId());
                dados[i][1] = cliente.getNome();
                dados[i][2] = cliente.getRg();
                dados[i][3] = cliente.getCpf();
                dados[i][4] = cliente.getNascimento();
                dados[i][5] = cliente.getTelefone1();
           
                i++;
            }
            String tituloColuna[] = {"Id", "Nome", "RG", "CPF","Nascimento","Fone Principal"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            TblCliente.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            TblCliente.getColumnModel().getColumn(0).setMaxWidth(0);
            TblCliente.getColumnModel().getColumn(0).setMinWidth(0);
            TblCliente.getColumnModel().getColumn(0).setPreferredWidth(0);
            
            TblCliente.getColumnModel().getColumn(1).setPreferredWidth(400);
            TblCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
            TblCliente.getColumnModel().getColumn(3).setPreferredWidth(100);
            TblCliente.getColumnModel().getColumn(4).setPreferredWidth(100);
            TblCliente.getColumnModel().getColumn(5).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            TblCliente.getColumnModel().getColumn(4).setCellRenderer(centralizado);
            TblCliente.setRowHeight(35);
            TblCliente.updateUI();
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
        TxtAdicional.setEnabled(false);
        
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
        TxtAdicional.setEnabled(true);
        
    }
    
    public void PrepararNovo(){
        BtnSair.setEnabled(false);
        BtnSair1.setEnabled(false);
        BtnAlterar.setEnabled(false);
        BtnExcluir.setEnabled(false);
        BtnNovo.setEnabled(false);
        BtnCancelar.setEnabled(true);
        BtnSalvar.setEnabled(true);
        TblCliente.setEnabled(false);
        TblCliente.clearSelection();
    }
    public void PrepararCancelarSalvar(){
        BtnNovo.setEnabled(true);
        BtnAlterar.setEnabled(false);
        BtnCancelar.setEnabled(false);
        BtnSalvar.setEnabled(false);
        TblCliente.setEnabled(true);
        BtnExcluir.setEnabled(true);
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
        TxtAdicional.setText("");
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PainelCliente = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
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
        TxtCPF = new javax.swing.JFormattedTextField();
        TxtNascimento = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtAdicional = new javax.swing.JTextPane();
        jLabel8 = new javax.swing.JLabel();
        TxtBairro = new javax.swing.JTextField();
        TxtNumero = new javax.swing.JTextField();
        TxtCidade = new javax.swing.JTextField();
        TxtEndereco = new javax.swing.JTextField();
        TxtCep = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        BtnNovo = new javax.swing.JButton();
        BtnSalvar = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();
        BtnSair = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblCliente = new javax.swing.JTable();
        BtnAlterar = new javax.swing.JButton();
        BtnExcluir = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        TxtPesquisa = new javax.swing.JTextField();
        BtnSair1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        PainelCliente.setBackground(new java.awt.Color(255, 255, 255));
        PainelCliente.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        PainelCliente.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        PainelCliente.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

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
            TxtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtCPF.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        try {
            TxtNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtNascimento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(TxtRG, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(jLabel5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                                .addComponent(TxtTelefone1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(TxtEmail)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7)
                    .addComponent(TxtNomeCompleto))
                .addGap(60, 60, 60)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TxtId, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(TxtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtTelefone2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TxtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtNascimento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNomeCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtTelefone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtTelefone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados Complementais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 14))); // NOI18N

        jScrollPane1.setViewportView(TxtAdicional);

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel8.setText("Adicional: ");

        TxtBairro.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        TxtNumero.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        TxtCidade.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        TxtEndereco.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

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

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel12.setText("CEP:");

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel13.setText("Endereço:");

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel14.setText("Número:");

        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel15.setText("Bairro:");

        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel16.setText("Cidade: ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(TxtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(TxtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(TxtEndereco))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(195, 195, 195))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(335, 335, 335)
                                .addComponent(jLabel14))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 861, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel12)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        BtnNovo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnNovo.setText("NOVO");
        BtnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNovoActionPerformed(evt);
            }
        });

        BtnSalvar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnSalvar.setText("SALVAR");
        BtnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarActionPerformed(evt);
            }
        });

        BtnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnCancelar.setText("CANCELAR");
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        BtnSair.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnSair.setText("SAIR");
        BtnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(BtnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 9, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCancelar)
                    .addComponent(BtnSalvar)
                    .addComponent(BtnNovo)
                    .addComponent(BtnSair))
                .addGap(5, 5, 5))
        );

        PainelCliente.addTab("    Cadastrar    ", jPanel2);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        TblCliente.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        TblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Douglas", null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TblCliente.setRowHeight(22);
        TblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TblCliente);

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

        jPanel6.setBackground(new java.awt.Color(250, 250, 250));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 14))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel9.setText("Nome:");

        TxtPesquisa.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        TxtPesquisa.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                TxtPesquisaCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel9)
                .addGap(59, 59, 59)
                .addComponent(TxtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(233, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BtnSair1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 897, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnSair1)
                    .addComponent(BtnAlterar)
                    .addComponent(BtnExcluir))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        PainelCliente.addTab("   Consultar   ", jPanel1);

        jLabel10.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel10.setText("CLIENTE");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PainelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 942, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(PainelCliente)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlterarActionPerformed
        AtivarCampos();
        PrepararNovo();
        PainelCliente.setSelectedIndex(0);
    }//GEN-LAST:event_BtnAlterarActionPerformed

    private void BtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExcluirActionPerformed
       if(TxtId.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "Selecione um dado para exlui-lo!","erro", JOptionPane.WARNING_MESSAGE);
       }
       else{
           cliente.setId(Integer.parseInt(TxtId.getText()));
           int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir: "+ TxtNomeCompleto.getText());
           if(confirma == 0){
               
               try {
                   clientedao.Excluir(cliente);
                   JOptionPane.showMessageDialog(null, "Excluido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                   LimparCampos();
               } catch (SQLException ex) {
                   Logger.getLogger(ClienteView.class.getName()).log(Level.SEVERE, null, ex);
               }
                 atualizaTabelaCliente();
                BtnExcluir.setEnabled(false);
                BtnAlterar.setEnabled(false);
           }
       }
    }//GEN-LAST:event_BtnExcluirActionPerformed

    private void TxtCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtCepActionPerformed

    private void BtnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNovoActionPerformed
       AtivarCampos();
       PrepararNovo();
       LimparCampos();
    }//GEN-LAST:event_BtnNovoActionPerformed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
       LimparCampos();
       DesativarCampos();
       PrepararCancelarSalvar();
    }//GEN-LAST:event_BtnCancelarActionPerformed

    private void BtnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_BtnSairActionPerformed

    private void BtnSair1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSair1ActionPerformed
      this.dispose();
    }//GEN-LAST:event_BtnSair1ActionPerformed

    private void BtnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarActionPerformed
        cliente = new Cliente();
        clientedao = new ClienteDAO();
        if(TxtNomeCompleto.getText().isEmpty() || TxtRG.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios !","erro", JOptionPane.WARNING_MESSAGE);
        }
        else if(TxtId.getText().isEmpty()){
            //Salva tudo digitado no campo de texto para o objeto e salva no banco de dados
            
            cliente.setNome(TxtNomeCompleto.getText());
            cliente.setNascimento(TxtNascimento.getText());
            cliente.setRg(TxtRG.getText());
            cliente.setCpf(TxtCPF.getText());
            cliente.setTelefone1(TxtTelefone1.getText());
            cliente.setTelefone2(TxtTelefone2.getText());
            cliente.setEmail(TxtEmail.getText());
            cliente.setRua(TxtEndereco.getText());
            cliente.setNumero(TxtNumero.getText());
            cliente.setCep(TxtCep.getText());
            cliente.setCidade(TxtCidade.getText());
            cliente.setBairro(TxtBairro.getText());
            cliente.setDescrição(TxtAdicional.getText());
            
            try {      
                clientedao.Salvar(cliente);
                JOptionPane.showMessageDialog(null, "Gravado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE); 
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
                Logger.getLogger(ClienteView.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            atualizaTabelaCliente();
            DesativarCampos();
            LimparCampos();
            PrepararCancelarSalvar();
        }
        else{
            //Salva tudo que foi alterado nos campos de texto para o objeto e salva no banco de dados
            
            cliente.setId(Integer.valueOf(TxtId.getText()));
            cliente.setNome(TxtNomeCompleto.getText());
            cliente.setNascimento(TxtNascimento.getText());
            cliente.setRg(TxtRG.getText());
            cliente.setCpf(TxtCPF.getText());
            cliente.setTelefone1(TxtTelefone1.getText());
            cliente.setTelefone2(TxtTelefone2.getText());
            cliente.setEmail(TxtEmail.getText());
            cliente.setRua(TxtEndereco.getText());
            cliente.setNumero(TxtNumero.getText());
            cliente.setCep(TxtCep.getText());
            cliente.setCidade(TxtCidade.getText());
            cliente.setBairro(TxtBairro.getText());
            cliente.setDescrição(TxtAdicional.getText());
            
            try {
                clientedao.Alterar(cliente);
                JOptionPane.showMessageDialog(null, "Alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE); 
            } catch (SQLException ex) {
                Logger.getLogger(ClienteView.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            atualizaTabelaCliente();
            DesativarCampos();
            LimparCampos();
            PrepararCancelarSalvar();
        }
    }//GEN-LAST:event_BtnSalvarActionPerformed

    private void TblClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblClienteMouseClicked
        LimparCampos();
        cliente = new Cliente();
        
        TxtId.setText(TblCliente.getValueAt(TblCliente.getSelectedRow(),0).toString());
        String identificador = TxtId.getText();
        int id = Integer.parseInt(identificador);
        cliente.setId(id);
        
        try {
            cliente = clientedao.Buscar(id);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TblCliente.getTableHeader().setReorderingAllowed(false);
        TxtNomeCompleto.setText(cliente.getNome());
        TxtNascimento.setText(cliente.getNascimento());
        TxtRG.setText(cliente.getRg());
        TxtCPF.setText(cliente.getCpf());
        TxtTelefone1.setText(cliente.getTelefone1());
        TxtTelefone2.setText(cliente.getTelefone2());
        TxtEmail.setText(cliente.getEmail());
        TxtCep.setText(cliente.getCep());
        TxtCidade.setText(cliente.getCidade());
        TxtBairro.setText(cliente.getBairro());
        TxtEndereco.setText(cliente.getRua());
        TxtNumero.setText(cliente.getNumero());
        TxtAdicional.setText(cliente.getDescrição());
        
        BtnAlterar.setEnabled(true);
        BtnExcluir.setEnabled(true);
    }//GEN-LAST:event_TblClienteMouseClicked

    private void TxtPesquisaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_TxtPesquisaCaretUpdate
       
    }//GEN-LAST:event_TxtPesquisaCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAlterar;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnExcluir;
    private javax.swing.JButton BtnNovo;
    private javax.swing.JButton BtnSair;
    private javax.swing.JButton BtnSair1;
    private javax.swing.JButton BtnSalvar;
    private javax.swing.JTabbedPane PainelCliente;
    private javax.swing.JTable TblCliente;
    private javax.swing.JTextPane TxtAdicional;
    private javax.swing.JTextField TxtBairro;
    private javax.swing.JFormattedTextField TxtCPF;
    private javax.swing.JFormattedTextField TxtCep;
    private javax.swing.JTextField TxtCidade;
    private javax.swing.JTextField TxtEmail;
    private javax.swing.JTextField TxtEndereco;
    private javax.swing.JTextField TxtId;
    private javax.swing.JFormattedTextField TxtNascimento;
    private javax.swing.JTextField TxtNomeCompleto;
    private javax.swing.JTextField TxtNumero;
    private javax.swing.JTextField TxtPesquisa;
    private javax.swing.JTextField TxtRG;
    private javax.swing.JFormattedTextField TxtTelefone1;
    private javax.swing.JFormattedTextField TxtTelefone2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
