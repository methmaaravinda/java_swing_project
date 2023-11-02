public class OrderList{
    private Orders first;

    public void add(String id, String phone, String size, String qty ){

        Orders order=new Orders(phone, id, size, qty);
        if(first==null){
            first=order;
        }else{
            Orders temp=first;
            while(temp.next!=null){
                temp=temp.next;
            }
            temp.next=order;
        }
    }

    public void add(Orders order){
        if(first==null){
            first=order;
        }else{
            Orders temp=first;
            while(temp.next!=null){
                temp=temp.next;
            }
            temp.next=order;
        }
    }

    public void print(){
        Orders temp=first;
        if(temp==null){
            System.out.println("[empty]");
        }else{
            System.out.print("[ ");
            while(temp!=null){
                System.err.print(
                    "{ "
                    +temp.id+", "
                    +temp.info("phone")+", "
                    +temp.info("size")+", "
                    +temp.info("qty")+", "
                    +temp.info("status")
                    +" } "
                );
                temp=temp.next;
            }
            System.out.println("\b ]");
        }
    }

    public boolean remove(String id){
        int index=search(id);
        if(index!=-1){
            Orders orderBefore=get(index-1);
            Orders orderNext=get(index+1);
            if(index==0) first=orderNext;
            else orderBefore.next=orderNext;
            return true;
        }
        return false;
    }

    public int search(String id){
        Orders temp=first;
        int index=-1;
        while(temp!=null){
            index++;
            if(temp.id.equalsIgnoreCase(id)) return index;
            temp=temp.next;
        }
        return -1;
    }

    public OrderList searchByPhone(String phone){
        Orders temp=first;
        OrderList ordersByPhone=new OrderList();
        while(temp!=null){
            if(temp.phn.equalsIgnoreCase(phone)){
                ordersByPhone.add(
                    temp.id, temp.phn, temp.size, temp.qty
                );
            }
            temp=temp.next;
        }
        return ordersByPhone;
    }

    public void changeStatus(String id){
        int index=search(id);
        if(isValidIndex(index)){
            Orders order=get(index);
            order.changeStatus();
        }
    }

    public int items(String Size){
        Orders temp=first;
        int [] sizeArr=new int[6];
        while(temp!=null){
            String size=temp.size;
            int qty=Integer.parseInt(temp.qty);
            switch (size) {
                case "XS":
                    sizeArr[0] +=qty;
                    break;
                case "S":
                    sizeArr[1] +=qty;
                    break;
                case "M":
                    sizeArr[2] +=qty;
                    break;
                case "L":
                    sizeArr[3] +=qty;
                    break;
                case "XL":
                    sizeArr[4] +=qty;
                    break;
                case "XXL":
                    sizeArr[5] +=qty;
                    break;
            }
            temp=temp.next;
        }
        switch (Size) {
                case "XS":
                    return sizeArr[0];
                case "S":
                    return sizeArr[1];
                case "M":
                    return sizeArr[2];
                case "L":
                    return sizeArr[3];
                case "XL":
                    return sizeArr[4];
                case "XXL":
                    return sizeArr[5];
        }
        return -1;
    }
    
    public int allQty(String phoneNumber){
        OrderList ordrsByPhn=searchByPhone(phoneNumber);
        String [] sizes=new String[]{
            "XS", "S", "M", "L", "XL", "XXL"
        };
        int total=0;
        for(int i=0; i<sizes.length; i++){
            total+=ordrsByPhn.items(sizes[i]);
        }
        return total;
    }

    public double totalAmount(String phoneNumber){
        OrderList ordrsByPhn=searchByPhone(phoneNumber);
        String [] sizes=new String[]{
            "XS", "S", "M", "L", "XL", "XXL"
        };
        double total=0;
        for(int i=0; i<sizes.length; i++){
            int qty=ordrsByPhn.items(sizes[i]);
            total+=ordrsByPhn.amount(sizes[i], qty);
        }
        return total;
    }

    public int length(){
        Orders temp=first;
        int index=0;
        while(temp!=null){
            index++;
            temp=temp.next;
        }
        return index;
    }

    public Orders get(int index){
        if(!isValidIndex(index)) return null;
        Orders temp=first;
        int Index=-1;
        while(temp!=null){
            Index++;
            if(Index==index) return temp;
            temp=temp.next;
        }
        return null;
    }

    private boolean isValidIndex(int index){
        int length=length();
        return length>index && index>-1;
    }

    public String [] phoneNumberArray(){
        int length=length();
        String [] arr=new String[length];
        for(int i=0; i<length; i++){
            arr[i]=get(i).phn;
        }
        return removeDuplicates(arr);
    }

    public String [] orderIdArray(){
        int length=length();
        String [] arr=new String[length];
        for(int i=0; i<length; i++){
            arr[i]=get(i).id;
        }
        return removeDuplicates(arr);
    }

    public boolean isPhoneNumberOntheList(String PhoneNumber){
        String [] phoneNumber=
            phoneNumberArray();
        for(int i=0; i<phoneNumber.length; i++){
            if(phoneNumber[i].equalsIgnoreCase(PhoneNumber)) 
                return true;
        }
        return false;
    }

    public boolean isOrderIdOntheList(String id){
        String [] Id=
            orderIdArray();
        for(int i=0; i<Id.length; i++){
            if(Id[i].equalsIgnoreCase(id)) 
                return true;
        }
        return false;
    }

    public double amount(String size, int qty){
        switch(size){
            case "XS": return 600.00*qty;
            case "S": return 800.00*qty;
            case "M": return 900.00*qty;
            case "L": return 1000.00*qty;
            case "XL": return 1100.00*qty;
            case "XXL": return 1200.00*qty;
        }
        return 0.0;
    }
    
    private String[] removeDuplicates(String[] array) {
        int uniqueCount = 0; 
        boolean[] isDuplicate = new boolean[array.length]; 

    
        for (int i = 0; i < array.length - 1; i++) {
        if (!isDuplicate[i]) {
            for (int j = i + 1; j < array.length; j++) {
                if (!isDuplicate[j] && array[i].equals(array[j])) {
                    isDuplicate[j] = true;
                }
            }
        }
    }


        for (boolean notDuplicate : isDuplicate) {
            if (!notDuplicate) {
                uniqueCount++;
            }
        }


        String[] uniqueArray = new String[uniqueCount];
        int currentIndex = 0;

        for (int i = 0; i < array.length; i++) {
            if (!isDuplicate[i]) {
                uniqueArray[currentIndex] = array[i];
                currentIndex++;
            }
        }

        return uniqueArray;
    }

    public Orders pointer(){
        return first;
    }
    class Orders{
        private String phn, id, size, qty,status, amount;
        private Orders next;
        Orders(String phn, String id, String size, String qty){
            this.phn=phn;
            this.id=id;
            this.size=size;
            this.qty=qty;
            this.amount=amount(size,Integer.parseInt(qty))+"";
            this.status="processing";
        }

        public String info(String property){
            switch(property){
                case "phone": return phn;
                case "id": return id;
                case "size": return size;
                case "qty": return qty;    
                case "status": return status;    
                case "amount": return amount;
            }
            return "-1";
        }

        public void changeStatus(){
            if(!status.equalsIgnoreCase("dilivered")){
                if(this.status.equalsIgnoreCase("delivering")) 
                    this.status="delivered";
                if(this.status.equalsIgnoreCase("processing")) 
                    this.status="delivering";
            }
        }

        private double amount(String size, int qty){
            switch(size){
                case "XS":return qty*600.00;
                case "S":return qty*800.00;
                case "M":return qty*900.00;
                case "L":return qty*1000.00;
                case "XL":return qty*1100.00;
                case "XXL":return qty*1200.00;
            }
            return -1.00;
        }
    }
    
}
