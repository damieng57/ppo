/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package td2.exo2;

import java.util.Comparator;

/**
 *
 * @author Damien
 */
public class ClientComparator implements Comparator<Client>{

	@Override
	public int compare(Client o1, Client o2) {
		String x1 = String.valueOf(o1.getCaClient());
		String x2 = String.valueOf(o2.getCaClient());
		
		return x1.compareTo(x2);
	}
	
}
