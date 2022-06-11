package com.gsnotes.services;

import com.gsnotes.bo.Element;
import com.gsnotes.bo.Module;
import com.gsnotes.bo.Niveau;

import java.util.List;

public interface IModuleService {
    List<Module> getAllModule();
    List<Element> getAllElement();
    List<Niveau> getAllNiveau();
    Module getModuleById(Long id);

    //get module by titre
    Module getModuleByTitre(String titre);


}
