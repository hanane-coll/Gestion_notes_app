package com.gsnotes.services;

import com.gsnotes.bo.Etudiant;
import com.gsnotes.bo.Niveau;
import com.gsnotes.utils.export.ExcelExporter;
import com.gsnotes.utils.export.ExcelExporterByModule;

import java.util.List;

public interface IEtudiantService {
    public List<Etudiant> getEtudiantsByNiveau(Niveau niveau);
    public ExcelExporter prepareModuleExport(List<Etudiant> listEtudiants);
    public ExcelExporterByModule prepareDataExport(String session, String nomModule, String annee, String enseignantName, String semestre, String Classe, List<Etudiant> etudiants, int numberOfElements,List<Float> coeffs);
}