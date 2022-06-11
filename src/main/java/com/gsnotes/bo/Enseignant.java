package com.gsnotes.bo;



import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;


/**
 * Represente un enseignant.
 * 
 * Un enseignant est un cas spéciale de l'Utilisateur
 * 
 * @author T. BOUDAA
 *
 */


@Entity
@PrimaryKeyJoinColumn(name = "idEnseighant")
public class Enseignant extends Utilisateur {



	private String specialite;

	@OneToMany(mappedBy = "enseignant" , cascade = CascadeType.ALL, targetEntity = Module.class)
	private List<Module> modules;


	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}






}