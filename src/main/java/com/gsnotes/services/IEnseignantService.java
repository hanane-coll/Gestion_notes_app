package com.gsnotes.services;

import com.gsnotes.bo.Enseignant;

import java.util.List;

public interface IEnseignantService {

    List<Enseignant> getAllEnseignant();

    String getEnseignantNameById(Long id);



}
