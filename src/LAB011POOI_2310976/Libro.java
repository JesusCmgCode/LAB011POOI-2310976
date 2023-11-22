package LAB011POOI_2310976;


public class Libro {
    public String name;
    public Autor autor;
    public double precio;
    public int qty;

    public Libro(String name, Autor autor, double precio, int qty) {
        this.name = name;
        this.autor = autor;
        this.precio = precio;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public Autor getAutor() {
        return autor;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "'" + name + "' de " + autor.getName() + "(" + autor.getGender() + ") Precio " + autor.getEmail();
    }
}
