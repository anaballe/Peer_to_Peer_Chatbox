import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ChatServergui extends javax.swing.JFrame {

    static Socket s;
    static ServerSocket ss;
    static DataInputStream din;
    static DataOutputStream dout;
    
    public ChatServergui() {
        try {
            initComponents();
            getContentPane().setBackground(new Color(204,204,105));
            mysqlConnection = new MySQLConnection();
            String message = "";
            ResultSet rs = mysqlConnection.get("select * from client2");
            while(rs.next()){
                message = message + rs.getString("message");
            }
            msg_area.setText(message);
        } catch (SQLException ex) {
            Logger.getLogger(ChatServergui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        msg_area = new javax.swing.JTextArea();
        msg_text = new javax.swing.JTextField();
        msg_Send = new javax.swing.JButton();
        labelcon = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        msg_area.setEditable(false);
        msg_area.setColumns(20);
        msg_area.setFont(new java.awt.Font("Verdana", 2, 12)); // NOI18N
        msg_area.setForeground(new java.awt.Color(0, 0, 255));
        msg_area.setRows(5);
        Font font = new Font("Courier", Font.BOLD,12);
        msg_area.setFont(font);
        msg_area.setBorder(new javax.swing.border.MatteBorder(null));
        jScrollPane1.setViewportView(msg_area);

        msg_text.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        msg_text.setText("");
        msg_text.setFont(font);
        msg_text.setToolTipText("Enter the Message");
        msg_text.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        msg_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msg_textActionPerformed(evt);
            }
        });

        msg_Send.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        msg_Send.setForeground(new java.awt.Color(0, 153, 153));
        msg_Send.setText("Send");
        msg_Send.setToolTipText("Send");
        msg_Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msg_SendActionPerformed(evt);
            }
        });

        labelcon.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        labelcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelcon.setText("Connecting");
        labelcon.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(msg_Send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelcon, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(197, 197, 197))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelcon, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(msg_Send, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void msg_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msg_textActionPerformed

    }//GEN-LAST:event_msg_textActionPerformed

    private void msg_SendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msg_SendActionPerformed

        try{
            String msout="";
            msout = msg_text.getText().trim();
            String message = "\n Client2 Send:\t\t" + msout;
            msg_area.setText(msg_area.getText().trim() + message);
            dout.writeUTF(msout);
            String sql = "insert into client2 values(\""+message+"\");";
            //mysqlConnection.put(sql);
            //String sql = "insert into statement values(\"Client1 Send: "+msout+"\");";
            System.out.println("Rows Affected: "+mysqlConnection.put(sql));
            msg_text.setText("");
        }catch(Exception e){}
    }//GEN-LAST:event_msg_SendActionPerformed


    public static void main(String args[]) {
      
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChatServergui gui = new ChatServergui();
                gui.setVisible(true);
            }
        });
        String msgin="";
        try{
        ss = new ServerSocket(9999);
        s = ss.accept();
        labelcon.setText("Connected");
        
        din =new DataInputStream(s.getInputStream());
        dout = new DataOutputStream(s.getOutputStream());
        
        while(!msgin.equals("EXIT")){
            msgin = din.readUTF();
            
            System.out.println(msgin);
            String message = "\n Client1 recieve:\t"+msgin;
            msg_area.setText(msg_area.getText().trim()+message);
            String sql = "insert into client2 values(\""+message+"\");";
            System.out.println("Rows Affected: "+ mysqlConnection.put(sql));
            
        }
        
        }catch(Exception e){}
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private static javax.swing.JLabel labelcon;
    private javax.swing.JButton msg_Send;
    private static javax.swing.JTextArea msg_area;
    private javax.swing.JTextField msg_text;
    // End of variables declaration//GEN-END:variables
    private static MySQLConnection mysqlConnection;
}
