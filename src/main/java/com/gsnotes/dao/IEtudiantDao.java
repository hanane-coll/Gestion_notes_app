package com.gsnotes.dao;

import com.gsnotes.bo.Etudiant;
import com.gsnotes.bo.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEtudiantDao extends JpaRepository<Etudiant, Long>
{
    public List<Etudiant> getEtudiantsByNiveau(Niveau niveau);
}
