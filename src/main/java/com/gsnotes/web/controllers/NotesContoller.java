package com.gsnotes.web.controllers;

import com.gsnotes.bo.*;
import com.gsnotes.bo.Module;
import com.gsnotes.services.IEnseignantService;
import com.gsnotes.services.IEtudiantService;
import com.gsnotes.services.IModuleService;
import com.gsnotes.utils.export.ExcelExporterByModule;
import com.gsnotes.web.models.NotesModel;
import com.gsnotes.web.models.UserAndAccountInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class NotesContoller {
    @Autowired
    private HttpSession httpSession;

    @Autowired
    private IModuleService moduleService; // On obtient par injection automatique le service

    @Autowired
    private IEtudiantService etudiantService;

    @Autowired
    IEnseignantService enseignantService;


    @RequestMapping(value = "/prof/exportNoteModule", method = RequestMethod.POST)
    public void exportToExcel(HttpServletResponse response, @Valid @ModelAttribute("NotesModel") NotesModel data, Model model) throws IOException {

        String session = data.getSession();

        Module myModule = moduleService.getModuleById(data.getModule());
        String moduleName = myModule.getTitre();

        Niveau myNiveau = myModule.getNiveau();
        String niveauName = myNiveau.getTitre();

        String enseignantName = myModule.getEnseignant().getNom() + " " + myModule.getEnseignant().getPrenom();

        String semestre="Printemps";

        int numberOfElements = myModule.getElements().size();

        List<Etudiant> listEtudiants = new ArrayList<Etudiant>();

        String year = null;

            if ("Normale".equals(session)) {

                List<InscriptionModule> inscriptionModules = myModule.getInscriptionModules();

                List<InscriptionAnnuelle> inscriptionAnnuelles = new ArrayList<>();

                for (InscriptionModule inscriptionModule : inscriptionModules) {
                    inscriptionAnnuelles.add(inscriptionModule.getInscriptionAnnuelle());
                }

                for (InscriptionAnnuelle inscriptionAnnuelle : inscriptionAnnuelles) {
                    listEtudiants.add(inscriptionAnnuelle.getEtudiant());

                }

                for (Etudiant etu : listEtudiants) {
                    System.out.println(etu.getCin() + "     " + etu.getNom() + "    " + etu.getPrenom());
                }

                year = String.valueOf(inscriptionAnnuelles.get(0).getAnnee());

                List<Element> listeElements = myModule.getElements();
                int numberElements = listeElements.size();

                List<Float> coefficients = new ArrayList<>();

                if (numberOfElements > 1) {
                    for (Element el : listeElements) {
                        coefficients.add((float) el.getCurrentCoefficient());
                        System.out.println(el.getNom());
                    }
                }


                response.setContentType("application/octet-stream");
                DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
                String currentDateTime = dateFormatter.format(new Date());

                String headerKey = "Content-Disposition";
                String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
                response.setHeader(headerKey, headerValue);

                ExcelExporterByModule excelExporterByModule = etudiantService.prepareDataExport(session, moduleName, year, enseignantName, semestre, niveauName, listEtudiants, numberOfElements, coefficients);

                excelExporterByModule.export(response);


            }else{

                List<Element> elements = myModule.getElements();

                if (numberOfElements<=1){

                    List<InscriptionModule> inscriptionModules = myModule.getInscriptionModules();

                    List<InscriptionAnnuelle> inscriptionAnnuelles= new ArrayList<>();

                    for (InscriptionModule inscriptionModule:inscriptionModules) {
                        if(inscriptionModule.getNoteSN()<12){
                            inscriptionAnnuelles.add(inscriptionModule.getInscriptionAnnuelle());

                        }
                    }
                    year = String.valueOf(inscriptionAnnuelles.get(0).getAnnee());

                    for (InscriptionAnnuelle inscriptionAnnuelle:inscriptionAnnuelles) {
                        listEtudiants.add(inscriptionAnnuelle.getEtudiant());

                    }

                }else if(numberOfElements>=2){
                    List<InscriptionMatiere> inscriptionMatieresFirstElement = elements.get(0).getInscriptionMatieres();

                    List<InscriptionMatiere> inscriptionMatieresForSecondeElement = elements.get(1).getInscriptionMatieres();

                    List<InscriptionAnnuelle> inscriptionAnnuelles = new ArrayList<>();
                    for (int i = 0; i < inscriptionMatieresFirstElement.size(); i++) {
                        for (int j = 0; j < inscriptionMatieresForSecondeElement.size(); j++){

                            if(inscriptionMatieresFirstElement.get(i).getInscriptionAnnuelle().getIdInscription()==inscriptionMatieresForSecondeElement.get(j).getInscriptionAnnuelle().getIdInscription()
                                    && (inscriptionMatieresFirstElement.get(i).getNoteSN()*inscriptionMatieresFirstElement.get(i).getCoefficient()+inscriptionMatieresForSecondeElement.get(j).getNoteSN()*inscriptionMatieresForSecondeElement.get(j).getCoefficient())<12){

                                inscriptionAnnuelles.add(inscriptionMatieresFirstElement.get(i).getInscriptionAnnuelle());
                            }
                        }
                    }
                    year = String.valueOf(inscriptionAnnuelles.get(0).getAnnee());

                    for (InscriptionAnnuelle inscriptionAnnuelle :inscriptionAnnuelles) {

                        if(!listEtudiants.contains(inscriptionAnnuelle.getEtudiant())){
                            listEtudiants.add(inscriptionAnnuelle.getEtudiant());
                        }
                    }
                }

                List<Float> coefficients = new ArrayList<>();
                List<Element> listeElements = myModule.getElements();

                if (numberOfElements > 1) {
                    for (Element el : listeElements) {
                        coefficients.add((float) el.getCurrentCoefficient());
                        System.out.println(el.getNom());
                    }
                }


                response.setContentType("application/octet-stream");
                DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
                String currentDateTime = dateFormatter.format(new Date());

                String headerKey = "Content-Disposition";
                String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
                response.setHeader(headerKey, headerValue);

                ExcelExporterByModule excelExporterByModule = etudiantService.prepareDataExport(session, moduleName, year, enseignantName, semestre, niveauName, listEtudiants, numberOfElements, coefficients);

                excelExporterByModule.export(response);

            }

    }

    @RequestMapping(value = "/prof/showForm", method = RequestMethod.GET)
    public String allInfo(Model model) {
        List<Module> modules = moduleService.getAllModule();
        List<Element> elements = moduleService.getAllElement();
        List<Niveau> niveaux = moduleService.getAllNiveau();
        model.addAttribute("listemodule",modules);
        model.addAttribute("listeelement",elements);
        model.addAttribute("listeniveau",niveaux);
        UserAndAccountInfos userInfo = (UserAndAccountInfos) httpSession.getAttribute("userInfo");
        model.addAttribute("idEns", userInfo.getIdPersonne());
        return "prof/form";

    }
}


