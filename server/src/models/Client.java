package models;

public class Client extends User {

    private Trolley trolley;
    private double wallet;

    public Client(String nickName, String password, String name) {
        super(nickName, password, name);
        trolley = new Trolley();
    }

    public Trolley getTrolley() {
        return trolley;
    }

    public double getWallet() {
        return wallet;
    }

    public boolean isEnoughMoney(){
        return (trolley.calculateTotal() <= wallet);
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public Object[] toVectorObject(){
        return new Object[]{
                getNickName(),getPassword(),getName(),wallet
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Client)) {
            return false;
        }
        Client client = (Client) obj;
        return client.getNickName().equals(this.getNickName());
    }

    @Override
    public String toString() {
        System.out.println(getNickName());
        trolley.showProducts();
        return "Client{" +
                " name "+getName()+
                " pass "+getPassword()+
                " nick "+getNickName()+
                ", wallet=" + wallet +
                '}';
    }

}
