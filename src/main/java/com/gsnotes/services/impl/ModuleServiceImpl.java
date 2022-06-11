package com.gsnotes.services.impl;

import com.gsnotes.bo.Element;
import com.gsnotes.bo.Module;
import com.gsnotes.bo.Niveau;
import com.gsnotes.dao.IElementDao;
import com.gsnotes.dao.IModuleDao;
import com.gsnotes.dao.INiveauDao;
import com.gsnotes.services.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ModuleServiceImpl implements IModuleService {
    @Autowired
    private IModuleDao moduleDao;

    @Autowired
    private IElementDao elementDao;

    @Autowired
    private INiveauDao niveauDao;


    @Override
    public List<Module> getAllModule() {return moduleDao.findAll();}

    @Override
    public List<Element> getAllElement() {
        return elementDao.findAll();
    }

    @Override
    public List<Niveau> getAllNiveau() {
        return niveauDao.findAll();
    }

    @Override
    public Module getModuleById(Long id) {
        return moduleDao.getById(id);
    }

    @Override
    public Module getModuleByTitre(String titre) {
        return moduleDao.findModuleByTitre(titre);
    }

}
