
package classes;

import java.io.Serializable;


public class Item implements Serializable {
    
    private String reference;
    private int quantite;

    public Item(String reference, int quantite) {
        this.reference = reference;
        this.quantite = quantite;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    public void delta(int qty) {
        this.quantite += qty;
    }
    
}
