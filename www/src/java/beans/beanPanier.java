/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Item;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author cdi310
 */
public class beanPanier implements Serializable {

    HashMap<String, Item> map;
    
    public beanPanier() {
        this.map = new HashMap<>();
    }
    
    public void add(String ref, int qty) {
        if (this.map.containsKey(ref)) {
            Item i = this.map.get(ref);
            
            // i.setQuantite(i.getQuantite() + qty);
            i.delta(qty); // equivalent a la ligne du dessus
            if (i.getQuantite()<1) {
                del(ref);
            }
        } else {
            this.map.put(ref, new Item(ref, qty));
        }
    }
    
    public void add(String ref){
        add(ref, +1);
    }
    
    public void dec(String ref){
        add(ref, -1);
    }
    
    public void del(String ref){
        this.map.remove(ref);
    }
    
    public void clear(){
        map.clear();
    }
    
    public int getSize() { // nombre d'élements dans le panier
        return this.map.size();
    }
    
    public boolean isEmpty(){
        return this.map.isEmpty();
    }
    
    public Collection<Item> list() {
        return this.map.values();
    }
    
    public float getTotalHT(){
        return -1;
    }
    
    public float getTotalTTC(){
        return -1;
    }   
}
