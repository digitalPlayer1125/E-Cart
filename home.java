import java.util.*;

// The print interface
interface Prints{ 
    public void print(); 
    public void addCont(double a);
} 

public class home{
    
    //Scanner
    public static Scanner scan= new Scanner(System.in);
    //ArrayList for ItemsList
    public static ArrayList<Item> Itemslist = new ArrayList<>();
    //ArrayList for Orders
    public static ArrayList<Item> orders = new ArrayList<>();
    //HashMap for Customers 
    public static HashMap<Integer, Customer> Customerslist = new HashMap<>();
    //HashMap for Rewards
    public static HashMap<Integer, Customer> Rewardslist = new HashMap<>();
    //HashMap for Merchants
    public static HashMap<Integer, Merchant> Merchantlist = new HashMap<>();
    //HashMap for Categories
    public static HashMap<String, ArrayList<Item>> CategoryList = new HashMap<>();
    //Lists for Cart
    public static Cart cobj1 = new Cart();
    public static Cart cobj2 = new Cart();
    //Company Balance
    public static double cbalance= 0.00;
    
    //Adding to the comapnies balance (merchant's)
    void addcontribution(double a){
        cbalance+= a;
    }
    public static void main(String args[]){

        //Adding Customers
        Customerslist.put(1, new Customer("Rishi", "Delhi"));
        Customerslist.put(2, new Customer("Ayush", "Mumbai"));
        Customerslist.put(3, new Customer("Vaibhav", "Punjab"));
        Customerslist.put(4, new Customer("Mrdul", "Ranchi"));
        Customerslist.put(5, new Customer("Utkarsh", "Roorkee"));

        //Adding their rewards accounts
        Rewardslist.put(1, new Customer("Rishi", "Delhi")); Rewardslist.get(1).setMoney(0);
        Rewardslist.put(2, new Customer("Ayush", "Mumbai")); Rewardslist.get(2).setMoney(0);
        Rewardslist.put(3, new Customer("Vaibhav", "Punjab")); Rewardslist.get(3).setMoney(0);
        Rewardslist.put(4, new Customer("Mrdul", "Ranchi")); Rewardslist.get(4).setMoney(0);
        Rewardslist.put(5, new Customer("Utkarsh", "Roorkee")); Rewardslist.get(5).setMoney(0);
        
        //Adding Merchants
        Merchantlist.put(1, new Merchant("CloudRetail", "Shimla"));
        Merchantlist.put(2, new Merchant("GenPac", "East Delhi"));
        Merchantlist.put(3, new Merchant("AWS", "Bangalore"));
        Merchantlist.put(4, new Merchant("Shazam", "Noida"));
        Merchantlist.put(5, new Merchant("Globules", "Gurgaon"));

        //Creating home object
        home newhome = new home();

        //Calling the main menu
        newhome.MainMenu();
    }

    //Returning the rewardslist 
    HashMap<Integer, Customer> getRewardsList(){
        return Rewardslist;
    }

    //Returning the rewardslist 
    HashMap<Integer, Customer> getCustomerslist(){
        return Customerslist;
    }


    void addToCart(Item a){
        cobj1.addToCart(a);
        cobj2.addToCart(a);
    }

    void emptyCarty(){
        cobj1.Empty();
        cobj2.Empty();
    }

    void performCheck(int a){
        Queue<Item> ch1= cobj1.getcartlist();
        makechangesinOListFake(ch1, a);
    }


    //Making fake changes to check if possible
    void makechangesinOListFake(Queue<Item> d, int b){
        System.out.println("----Checking----");
        ArrayList<Item> temporders= new ArrayList<Item>(orders);
        ArrayList<Item> tempItemslist= new ArrayList<Item>(Itemslist);
        HashMap<Integer, Customer> tempRewardslist = new HashMap<Integer,Customer>(Rewardslist);
        HashMap<Integer, Customer> tempCustomerslist = new HashMap<Integer,Customer>(Customerslist);
        HashMap<String, ArrayList<Item>> tempCategoryList= new HashMap<String, ArrayList<Item>>(CategoryList);
        
        //Till the list gets empty
        while(d.size()!=0){
            Item temp1= d.remove();
            temp1.print();
            //Customer Modification
            if(temp1.getPrice()<=tempCustomerslist.get(b).getMoney()){
                //Decreasing the money
                tempCustomerslist.get(b).setMoney(tempCustomerslist.get(b).getMoney()-temp1.getPrice());
                //Adding it to the list of all orders.
                temporders.add(temp1);
                //Increment orders
                tempCustomerslist.get(b).incrementnorders();
                //Giving the reward money to the customer
                if(tempCustomerslist.get(b).getorders()%5==0){
                    tempRewardslist.get(b).setMoney(tempRewardslist.get(b).getMoney()+10);  
                } 
                //Items list
                for(int i=0; i<tempItemslist.size(); i++){
                    if(tempItemslist.get(i).getuid()==tempItemslist.get(i).getuid() && tempItemslist.get(i).getMerchantName().equals(temp1.getMerchantName())){
                        tempItemslist.get(i).setQuantity(tempItemslist.get(i).getQuantity()-temp1.getQuantity());
                        break;
                    }
                }
                //HashMap
                for(int i=0; i<tempCategoryList.get(temp1.getCategory()).size(); i++){
                    if(tempCategoryList.get(temp1.getCategory()).get(i).getuid()==tempCategoryList.get(temp1.getCategory()).get(i).getuid() && tempCategoryList.get(temp1.getCategory()).get(i).getMerchantName().equals(temp1.getMerchantName())){
                        tempCategoryList.get(temp1.getCategory()).get(i).setQuantity(tempCategoryList.get(temp1.getCategory()).get(i).getQuantity());
                        break;
                    }
                }
            }
            else if(temp1.getPrice()<=tempCustomerslist.get(b).getMoney()+tempRewardslist.get(b).getMoney()){
                tempRewardslist.get(b).setMoney(temp1.getPrice()-tempCustomerslist.get(b).getMoney());
                tempCustomerslist.get(b).setMoney(0);
                //Adding it to the list of all orders.
                temporders.add(temp1);
                //Increment orders
                tempCustomerslist.get(b).incrementnorders();
                //Giving the reward money to the customer
                if(tempCustomerslist.get(b).getorders()%5==0){
                    tempRewardslist.get(b).setMoney(tempRewardslist.get(b).getMoney()+10);  
                } 
                //Items list
                for(int i=0; i<tempItemslist.size(); i++){
                    if(tempItemslist.get(i).getuid()==tempItemslist.get(i).getuid() && tempItemslist.get(i).getMerchantName().equals(temp1.getMerchantName())){
                        tempItemslist.get(i).setQuantity(tempItemslist.get(i).getQuantity()-temp1.getQuantity());
                        break;
                    }
                }
                //HashMap
                for(int i=0; i<tempCategoryList.get(temp1.getCategory()).size(); i++){
                    if(tempCategoryList.get(temp1.getCategory()).get(i).getuid()==tempCategoryList.get(temp1.getCategory()).get(i).getuid() && tempCategoryList.get(temp1.getCategory()).get(i).getMerchantName().equals(temp1.getMerchantName())){
                        tempCategoryList.get(temp1.getCategory()).get(i).setQuantity(tempCategoryList.get(temp1.getCategory()).get(i).getQuantity());
                        break;
                    }
                }
            }
            else{
                System.out.println("Not possible to perform the cart checkout.");
                return;
            }
        }
        System.out.println("----Checked!----");
        Performtransaction(cobj2, b);
    }

    //Performing after checking
    void Performtransaction(Cart c, int b){
        System.out.println("Performing the transaction.....");
        while(c.getcartlist().size()!=0){
            Item temp1= c.getcartlist().remove();
            
            //Adding it to the list of all orders.
            orders.add(temp1);
            // System.out.println("If order is added or not"+orders);

            //Customer Modification
            if(temp1.getPrice()<=Customerslist.get(b).getMoney()){
                //Decreasing the money
                Customerslist.get(b).setMoney(Customerslist.get(b).getMoney()-temp1.getPrice());
                //Increment orders
                Customerslist.get(b).incrementnorders();
                //Giving the reward money to the customer
                if(Customerslist.get(b).getorders()%5==0){
                    Rewardslist.get(b).setMoney(Rewardslist.get(b).getMoney()+10);  
                } 

                //Contributions
                cbalance+= temp1.getPrice()*0.01;
                Merchantlist.get(temp1.getuid()).addCont(temp1.getPrice());

                //Items list
                for(int i=0; i<Itemslist.size(); i++){
                    if(Itemslist.get(i).getuid()==Itemslist.get(i).getuid() && Itemslist.get(i).getMerchantName().equals(temp1.getMerchantName())){
                        Itemslist.get(i).setQuantity(Itemslist.get(i).getQuantity()-temp1.getQuantity());
                        break;
                    }
                }
                //HashMap
                for(int i=0; i<CategoryList.get(temp1.getCategory()).size(); i++){
                    if(CategoryList.get(temp1.getCategory()).get(i).getuid()==CategoryList.get(temp1.getCategory()).get(i).getuid() && CategoryList.get(temp1.getCategory()).get(i).getMerchantName().equals(temp1.getMerchantName())){
                        // System.out.println(CategoryList.get(temp1.getCategory()).get(i).getQuantity()+"                                 "+temp1.getQuantity());
                        CategoryList.get(temp1.getCategory()).get(i).setQuantity(CategoryList.get(temp1.getCategory()).get(i).getQuantity());
                        break;
                    }
                }
            }
            else if(temp1.getPrice()<=Customerslist.get(b).getMoney()+Rewardslist.get(b).getMoney()){
                Rewardslist.get(b).setMoney(temp1.getPrice()-Customerslist.get(b).getMoney());
                Customerslist.get(b).setMoney(0);
                //Increment orders
                Customerslist.get(b).incrementnorders();
                //Giving the reward money to the customer
                if(Customerslist.get(b).getorders()%5==0){
                    Rewardslist.get(b).setMoney(Rewardslist.get(b).getMoney()+10);  
                } 

                //Contributions
                //Contributions
                cbalance+= temp1.getPrice()*0.01;
                Merchantlist.get(temp1.getuid()).addCont(temp1.getPrice());

                //Items list
                for(int i=0; i<Itemslist.size(); i++){
                    if(Itemslist.get(i).getuid()==Itemslist.get(i).getuid() && Itemslist.get(i).getMerchantName().equals(temp1.getMerchantName())){
                        Itemslist.get(i).setQuantity(Itemslist.get(i).getQuantity()-temp1.getQuantity());
                        break;
                    }
                }
                //HashMap
                for(int i=0; i<CategoryList.get(temp1.getCategory()).size(); i++){
                    if(CategoryList.get(temp1.getCategory()).get(i).getuid()==CategoryList.get(temp1.getCategory()).get(i).getuid() && CategoryList.get(temp1.getCategory()).get(i).getMerchantName().equals(temp1.getMerchantName())){
                        // System.out.println(CategoryList.get(temp1.getCategory()).get(i).getQuantity()+"                                 "+temp1.getQuantity());
                        CategoryList.get(temp1.getCategory()).get(i).setQuantity(CategoryList.get(temp1.getCategory()).get(i).getQuantity());
                        break;
                    }
                }
            }
        }
        //Notifications
        System.out.println("Transaction Performed.");
        System.out.println("--- Money Left with the you be ---> " + Customerslist.get(b).getMoney());
        //Calling the customer menu again
        CustomerMenu(b);
    }

    //Make changes in the orders
    void makechangesinOList(double a, int b, Item c, Item obj, int d){

        //Customer Modification
            if(a<=Customerslist.get(b).getMoney()){
                //Decreasing the money
                Customerslist.get(b).setMoney(Customerslist.get(b).getMoney()-a);
                //Adding it to the list of all orders.
                orders.add(c);
                //Increment orders
                Customerslist.get(b).incrementnorders();
                //Giving the reward money to the customer
                if(Customerslist.get(b).getorders()%5==0){
                    Rewardslist.get(b).setMoney(Rewardslist.get(b).getMoney()+10);  
                } 

                //Contributions
                cbalance+= a*0.01;
                Merchantlist.get(obj.getuid()).addCont(a);

                //Items list
                for(int i=0; i<Itemslist.size(); i++){
                    if(Itemslist.get(i).getuid()==Itemslist.get(i).getuid() && Itemslist.get(i).getMerchantName().equals(obj.getMerchantName())){
                        Itemslist.get(i).setQuantity(Itemslist.get(i).getQuantity()-d);
                        break;
                    }
                }
                //HashMap
                for(int i=0; i<CategoryList.get(obj.getCategory()).size(); i++){
                    if(CategoryList.get(obj.getCategory()).get(i).getuid()==CategoryList.get(obj.getCategory()).get(i).getuid() && CategoryList.get(obj.getCategory()).get(i).getMerchantName().equals(obj.getMerchantName())){
                        CategoryList.get(obj.getCategory()).get(i).setQuantity(CategoryList.get(obj.getCategory()).get(i).getQuantity()-d);
                        break;
                    }
                }
            }
            else if(a<=Customerslist.get(b).getMoney()+Rewardslist.get(b).getMoney()){
                Rewardslist.get(b).setMoney(a-Customerslist.get(b).getMoney());
                Customerslist.get(b).setMoney(0);
                //Adding it to the list of all orders.
                orders.add(c);
                //Increment orders
                Customerslist.get(b).incrementnorders();
                //Giving the reward money to the customer
                if(Customerslist.get(b).getorders()%5==0){
                    Rewardslist.get(b).setMoney(Rewardslist.get(b).getMoney()+10);  
                } 

                //Contributions
                cbalance+= a*0.01;
                Merchantlist.get(obj.getuid()).addCont(a);

                //Items list
                for(int i=0; i<Itemslist.size(); i++){
                    if(Itemslist.get(i).getuid()==Itemslist.get(i).getuid() && Itemslist.get(i).getMerchantName().equals(obj.getMerchantName())){
                        Itemslist.get(i).setQuantity(Itemslist.get(i).getQuantity()-d);
                        break;
                    }
                }
                //HashMap
                for(int i=0; i<CategoryList.get(obj.getCategory()).size(); i++){
                    if(CategoryList.get(obj.getCategory()).get(i).getuid()==CategoryList.get(obj.getCategory()).get(i).getuid() && CategoryList.get(obj.getCategory()).get(i).getMerchantName().equals(obj.getMerchantName())){
                        CategoryList.get(obj.getCategory()).get(i).setQuantity(CategoryList.get(obj.getCategory()).get(i).getQuantity()-d);
                        break;
                    }
                }
            }
            return;
    }

    /* Main Menu */
    void MainMenu(){

        //Printing the menu options
        System.out.println("-------------------------------------");
        System.out.println("Welcome to Mercury");
        System.out.println("1) Enter as Merchant");
        System.out.println("2) Enter as Customer");
        System.out.println("3) See user details");
        System.out.println("4) Company account balance");
        System.out.println("5) Exit");
        System.out.println("-------------------------------------");
        
        //Taking user's choice
        int temp= scan.nextInt();
        
        //Choices

        //Emptying the cart
        emptyCarty();

        //Choose merchant
        if(temp==1) {
            System.out.println("Choose Merchant");
            for(int i=1; i<=5; i++) System.out.println(i+" "+ Merchantlist.get(i).getName());
            
            //Taking user's choice
            int temp1= scan.nextInt();
            if(temp1>=1 && temp1<=5) MerchantMenu(temp1);
        }

        //Enter as a customer
        else if(temp==2) {
            System.out.println("Choose Customer");
            for(int i=1; i<=5; i++) System.out.println(i+" "+ Customerslist.get(i).getName());
            
            //Taking user's choice
            int temp1= scan.nextInt();
            if(temp1>=1 && temp1<=5) CustomerMenu(temp1);
        }

        //See user details
        else if(temp==3) {
            SeeUserDetails();
            MainMenu();
        }

        //Comapny balance
        else if(temp==4){
            returncompanybalance();
            MainMenu();
        } 

        //Exit
        else if(temp==5) System.exit(0);

    }

    /* The company balance */
    void returncompanybalance(){
        System.out.println("The company balance is: " + cbalance);
    }

    /*  Printing user details */
    void SeeUserDetails(){
        for(int i=0;i<5;i++) Customerslist.get(i+1).print();
        for(int i=0;i<5;i++) Merchantlist.get(i+1).print();
    }


    /* Merchant's Menu */
    void MerchantMenu(int a){

        //Printing the menu options
        System.out.println("Welcome " + Merchantlist.get(a).getName());
        System.out.println("------------Merchant Menu------------");
        System.out.println("1) Add item:");
        System.out.println("2) Edit item:");
        System.out.println("3) Search by category:");
        System.out.println("4) Add offers:");
        System.out.println("5) Rewards won:");
        System.out.println("6) Exit:");
        System.out.println("-------------------------------------");
        //Taking user's choice
        int temp= scan.nextInt();
        //Choices
        Merchant tMerchant= new Merchant("", "");
        if(temp==1) AddItem(Itemslist, CategoryList, a);
        else if(temp==2) EditItem(Itemslist, CategoryList, a);
        else if(temp==3) tMerchant.SearchCategory(Itemslist, CategoryList, a);
        else if(temp==4) AddOffer(Itemslist, CategoryList, a);
        else if(temp==5) tMerchant.Rewardswon(orders, a);
        else if(temp==6) MainMenu();

    }

     /* Customer's Menu */
     void CustomerMenu(int a){

        //Printing the menu options
        System.out.println("Welcome " + Customerslist.get(a).getName());
        System.out.println("------------Customer Menu------------");
        System.out.println("1) Search by category");
        System.out.println("2) Checkout");
        System.out.println("3) Rewards won");
        System.out.println("4) List last 10 orders");
        System.out.println("5) Exit");
        System.out.println("-------------------------------------");
        
        //Taking user's choice
        Customer tCustomer= new Customer("", "");
        int temp= scan.nextInt();
        
        //Choices
        if(temp==1) tCustomer.SearchCategory(Itemslist, CategoryList, a);
        else if(temp==2) Checkout(a);
        else if(temp==3) tCustomer.Rewardswon(Rewardslist, Customerslist.get(a).getName(), a);
        else if(temp==4) Print10latest(a);
        else if(temp==5) MainMenu();
    }


    //Checking out
    void Checkout(int a){
        //Checking if possible
        performCheck(a);
    }


    //10 Latest orders
    void Print10latest(int a){
        System.out.println("Orders are ");
        for (int j = orders.size() - 1, k=10; j >= 0 && k>0; j--,k--) {
            orders.get(j).print();
        }
        // for(int i=0; i<orders.size(); i++)
            
        CustomerMenu(a);
    }

     //Adding item to the List & the Hash Map
     void AddItem(ArrayList<Item> a, HashMap<String, ArrayList<Item>> b, int c){
        System.out.println("Input Name");
        String tname= scan.next();
        System.out.println("Input Price");
        int tprice= scan.nextInt();
        System.out.println("Input Quantity");
        int tquantity= scan.nextInt();
        System.out.println("Input Category");
        String tcategory= scan.next();
        Item temp= new Item(a.size()+1, tname, tprice, tquantity, tcategory, Merchantlist.get(c).getName());
        //Adding to the itemslist
        a.add(temp);
        //Adding to the categorylist
        ArrayList<Item> tlist= new ArrayList<>();
        tlist.add(temp);
        // b.get(tcategory).add(temp);
        
        int countcheck= 0;
        int param= 11+(int)Merchantlist.get(c).getContribution()/100;
        for(int i=0; i<a.size(); i++){
            if(a.get(i).getMerchantName().equals(Merchantlist.get(c).getName()) && a.get(i).getQuantity()>0){
                countcheck++;
            }
        }

        if(countcheck<param){
            if(b.get(tcategory)==null) b.put(tcategory, tlist);
            else b.get(tcategory).add(temp);
            for (int i=0; i<b.get(tcategory).size(); i++)
                b.get(tcategory).get(i).print();
        }
        else{
            System.out.println("Can not add, more already permissible number of items for the merchant exists.");
        }
        MerchantMenu(c);
    }

    void EditItem(ArrayList<Item> a, HashMap<String, ArrayList<Item>> b, int c){
        System.out.println("Input Item Id");
        int UID= scan.nextInt();
        System.out.println("Input Price");
        int tprice= scan.nextInt();
        System.out.println("Input Quantity");
        int tquantity= scan.nextInt();
        
        //Setting it in ArrayList
        a.get(UID-1).setPrice(tprice);
        a.get(UID-1).setQuantity(tquantity);

        //Setting it in HashMap
        String tcates= a.get(UID-1).getCategory();
        for(int i=0; i<b.get(tcates).size(); i++){
            if(b.get(tcates).get(i).getuid()==(UID-1)){
                b.get(tcates).get(i).setPrice(tprice);
                b.get(tcates).get(i).setQuantity(tquantity);
            }
        }

        //Display the items
        for (int i=0; i<b.get(tcates).size(); i++)
            b.get(tcates).get(i).print();

        MerchantMenu(c);

    }

    void AddOffer(ArrayList<Item> a, HashMap<String, ArrayList<Item>> b, int c){

        //Display the items
        for (int i=0; i<a.size(); i++)
            a.get(i).print();

        System.out.println("Input Item Id");
        int UID= scan.nextInt();
        System.out.println("Set Offer: ");
        System.out.println("1) Buy one get one free");
        System.out.println("2) 25% Off");
        String ttext= scan.next();

        //Setting it in ArrayList
        if(ttext.equals("2")) a.get(UID-1).setOffer("25% Off");
        else a.get(UID-1).setOffer("Bye One Get One Free");

        //Setting it in HashMap
        String tcates= a.get(UID-1).getCategory();
        for(int i=0; i<b.get(tcates).size(); i++){
            if(b.get(tcates).get(i).getuid()==(UID-1) && b.get(tcates).get(i).getMerchantName().equals(Merchantlist.get(c).getName())){
                if(ttext.equals("2")) b.get(tcates).get(i).setOffer("25% Off");
                else b.get(tcates).get(i).setOffer("Bye One Get One Free");
            }
        }

        //Display the items
        for (int i=0; i<a.size(); i++)
            a.get(i).print();


        MerchantMenu(c);
                
    }

}

class Customer implements Prints{
    
    //Private variables
    private final String name;
    private final String address;
    private double money= 100;
    private int norders= 0;
    
    //Constructor
    Customer(String a, String b){
        name= a;
        address= b;
    }

    //Increment Orders
    void incrementnorders(){
        norders+=1;
    }

    //Decrement Orders
    void decrementnorders(){
        norders-=1;
    }

    //Getters
    String getName(){
        return name;
    }

    String getAddress(){
        return address;
    }
    
    double getMoney(){
        return money;
    }

    int getorders(){
        return norders;
    }

    //Setters
    void setMoney(double a){
        money= a;
    }


    //Printing the customer details
    @Override
    public void print(){
        System.out.println();
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Money: " + money);
        System.out.println("Orders: " + norders);
        System.out.println();
    }

    //Rewards won by the customer
    void Rewardswon(HashMap<Integer, Customer> d, String a, int c){
        System.out.println("Rewards awarded till now are: ");
        //Printing the rewards
        for(int i=0; i<d.size(); i++){
            if(d.get(i+1).getName().equals(a)){
                System.out.print(d.get(i+1).getMoney()+" Rupees"); 
                break;
            }
        } 
        System.out.println();

        home thome= new home();
        thome.CustomerMenu(c);
    }

    //Searching by category and placing the order
    void SearchCategory(ArrayList<Item> a, HashMap<String, ArrayList<Item>> b, int c){
        System.out.println("Categories are: ");
        int i=1;
        for (Map.Entry<String, ArrayList<Item>> entry : b.entrySet()){
            System.out.println(i+") "+entry.getKey());
            i++;
        }
        System.out.println("Choose Category: ");

        //Category Number 
        Scanner tscan = new Scanner(System.in);
        int J = tscan.nextInt();
        String T="";
        int j=1;
        for (Map.Entry<String, ArrayList<Item>> entry : b.entrySet()){
            if(j==J) {T= entry.getKey(); break;}
            j++;
        }

        //Printing items under that category
        for(int k=0; k<b.get(T).size(); k++)
            b.get(T).get(k).print();
        
        System.out.println("Enter item code: ");
        int JCode= tscan.nextInt();
        System.out.println("Enter item quantity: ");
        int JQuantity= tscan.nextInt();
        Item obj= null;
        for(int k=0; k<a.size(); k++){
            if(a.get(k).getuid()==JCode){
                obj = a.get(k);
                break;
            }
        }

        System.out.println("1) Buy Item");
        System.out.println("2) Add Item to cart");
        System.out.println("3) Exit");

        home thome = new home();

        //Choice of purchase
        int JOperators= tscan.nextInt();
        //Dealing with the order given
        if(obj!=null){
            if(obj.getQuantity()>=JQuantity){
                Item Itemtoaddinorders;
                int JQuantityextra= 0;
                double pricecheck= obj.getPrice();
                if(obj.getOffer()!=null && obj.getOffer().equals("25% Off")){
                    //Twenty Five Percent Off
                    pricecheck*= 0.75;
                    System.out.println("The Item ordered is with 25% Off offer.");
                }
                else if(obj.getOffer()!=null && obj.getOffer().equals("Bye One Get One Free")){
                    //Buy one get one free
                    JQuantityextra= JQuantity;
                    //Terminating condition
                    if(obj.getQuantity()<JQuantity+JQuantityextra){
                        System.out.println("Can't place the order, quantity ordered exceeds the quantity available.");
                        return;
                    }
                }
                pricecheck*=1.005;

                Itemtoaddinorders = new Item(obj.getuid(), obj.getName(), pricecheck*JQuantity, JQuantity+JQuantityextra, obj.getCategory(), obj.getMerchantName());

                if(JOperators==1){
                    //When it is to be bought directly
                    if(JQuantity*pricecheck<=getMoney() || JQuantity*pricecheck<=(getMoney()+thome.getRewardsList().get(c).getMoney())){
                        Cart tempc = new Cart();
                        tempc.addToCart(Itemtoaddinorders);
                        thome.Performtransaction(tempc, c);
                        System.out.println("Item Successfully bought.");
                        System.out.println("--- Money Left with the you be ---> " + thome.getCustomerslist().get(c).getMoney());
                    }
                    else{
                        System.out.println("Insufficient Balance.");
                    }
                }
                else if(JOperators==2){
                    //Add to the cart
                    thome.addToCart(Itemtoaddinorders);
                }
                else if(JOperators==3) {
                    //Calling the customer menu
                    thome.CustomerMenu(c);
                }
            }
            else{
                System.out.println("Can't place the order, quantity ordered exceeds the quantity available.");
            }
        }
        else{
            System.out.println("There is no item with the given code.");    
        }

        //Calling the customer menu
        thome.CustomerMenu(c);
    }
    @Override
    public void addCont(double a){
        System.out.println("This gets called");
        home thome= new home();
        thome.addcontribution(a*0.005);
    }

}


class Merchant implements Prints{

    //Private variables
    private final String name;
    private final String address;
    private double contribution= 0;

    //Constructor
    Merchant(String a, String b){
        name= a;
        address= b;
    }

    //Getters
    String getName(){
        return name;
    }

    String getAddress(){
        return address;
    }

    double getContribution(){
        return contribution;
    }

    //Printing the merchant details
    @Override
    public void print(){
        System.out.println();
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Contribution: " + contribution);
        System.out.println();
    }

    //Searching the category
    void SearchCategory(ArrayList<Item> a, HashMap<String, ArrayList<Item>> b, int c){
        System.out.println("Categories are: ");
        int i=1;
        for (Map.Entry<String, ArrayList<Item>> entry : b.entrySet()){
            System.out.println(i+") "+entry.getKey());
            i++;
        }
        System.out.println("Choose Category: ");

        //Category Number 
        Scanner tscan = new Scanner(System.in);
        int J = tscan.nextInt();
        String T="";
        int j=1;
        for (Map.Entry<String, ArrayList<Item>> entry : b.entrySet()){
            if(j==J) {T= entry.getKey(); break;}
            j++;
        }

        //Printing items under that category
        for(int k=0; k<b.get(T).size(); k++)
            b.get(T).get(k).print();
        
        //Getting back to the merchant menu
        home thome = new home();
        thome.MerchantMenu(c);
    }

    @Override
    public void addCont(double a){
        contribution+= a*0.005;
    }

    void Rewardswon(ArrayList<Item> d, int a){
        System.out.println("Rewards awarded till now are: ");
        //Printing the rewards
        for(int i=0; i<d.size(); i++) System.out.println(d.get(i).getOffer()); 
        home thome= new home();
        thome.MerchantMenu(a);
    }

}


class Cart{

    //The queue of the cart
    private Queue<Item> QueueCart;

    //Constructor
    Cart(){
        QueueCart= new LinkedList<>();
    }

    //Adding it to the cart Lists
    void addToCart(Item a){
        QueueCart.add(a);
    }

    //Removing from the cart
    Item removefromcart(){
        return QueueCart.remove();
    }

    //Returning the cart Lists
    Queue<Item> getcartlist(){
        return QueueCart;
    } 

    void Empty(){
        QueueCart= new LinkedList<>();
    }
    
}


class Item implements Prints{

    //Private Variables
    private final int uid;
    private final String name;
    private double price;
    private int quantity;
    private String offer;
    private final String category;
    public final String merchantname;

    //Constructor
    Item(int a, String b, double c, int d, String e, String f){
        uid= a;
        name= b;
        price= c;
        quantity= d;
        category= e;
        merchantname= f;
    }

    //Print
    @Override
    public void print(){
        System.out.println("");
        System.out.println("ID: " + uid);
        System.out.println("NAME: " + name);
        System.out.println("PRICE: " + price);
        System.out.println("QUANTITY: " + quantity);
        System.out.println("OFFER: " + offer);
        System.out.println("CATEGORY: " + category);
        System.out.println("MERCHANT NAME: " + merchantname);
        System.out.println("");
    }

    //Setters
    void setOffer(String a){
        offer= a;
    }

    void setPrice(double a){
        price= a;
    }

    void setQuantity(int a){
        quantity= a;
    }

    //Getters
    String getName(){
        return name;
    }

    String getCategory(){
        return category;
    }

    String getOffer(){
        return offer;
    }

    double getPrice(){
        return price;
    }

    int getuid(){
        return uid;
    }

    int getQuantity(){
        return quantity;
    }

    String getMerchantName(){
        return merchantname;
    }

    @Override
    public void addCont(double a){return;}

}