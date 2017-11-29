/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package td3.exo4.bdd.xml;

import java.sql.Connection;
import td3.exo4.bdd.Connexion;
import td3.exo4.bdd.DAOFactory;

/**
 *
 * @author user
 */
public class XmlDAOFactory extends DAOFactory{

	protected static final Connection connexion = Connexion.getInstance();

	@Override
	public XmlDAOPortefeuille getDaoPortefeuille() {
		return XmlDAOPortefeuille.getInstance(connexion);
	}

	@Override
	public XmlDAODevise getDaoDevise() {
		return XmlDAODevise.getInstance(connexion);
	}

}
