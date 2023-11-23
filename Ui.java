import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.Arrays;



class Ui extends JFrame{
    private OrderList orderList;
   
    Ui(OrderList orderList){
        this.orderList=orderList;
    }

    public void mainMenu(boolean isVisible){
        JFrame mainFrame=new JFrame("main");
        mainFrame.setSize(600,600);
        mainFrame.setLayout(new BorderLayout());

        JLabel heading=new JLabel("Fashion Shop",0);
        Font font = new Font("Arial", Font.BOLD, 24);
        heading.setFont(font);
    
        mainFrame.add(
            "North",
            heading
        );

        //button set
        JPanel btnPanel=new JPanel(
            new GridLayout(5,1)
        );

        String [] btnHeading={
            "Search",
            "Status",
            "Reports",
            "Delete",
            "Place Order"
        };

        JButton [] btnArray=new JButton[5];
        for(int i=0; i<btnArray.length; i++){
            btnArray[i]=new JButton(btnHeading[i]);
            btnPanel.add(btnArray[i]);
        }

        //btn handle
        //placeOrderBtn
        btnArray[4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                placeOrder(true);
                mainFrame.setVisible(false);
            }
        });
        btnArray[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchOptions(true);
            }
        });
        btnArray[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteOrder(true);
                mainFrame.setVisible(false);
            }
        });
        btnArray[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeStatus(true);
                mainFrame.setVisible(false);
            }
        });
        btnArray[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewReports(true);
                mainFrame.setVisible(false);
            }
        });
        
        mainFrame.add("Center",btnPanel);
        mainFrame.setVisible(isVisible);
    }

    public void placeOrder(boolean isVisible){
        String id=OrderIDGenerator.generateOrderID();
        OrderIDGenerator.cancelOrderId();

        JFrame mainFrame=new JFrame(
            "Place Order"
        );
        mainFrame.setSize(600, 600);
        mainFrame.setLayout(new BorderLayout());

        JButton backBtn=new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainMenu(true);
                mainFrame.setVisible(false);
            }
        });
        mainFrame.add(
            "North",
            backBtn
        );

        JPanel orderIdPanel=new JPanel(new GridLayout(1,2));
        JLabel orderID=new JLabel("Order Id");
        JLabel orderId=new JLabel(id);
        orderIdPanel.add(orderID);
        orderIdPanel.add(orderId);

        JPanel customerIdPanel=new JPanel(new GridLayout(1,2));
        JLabel customerID=new JLabel("Customer ID ");
        JTextField customerId=new JTextField();
        customerIdPanel.add(customerID);
        customerIdPanel.add(customerId);

        JPanel sizePanel=new JPanel(new GridLayout(1,2));
        JLabel Size=new JLabel("Size ");
        JTextField size=new JTextField();
        sizePanel.add(Size);
        sizePanel.add(size);
        
        JPanel qtyPanel=new JPanel(new GridLayout(1,2));
        JLabel Qty=new JLabel("Qty");
        JTextField qty=new JTextField();
        qtyPanel.add(Qty);
        qtyPanel.add(qty);

        JPanel inputTbl=new JPanel(new GridLayout(4,1,0,30));
        inputTbl.add(orderIdPanel);
        inputTbl.add(customerIdPanel);
        inputTbl.add(sizePanel);
        inputTbl.add(qtyPanel);

        mainFrame.add(
            "Center",
            inputTbl
        );
        JButton placeBtn=new JButton("Place");
        placeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String id=OrderIDGenerator.generateOrderID();
                String customerID=customerId.getText();
                String Size=size.getText();
                String Qty=qty.getText();

                orderList.add(id, customerID, Size, Qty);
                orderList.print();

                JOptionPane.showMessageDialog(
                    mainFrame,
                    "Order Placed!", 
                    "Alert", 
                    JOptionPane.INFORMATION_MESSAGE
                );
                mainFrame.setVisible(false);
                placeOrder(true);

            }
        });
        mainFrame.add(
            "South",
            placeBtn
        );
        mainFrame.setVisible(isVisible);
    }

    public void searchOptions(boolean isVisible){
        JFrame mainFrame=new JFrame(
            "Search Options"
        );
        mainFrame.setSize(400, 150);
        mainFrame.setLayout(new GridLayout(2,1));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel heading=new JLabel(
            "Please Select the Option",
            0
        );

        JPanel btnsPanel=new JPanel(new GridLayout(1,3,30,0));
        JButton searchCustomerBtn=new JButton(
            "Search Customer"
        );
        searchCustomerBtn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    searchCustomer(true);
                    mainFrame.setVisible(false);
                }
            }
        );

        JButton searchOrderBtn=new JButton(
            "Search Order"
        );
        searchOrderBtn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    searchOrder(true);
                    mainFrame.setVisible(false);
                }
            }
        );
        JButton cancelBtn=new JButton(
            "Cancel"
        );
        cancelBtn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mainFrame.setVisible(false);
                }
            }
        );

        btnsPanel.add(searchCustomerBtn);
        btnsPanel.add(searchOrderBtn);
        btnsPanel.add(cancelBtn);

        mainFrame.add(heading);
        mainFrame.add(btnsPanel);
        mainFrame.setVisible(isVisible);
    }
    
    public void searchCustomer(boolean isVisible){

        JFrame mainFrame=new JFrame(
            "Search Customer"
        );
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new BorderLayout());
        //mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton backBtn=new JButton("Back");
        backBtn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    searchOptions(true);
                    mainFrame.setVisible(false);
                }
            }
        );
        mainFrame.add(
            "North",
            backBtn
        );

        JPanel customerTblPanel=new JPanel();
        customerTblPanel.setLayout(new BorderLayout());

        JPanel layer1=new JPanel();
        layer1.setLayout(new GridLayout(1,3));
        JLabel customerId=new JLabel("Enter Customer Id ");
        JTextField customerIdTxtFld=new JTextField();
        JButton searchBtn=new JButton("Search");
        
        layer1.add(customerId);
        layer1.add(customerIdTxtFld);
        layer1.add(searchBtn);
        
        customerTblPanel.add(
            "North",
            layer1
        );
        
        DefaultTableModel dTableModel;
        

        String [] tableHeadings={
            "Size", 
            "Qty",
            "Amount"
        };

        dTableModel=new DefaultTableModel(tableHeadings,0);
        dTableModel.addRow(new String []{"M","S", "L"});
        JTable jTable=new JTable(dTableModel);

        customerTblPanel.add(
            "Center",
            jTable
        );
        JLabel totalLabel=
        new JLabel();
        String phn=customerIdTxtFld.getText();
        String totalAmount=
        " total     "+orderList.totalAmount(phn);
        totalLabel.setText(totalAmount);
        mainFrame.add(
            "South",
            totalLabel
        );

        mainFrame.add(
            "Center",
            customerTblPanel
        );
        mainFrame.setVisible(isVisible);

        //filter data
        OrderList orderListByPhn=
            orderList.searchByPhone(customerIdTxtFld.getText());
        String [] sizes={
            "XS", "S", "M", "L", "XL", "XXL"
        };
        for(int i=0; i<sizes.length; i++){
            dTableModel.addRow(
                new String []{
                    sizes[i],
                    orderListByPhn.items(sizes[i])+"",
                    orderListByPhn.amount(
                        sizes[i],
                        orderListByPhn.items(sizes[i])
                    )+""
                }
            );
        }
        searchBtn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dTableModel.setRowCount(0);
                    dTableModel.addRow(new String []{"M","S", "L"});
                    OrderList orderListByPhn=
                    orderList.searchByPhone(customerIdTxtFld.getText());
                    String [] sizes={
                        "XS", "S", "M", "L", "XL", "XXL"
                    };
                    for(int i=0; i<sizes.length; i++){
                        dTableModel.addRow(
                            new String []{
                                sizes[i],
                                orderListByPhn.items(sizes[i])+"",
                                orderListByPhn.amount(
                                    sizes[i],
                                    orderListByPhn.items(sizes[i])
                                )+""
                            }
                        );
                    }

                    String phn=customerIdTxtFld.getText();
                    String totalAmount=
                    " total     "+orderList.totalAmount(phn);
                    totalLabel.setText(totalAmount);
                }
            }
        );


    }

    public void searchOrder(boolean isVisible){
        JFrame mainFrame=new JFrame(
            "Search Order"
        );
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new BorderLayout());
        //mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //backButton
        JButton backBtn=new JButton("Back");
        backBtn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    searchOptions(true);
                    mainFrame.setVisible(false);
                }
            }
        );

        //layer 1
        JPanel layer1=new JPanel(new BorderLayout());
        
        //searchPannel
        JPanel searchPanel=new JPanel(new GridLayout(1,3));

        JLabel enterOrderId=new JLabel("Enter Order ID : ");
        JTextField enterOrderIdTxtFld=new JTextField();
        JButton searchBtn=new JButton("Search");

        

        searchPanel.add(enterOrderId);
        searchPanel.add(enterOrderIdTxtFld);
        searchPanel.add(searchBtn);

        //detailsForm
        JPanel formPanel=new JPanel(new GridLayout(5,1)); 
        JLabel 
            customerId=new JLabel("Customer ID : "),
            size=new JLabel("Size : "),
            qty=new JLabel("Qty : "),
            amount=new JLabel("Amount : "),
            status=new JLabel("Status : ");

        formPanel.add(customerId);
        formPanel.add(size);
        formPanel.add(qty);
        formPanel.add(amount);
        formPanel.add(status);
        
        layer1.add(
            "Center",
            formPanel
        );

         layer1.add(
            "North",
            searchPanel
        );

         mainFrame.add(
            "North",
            backBtn
        );
        mainFrame.add(
            "Center",
            layer1
        );

        //orderidtxtFiled
        enterOrderIdTxtFld.addKeyListener(
            new KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    validateInputId(enterOrderIdTxtFld.getText());
                }
            }
        );

        //search Btn

        searchBtn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    String id=enterOrderIdTxtFld.getText();

                    if(!id.isBlank()){

                        if(orderList.isOrderIdOntheList(id)){

                            int index=orderList.
                            search(enterOrderIdTxtFld.getText());

                            OrderList.Orders order=orderList.get(index);
                            customerId.setText(
                                "Customer Id : "+
                                order.info("id")
                            );
                            size.setText(
                                "Size : "+
                                order.info("size")
                            );
                            qty.setText(
                                "qty : "+
                                order.info("qty")
                            );
                            amount.setText(
                                "Amount : "+
                                order.info("amount")
                            );
                            status.setText(
                                "Customer Id : "+
                                order.info("status")
                            );      
                        }
                        
                    }
                    enterOrderIdTxtFld.setText("");
                }
            }
        );

        mainFrame.setVisible(isVisible);
    }
    
    public void viewReports(boolean isVisible){
        JFrame mainFrame=new JFrame(
            "View Reports"
        );
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new BorderLayout());

        JButton backBtn=new JButton("Back");
        backBtn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mainMenu(true);
                    mainFrame.setVisible(false);
                }
            }
        );
        mainFrame.add(
            "North",
            backBtn
        );

        JPanel layer1=new JPanel(new GridLayout(1,3));
            JPanel col1=new JPanel(new GridLayout(3,1));
                JButton col1Btn1=new JButton("View Customers");
                JButton col1Btn2=new JButton("Best In Customers");
                JButton col1Btn3=new JButton("All Customers");
            col1.add(col1Btn1);
            col1.add(col1Btn2);
            col1.add(col1Btn3);

            JPanel col2=new JPanel(new GridLayout(2,1));
                JButton col2Btn1=new JButton("Catergorized By Qty");
                JButton col2Btn2=new JButton("Catergorized By Amount");
            col2.add(col2Btn1);
            col2.add(col2Btn2);

            JPanel col3=new JPanel(new GridLayout(2,1));
                JButton col3Btn1=new JButton("Orders By Amount");
                JButton col3Btn2=new JButton("All Orders");
                    col3.add(col3Btn1);
                    col3.add(col3Btn2);

        layer1.add(col1);
        layer1.add(col2);
        layer1.add(col3);

        mainFrame.add(
            "Center",
            layer1
        );
        //bestInCustomers 
        col1Btn1.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mainFrame.setVisible(false);
                    viewCustomers(true);
                }
            }
        );
        col1Btn2.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mainFrame.setVisible(false);
                    bestInCustomers(true);
                }
            }
        );
        col1Btn3.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mainFrame.setVisible(false);
                    allCustomerReports(true);
                }
            }
        );
        col2Btn1.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mainFrame.setVisible(false);
                    itemsByQty(true);
                }
            }
        );
        col2Btn2.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mainFrame.setVisible(false);
                    itemsByAmount(true);
                }
            }
        );
        col3Btn1.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mainFrame.setVisible(false);
                    allOrdersByAmount(true);
                }
            }
        );
        col3Btn2.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mainFrame.setVisible(false);
                    allOrders(true);
                }
            }
        );
        mainFrame.setVisible(isVisible);
    }
    
    public void bestInCustomers(boolean isVisible){
        Reports reports=new Reports(orderList);
        JFrame mainFrame=new JFrame(
            "Best In Customers"
        );
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new BorderLayout());

        JButton backBtn=new JButton("Back");
        backBtn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewReports(true);
                    mainFrame.setVisible(false);
                }
            }
        );
        mainFrame.add(
            "North",
            backBtn
        );
        
        DefaultTableModel dTableModel;
        

        String [] tableHeadings={
            "Size", 
            "Qty",
            "Amount"
        };

        dTableModel=new DefaultTableModel(tableHeadings,0);
        dTableModel.addRow(new String []{
            "Customer ID","Qty", "Amount"
        });
        JTable jTable=new JTable(dTableModel);

        String [] phnArr=reports.
            sortedPhoneNumbersByAllQtyAndAmount(
                orderList.phoneNumberArray()
            );
        
        for(int i=0; i<phnArr.length; i++){
            String phn=phnArr[i];
            int qty=orderList.allQty(phn);
            double amount=orderList.totalAmount(phn);
            String [] row={
                phn,
                qty+"",
                amount+""
            };
            dTableModel.addRow(row);
        }
         mainFrame.add(
            "Center",
            jTable
        );
        
        mainFrame.setVisible(isVisible);
    }
    
    public void viewCustomers(boolean isVisible){
        JFrame mainFrame=new JFrame(
            "View Customers"
        );
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new BorderLayout());

        JButton backBtn=new JButton("Back");
        backBtn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewReports(true);
                    mainFrame.setVisible(false);
                }
            }
        );
        mainFrame.add(
            "North",
            backBtn
        );
        
        DefaultTableModel dTableModel;
        

        String [] tableHeadings={
            "Size", 
            "Qty",
            "Amount"
        };

        dTableModel=new DefaultTableModel(tableHeadings,0);
        dTableModel.addRow(new String []{
            "Customer ID","Qty", "Amount"
        });
        JTable jTable=new JTable(dTableModel);

        String [] phnArr=orderList.phoneNumberArray();

        for(int i=0; i<phnArr.length; i++){
            String phn=phnArr[i];
            int qty=orderList.allQty(phn);
            double amount=orderList.totalAmount(phn);
            String [] row={
                phn,
                qty+"",
                amount+""
            };
            dTableModel.addRow(row);
        }
         mainFrame.add(
            "Center",
            jTable
        );
        
        mainFrame.setVisible(isVisible);
    }
    
    public void itemsByQty(boolean isVisible){
        JFrame mainFrame=new JFrame(
            "Items By Qty"
        );
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new BorderLayout());

        JButton backBtn=new JButton("Back");
        backBtn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewReports(true);
                    mainFrame.setVisible(false);
                }
            }
        );
        mainFrame.add(
            "North",
            backBtn
        );
        
        DefaultTableModel dTableModel;
        

        String [] tableHeadings={
            "Size", 
            "Qty",
            "Amount"
        };

        dTableModel=new DefaultTableModel(tableHeadings,0);
        dTableModel.addRow(new String []{
            "Size", 
            "Qty",
            "Amount"
        });
        JTable jTable=new JTable(dTableModel);
        String [] sizeAndQty=new String[]{
            "XS-"+orderList.items("XS"),
            "S-"+orderList.items("S"),
            "M-"+orderList.items("M"),
            "L-"+orderList.items("L"),
            "XL-"+orderList.items("XL"),
            "XXL-"+orderList.items("XXL")
        };
        sizeAndQty=new Reports(orderList).
            bubbleSortForSizes(sizeAndQty);
        
        for(int i=0; i<sizeAndQty.length; i++){
            String size=sizeAndQty[i].split("-")[0];
            String qty=sizeAndQty[i].split("-")[1];
            String amount=orderList.amount(
                size, 
                Integer.parseInt(qty)
            )+"";
            dTableModel.addRow(
                new String []{
                    size,
                    qty,
                    amount
                }
            );
        }
       
         mainFrame.add(
            "Center",
            jTable
        );
        
        mainFrame.setVisible(isVisible);
    }
    
    public void itemsByAmount(boolean isVisible){
        JFrame mainFrame=new JFrame(
            "Items By Amount"
        );
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new BorderLayout());

        JButton backBtn=new JButton("Back");
        backBtn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewReports(true);
                    mainFrame.setVisible(false);
                }
            }
        );
        mainFrame.add(
            "North",
            backBtn
        );
        
        DefaultTableModel dTableModel;
        

        String [] tableHeadings={
            "Size", 
            "Qty",
            "Amount"
        };

        dTableModel=new DefaultTableModel(tableHeadings,0);
        dTableModel.addRow(new String []{
            "Size", 
            "Qty",
            "Amount"
        });
        JTable jTable=new JTable(dTableModel);
        String [] sizeQtyAndAmount=new String[]{
            "XS-"+orderList.items("XS")+"-"
            +orderList.amount("XS", orderList.items("XS")),
            "S-"+orderList.items("S")+"-"
            +orderList.amount("S", orderList.items("S")),
            "M-"+orderList.items("M")+"-"
            +orderList.amount("M", orderList.items("M")),
            "L-"+orderList.items("L")+"-"
            +orderList.amount("L", orderList.items("L")),
            "XL-"+orderList.items("XL")+"-"
            +orderList.amount("XL", orderList.items("XL")),
            "XXL-"+orderList.items("XXL")+"-"
            +orderList.amount("XXL", orderList.items("XXL"))
        };
        sizeQtyAndAmount=new Reports(orderList)
            .bubbleSortForAmount(sizeQtyAndAmount);
        
        mainFrame.add(
            "Center",
            jTable
        );
        for(int i=0; i<sizeQtyAndAmount.length; i++){
            String size=sizeQtyAndAmount[i].split("-")[0];
            String qty=sizeQtyAndAmount[i].split("-")[1];
            String amount=sizeQtyAndAmount[i].split("-")[2];
            dTableModel.addRow(
                new String [] {
                    size,
                    qty,
                    amount
                }
            );
        }
        
        mainFrame.setVisible(isVisible);
    }
    
    public void allCustomerReports(boolean isVisible){
        JFrame mainFrame=new JFrame(
            "All Customer Reports"
        );
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new BorderLayout());

        JButton backBtn=new JButton("Back");
        backBtn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewReports(true);
                    mainFrame.setVisible(false);
                }
            }
        );
        mainFrame.add(
            "North",
            backBtn
        );
        
        DefaultTableModel dTableModel;
        

        String [] tableHeadings={
            "Customer ID",
            "XS",
            "S",
            "M",
            "L",
            "XL",
            "XXL",
            "Amount"
        };

        dTableModel=new DefaultTableModel(tableHeadings,0);
        dTableModel.addRow(new String []{
            "Customer ID",
            "XS",
            "S",
            "M",
            "L",
            "XL",
            "XXL",
            "Amount"
        });

        JTable jTable=new JTable(dTableModel);

        String [] phnArr=orderList.phoneNumberArray();

        for(int i=0; i<phnArr.length; i++){
            String phn=phnArr[i];
            OrderList orderListByPhn=
                orderList.searchByPhone(phn);
            int xs=orderListByPhn.items("XS");
            int s=orderListByPhn.items("S");
            int m=orderListByPhn.items("M");
            int l=orderListByPhn.items("L");
            int xl=orderListByPhn.items("XL");
            int xxl=orderListByPhn.items("XXL");
            double amount=orderList.totalAmount(phn);
            String [] row={
                phn,
                xs+"",
                s+"",
                m+"",
                l+"",
                xl+"",
                xxl+"",
                amount+""
            };
            dTableModel.addRow(row);
        }
         mainFrame.add(
            "Center",
            jTable
        );
        
        mainFrame.setVisible(isVisible);
    }
    
    public void allOrdersByAmount(boolean isVisible){
        JFrame mainFrame=new JFrame(
            "All Orders"
        );
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new BorderLayout());

        JButton backBtn=new JButton("Back");
        backBtn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewReports(true);
                    mainFrame.setVisible(false);
                }
            }
        );
        mainFrame.add(
            "North",
            backBtn
        );
        
        DefaultTableModel dTableModel;
        

        String [] tableHeadings={
            "Order ID",
            "Customer ID",
            "Size",
            "Qty",
            "Amount",
            "Status"
        };

        dTableModel=new DefaultTableModel(tableHeadings,0);
        dTableModel.addRow(new String []{
           "Order ID",
            "Customer ID",
            "Size",
            "Qty",
            "Amount",
            "Status"
        });

        JTable jTable=new JTable(dTableModel);

        String [] ordersIdArr=new Reports(orderList).
            sortedOrderIdByAmount();
        
        for(int i=0; i<ordersIdArr.length; i++){
            int index=orderList.search(ordersIdArr[i]);
            OrderList.Orders order=
            orderList.get(index);
            String phone=order.info("phone");
            String size=order.info("size");
            String qty=order.info("qty");
            String status=order.info("status");
            String amount=order.info("amount");
            dTableModel.addRow(
                new String [] {
                    ordersIdArr[i], 
                    phone, 
                    size, 
                    qty, 
                    amount, 
                    status
                }
            );
        }

         mainFrame.add(
            "Center",
            jTable
        );
        
        mainFrame.setVisible(isVisible);
    }
    
    public void allOrders(boolean isVisible){
        JFrame mainFrame=new JFrame(
            "All Orders"
        );
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new BorderLayout());

        JButton backBtn=new JButton("Back");
        backBtn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewReports(true);
                    mainFrame.setVisible(false);
                }
            }
        );
        mainFrame.add(
            "North",
            backBtn
        );
        
        DefaultTableModel dTableModel;
        

        String [] tableHeadings={
            "Order ID",
            "Customer ID",
            "Size",
            "Qty",
            "Amount",
            "Status"
        };

        dTableModel=new DefaultTableModel(tableHeadings,0);
        dTableModel.addRow(new String []{
           "Order ID",
            "Customer ID",
            "Size",
            "Qty",
            "Amount",
            "Status"
        });

        JTable jTable=new JTable(dTableModel);

        String [] ordersArr=orderList.orderIdArray();

        for(int i=0; i<ordersArr.length; i++){
            int index=orderList.search(ordersArr[i]);
            OrderList.Orders order=
            orderList.get(index);
            String phone=order.info("phone");
            String size=order.info("size");
            String qty=order.info("qty");
            String status=order.info("status");
            String amount=order.info("amount");
            dTableModel.addRow(
                new String []{
                    ordersArr[i], 
                    phone, 
                    size,
                    qty, 
                    amount, 
                    status
                }
            );
        }
         mainFrame.add(
            "Center",
            jTable
        );
        
        mainFrame.setVisible(isVisible);
    }
    
    public void deleteOrder(boolean isVisible){
        JFrame mainFrame=new JFrame(
            "Search Order"
        );
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new BorderLayout());
        //mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //backButton
        JButton backBtn=new JButton("Back");
        backBtn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mainMenu(true);
                    mainFrame.setVisible(false);
                }
            }
        );

        //layer 1
        JPanel layer1=new JPanel(new BorderLayout());
        
        //searchPannel
        JPanel searchPanel=new JPanel(new GridLayout(1,3));

        JLabel enterOrderId=new JLabel("Enter Order ID : ");
        JTextField enterOrderIdTxtFld=new JTextField();
        JButton searchBtn=new JButton("Search");

        

        searchPanel.add(enterOrderId);
        searchPanel.add(enterOrderIdTxtFld);
        searchPanel.add(searchBtn);

        //detailsForm
        JPanel formPanel=new JPanel(new GridLayout(5,1)); 
        JLabel 
            customerId=new JLabel("Customer ID : "),
            size=new JLabel("Size : "),
            qty=new JLabel("Qty : "),
            amount=new JLabel("Amount : "),
            status=new JLabel("Status : ");

        formPanel.add(customerId);
        formPanel.add(size);
        formPanel.add(qty);
        formPanel.add(amount);
        formPanel.add(status);
        
        layer1.add(
            "Center",
            formPanel
        );

         layer1.add(
            "North",
            searchPanel
        );

         mainFrame.add(
            "North",
            backBtn
        );
        mainFrame.add(
            "Center",
            layer1
        );

        //search Btn
        String [] ID={"-1"};
        searchBtn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ID[0]=enterOrderIdTxtFld.getText();
                    String id=ID[0];
                    if(!id.isBlank()){

                        if(orderList.isOrderIdOntheList(id)){

                            int index=orderList.
                            search(enterOrderIdTxtFld.getText());

                            OrderList.Orders order=orderList.get(index);
                            customerId.setText(
                                "Customer Id : "+
                                order.info("id")
                            );
                            size.setText(
                                "Size : "+
                                order.info("size")
                            );
                            qty.setText(
                                "qty : "+
                                order.info("qty")
                            );
                            amount.setText(
                                "Amount : "+
                                order.info("amount")
                            );
                            status.setText(
                                "Customer Id : "+
                                order.info("status")
                            );      
                        }
                        
                    }
                    enterOrderIdTxtFld.setText("");
                }
            }
        );

        //orderidtxtFiled
        enterOrderIdTxtFld.addKeyListener(
            new KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    validateInputId(enterOrderIdTxtFld.getText());
                }
            }
        );

        JButton deleteBtn=new JButton("Delete");

        deleteBtn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    String id=ID[0];

                    int result = JOptionPane.
                    showConfirmDialog(mainFrame, "Do you want to delete this order?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    
                    if (result == JOptionPane.YES_OPTION) {
                        System.out.println("id : "+id);
                        System.out.println(Boolean.toString(orderList.remove(id)));
                        mainFrame.setVisible(false);
                        deleteOrder(true);
                    }
                }
            }
        );
        mainFrame.add(
            "South",
            deleteBtn
        );
        mainFrame.setVisible(isVisible);
    }

    public void changeStatus(boolean isVisible){
         JFrame mainFrame=new JFrame(
            "Status Form"
        );
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new BorderLayout());
        //mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //backButton
        JButton backBtn=new JButton("Back");
        backBtn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mainMenu(true);
                    mainFrame.setVisible(false);
                }
            }
        );

        //layer 1
        JPanel layer1=new JPanel(new BorderLayout());
        
        //searchPannel
        JPanel searchPanel=new JPanel(new GridLayout(1,3));

        JLabel enterOrderId=new JLabel("Enter Order ID : ");
        JTextField enterOrderIdTxtFld=new JTextField();
        JButton searchBtn=new JButton("Search");

        

        searchPanel.add(enterOrderId);
        searchPanel.add(enterOrderIdTxtFld);
        searchPanel.add(searchBtn);

        //detailsForm
        JPanel formPanel=new JPanel(new GridLayout(5,1)); 
        JLabel 
            customerId=new JLabel("Customer ID : "),
            size=new JLabel("Size : "),
            qty=new JLabel("Qty : "),
            amount=new JLabel("Amount : "),
            status=new JLabel("Status : ");

        formPanel.add(customerId);
        formPanel.add(size);
        formPanel.add(qty);
        formPanel.add(amount);
        formPanel.add(status);
        
        layer1.add(
            "Center",
            formPanel
        );

         layer1.add(
            "North",
            searchPanel
        );

         mainFrame.add(
            "North",
            backBtn
        );
        mainFrame.add(
            "Center",
            layer1
        );

        //search Btn
        String [] ID={"-1", "status"};
        searchBtn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ID[0]=enterOrderIdTxtFld.getText();
                    String id=ID[0];
                    if(!id.isBlank()){

                        if(orderList.isOrderIdOntheList(id)){

                            int index=orderList.
                            search(enterOrderIdTxtFld.getText());

                            OrderList.Orders order=orderList.get(index);
                            customerId.setText(
                                "Customer Id : "+
                                order.info("id")
                            );
                            size.setText(
                                "Size : "+
                                order.info("size")
                            );
                            qty.setText(
                                "qty : "+
                                order.info("qty")
                            );
                            amount.setText(
                                "Amount : "+
                                order.info("amount")
                            );
                            status.setText(
                                "Customer Id : "+
                                order.info("status")
                            );      
                        }
                        
                    }
                    enterOrderIdTxtFld.setText("");
                }
            }
        );

        //orderidtxtFiled
        enterOrderIdTxtFld.addKeyListener(
            new KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    validateInputId(enterOrderIdTxtFld.getText());
                }
            }
        );

        JButton changeStatusBtn=new JButton("Change Status");

        changeStatusBtn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    String id=ID[0];
                    if(!id.equalsIgnoreCase("-1")){
                        OrderList.Orders order= orderList.get(orderList.search(id));
                        String status=order.info("status");
                        ID[1]=status;
                        Object[] options = status.equalsIgnoreCase("processing") ?
                            new Object[]{"delivering", "delivered"}:
                            status.equalsIgnoreCase("delivering") ?
                            new Object[]{"delivered"}:
                            new Object[]{"already dilivered!"};
                        String msg=status.equalsIgnoreCase("delivered")?
                            "you cannot change the status":
                            "please select the status";

                        int result = JOptionPane.
                        showOptionDialog(
                            mainFrame, 
                            msg, 
                            "Status", 
                            //JOptionPane.YES_NO_OPTION(delivering/deleivered),
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.QUESTION_MESSAGE, 
                            null, 
                            options, 
                            options[0]
                        );
                        if (result == JOptionPane.YES_OPTION) {
                           orderList.changeStatus(id);
                        }else{
                            orderList.changeStatus(id);
                            orderList.changeStatus(id);
                        }
                    }
                    
                }
            }
        );
        mainFrame.add(
            "South",
            changeStatusBtn
        );
        mainFrame.setVisible(isVisible);
    }

    public void status(String status){
         JFrame mainFrame=new JFrame(
            "Status"
        );
        mainFrame.setSize(200,100);
        mainFrame.setLayout(new GridLayout(2,1));

        
        if(status.equalsIgnoreCase("processing")){
            
        }
        if(status.equalsIgnoreCase("delivering")){

        }
        if(status.equalsIgnoreCase("delivered")){

        }
    }
    
    private void validateInputId(String input) {
        if ( input.length()==4 && !input.matches("ORD#")){
            JOptionPane.
            showMessageDialog(null, "Invalid input. Please enter in the format 'ORD#00000'.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if ( input.length()==9 && !input.matches("ORD#\\d{5}")){
            JOptionPane.
            showMessageDialog(null, "Invalid input. Please enter in the format 'ORD#00000'.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if( 
            input.length()==9 && 
            input.matches("ORD#\\d{5}") && 
            !orderList.isOrderIdOntheList(input) 
        ){
            JOptionPane.
            showMessageDialog(null, "enter id is not in the list", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static class OrderIDGenerator {
            private static int orderIdCounter = 1;

            public static String generateOrderID() {
                String orderId = "ORD#" + String.format("%05d", orderIdCounter);
                orderIdCounter++;
                return orderId;
            }

            public static void cancelOrderId(){
                orderIdCounter--;
            }
        }
}
class Example{
    public static void main(String[] args) {
        OrderList orderList=new OrderList();
        Ui ui=new Ui(orderList);
        ui.mainMenu(true);
        
        
    }
}
