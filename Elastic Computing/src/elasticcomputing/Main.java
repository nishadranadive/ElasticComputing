/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticcomputing;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nisha
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Queue<Request> requestList;
    static volatile boolean isRunning = false;
    public Queue<Request> completedReqList;
    Thread requestGeneratorThread;
    Thread dispatcherThread;
    Logger logger;
    static int arrivalRate;
    static int processingTime;
    static int loadBalancingFactor;
    RequestQueueGenerator rtg;
    Dispatcher dsp;
    Timer timer;
    static int maxService;
    static  int serviceNumber;

    //static Queue<Service> servStaticQueue ;
    public Main() throws IOException {
        initComponents();
        btnChange.setEnabled(false);
        btnStop.setEnabled(false);
        //tmain = new Thread(this);
        requestList = new LinkedList<>();
        completedReqList = new LinkedList<>();
        logger = Logger.getLogger("MyLog");
        FileHandler fh; // log file
        //servStaticQueue = new LinkedList<>();
        try {
            // This block configure the logger with handler and formatter  
            System.out.println(new File(".").getCanonicalPath() + "\\" + "logs.log");
            fh = new FileHandler(new File(".").getCanonicalPath() + "\\" + "logs.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages  
            // logger.info("My first log");
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ControlPanel = new javax.swing.JPanel();
        btnStart = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnStop = new javax.swing.JButton();
        txtArrivalRate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtProcessingTime = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtLoadBalancing = new javax.swing.JTextField();
        btnChange = new javax.swing.JButton();
        txtServices = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        RequestPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableRequests = new javax.swing.JTable();
        ServiceAvgSpeedPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableServiceAvgSpeed = new javax.swing.JTable();
        lblDisatcherQueue = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(245, 249, 250));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jLabel1.setText("Elastic Computing Simulation");

        ControlPanel.setBackground(new java.awt.Color(255, 255, 255));

        btnStart.setBackground(new java.awt.Color(0, 153, 0));
        btnStart.setForeground(java.awt.Color.white);
        btnStart.setText("Start Simulation");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        jLabel2.setText("Arrival Rate");

        btnStop.setBackground(new java.awt.Color(204, 51, 0));
        btnStop.setForeground(java.awt.Color.white);
        btnStop.setText("Stop Simulation");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        jLabel3.setText("Processing Rate");

        jLabel4.setText("Load Balancing < 50");

        btnChange.setBackground(new java.awt.Color(0, 102, 204));
        btnChange.setForeground(java.awt.Color.white);
        btnChange.setText("Change");
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });

        jLabel5.setText("No. of Services < 10");

        javax.swing.GroupLayout ControlPanelLayout = new javax.swing.GroupLayout(ControlPanel);
        ControlPanel.setLayout(ControlPanelLayout);
        ControlPanelLayout.setHorizontalGroup(
            ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ControlPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtArrivalRate, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtProcessingTime, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(47, 47, 47)
                .addGroup(ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtLoadBalancing))
                .addGap(31, 31, 31)
                .addGroup(ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtServices))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnChange, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnStop, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnStart, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(44, 44, 44))
        );
        ControlPanelLayout.setVerticalGroup(
            ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ControlPanelLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ControlPanelLayout.createSequentialGroup()
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ControlPanelLayout.createSequentialGroup()
                        .addGroup(ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtArrivalRate, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProcessingTime, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLoadBalancing, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnChange, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(ControlPanelLayout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addComponent(txtServices, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(19, 19, 19))
        );

        jLabel2.getAccessibleContext().setAccessibleName("lblArrivalRate");

        RequestPanel.setBackground(new java.awt.Color(255, 255, 255));
        RequestPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Request Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 102, 204))); // NOI18N

        tableRequests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Request", "Completion Time", "Service"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableRequests.setColumnSelectionAllowed(true);
        tableRequests.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableRequests.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableRequests);
        tableRequests.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tableRequests.getColumnModel().getColumnCount() > 0) {
            tableRequests.getColumnModel().getColumn(0).setResizable(false);
            tableRequests.getColumnModel().getColumn(0).setHeaderValue("Request");
            tableRequests.getColumnModel().getColumn(1).setResizable(false);
            tableRequests.getColumnModel().getColumn(1).setHeaderValue("Completion Time");
            tableRequests.getColumnModel().getColumn(2).setResizable(false);
            tableRequests.getColumnModel().getColumn(2).setHeaderValue("Service");
        }

        javax.swing.GroupLayout RequestPanelLayout = new javax.swing.GroupLayout(RequestPanel);
        RequestPanel.setLayout(RequestPanelLayout);
        RequestPanelLayout.setHorizontalGroup(
            RequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
        );
        RequestPanelLayout.setVerticalGroup(
            RequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RequestPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );

        ServiceAvgSpeedPanel.setBackground(new java.awt.Color(255, 255, 255));
        ServiceAvgSpeedPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Service Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 102, 204))); // NOI18N

        tableServiceAvgSpeed.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Service", "Queue", "Avg Speed Processed", "Requests Processed"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableServiceAvgSpeed.setColumnSelectionAllowed(true);
        tableServiceAvgSpeed.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableServiceAvgSpeed.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tableServiceAvgSpeed);
        tableServiceAvgSpeed.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tableServiceAvgSpeed.getColumnModel().getColumnCount() > 0) {
            tableServiceAvgSpeed.getColumnModel().getColumn(0).setResizable(false);
            tableServiceAvgSpeed.getColumnModel().getColumn(1).setResizable(false);
            tableServiceAvgSpeed.getColumnModel().getColumn(2).setResizable(false);
            tableServiceAvgSpeed.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout ServiceAvgSpeedPanelLayout = new javax.swing.GroupLayout(ServiceAvgSpeedPanel);
        ServiceAvgSpeedPanel.setLayout(ServiceAvgSpeedPanelLayout);
        ServiceAvgSpeedPanelLayout.setHorizontalGroup(
            ServiceAvgSpeedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ServiceAvgSpeedPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        ServiceAvgSpeedPanelLayout.setVerticalGroup(
            ServiceAvgSpeedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ServiceAvgSpeedPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );

        lblDisatcherQueue.setText("Dispatcher Queue:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ControlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(ServiceAvgSpeedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(387, 387, 387)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(RequestPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(lblDisatcherQueue, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ServiceAvgSpeedPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 287, Short.MAX_VALUE)
                    .addComponent(RequestPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 287, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(lblDisatcherQueue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(ControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        // TODO add your handling code here:
        //  JOptionPane.showMessageDialog(null,txtArrivalRate.getText().trim());

        isRunning = true;
        //JOptionPane.showMessageDialog(null, isRunning);
        if (isRunning) {
            if (!txtArrivalRate.getText().trim().matches("[0-9]+")
                    || !txtProcessingTime.getText().trim().matches("[0-9]+")
                    || !txtLoadBalancing.getText().trim().matches("[0-9]+")
                    || !(Integer.parseInt(txtProcessingTime.getText().trim()) > 0)
                    || !(Integer.parseInt(txtLoadBalancing.getText().trim()) > 0 && Integer.parseInt(txtLoadBalancing.getText().trim()) < 50)
                    || !(Integer.parseInt(txtArrivalRate.getText().trim()) > 0)
                    || !(Integer.parseInt(txtServices.getText().trim()) > 0 && Integer.parseInt(txtServices.getText().trim()) < 10)) {  //After OK it still gives an error(to be solved)
                JOptionPane.showMessageDialog(null, "please enter valid values (Only Integers allowed)");
                txtArrivalRate.setText("");
                txtProcessingTime.setText("");
                txtLoadBalancing.setText("");
                txtServices.setText("");
                isRunning = false;
                return;
            }
            btnStop.setEnabled(true);
            btnChange.setEnabled(true);
            btnStart.setEnabled(false);
            int a = Integer.parseInt(txtArrivalRate.getText().trim());
            arrivalRate = a;
            maxService = Integer.parseInt(txtServices.getText().trim());
            processingTime = Integer.parseInt(txtProcessingTime.getText().trim());
            loadBalancingFactor = Integer.parseInt(txtLoadBalancing.getText().trim());
            logger.info("arrival rate:" + "\t" + a);

            rtg = new RequestQueueGenerator(a, processingTime, requestList, loadBalancingFactor, logger, completedReqList);
            requestGeneratorThread = new Thread(rtg);
            dsp = Dispatcher.getInstance(requestList, logger, loadBalancingFactor, completedReqList);//new Dispatcher(requestList, logger, isRunning, loadBalancingFactor, completedReqList);
            dispatcherThread = new Thread(dsp);
            // tmain.start();
            logger.fine("Main thread started");
            startDetailTimer();
        }

        //dispatcher.stop();
//        } else {
//            requestGeneratorThread.interrupt();
//        }
        requestGeneratorThread.start();
        dispatcherThread.start();
//        jLabel1.setText("Processing");
    }//GEN-LAST:event_btnStartActionPerformed

    public void startDetailTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                detail();
            }
        }, 1000, 1000);
    }

    synchronized public void detail() {
        int noOfservers = dsp.serviceList.size();// Service.getInstance().getServersInProccess();// get no of servers in use
        int reqInQueue = requestList.size();//Dispatcher.queue.size();
        int inProc = dsp.getInProcCount();//get requests in process
        // int proc = Service.getInstance().getProcessedCount();//get processed requests
        int[] serverSizes = dsp.getServerSizes();
        List<Service> allServices = dsp.getServiceList();

        long avgProcTime = 0;//Service.getInstance().getAvgProccessTime();
        updateDetails(noOfservers, reqInQueue, inProc, avgProcTime, serverSizes, allServices);
//                updateDetails(noOfservers, reqInQueue, inProc, avgProcTime, serverSizes);

    }

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        // TODO add your handling code here:
        btnStop.setEnabled(false);
        btnChange.setEnabled(false);
        isRunning = false;
        if (!isRunning) {
            //System.out.println("service capacity" + dsp.isSerEmpty);
            dsp.stopService();
        }
        if (!isRunning && dsp.isSerEmpty) {
            logger.warning("while is false");
            JOptionPane.showMessageDialog(null, "stopped");
            //webserver.interrupt();
            System.out.println("assignment2.Main.run() halted");
            logger.info("Main thread stopped");
            dispatcherThread.interrupt();
            dsp.stopDispatchingRequests();
            //tmain.interrupt();
        }
        btnStart.setEnabled(true);
    }//GEN-LAST:event_btnStopActionPerformed

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        // TODO add your handling code here:
//        btnChange.setEnabled(false);
        arrivalRate = Integer.parseInt(txtArrivalRate.getText().trim());
        processingTime = Integer.parseInt(txtProcessingTime.getText().trim());
        System.out.println("updated values" + arrivalRate + " ----  " + processingTime);

        rtg.arrivalRate = arrivalRate;
        rtg.processingTime = processingTime;
        rtg.changePeriod();
    }//GEN-LAST:event_btnChangeActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void updateDetails(int serCount, int QueueCount, int reqInProc, long avgProcTime, int[] serverSizes, List<Service> allServices) {
//        public void updateDetails(int serCount, int QueueCount, int reqInProc, long avgProcTime, int[] serverSizes) {

        lblDisatcherQueue.setText(" No of requests in Dispatcher Queue: " + QueueCount);

        // request details table
        DefaultTableModel model = (DefaultTableModel) tableRequests.getModel();
        Object[] row = new Object[3];
        model.setRowCount(0);

        synchronized (completedReqList) {

            for (Request r : completedReqList) {
                if (r.isDone == true) {
                    row[0] = r.getRequest_Name();
                    row[1] = r.getCompletionTime();
                    row[2] = r.serviceName;
                    model.addRow(row);
                } else {

                    long endTime = System.nanoTime();
                    double timeElapsed = endTime - (r.getStartTime());
                    timeElapsed = timeElapsed / 1000000000; // in secs
                    System.out.println(timeElapsed + " [[[[[[[[[[[[[[[[[[ Elaspsed time in secs in Main]]]]]]]]]]]]]]]]]]" + r.serviceName);
                    r.completionTime = timeElapsed;
                    r.isDone = true;

                }
            }
        }

        //service avg time table
        DefaultTableModel modelService = (DefaultTableModel) tableServiceAvgSpeed.getModel();
        Object[] rowService = new Object[4];
        modelService.setRowCount(0);

        for (Service s : allServices) {
            rowService[0] = s.serviceName;
            rowService[1] = s.serReqQueue.size();
            rowService[2] = s.avgRate;
            rowService[3] = s.requestCount;
            modelService.addRow(rowService);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ControlPanel;
    private javax.swing.JPanel RequestPanel;
    private javax.swing.JPanel ServiceAvgSpeedPanel;
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblDisatcherQueue;
    private javax.swing.JTable tableRequests;
    private javax.swing.JTable tableServiceAvgSpeed;
    private javax.swing.JTextField txtArrivalRate;
    private javax.swing.JTextField txtLoadBalancing;
    private javax.swing.JTextField txtProcessingTime;
    private javax.swing.JTextField txtServices;
    // End of variables declaration//GEN-END:variables

}

// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
