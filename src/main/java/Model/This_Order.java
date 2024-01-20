package Model;

public class This_Order {
    private int id;
    private int id_client;
    private int id_produs;
    private int total;
    private int quantity;
    public This_Order(){

    }

    public This_Order(int id, int id_client, int id_produs, int total, int quantity) {
        super();
        this.id = id;
        this.id_client = id_client;
        this.id_produs = id_produs;
        this.total = total;
        this.quantity = quantity;
    }

    public This_Order(int id_client, int id_produs, int total, int quantity) {
        super();
        this.id_client = id_client;
        this.id_produs = id_produs;
        this.total = total;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_produs() {
        return id_produs;
    }

    public void setId_produs(int id_produs) {
        this.id_produs = id_produs;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
