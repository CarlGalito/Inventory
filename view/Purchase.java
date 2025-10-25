
package view;

import InventoryConnectionProvider.inventoryConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;

public class Purchase extends javax.swing.JFrame {
      private List<CartItem> cart = new ArrayList<>();
    private double total = 0;

   
    private static class CartItem {
        int productId;
        String productName;
        double price;
        int quantity;

        CartItem(int productId, String productName, double price, int quantity) {
            this.productId = productId;
            this.productName = productName;
            this.price = price;
            this.quantity = quantity;
        }
    }
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Purchase.class.getName());


    public Purchase() {
       initComponents();
       setLocationRelativeTo(null);
        loadProducts();
        jtable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        updateTotalLabel();
    }
        private void loadProducts() {
    DefaultTableModel model = (DefaultTableModel) jtable.getModel();
    model.setRowCount(0);

    try (Connection conn = inventoryConnection.getCon()) {
        String sql = "SELECT * FROM product";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("product_id");
            String name = rs.getString("product_name");
            String category = rs.getString("category_name");
            int quantity = rs.getInt("quantity");
            double price = rs.getDouble("price");

            model.addRow(new Object[]{id, name, quantity, price, category});
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Database error while loading: " + e.getMessage());
        e.printStackTrace();
    }

    // 🟢 When you click a product, show details (but don't add to cart yet)
    jtable.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int row = jtable.getSelectedRow();
            if (row != -1) {
                DefaultTableModel model = (DefaultTableModel) jtable.getModel();

                int id = (int) model.getValueAt(row, 0);
                String name = (String) model.getValueAt(row, 1);
                int quantity = (int) model.getValueAt(row, 2);
                double price = (double) model.getValueAt(row, 3);
                String category = (String) model.getValueAt(row, 4);

                // Display details
                updateTotalLabel.setText("Selected: " + name + " (" + category + ")");
                txtquantity.setText(""); // Let user input manually
                txttotal.setText(String.format("₱%.2f", price));
            }
        }
    });

}
       private void updateTotalLabel() {
    total = 0;
    for (CartItem item : cart) {
        total += item.price * item.quantity;
    }
    txttotal.setText(String.format("₱%.2f", total));

}
       
       
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnmanage = new javax.swing.JButton();
        btnOrder = new javax.swing.JButton();
        btnRestockhis = new javax.swing.JButton();
        btnSalesrecord = new javax.swing.JButton();
        BTNCLOSE = new javax.swing.JButton();
        btnaddstock1 = new javax.swing.JButton();
        btnarchived = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtquantity = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtable = new javax.swing.JTable();
        btnorder = new javax.swing.JButton();
        txttotal = new javax.swing.JLabel();
        updateTotalLabel = new javax.swing.JLabel();
        jlabelsearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jcarttable = new javax.swing.JTable();
        btnadd = new javax.swing.JButton();

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

        btnOrder.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Orders.png"))); // NOI18N
        btnOrder.setText("Order");
        btnOrder.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });

        btnRestockhis.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRestockhis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/View-orders.png"))); // NOI18N
        btnRestockhis.setText("Restock History");
        btnRestockhis.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRestockhis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestockhisActionPerformed(evt);
            }
        });

        btnSalesrecord.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSalesrecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/customers.png"))); // NOI18N
        btnSalesrecord.setText("Sales");
        btnSalesrecord.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSalesrecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalesrecordActionPerformed(evt);
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
            .addComponent(btnRestockhis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSalesrecord, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnmanage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnaddstock1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnOrder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnarchived, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(BTNCLOSE, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnmanage, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnaddstock1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnSalesrecord, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnRestockhis, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnarchived, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(BTNCLOSE, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ORDER");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        txtquantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtquantityActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Quantity");

        jtable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Product Name", "Quantity", "Price (₱)", "Category"
            }
        ));
        jScrollPane1.setViewportView(jtable);

        btnorder.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnorder.setText("Order");
        btnorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnorderActionPerformed(evt);
            }
        });

        txttotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        updateTotalLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        updateTotalLabel.setText("TOTAL:");

        jlabelsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlabelsearchActionPerformed(evt);
            }
        });
        jlabelsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jlabelsearchKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Search:");

        jcarttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Price", "Quantity"
            }
        ));
        jScrollPane2.setViewportView(jcarttable);

        btnadd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnadd.setText("Add");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlabelsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnorder, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(updateTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnorder, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(updateTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlabelsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnmanageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmanageActionPerformed

        new ManageProd().setVisible(true);

    }//GEN-LAST:event_btnmanageActionPerformed

    private void btnRestockhisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestockhisActionPerformed
        new RestockHistory().setVisible(true);
    }//GEN-LAST:event_btnRestockhisActionPerformed

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed
        new Purchase().setVisible(true);
    }//GEN-LAST:event_btnOrderActionPerformed

    private void btnSalesrecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalesrecordActionPerformed
        new Sales().setVisible(true);
    }//GEN-LAST:event_btnSalesrecordActionPerformed

    private void BTNCLOSEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCLOSEActionPerformed

        setVisible(false);
    }//GEN-LAST:event_BTNCLOSEActionPerformed

    private void txtquantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtquantityActionPerformed

    }//GEN-LAST:event_txtquantityActionPerformed

    private void btnorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnorderActionPerformed
     DefaultTableModel cartModel = (DefaultTableModel) jcarttable.getModel();

    // 🛑 Step 1: check if cart is empty
    if (cartModel.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "No items in cart to order.");
        return;
    }

    try (Connection con = inventoryConnection.getCon()) {
        con.setAutoCommit(false); // Begin transaction

        // 🔹 Prepare SQL for updating stock
        String updateProductSQL = "UPDATE product SET quantity = quantity - ? WHERE product_id = ?";
        PreparedStatement updatePs = con.prepareStatement(updateProductSQL);

        // 🔹 Prepare SQL for inserting into sales history
        String insertSaleSQL = "INSERT INTO sales (product_id, product_name, quantity, price, subtotal, sale_date) VALUES (?, ?, ?, ?, ?, NOW())";
        PreparedStatement salePs = con.prepareStatement(insertSaleSQL);

        double total = 0;

        // 🧾 Step 2: Loop all items in cart
        for (int i = 0; i < cartModel.getRowCount(); i++) {
            int productId = (int) cartModel.getValueAt(i, 0);
            String productName = cartModel.getValueAt(i, 1).toString();
            double price = Double.parseDouble(cartModel.getValueAt(i, 2).toString());
            int quantity = Integer.parseInt(cartModel.getValueAt(i, 3).toString());

            double subtotal = price * quantity;
            total += subtotal;

            // 🧩 Deduct stock
            updatePs.setInt(1, quantity);
            updatePs.setInt(2, productId);
            updatePs.executeUpdate();

            // 🧩 Record sale
            salePs.setInt(1, productId);
            salePs.setString(2, productName);
            salePs.setInt(3, quantity);
            salePs.setDouble(4, price);
            salePs.setDouble(5, subtotal);
            salePs.executeUpdate();
        }

        con.commit(); // Save all

        // ✅ Step 3: Show confirmation
        JOptionPane.showMessageDialog(this,
            "Order placed successfully!\nTotal: ₱" + String.format("%.2f", total),
            "Order Success", JOptionPane.INFORMATION_MESSAGE);

        // ✅ Step 4: Clear cart and refresh
        cartModel.setRowCount(0);
        txttotal.setText("₱0.00");
        loadProducts();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error placing order: " + e.getMessage());
        e.printStackTrace();
    }
}

    private void updateCartTotal() {
    double total = 0;
    DefaultTableModel cartModel = (DefaultTableModel) jcarttable.getModel();

    for (int i = 0; i < cartModel.getRowCount(); i++) {
        Object priceObj = cartModel.getValueAt(i, 2);
        Object qtyObj = cartModel.getValueAt(i, 3);

        double price = 0;
        int qty = 0;

        // safely convert price
        if (priceObj != null) {
            try {
                price = Double.parseDouble(priceObj.toString());
            } catch (NumberFormatException e) {
                price = 0;
            }
        }

        // safely convert quantity
        if (qtyObj != null) {
            try {
                qty = Integer.parseInt(qtyObj.toString());
            } catch (NumberFormatException e) {
                qty = 0;
            }
        }

        total += price * qty;
    }

    txttotal.setText(String.format("₱%.2f", total));


    }//GEN-LAST:event_btnorderActionPerformed

    private void jlabelsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlabelsearchActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jlabelsearchActionPerformed

    private void jlabelsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlabelsearchKeyReleased
        // TODO add your handling code here:
        DefaultTableModel ob = (DefaultTableModel) jtable.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        jtable.setRowSorter(obj);

        // Make the search case-insensitive
        String searchText = jlabelsearch.getText().trim();
        obj.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
    }//GEN-LAST:event_jlabelsearchKeyReleased

    private void btnaddstock1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddstock1ActionPerformed
        new Addstock().setVisible(true);
    }//GEN-LAST:event_btnaddstock1ActionPerformed

    private void btnarchivedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnarchivedActionPerformed
        new Archived().setVisible(true);
    }//GEN-LAST:event_btnarchivedActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
                                     
    int row = jtable.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Please select a product first.");
        return;
    }

    String qtyText = txtquantity.getText().trim();
    if (qtyText.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a quantity.");
        return;
    }

    int quantity;
    try {
        quantity = Integer.parseInt(qtyText);
        if (quantity <= 0) {
            JOptionPane.showMessageDialog(this, "Quantity must be greater than 0.");
            return;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Enter a valid number for quantity.");
        return;
    }

    DefaultTableModel model = (DefaultTableModel) jtable.getModel();
    int productId = (int) model.getValueAt(row, 0);
    String productName = (String) model.getValueAt(row, 1);
    int availableQty = (int) model.getValueAt(row, 2);
    double price = (double) model.getValueAt(row, 3);

    if (quantity > availableQty) {
        JOptionPane.showMessageDialog(this, "Not enough stock available for " + productName);
        return;
    }

    // 🛒 Add to cart table
    DefaultTableModel cartModel = (DefaultTableModel) jcarttable.getModel();

    // check if product already in cart
    boolean found = false;
    for (int i = 0; i < cartModel.getRowCount(); i++) {
        int existingId = (int) cartModel.getValueAt(i, 0);
        if (existingId == productId) {
            int currentQty = (int) cartModel.getValueAt(i, 3);
            cartModel.setValueAt(currentQty + quantity, i, 3);
            found = true;
            break;
        }
    }

    if (!found) {
        cartModel.addRow(new Object[]{productId, productName, price, quantity});
    }

    // 🧾 Update total
    updateCartTotal();

    // clear field
    txtquantity.setText("");

    }//GEN-LAST:event_btnaddActionPerformed
private void confirmOrder() {
    if (cart.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No items ordered yet.");
        return;
    }

    try (Connection con = inventoryConnection.getCon()) {
        con.setAutoCommit(false);

        // prepare both update and insert for sales record
        String updateProductSQL = "UPDATE product SET quantity = quantity - ? WHERE product_id = ?";
        PreparedStatement updatePs = con.prepareStatement(updateProductSQL);

        String insertSaleSQL = "INSERT INTO sales (product_id, product_name, quantity, price, subtotal, sale_date) VALUES (?, ?, ?, ?, ?, NOW())";
        PreparedStatement salePs = con.prepareStatement(insertSaleSQL);

        for (CartItem item : cart) {
            // Deduct from product stock
            updatePs.setInt(1, item.quantity);
            updatePs.setInt(2, item.productId);
            updatePs.executeUpdate();

            // 🧾Record the sale
            double subtotal = item.price * item.quantity;
            salePs.setInt(1, item.productId);
            salePs.setString(2, item.productName);
            salePs.setInt(3, item.quantity);
            salePs.setDouble(4, item.price);
            salePs.setDouble(5, subtotal);
            salePs.executeUpdate();
        }

        // save all updates
        con.commit();

        JOptionPane.showMessageDialog(this, 
            "Order placed successfully!\nTotal: ₱" + String.format("%.2f", total),
            "Order Success", JOptionPane.INFORMATION_MESSAGE);

        // reset values
        cart.clear();
        total = 0;
        txttotal.setText("₱0.00");

        // refresh product list
        loadProducts();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error confirming order: " + e.getMessage());
        e.printStackTrace();
    }
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Purchase().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNCLOSE;
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnRestockhis;
    private javax.swing.JButton btnSalesrecord;
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnaddstock1;
    private javax.swing.JButton btnarchived;
    private javax.swing.JButton btnmanage;
    private javax.swing.JButton btnorder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jcarttable;
    private javax.swing.JTextField jlabelsearch;
    private javax.swing.JTable jtable;
    private javax.swing.JTextField txtquantity;
    private javax.swing.JLabel txttotal;
    private javax.swing.JLabel updateTotalLabel;
    // End of variables declaration//GEN-END:variables
}
