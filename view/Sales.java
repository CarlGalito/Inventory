
package view;

import InventoryConnectionProvider.inventoryConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sales extends javax.swing.JFrame {
    
    private List<CartItem> cart = new ArrayList<>();
    private double total = 0;

   
    private static class CartItem {
    int productId;
    String productName;
    double price;
    int quantity;
    String datetime; 

    CartItem(int productId, String productName, double price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;

        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.datetime = now.format(formatter);
    }
}
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Sales.class.getName());

   
    public Sales() {
        initComponents();
        setLocationRelativeTo(null);
        loadSalesHistory();
        loadFilteredSales("All Sales");
       
    }
     private void loadSalesHistory() {
    DefaultTableModel model = (DefaultTableModel) jtable1.getModel();
    model.setRowCount(0);

    try (Connection con = inventoryConnection.getCon()) {
        String sql = "SELECT * FROM sales ORDER BY sale_date DESC";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        int totalItems = 0;
        double totalSales = 0;

        while (rs.next()) {
            int id = rs.getInt("sales_id");
            String name = rs.getString("product_name");
            int qty = rs.getInt("quantity");
            double price = rs.getDouble("price");
            double subtotal = rs.getDouble("subtotal");
            Timestamp date = rs.getTimestamp("sale_date");

            model.addRow(new Object[]{id, name, qty, price, subtotal, date});

            totalItems += qty;
            totalSales += subtotal;
        }

        txtsold.setText(" " + totalItems);
        txttotalsales.setText(String.format("₱%.2f", totalSales));

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error loading sales: " + e.getMessage());
    }
} // ✅ properly closed here


// ✅ Now this is a separate method
private void loadFilteredSales(String filter) {
    DefaultTableModel model = (DefaultTableModel) jtable1.getModel();
    model.setRowCount(0);

    String sql = "SELECT * FROM sales ";
    switch (filter) {
        case "Today":
            sql += "WHERE DATE(sale_date) = CURDATE() ";
            break;
        case "This Week":
            sql += "WHERE YEARWEEK(sale_date, 1) = YEARWEEK(CURDATE(), 1) ";
            break;
        case "This Month":
            sql += "WHERE YEAR(sale_date) = YEAR(CURDATE()) AND MONTH(sale_date) = MONTH(CURDATE()) ";
            break;
        default:
            // All Sales (no filter)
            break;
    }
    sql += "ORDER BY sale_date DESC";

    try (Connection con = inventoryConnection.getCon()) {
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        int totalItems = 0;
        double totalSales = 0;

        while (rs.next()) {
            int id = rs.getInt("sales_id");
            String name = rs.getString("product_name");
            int qty = rs.getInt("quantity");
            double price = rs.getDouble("price");
            double subtotal = rs.getDouble("subtotal");
            Timestamp date = rs.getTimestamp("sale_date");

            model.addRow(new Object[]{id, name, qty, price, subtotal, date});

            totalItems += qty;
            totalSales += subtotal;
        }

        txtsold.setText(" " + totalItems);
        txttotalsales.setText(String.format("₱%.2f", totalSales));

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error loading filtered sales: " + e.getMessage());
    }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnmanage = new javax.swing.JButton();
        btnorder = new javax.swing.JButton();
        btnrestockhis = new javax.swing.JButton();
        btnsalesrecord = new javax.swing.JButton();
        BTNCLOSE = new javax.swing.JButton();
        btnaddstock1 = new javax.swing.JButton();
        btnarchived = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtsold = new javax.swing.JTextField();
        txttotalsales = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        combofilter = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(126, 189, 210));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Welcome, Admin!");

        btnmanage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnmanage.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dell\\Downloads\\Inventory Management System Images & Icon\\images\\category.png")); // NOI18N
        btnmanage.setText(" Manage Product");
        btnmanage.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnmanage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmanageActionPerformed(evt);
            }
        });

        btnorder.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnorder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Orders.png"))); // NOI18N
        btnorder.setText("Order");
        btnorder.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnorderActionPerformed(evt);
            }
        });

        btnrestockhis.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnrestockhis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/View-orders.png"))); // NOI18N
        btnrestockhis.setText("Restock History");
        btnrestockhis.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnrestockhis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrestockhisActionPerformed(evt);
            }
        });

        btnsalesrecord.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnsalesrecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/customers.png"))); // NOI18N
        btnsalesrecord.setText("Sales Record");
        btnsalesrecord.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnsalesrecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalesrecordActionPerformed(evt);
            }
        });

        BTNCLOSE.setBackground(new java.awt.Color(255, 102, 102));
        BTNCLOSE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BTNCLOSE.setForeground(new java.awt.Color(255, 255, 255));
        BTNCLOSE.setText("Close");
        BTNCLOSE.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTNCLOSE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNCLOSEActionPerformed(evt);
            }
        });

        btnaddstock1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnaddstock1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/product.png"))); // NOI18N
        btnaddstock1.setText("Add Stock");
        btnaddstock1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnaddstock1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddstock1ActionPerformed(evt);
            }
        });

        btnarchived.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnarchived.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/save.png"))); // NOI18N
        btnarchived.setText("Archived Record");
        btnarchived.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnarchived.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnarchivedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnrestockhis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnsalesrecord, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnorder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnmanage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnaddstock1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(BTNCLOSE, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnarchived, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnmanage, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnaddstock1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnorder, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnsalesrecord, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnrestockhis, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnarchived, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(BTNCLOSE, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("SALES");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jtable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Sale_ID", "Product_Name", "Quantity", "Price (₱)", "SubTotal (#)", "Date&Time"
            }
        ));
        jScrollPane1.setViewportView(jtable1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("SOLD ITEMS:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("TOTAL SALES:");

        txtsold.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txttotalsales.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("All Sales:");

        combofilter.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        combofilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Today", "This week ", "This month" }));
        combofilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combofilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txttotalsales, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtsold, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(115, 115, 115))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(combofilter, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(176, 176, 176))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combofilter, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsold, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttotalsales, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnmanageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmanageActionPerformed

        new ManageProd().setVisible(true);

    }//GEN-LAST:event_btnmanageActionPerformed

    private void btnrestockhisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrestockhisActionPerformed
       new RestockHistory().setVisible(true);
    }//GEN-LAST:event_btnrestockhisActionPerformed

    private void btnorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnorderActionPerformed
        new Purchase().setVisible(true);
    }//GEN-LAST:event_btnorderActionPerformed

    private void btnsalesrecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalesrecordActionPerformed
       new Sales().setVisible(true);
    }//GEN-LAST:event_btnsalesrecordActionPerformed

    private void BTNCLOSEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCLOSEActionPerformed

        setVisible(false);
    }//GEN-LAST:event_BTNCLOSEActionPerformed

    private void btnaddstock1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddstock1ActionPerformed
        new Addstock().setVisible(true);
    }//GEN-LAST:event_btnaddstock1ActionPerformed

    private void btnarchivedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnarchivedActionPerformed
        new Archived().setVisible(true);
    }//GEN-LAST:event_btnarchivedActionPerformed

    private void combofilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combofilterActionPerformed
                                           
    String selected = combofilter.getSelectedItem().toString();
    loadFilteredSales(selected);
 
    }//GEN-LAST:event_combofilterActionPerformed

private void confirmOrder() {
    if (cart.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No items ordered yet.");
        return;
    }

    try (Connection con = inventoryConnection.getCon()) {
        con.setAutoCommit(false);

        // Deduct stock
        String updateProductSQL = "UPDATE product SET quantity = quantity - ? WHERE product_id = ?";
        PreparedStatement updatePs = con.prepareStatement(updateProductSQL);

        // Record each sale
        String insertSaleSQL = """
            INSERT INTO sales 
            (product_id, product_name, quantity, price, subtotal, sale_date) 
            VALUES (?, ?, ?, ?, ?, NOW())
        """;
        PreparedStatement salePs = con.prepareStatement(insertSaleSQL);

        for (CartItem item : cart) {
            // Update product quantity
            updatePs.setInt(1, item.quantity);
            updatePs.setInt(2, item.productId);
            updatePs.executeUpdate();

            // Insert sale record
            double subtotal = item.price * item.quantity;
            salePs.setInt(1, item.productId);
            salePs.setString(2, item.productName);
            salePs.setInt(3, item.quantity);
            salePs.setDouble(4, item.price);
            salePs.setDouble(5, subtotal);
            salePs.executeUpdate();
        }

        con.commit();

        JOptionPane.showMessageDialog(this, 
            "Order placed successfully!\nTotal: ₱" + String.format("%.2f", total),
            "Order Success", JOptionPane.INFORMATION_MESSAGE);

        cart.clear();
        total = 0;
        txttotalsales.setText("₱0.00");
        loadSalesHistory();

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error confirming order: " + e.getMessage());
    }

}
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Sales().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNCLOSE;
    private javax.swing.JButton btnaddstock1;
    private javax.swing.JButton btnarchived;
    private javax.swing.JButton btnmanage;
    private javax.swing.JButton btnorder;
    private javax.swing.JButton btnrestockhis;
    private javax.swing.JButton btnsalesrecord;
    private javax.swing.JComboBox<String> combofilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtable1;
    private javax.swing.JTextField txtsold;
    private javax.swing.JTextField txttotalsales;
    // End of variables declaration//GEN-END:variables
}
