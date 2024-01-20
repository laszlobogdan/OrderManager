package Model;
//public recordBill(int id, int total);
public class Bill {
    private int id;
    private int total;
    public Bill() {

    }
    public Bill(int id, int total) {
        super();
        this.id = id;
        this.total = total;
    }
    public Bill(int total) {
        super();
        this.total = total;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Bill[" +
                "id=" + id +
                ", total=" + total +
                ']';
    }
}
