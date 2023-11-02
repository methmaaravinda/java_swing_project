public class Reports{
    private OrderList orderList;

    Reports(OrderList orderList){
        this.orderList=orderList;
    }

    public void searchCustomer(String phone){
        OrderList orderListByPhone=
        orderList.searchByPhone(phone);
        sortedOrdersByAmount(orderListByPhone);
        double totalAmount=
        orderList.totalAmount(phone);
        Tables table=new Tables(
            "Total Amount",
            totalAmount+"    "
        );
    }
    
    public String[] sortedPhoneNumbersByAllQtyAndAmount(String[] phnArr) {
    // Sort by quantity in descending order using bubble sort
    int n = phnArr.length;
    boolean swapped;

    for (int i = 0; i < n - 1; i++) {
        swapped = false;

        for (int j = 0; j < n - 1 - i; j++) {
            int qty1 = orderList.allQty(phnArr[j]);
            int qty2 = orderList.allQty(phnArr[j + 1]);

            if (qty1 < qty2 || (qty1 == qty2 && orderList.totalAmount(phnArr[j]) < orderList.totalAmount(phnArr[j + 1]))) {
                String temp = phnArr[j];
                phnArr[j] = phnArr[j + 1];
                phnArr[j + 1] = temp;
                swapped = true;
            }
        }

        if (!swapped) {
            break;
        }
    }

    return phnArr;
}

    public void bestInCustomers(){
        String [] phnArr=sortedPhoneNumbersByAllQtyAndAmount(
            orderList.phoneNumberArray()
        );
        Tables tables=new Tables(
            "  Customer ID ",
            " All Qty ",
            " Total Amount "
        );
        for(int i=0; i<phnArr.length; i++){
            String phnNmbr=phnArr[i];
            String allQty=orderList.allQty(phnNmbr)+"";
            String totalAmount=
                orderList.totalAmount(phnNmbr)+"";
            tables.add(phnNmbr, allQty, totalAmount);
        }
    }

    public void viewCustomers(){
        System.out.println();
        String [] phnArr=orderList.phoneNumberArray();
        Tables tables=new Tables(
            "  Customer ID ",
            " All Qty ",
            " Total Amount "
        );
        for(int i=0; i<phnArr.length; i++){
            String phnNmbr=phnArr[i];
            String allQty=orderList.allQty(phnNmbr)+"";
            String totalAmount=
                orderList.totalAmount(phnNmbr)+"";
            tables.add(phnNmbr, allQty, totalAmount);
        }
    }

    public void allCustomerReport(){
        System.out.println();
        String [] phnArr=orderList.phoneNumberArray();
        Tables tables=new Tables(
            "Phone Number",
            "XS"," S", " M", " L", " XL", "XXL",
            "  Total  "
        );
        for(int i=0; i<phnArr.length; i++){
            String phnNmbr=phnArr[i];
            OrderList orderListByPhone=
                orderList.searchByPhone(phnNmbr);
            String xs=orderListByPhone.items("XS")+"";
            String s=orderListByPhone.items("S")+"";
            String m=orderListByPhone.items("M")+"";
            String l=orderListByPhone.items("L")+"";
            String xl=orderListByPhone.items("XL")+"";
            String xxl=orderListByPhone.items("XXL")+"";
            String totalAmount=
                orderList.totalAmount(phnNmbr)+"";
            tables.
            add(phnNmbr, xs, s, m, l, xl, xxl, totalAmount);
        }
    }

    public void allOrders(){
        System.out.println();
        String [] ordersArr=orderList.orderIdArray();
        Tables table=new Tables(
            "  Order ID  ",
            "Cutstomer ID",
            "Size",
            "Qty",
            "  Amount  ",
            "  Status  "
        );
        for(int i=0; i<ordersArr.length; i++){
            int index=orderList.search(ordersArr[i]);
            OrderList.Orders order=
            orderList.get(index);
            String phone=order.info("phone");
            String size=order.info("size");
            String qty=order.info("qty");
            String status=order.info("status");
            String amount=order.info("amount");
            table.add
            (ordersArr[i], phone, size, qty, amount, status);
        }
    }
    public void allOrdersByAmount(){
        System.out.println();
        String [] ordersIdArr=sortedOrderIdByAmount();
        Tables table=new Tables(
            "  Order ID  ",
            "Cutstomer ID",
            "Size",
            "Qty",
            "  Amount  ",
            "  Status  "
        );
        for(int i=0; i<ordersIdArr.length; i++){
            int index=orderList.search(ordersIdArr[i]);
            OrderList.Orders order=
            orderList.get(index);
            String phone=order.info("phone");
            String size=order.info("size");
            String qty=order.info("qty");
            String status=order.info("status");
            String amount=order.info("amount");
            table.add
            (ordersIdArr[i], phone, size, qty, amount, status);
        }
    }

    private String [] sortedPhoneNumbersByAllQty(){
            String [] phnArr=orderList.phoneNumberArray();
            phnArr=bubbleSortForAllQty(phnArr);
            return phnArr;
    }
    public String [] sortedOrderIdByAmount(){
            String [] idArray=orderList.orderIdArray();
            idArray=bubbleSortAmountById(idArray);
            return idArray;
    }

    public void sortedOrdersByQty(){
        String [] sizeAndQty=new String[]{
            "XS-"+orderList.items("XS"),
            "S-"+orderList.items("S"),
            "M-"+orderList.items("M"),
            "L-"+orderList.items("L"),
            "XL-"+orderList.items("XL"),
            "XXL-"+orderList.items("XXL")
        };
        sizeAndQty=bubbleSortForSizes(sizeAndQty);
        System.out.println();
        Tables table=new Tables(
            "Size",
            " Qty ",
            "Total Amount"
        );
        for(int i=0; i<sizeAndQty.length; i++){
            String size=sizeAndQty[i].split("-")[0];
            String qty=sizeAndQty[i].split("-")[1];
            String amount=orderList.amount(
                size, 
                Integer.parseInt(qty)
            )+"";
            table.add(size,qty,amount);
        }
    }

    public void sortedOrdersByAmount(){
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
        sizeQtyAndAmount=bubbleSortForAmount(sizeQtyAndAmount);
        System.out.println();
        Tables table=new Tables(
            "Size",
            " Qty ",
            "Total Amount"
        );
        for(int i=0; i<sizeQtyAndAmount.length; i++){
            String size=sizeQtyAndAmount[i].split("-")[0];
            String qty=sizeQtyAndAmount[i].split("-")[1];
            String amount=sizeQtyAndAmount[i].split("-")[2];
            table.add(size,qty,amount);
        }
    }
    public void sortedOrdersByAmount(
        OrderList orderList
    ){
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
        sizeQtyAndAmount=bubbleSortForAmount(sizeQtyAndAmount);
        System.out.println();
        Tables table=new Tables(
            "Size",
            " Qty ",
            "  Amount  "
        );
        for(int i=0; i<sizeQtyAndAmount.length; i++){
            String size=sizeQtyAndAmount[i].split("-")[0];
            String qty=sizeQtyAndAmount[i].split("-")[1];
            String amount=sizeQtyAndAmount[i].split("-")[2];
            table.add(size,qty,amount);
        }
    }
    
    private String[] bubbleSortForAllQty(String[] phnArr) {
        int n = phnArr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                if (
                    orderList.allQty(phnArr[j]) < 
                    orderList.allQty(phnArr[j+1])
                ){
                    String temp = phnArr[j];
                    phnArr[j] = phnArr[j + 1];
                    phnArr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        return phnArr;
    }
    
    private String[] bubbleSortAmountById(String[] idArray) {
        int n = idArray.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                if (
                    Double.parseDouble(orderList.get(orderList.search(idArray[j])).
                    info("amount")) < 
                    Double.parseDouble(orderList.get(orderList.search(idArray[j+1])).
                    info("amount"))
                ){
                    String temp = idArray[j];
                    idArray[j] = idArray[j + 1];
                    idArray[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        return idArray;
    }
    public String[] bubbleSortForSizes(String[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                if (
                    Integer.parseInt(arr[j].split("-")[1])
                    <
                    Integer.parseInt(arr[j+1].split("-")[1])
                ) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        return arr;
    }
    public String[] bubbleSortForAmount(String[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                if (
                    Double.parseDouble(arr[j].split("-")[2])
                    <
                    Double.parseDouble(arr[j+1].split("-")[2])
                ) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        return arr;
    }

    private static class Tables{
        int [] argLengths;
        Tables(String ... args){
            argLengths=new int[args.length];
            for(int i=0; i<args.length; i++){
                argLengths[i]=args[i].length();
            }
            String format="";
            for(int i=0; i<argLengths.length; i++){
                format+="| %-"+argLengths[i]+"s"+" ";
            }
            format+="|%n";
            System.out.printf(format, (Object []) args);
        }

        public void add(String ... args){
            String format="";
            for(int i=0; i<argLengths.length; i++){
                format+="| %"+argLengths[i]+"s"+" ";
            }
            format+="|%n";
            System.out.printf(format, (Object []) args);
        }

    }

}

