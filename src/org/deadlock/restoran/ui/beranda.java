package org.deadlock.restoran.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;
import org.deadlock.restoran.connector;

/**
 *
 * @author defuj
 */
public class beranda extends javax.swing.JFrame {

    CardLayout konten,kontenHome;
    String username,id_transaksiSelected,id_detailSelected,query,transaksiID_created,productFoodIDSelected,productDrinksIDSelected;
    int totalPayment,cashMoney,changeMoney;
    DefaultTableModel modelTblPayment,modelTblPaymentDetail,modelTblProducts,modelTblShopping,modelTblListProductsFood,modelTblListProductsDrinks;
    
    public beranda() {
        initComponents();
        setLocationRelativeTo(null);
        
        JFrame frame = new JFrame();
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        
        kontenHome = (CardLayout)(Home.getLayout());
        kontenHome.show(Home, "login");
        
        konten = (CardLayout)(panelContent.getLayout());
        konten.show(panelContent, "card1");
        Clicked1.setVisible(true);
        Clicked2.setVisible(false);
        Clicked3.setVisible(false);
        Clicked4.setVisible(false);
        Clicked5.setVisible(false);
        
        CustomizeTablePayment();
        CustomizeTableDetailPayment();
        CostumizeTableShoppingCart();
        CostumizeTableProducts();
        CustomizeTableListProducts();
        CustomizeTableListProductsDrinks();
        Payments();
        Products();
        listProducts();
        listProductsDrinks();
        
        //Customize Button
        btnPay.setBackground(Color.decode("#B20837"));
        btnPay.setOpaque(true);
        btnPay.setEnabled(false);
        
        
        
        tblPemesanan.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            try{
                String id = tblPemesanan.getValueAt(tblPemesanan.getSelectedRow(), 1).toString();
                id_transaksiSelected = id.replaceAll("\\s","");
                DetailPayment(id_transaksiSelected);
                txtCash.setText("");
                txtChange.setText("");
            }catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            btnRefreshDetail.setEnabled(true);
            btnDeleteTransaction.setEnabled(true);
            btnDeleteDetail.setEnabled(false);
            btnAddDetail.setEnabled(true);
            btnEditTransaction.setEnabled(true);
            System.out.println(id_transaksiSelected);
            
        });
        
        tblListProducs.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            try{
                String id = tblListProducs.getValueAt(tblListProducs.getSelectedRow(), 4).toString();
                productFoodIDSelected = id.replaceAll("\\s","");
                txtProductNameFood.setText(tblListProducs.getValueAt(tblListProducs.getSelectedRow(), 1).toString());
                txtProductDiscountFood.setText(tblListProducs.getValueAt(tblListProducs.getSelectedRow(), 2).toString().replaceAll("%", ""));
                String hr = tblListProducs.getValueAt(tblListProducs.getSelectedRow(), 3).toString().replaceAll("Rp ", "");
                txtProductPriceFood.setText(hr.replace(",", ""));
            }catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            btnModifyProduct.setEnabled(true);
            btnAddProducts.setEnabled(true);
        });
        
        tblListProducsDrinks.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            try{
                String id = tblListProducsDrinks.getValueAt(tblListProducsDrinks.getSelectedRow(), 4).toString();
                productDrinksIDSelected = id.replaceAll("\\s","");
                txtProductNameDrinks.setText(tblListProducsDrinks.getValueAt(tblListProducsDrinks.getSelectedRow(), 1).toString());
                txtProductDiscountDrinks.setText(tblListProducsDrinks.getValueAt(tblListProducsDrinks.getSelectedRow(), 2).toString().replaceAll("%", ""));
                String hr = tblListProducsDrinks.getValueAt(tblListProducsDrinks.getSelectedRow(), 3).toString().replaceAll("Rp ", "");
                txtProductPriceDrinks.setText(hr.replace(",", ""));
            }catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            btnModifyProductDrinks.setEnabled(true);
            btnAddProductsDrinks.setEnabled(true);
        });
        
        tblDetailPemesanan.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            
            try{
                String id_transaksi = tblDetailPemesanan.getValueAt(tblDetailPemesanan.getSelectedRow(), 0).toString();
                String id_detail = tblDetailPemesanan.getValueAt(tblDetailPemesanan.getSelectedRow(), 1).toString();
                id_detailSelected = tblDetailPemesanan.getValueAt(tblDetailPemesanan.getSelectedRow(), 1).toString();
            }catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            btnDeleteDetail.setEnabled(true);
            btnAddDetail.setEnabled(true);
            
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Home = new javax.swing.JPanel();
        PanelLogin = new javax.swing.JPanel();
        btnCancelLogin = new javax.swing.JButton();
        btnShowAccountDefault = new javax.swing.JButton();
        loginForm = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPin = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        PanelHome = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        menuFood = new javax.swing.JLabel();
        menuAccount = new javax.swing.JLabel();
        menuTransaction = new javax.swing.JLabel();
        menuPayment = new javax.swing.JLabel();
        menuDrinks = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Clicked1 = new javax.swing.JPanel();
        Clicked2 = new javax.swing.JPanel();
        Clicked3 = new javax.swing.JPanel();
        Clicked4 = new javax.swing.JPanel();
        Clicked5 = new javax.swing.JPanel();
        menuLogout = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        btnSettings = new javax.swing.JButton();
        btnHelp = new javax.swing.JButton();
        panelContent = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPanePemesanan = new javax.swing.JScrollPane();
        tblPemesanan = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jScrollPaneDetailPemesanan = new javax.swing.JScrollPane();
        tblDetailPemesanan = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnPay = new javax.swing.JLabel();
        txtTotalPayment = new javax.swing.JTextField();
        btnRefreshDetail = new javax.swing.JButton();
        btnRefreshTransaction = new javax.swing.JButton();
        btnAddDetail = new javax.swing.JButton();
        btnDeleteDetail = new javax.swing.JButton();
        btnDeleteTransaction = new javax.swing.JButton();
        btnEditTransaction = new javax.swing.JButton();
        txtCash = new javax.swing.JTextField();
        txtChange = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPaneTableShoppingCart = new javax.swing.JScrollPane();
        tblShoppingCart = new javax.swing.JTable();
        btnConformOrder = new javax.swing.JButton();
        cmbCategories = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jScrollPaneProducts = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        txtNamaOrder = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPaneListProducts = new javax.swing.JScrollPane();
        tblListProducs = new javax.swing.JTable();
        txtProductName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtProductDiscountFood = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtProductPriceFood = new javax.swing.JTextField();
        txtProductNameFood = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnAddProducts = new javax.swing.JButton();
        btnModifyProduct = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPaneListProductsDrinks = new javax.swing.JScrollPane();
        tblListProducsDrinks = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        txtProductPriceDrinks = new javax.swing.JTextField();
        txtProductNameDrinks = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtProductDiscountDrinks = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtProductName3 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        btnModifyProductDrinks = new javax.swing.JButton();
        btnAddProductsDrinks = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtID = new javax.swing.JLabel();
        txtDateLog = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Beranda");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);

        Home.setLayout(new java.awt.CardLayout());

        PanelLogin.setBackground(new java.awt.Color(41, 39, 40));
        PanelLogin.setLayout(null);

        btnCancelLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/deadlock/restoran/icons/power_settings_new_white_18dp.png"))); // NOI18N
        btnCancelLogin.setContentAreaFilled(false);
        btnCancelLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelLoginActionPerformed(evt);
            }
        });
        PanelLogin.add(btnCancelLogin);
        btnCancelLogin.setBounds(990, 580, 40, 40);

        btnShowAccountDefault.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/deadlock/restoran/icons/info_white_18dp.png"))); // NOI18N
        btnShowAccountDefault.setContentAreaFilled(false);
        btnShowAccountDefault.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnShowAccountDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowAccountDefaultActionPerformed(evt);
            }
        });
        PanelLogin.add(btnShowAccountDefault);
        btnShowAccountDefault.setBounds(943, 580, 40, 40);

        loginForm.setOpaque(false);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/deadlock/restoran/icons/resta.png"))); // NOI18N
        jButton2.setContentAreaFilled(false);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Login into your account");

        txtUsername.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(51, 51, 51));
        txtUsername.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsername.setText("Username");
        txtUsername.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15));
        txtUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsernameFocusLost(evt);
            }
        });
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsernameKeyTyped(evt);
            }
        });

        txtPin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPin.setForeground(new java.awt.Color(51, 51, 51));
        txtPin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPin.setText("******");
        txtPin.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15));
        txtPin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPinFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPinFocusLost(evt);
            }
        });
        txtPin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPinKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPinKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPinKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout loginFormLayout = new javax.swing.GroupLayout(loginForm);
        loginForm.setLayout(loginFormLayout);
        loginFormLayout.setHorizontalGroup(
            loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginFormLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jButton2))
            .addGroup(loginFormLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(loginFormLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(loginFormLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(txtPin, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        loginFormLayout.setVerticalGroup(
            loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginFormLayout.createSequentialGroup()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(txtPin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        PanelLogin.add(loginForm);
        loginForm.setBounds(380, 200, 250, 220);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/deadlock/restoran/icons/bg_login.png"))); // NOI18N
        PanelLogin.add(jLabel9);
        jLabel9.setBounds(0, 0, 1040, 631);

        Home.add(PanelLogin, "login");

        PanelHome.setBackground(new java.awt.Color(245, 245, 245));
        PanelHome.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PanelHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(146, 10, 48));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        PanelHome.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 820, 10));

        jPanel1.setBackground(new java.awt.Color(41, 39, 40));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuFood.setBackground(new java.awt.Color(41, 39, 40));
        menuFood.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 16)); // NOI18N
        menuFood.setForeground(new java.awt.Color(255, 255, 255));
        menuFood.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/deadlock/restoran/icons/fastfood_white_18dp.png"))); // NOI18N
        menuFood.setText("     Foods");
        menuFood.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        menuFood.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuFood.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuFoodMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuFoodMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuFoodMouseExited(evt);
            }
        });
        jPanel1.add(menuFood, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 210, 52));

        menuAccount.setBackground(new java.awt.Color(41, 39, 40));
        menuAccount.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 16)); // NOI18N
        menuAccount.setForeground(new java.awt.Color(255, 255, 255));
        menuAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/deadlock/restoran/icons/assignment_ind_white_18dp.png"))); // NOI18N
        menuAccount.setText("     Account");
        menuAccount.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        menuAccount.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuAccountMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuAccountMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuAccountMouseExited(evt);
            }
        });
        jPanel1.add(menuAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 210, 52));

        menuTransaction.setBackground(new java.awt.Color(41, 39, 40));
        menuTransaction.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 16)); // NOI18N
        menuTransaction.setForeground(new java.awt.Color(255, 255, 255));
        menuTransaction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/deadlock/restoran/icons/add_shopping_cart_white_18dp.png"))); // NOI18N
        menuTransaction.setText("     Shopping");
        menuTransaction.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        menuTransaction.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuTransaction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuTransactionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuTransactionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuTransactionMouseExited(evt);
            }
        });
        jPanel1.add(menuTransaction, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 210, 52));

        menuPayment.setBackground(new java.awt.Color(41, 39, 40));
        menuPayment.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 16)); // NOI18N
        menuPayment.setForeground(new java.awt.Color(255, 255, 255));
        menuPayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/deadlock/restoran/icons/assignment_turned_in_white_18dp.png"))); // NOI18N
        menuPayment.setText("     Bill - Payment");
        menuPayment.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        menuPayment.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuPayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPaymentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuPaymentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuPaymentMouseExited(evt);
            }
        });
        jPanel1.add(menuPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 210, 52));

        menuDrinks.setBackground(new java.awt.Color(41, 39, 40));
        menuDrinks.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 16)); // NOI18N
        menuDrinks.setForeground(new java.awt.Color(255, 255, 255));
        menuDrinks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/deadlock/restoran/icons/local_drink_white_18dp.png"))); // NOI18N
        menuDrinks.setText("     Drinks");
        menuDrinks.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        menuDrinks.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuDrinks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuDrinksMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuDrinksMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuDrinksMouseExited(evt);
            }
        });
        jPanel1.add(menuDrinks, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 210, 52));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Version 1.0");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 600, -1, -1));

        Clicked1.setBackground(new java.awt.Color(178, 8, 55));

        javax.swing.GroupLayout Clicked1Layout = new javax.swing.GroupLayout(Clicked1);
        Clicked1.setLayout(Clicked1Layout);
        Clicked1Layout.setHorizontalGroup(
            Clicked1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        Clicked1Layout.setVerticalGroup(
            Clicked1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jPanel1.add(Clicked1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 10, 52));

        Clicked2.setBackground(new java.awt.Color(178, 8, 55));

        javax.swing.GroupLayout Clicked2Layout = new javax.swing.GroupLayout(Clicked2);
        Clicked2.setLayout(Clicked2Layout);
        Clicked2Layout.setHorizontalGroup(
            Clicked2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        Clicked2Layout.setVerticalGroup(
            Clicked2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jPanel1.add(Clicked2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 10, 52));

        Clicked3.setBackground(new java.awt.Color(178, 8, 55));

        javax.swing.GroupLayout Clicked3Layout = new javax.swing.GroupLayout(Clicked3);
        Clicked3.setLayout(Clicked3Layout);
        Clicked3Layout.setHorizontalGroup(
            Clicked3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        Clicked3Layout.setVerticalGroup(
            Clicked3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jPanel1.add(Clicked3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 10, 52));

        Clicked4.setBackground(new java.awt.Color(178, 8, 55));

        javax.swing.GroupLayout Clicked4Layout = new javax.swing.GroupLayout(Clicked4);
        Clicked4.setLayout(Clicked4Layout);
        Clicked4Layout.setHorizontalGroup(
            Clicked4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        Clicked4Layout.setVerticalGroup(
            Clicked4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jPanel1.add(Clicked4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 10, 52));

        Clicked5.setBackground(new java.awt.Color(178, 8, 55));

        javax.swing.GroupLayout Clicked5Layout = new javax.swing.GroupLayout(Clicked5);
        Clicked5.setLayout(Clicked5Layout);
        Clicked5Layout.setHorizontalGroup(
            Clicked5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        Clicked5Layout.setVerticalGroup(
            Clicked5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jPanel1.add(Clicked5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 10, 52));

        menuLogout.setBackground(new java.awt.Color(41, 39, 40));
        menuLogout.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 16)); // NOI18N
        menuLogout.setForeground(new java.awt.Color(255, 255, 255));
        menuLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/deadlock/restoran/icons/outline_power_settings_new_white_18dp.png"))); // NOI18N
        menuLogout.setText("     Log Out");
        menuLogout.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        menuLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuLogoutMouseExited(evt);
            }
        });
        jPanel1.add(menuLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 210, 52));

        jPanel13.setBackground(new java.awt.Color(146, 10, 48));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/deadlock/restoran/icons/resta2.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setContentAreaFilled(false);
        jPanel13.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 70));

        jLabel1.setBackground(new java.awt.Color(178, 8, 55));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DeFoods");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel13.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 110, -1));

        jLabel2.setBackground(new java.awt.Color(178, 8, 55));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Modern Restourant");
        jPanel13.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 120, -1));

        jPanel1.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 70));

        PanelHome.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 630));

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/deadlock/restoran/icons/power_24dp.png"))); // NOI18N
        btnExit.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnExit.setContentAreaFilled(false);
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        PanelHome.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 20, 35, 35));

        btnSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/deadlock/restoran/icons/settings-24.png"))); // NOI18N
        btnSettings.setContentAreaFilled(false);
        btnSettings.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelHome.add(btnSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(942, 20, 35, 35));

        btnHelp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnHelp.setForeground(new java.awt.Color(255, 255, 255));
        btnHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/deadlock/restoran/icons/help_outline_black_24dp.png"))); // NOI18N
        btnHelp.setContentAreaFilled(false);
        btnHelp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelHome.add(btnHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 20, 40, 35));

        panelContent.setBackground(new java.awt.Color(242, 242, 242));
        panelContent.setLayout(new java.awt.CardLayout());

        jPanel6.setBackground(new java.awt.Color(245, 245, 245));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPanePemesanan.setBackground(new java.awt.Color(244, 248, 249));
        jScrollPanePemesanan.setBorder(null);
        jScrollPanePemesanan.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        tblPemesanan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));
        tblPemesanan.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        tblPemesanan.setForeground(new java.awt.Color(102, 102, 102));
        tblPemesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "  No", "No. Transaction", "Ordering Name", "Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPemesanan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblPemesanan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblPemesanan.setFocusable(false);
        tblPemesanan.setRowHeight(30);
        tblPemesanan.setRowMargin(0);
        tblPemesanan.setSelectionBackground(new java.awt.Color(255, 204, 102));
        tblPemesanan.setShowVerticalLines(false);
        tblPemesanan.getTableHeader().setResizingAllowed(false);
        tblPemesanan.getTableHeader().setReorderingAllowed(false);
        jScrollPanePemesanan.setViewportView(tblPemesanan);
        if (tblPemesanan.getColumnModel().getColumnCount() > 0) {
            tblPemesanan.getColumnModel().getColumn(0).setResizable(false);
            tblPemesanan.getColumnModel().getColumn(0).setPreferredWidth(6);
            tblPemesanan.getColumnModel().getColumn(1).setResizable(false);
            tblPemesanan.getColumnModel().getColumn(2).setResizable(false);
            tblPemesanan.getColumnModel().getColumn(3).setResizable(false);
            tblPemesanan.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        jPanel5.add(jScrollPanePemesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 410));

        jPanel6.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 310, 420));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPaneDetailPemesanan.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneDetailPemesanan.setBorder(null);
        jScrollPaneDetailPemesanan.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblDetailPemesanan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tblDetailPemesanan.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        tblDetailPemesanan.setForeground(new java.awt.Color(51, 51, 51));
        tblDetailPemesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id_transaksi", "id_detail", "   No", "Product", "QTY", "Price", "Discount", "Total Cost"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDetailPemesanan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblDetailPemesanan.setFocusable(false);
        tblDetailPemesanan.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblDetailPemesanan.setRowHeight(30);
        tblDetailPemesanan.setSelectionBackground(new java.awt.Color(255, 204, 102));
        tblDetailPemesanan.setShowVerticalLines(false);
        tblDetailPemesanan.getTableHeader().setReorderingAllowed(false);
        jScrollPaneDetailPemesanan.setViewportView(tblDetailPemesanan);
        if (tblDetailPemesanan.getColumnModel().getColumnCount() > 0) {
            tblDetailPemesanan.getColumnModel().getColumn(0).setMinWidth(0);
            tblDetailPemesanan.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblDetailPemesanan.getColumnModel().getColumn(0).setMaxWidth(0);
            tblDetailPemesanan.getColumnModel().getColumn(1).setMinWidth(0);
            tblDetailPemesanan.getColumnModel().getColumn(1).setPreferredWidth(0);
            tblDetailPemesanan.getColumnModel().getColumn(1).setMaxWidth(0);
            tblDetailPemesanan.getColumnModel().getColumn(2).setResizable(false);
            tblDetailPemesanan.getColumnModel().getColumn(2).setPreferredWidth(10);
            tblDetailPemesanan.getColumnModel().getColumn(3).setResizable(false);
            tblDetailPemesanan.getColumnModel().getColumn(4).setResizable(false);
            tblDetailPemesanan.getColumnModel().getColumn(4).setPreferredWidth(15);
            tblDetailPemesanan.getColumnModel().getColumn(5).setResizable(false);
            tblDetailPemesanan.getColumnModel().getColumn(5).setPreferredWidth(40);
            tblDetailPemesanan.getColumnModel().getColumn(6).setResizable(false);
            tblDetailPemesanan.getColumnModel().getColumn(6).setPreferredWidth(30);
            tblDetailPemesanan.getColumnModel().getColumn(7).setResizable(false);
            tblDetailPemesanan.getColumnModel().getColumn(7).setPreferredWidth(40);
        }

        jPanel12.add(jScrollPaneDetailPemesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 410));

        jPanel6.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 460, 420));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Transactions");
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, -1));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Detail");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 130, -1));

        btnPay.setBackground(new java.awt.Color(178, 8, 55));
        btnPay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPay.setForeground(new java.awt.Color(255, 255, 255));
        btnPay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/deadlock/restoran/icons/monetization_on_white_18dp.png"))); // NOI18N
        btnPay.setText("Pay Bill");
        btnPay.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 6, 1, 1));
        btnPay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPay.setEnabled(false);
        btnPay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPayMouseClicked(evt);
            }
        });
        jPanel6.add(btnPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 500, 90, 35));

        txtTotalPayment.setEditable(false);
        txtTotalPayment.setBackground(new java.awt.Color(217, 217, 217));
        txtTotalPayment.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        txtTotalPayment.setForeground(new java.awt.Color(51, 51, 51));
        txtTotalPayment.setText("Rp");
        txtTotalPayment.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jPanel6.add(txtTotalPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 310, 35));

        btnRefreshDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/deadlock/restoran/icons/refresh_black_18dp.png"))); // NOI18N
        btnRefreshDetail.setContentAreaFilled(false);
        btnRefreshDetail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefreshDetail.setEnabled(false);
        btnRefreshDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshDetailActionPerformed(evt);
            }
        });
        jPanel6.add(btnRefreshDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 30, 30));

        btnRefreshTransaction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/deadlock/restoran/icons/refresh_black_18dp.png"))); // NOI18N
        btnRefreshTransaction.setContentAreaFilled(false);
        btnRefreshTransaction.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefreshTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshTransactionActionPerformed(evt);
            }
        });
        jPanel6.add(btnRefreshTransaction, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 30, 30));

        btnAddDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/deadlock/restoran/icons/playlist_add_black_18dp.png"))); // NOI18N
        btnAddDetail.setContentAreaFilled(false);
        btnAddDetail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddDetail.setEnabled(false);
        jPanel6.add(btnAddDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 30, 30));

        btnDeleteDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/deadlock/restoran/icons/delete_forever_black_18dp.png"))); // NOI18N
        btnDeleteDetail.setContentAreaFilled(false);
        btnDeleteDetail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteDetail.setEnabled(false);
        btnDeleteDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteDetailActionPerformed(evt);
            }
        });
        jPanel6.add(btnDeleteDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, 30, 30));

        btnDeleteTransaction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/deadlock/restoran/icons/delete_forever_black_18dp.png"))); // NOI18N
        btnDeleteTransaction.setContentAreaFilled(false);
        btnDeleteTransaction.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteTransaction.setEnabled(false);
        btnDeleteTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteTransactionActionPerformed(evt);
            }
        });
        jPanel6.add(btnDeleteTransaction, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 30, 30));

        btnEditTransaction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/deadlock/restoran/icons/edit_black_18dp.png"))); // NOI18N
        btnEditTransaction.setContentAreaFilled(false);
        btnEditTransaction.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditTransaction.setEnabled(false);
        btnEditTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditTransactionActionPerformed(evt);
            }
        });
        jPanel6.add(btnEditTransaction, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 30, 30));

        txtCash.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        txtCash.setForeground(new java.awt.Color(102, 102, 102));
        txtCash.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txtCash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCashKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCashKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCashKeyTyped(evt);
            }
        });
        jPanel6.add(txtCash, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 500, 180, 35));

        txtChange.setEditable(false);
        txtChange.setBackground(new java.awt.Color(217, 217, 217));
        txtChange.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        txtChange.setForeground(new java.awt.Color(102, 102, 102));
        txtChange.setText("Rp");
        txtChange.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jPanel6.add(txtChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 500, 170, 35));

        jLabel12.setText("Change Money");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 480, 90, -1));

        jLabel13.setText("Cash");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 480, -1, -1));

        jLabel14.setText("Total Payment");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, -1, -1));

        panelContent.add(jPanel6, "card1");

        jPanel7.setBackground(new java.awt.Color(245, 245, 245));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Shopping");
        jPanel7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField1.setText("Search");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15));
        jPanel7.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 340, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPaneTableShoppingCart.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPaneTableShoppingCart.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblShoppingCart.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tblShoppingCart.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        tblShoppingCart.setForeground(new java.awt.Color(102, 102, 102));
        tblShoppingCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "detail_id", "transaksi_id", "   No", "Products", "QTY"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblShoppingCart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblShoppingCart.setFocusable(false);
        tblShoppingCart.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblShoppingCart.setRowHeight(30);
        tblShoppingCart.setSelectionBackground(new java.awt.Color(255, 204, 102));
        tblShoppingCart.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblShoppingCart.setShowVerticalLines(false);
        tblShoppingCart.getTableHeader().setReorderingAllowed(false);
        jScrollPaneTableShoppingCart.setViewportView(tblShoppingCart);
        if (tblShoppingCart.getColumnModel().getColumnCount() > 0) {
            tblShoppingCart.getColumnModel().getColumn(0).setMinWidth(0);
            tblShoppingCart.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblShoppingCart.getColumnModel().getColumn(0).setMaxWidth(0);
            tblShoppingCart.getColumnModel().getColumn(1).setMinWidth(0);
            tblShoppingCart.getColumnModel().getColumn(1).setPreferredWidth(0);
            tblShoppingCart.getColumnModel().getColumn(1).setMaxWidth(0);
            tblShoppingCart.getColumnModel().getColumn(2).setResizable(false);
            tblShoppingCart.getColumnModel().getColumn(2).setPreferredWidth(10);
            tblShoppingCart.getColumnModel().getColumn(3).setResizable(false);
            tblShoppingCart.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblShoppingCart.getColumnModel().getColumn(4).setResizable(false);
            tblShoppingCart.getColumnModel().getColumn(4).setPreferredWidth(20);
        }

        jPanel2.add(jScrollPaneTableShoppingCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 440));

        jPanel7.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 310, 450));

        btnConformOrder.setText("Confirm Order");
        btnConformOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConformOrderActionPerformed(evt);
            }
        });
        jPanel7.add(btnConformOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 50, 110, 30));

        cmbCategories.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Foods", "Drinks" }));
        cmbCategories.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCategoriesItemStateChanged(evt);
            }
        });
        jPanel7.add(cmbCategories, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 110, 30));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPaneProducts.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPaneProducts.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblProducts.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tblProducts.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        tblProducts.setForeground(new java.awt.Color(102, 102, 102));
        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "  No", "Products", "Categories", "Discount", "Price", "produk_id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProducts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblProducts.setFocusable(false);
        tblProducts.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblProducts.setRowHeight(30);
        tblProducts.setSelectionBackground(new java.awt.Color(255, 204, 102));
        tblProducts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblProducts.setShowVerticalLines(false);
        tblProducts.getTableHeader().setReorderingAllowed(false);
        jScrollPaneProducts.setViewportView(tblProducts);
        if (tblProducts.getColumnModel().getColumnCount() > 0) {
            tblProducts.getColumnModel().getColumn(0).setResizable(false);
            tblProducts.getColumnModel().getColumn(0).setPreferredWidth(15);
            tblProducts.getColumnModel().getColumn(1).setResizable(false);
            tblProducts.getColumnModel().getColumn(2).setResizable(false);
            tblProducts.getColumnModel().getColumn(2).setPreferredWidth(25);
            tblProducts.getColumnModel().getColumn(3).setResizable(false);
            tblProducts.getColumnModel().getColumn(3).setPreferredWidth(15);
            tblProducts.getColumnModel().getColumn(4).setResizable(false);
            tblProducts.getColumnModel().getColumn(4).setPreferredWidth(30);
            tblProducts.getColumnModel().getColumn(5).setMinWidth(0);
            tblProducts.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblProducts.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        jPanel11.add(jScrollPaneProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -1, 460, 440));

        jPanel7.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 460, 430));

        txtNamaOrder.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNamaOrder.setText("A/N");
        txtNamaOrder.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15));
        jPanel7.add(txtNamaOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 190, 30));

        jLabel23.setText("Recomend : input this first!");
        jPanel7.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, -1, -1));

        jLabel24.setText("Note : Double click item in table to add to the shpping cart.");
        jPanel7.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        panelContent.add(jPanel7, "card2");

        jPanel8.setBackground(new java.awt.Color(245, 245, 245));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Update or Add Product");
        jPanel8.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPaneListProducts.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneListProducts.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblListProducs.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tblListProducs.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        tblListProducs.setForeground(new java.awt.Color(51, 51, 51));
        tblListProducs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "  No", "Producs", "Discount", "Price", "produduk_id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListProducs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblListProducs.setFocusable(false);
        tblListProducs.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblListProducs.setRowHeight(30);
        tblListProducs.setSelectionBackground(new java.awt.Color(255, 204, 102));
        tblListProducs.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblListProducs.setShowVerticalLines(false);
        tblListProducs.getTableHeader().setReorderingAllowed(false);
        jScrollPaneListProducts.setViewportView(tblListProducs);
        if (tblListProducs.getColumnModel().getColumnCount() > 0) {
            tblListProducs.getColumnModel().getColumn(0).setResizable(false);
            tblListProducs.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblListProducs.getColumnModel().getColumn(1).setResizable(false);
            tblListProducs.getColumnModel().getColumn(1).setPreferredWidth(130);
            tblListProducs.getColumnModel().getColumn(2).setResizable(false);
            tblListProducs.getColumnModel().getColumn(2).setPreferredWidth(15);
            tblListProducs.getColumnModel().getColumn(3).setResizable(false);
            tblListProducs.getColumnModel().getColumn(3).setPreferredWidth(15);
            tblListProducs.getColumnModel().getColumn(4).setMinWidth(0);
            tblListProducs.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblListProducs.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        jPanel4.add(jScrollPaneListProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 440));

        jPanel8.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 460, 450));

        txtProductName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtProductName.setText("Search");
        txtProductName.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15));
        jPanel8.add(txtProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 460, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Product Name");
        jPanel8.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Product Discount");
        jPanel8.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, -1, -1));

        txtProductDiscountFood.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtProductDiscountFood.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15));
        txtProductDiscountFood.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProductDiscountFoodKeyTyped(evt);
            }
        });
        jPanel8.add(txtProductDiscountFood, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, 320, 40));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Product Price");
        jPanel8.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, -1, -1));

        txtProductPriceFood.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtProductPriceFood.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15));
        txtProductPriceFood.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProductPriceFoodKeyTyped(evt);
            }
        });
        jPanel8.add(txtProductPriceFood, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, 320, 40));

        txtProductNameFood.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtProductNameFood.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15));
        jPanel8.add(txtProductNameFood, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 320, 40));

        jLabel18.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("List Food");
        jPanel8.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        btnAddProducts.setText("Add");
        btnAddProducts.setEnabled(false);
        btnAddProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductsActionPerformed(evt);
            }
        });
        jPanel8.add(btnAddProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 310, 100, 40));

        btnModifyProduct.setText("Modify");
        btnModifyProduct.setEnabled(false);
        btnModifyProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyProductActionPerformed(evt);
            }
        });
        jPanel8.add(btnModifyProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 310, 100, 40));

        panelContent.add(jPanel8, "card3");

        jPanel9.setBackground(new java.awt.Color(245, 245, 245));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("List Drinks");
        jPanel9.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPaneListProductsDrinks.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneListProductsDrinks.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblListProducsDrinks.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tblListProducsDrinks.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        tblListProducsDrinks.setForeground(new java.awt.Color(51, 51, 51));
        tblListProducsDrinks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "  No", "Producs", "Discount", "Price", "produduk_id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListProducsDrinks.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblListProducsDrinks.setFocusable(false);
        tblListProducsDrinks.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblListProducsDrinks.setRowHeight(30);
        tblListProducsDrinks.setSelectionBackground(new java.awt.Color(255, 204, 102));
        tblListProducsDrinks.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblListProducsDrinks.setShowVerticalLines(false);
        tblListProducsDrinks.getTableHeader().setReorderingAllowed(false);
        jScrollPaneListProductsDrinks.setViewportView(tblListProducsDrinks);
        if (tblListProducsDrinks.getColumnModel().getColumnCount() > 0) {
            tblListProducsDrinks.getColumnModel().getColumn(0).setResizable(false);
            tblListProducsDrinks.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblListProducsDrinks.getColumnModel().getColumn(1).setResizable(false);
            tblListProducsDrinks.getColumnModel().getColumn(1).setPreferredWidth(130);
            tblListProducsDrinks.getColumnModel().getColumn(2).setResizable(false);
            tblListProducsDrinks.getColumnModel().getColumn(2).setPreferredWidth(15);
            tblListProducsDrinks.getColumnModel().getColumn(3).setResizable(false);
            tblListProducsDrinks.getColumnModel().getColumn(3).setPreferredWidth(15);
            tblListProducsDrinks.getColumnModel().getColumn(4).setMinWidth(0);
            tblListProducsDrinks.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblListProducsDrinks.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        jPanel14.add(jScrollPaneListProductsDrinks, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 440));

        jPanel9.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 460, 450));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Product Name");
        jPanel9.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, -1));

        txtProductPriceDrinks.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtProductPriceDrinks.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15));
        txtProductPriceDrinks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProductPriceDrinksKeyTyped(evt);
            }
        });
        jPanel9.add(txtProductPriceDrinks, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, 320, 40));

        txtProductNameDrinks.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtProductNameDrinks.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15));
        jPanel9.add(txtProductNameDrinks, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 320, 40));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("Product Price");
        jPanel9.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, -1, -1));

        txtProductDiscountDrinks.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtProductDiscountDrinks.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15));
        txtProductDiscountDrinks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProductDiscountDrinksKeyTyped(evt);
            }
        });
        jPanel9.add(txtProductDiscountDrinks, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, 320, 40));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Product Discount");
        jPanel9.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, -1, -1));

        txtProductName3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtProductName3.setText("Search");
        txtProductName3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15));
        jPanel9.add(txtProductName3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 460, 40));

        jLabel22.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Update or Add Product");
        jPanel9.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, -1, -1));

        btnModifyProductDrinks.setText("Modify");
        btnModifyProductDrinks.setEnabled(false);
        btnModifyProductDrinks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyProductDrinksActionPerformed(evt);
            }
        });
        jPanel9.add(btnModifyProductDrinks, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 310, 100, 40));

        btnAddProductsDrinks.setText("Add");
        btnAddProductsDrinks.setEnabled(false);
        btnAddProductsDrinks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductsDrinksActionPerformed(evt);
            }
        });
        jPanel9.add(btnAddProductsDrinks, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 310, 100, 40));

        panelContent.add(jPanel9, "card4");

        jPanel10.setBackground(new java.awt.Color(245, 245, 245));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Accounts");
        jPanel10.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, -1, -1));

        panelContent.add(jPanel10, "card5");

        PanelHome.add(panelContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 820, 550));

        txtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtID.setForeground(new java.awt.Color(51, 51, 51));
        txtID.setText("Logged  :");
        PanelHome.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 290, -1));

        txtDateLog.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDateLog.setForeground(new java.awt.Color(51, 51, 51));
        txtDateLog.setText("Date      :");
        PanelHome.add(txtDateLog, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 310, -1));

        Home.add(PanelHome, "home");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        getToolkit().beep();
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure want to exit the app?","Warning",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void menuPaymentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPaymentMouseEntered
        menuPayment.setBackground(Color.decode("#333333"));
    }//GEN-LAST:event_menuPaymentMouseEntered

    private void menuPaymentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPaymentMouseExited
        menuPayment.setBackground(Color.decode("#292728"));
        menuPayment.setOpaque(true);
    }//GEN-LAST:event_menuPaymentMouseExited

    private void menuPaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPaymentMouseClicked
        konten.show(panelContent, "card1");
        menuPayment.setBackground(Color.decode("#494848"));
        Clicked1.setVisible(true);
        Clicked2.setVisible(false);
        Clicked3.setVisible(false);
        Clicked4.setVisible(false);
        Clicked5.setVisible(false);
    }//GEN-LAST:event_menuPaymentMouseClicked

    private void menuFoodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuFoodMouseClicked
        konten.show(panelContent, "card3");
        menuFood.setBackground(Color.decode("#494848"));
        menuFood.setOpaque(true);
        
        Clicked1.setVisible(false);
        Clicked2.setVisible(false);
        Clicked3.setVisible(true);
        Clicked4.setVisible(false);
        Clicked5.setVisible(false);
    }//GEN-LAST:event_menuFoodMouseClicked

    private void menuFoodMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuFoodMouseEntered
        menuFood.setBackground(Color.decode("#333333"));
        menuFood.setOpaque(true);
    }//GEN-LAST:event_menuFoodMouseEntered

    private void menuFoodMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuFoodMouseExited
        menuFood.setBackground(Color.decode("#292728"));
        menuFood.setOpaque(true);
    }//GEN-LAST:event_menuFoodMouseExited

    private void menuAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAccountMouseClicked
        konten.show(panelContent, "card5");
        menuAccount.setBackground(Color.decode("#494848"));
        menuAccount.setOpaque(true);
        
        Clicked1.setVisible(false);
        Clicked2.setVisible(false);
        Clicked3.setVisible(false);
        Clicked4.setVisible(false);
        Clicked5.setVisible(true);
    }//GEN-LAST:event_menuAccountMouseClicked

    private void menuAccountMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAccountMouseEntered
        menuAccount.setBackground(Color.decode("#333333"));
        menuAccount.setOpaque(true);
    }//GEN-LAST:event_menuAccountMouseEntered

    private void menuAccountMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAccountMouseExited
        menuAccount.setBackground(Color.decode("#292728"));
        menuAccount.setOpaque(true);
    }//GEN-LAST:event_menuAccountMouseExited

    private void menuTransactionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTransactionMouseClicked
        konten.show(panelContent, "card2");
        menuTransaction.setBackground(Color.decode("#494848"));
        menuTransaction.setOpaque(true);
        
        Clicked1.setVisible(false);
        Clicked2.setVisible(true);
        Clicked3.setVisible(false);
        Clicked4.setVisible(false);
        Clicked5.setVisible(false);
    }//GEN-LAST:event_menuTransactionMouseClicked

    private void menuTransactionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTransactionMouseEntered
        menuTransaction.setBackground(Color.decode("#333333"));
        menuTransaction.setOpaque(true);
    }//GEN-LAST:event_menuTransactionMouseEntered

    private void menuTransactionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTransactionMouseExited
        menuTransaction.setBackground(Color.decode("#292728"));
        menuTransaction.setOpaque(true);
    }//GEN-LAST:event_menuTransactionMouseExited

    private void menuDrinksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDrinksMouseClicked
        konten.show(panelContent, "card4");
        menuDrinks.setBackground(Color.decode("#494848"));
        menuDrinks.setOpaque(true);
        
        Clicked1.setVisible(false);
        Clicked2.setVisible(false);
        Clicked3.setVisible(false);
        Clicked4.setVisible(true);
        Clicked5.setVisible(false);
    }//GEN-LAST:event_menuDrinksMouseClicked

    private void menuDrinksMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDrinksMouseEntered
        menuDrinks.setBackground(Color.decode("#333333"));
        menuDrinks.setOpaque(true);
    }//GEN-LAST:event_menuDrinksMouseEntered

    private void menuDrinksMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDrinksMouseExited
        menuDrinks.setBackground(Color.decode("#292728"));
        menuDrinks.setOpaque(true);
    }//GEN-LAST:event_menuDrinksMouseExited

    private void btnCancelLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelLoginActionPerformed
        getToolkit().beep();
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure want to exit the app?","Warning",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_btnCancelLoginActionPerformed

    private void txtUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsernameFocusGained
        if(txtUsername.getText().equals("Username")){
            txtUsername.setText("");
        }
    }//GEN-LAST:event_txtUsernameFocusGained

    private void txtUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsernameFocusLost
        if(txtUsername.getText().isEmpty()){
            txtUsername.setText("Username");
        }
    }//GEN-LAST:event_txtUsernameFocusLost

    private void txtPinFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPinFocusGained
        if(txtPin.getText().equals("******")){
            txtPin.setText("");
        }
    }//GEN-LAST:event_txtPinFocusGained

    private void txtPinFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPinFocusLost
        if(txtPin.getText().isEmpty()){
            txtPin.setText("******");
        }
    }//GEN-LAST:event_txtPinFocusLost

    private void txtUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyPressed
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            if(txtUsername.getText().isEmpty()){
                txtUsername.setText("Username");
            }
            txtPin.requestFocus();
        }
    }//GEN-LAST:event_txtUsernameKeyPressed

    private void txtPinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPinKeyPressed
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            if(txtPin.getText().isEmpty()){
                txtPin.setText("******");
            }
            
            Login();
        }
    }//GEN-LAST:event_txtPinKeyPressed

    private void txtPinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPinKeyTyped
        cekPin(evt);
    }//GEN-LAST:event_txtPinKeyTyped

    private void txtUsernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyTyped
        char karakter = evt.getKeyChar();
        if((((karakter == KeyEvent.VK_SPACE) || (karakter == KeyEvent.VK_TAB)))){
            getToolkit().beep();
            evt.consume();
        }
        
        if(txtUsername.getText().length() >= 15){
            evt.consume();
        }
    }//GEN-LAST:event_txtUsernameKeyTyped

    private void txtPinKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPinKeyReleased
        if(txtPin.getText().length() == 6){
            Login();
        }
    }//GEN-LAST:event_txtPinKeyReleased

    private void btnShowAccountDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowAccountDefaultActionPerformed
        getToolkit().beep();
        JOptionPane.showMessageDialog(null,
                "Username : Admin \n"
               +"PIN      : 123456","Default Login Account",1);
    }//GEN-LAST:event_btnShowAccountDefaultActionPerformed

    private void menuLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLogoutMouseClicked
        getToolkit().beep();
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure want to logout from this account?","Warning",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            konten.show(panelContent, "card1");
            menuLogout.setBackground(Color.decode("#494848"));
            menuLogout.setOpaque(true);
            kontenHome.show(Home,"login");

            Clicked1.setVisible(true);
            Clicked2.setVisible(false);
            Clicked3.setVisible(false);
            Clicked4.setVisible(false);
            Clicked5.setVisible(false);
        }
        
    }//GEN-LAST:event_menuLogoutMouseClicked

    private void menuLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLogoutMouseEntered
        menuLogout.setBackground(Color.decode("#333333"));
        menuLogout.setOpaque(true);
    }//GEN-LAST:event_menuLogoutMouseEntered

    private void menuLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLogoutMouseExited
        menuLogout.setBackground(Color.decode("#292728"));
        menuLogout.setOpaque(true);
    }//GEN-LAST:event_menuLogoutMouseExited

    private void btnDeleteTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteTransactionActionPerformed
        if(id_transaksiSelected.equals("0")){
            btnDeleteTransaction.setEnabled(false);
        }else{
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to delete this transaction?","Warning",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION){
                tblDetailPemesanan.clearSelection();
                tblDetailPemesanan.removeAll();
                try {
                    Statement stat = connector.getKoneksi().createStatement();
                    query = "delete from transaksi where transaksi_id = '"+id_transaksiSelected+"'";
                    stat.executeUpdate(query);
                    modelTblPayment = (DefaultTableModel) tblPemesanan.getModel();
                    int[] rows = tblPemesanan.getSelectedRows();
                    for (int row : rows) {
                        modelTblPayment.removeRow(tblPemesanan.convertRowIndexToModel(row));
                    }
                    DetailPayment("0");
                    tblPemesanan.clearSelection();
                } catch (SQLException ex) {
                    Logger.getLogger(beranda.class.getName()).log(Level.SEVERE, null, ex);
                }
                btnDeleteTransaction.setEnabled(false);
                id_transaksiSelected = "0";
            }
        }
    }//GEN-LAST:event_btnDeleteTransactionActionPerformed

    private void btnRefreshTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshTransactionActionPerformed
        btnDeleteTransaction.setEnabled(false);
        btnDeleteDetail.setEnabled(false);
        btnAddDetail.setEnabled(false);
        btnEditTransaction.setEnabled(false);
        btnPay.setEnabled(false);
        id_transaksiSelected = "0";
        btnRefreshDetail.setEnabled(false);
        Payments();
        DetailPayment("0");
        btnRefreshDetail.setEnabled(false);
    }//GEN-LAST:event_btnRefreshTransactionActionPerformed

    private void txtCashKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashKeyPressed
        
    }//GEN-LAST:event_txtCashKeyPressed

    private void txtCashKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashKeyTyped
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || 
                (karakter == KeyEvent.VK_BACK_SPACE) || 
                (karakter == KeyEvent.VK_DELETE))) || (karakter == KeyEvent.VK_SPACE) || (karakter == KeyEvent.VK_TAB)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtCashKeyTyped

    private void txtCashKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashKeyReleased
        if(!txtCash.getText().isEmpty()){
            int a,b;
            a = Integer.valueOf(txtCash.getText());
            b = totalPayment;
            if(a >= b){
                btnPay.setEnabled(true);
                cashMoney = Integer.parseInt(txtCash.getText());
                changeMoney = a-b;
                txtChange.setText("Rp "+changeMoney);
            }else{
                btnPay.setEnabled(false);
                cashMoney = 0;
                txtChange.setText("Rp "+cashMoney);
            }
        }else{
            btnPay.setEnabled(false);
            cashMoney = 0;
            txtChange.setText("Rp "+cashMoney);
        }
    }//GEN-LAST:event_txtCashKeyReleased

    private void btnPayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPayMouseClicked
        try {
            Statement stat = connector.getKoneksi().createStatement();
            query = "update transaksi set "
                    + "transaksi_bayar = "+totalPayment+", "
                    + "transaksi_cash = "+cashMoney+","
                    + "transaksi_kembali = "+changeMoney+","
                    + "transaksi_status = 1 where transaksi_id = '"+id_transaksiSelected+"'";
            stat.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Payment Successful");
            PrintStruk("Struk",
                    "SELECT transaksi.transaksi_id,"
                            + "transaksi.transaksi_waktu,"
                            + "kasir.kasir_id,"
                            + "UPPER(kasir.kasir_nama) as kasir_nama,"
                            + "transaksi.AN,"
                            + "UPPER(produk.produk_nama) as produk_nama, "
                            + "detail_transaksi.jumlah,"
                            + "FORMAT(((produk.produk_harga-((produk.produk_diskon/100)*produk.produk_harga))*detail_transaksi.jumlah),0) as harga,"
                            + "FORMAT(transaksi.transaksi_bayar,0) as transaksi_bayar,"
                            + "FORMAT(transaksi.transaksi_cash,0) as transaksi_cash,"
                            + "FORMAT(transaksi.transaksi_kembali,0) as transaksi_kembali,"
                            + "DATE_FORMAT(transaksi.transaksi_waktu,'%d/%m/%Y %H:%i %p') as waktu "
                            + "FROM transaksi "
                            + "left join detail_transaksi "
                            + "on transaksi.transaksi_id = detail_transaksi.transaksi_id "
                            + "INNER join produk "
                            + "on produk.produk_id = detail_transaksi.produk_id "
                            + "inner join kasir "
                            + "on kasir.kasir_id = transaksi.kasir_id where detail_transaksi.transaksi_id = "+id_transaksiSelected,
                    "./src/org/deadlock/restoran/report/struk.jrxml");
            
            txtCash.setText("");
            txtChange.setText("Rp");
            txtTotalPayment.setText("Rp");
            
            DetailPayment("0");
            Payments();
            
        } catch (SQLException ex) {
            Logger.getLogger(beranda.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }//GEN-LAST:event_btnPayMouseClicked

    private void PrintStruk(String TitleForm, String Query, String ReportFileLocation){
        try{
            ResultSet rs;
            Statement st = connector.getKoneksi().createStatement();
            rs = st.executeQuery(Query);
            JasperPrint jasperPrint;       
            JRResultSetDataSource jrRS = new JRResultSetDataSource (rs);        
            JasperReport jasperReport = JasperCompileManager.compileReport(ReportFileLocation);          
            jasperPrint = JasperFillManager.fillReport(jasperReport, null, jrRS);
            JRViewer aViewer = new JRViewer(jasperPrint);                  
            JDialog viewer = new JDialog();
            viewer.setTitle(TitleForm);
            viewer.setAlwaysOnTop(true);
            viewer.getContentPane().add(aViewer);                  
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();     
            viewer.setBounds(0,0,400, 650);
            viewer.setVisible(true);                   
        }catch (HeadlessException | SecurityException | SQLException | JRException e) {
            Logger.getLogger(beranda.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    private void btnRefreshDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshDetailActionPerformed
        btnDeleteDetail.setEnabled(false);
        id_detailSelected = "0";
        DetailPayment(id_transaksiSelected);
    }//GEN-LAST:event_btnRefreshDetailActionPerformed

    private void btnDeleteDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteDetailActionPerformed
        if(id_detailSelected.equals("0")){
            btnDeleteDetail.setEnabled(false);
        }else{
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to delete this item?","Warning",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION){
                try {
                    System.out.println("ID DETAIL : "+id_detailSelected);
                    Statement stat = connector.getKoneksi().createStatement();
                    query = "delete from detail_transaksi where detail_id = "+id_detailSelected;
                    stat.executeUpdate(query);
                    modelTblPaymentDetail = (DefaultTableModel) tblDetailPemesanan.getModel();
                    int[] rows = tblDetailPemesanan.getSelectedRows();
                    for (int row : rows) {
                        modelTblPaymentDetail.removeRow(tblDetailPemesanan.convertRowIndexToModel(row));
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(beranda.class.getName()).log(Level.SEVERE, null, ex);
                }
                btnDeleteDetail.setEnabled(false);
                DetailPayment(id_transaksiSelected);
            }
        }
    }//GEN-LAST:event_btnDeleteDetailActionPerformed

    private void cmbCategoriesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCategoriesItemStateChanged
        Products();
    }//GEN-LAST:event_cmbCategoriesItemStateChanged

    private void btnEditTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditTransactionActionPerformed
        String transaksi_id = tblPemesanan.getValueAt(tblPemesanan.getSelectedRow(), 1).toString();
        String order_name = tblPemesanan.getValueAt(tblPemesanan.getSelectedRow(), 2).toString();;
        txtNamaOrder.setText(order_name);
        transaksiID_created = transaksi_id;
        shoppingList(transaksiID_created);
        
        konten.show(panelContent, "card2");
        menuTransaction.setBackground(Color.decode("#494848"));
        menuTransaction.setOpaque(true);
        Clicked1.setVisible(false);
        Clicked2.setVisible(true);
        Clicked3.setVisible(false);
        Clicked4.setVisible(false);
        Clicked5.setVisible(false);
    }//GEN-LAST:event_btnEditTransactionActionPerformed

    private void btnConformOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConformOrderActionPerformed
        query = "update transaksi set AN = '"+txtNamaOrder.getText()+"' where transaksi_id='"+transaksiID_created+"'";
        try{
            Statement stat = connector.getKoneksi().createStatement();
            stat.executeUpdate(query);
            shoppingList("0");
            txtNamaOrder.setText("");
            transaksiID_created = "";
            JOptionPane.showMessageDialog(null, "Transaction Saved");
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error Connection");
        }
        
    }//GEN-LAST:event_btnConformOrderActionPerformed

    private void txtProductDiscountDrinksKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductDiscountDrinksKeyTyped
        cekAngka(evt);
    }//GEN-LAST:event_txtProductDiscountDrinksKeyTyped

    private void txtProductPriceDrinksKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductPriceDrinksKeyTyped
        cekAngka(evt);
    }//GEN-LAST:event_txtProductPriceDrinksKeyTyped

    private void txtProductDiscountFoodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductDiscountFoodKeyTyped
        cekAngka(evt);
    }//GEN-LAST:event_txtProductDiscountFoodKeyTyped

    private void txtProductPriceFoodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductPriceFoodKeyTyped
        cekAngka(evt);
    }//GEN-LAST:event_txtProductPriceFoodKeyTyped

    private void btnModifyProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyProductActionPerformed
        query = "update produk set produk_nama = '"+txtProductNameFood.getText()+"',produk_harga="+txtProductPriceFood.getText()+",produk_diskon="+txtProductDiscountFood.getText()+" where produk_id="+productFoodIDSelected;
        try{
            Statement stat = connector.getKoneksi().createStatement();
            stat.executeUpdate(query);
            productFoodIDSelected = "";
            txtProductNameFood.setText("");
            txtProductDiscountFood.setText("");
            txtProductPriceFood.setText("");
            btnModifyProduct.setEnabled(false);
            listProducts();
            DetailPayment("0");
            Payments();
            Products();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }//GEN-LAST:event_btnModifyProductActionPerformed

    private void btnModifyProductDrinksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyProductDrinksActionPerformed
        query = "update produk set produk_nama = '"+txtProductNameDrinks.getText()+"',produk_harga="+txtProductPriceDrinks.getText()+",produk_diskon="+txtProductDiscountDrinks.getText()+" where produk_id="+productDrinksIDSelected;
        try{
            Statement stat = connector.getKoneksi().createStatement();
            stat.executeUpdate(query);
            productDrinksIDSelected = "";
            txtProductNameDrinks.setText("");
            txtProductDiscountDrinks.setText("");
            txtProductPriceDrinks.setText("");
            btnModifyProductDrinks.setEnabled(false);
            listProductsDrinks();
            DetailPayment("0");
            Payments();
            Products();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }//GEN-LAST:event_btnModifyProductDrinksActionPerformed

    private void btnAddProductsDrinksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductsDrinksActionPerformed
        query = "insert into produk(produk_nama,kategori_id,produk_harga,produk_diskon) values ('"+txtProductNameDrinks.getText()+"',2,"+txtProductPriceDrinks.getText()+","+txtProductDiscountDrinks.getText()+")";
        try{
            Statement stat = connector.getKoneksi().createStatement();
            stat.executeUpdate(query);
            productDrinksIDSelected = "";
            txtProductNameDrinks.setText("");
            txtProductDiscountDrinks.setText("");
            txtProductPriceDrinks.setText("");
            btnModifyProductDrinks.setEnabled(false);
            listProductsDrinks();
            DetailPayment("0");
            Payments();
            Products();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }//GEN-LAST:event_btnAddProductsDrinksActionPerformed

    private void btnAddProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductsActionPerformed
        query = "insert into produk(produk_nama,kategori_id,produk_harga,produk_diskon) values ('"+txtProductNameDrinks.getText()+"',1,"+txtProductPriceDrinks.getText()+","+txtProductDiscountDrinks.getText()+")";
        try{
            Statement stat = connector.getKoneksi().createStatement();
            stat.executeUpdate(query);
            productFoodIDSelected = "";
            txtProductNameFood.setText("");
            txtProductDiscountFood.setText("");
            txtProductPriceFood.setText("");
            btnModifyProduct.setEnabled(false);
            listProducts();
            DetailPayment("0");
            Payments();
            Products();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }//GEN-LAST:event_btnAddProductsActionPerformed

    private void Login(){        
        String id,pin;
        if(!txtUsername.getText().isEmpty() && !txtPin.getText().isEmpty()){
            if(txtUsername.getText().equals("Username") || txtPin.getText().equals("******")){
                getToolkit().beep();
                JOptionPane.showMessageDialog(null,"Change Username or PIN");
            }else{
                id = txtUsername.getText();
                pin = txtPin.getText();
                
                if(txtPin.getText().length() == 6){
                    //login.show(loginArea, "loginResult");
                    try {
                        Statement stat = connector.getKoneksi().createStatement();
                        query = "select * from kasir where kasir_id = '"+id+"' and kasir_pin = md5('"+pin+"')";
                        ResultSet res = stat.executeQuery(query);
                        if(res.next()){
                            String pola = "dd-MM-yyyy";
                            Locale lokal = null;
                            String hasil;
                            Date tanggalDanWaktu = new Date();
                            //tanggalDanWaktu = new SimpleDateFormat("yy-MM-dd hh:mm:ss").parse(res.getString("transaksi_waktu"));
                            hasil = tampilkanTanggalDanWaktu(tanggalDanWaktu, pola, lokal);
                            System.out.println("Login Berhasil : "+res.getString("kasir_nama"));
                            txtID.setText("Logged  : "+res.getString("kasir_nama"));
                            txtDateLog.setText("Date      : "+hasil);
                            kontenHome.show(Home, "home");
                            username = res.getString("kasir_id");
                        }else{
                            getToolkit().beep();
                            JOptionPane.showMessageDialog(null,"Account not found");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,"Error Connection");
                    }
                }else{
                    getToolkit().beep();
                    JOptionPane.showMessageDialog(null,"PIN must be 6 characters");
                }
                
                
            }
        }else{
            getToolkit().beep();
            JOptionPane.showMessageDialog(null,"Please input Username and PIN");
        }
    }
    
    private void cekPin(java.awt.event.KeyEvent evt){
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || 
                (karakter == KeyEvent.VK_BACK_SPACE) || 
                (karakter == KeyEvent.VK_DELETE))) || (karakter == KeyEvent.VK_SPACE) || (karakter == KeyEvent.VK_TAB)){
            getToolkit().beep();
            evt.consume();
        }
        
        if(txtPin.getText().length() >= 6){
            evt.consume();
        }
    }
    
    private void cekAngka(java.awt.event.KeyEvent evt){
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || 
                (karakter == KeyEvent.VK_BACK_SPACE) || 
                (karakter == KeyEvent.VK_DELETE))) || (karakter == KeyEvent.VK_SPACE) || (karakter == KeyEvent.VK_TAB)){
            getToolkit().beep();
            evt.consume();
        }
    }
    
    private void CustomizeTablePayment(){
        //kostumisasi tabel pemesanan
        JTableHeader header = tblPemesanan.getTableHeader();
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(178,8,55));
        headerRenderer.setForeground(new Color(255,255,255));
        header.setPreferredSize(new Dimension(100, 32));
        header.setFont(new Font("Arial",Font.BOLD,11));
        
        for (int i = 0; i < tblPemesanan.getModel().getColumnCount(); i++) {
                tblPemesanan.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        header.setOpaque(true);
        //tblPemesanan.setIntercellSpacing(new Dimension (0,6));
        tblPemesanan.getColorModel();
        
        jScrollPanePemesanan.setOpaque(false);
        jScrollPanePemesanan.getViewport().setOpaque(false);
        jScrollPanePemesanan.setViewportBorder(null);
    }
    
    private void CustomizeTableDetailPayment(){
        //kostumisasi tabel detail pemesanan
        JTableHeader header = tblDetailPemesanan.getTableHeader();
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(178,8,55));
        headerRenderer.setForeground(new Color(255,255,255));
        header.setPreferredSize(new Dimension(100, 32));
        header.setFont(new Font("Sagoe UI",Font.BOLD,12));
        
        for (int i = 0; i < tblDetailPemesanan.getModel().getColumnCount(); i++) {
                tblDetailPemesanan.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        header.setOpaque(true);
        
        jScrollPaneDetailPemesanan.setOpaque(false);
        jScrollPaneDetailPemesanan.getViewport().setOpaque(false);
        jScrollPaneDetailPemesanan.setViewportBorder(null);
    }
    
    private void CostumizeTableShoppingCart(){
        //kostumisasi tabel Shopping Cart
        JTableHeader header = tblShoppingCart.getTableHeader();
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(178,8,55));
        headerRenderer.setForeground(new Color(255,255,255));
        header.setPreferredSize(new Dimension(100, 32));
        header.setFont(new Font("Sagoe UI",Font.BOLD,12));
        
        for (int i = 0; i < tblShoppingCart.getModel().getColumnCount(); i++) {
                tblShoppingCart.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        header.setOpaque(true);
        
        jScrollPaneTableShoppingCart.setOpaque(false);
        jScrollPaneTableShoppingCart.getViewport().setOpaque(false);
        jScrollPaneTableShoppingCart.setViewportBorder(null);
        
    }
    
    private void CostumizeTableProducts(){
        //kostumisasi tabel detail pemesanan
        JTableHeader header = tblProducts.getTableHeader();
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(178,8,55));
        headerRenderer.setForeground(new Color(255,255,255));
        header.setPreferredSize(new Dimension(100, 32));
        header.setFont(new Font("Sagoe UI",Font.BOLD,12));
        
        for (int i = 0; i < tblProducts.getModel().getColumnCount(); i++) {
                tblProducts.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        header.setOpaque(true);
        
        jScrollPaneProducts.setOpaque(false);
        jScrollPaneProducts.getViewport().setOpaque(false);
        jScrollPaneProducts.setViewportBorder(null);
    }
    
    //Tabel pemesanan
    private void Payments(){
        //Mengisi tabel pemesanan
        //btnEditTransaction.setVisible(false);
        btnAddDetail.setVisible(false);
        tblPemesanan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modelTblPayment = (DefaultTableModel) tblPemesanan.getModel();
        modelTblPayment.getDataVector().removeAllElements();
        modelTblPayment.fireTableDataChanged();
        
        String pola = "h:mm a";
        Locale lokal = null;
        String hasil = null;
        Date tanggalDanWaktu;
        
        //tblPemesanan.removeColumn(tblPemesanan.getColumnModel().getColumn(1));
        
        try {
            Statement stat = connector.getKoneksi().createStatement();
            query = "select * from transaksi where transaksi_status=0 and (select count(detail_id) from detail_transaksi where detail_transaksi.transaksi_id = transaksi.transaksi_id)>0 and AN !='A/N'";
            ResultSet res = stat.executeQuery(query);
                       
            int a = 1;
            while(res.next()){
                Object[] obj = new Object[4];
                obj[0] = "  "+a;
                obj[1] = res.getString("transaksi_id"); 
                obj[2] = res.getString("AN");
                try {
                    tanggalDanWaktu = new SimpleDateFormat("yy-MM-dd hh:mm:ss").parse(res.getString("transaksi_waktu"));
                    hasil = tampilkanTanggalDanWaktu(tanggalDanWaktu, pola, lokal);
                } catch (ParseException ex) {
                    Logger.getLogger(beranda.class.getName()).log(Level.SEVERE, null, ex);
                }
                obj[3] = hasil;
                modelTblPayment.addRow(obj);
                a++;
            }
        } catch (SQLException ex) {
            System.out.println("Error : "+ex.getMessage());
        }
        
    }
    
    private void DetailPayment(String id){     
        //Mengisi tabel pemesanan
        btnDeleteDetail.setEnabled(false);
        tblDetailPemesanan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modelTblPaymentDetail = (DefaultTableModel) tblDetailPemesanan.getModel();
        modelTblPaymentDetail.getDataVector().removeAllElements();
        modelTblPaymentDetail.fireTableDataChanged();
        int total = 0;
        
        try {
            Statement stat = connector.getKoneksi().createStatement();
            query = "select detail_transaksi.detail_id,"
                    + "detail_transaksi.transaksi_id,"
                    + "produk.produk_id,"
                    + "jumlah as qty,"
                    + "produk.produk_nama,"
                    + "kategori_nama,"
                    + "produk.produk_harga as harga,"
                    + "produk.produk_diskon as diskon,"
                    + "(detail_transaksi.jumlah*(produk.produk_harga-((produk_diskon/100)*(produk.produk_harga)))) as total "
                    + "from detail_transaksi "
                    + "inner join "
                    + "produk on "
                    + "detail_transaksi.produk_id = produk.produk_id "
                    + "inner join "
                    + "kategori on kategori.kategori_id = produk.kategori_id "
                    + "where detail_transaksi.transaksi_id = '"+id+"'";
            System.out.println(query);
            ResultSet res = stat.executeQuery(query);
                       
            int a = 1;
            while(res.next()){
                Object[] obj = new Object[8];
                obj[0] = res.getString("transaksi_id");;
                obj[1] = res.getString("detail_id");
                obj[2] = "   "+a;
                obj[3] = res.getString("produk_nama");   
                obj[4] = res.getString("qty");
                obj[5] = "Rp "+res.getString("harga");
                obj[6] = res.getString("diskon")+"%";
                obj[7] = "Rp "+res.getString("total");
                total += Integer.parseInt(res.getString("total"));
                modelTblPaymentDetail.addRow(obj);
                a++;
            }
            txtTotalPayment.setText("Rp "+total);
            totalPayment = total;
        } catch (SQLException ex) {
            System.out.println("Error : "+ex.getMessage());
        }
    }
    
    private void CustomizeTableListProducts(){
        JTableHeader header = tblListProducs.getTableHeader();
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(178,8,55));
        headerRenderer.setForeground(new Color(255,255,255));
        header.setPreferredSize(new Dimension(100, 32));
        header.setFont(new Font("Sagoe UI",Font.BOLD,12));
        
        for (int i = 0; i < tblListProducs.getModel().getColumnCount(); i++) {
                tblListProducs.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        header.setOpaque(true);
        
        jScrollPaneListProducts.setOpaque(false);
        jScrollPaneListProducts.getViewport().setOpaque(false);
        jScrollPaneListProducts.setViewportBorder(null);
    }
    
    private void CustomizeTableListProductsDrinks(){
        JTableHeader header = tblListProducsDrinks.getTableHeader();
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(178,8,55));
        headerRenderer.setForeground(new Color(255,255,255));
        header.setPreferredSize(new Dimension(100, 32));
        header.setFont(new Font("Sagoe UI",Font.BOLD,12));
        
        for (int i = 0; i < tblListProducsDrinks.getModel().getColumnCount(); i++) {
                tblListProducsDrinks.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        header.setOpaque(true);
        
        jScrollPaneListProductsDrinks.setOpaque(false);
        jScrollPaneListProductsDrinks.getViewport().setOpaque(false);
        jScrollPaneListProductsDrinks.setViewportBorder(null);
    }
    
    private void listProducts(){
        query = "select produk.produk_id,produk.produk_nama,produk.produk_diskon,FORMAT(produk.produk_harga,0) as produk_harga,kategori.kategori_nama from produk inner join kategori on kategori.kategori_id = produk.kategori_id where produk.kategori_id = 1";
        tblListProducs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modelTblListProductsFood = (DefaultTableModel) tblListProducs.getModel();
        modelTblListProductsFood.getDataVector().removeAllElements();
        modelTblListProductsFood.fireTableDataChanged();
        
        
        try {
            Statement stat = connector.getKoneksi().createStatement();
            ResultSet res = stat.executeQuery(query);
                       
            int a = 1;
            while(res.next()){
                Object[] obj = new Object[5];
                obj[0] = " "+a;
                obj[1] = res.getString("produk_nama");
                obj[2] = res.getString("produk_diskon")+"%";
                obj[3] = "Rp "+res.getString("produk_harga");
                obj[4] = res.getString("produk_id");
                modelTblListProductsFood.addRow(obj);
                a++;
            }
        } catch (SQLException ex) {
            System.out.println("Error : "+ex.getMessage());
        }
    }
    
    private void listProductsDrinks(){
        query = "select produk.produk_id,produk.produk_nama,produk.produk_diskon,FORMAT(produk.produk_harga,0) as produk_harga,kategori.kategori_nama from produk inner join kategori on kategori.kategori_id = produk.kategori_id where produk.kategori_id = 2";
        tblListProducsDrinks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modelTblListProductsDrinks = (DefaultTableModel) tblListProducsDrinks.getModel();
        modelTblListProductsDrinks.getDataVector().removeAllElements();
        modelTblListProductsDrinks.fireTableDataChanged();
        
        
        try {
            Statement stat = connector.getKoneksi().createStatement();
            ResultSet res = stat.executeQuery(query);
                       
            int a = 1;
            while(res.next()){
                Object[] obj = new Object[5];
                obj[0] = " "+a;
                obj[1] = res.getString("produk_nama");
                obj[2] = res.getString("produk_diskon")+"%";
                obj[3] = "Rp "+res.getString("produk_harga");
                obj[4] = res.getString("produk_id");
                modelTblListProductsDrinks.addRow(obj);
                a++;
            }
        } catch (SQLException ex) {
            System.out.println("Error : "+ex.getMessage());
        }
    }
    
    private void Products(){
        query = "select produk.produk_id,produk.produk_nama,produk.produk_diskon,FORMAT(produk.produk_harga,0) as produk_harga,kategori.kategori_nama from produk inner join kategori on kategori.kategori_id = produk.kategori_id";
        if(cmbCategories.getSelectedItem().equals("All")){
            query = "select produk.produk_id,produk.produk_nama,produk.produk_diskon,FORMAT(produk.produk_harga,0) as produk_harga,kategori.kategori_nama from produk inner join kategori on kategori.kategori_id = produk.kategori_id";
        }else if(cmbCategories.getSelectedItem().equals("Foods")){
            query = "select produk.produk_id,produk.produk_nama,produk.produk_diskon,FORMAT(produk.produk_harga,0) as produk_harga,kategori.kategori_nama from produk inner join kategori on kategori.kategori_id = produk.kategori_id where produk.kategori_id = 1";
        }else if(cmbCategories.getSelectedItem().equals("Drinks")){
            query = "select produk.produk_id,produk.produk_nama,produk.produk_diskon,FORMAT(produk.produk_harga,0) as produk_harga,kategori.kategori_nama from produk inner join kategori on kategori.kategori_id = produk.kategori_id where produk.kategori_id = 2";
        }
        tblProducts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modelTblProducts = (DefaultTableModel) tblProducts.getModel();
        modelTblProducts.getDataVector().removeAllElements();
        modelTblProducts.fireTableDataChanged();
        
        
        try {
            Statement stat = connector.getKoneksi().createStatement();
            ResultSet res = stat.executeQuery(query);
                       
            int a = 1;
            while(res.next()){
                Object[] obj = new Object[6];
                obj[0] = "  "+a;
                obj[1] = res.getString("produk_nama"); 
                obj[2] = res.getString("kategori_nama");
                obj[3] = res.getString("produk_diskon")+"%";
                obj[4] = "Rp "+res.getString("produk_harga");
                obj[5] = res.getString("produk_id");
                modelTblProducts.addRow(obj);
                a++;
            }
        } catch (SQLException ex) {
            System.out.println("Error : "+ex.getMessage());
        }
        
        tblProducts.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                tblProducts = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = tblProducts.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && tblProducts.getSelectedRow() != -1) {
                    try{
                        String prodid = tblProducts.getValueAt(tblProducts.getSelectedRow(), 5).toString();
                        System.out.println("Produk ID : "+prodid);
                        if(transaksiID_created == "" || transaksiID_created == null){
                            transaksiID_created = randomTransaksi();
                            addTransaksi(prodid,"0");
                        }else{
                            addTransaksi(prodid,"1");
                        }
                    }catch(Exception ex){
                        System.out.println("Error : "+ex.getMessage());
                    }
                }
            }
        });
    }
    
    private void shoppingList(String transaksi_id){
        tblShoppingCart.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modelTblShopping = (DefaultTableModel) tblShoppingCart.getModel();
        modelTblShopping.getDataVector().removeAllElements();
        modelTblShopping.fireTableDataChanged();
        query = "select * from detail_transaksi inner join produk using(produk_id) where detail_transaksi.transaksi_id='"+transaksi_id+"'";
        
        try {
            Statement stat = connector.getKoneksi().createStatement();
            ResultSet res = stat.executeQuery(query);
                       
            int a = 1;
            while(res.next()){
                Object[] obj = new Object[5];
                obj[0] = res.getString("detail_id"); 
                obj[1] = res.getString("transaksi_id");
                obj[2] = "   "+a;
                obj[3] = res.getString("produk_nama");
                obj[4] = res.getString("jumlah");
                modelTblShopping.addRow(obj);
                a++;
            }
        } catch (SQLException ex) {
            System.out.println("Error : "+ex.getMessage());
        }
        
        tblShoppingCart.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                tblShoppingCart = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = tblShoppingCart.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && tblShoppingCart.getSelectedRow() != -1) {
                    try{
                        String detailID = tblShoppingCart.getValueAt(tblShoppingCart.getSelectedRow(), 0).toString();
                        System.out.println("DETAIL ID : "+detailID);
                        query = "update detail_transaksi set jumlah = jumlah-1 where detail_id="+detailID;
                        try {
                            Statement stat = connector.getKoneksi().createStatement();
                            stat.executeUpdate(query);
                            query = "delete from detail_transaksi where jumlah=0";
                            stat.executeUpdate(query);
                            shoppingList(transaksi_id);
                        } catch (SQLException ex) {
                            Logger.getLogger(beranda.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }catch(Exception ex){
                        System.out.println("Error : "+ex.getMessage());
                    }
                }
            }
        });
    }
    
    private void addTransaksi(String produk_id, String status){
        if(status == "0"){
            String pola = "yyyy-MM-dd H:m:s";
            Locale lokal = null;
            String hasil;
            Date tanggalDanWaktu = new Date();
            hasil = tampilkanTanggalDanWaktu(tanggalDanWaktu, pola, lokal);
            System.out.println("Date : "+hasil);

            query = "insert into transaksi values('"+transaksiID_created+"','"+hasil+"','"+username+"',0,0,0,'"+txtNamaOrder.getText()+"',0)";
            System.out.println("Query : "+query);
            try {
                Statement stat = connector.getKoneksi().createStatement();
                stat.executeUpdate(query);
                addTransaksi(produk_id, "1");
            } catch (SQLException ex) {
                Logger.getLogger(beranda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            query = "select * from detail_transaksi where transaksi_id = '"+transaksiID_created+"' and produk_id ='"+produk_id+"'";
            System.out.println("Query : "+query);
            try {
                Statement stat = connector.getKoneksi().createStatement();
                ResultSet res = stat.executeQuery(query);
                if(res.next()){
                    query = "update detail_transaksi set jumlah = jumlah+1 where transaksi_id = '"+transaksiID_created+"' and produk_id="+produk_id;
                    stat.executeUpdate(query);
                    shoppingList(transaksiID_created);
                }else{
                    query = "insert into detail_transaksi(transaksi_id,produk_id,jumlah) values('"+transaksiID_created+"','"+produk_id+"',1)";
                    stat.executeUpdate(query);
                    System.out.println("Query : "+query);
                    shoppingList(transaksiID_created);
                }
            } catch (SQLException ex) {
                Logger.getLogger(beranda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
    private static String tampilkanTanggalDanWaktu(Date tanggalDanWaktu,String pola, Locale lokal) {
        String tanggalStr;
        SimpleDateFormat formatter;
        if (lokal == null) {
            formatter = new SimpleDateFormat(pola);
        } else {
            formatter = new SimpleDateFormat(pola, lokal);
        }
 
        tanggalStr = formatter.format(tanggalDanWaktu);
        return tanggalStr;
    }
    
    private static String randomTransaksi(){
        String pola = "ddMMyym";
        Locale lokal = null;
        String hasil;
        Date tanggalDanWaktu = new Date();
        hasil = tampilkanTanggalDanWaktu(tanggalDanWaktu, pola, lokal);
        String chars = "0123456789";
        Random r = new Random();
        String hasilRandom = hasil+"-";
        for (int i = 0; i < 3; i++) {
            hasilRandom = hasilRandom+chars.charAt(r.nextInt(chars.length()));
        }
        
        return hasilRandom;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new beranda().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Clicked1;
    private javax.swing.JPanel Clicked2;
    private javax.swing.JPanel Clicked3;
    private javax.swing.JPanel Clicked4;
    private javax.swing.JPanel Clicked5;
    private javax.swing.JPanel Home;
    private javax.swing.JPanel PanelHome;
    private javax.swing.JPanel PanelLogin;
    private javax.swing.JButton btnAddDetail;
    private javax.swing.JButton btnAddProducts;
    private javax.swing.JButton btnAddProductsDrinks;
    private javax.swing.JButton btnCancelLogin;
    private javax.swing.JButton btnConformOrder;
    private javax.swing.JButton btnDeleteDetail;
    private javax.swing.JButton btnDeleteTransaction;
    private javax.swing.JButton btnEditTransaction;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnHelp;
    private javax.swing.JButton btnModifyProduct;
    private javax.swing.JButton btnModifyProductDrinks;
    private javax.swing.JLabel btnPay;
    private javax.swing.JButton btnRefreshDetail;
    private javax.swing.JButton btnRefreshTransaction;
    private javax.swing.JButton btnSettings;
    private javax.swing.JButton btnShowAccountDefault;
    private javax.swing.JComboBox<String> cmbCategories;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPaneDetailPemesanan;
    private javax.swing.JScrollPane jScrollPaneListProducts;
    private javax.swing.JScrollPane jScrollPaneListProductsDrinks;
    private javax.swing.JScrollPane jScrollPanePemesanan;
    private javax.swing.JScrollPane jScrollPaneProducts;
    private javax.swing.JScrollPane jScrollPaneTableShoppingCart;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel loginForm;
    private javax.swing.JLabel menuAccount;
    private javax.swing.JLabel menuDrinks;
    private javax.swing.JLabel menuFood;
    private javax.swing.JLabel menuLogout;
    private javax.swing.JLabel menuPayment;
    private javax.swing.JLabel menuTransaction;
    private javax.swing.JPanel panelContent;
    private javax.swing.JTable tblDetailPemesanan;
    private javax.swing.JTable tblListProducs;
    private javax.swing.JTable tblListProducsDrinks;
    private javax.swing.JTable tblPemesanan;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTable tblShoppingCart;
    private javax.swing.JTextField txtCash;
    private javax.swing.JTextField txtChange;
    private javax.swing.JLabel txtDateLog;
    private javax.swing.JLabel txtID;
    private javax.swing.JTextField txtNamaOrder;
    private javax.swing.JPasswordField txtPin;
    private javax.swing.JTextField txtProductDiscountDrinks;
    private javax.swing.JTextField txtProductDiscountFood;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtProductName3;
    private javax.swing.JTextField txtProductNameDrinks;
    private javax.swing.JTextField txtProductNameFood;
    private javax.swing.JTextField txtProductPriceDrinks;
    private javax.swing.JTextField txtProductPriceFood;
    private javax.swing.JTextField txtTotalPayment;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
