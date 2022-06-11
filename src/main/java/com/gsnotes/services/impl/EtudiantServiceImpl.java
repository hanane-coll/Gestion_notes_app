package com.gsnotes.services.impl;

import com.gsnotes.bo.Etudiant;
import com.gsnotes.bo.Niveau;
import com.gsnotes.dao.IEtudiantDao;
import com.gsnotes.services.IEtudiantService;
import com.gsnotes.utils.export.ExcelExporter;
import com.gsnotes.utils.export.ExcelExporterByModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EtudiantServiceImpl implements IEtudiantService {
    @Autowired
    IEtudiantDao etudiantDao;


    @Override
    public List<Etudiant> getEtudiantsByNiveau(Niveau niveau)
    {
        return etudiantDao.getEtudiantsByNiveau(niveau);
    }

    @Override
    public ExcelExporter prepareModuleExport(List<Etudiant> listEtudiants) {
        String[] columnNames = new String[] {"CNE", "NOM", "PRENOM" ,"Elément1","Elément2","Moyenne","Validation"};

        String[][] data = new String[listEtudiants.size()][7];
        int i = 0;
        for (Etudiant u : listEtudiants) {
            data[i][0] = u.getCne();
            data[i][1] = u.getNom();
            data[i][2] = u.getPrenom();
            data[i][3] = String.valueOf(0);
            data[i][4] = String.valueOf(0);
            data[i][5] = String.valueOf(0);
            data[i][6] = String.valueOf(0);
            i++;
        }

        return new ExcelExporter(columnNames,data,"Liste Etudiant");
    }

    @Override
    public ExcelExporterByModule prepareDataExport(String session, String nomModule, String annee, String enseignantName, String semestre, String Classe, List<Etudiant> etudiants, int numberOfElements,List<Float> coeffs) {


        //the seconde Table:


        // List<String> columns = List.of(new String[]{"id", "cne", "Nom", "Prénom"});
        List<String> columnNames = new ArrayList<>();
        columnNames.add("ID");
        columnNames.add("CNE");
        columnNames.add("Nom");
        columnNames.add("Prénom");


        //elements


        if(numberOfElements==1){
            columnNames.add("La Note Du Module");
        }else{

            for (int j = 1; j <= numberOfElements; j++) {
                columnNames.add("Element " + j);
            }

        }



        //moyenne

        columnNames.add("Moyenne");
        //validation

        columnNames.add("Validation");

        String[][] data = new String[etudiants.size()][4];

        int i = 0;
        for (Etudiant e : etudiants) {
            data[i][0] = String.valueOf(e.getIdUtilisateur());
            data[i][1] = e.getCne();
            data[i][2] = e.getNom();
            data[i][3] = e.getPrenom();

            i++;
        }


        //String columnNames[] = columns.toArray(new String[columns.size()]);


        return new ExcelExporterByModule(columnNames, data, "Etudiants", session, nomModule, annee, enseignantName, semestre, Classe, numberOfElements,coeffs);
    }
}
